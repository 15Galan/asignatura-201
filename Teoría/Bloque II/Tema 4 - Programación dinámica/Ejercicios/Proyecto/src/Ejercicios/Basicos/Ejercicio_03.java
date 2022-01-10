package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * Un estudiante aventajado intenta organizar sus 'H' horas de estudio para
 * maximizar la nota acumulada que obtendrá en las 'n' asignaturas del cuatrimestre.
 * Para ello, analiza los temarios y obtiene una estimación 'c(i,j)' de la nota
 * numérica que obtendría en la asignatura 'i' dedicándole 'j' horas de estudio.
 *
 * Su objetivo ahora es determinar cuántas horas debe dedicar a cada asignatura.
 */
public class Ejercicio_03 {

    // Datos del problema
    private static int[][] c;   // Tabla que representa las notas estimadas
    private static int H;       // Número de horas de estudio
    private static int n;       // Número de asignaturas

    // Datos del desarrollo
    private static int[][] A;   // Tabla utilizada en el proceso de programación dinámica
    private static int[][] B;   // Tabla utilizada para almacenar las horas dedicadas óptimas


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Datos del problema
        c = new int[][] {   // Ejemplo del profesor Julio
                {0, 3, 4, 6,  8,  9, 10, 10},   // Asignatura 1
                {1, 5, 6, 8, 10, 10, 10, 10},   // Asignatura 2
                {0, 4, 4, 5,  6,  7,  9, 10},   // Asignatura 3
                {0, 2, 4, 7,  7,  8,  8,  9}    // Asignatura 4
        };

//        c = new int[][] {   // Ejemplo de Diego
//                {0, 1, 3, 5, 8, 10, 10},    // Asignatura 1 (A.D.A)
//                {2, 3, 4, 6, 7,  8,  9},    // Asignatura 2 (Cálculo)
//                {0, 2, 5, 7, 8,  9, 10},    // Asignatura 3 (Discreta)
//        };

        H = c[0].length;    // Número de columnas
        n = c.length;       // Número de filas


        // Mostrar los datos del problema
        System.out.println("Se quiere repartir " + (H-1) + " horas para estudiar " + n + " asignaturas,");
        System.out.println("donde según las horas estudiadas, las notas variarían como:\n\n" + mostrarTabla(c));


        // Resolución
        rellenarTablaA();

        System.out.println("\nLa máxima nota acumulada es " + maximaNotaAcumulada() + ".");
        System.out.println("El reparto de horas entre cada asignatura es " + Arrays.toString(horasPorAsignatura()) + ".");


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
        System.out.println("\nTabla B:\n" + mostrarTabla(B));
    }


    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[n][H];
        B = new int[n][H];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < H; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = c(i,j)                                  si  i = 0
     * A(i,j) = MAX[0 <= k <= j](A(i-1,c-k) + c(i,k))   si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        B[i][j] = 0;

        // Caso base
        if (i == 0) {
            A[i][j] = c[i][j];
            B[i][j] = j;

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            int max = 0, kax = 0;

            for (int k = 0; k <= j; k++) {
                int aux = A[i-1][j-k] + c[i][k];

                if (max < aux) {
                    max = aux;
                    kax = k;
                }
            }

            A[i][j] = max;
            B[i][j] = kax;
        }
    }

    /**
     * Calcula el interés máximo obtenido al repartir 'M' euros entre las 'n' entidades.
     *
     * @return  El valor de la tabla 'A' que representa el interés máximo
     */
    private static int maximaNotaAcumulada() {
        int max = 0;

        for (int j = 0; j < H; j++) {
            int aux = A[n-1][j];

            if (max < aux) {
                max = aux;
            }
        }

        return max;
    }

    /**
     * Calcula la cantidad de euros a invertir en cada banco.
     *
     * @return  Vector con la cantidad 'm' de euros a invertir en la entidad 'i'
     */
    private static int[] horasPorAsignatura() {
        int[] reparto = new int[n];     // Vector con la distribución de horas
        int resto = H-1;                // Se resta porque 'H' son las columnas

        for (int i = n-1; i >= 0; i--) {
            reparto[i] = B[i][resto];
            resto -= B[i][resto];
        }

        return reparto;
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
            sb.append("\n").append(fil+1).append("\t");

            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }

        return sb.toString();
    }
}
