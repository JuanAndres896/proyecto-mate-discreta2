/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Main.java: En donde se manda a llamar la interfaz grafica (rejilla) y se visualiza la ejecucion del algoritmo
*/
// Librerias
import javax.swing.*;
        import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame ventana2 = new JFrame();
        ventana2.setLayout(new BorderLayout());
        ventana2.setLocationRelativeTo(null);
        ventana2.add(new Rejilla4Floyd(".\\FloydFuncionDePeso.txt"), BorderLayout.CENTER);
        ventana2.setSize(600,600);
        ventana2.setVisible(true);
    }
}
