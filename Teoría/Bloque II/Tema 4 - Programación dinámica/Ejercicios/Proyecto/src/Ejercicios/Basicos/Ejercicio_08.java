package Ejercicios.Basicos;

import java.util.Arrays;

public class Ejercicio_08 {

    // Datos del problema
    private static int[][] t;   // Tablero de juego
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
        t = new int[][] {
                {1, 4, 8,  3},
                {1, 5, 2, 10},
                {8, 2, 7,  9},
                {3, 5, 2,  1}
        };
        n = t.length;

        // Mostrar datos del problema
        System.out.println("Se quiere jugar a un juego que consiste en recorrer el siguiente tablero,");
        System.out.println("de abajo a arriba y solo en diagonal, de forma que al final se haya pasado");
        System.out.println("por un camino con el mayor peso posible.\n");

        System.out.println(tablero(t) + "\n");

        // Resolución
        rellenarTablaA();

        System.out.println("El camino de mayor puntuación es: " + Arrays.toString(mejorCamino()));

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
        A = new int[n][n];

        // Aplicar la ecuación de Bellman
        for (int i = n-1; i >= 0; i--) {    // Esta vez se empieza de abajo a arriba
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = t(i,j)                                      si  i = n-1
     * A(i,j) = MAX[j-1 <= k <= j+1](A(i+1,k)) + t(i,j)     si  i < n-1
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    public static void bellman(int i, int j) {
        // Caso base
        if (i == n-1) {
            A[i][j] = t[i][j];

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            int max = 0;

            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está dentro del tablero
                if (0 <= k && k < n) {
                    int aux = A[i+1][k] + t[i][j];

                    if (aux > max) {
                        max = aux;
                    }
                }
            }

            A[i][j] = max;
        }
    }


    public static int[] mejorCamino() {
        int[] solucion = new int[n];    // Vector solución
        int col = 0;                    // Columna del valor máximo anterior

        // Caso base: el máximo de la primera fila (fin del camino)
        for (int j = 0; j < n; j++) {
            // Guardar el valor máximo de los posibles
            if (A[0][col] < A[0][j]) {
                solucion[n-1] = t[0][j];
                col = j;                    // Se actualiza la columna del valor máximo anterior
            }
        }

        // Caso intermedio: resto de filas (camino desde el final hasta el inicio
        for (int i = 1; i < n; i++) {
            int j = col;

            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está dentro del tablero
                if (0 <= k && k < n) {
                    // Guardar el valor máximo de los posibles
                    if (A[i][j] < A[i][k]) {
                        solucion[(n-1)-i] = t[i][k];
                        col = k;                        // Se actualiza la columna del valor máximo anterior
                    }
                }
            }
        }

        return solucion;
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de una matriz.
     *
     * @param tabla    Matriz a mostrar
     *
     * @return Matriz como una tabla
     */
    public static String tablero(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        for (int[] f : tabla) {
            for (int c = 0; c < tabla.length; c++) {
                sb.append(String.format("%3d", f[c]));
            }

            if (f != tabla[tabla.length - 1]) {
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

        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append(fil+1).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
