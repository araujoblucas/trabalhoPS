package ps.z808.ligador;
import java.util.ArrayList;
import ps.z808.montador.TD;
/*
Tabela de Símbolos Globais (TSG)
    -“Internal symbol table” ou “Linkage symbol table”
    -Armazena todos os símbolos globais definidos
    -União das tabelas de definição dos diferentes segmentos
    -Cada entrada da tabela é composta:
        Símbolo
        Endereço
*/
public class TSG {
    private ArrayList<String> simbolos;
    private ArrayList<Integer> endValores;

    public TSG() {
        this.simbolos = new ArrayList<>();
        this.endValores = new ArrayList<>();
    }
    
    protected void setTabelaDef(String nomeSegmento, TD td, int incremento) {
        for(int cont = 0; cont < td.getSimbolos().length; cont++) {
            simbolos.add(nomeSegmento + ":" + td.getSimbolos()[cont]);
            if(td.getEndValor(td.getSimbolos()[cont]) < 0) {
                endValores.add(td.getEndValor(td.getSimbolos()[cont]));
            } else {
                endValores.add(td.getEndValor(td.getSimbolos()[cont]) + incremento);
            }
        }
    }
    
    protected void atualizaSimbolosExtrns() {
        for(int cont = 0; cont < simbolos.size(); cont++) {
            if(endValores.get(cont) < 0) {
                String temp = simbolos.get(cont).split(":")[1];
                for(int contAux = 0; contAux < simbolos.size() || endValores.get(cont) < 0; contAux++) {
                    if(simbolos.get(contAux).split(":")[1].contentEquals(temp) && contAux != cont) {
                        endValores.set(cont, endValores.get(contAux));
                    }
                }
            }
        }
    }
    
    protected Integer getEndValor(int index) {
        return endValores.get(index);
    }
}
