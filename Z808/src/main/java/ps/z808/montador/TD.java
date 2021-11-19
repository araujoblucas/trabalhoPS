package ps.z808.montador;
import java.util.ArrayList;
/*
Tabela de Definição
    -Lista cada símbolo global definido internamente
    -Construída pelo montador
    -Uma entrada para cada símbolo incluindo o nome do segmento de programa
    -Cada entrada da tabela é composta:
        Símbolo
        Endereço
*/
public class TD {
    private ArrayList<String> simbolos;
    private ArrayList<Integer> endValores;
    protected int lineCounter;
    protected int locationCounter;

    public TD() {
        this.lineCounter = 0;
        this.locationCounter = 0;
        this.simbolos = new ArrayList<>();
        this.endValores = new ArrayList<>();
    }
    
    public String[] getSimbolos() {
        String[] temp = new String[simbolos.size()];
        simbolos.toArray(temp);
        return temp;
    }
    
    public Integer getEndValor(String simbolo) {
        return endValores.get(simbolos.indexOf(simbolo));
    }
    
    public Integer getEndValor(int index) {
        return endValores.get(index);
    }

    protected void setSimbolo(String simbolo) {
        simbolos.add(simbolo);
        endValores.add(-1);
    }
    
    protected void setEndValor(String simbolo) {
        endValores.set(simbolos.indexOf(simbolo), locationCounter);
    }
}