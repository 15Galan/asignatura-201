package Parciales;

import java.util.Arrays;



/**
 * En un contexto de rápida inflación, debemos adquirir 'n' ítems cuyo precio inicial 'C'
 * (siendo 0 < C) es para todos el mismo.
 * Sin embargo, sólo podemos comprar un ítem cada semana y el precio del i-ésimo ítem se
 * incrementa en progresión geométrica con tasa 'Ti' (siendo 1 < Ti).
 * Así, si compramos el ítem 'i' en la semana 't', siendo 0 <= t <= n-1, su precio será
 * C · Ti^t.
 *
 * Se pide diseñar un algoritmo voraz que dadas las tasas 'T0', ..., 'Tn-1' y el valor 'C',
 * determine en qué orden deben hacerse las compras y qué valor total tendrán, de modo que
 * dicho valor sea mínimo.
 *
 * En concreto, para cada semana 't', el algoritmo debe devolver un vector 'ítem' donde
 * 'ítem[t]' es el índice del ítem que debe comprarse en dicha semana 't'.
 *
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2014 {

    // Datos del problema
    private static int n;       // Número de ítems
    private static int[] T;     // Tasas de inflación
    private static int C;       // Precio inicial de cada ítem


    /**
     * Método que inicializa y resuelve el problema para un ejemplo concreto.
     *
     * @param args  null
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[] {3, 2, 4};
        C = 100;
        n = T.length;

        // Mostrar el problema
        System.out.println("Tasas:\t" + Arrays.toString(T) + "\n");
        System.out.println("Precio:\t" + C + "\n");


        // Resolver el problema
        int[] solucion  = algoritmoVoraz();
        int valor       = calcularCompra(solucion);

        // Mostrar la solución
        System.out.println("Solución:\t" + Arrays.toString(solucion));
        System.out.println("Compra:\t\t" + valor + " euros");
    }


    // -------------------------------------------------------------------------
    // Algoritmo voraz
    // -------------------------------------------------------------------------

    /**
     * HEURÍSTICA:
     * Ordenando los ítems de mayor a menor precio, se consigue que el ítem más caro tenga
     * la tasa más barata, por lo que el precio acumulado que se va arrastrando es mínimo.
     *
     * @return  Vector de ítems
     */
    public static int[] algoritmoVoraz() {
        // Inicializar la solución con una copia de las tasas
        int[] tasas = Arrays.copyOf(T, n);
        int[] solucion = new int[n];

        /*
        Para cada semana, el valor de la compra resulta:

        Compra 0:   C·Tn^t0                                             -> El más barato
        Compra 1:   C·Tn^t0 + C·Tn-1^t1                                 -> Cuesta más que el primero
        Compra 2:   C·Tn^t0 + C·Tn-1^t1 + C·Tn-2^t2                     -> Cuesta más que el segundo y el primero
        ( . . . )
        Compra n:   C·Tn^t0 + C·Tn-1^t1 + C·Tn-2^t2 + ... + C·T0^tn     -> El más caro

        Al final, se trata de comprar primero los ítems más caros para que tengan una tasa
        menor y su precio no se infle demasiado para que el valor final sea mínimo.

        Dicho mínimo surge de la fórmula:   n·(C·Tn^t0) + (n-1)·(C·Tn-1^t1) + ... + 1·(C·T0^tn),
        que se obtiene al sumar el valor de todas las compras, teniendo en cuenta:

        Total = C·Tn^t0 + (C·Tn^t0 + C·Tn-1^t1) + ... + (C·Tn^t0 + C·Tn-1^t1 + C·Tn-2^t2 + ... + C·T0^tn).
        */

        ordenar(tasas);

        for (int i = 0; i < n; i++) {
            solucion[i] = buscar(T, tasas[i]);
        }

        return solucion;
    }

    /**
     * Calcula el valor total de una solución.
     *
     * @param items     Vector de ítems
     *
     * @return  Valor total de la compra
     */
    public static int calcularCompra(int[] items) {
        int total = 0;

        for (int i = 0; i < n; i++) {
            total += C * Math.pow(T[items[i]], i);
        }

        return total;
    }


    // -------------------------------------------------------------------------
    // Métodos auxiliares
    // -------------------------------------------------------------------------

    /**
     * Ordena los elementos de un vector de menor a menor.
     *
     * @param vector  Vector a ordenar
     */
    private static void ordenar(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[i] < vector[j]) {
                    int aux = vector[i];
                    vector[i] = vector[j];
                    vector[j] = aux;
                }
            }
        }
    }


    /**
     * Busca un elemento en un vector.
     *
     * @param vector    Vector donde buscar
     * @param elemento  Elemento a buscar
     *
     * @return  Índice del elemento en el vector
     */
    private static int buscar(int[] vector, int elemento) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == elemento) {
                return i;
            }
        }

        return -1;
    }
}
