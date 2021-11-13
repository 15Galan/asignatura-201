// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// 		Práctica 2: Ordenación rápida
// 			Apartado (d)

import java.util.Arrays;


/**
 * Ordena un vector de elementos de forma ascendente.
 *
 * @author 		Antonio J. Galán Herrera
 */
public class OrdenacionJava extends Ordenacion {

	// Pequeños ejemplos para pruebas iniciales.
	public static void main (String[] args) {

		// Un vector de enteros
		Integer[] vEnt = {3,8,6,5,2,9,1,1,4};
		ordenar(vEnt);
		System.out.println(vectorAString(vEnt));

		// Un vector de caracteres
		Character[] vCar = {'d','c','v','b'};
		ordenar(vCar);
		System.out.println(vectorAString(vCar));
	}

	/**
	 * Ordena un vector usando un método "Arrays.sort()" de la librería "java.util".
	 *
	 * @param V 	Vector a ordenar
	 * @param <T> 	Tipo del vector
	 */
	public static <T extends Comparable<? super T>> void ordenar(T[] V) {
   		Arrays.sort(V);
	}
}
