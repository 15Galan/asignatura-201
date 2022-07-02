package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * Un estudiante aventajado intenta organizar sus 'h' horas de estudio para
 * maximizar la nota acumulada que obtendrá en las 'n' asignaturas del cuatrimestre.
 * Para ello, analiza los temarios y obtiene una estimación 'c(i,j)' de la nota
 * numérica que obtendría en la asignatura 'i' dedicándole 'j' horas de estudio.
 *
 * Su objetivo ahora es determinar cuántas horas debe dedicar a cada asignatura.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_03 {

    // Datos del problema
    private static int[][] C;   // Tabla de notas estimadas
    private static int h;       // Número de horas de estudio
    private static int n;       // Número de asignaturas

    // Datos del desarrollo
    private static int[][] A;   // Tabla utilizada en el proceso de programación dinámica
    private static int[][] aux;   // Tabla utilizada para almacenar las horas dedicadas óptimas


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        C = new int[][] {                   // Ejemplo del profesor Julio
            {0, 3, 4, 6,  8,  9, 10, 10},   // Asignatura 1
            {1, 5, 6, 8, 10, 10, 10, 10},   // Asignatura 2
            {0, 4, 4, 5,  6,  7,  9, 10},   // Asignatura 3
            {0, 2, 4, 7,  7,  8,  8,  9}    // Asignatura 4
        };

        // C = new int[][] {              // Ejemplo de Diego
        //    {0, 1, 3, 5, 8, 10, 10},    // Asignatura 1 (A.D.A)
        //    {2, 3, 4, 6, 7,  8,  9},    // Asignatura 2 (Cálculo)
        //    {0, 2, 5, 7, 8,  9, 10},    // Asignatura 3 (Discreta)
        // };

        h = C[0].length;    // Número de columnas
        n = C.length;       // Número de filas


        // Mostrar los datos del problema
        System.out.println("Asignaturas: " + n);
        System.out.println("Horas de estudio: " + h);
        System.out.println("Notas estimadas:\n" + mostrarTabla(C));


        // Resolución
        rellenarTablaA();

        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
        System.out.println("\nTabla Auxiliar:\n" + mostrarTabla(aux));
    }


    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[n][h];
        aux = new int[n][h];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < h; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = c(i,j)                                  si  i = 0
     * A(i,j) = MAX[0 <= k <= j](A(i-1,k) + c(i,l-k))   si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        aux[i][j] = 0;

        // Caso base
        if (i == 0) {
            A[i][j] = C[i][j];  // Nota estimada acumulada
            aux[i][j] = j;      // Horas dedicadas acumuladas

        // Caso iterativo
        } else {
            int max = 0;    // Máxima nota acumulada
            int kax = 0;    // Máxima hora dedicada acumulada

            for (int k = 0; k <= j; k++) {
                int aux = A[i-1][j-k] + C[i][k];

                if (max < aux) {
                    max = aux;
                    kax = k;
                }
            }

            A[i][j] = max;
            aux[i][j] = kax;
        }
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @return      Solución óptima
     */
    private static int[] solucion() {
        int[] S = new int[n];           // Vector con la distribución de horas
        int resto = h-1;                // Se resta porque 'h' son las columnas

        for (int i = n-1; i >= 0; i--) {
            S[i] = aux[i][resto];
            resto -= aux[i][resto];
        }

        return S;
    }

    /**
     * Indica el valor óptimo de la solución
     * a partir de la tabla generada.
     *
     * @return      Valor óptimo
     */
    private static int valor() {
        return A[n-1][h-1];
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

        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append((char) (65 + fil)).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
