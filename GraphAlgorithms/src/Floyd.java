/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* Floyd.java - Algoritmo de Floyd para ruta mas corta en un grafo
*/
import java.util.Stack;
public class Floyd {

	static int[][] P;   //matriz con los vértices previos
        static final int alto = 4;  //alto de la malla
        static final int ancho = 4; //ancho de la malla
	static final int N = alto*ancho;    //cantidad total de vértices

	public static void main(String[] args) {
                //La matriz M es la matriz de distancias
		int[][] M = new int[N][N];
                //Se llena la matriz de distancias de "infinito" primero
                M = LlenarMatriz(M);
		P = new int[N][N];
		System.out.println("Matrix to find the shortest path of.");
		printMatrix(M);
		System.out.println("Shortest Path Matrix.");
                printMatrix(FloydAlgo(M));
		
		System.out.println("Path Matrix");
		printMatrix(P);
                System.out.println("La distancia mínima entre las dos esquinas es de: "+M[(ancho-1)*(alto-1)][ancho-1]);
                ImprimirCamino(P,0,0,3,3);
	}
        
        public static int[][] LlenarMatriz(int[][] M){
            for (int i = 0; i<N; i++){
                    for (int j = 0; j<N; j++){
                        M[i][j]=9999999;
                    }
                }
                //Ahora se llena según donde esté el vértice
                //el número de nodo en la matriz M está denotado por ancho*(i)+j 
                for (int i = 0; i<alto; i++){
                    for (int j = 0; j<ancho; j++){
                        //Si el vértice se encuentra en la primera fila
                        if (i==0){  
                            if (j==0){
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                                M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                            }
                            if (j==ancho-1){
                                M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                            }
                            if(j!=0 && j!=ancho-1){
                                M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                        }
                        //Si se encuentra en la última fila
                        if (i==alto-1){
                            if (j==0){
                                M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                            if (j==ancho-1){
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                            }
                            if(j!=0 && j!=ancho-1){
                                M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                        }
                        //Si se encuentra en la primera columna
                        if (j==0 && i!=0 && i!=alto-1){
                            M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                        }
                        //Si se encuentra en la última columna                        
                        if (j==ancho-1 && i!=0 && i!=alto-1){
                            M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                        }
                        //De lo contrario
                        if (i!=0 && i!=alto-1 && j!=0 & j!=ancho-1){                            
                            M[ancho*(i)+j][ancho*(i-1)+j]=i+j;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=i+j;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                            M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                        }
                        M[ancho*(i)+j][ancho*(i)+j]=0;
                    }
                }
                return M;
        }

	public static int[][] FloydAlgo(int[][] M) {
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (M[i][k] + M[k][j] < M[i][j]) {
						M[i][j] = M[i][k] + M[k][j];
						P[i][j] = k;
					}
				}
			}
		}
		return M;
	}
        
        
        public static void ImprimirCamino(int[][] Matrix, int xinicial, int yinicial, int xfinal, int yfinal){
            Stack<Integer> pilaX = new Stack<Integer>();
            Stack<Integer> pilaY = new Stack<Integer>();
            pilaX.push(xfinal);
            pilaY.push(yfinal);
            int cInicial=(ancho*yinicial)+xinicial;
            int cFinal=(ancho*yfinal)+xfinal;
            while(cInicial!=cFinal){
                cFinal = Matrix[cInicial][cFinal];
                yfinal = cFinal/alto;
                xfinal = cFinal%alto;
                pilaY.push(yfinal);
                pilaX.push(xfinal);
            }
            System.out.println("A continuación se muestra el camino a tomar para llegar de una esquina a la otra: ");
            String camino = "";
            while(!pilaY.isEmpty()){
                camino += "("+pilaX.pop()+",";
                camino += pilaY.pop()+") -> ";
            }
            camino = camino.substring(0,camino.length()-3);
            System.out.println(camino);
        }
        
	public static void printMatrix(int[][] Matrix) {
		System.out.print("\n\t");
		for (int j = 0; j < N; j++) {
			System.out.print(j + "\t");
		}
		System.out.println();
		for (int j = 0; j < 35; j++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.print(i + " |\t");
			for (int j = 0; j < N; j++) {
				System.out.print(Matrix[i][j]);
				System.out.print("\t");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
	}

}