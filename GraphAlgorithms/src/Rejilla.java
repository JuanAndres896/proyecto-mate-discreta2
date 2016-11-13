/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Rejilla.java: Rejilla de m x n donde se lleva a cabo la ejecucion de los algoritmos
*/
// Librerias
import java.awt.Color;
import java.awt.Graphics;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rejilla extends JPanel{
    // CONSTANTES Y OBJETOS
    private static final long ID = 1L;
    
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    
    private static final int ESCALA_X = 8;
    private static final int ESCALA_Y = 8;
    
    
    private final Map map = new MapaMain(WIDTH, HEIGHT);
    private final Deque<Integer> path;
    
    // Constructor
    public Rejilla(){
        AEstrella estrella = new AEstrella(map,0,0,map.width-1,map.height-1);
        path = estrella.encontrarRuta() ? estrella.getPath(): new LinkedList<Integer>();
        setSize(ESCALA_X * WIDTH, ESCALA_Y * HEIGHT);
        setVisible(true);
    }
    
    private void fillRect(Graphics graphics, int x , int y){
        graphics.fillRect(ESCALA_X * x, ESCALA_Y * y, ESCALA_X, ESCALA_Y);
    }
    
    // Marcar en gris los obstaculos definidos
    private void pintarObstaculos(Graphics graphics){
        graphics.setColor(Color.DARK_GRAY);
        
        for(int x=0; x<WIDTH; ++x){
            for(int y=0; y<HEIGHT; ++y){
                if(!map.isWalkable(x, y)){
                    fillRect(graphics, x,y);
                }
            }
        }
    }
    
    // Marcar en verde el camino tomado por el algoritmo
    private void pintarPath(Graphics graphics){
       graphics.setColor(Color.GREEN);
       for(Iterator<Integer> i = path.iterator();i.hasNext();){
           int x = i.next(), y = i.next();
           fillRect(graphics, x, y);
       }
    }
    
    // Mandar a pintar toda la rejilla
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, ESCALA_X*WIDTH, ESCALA_Y*HEIGHT);
        pintarObstaculos(graphics);
        pintarPath(graphics);
    }
    
}
