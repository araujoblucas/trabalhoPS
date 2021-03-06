package ps.z808.vm;

public class Reg2B {
    private Boolean[] registrador;
    private String representacaoString;
    private Integer representacaoInt;
    private boolean sinal;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    protected Reg2B() {
        this.registrador = new Boolean[16];
        this.representacaoString = new String();
        setRegistrador("", false);
    }
    
    protected String getRepresentacaoString() {                                 //binario
        return representacaoString;
    }
    
    protected Integer getRepresentacaoInt() {                                   //decimal
        return representacaoInt;
    }
    
    protected Boolean get1Bit(int index) {
        return registrador[index];
    }
    
    protected void setRegistrador(String conteudo, boolean sinal) {             //binario
        this.sinal = sinal;
        setRepresentacaoString(conteudo);
        setRepresentacaoInt(representacaoString);
        char[] temp = representacaoString.toCharArray();
        int cont;
        for(cont = 0; cont < 16; cont++) {
            switch (temp[cont]) {
                case '0':
                    registrador[cont] = false;
                    break;
                case '1':
                    registrador[cont] = true;
                    break;
                default:
                    System.out.println("ERRO!");
                    break;
            }
        }
    }
    
    protected void setRegistrador(Integer conteudo) {                           //decimal
        if(conteudo < 0) {
            sinal = true;
            conteudo *= (-1);
        } else {
            sinal = false;
        }
        representacaoInt = 0;
        representacaoString = "";
        int cont, aux = 0;
        for(cont = 32768; aux++ < 16; cont /= 2) {
            if(conteudo < cont*2 && conteudo >= cont) {
                representacaoString += "1";
                conteudo -= cont;
            } else{
                representacaoString += "0";
            }
        }
        setRegistrador(representacaoString, sinal);
    }
    
    private void setRepresentacaoString(String conteudo) {
        representacaoString = "";
        int cont;
        for(cont = 0; cont < 16 - conteudo.length(); cont++) {
            representacaoString += "0";
        }
        representacaoString += conteudo;
    }
    
    private void setRepresentacaoInt(String conteudo) {
        representacaoInt = 0;
        int cont, aux = 15;
        char[] temp = conteudo.toCharArray();
        for(cont = 1; aux >= 0; cont *= 2) {
            if(temp[aux--] == '1') {
                representacaoInt += cont;
            }
        }
        if(sinal) {
            representacaoInt *= (-1);
        }
    }
    
    protected void atualiza1Bit(int index, Boolean valor) {
        registrador[index] = valor;
        if(valor) {
            representacaoString = representacaoString.substring(0, index) + "1" + representacaoString.substring(index + 1);
        } else {
            representacaoString = representacaoString.substring(0, index) + "0" + representacaoString.substring(index + 1);
        }
        setRepresentacaoInt(representacaoString);
    }
}
