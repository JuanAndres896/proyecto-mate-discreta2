/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Rejilla.java: Rejilla de m x n donde se lleva a cabo la ejecucion de los algoritmos
*/
// Librerias
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rejilla4Floyd extends JPanel{
    // CONSTANTES Y OBJETOS
    private static final long ID = 1L;

    private static final int WIDTH = 27;
    private static final int HEIGHT = 27;

    private static final int ESCALA_X = 20;
    private static final int ESCALA_Y = 20;
    public int [][] laberinto = null;
    //public final Map map;
    //private final Prim prim = new Prim(WIDTH,HEIGHT);
    //private final Deque<Integer> path;

    // Constructor
    public Rejilla4Floyd(String archivo){
        MazeReader mazeReader = new MazeReader(archivo);
        mazeReader.convertToMatriz(WIDTH,HEIGHT);
        laberinto = mazeReader.matriz;
        //map = new MapaMain(WIDTH, HEIGHT, laberinto );
        //AEstrella estrella = new AEstrella(map,x_0,y_0,x_1,y_1);
        //path = estrella.encontrarRuta() ? estrella.getPath(): new LinkedList<Integer>();
        setSize(ESCALA_X * WIDTH, ESCALA_Y * HEIGHT);
        setVisible(true);
    }

    private void fillRect(Graphics graphics, int x , int y){
        graphics.fillRect(ESCALA_X * x, ESCALA_Y * y, ESCALA_X, ESCALA_Y);
    }

    // Marcar en gris los obstaculos definidos
    private void pintarObstaculos(Graphics graphics){
        graphics.setColor(Color.BLUE);

        for(int x=0; x<WIDTH; x++){
            for(int y=0; y<HEIGHT; y++){
                if(laberinto[x][y] == 9){
                    graphics.setColor(Color.BLUE);
                    fillRect(graphics, x,y);

                }
                else if(laberinto[x][y]==1){
                    graphics.setColor(Color.darkGray);
                    fillRect(graphics, x,y);
                }
            }
        }
    }

    // Marcar en rojo el camino tomado por el algoritmo
    //private void pintarPath(Graphics graphics){
     //   graphics.setColor(Color.RED);
      //  for(Iterator<Integer> i = path.iterator();i.hasNext();){
       //     int x = i.next(), y = i.next();
         //   fillRect(graphics, x, y);
       // }
    //}

    // Mandar a pintar toda la rejilla
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, ESCALA_X*WIDTH, ESCALA_Y*HEIGHT);
        pintarObstaculos(graphics);
        //pintarPath(graphics);
    }

}
