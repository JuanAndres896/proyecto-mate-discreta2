/*
* tomado como referencia: http://jonathanzong.com/blog/2012/11/06/maze-generation-with-prims-algorithm
* */

import java.io.*;
import java.util.*;


public class Prim {

    public Prim (int rows, int colums) {


        // dimensiones del laberinto a generar
        int r = (rows-2), c = (colums-2);

        // Se contruye el laberinto con solo paredes
        StringBuilder s = new StringBuilder(c);
        for (int x = 0; x < c; x++)
            s.append('1');
        char[][] maz = new char[r][c];
        for (int x = 0; x < r; x++) maz[x] = s.toString().toCharArray();

        // se hace un punto de inicio aleatorio
        Point st = new Point((int)(Math.random() * r), (int)(Math.random() * c), null);
        //Point st = new Point(0,0, null);
        maz[st.r][st.c] = '0';

        // iterate through direct neighbors of node
        ArrayList < Point > frontier = new ArrayList < Point > ();
        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0 || x != 0 && y != 0)
                    continue;
                try {
                    if (maz[st.r + x][st.c + y] == '0') continue;
                } catch (Exception e) { // ignore ArrayIndexOutOfBounds
                    continue;
                }
                // add eligible points to frontier
                frontier.add(new Point(st.r + x, st.c + y, st));
            }

        Point last = null;
        while (!frontier.isEmpty()) {

            // pick current node at random
            Point cu = frontier.remove((int)(Math.random() * frontier.size()));
            Point op = cu.opposite();
            try {
                // en caso de que el nodo y la y haya una pared
                if (maz[cu.r][cu.c] == '1') {
                    if (maz[op.r][op.c] == '1') {

                        // open path between the nodes
                        maz[cu.r][cu.c] = '0';
                        maz[op.r][op.c] = '0';

                        // store last node in order to mark it later
                        last = op;

                        // iterate through direct neighbors of node, same as earlier
                        for (int x = -1; x <= 1; x++)
                            for (int y = -1; y <= 1; y++) {
                                if (x == 0 && y == 0 || x != 0 && y != 0)
                                    continue;
                                try {
                                    if (maz[op.r + x][op.c + y] == '0') continue;
                                } catch (Exception e) {
                                    continue;
                                }
                                frontier.add(new Point(op.r + x, op.c + y, op));
                            }
                    }
                }
            } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
            }

            // if algorithm has resolved, mark end node
            if (frontier.isEmpty())
                maz[last.r][last.c] = '0';
        }
        String salida = "";
        // guaradar el laberinto en un txt
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (j < (c-1)){
                    salida += maz[i][j]+",";
                }
                else{
                    salida += maz[i][j]+ "\n";
                }
            }
        }
        System.out.print(salida);
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test.txt"));
            writer.write(salida);
            writer.close();
        }catch (IOException e){

        }
    }

    static class Point {
        Integer r;
        Integer c;
        Point parent;
        public Point(int x, int y, Point p) {
            r = x;
            c = y;
            parent = p;
        }
        // compute opposite node given that it is in the other direction from the parent
        public Point opposite() {
            if (this.r.compareTo(parent.r) != 0)
                return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
            if (this.c.compareTo(parent.c) != 0)
                return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
            return null;
        }
    }
}