package Ejercicios.Complementarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * PROBLEMA:
 * Dada una tabla de tamaño 'n x n' de números naturales, se pretende resolver
 * el problema de obtener un camino de la casilla (1,1) a la casilla (n, n)
 * que minimice la suma de los valores de las casillas por las que pasa.
 *
 * En cada casilla (i, j) habrá solo dos posibles movimientos:
 * ir hacia abajo (i+1, j), o hacia la derecha (i, j+1).
 *
 * Se desea resolver el problema usando programación dinámica.
 *
 * EJEMPLO:
 * Planteado abajo (porque es una tabla).
 *
 *      SOLUCIÓN: Planteado abajo (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_11 {

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
            {2, 8, 3, 4},
            {5, 3, 4, 5},
            {1, 2, 2, 1},
            {3, 4, 6, 5}
        };


        // Mostrar datos del problema
        System.out.println("Tablero:\n" + mostrarTablero(T));


        // Resolución
        rellenarTablaA();

        List<Integer> S = solucion(A);

        System.out.println("\nSolución: " + S);
        System.out.println("\tValor: " + valor());


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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = t(i,j)                              si  i = 1 && j = 1
     * A(i,j) = A(i-1,j) + t(i,j)                   si  i > 1 && j = 1
     * A(i,j) = A(i-1,j) + t(i,j)                   si  i = 1 && j > 1
     * A(i,j) = MIN[(A(i-1,j) + A(i,j-1)] + t(i,j)  si  i > 1
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    public static void bellman(int i, int j) {
        // Caso base
        if (i == 0 && j == 0) {
            A[i][j] = T[i][j];

        } else if (i > 0 && j == 0) {
            A[i][j] = A[i-1][j] + T[i][j];

        } else if (i == 0 && j > 0) {
            A[i][j] = A[i][j-1] + T[i][j];

        } else {
            A[i][j] = Math.min(A[i-1][j], A[i][j-1]) + T[i][j];
        }
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    public static List<Integer> solucion(int[][] A) {
        // Inicializar los índices
        int i = n-1, j = n-1;

        // Inicializar la solución
        List<Integer> S = new ArrayList<>();

        // Recorrer la tabla A
        while (0 < i || 0 < j) {
            // Comprobar la dirección del paso previo
            if (0 < i && A[i-1][j] < A[i][j]) {     // Arriba
                i--;

            } else {                                // Izquierda
                j--;
            }

            // Añadir el valor de la casilla a la solución
            S.add(T[i][j]);
        }

        // Invertir la lista
        Collections.reverse(S);     // Se añadieron al revés

        return S;
    }

    /**
     * Calcula el valor de una solución.
     *
     * @return      Valor
     */
    private static int valor() {
        return A[n-1][n-1];
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
            sb.append("\n").append(fil+1).append("\t");

            for (int col = 0; col < n; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}