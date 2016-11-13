/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* MapaMain.java: rejilla de prueba para el algoritmo A*
*/
// Librerias
import java.util.Random;

public class MapaMain extends Map{
    // Declaracion de variables
    private static final int PROBABILIDAD_OBSTACULO = 25;
    private final boolean[][] obstaculo;
    
    // Constructor 
    public MapaMain(int w, int h){
        super(w,h);
        obstaculo = new boolean[w][h];
        
        Random ranInt = new Random();
        // Colocar obstaculo en coordenada al azar
        for(int i =0; i<w;i++){
            for(int j=0; j<h; j++){
                obstaculo[i][j] = (Math.abs(ranInt.nextInt(100)) < PROBABILIDAD_OBSTACULO) ? true : false;
            }
        }
     
    }
    @Override
    public boolean isWalkable(int x, int y){
        return !obstaculo[x][y];
    }
}   


