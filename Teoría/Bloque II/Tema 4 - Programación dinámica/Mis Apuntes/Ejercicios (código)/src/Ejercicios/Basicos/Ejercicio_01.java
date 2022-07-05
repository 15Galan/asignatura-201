package Ejercicios.Basicos;



/**
 * PROBLEMA:
 * Sean 'A_1, ..., A_n' un conjunto de matrices que se desean multiplicar en secuencia.
 * Se supone que estas matrices son compatibles, esto es,
 * la dimensión de A_i es 'n_i x m_i' y se cumple que m_i = n_i + 1.
 *
 * Nótese que al multiplicar 2 matrices de tamaños 'm x n' y 'n x p',
 * se realizan m·n·p multiplicaciones.
 *
 * El número total de multiplicaciones escalares que se realizan dependerá
 * del modo en que se asocien las matrices para la multiplicación.
 *
 * Implementar un algoritmo que calcule el número óptimo de multiplicaciones
 * escalares necesarias para multiplicar una serie de 'n'' matrices
 *
 *
 * EJEMPLO:
 * Sean las matrices A, B, C y D, cuyas respectivas
 * dimensiones son '2x3', '3x4', '4x5' y '5x6'.
 *
 *      SOLUCIÓN: 118 multiplicaciones
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_01 {

    // Datos del problema
    private static int[][] M;   // Matrices a multiplicar
    private static int n;       // Número de matrices a multiplicar

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        M = new int[][]{{2, 3}, {3, 5}, {5, 4}, {4, 6}};    // Dimensiones
        n = M.length;


        // Mostrar los datos del problema
        System.out.println("Matrices: " + mostrarMatrices());


        // Resolución
        rellenarTablaA();

        System.out.println("\nValor: " + valor());


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
        A = new int[n][n];

        // Aplicar la ecuación de Bellman (recorrer en diagonal)
        for (int i = 0; i < n; i++) {
            A[i][i] = 0;
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                                                   si  i = j
     * A(i,j) = MIN[i <= k < j][A(i,k) + A(i+k,j) + (n(i,k) + n(k+1,j) + m(i+k,j)]  si  i > j
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int fI_K = M[i][0];
            int fIK_J = M[k+1][0];
            int cIK_J = M[k+1][1];
            int v = A[i][k] + A[k+1][j] + (fI_K * fIK_J * cIK_J);

            if (v < min) {
                min = v;
            }
        }

        A[i][j] = min;
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @return      Solución óptima
     */
    private static int valor() {
        return A[0][n-1];
    }


    // --------------------------------------------------
    // Funciones auxiliares
    // --------------------------------------------------

    /**
     * Crea una representación de los nombres de las matrices y sus dimensiones.
     *
     * @return          Representación de las matrices
     */
    private static String mostrarMatrices() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n ; i++) {
            sb.append((char) (65 + i));
            sb.append(" (").append(M[i][0]);
            sb.append(" x ");
            sb.append(M[i][1]).append(")");

            if (i < n-1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    /**
     * Crea una representación de una tabla con sus cabeceras.
     *
     * @param tabla     Tabla a representar
     *
     * @return      Representación de la tabla
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append((char) (65 + cabecera)).append("\t");
        }

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append((char) (65+fil)).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
