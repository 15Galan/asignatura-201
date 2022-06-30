package Ejercicios.Basicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * PROBLEMA:
 * Dado un conjunto de 'n' objetos, cada uno con un peso 'P = p_1, p_2, ..., p_n',
 * supongamos que queremos repartir los objetos en dos montones diferentes,
 * de manera que queden lo más equilibrados posible en peso.
 *
 * Para conocer el desequilibrio mínimo queremos aplicar un algoritmo de Programación Dinámica.
 *
 * Encontrar la solución y su desequilibrio mínimo.
 *
 *
 * EJEMPLO:
 * Sea P = {2,3,7,8,1,4,5}.
 *
 *      SOLUCIÓN: {{7,8}, {1,2,3,4,5}} o bien {{3,4,8}, {1,2,5,7}},
 *                donde el desequilibrio mínimo es 0 en ambos casos.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_07 {

    // Datos del problema
    private static List<Integer> P; // Pesos
    private static int n;           // Cantidad de pesos
    private static int T;           // Peso total
    private static int M;           // Mitad del peso total

    // Datos del desarrollo
    private static boolean[][] A;   // Tabla de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        P = new ArrayList<>(Arrays.asList(2, 3, 7, 8, 1, 4, 5));
        // P = new ArrayList<>(Arrays.asList(2, 3, 1, 4));
        n = P.size();
        T = suma(P, n-1);
        M = T/2;


        // Mostrar los datos del problema
        System.out.println("Pesos: " + Arrays.toString(P.toArray()));
        System.out.println("Peso total: " + T);


        // Resolución
        P.sort(Integer::compareTo);     // Ordenar los pesos de menor a mayor

        rellenarTablaA();

        List<List<Integer>> S = solucion(A);

        System.out.println("\nSolución:  " + S);
        System.out.println("\tValor: " + valor(S));


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
        A = new boolean[n+1][M+1];  // +1 porque contemplan pesos y montón vacíos

        // Aplicar la ecuación de Bellman
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna concretas de la tabla 'A'.
     *
     *
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Solo hay un caso porque la tabla se inicializa a 'false'
        if (i == 0 || j == 0) {
            A[i][j] = false;

        } else {
            A[i][j] = suma(P, i-1) >= j;
        }
    }

    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    private static List<List<Integer>> solucion(boolean[][] A) {
        // Inicializar los índices
        int i = n-1, j = M;

        // Inicializar la solución
        List<Integer> Sa = new ArrayList<>();   // Solución (montón A)
        List<Integer> Sb = new ArrayList<>(P);  // Copia de P

        // Recorrer la tabla generando la solución óptima
        while (i > 0 && j > 0) {
            int p = P.get(i-1);

            // Si el valor acumulado es mejor que el anterior
            if (A[i][j] != A[i-1][j]) {
                Sa.add(p);              // Añadir el peso al montón A
                Sb.remove(i-1);     // Eliminar el peso del montón B
                j -= p;                 // Restar el peso al peso a alcanzar
            }

            i--;    // Se descarta el peso
        }

        // Ordenar la solución
        Sa.sort(Integer::compareTo);    // Porque se añadieron en orden inverso
        Sb.sort(Integer::compareTo);    // Porque se añadieron en orden inverso

        // Devolver la solución óptima
        List<List<Integer>> S = new ArrayList<>();
        S.add(Sa); S.add(Sb);

        return S;
    }

    /**
     * Indica el valor de la solución.
     *
     * @param S     Solución
     *
     * @return      Valor
     */
    private static int valor(List<List<Integer>> S) {
        int a = S.get(0).size();    // Tamaño del montón A
        int b = S.get(1).size();    // Tamaño del montón B

        return Math.abs(suma(S.get(0), a-1) - suma(S.get(1), b-1));
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Calcula el peso de un conjunto de pesos hasta un elemento.
     *
     * @param lista     Conjunto de pesos
     * @param j         Último elemento a sumar
     *
     * @return  Suma de todos los pesos hasta un elemento.
     */
    private static int suma(List<Integer> lista, int j) {
        int suma = 0;

        for (int i = 0; i <= j; i++) {
            suma += lista.get(i);
        }

        return suma;
    }

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(boolean[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        sb.append("\t");

        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t");
        }

        sb.append("\n");

        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {

            // Cabecera de la fila
            if (fil == 0) {
                sb.append("∅");     // Conjunto de items vacío

            } else {
                sb.append(P.get(fil-1));     // Índice del item
            }

            sb.append("\t");

            // Valores de las columnas
            for (int col = 0; col < tabla[0].length; col++) {

                if (tabla[fil][col]) {
                    sb.append("✔");

                } else {
                    sb.append("✘");
                }

                sb.append("\t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
