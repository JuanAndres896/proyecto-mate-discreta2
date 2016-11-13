/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Main.java: En donde se manda a llamar la interfaz grafica (rejilla) y se visualiza la ejecucion del algoritmo
*/
// Librerias
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame ventana = new JFrame();
        ventana.setSize(900,900);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.add(new Rejilla());
    }
}
