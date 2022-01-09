package Ejemplos.SumaSubconjuntos;

import java.util.*;


public class Solucion {

    /**
     * Resuelve todas las soluciones de la suma de los elementos de una lista.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Datos del problema
        SortedSet<Integer> numeros = new TreeSet<>(Arrays.asList(1,3,4,6,8,9));
        int maximo = 14;

        // Crear el problema
        Problema problema = new Problema(numeros, maximo);
        System.out.println("Números:\t" + Arrays.toString(numeros.toArray()));
        System.out.println("Máximo:\t\t" + maximo + "\n");

        // Calcular la solución (óptima)
        SortedSet<Integer> solucion = problema.resolver();
        System.out.println("Solución:\t" + solucion + " (óptima)");
    }
}
