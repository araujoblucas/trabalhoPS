package ps.vm;

import java.util.Arrays;

public class Memoria {
    private String[] palavras;
   
    public Memoria() {
        this.palavras = new String[32768];
        Arrays.fill(palavras, "");
        palavras[2] = "10";
        for(int cont = 3; cont < (3 + Integer.parseInt(palavras[2])); cont++) {
            palavras[cont] = "**pilha**";
        }
    }
    
    protected void setPalavra(String palavra) {
        for(int cont = (3 + Integer.parseInt(palavras[2])); cont < 32768; cont++) {
            if(palavras[cont].equalsIgnoreCase("")) {
                palavras[cont] = palavra;
            }
        }
    }
    
    protected void setPalavra(String palavra, int index) {
        if(index > (3 + Integer.parseInt(palavras[2]))) {
            palavras[index] = palavra;
        }
    }
    
    protected String getPalavra(int index) {
        return palavras[index];
    }
    
    protected void setLinha(String[] linha) {
        boolean flag;
        for(int cont = (3 + Integer.parseInt(palavras[2])); cont < 32768; cont++) {
            if(palavras[cont].equalsIgnoreCase("")) {
                flag = true;
                for(int aux = 0; aux < linha.length; aux++) {
                    if(!palavras[cont + aux].equalsIgnoreCase("")) {
                        flag = false;
                    }
                }
                if(flag) {
                    System.arraycopy(linha, 0, palavras, cont, linha.length);
                    return;
                }
            }
        }
    }
    
    protected void pushPilha(String palavra) {
        palavras[getEndTopoPilha() + 1] = palavra;
    }
    
    protected void popPilha() {
        palavras[getEndTopoPilha()] = "**pilha**";
    }
    
    protected int getEndTopoPilha() {
        for(int cont = 0; cont < (3 + Integer.parseInt(palavras[2])); cont++) {
            if(palavras[cont].equalsIgnoreCase("**pilha**")) {
                return (cont - 1);
            }
        }
        return 0;                                                               //pilha cheia
    }
    
    protected int getMaxPilha() {
        return Integer.parseInt(palavras[2]);
    }
    
    protected int getEndMaxPilha() {
        return (3 + Integer.parseInt(palavras[2]));
    }
}
