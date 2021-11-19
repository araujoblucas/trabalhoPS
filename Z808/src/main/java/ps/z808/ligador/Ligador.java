package ps.z808.ligador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;
import static java.awt.image.ImageObserver.WIDTH;
import ps.z808.montador.Montador;
import ps.z808.montador.TD;
import ps.z808.montador.TU;
/*
Símbolos intersegmentos
    -Identificação implícita: montador assume que os símbolos não definidos no segmento são definidos externamente
    -Identificação explícita: uso de instruções de montagem
        EXTRN: símbolo utilizado internamente e definido externamente
        PUBLIC: símbolo definido internamente que pode ser utilizado externamente
*/
public class Ligador {
    
    @SuppressWarnings("static-access")
    public static void ligar() throws IOException, FileNotFoundException, InterruptedException {
        TSG tsg = new TSG();
        
        TD td1 = new TD();
        TD td2 = new TD();
        
        TU tu1 = new TU();
        TU tu2 = new TU();
        
        Montador.montar(td1, tu1, "codigoObjeto1");
        Montador.montar(td2, tu2, "codigoObjeto2");
        
        ArrayList<String[]> linhasSeg1 = lerSegmento(1);
        Integer tamanhoSeg1 = getTamanhoSegmento(linhasSeg1);
        ArrayList<String[]> linhasSeg2 = lerSegmento(2);
        
        passo1(tsg, td1, td2, tamanhoSeg1);
        passo2(linhasSeg1, linhasSeg2, tsg, td1, td2, tu1, tu2, tamanhoSeg1);
        
        saidaLigador();
    }
    
    private static void passo1(TSG tsg, TD td1, TD td2, int tamanhoSeg1) {
        tsg.setTabelaDef("segmento1", td1, 0);
        tsg.setTabelaDef("segmento2", td2, tamanhoSeg1);
    }
    
    private static void passo2(ArrayList<String[]> linhasSeg1, ArrayList<String[]> linhasSeg2, TSG tsg, TD td1, TD td2, TU tu1, TU tu2, int tamanhoSeg1) throws IOException {
        tsg.atualizaSimbolosExtrns();
        
        for(int cont = 0; cont < td1.getSimbolos().length; cont++) {
            for(int cont2 = 0; cont2 < tu1.getOcorrencias(cont).length; cont2++) {
                int endOcorrencia = tu1.getOcorrencias(cont)[cont2];
                for(int cont3 = 0; cont3 < linhasSeg1.size(); cont3++) {
                    if((endOcorrencia - linhasSeg1.get(cont3).length >= 0) && td1.getEndValor(cont) < 0) {
                        endOcorrencia -= linhasSeg1.get(cont3).length;
                    } else if(td1.getEndValor(cont) < 0) {
                        linhasSeg1.get(cont3)[endOcorrencia] = tsg.getEndValor(cont).toString();
                        cont3 = linhasSeg1.size();
                    } else {
                        cont3 = linhasSeg1.size();
                    }
                }
            }
        }
        
        for(int cont = 0; cont < td2.getSimbolos().length; cont++) {                //contador de simbolos da td2
            for(int cont2 = 0; cont2 < tu2.getOcorrencias(cont).length; cont2++) {  //contador de ocorrencias do simbolo cont
                int endOcorrencia = tu2.getOcorrencias(cont)[cont2];
                for(int cont3 = 0; cont3 < linhasSeg2.size(); cont3++) {            //contador de linhas p/ identificar end da ocorrencia
                    if(((endOcorrencia - linhasSeg2.get(cont3).length >= 0) && (td2.getEndValor(cont) >= 0) &&
                        (!Objects.equals(tu2.getOcorrencias(cont)[cont2], td2.getEndValor(cont)))) ||
                        ((endOcorrencia - linhasSeg2.get(cont3).length >= 0) && td2.getEndValor(cont) < 0)) {
                        endOcorrencia -= linhasSeg2.get(cont3).length;
                    } else if(td2.getEndValor(cont) >= 0 && (!Objects.equals(tu2.getOcorrencias(cont)[cont2], td2.getEndValor(cont)))) {
                        Integer temp = Integer.parseInt(linhasSeg2.get(cont3)[endOcorrencia]) + tamanhoSeg1;
                        linhasSeg2.get(cont3)[endOcorrencia] = temp.toString();
                        cont3 = linhasSeg2.size();
                    } else if(td2.getEndValor(cont) < 0) {
                        linhasSeg2.get(cont3)[endOcorrencia] = tsg.getEndValor(td1.getSimbolos().length + cont).toString();
                        cont3 = linhasSeg2.size();
                    } else {
                        cont3 = linhasSeg2.size();
                    }
                }
            }
        }
        
        tu2.atualizaTUSeg2(tamanhoSeg1);
        
        FileWriter wArquivo = new FileWriter("codigoObjeto.o");
        BufferedWriter wBuffer = new BufferedWriter(wArquivo);
        escreverSegmento(linhasSeg1, wBuffer);
        escreverSegmento(linhasSeg2, wBuffer);        
        wBuffer.close();
        wArquivo.close();
    }
    
    private static ArrayList<String[]> lerSegmento(Integer numSegmento) throws FileNotFoundException, IOException {
        ArrayList<String[]> linhasSeg = new ArrayList<>();
        FileReader rSeg = new FileReader("codigoObjeto" + numSegmento + ".o");
        BufferedReader rBuffer = new BufferedReader(rSeg);
        while(rBuffer.ready()) {
            linhasSeg.add(rBuffer.readLine().split(" "));
        }
        rBuffer.close();
        rSeg.close();
        
        int cont = 0;
        while(linhasSeg.get(cont)[0].contentEquals("public") || linhasSeg.get(cont)[0].contentEquals("PUBLIC") ||
            linhasSeg.get(cont)[0].contentEquals("extrn") || linhasSeg.get(cont)[0].contentEquals("EXTRN")) {
            linhasSeg.remove(cont);
            cont = 0;
        }
        return linhasSeg;
    }
    
    private static Integer getTamanhoSegmento(ArrayList<String[]> linhasSeg) {
        Integer tamanhoSeg = 0;
        for(int cont = 0; cont < linhasSeg.size(); cont++) {
            if(linhasSeg.get(cont)[0].contentEquals("public") || linhasSeg.get(cont)[0].contentEquals("PUBLIC") ||
                linhasSeg.get(cont)[0].contentEquals("extrn") || linhasSeg.get(cont)[0].contentEquals("EXTRN")) {     
            } else {
                tamanhoSeg += linhasSeg.get(cont).length;
            }
        }
        return tamanhoSeg;
    }
    
    private static void escreverSegmento(ArrayList<String[]> linhasSeg, BufferedWriter wBuffer) throws IOException {
        String temp = "";
        for(int cont = 0; cont < linhasSeg.size(); cont++) {
            for(int contAux = 0; contAux < linhasSeg.get(cont).length; contAux++) {
                temp += linhasSeg.get(cont)[contAux] + " ";
            }
            wBuffer.write(temp, 0, temp.length());
            wBuffer.newLine();
            temp = "";
        }
    }
    
    private static void saidaLigador() throws FileNotFoundException, IOException {
        FileReader arqCodigoLigado = new FileReader("codigoObjeto.o");
        BufferedReader bufferCodigoLigado = new BufferedReader(arqCodigoLigado);
        String codigoLigado = "";
        while(bufferCodigoLigado.ready()) {
            codigoLigado += bufferCodigoLigado.readLine() + "\n";
        }
        JOptionPane.showMessageDialog(null, codigoLigado, "Código Ligado", WIDTH);
    }
}