/*
instruções que foram implementadas com outros opcodes na máquina virtual:
*ret    |   C3   |  OK com opcode EF
*RET    |   16   |  OK com opcode EF
*CALL   |   15   |  OK com opcode E8
*SUB    |   06   |  OK com opcode 2D

diretiva não utilizada -> OFFSET
ação não informada -> int cte | CD cte
instrução diferente da máquina virtual -> STOP  |   11
instrução alterada na máquina virtual -> STORE  |   07

flag inserido na PMUI
instruções load, divide, mult e copy inseridos na VMUI
chamadas de métodos corrigidas na VMUI
*/
package ps.z808.montador;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import ps.z808.pm.PMUI;

public class Montador {
    //instruções que antecedem operandos
    static String[] instOpd = {"05", "2D", "3D", "25", "0D", "35", "EB", "74", "75", "7A", "59", "12", "08", "10", "14"};
    
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        PMUI PM = new PMUI();
        PM.setVisible(true);                                                    //chamada processador de macro
        while(!PM.fPM) {}                                                       //aguarda finalização do processador de macro
        
        ArrayList<String[]> linhas = new ArrayList<>();
        FileReader rArquivo = new FileReader("saida.asm");
        BufferedReader rBuffer = new BufferedReader(rArquivo);
        FileWriter wArquivo = new FileWriter("codigoObjeto.o");
        BufferedWriter wBuffer = new BufferedWriter(wArquivo);
        
        String[] temp;                                                          //utilizada p/ manipulação de cada linha lida do arquivo
        ArrayList<String> tempA = new ArrayList<>();
        while(rBuffer.ready()) {
            //laço p/ leitura do arquivo linha a linha
            /*transforma cada instrução em um vetor de strings, cada parte da instrução ocupa uma posição
            após limpar os espaços e vírgulas, armazena cada vetor no ArrayList linhas*/
            temp = rBuffer.readLine().split(" ");
            for(int cont = 0; cont < temp.length; cont++) {
                temp[cont] = temp[cont].trim();
                if(temp[cont].equalsIgnoreCase("")) {
                } else if(temp[cont].indexOf(",") > 0) {
                    tempA.add(temp[cont].substring(0, temp[cont].indexOf(",")));
                    temp[cont] = temp[cont].substring(temp[cont].indexOf(",") + 1);
                    cont--;
                } else {
                    tempA.add(temp[cont]);
                }
            }
            temp = new String[tempA.size()];
            tempA.toArray(temp);
            linhas.add(temp);
            tempA.clear();
        }
        
        TS ts = new TS();                                                       //cria tabela de símbolos
        passo1(ts, linhas);
        passo2(ts, linhas, wBuffer);
        
        rBuffer.close();
        rArquivo.close();
        wBuffer.close();
        wArquivo.close();
        
        saidaMontador();
        
        System.exit(0);
    }
    
    private static void passo1(TS ts, ArrayList<String[]> linhas) {
        for(; ts.lineCounter < linhas.size(); ts.lineCounter++) {
        //repete enquanto o contador de linhas for menor do que o número de linhas do arquivo de entrada
            for(int cont = 0; cont < linhas.get(ts.lineCounter).length; cont++) {
            //repete enquanto cont for menor do que a quantidade de partes da instrução
                switch(linhas.get(ts.lineCounter)[cont]) {
                //verifica se é uma instrução ou uma referência aos registradores AX/DX
                //se não, entra no default
                    case "AX":
                        linhas.get(ts.lineCounter)[cont] = "C0";
                        break;
                    case "DX":
                        linhas.get(ts.lineCounter)[cont] = "C2";
                        break;
                    case "add": 
                    case "ADD": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "03", "05");
                        break;
                    case "div": 
                    case "DIV": 
                        linhas.get(ts.lineCounter)[cont] = "F7";
                        cont++;
                        ts.locationCounter++;
                        if(linhas.get(ts.lineCounter)[cont].contentEquals("SI")) {
                            linhas.get(ts.lineCounter)[cont] = "F6";
                        } else if(linhas.get(ts.lineCounter)[cont].contentEquals("AX")) {
                            linhas.get(ts.lineCounter)[cont] = "F0";
                        }
                        break;
                    case "mul": 
                    case "MUL": 
                        linhas.get(ts.lineCounter)[cont] = "F7";
                        cont++;
                        ts.locationCounter++;
                        if(linhas.get(ts.lineCounter)[cont].contentEquals("SI")) {
                            linhas.get(ts.lineCounter)[cont] = "E6";
                        } else if(linhas.get(ts.lineCounter)[cont].contentEquals("AX")) {
                            linhas.get(ts.lineCounter)[cont] = "E0";
                        }
                        break;
                    case "sub": 
                    case "SUB": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "2B", "2D");
                        break;
                    case "cmp": 
                    case "CMP": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "3B", "3D");
                        break;
                    case "and": 
                    case "AND": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "23", "25");
                        break;
                    case "not": 
                    case "NOT": 
                        linhas.get(ts.lineCounter)[cont] = "F8";
                        break;
                    case "or": 
                    case "OR": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "0B", "0D");
                        break;
                    case "xor": 
                    case "XOR": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "33", "35");
                        break;
                    case "jmp": 
                    case "JMP": 
                        linhas.get(ts.lineCounter)[cont] = "EB";
                        break;
                    case "jz": 
                    case "JZ": 
                        linhas.get(ts.lineCounter)[cont] = "74";
                        break;
                    case "jnz": 
                    case "JNZ": 
                        linhas.get(ts.lineCounter)[cont] = "75";
                        break;
                    case "jp": 
                    case "JP": 
                        linhas.get(ts.lineCounter)[cont] = "7A";
                        break;
                    case "call": 
                    case "CALL": 
                        linhas.get(ts.lineCounter)[cont] = "E8";
                        break;
                    case "ret": 
                    case "RET": 
                        linhas.get(ts.lineCounter)[cont] = "EF";
                        break;
                    case "hlt": 
                    case "HLT": 
                        linhas.get(ts.lineCounter)[cont] = "EE";
                        break;
                    case "pop": 
                    case "POP": 
                        linhas.get(ts.lineCounter)[cont] = verificaInst(linhas.get(ts.lineCounter)[cont + 1], "58", "59");
                        break;
                    case "popf": 
                    case "POPF": 
                        linhas.get(ts.lineCounter)[cont] = "9D";
                        break;
                    case "push": 
                    case "PUSH": 
                        linhas.get(ts.lineCounter)[cont] = "50";
                        break;
                    case "pushf": 
                    case "PUSHF": 
                        linhas.get(ts.lineCounter)[cont] = "9C";
                        break;
                    case "store": 
                    case "STORE": 
                        linhas.get(ts.lineCounter)[cont] = "07";
                        break;
                    case "read": 
                    case "READ": 
                        linhas.get(ts.lineCounter)[cont] = "12";
                        break;
                    case "write": 
                    case "WRITE":
                        linhas.get(ts.lineCounter)[cont] = "08";
                        break;
                    case "load":
                    case "LOAD":
                        linhas.get(ts.lineCounter)[cont] = "03";
                        break;
                    case "copy":
                    case "COPY":
                        linhas.get(ts.lineCounter)[cont] = "13";
                        break;
                    case "divide":
                    case "DIVIDE":
                        linhas.get(ts.lineCounter)[cont] = "10";
                        break;
                    case "mult":
                    case "MULT":
                        linhas.get(ts.lineCounter)[cont] = "14";
                        break;
                    default:
                        if(cont == 0) {
                        //se cont = 0, então é label
                            if(!Arrays.asList(ts.getSimbolos()).contains(linhas.get(ts.lineCounter)[cont])) {
                            //se ainda não estiver armazenada na tabela de símbolos
                                ts.setSimbolo(linhas.get(ts.lineCounter)[cont]);
                                //armazena a label como símbolo
                                ts.setEndValor(linhas.get(ts.lineCounter)[cont]);
                                //armazena o endereço referenciado pela label
                                ts.locationCounter--;                           
                                //decrementa contador de endereços porque a cada repetição ele é incrementado e
                                //label não ocupa endereço
                            } else if((linhas.get(ts.lineCounter).length - cont) > 1 && 
                            (linhas.get(ts.lineCounter)[cont + 1].contentEquals("EQU") || 
                            linhas.get(ts.lineCounter)[cont + 1].contentEquals("equ"))
                            && ts.getEndValor(linhas.get(ts.lineCounter)[cont]) < 0) {
                            //se já estiver na tabela de símbolos, for seguida da diretiva EQU/equ e não possuir endereço associado armazenado
                                ts.setEndValor(linhas.get(ts.lineCounter)[cont]);
                                //armazena o endereço referenciado pela label
                                String temp = linhas.get(ts.lineCounter)[linhas.get(ts.lineCounter).length - 1];
                                linhas.set(ts.lineCounter, new String[1]);
                                linhas.get(ts.lineCounter)[0] = temp;
                                //atualiza a linha p/ que contenha somente o valor
                                //não precisa decrementar o contador de endereços porque o único conteúdo da linha passa a ser o valor
                            } else if(ts.getEndValor(linhas.get(ts.lineCounter)[cont]) < 0) {
                            //se já estiver na tabela de símbolos e não possuir endereço associado
                                ts.setEndValor(linhas.get(ts.lineCounter)[cont]);
                                //armazena o endereço referenciado pela label
                                ts.locationCounter--;
                                //decrementa contador de endereços porque a cada repetição ele é incrementado e
                                //label não ocupa endereço
                            }
                        } //não é label, então é referência a símbolo
                        else if(Arrays.asList(instOpd).contains(linhas.get(ts.lineCounter)[cont - 1])) {
                        //se a referência ao símbolo for antecedida por uma instrução que suporta operando
                            if(!Arrays.asList(ts.getSimbolos()).contains(linhas.get(ts.lineCounter)[cont])) {
                            //se o símbolo ainda não estiver na tabela de símbolos, adiciona a ela
                                ts.setSimbolo(linhas.get(ts.lineCounter)[cont]);
                            }
                        } else if(linhas.get(ts.lineCounter)[cont - 1].contentEquals("03") &&
                        !Arrays.asList(ts.getSimbolos()).contains(linhas.get(ts.lineCounter)[cont])) {
                        //se a referência ao símbolo for antecedida pela instrução LOAD/load e
                        //o símbolo ainda não estiver na tabela de símbolos, adiciona a ela
                            ts.setSimbolo(linhas.get(ts.lineCounter)[cont]);
                        } else if(cont > 1 && (linhas.get(ts.lineCounter)[cont - 1].contentEquals("C0") || 
                        linhas.get(ts.lineCounter)[cont - 1].contentEquals("C2")) && 
                        linhas.get(ts.lineCounter)[cont - 2].contentEquals("07") &&
                        !Arrays.asList(ts.getSimbolos()).contains(linhas.get(ts.lineCounter)[cont])) {
                        //se a referência ao símbolo for antecedida pela instrução STORE/store e por uma referência a um registrador
                        //e o símbolo ainda não estiver na tabela de símbolos, adiciona a ela
                            ts.setSimbolo(linhas.get(ts.lineCounter)[cont]);
                        } else if((cont > 1 && linhas.get(ts.lineCounter)[cont - 2].contentEquals("13")) ||
                        (cont > 0 && linhas.get(ts.lineCounter)[cont - 1].contentEquals("13")) &&
                        !Arrays.asList(ts.getSimbolos()).contains(linhas.get(ts.lineCounter)[cont])) {
                        //se a referência ao símbolo for antecedida pela instrução COPY/copy e
                        //o símbolo ainda não estiver na tabela de símbolos, adiciona a ela
                            ts.setSimbolo(linhas.get(ts.lineCounter)[cont]);
                        } else {
                        //se nenhum dos casos, então o formato da instrução tratada não é válido
                            System.out.println("Formato de instrução inválido! Linha " + ts.lineCounter + ", Posição " + 
                                (cont - 1) + " e " + cont + ", Endereço " + ts.locationCounter);
                        }
                        break;
                }
                ts.locationCounter++;                                           //incrementa contador de endereços
            }
        }
    }
    
    private static void passo2(TS ts, ArrayList<String[]> linhas, BufferedWriter wBuffer) throws IOException {
        String linha = "";                                                      //recebe a instrução montada p/ escrever na saída
        for(int cont = 0; cont < ts.lineCounter; cont++) {
        //repete enquanto cont for menor do que o contador de linhas
            for(int contAux = 0; contAux < linhas.get(cont).length; contAux++) {
            //repete enquanto contAux for menor do que a quantidade de partes da instrução
                if(contAux == 0 && Arrays.asList(ts.getSimbolos()).contains(linhas.get(cont)[contAux])) {
                //se for a primeira posição do vetor e o conteúdo corresponder a um símbolo armazenado na tabela, então é label
                    contAux++;
                    //incrementa o contador p/ manter na linha somente o conteúdo da instrução referenciada pela label
                } else if(Arrays.asList(ts.getSimbolos()).contains(linhas.get(cont)[contAux])) {
                //se a string corresponder a um símbolo armazenado na tabela, então é referência a ele
                    linhas.get(cont)[contAux] = ts.getEndValor(linhas.get(cont)[contAux]).toString();
                    //substitui o símbolo pelo endereço de memória onde o valor dele se encontra
                }
                linha += linhas.get(cont)[contAux] + " ";
            }
            wBuffer.write(linha, 0, linha.length());                            //escreve a instrução montada na saída
            wBuffer.newLine();
            linha = "";
        }
    }
    
    private static String verificaInst(String aux, String op1, String op2) {
    //método criado p/ auxiliar na substituição das instruções pelos seus opcodes
    //diferencia instruções que podem ser aplicadas a registradores e também a operandos
        if(aux.contentEquals("AX") || aux.contentEquals("DX")) {
        //se for aplicada a um registrador, retorna opcode 1
            return op1;
        } else {
        //senão é aplicada a operando, retorna opcode 2
            return op2;
        }
    }
    
    private static void saidaMontador() throws FileNotFoundException, IOException {
        FileReader arqCodigoMontado = new FileReader("codigoObjeto.o");
        BufferedReader bufferCodigoMontado = new BufferedReader(arqCodigoMontado);
        String codigoMontado = "";
        while(bufferCodigoMontado.ready()) {
            codigoMontado += bufferCodigoMontado.readLine() + "\n";
        }
        JOptionPane.showMessageDialog(null, codigoMontado, "Código Montado", WIDTH);
    }
}