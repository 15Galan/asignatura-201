package Parciales;

import java.util.Arrays;



/**
 * Tras una gran subida de tensión, se han dañado los aparatos eléctricos de 'n' casas.
 * Se sabe que el tiempo necesario para cada reparación es 'T0', ..., 'Tn-1' horas.
 * El electricista que hará todas las reparaciones quiere que el tiempo total (considerando
 * el tiempo de espera y el de reparación) sea mínimo.
 *
 * Diseñar un algoritmo voraz que, dados los tiempos, determine en qué orden deben hacerse las
 * reparaciones y calcule el tiempo total de esperas y reparación.
 *
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2020 {

    // Datos del problema
    private static int n;       // Número de casas
    private static int[] T;     // Tiempos de reparación de cada casa


    /**
     * Método que inicializa y resuelve el problema para un ejemplo concreto.
     *
     * @param args  null
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[] {10, 2, 8, 4, 6, 5};
        n = T.length;

        // Mostrar el problema
        System.out.println("Tiempos:\t" + Arrays.toString(T) + "\n");


        // Resolver el problema
        int[] solucion = algoritmoVoraz();
        int tiempoTotal = calcularTiempo(solucion);

        // Mostrar la solución
        System.out.println("Solución:\t" + Arrays.toString(solucion));
        System.out.println("Espera:\t\t" + tiempoTotal + " horas");
    }


    // -------------------------------------------------------------------------
    // Algoritmo voraz
    // -------------------------------------------------------------------------

    /*
    HEURÍSTICA:
    Ordenando los tiempos de menor a mayor, la espera que se va arrastrando para
    el siguiente vecino es mínima. Para cada vecino, el tiempo de espera resulta:

    Vecino 0:   T0                          -> Es el primero, no espera
    Vecino 1:   T0 + T1                     -> Es el segundo, espera al primero
    Vecino 2:   T0 + T1 + T2                -> Es el tercero, espera al segundo y el primero
    ( . . . )
    Vecino n:   T0 + T1 + T2 + ... + Tn     -> Es el último, espera a todos los anteriores

    Al final, se trata de minimizar el tiempo de espera del último vecino, y como se
    ha mencionado arriba, ordenando los tiempos de reparación de menor a mayor, el
    tiempo de espera que se va arrastrando es mínimo.

    Dicho mínimo surge de la fórmula:   n·T0 + (n-1)·T1 + (n-2)·T2 + ... + 1·Tn,
    que se obtiene al sumar todos los tiempos de espera, teniendo en cuenta:

    Total = T0 + (T0 + T1) + (T0 + T1 + T2) + ... + (T0 + ... + Tn).
     */

    /**
     * Resuelve el problema de ordenación de tiempos de reparación de 'n' casas.
     *
     * @return  Vector de tiempos de reparación ordenados de menor a mayor
     */
    public static int[] algoritmoVoraz() {
        // Inicializar la solución con una copia de los tiempos
        int[] solucion = Arrays.copyOf(T, n);

        ordenar(solucion);  // El algoritmo voraz consiste básicamente en esto

        return solucion;
    }


    /**
     * Calcula el tiempo de espera total de una solución.
     *
     * @param tiempos   Vector de tiempos de reparación
     */
    public static int calcularTiempo(int[] tiempos) {
        int espera = 0;

        for (int i = 0; i < n; i++) {
            espera += tiempos[i] * (n-i);
        }

        return espera;
    }


    // -------------------------------------------------------------------------
    // Métodos auxiliares
    // -------------------------------------------------------------------------

    /**
     * Ordena los elementos de un vector de menor a mayor.
     *
     * @param vector    Vector de tiempos
     */
    private static void ordenar(int[] vector) {
        Arrays.sort(vector);
    }
}
