package Examen;

import java.text.DecimalFormat;
import java.util.Arrays;


/**
 * Consideremos 'n' entidades financieras y que cada una ofrece un interés
 * anual diferente según la cantidad de dinero aportada.
 * Así 'interes(i,j)' es el interés que nos dará la entidad financiera 'i'
 * al cabo de un año si depositamos la cantidad 'j'; donde 'i' varía desde
 * 1 hasta 'n' (número de bancos) y 'j' desde 1 hasta 'M' (cantidad en euros).
 *
 * Se pretende distribuir 'M' euros entre las 'n' entidades
 * de forma que el interés total obtenido sea máximo.
 *
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2020 {

    // Datos del problema
    private static double[][] interes;  // Tabla que representa el interés anual de cada banco
    private static int M;               // Cantidad de euros a depositar
    private static int n;               // Número de bancos

    // Datos del desarrollo
    private static double[][] A;        // Tabla utilizada en el proceso de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Datos del problema
        interes = new double[][] {  // Ejemplo del parcial
                {0.0, 0.3, 0.4, 0.6, 0.8, 0.9, 1.0, 1.0},  // Entidad 1
                {0.0, 0.5, 0.6, 0.8, 1.0, 1.0, 1.0, 1.0},  // Entidad 2
                {0.0, 0.4, 0.4, 0.5, 0.6, 0.7, 0.9, 1.0},  // Entidad 3
                {0.0, 0.2, 0.4, 0.7, 0.7, 0.8, 0.8, 0.9}   // Entidad 4
        };

//        double interes = new double[][] {   // Ejemplo de Diego
//                {0.0, 0.1, 0.2, 0.4, 0.6, 0.7},  // Entidad 1
//                {0.0, 0.1, 0.3, 0.4, 0.5, 0.8},  // Entidad 2
//                {0.0, 0.0, 0.2, 0.5, 0.7, 0.8}   // Entidad 3
//        };


        // NOTA: Referente al parcial 2018-2019 (mismo ejercicio).
        // El de 2020-2021 ya incluye M = 0 en el ejemplo.

        /*
        Nótese que la primera columna es 0.0, correspondiente a un interés M = 0 euros.

        Esta columna se utiliza para facilitar el proceso de programación dinámica, y se llega
        a esta conclusión tras intentar rellenar la tabla 'A' por primera vez, ya que 'como la
        tabla A maneja el caso base de M = 0, entonces también debería haber un interés con 0;
        y es necesario que la tabla A tenga un caso 0, porque requiere repartir el dinero y es
        posible que se repartan 0 euros a una entidad'.

        Por tanto, debe haber un interés para M = 0 y por lógica, lo normal es que sea 0.0.
        */

        M = interes[0].length;  // Número de columnas
        n = interes.length;     // Número de filas


        // Mostrar los datos del problema
        System.out.println("Se quiere repartir " + (M-1) + " euros entre " + n + " entidades,");
        System.out.println("cuyos intereses siguen esta proporción (n\\M):\n" + mostrarTabla(interes));


        // Resolución
        rellenarTablaA();

        System.out.println("El interés máximo obtenido es " + maximoBeneficio() + " euros.");
        System.out.println("La distribución de euros en cada banco es " + Arrays.toString(eurosPorBanco()) + ".");

        // Información adicional
        System.out.println("\n--- Información adicional ---");
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
     * A(i,j) = interés(i,j)                                   si  i = 0
     * A(i,j) = MAX[0 <= k <= j](A(i-1,k) + interés(i,j-k))    si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == 0) {
            A[i][j] = interes[i][j];

        // Caso 'recursivo' (no se usa 'bellman()', sino 'A[][]')
        } else {
            double max = 0.0;

            for (int k = 0; k <= j; k++) {
                double aux = A[i-1][k] + interes[i][j-k];

                if (max < aux) {
                    max = aux;
                }
            }

            A[i][j] = max;
        }
    }

    /**
     * Calcula el interés máximo obtenido al repartir 'M' euros entre las 'n' entidades.
     *
     * @return  El valor de la tabla 'A' que representa el interés máximo
     */
    private static double maximoBeneficio() {
        double max = 0.0;

        for (int j = 0; j < M; j++) {
            double aux = A[n-1][j];

            if (max < aux) {
                max = aux;
            }
        }

        return max;
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Calcula la cantidad de euros a invertir en cada banco.
     *
     * @return  Vector con la cantidad 'm' de euros a invertir en la entidad 'i'
     */
    private static double[] eurosPorBanco() {
        double[] distribucion = new double[n];  // Vector con la distribución de euros
        int resto = M-1;                        // Se resta porque 'M' son las columnas

        for (int i = n-1; i >= 0; i--) {
            distribucion[i] = A[i][resto];
            resto -= A[i][resto];
        }

        return distribucion;
    }

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

        sb.append("\n");    // Separación de cabeceras y datos

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append(fil+1).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                DecimalFormat df = new DecimalFormat("#.##");   // Redondear 2 decimales
                sb.append(df.format(tabla[fil][col])).append("\t\t");   // como máximo (lo evita)
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
