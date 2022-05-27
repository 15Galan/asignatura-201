package Ejemplos;



/**
 * PROBLEMA:
 * Calcular el valor de la sucesión de Fibonacci que se encuentra en una posición concreta.
 *
 * EJEMPLO:
 * Calcular el valor en la quinta posición de la serie de Fibonacci.
 *
 *      SOLUCIÓN: 5.
 */
public class Fibonacci {

    public static void main(String[] args) {
        int posicion = 5;

        System.out.println("Sucesión de Fibonacci:\t\t0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 ...");
        System.out.println("Número en la " + posicion + "ª posición:\t" + fibonacci(posicion));
    }

    /**
     * Calcula un valor en la sucesión de Fibonacci
     * aplicando el método de programación dinámica.
     *
     * Los valores 0 y 1 se consideran casos base.
     *
     * @param n     Posición del valor buscado
     *
     * @return  Número de la sucesión en la posición indicada
     */
    private static int fibonacci(int n) {
        int res;

        if (n == 0 || n == 1) {
            res = 1;

        } else {
            int anterior = 1, actual = 1;

            for (int i = 0; i < n-1; i++) {
                int aux = actual;
                actual += anterior;
                anterior = aux;
            }

            res = actual;
        }

        return res;
    }
}
