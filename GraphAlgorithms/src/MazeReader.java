/**
 * Created by Alejanddro on 14/11/2016.
 */
import java.io.*;
import java.util.*;

public class MazeReader {

    public ArrayList<String> rawsplited= new ArrayList<String>();
    public String[] superCadena = null;
    String archivo;
    int[][] matriz;
    public MazeReader(String  archivo){
        this.archivo = archivo;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(archivo));
            String read = null;
            while ((read = in.readLine()) != null) {
                rawsplited.add(read);
            }
        } catch (IOException e) {
            System.out.println("error al cargar" + e);
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
        String temp = "";
        for (int i = 0; i < rawsplited.size(); i++) {
            if(i != rawsplited.size()){temp += rawsplited.get(i) + ",";}
            else{temp += rawsplited.get(i);}
        }
        superCadena = temp.split(",");
    }
    //metodo que convierte la cadena leida en una matrz del tamanio requerido
    public void convertToMatriz(int rows, int colums){
        matriz = new int[rows+10][colums+10];
        //rellenar matriz de ceros
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                    matriz[i][j] = 1;
            }
        }

        int splitedSize = 0;
        //llenar matriz con datos
        for (int j = 1; j < rows-1; j++) {
            for (int i = 1; i < colums-1; i++) {
                matriz[j][i] = Integer.parseInt(superCadena[splitedSize]);
                if(splitedSize < superCadena.length-1){
                    splitedSize = splitedSize + 1;
                }
            }
        }
        //imprimir resutado
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                System.out.print(matriz[i][j]);
                System.out.print(",");
            }
            System.out.println();
        }

    }


}
