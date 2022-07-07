package Ejercicios.Complementarios;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Tienes la misión de controlar la calefacción central de una estación
 * antártica; para ello has recopilado información sobre las preferencias
 * de tus compañeros en cada momento del día:
 *
 * Sea 'c(i,j)' el confort global de los miembros de la base si
 * a la hora 'i' (1 <= i <= H) la temperatura es de 'j' grados.
 *
 * Al comienzo del día la temperatura se fija automáticamente a 'T' grados
 * y a partir de ahí puedes modificarla en como máximo 'K' grados en cada
 * paso/hora (pero nunca puedes salirte del rango [T_min, T_max].
 *
 * Debes determinar qué temperatura tener en cada momento de manera que
 * se maximice la satisfacción (confort) de toda la base.
 *
 *
 * EJEMPLO:
 * Sea una estación donde se trabajan H = 5 horas, cuya temperatura inicial
 * es T = 3 grados y que permite variar K = 1 grados cada hora, donde los
 * límites de temperatura son [T_min, T_max] = [0, 6], se realizó un histórico
 * para conocer el confort del personal:
 *
 * - Propuesta en la resolución (porque es una tabla).
 *
 *      SOLUCIÓN: Propuesta en la resolución (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_10 {
    // Datos del problema
    private static int H;       // Número de horas
    private static int T;       // Temperatura inicial
    private static int K;       // Número de grados que se pueden variar cada hora

    private static int T_min;   // Temperatura mínima
    private static int T_max;   // Temperatura máxima

    private static int[][] c;   // Confort de cada miembro de la base en cada momento del día

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica

    private static String debug;    // Mensaje de depuración


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     */
    public static void main(String[] args) {
        // Datos del problema
        H = 5; T = 3; K = 1;
        T_min = 0; T_max = 6;

        c = new int[][]{
            {0, 0, 1, 2, 3, 3, 3},
            {1, 1, 2, 3, 1, 4, 4},
            {3, 3, 4, 4, 4, 3, 3},
            {3, 3, 3, 3, 1, 1, 1},
            {3, 2, 2, 1, 1, 0, 0}
        };


        // Mostrar datos del problema
        System.out.println("H: " + H);
        System.out.println("T: " + T);
        System.out.println("K: " + K);
        System.out.println("Límites: [" + T_min + ", " + T_max + "]");
        System.out.println("Confort:\n" + mostrarTabla(c));


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---\n");
        System.out.println("Tabla A:\n" + mostrarTabla(A));

        // Información de depuración
        // System.out.println("\n\n--- Información de depuración ---\n");
        // System.out.println(debug);
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Rellena la tabla de programación dinámica.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[H+1][T_max+1];
        debug = "";

        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= T_max; j++) {
                A[i][j] = 0;
            }
        }

        for (int i = 1; i <= H; i++) {
            for (int j = T_min; j <= T_max; j++) {
                debug += bellman(i, j);
            }
        }
    }

    /**
     * Resuelve el problema de Bellman.
     *
     * @param i     Hora actual.
     * @param j     Temperatura actual.
     */
    private static String bellman(int i, int j) {
        // Debug
        StringBuilder sb = new StringBuilder();
        sb.append("A(").append(i).append(",").append(j).append("):").append("\t");

        // Caso base
        if (i == 0) {
            A[i][j] = c[i][j];

        // Caso iterativo
        } else {
            int max = -1;
            int inf = Math.max(T_min, j-K);     // Límite inferior de la fila
            int sup = Math.min(j+K, T_max);     // Límite superior de la fila

            // Debug
            sb.append("[").append(inf).append(" .. k .. ").append(sup).append("]").append("\n");

            // Encontrar el máximo entre los límites
            for (int k = inf; k <= sup; k++) {
                int valor = A[i-1][k] + c[i-1][j];

                if (valor > max) {
                    max = valor;
                }

                // Debug
                sb.append("\tA(").append(i-1).append(",").append(k).append(") + c(").append(i-1).append(",").append(j).append(") = ");
                sb.append(A[i-1][k]).append(" + ").append(c[i-1][j]).append(" = ").append(valor).append("\n");
            }

            A[i][j] = max;
        }

        // Debug
        return sb + "\n" + mostrarTabla(A) + "\n\n";
    }

    private static int[] solucion() {
        // Inicializar los índices
        int j = -1;

        // Encontrar la posición del valor máximo
        int max = -1;
        for (int k = T_min; k <= T_max; k++) {
            int valor = A[H][k];

            if (valor > max) {
                max = valor;
                j = k;
            }
        }

        // Inicializar la solución
        int[] S = new int[H];

        // Recorrer la tabla A
        for (int i = H; 0 < i; i--) {
            max = -1;                           // Reciclo la variable
            int inf = Math.max(T_min, j-K);     // Límite inferior de la fila
            int sup = Math.min(j+K, T_max);     // Límite superior de la fila

            S[i-1] = j;     // Añado el valor porque ya tengo el índice 'j' anterior

            for (int k = inf; k <= sup; k++) {
                int valor = A[i-1][k];

                if (valor > max) {
                    max = valor;
                    j = k;
                }
            }
        }

        return S;
    }

    private static int valor() {
        int valor = 0;

        for (int j = 0; j < T_max; j++) {
            if (A[H][j] > valor) {
                valor = A[H][j];
            }
        }

        return valor;
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int i = 0; i < tabla[0].length; i++) {
            sb.append(i).append("\t");
        }

        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append("\n").append(i).append("\t");

            for (int j = 0; j < tabla[i].length; j++) {
                if (tabla[i][j] == 0) {
                    sb.append("-");

                } else {
                    sb.append(tabla[i][j]);
                }

                sb.append("\t");
            }
        }

        return sb.toString();
    }
}
