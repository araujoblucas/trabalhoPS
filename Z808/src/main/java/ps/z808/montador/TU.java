package ps.z808.montador;
import java.util.ArrayList;
/*
Tabela de Uso
    -Lista cada uso interno de um símbolo global
    -Construída pelo montador
    -Cada entrada da tabela é composta:
        Símbolo
        Location counter (Endereço relativo) do campo de operandos
*/
public class TU {
    private ArrayList<String> simbolos;
    private ArrayList<ArrayList<Integer>> endOcorrencias;
    protected int locationCounter;

    public TU() {
        this.locationCounter = 0;
        this.simbolos = new ArrayList<>();
        this.endOcorrencias = new ArrayList<>();
    }

    private void setSimbolo(String simbolo) {
        simbolos.add(simbolo);
        endOcorrencias.add(new ArrayList<>());
        endOcorrencias.get(endOcorrencias.size() - 1).add(locationCounter);
    }
    
    protected void setSimboloGlobal(String simbolo) {
        simbolos.add(simbolo);
        endOcorrencias.add(new ArrayList<>());
    }
    
    protected void setOcorrencia(String simbolo) {
        if(simbolos.indexOf(simbolo) == -1) {
            setSimbolo(simbolo);
        } else {
            endOcorrencias.get(simbolos.indexOf(simbolo)).add(locationCounter);
        }
    }
    
    public Integer[] getOcorrencias(int index) {
        Integer[] ocorrencias = new Integer[endOcorrencias.get(index).size()];
        endOcorrencias.get(index).toArray(ocorrencias);
        return ocorrencias;
    }
    
    public void atualizaTUSeg2(int tamanhoSeg1) {
        for(int cont = 0; cont < endOcorrencias.size(); cont++) {
            for(int contAux = 0; contAux < endOcorrencias.get(cont).size(); contAux++) {
                endOcorrencias.get(cont).set(contAux, endOcorrencias.get(cont).get(contAux) + tamanhoSeg1);
            }
        }
    }
}