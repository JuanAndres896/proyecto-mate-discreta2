/*
* Universidad del Valle de Guatemala
* Matematica Discreta 2 - Seccion 10
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Jonnathan Juarez - 15377
* FloydInciso3.java - Algoritmo de Floyd para ruta mas corta en un grafo, hay aristas horizontales y verticales con costo 1 y en diagonal con costo de raíz de 2
*/
import java.util.Stack;
import java.util.Random;
public class FloydInciso3 {


	static int[][] P;   //matriz con los vértices previos
        static final int alto = 25;  //alto de la malla
        static final int ancho = 25; //ancho de la malla
	static final int N = alto*ancho;    //cantidad total de vértices

	public static void main(String[] args) {
                //La matriz M es la matriz de distancias
		double[][] M = new double[N][N];
                //Se llena la matriz de distancias de "infinito" primero
                M = LlenarMatriz(M);
		P = new int[N][N];
		/*System.out.println("Matriz de distancias inicial:");
		printMatrix(M);
		System.out.println("Matriz de distancias final:");*/
                FloydAlgo(M);		
		/*System.out.println("Matriz de nodos previos final:");
		printMatrix(P);*/
                System.out.println("La distancia mínima entre las dos esquinas (superior izquierda -> inferior derecha) es de: "+M[0][N-1]+"\n");
                ImprimirCamino(P,0,0,24,24);
	}

        public static double[][] LlenarMatriz(double[][] M){
            Random ranInt = new Random();
            int[][] diagonales = new int[25][25];
            for (int n=0; n<25 ; n++){
                for (int m=0; m<25 ; m++){
                    diagonales[n][m] = (Math.abs(ranInt.nextInt(2)));
                }
            }
            
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
                                if (diagonales[i][j]==0){
                                    M[ancho*(i)+j][ancho*(i+1)+j+1]=Math.sqrt(2);  //abajoderecha
                                }
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                                M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                            }
                            if (j==ancho-1){
                                if (diagonales[i][j]==1){
                                    M[ancho*(i)+j][ancho*(i+1)+j-1]=Math.sqrt(2);  //abajoizquierda
                                }
                                M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                            }
                            if(j!=0 && j!=ancho-1){
                                if (diagonales[i][j]==1){
                                    M[ancho*(i)+j][ancho*(i+1)+j-1]=Math.sqrt(2);  //abajoizquierda
                                }
                                else{
                                    M[ancho*(i)+j][ancho*(i+1)+j+1]=Math.sqrt(2);  //abajoderecha
                                }
                                M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                        }
                        //Si se encuentra en la última fila
                        if (i==alto-1){
                            if (j==0){
                                M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                            if (j==ancho-1){
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                            }
                            if(j!=0 && j!=ancho-1){
                                M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                                M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                                M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                            }
                        }
                        //Si se encuentra en la primera columna
                        if (j==0 && i!=0 && i!=alto-1){
                            if (diagonales[i][j]==0){
                                 M[ancho*(i)+j][ancho*(i+1)+j+1]=Math.sqrt(2);  //abajoderecha
                            }
                            M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                        }
                        //Si se encuentra en la última columna                        
                        if (j==ancho-1 && i!=0 && i!=alto-1){
                            if (diagonales[i][j]==1){
                                M[ancho*(i)+j][ancho*(i+1)+j-1]=Math.sqrt(2);  //abajoizquierda
                            }
                            M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                        }
                        //De lo contrario
                        if (i!=0 && i!=alto-1 && j!=0 & j!=ancho-1){    
                            if (diagonales[i][j]==1){
                                M[ancho*(i)+j][ancho*(i+1)+j-1]=Math.sqrt(2);  //abajoizquierda
                            }
                            else{
                                M[ancho*(i)+j][ancho*(i+1)+j+1]=Math.sqrt(2);  //abajoderecha
                            }
                            M[ancho*(i)+j][ancho*(i-1)+j]=1;    //arriba
                            M[ancho*(i)+j][ancho*(i+1)+j]=1;    //abajo
                            M[ancho*(i)+j][ancho*(i)+j-1]=1;    //izquierda
                            M[ancho*(i)+j][ancho*(i)+j+1]=1;    //derecha
                        }
                        M[ancho*(i)+j][ancho*(i)+j]=0;
                    }
                }
                return M;
        }
        //Algoritmo de Floyd
        //Parámetro: matriz de distancias iniciales M de tamaño NxN
	public static double[][] FloydAlgo(double[][] M) {
                int contadorIteraciones=0;
                //para todos los nodos
		for (int k = 0; k < N; k++) {
                        //para cada fila
			for (int i = 0; i < N; i++) {
                                //para cada columna
				for (int j = 0; j < N; j++) {
                                        //Si el peso para llegar 
					if (M[i][k] + M[k][j] < M[i][j]) {
						M[i][j] = M[i][k] + M[k][j];
						P[i][j] = k;
					}
                                    contadorIteraciones++;
				}
                            contadorIteraciones++;
			}
                    contadorIteraciones++;
		}
                System.out.println("La cantidad total de iteraciones en el algoritmo de Floyd - Warshal es de: "+contadorIteraciones);
		return M;
	}
        //Imprime el camino tomado desde un nodo a otro
        public static void ImprimirCamino(int[][] Matrix, int xinicial, int yinicial, int xfinal, int yfinal){
            Stack<Integer> pilaX = new Stack<Integer>();
            Stack<Integer> pilaY = new Stack<Integer>();
            Stack<Integer> temporalx;
            Stack<Integer> temporaly;
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
            temporalx = pilaX;
            temporaly = pilaY;
            System.out.println("A continuación se muestra el camino a tomar para llegar de una esquina a la otra: ");
            String camino = "";
            
            int[][] impresion = new int[alto][ancho];
           
            while(!pilaY.isEmpty()){
                impresion[pilaY.peek()][pilaX.peek()]=1;
                camino += "("+pilaX.pop()+",";
                camino += pilaY.pop()+") -> ";
            }
            
            camino = camino.substring(0,camino.length()-3);
            System.out.println(camino);
            System.out.println("\nA continuación se muestra el camino. Los números 1 son los que representan el camino tomado:\n");
             for (int i=0; i<alto; i++){
                for (int j=0; j<ancho; j++){
                    if (j==ancho-1){
                        System.out.print(impresion[i][j]+",\n");
                    }
                    else{
                        System.out.print(impresion[i][j]+",");
                    }
                }
            }
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
