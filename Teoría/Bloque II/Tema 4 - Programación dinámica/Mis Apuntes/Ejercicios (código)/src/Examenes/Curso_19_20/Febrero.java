package Examenes.Curso_19_20;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Dado un vector de números naturales {a_1, ..., a_n}, donde cada elemento 'a_i'
 * representa el número máximo de pasos que se pueden avanzar desde ese elemento.
 *
 * Si un elemento es 0, entonces no puede moverse a través de ese elemento.
 *
 * EJEMPLO:
 * Sea el vector {1, 3, 5, 9, 4, 3, 8, 1, 2, 6, 9}.
 *
 *      SOLUCIÓN: 3 es una solución válida (#0 -> #1 -> #3 -> #10).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Febrero {

    // Datos del problema
    private static int[] V;     // Vector de números naturales
    private static int n;       // Número de elementos del vector

    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación dinámica
    private static String debug;    // Mensaje de depuración


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        V = new int[]{1, 3, 5, 9, 4, 3, 8, 1, 2, 6, 9};
        n = V.length;


        // Mostrar los datos del problema
        System.out.println("V: " + Arrays.toString(V));


        // Resolución
        rellenarTablaA();

        System.out.println("Valor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));

        // Información de depuración
        // System.out.println("\n--- Información de depuración ---\n");
        // System.out.println(debug);
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    public static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[n][n];
        debug = "";

        // Aplicar la ecuación de Bellman
        for (int i = n-1; 0 <= i; i--) {       // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                debug += bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                   si  i = j
     * A(i,j) = 1                                   si  i + V(i) >= j
     * A(i,j) = MIN[i < k <= i+V(i)](1 + A(k,j))    si  i + V(i) < j
     *
     * @param i Fila de la tabla 'A'
     * @param j Columna de la tabla 'A'
     */
    private static String bellman(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nA(").append(i).append(", ").append(j).append("):\t");

        // Caso base 1
        if (i == j) {
            A[i][j] = 0;

        // Caso base 2
        } else if (i + V[i] >= j) {
            A[i][j] = 1;

            // Debug
            sb.append("Caso base.\n");
            sb.append("\t").append("A(").append(i).append(",").append(j).append(") = 1").append("\n");

        // Caso iterativo
        } else {
            // Debug
            sb.append("MIN[i < k <= i+V(i)] de 1 + A(k,j)\n");
            sb.append("\t").append("i + V(i) = ");
            sb.append(i).append(" + ").append(V[i]).append(" = ").append(i + V[i]).append("\n\n");

            int min = Integer.MAX_VALUE;
            int salto = i + V[i];

            for (int k = i+1; k <= salto; k++) {
                int valor = 1 + A[k][j];

                if (valor < min) {
                    min = valor;
                }

                // Debug
                sb.append("\t1 + ").append("A(").append(k).append(",").append(j).append(") = ");
                sb.append("1 + ").append(A[k][j]).append(" = ").append(valor).append("\n");
            }

            A[i][j] = min;
        }

        return sb + "\n" + mostrarTabla(A) + "\n";
    }

    /**
     * Devuelve el valor de la solución.
     */
    public static int valor() {
        return A[0][n-1];
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Uso letras para identificar a los embarcaderos

        // Cabeceras
        for (int j = 0; j < tabla[0].length; j++) {
            sb.append("\t").append(j);
        }

        sb.append("\n");

        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append(i).append("\t");

            // Valores de las columnas
            for (int j = 0; j < tabla[0].length; j++) {

                // Mostrar los casos no-relevantes como '-'
                if (i > j) {
                    sb.append("-");

                } else {
                    sb.append(tabla[i][j]);
                }

                sb.append("\t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}