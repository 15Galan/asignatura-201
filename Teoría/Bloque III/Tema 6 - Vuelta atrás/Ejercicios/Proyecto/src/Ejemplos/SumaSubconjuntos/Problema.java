package Ejemplos.SumaSubconjuntos;

import java.util.SortedSet;



public class Problema {

    protected static SortedSet<Integer> conjunto;
    protected static int MAXIMO;


    // --------------------------------------------------------
    // Constructor
    // --------------------------------------------------------

    /**
     *
     * @param numeros
     * @param maximo
     */
    public Problema(SortedSet<Integer> numeros, int maximo) {
        conjunto = numeros;
        MAXIMO = maximo;
    }


    // --------------------------------------------------------
    // Algoritmo de vuelta atrás
    // --------------------------------------------------------

    /**
     * Resuelve el problema de la suma de los elementos de una lista.
     *
     * @return Subconjunto de elementos que componen la solución óptima
     */
    public SortedSet<Integer> resolver() {
        SortedSet<Integer> subconjunto = new java.util.TreeSet<>();

        return resolver(conjunto, subconjunto, 0);
    }

    /**
     * Resuelve el problema de la suma de los elementos de un conjunto.
     *
     * @param conjunto  Conjunto de elementos
     * @param mejor     Subconjunto de elementos que componen la solución óptima
     * @param suma      Suma de los elementos de la solución
     *
     * @return Subconjunto de elementos que componen la solución óptima
     */
    public SortedSet<Integer> resolver(SortedSet<Integer> conjunto, SortedSet<Integer> mejor, int suma) {
        // Datos para la resolución
        SortedSet<Integer> mejorSubconjunto = mejor;    // Subconjunto que compone la solución óptima
        int mejorSuma = suma;                           // Suma de los elementos que componen la solución óptima

        // Comprobar si la suma es mayor que el máximo
        for (int numero : conjunto) {
            // Comprobar si el número es mayor que todos los de un conjunto
            if (!mejor.contains(numero) && esMayor(numero, mejor) && suma + numero <= MAXIMO) {
                // Añadir el número al subconjunto
                SortedSet<Integer> auxiliar = new java.util.TreeSet<>(mejor);
                auxiliar.add(numero);

                // Comprobar si es mejor que la solución óptima
                SortedSet<Integer> solucion = resolver(conjunto, auxiliar, suma + numero);
                int sumaSolucion = suma(solucion);

                if (mejorSuma < sumaSolucion) {
                    mejorSubconjunto = solucion;
                    mejorSuma = sumaSolucion;
                }
            }
        }

        return mejorSubconjunto;
    }


    // --------------------------------------------------------
    // Métodos auxiliares
    // --------------------------------------------------------

    /**
     * Comprueba si un número es mayor que todos los de un conjunto.
     *
     * @param numero    Número a comprobar
     * @param conjunto  Conjunto donde comprobar
     *
     * @return TRUE si el número es menor que el máximo, FALSE en caso contrario
     */
    public static boolean esMayor(int numero, SortedSet<Integer> conjunto) {
        for (int elemento : conjunto) {
            if (numero <= elemento) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calcula la suma de los elementos de un conjunto.
     *
     * @param conjunto  Conjunto de elementos
     *
     * @return Suma de los elementos
     */
    public static int suma(SortedSet<Integer> conjunto) {
        int suma = 0;

        for (int numero : conjunto) {
            suma += numero;
        }

        return suma;
    }
}
