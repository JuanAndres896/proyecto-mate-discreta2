/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* AEstrella.java - Algoritmo de "A*" para ruta mas corta en un grafo
*/
// Librerías
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/*
FUNCION DE PESO PARA ESTA CLASE:
H(m,n) = m+n (mod 17)
V(m,n) = m+n (mod 13)
*/
public final class AEstrellaPeso2 {
     // CONSTANTES Y OBJETOS
    private static final Comparador COSTO_CMP = new Comparador();
    private final int COSTO_VERTICAL = 1;
    private final int COSTO_HORIZONTAL = 1;
    // Costo en diagonal = parte entera de raiz de 2 (distancia euclidiana para funcion de heuristica)
    private final double COSTO_DIAGONAL =9999999.0;
    //(int) Math.rint(Math.sqrt((COSTO_VERTICAL * COSTO_VERTICAL) + (COSTO_HORIZONTAL * COSTO_HORIZONTAL)));
    
    private final Map map; // Grafo de entrada
    private final Node origen; // Coordenadas de origen
    private final Node destino; // Coordenadas de destino
    
    private final Queue<Node> open; // Cola de prioridad nodos por evaluar/en evaluacion
    private final int[] closed; // Arreglo de nodos ya visitados
    
    
    // Constructor de A*
    // Recibe un mapa (grafo) coordenadas 'x' y 'y' de origen y de destino 
    public AEstrellaPeso2(Map map, int origenX, int origenY, int destinoX, int destinoY){
        assert map != null : "map = " + map;
        this.map = map;
        destino = new Node(destinoX, destinoY);
        origen = new Node(origenX, origenY);
        open = new PriorityQueue<Node>(Math.max(map.width, map.height)*2,COSTO_CMP); // Open = nodos sin evaluar/en evaluacion
        closed = new int[(map.width * map.height >> 5)+1]; // Closed = nodos evaluados
    }
    
    // Comparacion de costo
    // Se utiliza para determinar el costo en iteraciones de los algoritmos
    private static class Comparador implements Comparator<Node>{
        // Retorna el valor del costo 'g' y el costo 'h' (funcion heuristica) entre 2 nodos A y B
        @Override
        public int compare(Node nodoA, Node nodoB){
            return (int) ((nodoA.gcost + nodoA.hcost) - (nodoB.gcost + nodoB.hcost));
        }
    }
    
    // Definicion de nodos en la rejilla
    class Node{
        final int x;
        final int y; // Coordenadas 'x' y 'y' en rejilla
        public int CostoH;
        public int CostoV;
        Node padre;
        double gcost, hcost;
        // hcost = costo de funcion heuristica
        // Constructor para nodos
        public Node(int x, int y){
            this.CostoV = x+y;
            assert x>=0 && x < map.width : "x= "+x;
            assert y>=0  && y < map.height : "y = "+y;
            assert CostoH>=0 && CostoH < map.width : "CostoH= "+CostoH;
            assert CostoV>=0 && CostoV < map.width : "CostoV= "+CostoV;
            
            this.x = x;
            this.y = y;
            this.CostoV = CostoV;
            this.CostoH = CostoH;
                   
        }
        
     // Defincion de funcion de heuristica
     public void funcionHeuristica(){
         // Definicion de distancia Manhattan
         double dx = Math.abs(destino.x - x);
         double dy = Math.abs(destino.y - y);
         double hcost =Math.sqrt((dx*dx)+(dy*dy));
         //hcost = (Math.abs(x - destino.x)+ Math.abs(y-destino.y)) * (COSTO_VERTICAL + COSTO_HORIZONTAL)/2;
         }
     
     public void setPadre(Node padre){
         this.padre = padre;
         if(padre.x == x){
             gcost = padre.gcost + (padre.CostoH + padre.CostoV)%(17);
         }
         else if(padre.y == y){
             gcost = padre.gcost + (padre.CostoH + padre.CostoV)%(13);
         } else {
             gcost = padre.gcost + COSTO_DIAGONAL;
         }
     }
     @Override
     public String toString(){
         return "(" + x + " , " + y + " : " + super.toString() + ')';
     }
     
     }

    
    /*
    * Metodo para agregar un nodo a evaluacion
    * Parametros:
    * Coordenada X
    * Coordenada Y
    * Nodo padre
    */
    private void addToOpen(int x, int y, Node padre){
        Node nodoAbierto = new Node(x,y);
        nodoAbierto.setPadre(padre);
        // Buscar con iteraciones el siguiente nodo a evaluar
        replacing: for(Iterator<Node> i = open.iterator(); i.hasNext();){
            Node existente = i.next();
            if(existente.x == x && existente.y == y){
                if(existente.gcost > nodoAbierto.gcost){
                    i.remove();
                    break replacing;
                } else{
                    return;
                }
            }
        }
        // Aplicar funcion de heuristica (moverse en el grafo) y agregar a cola
        nodoAbierto.funcionHeuristica();
        open.add(nodoAbierto);
    }
    
    
    /*
    * Metodo para declarar un nodo como evaluado
    */
    private void setClosed(int x, int y){
        int i = map.width * y+x;
        closed[i>>5] |=(1 << (i&31));
    }
    
    
    /*
    * Metodo para determinar si un nodo fue evaluado o no
    */
    private boolean isClosed(int x, int y){
        int i = map.width * y + x;
        return (closed[i>>5]&(1<<(i&31)))!=0;
    }
    
    
    /*
    * Metodo para evaluar un nodo
    * Se utiliza en la inicializacion del algoritmo
    */
    private void evaluar(Node nodo){
        // Para no evaluar 2 veces el mismo
        setClosed(nodo.x, nodo.y);
        
        // Para no salirse de la rejilla
        int x_1 = nodo.x ==0 ? 0 : nodo.x-1;
        int x_2 = nodo.x >= map.width -1 ? map.width -1 : nodo.x+1;
        int y_1 = nodo.y ==0 ? 0 : nodo.y -1 ;
        int y_2 = nodo.y >= map.height -1 ? map.height -1 : nodo.y+1;
        
        // Revisar vecindad del nodo
        for (int x =x_1; x <=x_2; ++x){
            for(int y=y_1; y<=y_2; ++y){
                if (!isClosed(x,y) && map.isWalkable(x,y)){
                    addToOpen(x,y,nodo);
                }
            }
        }
    }
    
    /*
    * Metodo para inicializar el algoritmo ssi encuentra un camino valido
    */
    public boolean encontrarRuta(){
        Node actual = origen;
        while(actual != null &&(actual.x != destino.x || actual.y != destino.y)){
            evaluar(actual);
            actual = open.poll();
        }
        if(actual!= null){
            destino.setPadre(actual.padre);
        }
        return actual !=null;
    }
    
    /*
    * Metodo que retorna el camino mas corto encontrado por el algoritmo
    */
    public Deque<Integer> getPath(){
        assert destino.padre != null || (destino.x == origen.x && destino.y == origen.y);
        Deque<Integer> path = new LinkedList<Integer>();
        Node actual = destino;
        
        while(actual !=null){
            path.addFirst(actual.y);
            path.addFirst(actual.x);
            actual = actual.padre;
        }
        System.out.println(path);
        return path;
    }
    }
  
