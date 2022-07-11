package Examenes.Curso_20_21;

import java.text.DecimalFormat;
import java.util.Arrays;



/**
 * Consideremos 'n' entidades financieras y que cada una ofrece un interés
 * anual diferente según la cantidad de dinero aportada.
 * Así 'I(i,j)' es el interés que nos dará la entidad financiera 'i' al cabo
 * de un año si depositamos la cantidad 'j'; donde 'i' varía desde 1 hasta
 * 'n' (número de bancos) y 'j' desde 1 hasta 'M' (cantidad en euros).
 *
 * Se pretende distribuir 'M' euros entre las 'n' entidades
 * de forma que el interés total obtenido sea máximo.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Control_INF {

    // Datos del problema
    private static double[][] I;    // Tabla que representa el interés anual de cada banco
    private static int n;           // Número de bancos
    private static int M;           // Cantidad de euros a depositar

    // Datos del desarrollo
    private static double[][] A;    // Tabla utilizada en el proceso de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        I = new double[][] {    // Ejemplo del parcial
            {0.0, 0.3, 0.4, 0.6, 0.8, 0.9, 1.0, 1.0},  // Entidad 1
            {0.0, 0.5, 0.6, 0.8, 1.0, 1.0, 1.0, 1.0},  // Entidad 2
            {0.0, 0.4, 0.4, 0.5, 0.6, 0.7, 0.9, 1.0},  // Entidad 3
            {0.0, 0.2, 0.4, 0.7, 0.7, 0.8, 0.8, 0.9}   // Entidad 4
        };

        // I = new double[][] {    // Ejemplo de Diego
        //     {0.0, 0.1, 0.2, 0.4, 0.6, 0.7},  // Entidad 1
        //     {0.0, 0.1, 0.3, 0.4, 0.5, 0.8},  // Entidad 2
        //     {0.0, 0.0, 0.2, 0.5, 0.7, 0.8}   // Entidad 3
        // };

        M = I[0].length;    // Número de columnas
        n = I.length;       // Número de filas


        // Mostrar los datos del problema
        System.out.println("Dinero: " + (M-1));     // Porque hay 1 columna más
        System.out.println("Bancos: " + n);
        System.out.println("Intereses:\n" + mostrarTabla(I));


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---\n");
        System.out.println("Tabla A:\n" + mostrarTabla(A) + "\n");
    }


    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new double[n][M];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < M; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = I(i,j)                                   si  i = 0
     * A(i,j) = MAX[0 <= k <= j][A(i-1,k) + I(i,j-k)]    si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == 0) {
            A[i][j] = I[i][j];

        // Caso iterativo
        } else {
            double max = 0.0;

            for (int k = 0; k <= j; k++) {
                double aux = A[i-1][k] + I[i][j-k];

                if (max < aux) {
                    max = aux;
                }
            }

            A[i][j] = max;
        }
    }

    /**
     * Calcula la cantidad de euros a invertir en cada banco.
     *
     * @return  Vector con la cantidad 'm' de euros a invertir en la entidad 'i'
     */
    private static int[] solucion() {
        int[] S = new int[n];           // Euros a invertir en cada banco
        double[] D = new double[n];     // Interés óptimo de cada entidad
        int resto = M-1;                // Cantidad restante de euros a depositar

        // Calcular el interés obtenido en cada entidad para la cantidad 'm' de euros
        for (int i = n-1; i >= 0; i--) {
            D[i] = A[i][resto];
            resto -= A[i][resto];
        }

        int ingresado = 0;  // Cantidad de euros ingresados en total

        // Calcular la cantidad de 'm' euros obtenida en cada entidad
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M; j++) {
                // Si el interés está en la distribución, se han metido 'm' euros en la entidad 'i'
                if (A[i][j] == D[i]) {
                    int m = j - ingresado;  // 'j' euros del total menos los ya ingresados

                    S[i] = m;
                    ingresado = j;          // Se actualiza el total de ingresos
                }
            }
        }

        return S;
    }

    /**
     * Calcula el interés máximo obtenido al repartir 'M' euros entre las 'n' entidades.
     *
     * @return  El valor de la tabla 'A' que representa el interés máximo
     */
    private static double valor() {
        return A[n-1][M-1];
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(double[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t\t");
        }

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append(fil+1).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                DecimalFormat df = new DecimalFormat("#.##");   // Redondear 2 decimales
                sb.append(df.format(tabla[fil][col])).append("\t\t");   // como máximo (lo evita)
            }
        }

        return sb.toString();
    }
}
