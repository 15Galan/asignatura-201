package Examenes.Curso_18_19;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Sea un vector de enteros 'A = {a_1, a_2, ..., a_n}', definimos
 * su «sumaducto» como el mayor valor que puede conseguirse sumando
 * elementos y/o productos de dos elementos adyacentes.
 *
 * Cada elemento solo puede estar en una suma o en un producto.
 *
 * Determinar el sumaducto de 'A'.
 *
 *
 * EJEMPLO:
 * Sea A = {1, 3, 4, 3, 2, 2, 5, 3}.
 *
 *      SOLUCIÓN: 36, porque 1 + (3·4) + (3·2) + 2 + (5·3) = 36.
 */
public class Febrero {

    // Datos del problema
    private static int[] A;     // Vector de enteros
    private static int n;       // Cantidad de elementos

    // Datos del desarrollo
    private static int[] SD;    // Vector de programación dinámica


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        A = new int[]{1, 3, 4, 3, 2, 2, 5, 3};
        n = A.length;


        // Mostrar los datos del problema
        System.out.println("A: " + Arrays.toString(A));


        // Resolución
        rellenarVectorSD();

        System.out.println("\nValor: " + valor());


        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nVector SD:\n" + Arrays.toString(SD));
    }

    /**
     * Rellena el vector de programación dinámica.
     */
    private static void rellenarVectorSD() {
        SD = new int[n+1];

        for (int i = 0; i <= n; i++) {
            bellman(i);
        }
    }


    /**
     * Aplica la ecuación de Bellman para el elemento i.
     *
     * @param i  Índice del elemento
     */
    private static void bellman(int i) {
        // NOTA: los accesos al vector A llevan un índice menos
        // porque el vector A empieza en la posición 0.

        // Caso base 1
        if (i == 0) {
            SD[i] = 0;

        // Caso base 2
        } else if (i == 1) {
            SD[i] = A[i-1];

        // Caso iterativo
        } else {
            SD[i] = Math.max(SD[i-1] + A[i-1], SD[i-2] + (A[(i-1)-1] * A[i-1]));
        }
    }

    /**
     * Devuelve el valor del sumaducto.
     */
    private static int valor() {
        return SD[n];
    }
}
