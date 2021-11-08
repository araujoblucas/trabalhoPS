package ps.z808.montador;
import java.util.ArrayList;

public class TS {
    private ArrayList<String> simbolos;                                         
    private ArrayList<Integer> endSimbolos;
    private ArrayList<Integer> endValores;
    protected int lineCounter;
    protected int locationCounter;

    protected TS() {
        this.lineCounter = 0;
        this.locationCounter = 0;
        this.simbolos = new ArrayList<>();
        this.endSimbolos = new ArrayList<>();
        this.endValores = new ArrayList<>();
    }
    
    protected String[] getSimbolos() {
        String[] temp = new String[simbolos.size()];
        simbolos.toArray(temp);
        return temp;
    }
    
    protected Integer getEndValor(String simbolo) {
        return endValores.get(simbolos.indexOf(simbolo));
    }

    protected void setSimbolo(String simbolo) {
        simbolos.add(simbolo);
        endSimbolos.add(locationCounter);
        endValores.add(-1);
    }

    protected void setEndValor(String simbolo) {
        endValores.set(simbolos.indexOf(simbolo), locationCounter);
    }
}