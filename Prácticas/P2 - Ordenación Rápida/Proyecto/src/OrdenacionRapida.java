// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// 		Práctica 2: Ordenación rápida
// 			Apartado (a)


/**
 * Ordena un vector de elementos de forma ascendente.
 *
 * @author 		Antonio J. Galán Herrera
 */
public class OrdenacionRapida extends Ordenacion {

	// Pequeños ejemplos para pruebas iniciales
	public static void main (String[] args) {

		// Un vector de enteros
		Integer[] enteros = {3,8,6,5,2,9,1,1,4};
		ordenar(enteros);
		System.out.println(vectorAString(enteros));

		// Un vector de caracteres
		Character[] caracteres = {'d','c','v','b'};
		ordenar(caracteres);
		System.out.println(vectorAString(caracteres));
	}

	/**
	 * Ordena ascendentemente los elementos de un vector.
	 *
	 * @param V 	Vector a ordenar
	 * @param <T> 	Tipo del vector
	 */
	public static <T extends Comparable<? super T>> void ordenar(T[] V) {
		ordRapidaRec(V, 0, V.length-1);
	}

	/**
	 * Ordena ascendentemente un intervalo de un vector.
	 *
	 * @param V		Vector a ordenar
	 * @param inf 	Límite inferior del intervalo
	 * @param sup 	Límite superior del intervalo
	 * @param <T> 	Tipo del vector
	 */
	public static <T extends Comparable <? super T>> void ordRapidaRec(T[] V, int inf, int sup) {
		// Caso base
			// Los límites del intervalo son iguales o están invertidos, indicando que
			// el intervalo está ordenado, por lo que no es necesario hacer nada más

		// Caso intermedio
		if(inf < sup) {
			// Se ordena el sub-vector entre los límites inferior y superior
			// en base al pivote, que será el primer elemento del sub-vector
			int piv = partir(V, V[inf], inf, sup);

			ordRapidaRec(V, inf, piv-1);	// Ordenar el sub-vector inferior
			ordRapidaRec(V, piv+1, sup);	// Ordenar el sub-vector superior
		}
	}

	/**
	 * Ordena un intervalo de un vector en base a un pivote, de forma
	 * que todos los elementos menores que él quedan a su izquierda.
	 *
	 * @param V 	Vector a dividir
	 * @param piv 	Pivote en base al que se ordenará el intervalo
	 * @param inf 	Límite inferior del intervalo
	 * @param sup 	Límite superior del intervalo
	 * @param <T> 	Tipo del vector
	 *
	 * @return 	La posición siguiente al último elemento menor desplazado
	 */
	public static <T extends Comparable <? super T>> int partir(T[] V, T piv, int inf, int sup) {
		int izq = inf, der = sup;	// Los valores originales no deben modificarse

		while (izq < der) {
			// Mover el puntero superior a la izquierda, hasta encontrar un valor menor que el pivote
			while (0 < V[der].compareTo(piv) && 0 < der) {
				der--;
			}

			// Mover el puntero inferior a la derecha, hasta encontrar un valor mayor/igual que el pivote
			while (V[izq].compareTo(piv) <= 0 && izq < V.length-1) {
				izq++;
			}

			// Si se encontraron valores desordenados, se intercambian (incluso el pivote)
			if (izq < der) {
				intercambiar(V, izq, der);
			}
		}

		// Colocar el pivote en la posición correcta al terminar una ordenación
		if (0 < piv.compareTo(V[der])) {
			intercambiar(V, inf, der);		// El pivote se encuentra en la posición 'inf'
		}

		return der;
	}
}
