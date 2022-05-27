package Parciales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Dado un array unidimensional de tamaño 'n', cumple que:
 * - Cada componente tiene un policía ('P') o un ladrón ('L').
 * - Cada policía puede atrapar a un solo ladrón.
 * - Un policía no puede atrapar a un ladrón que está a más distancia que 'K'.
 *
 * Se pide encontrar el máximo número de ladrones que pueden ser atrapados.
 *
 * (a) Diseñar un algoritmo voraz que, dado el array y el valor 'K': determine
 *     el máximo número de ladrones que pueden ser atrapados; y construya otro
 *     array donde para cada posición de un policía, indique la posición del
 *     ladrón que captura, y para cada posición de un ladrón, indique si fue
 *     capturado (-1) o no (1).
 *
 *     Ejemplo:
 *     - Array {P, L, P, L, L, P}.
 *     - K = 3.
 *     + Solución válida: {3, -1, 1, -1, -1, 4}, se capturan 3 ladrones.
 *
 * @author Antonio J. Galán Herrera
 */

public class Parcial_2018 {

    // Datos del problema
    private static char[] escenario;    // Vector de los policías (P) y ladrones (L)
    private static int K;               // Distancia máxima a la que un policía puede capturar un ladrón


    /**
     * Método que inicializa el escenario de los policías y los ladrones.
     *
     * @param args  null
     */
    public static void main(String[] args) {
        // Datos del problema
        escenario = new char[] {'P', 'L', 'P', 'L', 'L', 'P'};
        K = 3;

        // Mostrar problema
        System.out.println("Escenario:\t" + Arrays.toString(escenario));


        // Resolver problema
        int[] solucion = algoritmoVoraz();
        int L = contarCapturas(solucion);

        // Mostrar solución
        System.out.println("\nSolución:\t" + Arrays.toString(solucion));
        System.out.println("Capturas:\t" + L);
    }


    // ---------------------------------------------------------------------------------------------
    // Algoritmo voraz
    // ---------------------------------------------------------------------------------------------

    /**
     * HEURÍSTICA:
     * Tratar de capturar al ladrón más a la izquierda que no haya sido capturado,
     * con el policía más a la izquierda que no haya capturado a un ladrón.
     *
     * @return  Vector de capturas
     */
    public static int[] algoritmoVoraz() {
        int n = escenario.length;

        // Inicializar la solución
        int[] solucion = new int[n];

        /*
        Un -1 en una posición 'i' del vector, indica
        que el ladrón en esa posición no fue capturado.
         */

        for (int i = 0; i < n; i++) {
            solucion[i] = -1;
        }

        // Inicializar los punteros
        List<Integer> policias = new ArrayList<>();     // Lista de índices de los policías
        List<Integer> ladrones = new ArrayList<>();     // Lista de índices de los ladrones

        for (int i = 0; i < escenario.length; i++) {
            if (escenario[i] == 'P') {
                policias.add(i);

            } else {
                ladrones.add(i);
            }
        }

        // Comenzar a resolver
        int p = 0, l = 0;       // Índices de los policías y los ladrones

        while (p < policias.size() && l < ladrones.size()) {
            int P = policias.get(p);    // Policía actual
            int L = ladrones.get(l);    // Ladrón actual

            // Comprobar si el ladrón actual puede ser capturado
            if (Math.abs(P - L) <= K) {
                solucion[P] = L;

                p++; l++;   // Pasar a los siguientes policía y ladrón

            } else {

                /*
                Un ladrón está fuera de rango, por lo que no puede ser capturado
                y debe actualizarse el puntero que esté más a la izquierda.

                Esto es así porque como se está planteando recorrer el vector de
                izquierda a derecha, ambos punteros solo pueden avanzar; por tanto,
                lo que queda por tener en cuenta es qué puntero está más alejado
                (a la izquierda), para acercarlo más: si se acerca un ladrón, el
                policía actual quizás pueda atraparlo; si se acerca un policía,
                el ladrón actual quizás pueda ser atrapado.
                 */

                // Comprobar qué puntero está más a la izquierda
                if (P < L) {
                    p++;    // Pasar al siguiente policía

                } else {
                    l++;    // Pasar al siguiente ladrón
                }
            }
        }

        return solucion;
    }

    /**
     * Método que devuelve el número de ladrones capturados en una solución.
     *
     * @param capturas  Vector solución del algoritmo voraz
     *
     * @return  Número de ladrones capturados
     */
    public static int contarCapturas(int[] capturas) {
        int res = 0;

        /*
        Un 1 en una posición 'i' del vector, indica
        que el ladrón en esa posición fue capturado.
         */

        for (int i : capturas) {
            if (0 <= i) {
                res++;
            }
        }

        return res;
    }
}
