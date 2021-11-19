package ps.z808.vm;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import ps.z808.ligador.Ligador;

public class Carregador {
    static Memoria mem = new Memoria();
    
    public static void carregar() throws FileNotFoundException, IOException, InterruptedException {
        Ligador.ligar();
        
        FileReader rArquivo = new FileReader("codigoObjeto.o");
        BufferedReader rBuffer = new BufferedReader(rArquivo);
          
        String[] temp;
        Integer tempAux;
        while(rBuffer.ready()) {
            temp = rBuffer.readLine().split(" ");
            if(temp.length > 1) {
                switch(temp[0]) {
                    case "05":
                    case "2D":
                    case "3D":
                    case "25":
                    case "0D":
                    case "35":
                    case "EB":
                    case "74":
                    case "75":
                    case "7A":
                    case "59":
                    case "12":
                    case "08":
                    case "10":
                    case "14":
                        tempAux = Integer.parseInt(temp[1]) + mem.getEndMaxPilha();
                        temp[1] = tempAux.toString();
                        break;
                    case "03":
                        if(temp[1].equals("C0") || temp[1].equals("C2")) {
                        } else {
                            tempAux = Integer.parseInt(temp[1]) + mem.getEndMaxPilha();
                            temp[1] = tempAux.toString();
                        }
                        break;
                    case "07":
                        tempAux = Integer.parseInt(temp[2]) + mem.getEndMaxPilha();
                        temp[2] = tempAux.toString();
                        break;
                    case "13":
                        tempAux = Integer.parseInt(temp[1]) + mem.getEndMaxPilha();
                        temp[1] = tempAux.toString();
                        tempAux = Integer.parseInt(temp[2]) + mem.getEndMaxPilha();
                        temp[2] = tempAux.toString();
                        break;
                }
            }
            mem.setLinha(temp);
        }
        
        rBuffer.close();
        rArquivo.close();
    }
}
