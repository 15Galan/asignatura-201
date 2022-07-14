package Ejercicios.Complementarios;



/**
 * PROBLEMA:
 * Sean 'u' y 'v' dos cadenas de caracteres, se desea transformar 'u' en 'v'
 * con el mínimo número de operaciones elementales del tipo siguiente:
 * - Eliminar un carácter.
 * - Añadir un carácter.
 * - Cambiar un carácter.
 *
 * EJEMPLO:
 * Sean u = "abbac" y v = "abcdc".
 *
 *      SOLUCIÓN: 2.
 *      Se hacen dos operaciones de cambio: U[2] -> c y U[3] -> d.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_08 {
    // Datos del problema
    private static String U;    // Cadena U
    private static String V;    // Cadena V
    private static int n;       // Longitud de la cadena U
    private static int m;       // Longitud de la cadena V

    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación
    private static String[][] aux;  // Tabla auxiliar


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        U = "abbac";    n = U.length();
        V = "abcdc";    m = V.length();


        // Mostrar los datos del problema
        System.out.println("Cadena u: " + U);
        System.out.println("Cadena v: " + V);


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:\t(la posición inicial es 0)\n" + solucion());
        System.out.println("\tValor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }

    /**
     * Rellena la tabla A con los valores correspondientes.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla A
        A = new int[n+1][m+1];

        // Inicializar la tabla auxiliar
        aux = new String[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                aux[i][j] = "";
            }
        }

        // Rellenar la tabla A
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la ecuación de Bellman para calcular el valor de la tabla A.
     *
     * @param i  Posición de la cadena u
     * @param j  Posición de la cadena v
     */
    private static void bellman(int i, int j) {
        // Caso base 1
        if (i == 0 && j == 0) {
            A[i][j] = 0;
            aux[i][j] = "";

        // Caso base 2
        } else if (i == 0) {
            A[i][j] = A[i][j-1] + 1;
            aux[i][j] = "";

        // Caso base 3
        } else if (j == 0) {
            A[i][j] = A[i-1][j] + 1;
            aux[i][j] = "";

        // Caso iterativo
        } else {
            // Los caracteres coinciden, no se hace nada
            if (U.charAt(i-1) == V.charAt(j-1)) {
                A[i][j] = A[i-1][j-1];
                aux[i][j] = aux[i-1][j-1];

            // Los caracteres no coinciden, se hace una operación de cambio
            } else {
                // A[i][j] = Math.min(Math.min(A[i-1][j], A[i][j-1]), A[i-1][j-1]) + 1;

                int min;
                String op = "";

                if (A[i-1][j] < A[i][j-1]) {
                    min = A[i-1][j];
                    op = aux[i-1][j] + "- Se elimina '" + U.charAt(i-1) + "' en la posición " + (i-1) + ".\n";


                } else {
                    min = A[i][j-1];
                    op = aux[i][j-1] + "- Se añade '" + V.charAt(j-1) + "' en la posición " + (j-1) + ".\n";

                }

                if (A[i-1][j-1] < min) {
                    min = A[i-1][j-1];
                    op = aux[i-1][j-1] + "- Se cambia '" + U.charAt(i-1) + "' por '" + V.charAt(j-1) + "' en la posición " + (i-1) + ".\n";

                }

                A[i][j] = min + 1;
                aux[i][j] = op;
            }
        }
    }

    private static String solucion() {
        return aux[n][m];
    }

    /**
     * Devuelve el valor de la solución.
     *
     * @return  Valor de la solución
     */
    private static int valor() {
        return A[n][m];
    }

    /**
     * Devuelve una representación en cadena de la tabla A.
     *
     * @return  Representación en cadena de la tabla A
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t").append("∅").append("\t");

        for (int h = 0; h < m; h++) {
            sb.append(V.charAt(h)).append("\t");    // Caracteres de v
        }

        // Filas
        for (int f = 0; f < n+1; f++) {
            sb.append("\n");

            // Cabecera de la fila
            if (f == 0) {
                sb.append("∅");             // Subcadena u vacía

            } else {
                sb.append(U.charAt(f-1));   // Caracteres de u
            }

            sb.append("\t");

            // Valores de las columnas
            for (int c = 0; c < m+1; c++) {
                sb.append(tabla[f][c]).append("\t");
            }
        }

        return sb.toString();
    }
}
