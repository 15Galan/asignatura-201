package Ejemplos;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Pagar un precio con la mínima cantidad de monedas posibles, habiendo monedas de diferente valor.
 *
 * EJEMPLO 1:
 * Pagar 8 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 2 monedas (cada una de 4 u).
 *
 * EJEMPLO 2:
 * Pagar 11 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 3 monedas (una de 6 u, otra de 4 u y otra de 1 u).
 */
public class Monedas {

    // Datos del problema
    private static int[] D;     // Denomincaciones
    private static int M;       // Precio a pagar

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        D = new int[]{1, 4, 6};
        M = 8;


        // Mostrar los datos del problema
        System.out.println("Monedas: " + Arrays.toString(D));
        System.out.println("Precio:  " + M);


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:  " + Arrays.toString(solucion(A)));
        System.out.println("\tValor: " + valor(A));


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
    public static void rellenarTablaA() {
        // Inicializar la tabla
        int n = D.length + 1, c = M + 1;    // +1 porque contemplan denominaciones
        A = new int[n][c];                  // vacías y precio pagado

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
     * A(i,m) = 0                                   si  i = 0 || c = 0
     * A(i,m) = A(i-1,m)                            si  i,m > 0 && D(i) > m
     * A(i,m) = MIN[A(i-1,m), A(i,m-P(i-1)) + 1]    si  i,m > 0 && D(i) <= m
     *
     * @param i     Fila de la tabla 'A'
     * @param m     Columna de la tabla 'A'
     */
    private static void bellman(int i, int m) {
        // Caso base 1
        if (i == 0) {
            A[i][m] = Integer.MAX_VALUE;    // Representa '∞'

        // Caso base 2
        } else if (i > 0 && m == 0) {
            A[i][m] = 0;

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (D[i-1] > m) {
                A[i][m] = A[i-1][m];
            } else {
                A[i][m] = Math.min(A[i-1][m], A[i][m-D[i-1]] + 1);
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
    private static int[] solucion(int[][] A) {
        // Inicializar los índices
        int i = D.length, m = M;

        // Inicializar la solución
        int[] S = new int[D.length];    // Inicializado a '0'

        // Recorrer la tabla generando la solución óptima
        while (i > 0 && m > 0) {
            // Si el valor acumulado es menor que el anterior (mejor)
            if (A[i][m] < A[i-1][m]) {
                S[i-1]++;               // Se suma una moneda
                m -= D[i-1];            // Se resta el precio pagado

            // Si no, el valor acumulado mejor es el anterior
            } else {
                i--;                    // Se descarta la denominación
            }
        }

        // Devolver la solución óptima
        return S;
    }

    /**
     * Indica el valor óptimo de la solución
     * a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Valor óptimo
     */
    private static int valor(int[][] A) {
        return A[D.length][M];              // El valor óptimo está en la última celda
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 0; cabecera <= M; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        // Filas
        for (int fil = 0; fil <= D.length; fil++) {
            sb.append("\n");

            // Cabecera de la fila
            if (fil == 0) {
                sb.append("0");

            } else {
                sb.append(D[fil-1]);
            }

            sb.append("\t");

            // Valores de las columnas
            for (int col = 0; col <= M; col++) {
                int valor = tabla[fil][col];

                // Sustituir el número 'MAX_VALUE' por '∞'
                if (valor == Integer.MAX_VALUE) {
                    sb.append("∞");

                } else {
                    sb.append(valor);
                }

                sb.append("\t");
            }
        }

        return sb.toString();
    }
}
