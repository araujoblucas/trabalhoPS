/*
Flag 0 e método verificaCarry -> CF (Carry/Borrow): Recebe 1 se uma operação de adição resulta em "carry" (vai-um) ou se uma operação de subtração resulta em "borrow" (vem-um). Se nenhum "carry" ou "borrow" ocorrer após uma adição ou subtração, recebe 0
Endereçamentos e SI
*/
package ps.z808.vm;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import static ps.z808.vm.Carregador.mem;
/*
Tamanho da memória: 64 KB (65536 bytes -> 32768 palavras)
Palavra de memória: 16 bits (2 bytes)
Unidade de endereçamento: palavra
*/
public class VMUI extends javax.swing.JFrame {
    
    public VMUI() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Máquina Virtual Z808 - Grupo 100");

        jLabel1.setText("AX");

        jLabel2.setText("DX");

        jLabel3.setText("SR");

        jLabel4.setText("SP");

        jLabel5.setText("IP");

        jLabel6.setText("SI");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel7.setText("Memória");

        jLabel8.setText("Endereços");

        jLabel9.setText("Valores");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jMenu1.setText("Arquivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Executar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jMenuItem3ActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(VMUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(VMUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre");

        jMenuItem2.setText("Informações");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField5)
                        .addComponent(jTextField1)
                        .addComponent(jTextField2)
                        .addComponent(jTextField3)
                        .addComponent(jTextField4)
                        .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(84, 84, 84))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 253, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>                        

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JOptionPane.showMessageDialog(null, "Universidade Federal de Pelotas - 2021/1 \n" + "Trabalho de Programação de Sistemas - Grupo 100 \n" + "\n" + "18200581 THALIA DJUNE COSTA LONGARAY\n" +
            "19100900 ALEJANDRO TOMAS REYES ALBERONI\n" +
            "11108244 MATEUS AL ALAM DE ALMEIDA\n");
    }                                          

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        exit(0);
    }                                          

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, FileNotFoundException, InterruptedException {
        Reg2B AX, DX, SP, SI, IP, SR;
        AX = new Reg2B();
        DX = new Reg2B();
        SP = new Reg2B();
        SI = new Reg2B();
        IP = new Reg2B();
        SR = new Reg2B();
        //AX (código C0) e DX (código C2)
        //SP (Stack Pointer) -> Usado pelas instruções push e pop
        //SI (Source Index) -> Usado para indexação de tabelas no endereçamento indireto
        //IP (Instruction Pointer) -> Armazena o endereço da próxima instrução a ser executada
        //SR (Status Register) -> 6 flags de um bit usados para indicar várias condições durante a execução do programa
            //0 -> CF (Carry/Borrow); 6 -> PF (Paridade); 8 -> ZF (Zero); 9 -> SF (Sinal); 12 -> OF (Overflow)
            //7 -> IF (Interrupção) -> Desconsiderada por não utilização
            //Testados após a realização de algumas operações; Os conteúdos podem ser usados para desvios condicionais
        Pilha pilha = new Pilha();
        IP.setRegistrador(mem.getEndMaxPilha());
        
        try {
            
        int cont;
        for(;;) {
            cont = IP.getRepresentacaoInt();
            switch (mem.getPalavra(cont)) {
                case "EF":
                case "EE":
                case "9D":
                case "9C":
                    IP.setRegistrador(cont + 1);
                    break;
                case "07":
                case "13":
                    IP.setRegistrador(cont + 3);
                    break;
                default:
                    IP.setRegistrador(cont + 2);
                    break;
            }
            switch(mem.getPalavra(cont++)) {
                case "03":
                    if(mem.getPalavra(cont).equals("C0")) {
                        addReg(AX, AX, SR);
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        addReg(AX, DX, SR);
                    } else {                                                    //nova instrução
                        load(AX, Integer.parseInt(mem.getPalavra(cont)), SR);
                    }
                    break;
                case "05":
                    addOpd(AX, getOperando(cont), SR);
                    break;
                case "F7":
                    switch (mem.getPalavra(cont)) {
                        case "F6":
                            div(AX, SI, DX, SR);
                            break;
                        case "F0":
                            div(AX, AX, DX, SR);
                            break;
                        case "E6":
                            mul(AX, SI, DX, SR);
                            break;
                        case "E0":
                            mul(AX, AX, DX, SR);
                            break;
                        default:
                            break;
                    }
                    break;
                case "2B":
                    if(mem.getPalavra(cont).equals("C0")) {
                        subReg(AX, AX, SR);
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        subReg(AX, DX, SR);
                    }
                    break;
                case "2D":
                    subOpd(AX, getOperando(cont), SR);
                    break;
                case "3D":
                    cmpOpd(AX, getOperando(cont), SR);
                    break;
                case "3B":
                    cmpReg(AX, DX, SR);
                    break;
                case "23":
                    if(mem.getPalavra(cont).equals("C2")) {
                        andReg(AX, DX, SR);
                    }
                    break;
                case "25":
                    andOpd(AX, getOperando(cont), SR);
                    break;
                case "F8":
                    not(AX, SR);
                    break;
                case "0B":
                    if(mem.getPalavra(cont).equals("C2")) {
                        orReg(AX, DX, SR);
                    }
                    break;
                case "0D":
                    orOpd(AX, getOperando(cont), SR);
                    break;
                case "33":
                    if(mem.getPalavra(cont).equals("C0")) {
                        xorReg(AX, AX, SR);
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        xorReg(AX, DX, SR);
                    }
                    break;
                case "35":
                    xorOpd(AX, getOperando(cont), SR);
                    break;
                case "EB":
                    jmp(IP, Integer.parseInt(mem.getPalavra(cont)));
                    break;
                case "74":
                    jz(IP, Integer.parseInt(mem.getPalavra(cont)), SR);
                    break;
                case "75":
                    jnz(IP, Integer.parseInt(mem.getPalavra(cont)), SR);
                    break;
                case "7A":
                    jp(IP, Integer.parseInt(mem.getPalavra(cont)), SR);
                    break;
                case "E8":
                    call(SP, IP, cont, pilha);
                    break;
                case "EF":
                    ret(SP, IP, pilha);
                    break;
                case "EE":
                    atualizaUI(AX, DX, SR, SP, IP, SI);
                    hlt();
                    break;
                case "58":
                    if(mem.getPalavra(cont).equals("C0")) {
                        popReg(SP, AX, pilha);
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        popReg(SP, DX, pilha);
                    }
                    break;
                case "59":                                                      
                    //opd<-[SP]
                    //pega valor do topo da pilha e armazena no endereço dado por opd
                    //falta opd no opcode
                    //instrução possui tamanho 2 -> opcode corrigido: 59 opd
                    popOpd(SP, getOperando(cont), pilha);
                    break;
                case "9D":
                    popF(SP, SR, pilha);
                    break;
                case "50":
                    if(mem.getPalavra(cont).equals("C0")) {
                        pushReg(SP, AX, pilha);
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        pushReg(SP, DX, pilha);
                    }
                    break;
                case "9C":
                    pushF(SP, SR, pilha);
                    break;
                case "07":
                    //opd<-AX/DX
                    //falta opd no opcode
                    //instrução possui tamanho 3 -> opcode corrigido: 07 C0/C2 opd
                    if(mem.getPalavra(cont).equals("C0")) {
                        store(AX, Integer.parseInt(mem.getPalavra(cont + 1)));
                    } else if(mem.getPalavra(cont).equals("C2")) {
                        store(DX, Integer.parseInt(mem.getPalavra(cont + 1)));
                    }
                    break;
                case "12":
                    read(Integer.parseInt(mem.getPalavra(cont)));
                    break;
                case "08":
                    write(Integer.parseInt(mem.getPalavra(cont)));
                    break;
                //novas instruções adicionadas
                case "13":
                    copy(Integer.parseInt(mem.getPalavra(cont)), Integer.parseInt(mem.getPalavra(cont + 1)));
                    break;
                case "10":
                    divide(AX, getOperando(cont), SR);
                    break;
                case "14":
                    mult(AX, getOperando(cont), SR);
                    break;
                default:
                    break;
            }
            atualizaUI(AX, DX, SR, SP, IP, SI);
        }
        
        } catch (InterruptedException ex) {
            Logger.getLogger(VMUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                          

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, FileNotFoundException, InterruptedException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VMUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VMUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VMUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VMUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Carregador.carregar();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VMUI().setVisible(true);
            }
        });
    }
    
    private static int getOperando(int index) {
        return Integer.parseInt(mem.getPalavra(Integer.parseInt(mem.getPalavra(index))));
    }
    
    private static boolean verificaCarry(String conteudo) {                     ///////
        return false;
    }
    
    private static boolean verificaParidade(String conteudo) {
        char[] aux = conteudo.toCharArray();
        int cont, cont1 = 0;
        for(cont = 0; cont < aux.length; cont++) {
            if(aux[cont] == '1') {
                cont1++;
            }
        }
        if((cont1 % 2) == 0) {
            return true;
        }
        return false;
    }
    
    private static boolean verificaZero(int conteudo) {
        if(conteudo == 0) {
            return true;
        }
        return false;
    }
    
    private static boolean verificaSinal(int conteudo) {
        if(conteudo < 0) {
            return true;
        }
        return false;
    }
    
    private static boolean verificaEAtualizaOverflow(int valor, Reg2B flags) {
        if(valor > 65535) {
            flags.atualiza1Bit(3, true);
            return true;
        }
        flags.atualiza1Bit(3, false);
        return false;
    }
    
    private static void atualizaFlagsCPZS(Reg2B reg, Reg2B flags) {
        flags.atualiza1Bit(15, verificaCarry(reg.getRepresentacaoString()));
        flags.atualiza1Bit(9, verificaParidade(reg.getRepresentacaoString()));
        flags.atualiza1Bit(7, verificaZero(reg.getRepresentacaoInt()));
        flags.atualiza1Bit(6, verificaSinal(reg.getRepresentacaoInt()));
    }
    
    private static void addReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        int soma = reg1.getRepresentacaoInt() + reg2.getRepresentacaoInt();
        if(verificaEAtualizaOverflow(soma, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg1.setRegistrador(soma);
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void addOpd(Reg2B reg, int opd, Reg2B flags) {
        int soma = reg.getRepresentacaoInt() + opd;
        if(verificaEAtualizaOverflow(soma, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg.setRegistrador(soma);
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void div(Reg2B reg1, Reg2B reg2, Reg2B reg3, Reg2B flags) {  //divisão por 0!
        int div = reg1.getRepresentacaoInt() / reg2.getRepresentacaoInt();
        int mod = reg1.getRepresentacaoInt() % reg2.getRepresentacaoInt();
        if(verificaEAtualizaOverflow(div, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg1.setRegistrador(div);
        reg3.setRegistrador(mod);
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void subReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        int subtracao = reg1.getRepresentacaoInt() - reg2.getRepresentacaoInt();
        if(verificaEAtualizaOverflow(subtracao, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg1.setRegistrador(subtracao);
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void subOpd(Reg2B reg, int opd, Reg2B flags) {
        int subtracao = reg.getRepresentacaoInt() - opd;
        if(verificaEAtualizaOverflow(subtracao, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg.setRegistrador(subtracao);
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void mul(Reg2B reg1, Reg2B reg2, Reg2B reg3, Reg2B flags) {
        int mul = reg1.getRepresentacaoInt() * reg2.getRepresentacaoInt();
        int mulOf = reg1.getRepresentacaoInt() * reg2.getRepresentacaoInt();    //
        if(verificaEAtualizaOverflow(mul, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg1.setRegistrador(mul);
        reg3.setRegistrador(mulOf);                                             //
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void cmpOpd(Reg2B reg, int opd, Reg2B flags) {
        flags.atualiza1Bit(7, (reg.getRepresentacaoInt() == opd));
    }
    
    private static void cmpReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        flags.atualiza1Bit(7, (reg1.getRepresentacaoInt() == reg2.getRepresentacaoInt().intValue()));
    }
    
    private static void andReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        for(int cont = 0; cont < 16; cont++) {
            if((reg1.get1Bit(cont) && !reg2.get1Bit(cont)) || (!reg1.get1Bit(cont) && reg2.get1Bit(cont))) {
                reg1.atualiza1Bit(cont, false);
            }
        }
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void andOpd(Reg2B reg, int opd, Reg2B flags) {
        int cont, aux = 0;
        Boolean[] opdBin = new Boolean[16];
        for(cont = 32768; aux < 16; cont /= 2) {
            if(opd < cont*2 && opd >= cont) {
                opdBin[aux++] = true;
                opd -= cont;
            } else{
                opdBin[aux++] = false;
            }
        }
        for(cont = 0; cont < 16; cont++) {
            if((reg.get1Bit(cont) && !opdBin[cont]) || (!reg.get1Bit(cont) && opdBin[cont])) {
                reg.atualiza1Bit(cont, false);
            }
        }
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void not(Reg2B reg, Reg2B flags) {
        for(int cont = 0; cont < 16; cont++) {
            reg.atualiza1Bit(cont, !reg.get1Bit(cont));
        }
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void orReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        for(int cont = 0; cont < 16; cont++) {
            if((reg1.get1Bit(cont) && !reg2.get1Bit(cont)) || (!reg1.get1Bit(cont) && reg2.get1Bit(cont))) {
                reg1.atualiza1Bit(cont, true);
            }
        }
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void orOpd(Reg2B reg, int opd, Reg2B flags) {
        int cont, aux = 0;
        Boolean[] opdBin = new Boolean[16];
        for(cont = 32768; aux < 16; cont /= 2) {
            if(opd < cont*2 && opd >= cont) {
                opdBin[aux++] = true;
                opd -= cont;
            } else{
                opdBin[aux++] = false;
            }
        }
        for(cont = 0; cont < 16; cont++) {
            if((reg.get1Bit(cont) && !opdBin[cont]) || (!reg.get1Bit(cont) && opdBin[cont])) {
                reg.atualiza1Bit(cont, true);
            }
        }
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void xorReg(Reg2B reg1, Reg2B reg2, Reg2B flags) {
        for(int cont = 0; cont < 16; cont++) {
            if((reg1.get1Bit(cont) && !reg2.get1Bit(cont)) || (!reg1.get1Bit(cont) && reg2.get1Bit(cont))) {
                reg1.atualiza1Bit(cont, true);
            } else {
                reg1.atualiza1Bit(cont, false);
            }
        }
        atualizaFlagsCPZS(reg1, flags);
    }
    
    private static void xorOpd(Reg2B reg, int opd, Reg2B flags) {
        int cont, aux = 0;
        Boolean[] opdBin = new Boolean[16];
        for(cont = 32768; aux < 16; cont /= 2) {
            if(opd < cont*2 && opd >= cont) {
                opdBin[aux++] = true;
                opd -= cont;
            } else{
                opdBin[aux++] = false;
            }
        }
        for(cont = 0; cont < 16; cont++) {
            if((reg.get1Bit(cont) && !opdBin[cont]) || (!reg.get1Bit(cont) && opdBin[cont])) {
                reg.atualiza1Bit(cont, true);
            } else {
                reg.atualiza1Bit(cont, false);
            }
        }
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void jmp(Reg2B reg, int opd) {
        reg.setRegistrador(opd);
    }
    
    private static void jz(Reg2B reg, int opd, Reg2B flags) {
        if(flags.get1Bit(7)) {
            reg.setRegistrador(opd);
        }
    }
    
    private static void jnz(Reg2B reg, int opd, Reg2B flags) {
        if(!flags.get1Bit(7)) {
            reg.setRegistrador(opd);
        }
    }
    
    private static void jp(Reg2B reg, int opd, Reg2B flags) {
        if(!flags.get1Bit(6)) {
            reg.setRegistrador(opd);
        }
    }
    
    private static void call(Reg2B regPilha, Reg2B IP, int index, Pilha pilha) {
        pilha.push(IP.getRepresentacaoString());
        IP.setRegistrador(Integer.parseInt(mem.getPalavra(index)) - 1/**/);
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void ret(Reg2B regPilha, Reg2B IP, Pilha pilha) {
        IP.setRegistrador(pilha.pop(), false);
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    @SuppressWarnings("WaitWhileNotSynced")
    private void hlt() throws InterruptedException {
        JOptionPane.showMessageDialog(null, "Execução finalizada!\nPara sair, pressione Arquivo -> Sair.", "Instrução Hlt", WIDTH);
        wait();
    }
    
    private static void popReg(Reg2B regPilha, Reg2B reg, Pilha pilha) {
        reg.setRegistrador(pilha.pop(), false);
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void popOpd(Reg2B regPilha, int opd, Pilha pilha) {
        mem.setPalavra(pilha.pop(), Integer.parseInt(mem.getPalavra(opd)));
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void popF(Reg2B regPilha, Reg2B flags, Pilha pilha) {
        flags.setRegistrador(pilha.pop(), false);
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void pushReg(Reg2B regPilha, Reg2B reg, Pilha pilha) {
        pilha.push(reg.getRepresentacaoString());
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void pushF(Reg2B regPilha, Reg2B flags, Pilha pilha) {
        pilha.push(flags.getRepresentacaoString());
        regPilha.setRegistrador(pilha.getEndTopoPilha());
    }
    
    private static void store(Reg2B reg, int opd) {
        mem.setPalavra(reg.getRepresentacaoInt().toString(), opd);
    }
    
    private static void read(int opd) {
        mem.setPalavra(JOptionPane.showInputDialog(null, "Digite o valor da entrada:", "Instrução Read", WIDTH), opd);
    }
    
    private static void write(int opd) {
        JOptionPane.showMessageDialog(null, "Saída: " + mem.getPalavra(opd), "Instrução Write", WIDTH);
    }

    private static void load(Reg2B reg, int opd, Reg2B flags) {
        reg.setRegistrador(Integer.parseInt(mem.getPalavra(opd)));
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void copy(int opd1, int opd2) {
        mem.setPalavra(mem.getPalavra(opd2), opd1);
    }
    
    private static void divide(Reg2B reg, int opd, Reg2B flags) {
        int div = reg.getRepresentacaoInt() / opd;
        if(verificaEAtualizaOverflow(div, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg.setRegistrador(div);
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static void mult(Reg2B reg, int opd, Reg2B flags) {
        int mul = reg.getRepresentacaoInt() * opd;
        if(verificaEAtualizaOverflow(mul, flags)) {
            System.out.println("OVERFLOW!");
            return;
        }
        reg.setRegistrador(mul);
        atualizaFlagsCPZS(reg, flags);
    }
    
    private static String setMemoriaUI() {
        String temp = "";
        for(int cont = 0; cont <  32768; cont++) {
            temp += (cont + "\t" + mem.getPalavra(cont) + "\n");
        }
        return temp;
    }
    
    private void atualizaUI(Reg2B reg1, Reg2B reg2, Reg2B reg3, Reg2B reg4, Reg2B reg5, Reg2B reg6) throws InterruptedException {
        jTextArea1.setText(setMemoriaUI());
        jTextField1.setText(reg1.getRepresentacaoString());
        jTextField2.setText(reg2.getRepresentacaoString());
        jTextField3.setText(reg3.getRepresentacaoString());
        jTextField4.setText(reg4.getRepresentacaoString());
        jTextField5.setText(reg5.getRepresentacaoString());
        jTextField6.setText(reg6.getRepresentacaoString());
        update(getGraphics());
        TimeUnit.SECONDS.sleep(2);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration                   
}
