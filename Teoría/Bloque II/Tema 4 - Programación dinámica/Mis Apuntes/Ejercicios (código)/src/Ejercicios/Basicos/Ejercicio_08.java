package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * Queremos jugar a un juego en el que se tiene un tablero 'n x n' donde en cada casilla
 * aparece un número natural.
 * El juego consiste en elegir una casilla de la última fila 'n' y ascender mediante los
 * movimientos {↖, ↑, ↗} hasta llegar a una casilla de la primera fila.
 * La cantidad ganada será la suma de los valores de las casillas recorridas.
 *
 * Determinar cómo hay que jugar para maximizar los puntos.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_08 {

    // Datos del problema
    private static int[][] T;   // Tablero de juego
    private static int n;       // Longitud del tablero (n x n)

    // Datos del desarrollo
    private static int[][] A;   // Tabla utilizada en el proceso de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[][] {
            {1, 4, 8,  3},
            {1, 5, 2, 10},
            {8, 2, 7,  9},
            {3, 5, 2,  1}
        };


        // Mostrar datos del problema
        System.out.println("Tablero:\n" + mostrarTablero(T));


        // Resolución
        rellenarTablaA();

        int[] S = solucion(A);

        System.out.println("\nSolución: " + Arrays.toString(S));
        System.out.println("\tValor: " + valor(S));


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        n = T.length;
        A = new int[n][n];

        // Aplicar la ecuación de Bellman
        for (int i = n-1; i >= 0; i--) {    // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = t(i,j)                                      si  i = n
     * A(i,j) = MAX[j <= k <= j+1][(A(i+1,k)) + t(i,j)]     si  i > 1 && j = 1
     * A(i,j) = MAX[j-1 <= k <= j][(A(i+1,k)) + t(i,j)]     si  i > 1 && j = n
     * A(i,j) = MAX[j-1 <= k <= j+1][(A(i+1,k)) + t(i,j)]   si  i > 1
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    public static void bellman(int i, int j) {
        // Caso base
        if (i == n-1) {
            A[i][j] = T[i][j];

            // Caso iterativo
        } else {
            int max = 0;

            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está dentro del tablero
                if (0 <= k && k < n) {
                    int aux = A[i+1][k] + T[i][j];

                    if (aux > max) {
                        max = aux;
                    }
                }
            }

            A[i][j] = max;
        }
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    public static int[] solucion(int[][] A) {
        // Inicializar los índices
        int c = 0;                      // Columna del tablero

        // Guardar la columna del máximo valor posible
        for (int j = 0; j < n; j++) {
            if (A[0][c] < A[0][j]) {
                c = j;                  // Se actualiza la columna
            }                           // del valor máximo anterior
        }

        // Inicializar la solución
        int[] solucion = new int[n];
        solucion[n-1] = T[0][c];        // Casilla mejor puntuada

        // Recorrer la tabla A
        for (int i = 1; i < n; i++) {
            int j = c;

            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está en del tablero (las 3 sentencias de la E.B)
                if (0 <= k && k < n) {
                    // Guardar el valor máximo de los posibles
                    if (A[i][j] < A[i][k]) {
                        solucion[(n - 1) - i] = T[i][k];
                        c = k;
                    }
                }
            }
        }

        return solucion;
    }

    /**
     * Calcula el valor de una solución.
     *
     * @param S     Solución
     *
     * @return      Valor
     */
    private static int valor(int[] S) {
        int valor = 0;

        for (int s : S) {
            valor += s;
        }

        return valor;
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de un tablero.
     *
     * @param tablero    Tablero a mostrar
     *
     * @return Tablero como una tabla
     */
    public static String mostrarTablero(int[][] tablero) {
        StringBuilder sb = new StringBuilder();

        // Recorrer el tablero
        for (int[] f : tablero) {
            // Recorrer la fila
            for (int i : f) {
                sb.append(String.format("%3d", i));
            }

            if (f != tablero[tablero.length-1]) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 1; cabecera <= n; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        // Filas
        for (int fil = 0; fil < n; fil++) {
            sb.append("\n").append(n-fil).append("\t");

            for (int col = 0; col < n; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}