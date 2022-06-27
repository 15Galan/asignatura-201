package Ejemplos;


import java.util.Arrays;

/**
 * PROBLEMA:
 * Sea una mochila con una determinada capacidad y un conjunto
 * de obejtos que poseen un peso y valor concretos cada uno.
 *
 * Se desea encontrar qué objetos se pueden colocar en la mochila,
 * de forma que su valor sea máximo.
 *
 * EJEMPLO:
 * Una mochila de capacidad 5, con objetos cuyos atributos son:
 * - Pesos:     P = {2, 1, 3, 2}
 * - Valores:   V = {12, 10, 20, 15}
 *
 *      SOLUCIÓN: {1, 2, 4} cuyo valor es 37.
 *
 */
public class Mochila_01 {

    // Datos del problema
    private static int[] P;     // Pesos de los objetos
    private static int[] V;     // Valores de los objetos
    private static int C;       // Capacidad de la mochila

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        P = new int[]{ 2,  1,  3,  2};
        V = new int[]{12, 10, 20, 15};
        C = 5;


        // Mostrar los datos del problema
        System.out.println("Pesos:\t\t" + Arrays.toString(P));
        System.out.println("Valores:\t" + Arrays.toString(V));
        System.out.println("Capacidad:\t" + C);


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución: " + solucion(A) + "\t (valor: " + valor(A) + ")");


        // Información adicional
        // System.out.println("\n--- Información adicional ---");
        // System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    public static void rellenarTablaA() {
        // Inicializar la tabla
        int n = P.length + 1, c = C + 1;    // +1 porque contemplan items y mochila vacíos
        A = new int[n][c];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                           si  i = 0 || c = 0
     * A(i,j) = A(i-1,c)                                    si  i,j > 0 && P(i) > c
     * A(i,j) = MAX[A(i-1,c), A(i-1,c-P(i-1)) + V(i-1)]     si  i,j > 0 && P(i) <= c
     *
     * @param i     Fila de la tabla 'A'
     * @param c     Columna de la tabla 'A'
     */
    private static void bellman(int i, int c) {
        // Caso base
        if (i == 0 || c == 0) {
            A[i][c] = 0;

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (P[i-1] > c) {
                A[i][c] = A[i-1][c];
            } else {
                A[i][c] = Math.max(A[i-1][c], A[i-1][c-P[i-1]] + V[i-1]);
            }
        }
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    private static String solucion(int[][] A) {
        // Inicializar los índices
        int i = P.length, c = C;

        // Inicializar la solución
        StringBuilder sb = new StringBuilder();

        // Recorrer la tabla generando la solución óptima
        while (i > 0 && c > 0) {
            // Si el valor acumulado es mejor que el anterior
            if (A[i][c] != A[i-1][c]) {
                sb.append(i);           // Se añade el item a la solución
                c -= P[i-1];            // Se resta el peso del item a la mochila
                                            // ('i-1' porque hay 'n+1' filas en la tabla)
                if (0 < c) {
                    sb.append(" ,");    // Se añade ',' si no terminó la solución
                }
            }

            i--;
        }

        // Devolver la solución óptima
        return "{" + sb.reverse() + "}";
    }

    /**
     * Genera el valor óptimo a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Valor óptimo
     */
    private static int valor(int[][] A) {
        return A[P.length][C];              // El valor óptimo está en la última celda
    }

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 0; cabecera <= C; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        // Filas
        for (int fil = 0; fil <= P.length; fil++) {
            sb.append("\n").append(fil).append("\t");

            for (int col = 0; col <= C; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
