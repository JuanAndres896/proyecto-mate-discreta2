/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Map.java: Clase abstracta para definicion del mapa (grafo)
*/
public abstract class Map {
    final int width;
    final int height;
    // Constructor
    public Map(int width, int height){
        assert width > 0;
        assert height > 0;
        
        this.width = width;
        this.height = height;
    }
    // Para ver si hay obstaculos (se puede pasar por ahi)
    public abstract boolean isWalkable(int x, int y);    
    }
