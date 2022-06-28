package Ejemplos;



/**
 * PROBLEMA:
 * Encontrar la subsecuencia común más larga entre 2 cadenas de ADN.
 *
 * EJEMPLO:
 * - X = ACCGGTCGAGTGCGCGGAAGCCGGCCGAA
 * - Y = GTCGTTCGGAATGCCGTTGCTCTGTAAA
 *
 *      SOLUCIÓN: GTCGTCGGAAGCCGGCCGAA.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Subsecuencia {

    // Datos del problema
    private static String X, Y;     // Cadenas de ADN

    // Datos del desarrollo
    private static int[][] A;       // Tabla utilizada en el proceso de programación dinámica

    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        X = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";    // Ejemplos de las
        Y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";     // diapositivas
        // X = "ABCBDAB";
        // Y = "BDCABA";

        // Mostrar los datos del problema
        System.out.println("X = " + X);
        System.out.println("Y = " + Y);


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:  " + solucion(A));
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
        int n = X.length() + 1, m = Y.length() + 1;     // +1 porque se contemplan subcadenas vacías
        A = new int[n][m];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = 0                           si  i = 0 || j = 0
     * A(i,j) = A(i-1,j-1) + 1              si  i,j > 0 && x[i] == y[j]
     * A(i,j) = MAX[A(i-1,j), A(i,j-1)]     si  i,j > 0 && x[i] != y[j]
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == 0 || j == 0) {
            A[i][j] = 0;

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                A[i][j] = A[i-1][j-1] + 1;
            } else {
                A[i][j] = Math.max(A[i-1][j], A[i][j-1]);
            }
        }
    }

    /**
     * Devuelve la SCL entre 2 cadenas de ADN.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Subsecuencia común más larga
     */
    private static String solucion(int[][] A) {
        // Inicializar los índices
        int i = X.length(), j = Y.length();

        // Inicializar la solución
        StringBuilder SCL = new StringBuilder();

        // Recorrer la tabla 'A' hacia atrás
        while (i > 0 && j > 0) {
            // Las moléculas coinciden
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                SCL.append(X.charAt(i-1));          // Añadir la molécula a la SCL
                i--;
                j--;                                // Disminuir los índices

            // Las moléculas no coinciden
            } else {
                // Se comprueba hacia dónde se mueve el recorrido
                if (A[i-1][j] >= A[i][j]) {
                    i--;

                } else {
                    j--;
                }
            }
        }

        // Devolver la solución
        return SCL.reverse().toString();    // Se han añadido al revés
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
        return A[X.length()][Y.length()];   // El valor óptimo está en la última celda
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
        sb.append("\t").append("\t").append("∅").append("\t");

        for (int j = 0; j < Y.length(); j++) {
            sb.append(Y.charAt(j)).append("\t");    // Moléculas de Y
        }

        sb.append("\n").append("\t").append("\t");

        for (int cabecera = 0; cabecera <= Y.length(); cabecera++) {
            sb.append(cabecera).append("\t");       // Tamaño de subcadenas de Y
        }

        // Filas
        for (int fil = 0; fil <= X.length(); fil++) {
            sb.append("\n");

            // Cabecera de la fila
            if (fil == 0) {
                sb.append("∅");                 // Subcadena X vacía

            } else {
                sb.append(X.charAt(fil-1));     // Moléculas de X
            }

            sb.append("\t").append(fil).append("\t");   // Tamaño de subcadenas de X

            // Valores de las columnas
            for (int col = 0; col <= Y.length(); col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
