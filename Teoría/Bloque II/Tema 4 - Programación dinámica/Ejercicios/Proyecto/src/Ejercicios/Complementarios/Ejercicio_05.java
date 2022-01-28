package Ejercicios.Complementarios;

import java.util.Arrays;



/**
 * Un desconocido (que dice ser familiar suyo, aunque no se parece en nada a ti) se presenta
 * ante ti montado en un DeLorean y te da un anuario con todos los resultados de la quiniela
 * del próximo año.
 * Sabemos también el premio 'r_i' que habrá en la semana 'i' (0 <= i <= 'n') si se juega la
 * quiniela ganadora en dicha semana.
 * Para no levantar sospechas, decidimos que no podremos ganar más de una vez cada 'K' semanas
 * consecutivas (0 < 'K'); es decir, debe haber al menos 'K-1' semanas entre cada premio.
 *
 * Determinar cómo hay que jugar para maximizar la ganancia.
 */
public class Ejercicio_05 {

    // Datos del problema
    private static int[] r;     // Vector de premios semanales
    private static int n;       // Cantidad de semanas (y premios)
    private static int K;       // Semanas de margen para no ser sospechoso

    // Datos del desarrollo
    private static int[][] A;   // Tabla utilizada en el proceso de programación dinámica
    private static int p;       // Cantidad de premios posibles según 'n' y 'K'


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        r = new int[] {4, 10, 3, 5, 9, 6, 4, 4, 7, 8};
        n = r.length;
        K = 4;

        // Datos del desarrollo
        p = premiosMaximos();   // En esta ocasión, falta saber la cantidad de filas de 'A'


        // Mostrar los datos del problema
        System.out.println("Recibes un anuario que contiene los resultados de las quinielas de " + n + " semanas.");
        System.out.println("Quieres usarlo para hacer trampas, pero para no levantar sospechas, decides no ganar");
        System.out.println("ningún premio hasta que hayan pasado " + K + " semanas del último que ganaste.");
        System.out.println("La cantidad en miles de euros de los premios del anuario es la siguiente:\n");

        System.out.println(Arrays.toString(r));


        // Resolución
        rellenarTablaA();

        System.out.println("\nEl máximo beneficio que puede obtenerse es " + maximoBeneficio() + " miles de euros.");
        System.out.println("El reparto de premios para dicho beneficio es " + Arrays.toString(premiosGanados()) + ".");


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }


    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------

    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[p][n];

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = MAX[0 <= k <= j](r[j])                  si  j < K
     * A(i,j) = MAX[0 <= k <= j-K](A(i-1,k)) + r(j)     si  K <= j
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base: si se ganan 0 premios, el beneficio acumulado es 0
        if (i == 0) {
            int max = 0;

            // Encontrar el máximo hasta la semana actual
            for (int k = 0; k <= j; k++) {
                if (max < r[k]) {
                    max = r[k];
                }

                A[i][j] = max;
            }

        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            int max = 0;

            for (int k = 0; k <= j-K; k++) {
                int aux = A[i-1][k];

                if (max < aux) {
                    max = aux;
                }
            }

            A[i][j] = max + r[j];
        }
    }


    /**
     * Calcula el máximo beneficio que se puede obtener
     * con el anuario y la restricción de semanas 'K'.
     *
     * Se corresponde con la última celda de la tabla 'A'.
     *
     * @return  Máximo beneficio
     */
    private static int maximoBeneficio() {
        return A[p-1][n-1];
    }

    /**
     * Calcula qué premios deben ganarse usando el anuario.
     *
     * @return  Vector con los premios a los que apuntar, en orden
     */
    private static int[] premiosGanados() {
        int[] premios = new int[p];             // Vector con la distribución de premios
        int beneficio = maximoBeneficio();      // Beneficio máximo

        // Recorrer la tabla 'A' desde la solución (última fila) hasta el inicio
        for (int i = premios.length-1; i >= 0; i--) {
            int k = 0;

            // Encontrar la semana en la que se gana el beneficio del premio 'i'
            while (A[i][k] < beneficio) {
                k++;
            }

            premios[i] = r[k];  // Se añade el premio a la solución
            beneficio -= r[k];  // Se elimina el valor del premio añadido del beneficio total
        }

        return premios;
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Calcula el máximo número de premios que se pueden obtener de un anuario.
     *
     * @return  El máximo número de premios que se pueden obtener de un anuario
     */
    private static int premiosMaximos() {
        int premios = 0;
        int recorrido = 0;

        do {
            premios++;
            recorrido += K;

        } while (recorrido < n);

        return premios;
    }


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
