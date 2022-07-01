package Ejercicios.Basicos;



/**
 * PROBLEMA:
 * A lo largo de un río que solo se puede navegar en una dirección,
 * se encuentran 'n' embarcaderos.
 * Se concoce el coste 'T(i,j)' para ir del embarcadero 'i' al 'j',
 * que puede ser más barato o más caro que un recorrido de 'i' a 'j'
 * con paradas intermedias.
 *
 * Se desea encontrar el coste mínimo para viajar entre cualesquiera
 * de los embarcaderos 'i' y 'j'.
 *
 * EJEMPLO:
 * Propuesto en los datos (porque es una tabla).
 *
 *      SOLUCIÓN: Propuesta en la resolución (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_05 {

    // Datos del problema
    private static int[][] T;   // Costes de trayectos

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[][]{
            {0, 4, 3, 5, 8},
            {0, 0, 1, 2, 5},
            {0, 0, 0, 2, 4},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0}
        };


        // Mostrar los datos del problema
        System.out.println("Costes de trayectos:\n" + mostrarTabla(T));


        // Resolución
        rellenarTablaA();

        System.out.println("Solución:\n" + mostrarTabla(A));    // Esta vez, la tabla A representa los valores pedidos
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    public static void rellenarTablaA() {
        // Inicializar la tabla
        int n = T.length;
        A = new int[n][n];

        // Aplicar la ecuación de Bellman
        for (int i = n-1; 0 <= i; i--) {       // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = ?   si  ?
     * A(i,j) = ?   si  ?
     * A(i,j) = ?   si  ?
     *
     * @param i Fila de la tabla 'A'
     * @param j Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == j) {
            A[i][j] = 0;

        // Caso iterativo
        } else {
            int min = Integer.MAX_VALUE;

            for (int k = i+1; k <= j; k++) {
                int valor = T[i][k] + A[k][j];

                if (valor < min) {
                    min = valor;
                }
            }

            A[i][j] = min;
        }
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
            sb.append("\t").append((char) (65 + j));    // Embarcadero de destino
        }

        sb.append("\n");

        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append((char) (65 + i)).append("\t");    // Embarcadero de origen

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