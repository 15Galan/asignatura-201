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
	 * Ordena ascendentemente un intervalo del vector.
	 *
	 * @param V		Vector a ordenar
	 * @param izq 	Valor inicial del intervalo
	 * @param der 	Valor final del intervalo
	 * @param <T> 	Tipo del vector
	 */
	public static <T extends Comparable <? super T>> void ordRapidaRec(T[] V, int izq, int der) {
		if(izq < der) {
			int med = partir(V, V[izq], izq, der);

			ordRapidaRec(V, izq, med);
			ordRapidaRec(V, med+1, der);
		}
	}

	/**
	 * Divide un intervalo del vector en 2 sub-intervalos de forma
	 * que todos los elementos inferiores a un pivote son menores
	 * que él y todos los superiores son mayores o iguales que él.
	 *
	 * @param V 	Vector a dividir
	 * @param med 	Valor por el que dividir el intervalo (pivote)
	 * @param izq 	Valor inicial del intervalo
	 * @param der 	Valor final del intervalo
	 * @param <T> 	Tipo del vector
	 *
	 * @return 	Nuevo pivote
	 */
	public static <T extends Comparable <? super T>> int partir(T[] V, T med, int izq, int der) {
		int i = izq - 1, d = der + 1;

		while (i < d) {
			do {
				d--;	// Mover el puntero derecho a la izquierda

			} while (0 < V[d].compareTo(med));

			do {
				i++;	// Mover el puntero izquierdo a la derecha

			} while (V[i].compareTo(med) < 0);

			if (i < d) {
				Ordenacion.intercambiar(V, i, d);
			}
		}

		return d;
	}
}
