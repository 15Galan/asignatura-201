// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// 		Práctica 2: Ordenación rápida
// 			Apartado (c)

import java.util.Arrays;
import java.util.Scanner;


/**
 * Busca un elemento en un vector.
 *
 * @author 		Antonio J. Galán Herrera
 */
public final class BuscaElem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tam, k;

		System.out.print("Introduce el número de posiciones del vector: ");
		tam = sc.nextInt();

		System.out.print("Introduce " + tam + " enteros separados por espacios: ");
		Integer[] v = new Integer[tam];

		for (int i = 0; i < tam; i++) {
			v[i] = sc.nextInt();
		}

		System.out.print("Introduce la posición k deseada (de 1-" + tam + "): ");
		k = sc.nextInt();

		System.out.print("El elemento " + k + "-esimo es: " + kesimo(v,k-1));
	}

	/**
	 * Devuelve el valor contenido en la posición k-ésima
	 * de un vector si estuviera ascendentemente ordenado.
	 *
	 * @param V 	Vector donde buscar
	 * @param k 	Posición del valor deseado
	 * @param <T> 	Tipo del vector
	 *
	 * @return 	Valor k-ésimo del vector si estuviera ascendentemente ordenado
	 */
	public static <T extends Comparable <? super T>> T kesimo(T[] V, int k) {
		T[] W = Arrays.copyOf(V, V.length);		// Copia del array original para no modificarlo con 'partir()'

		return kesimoRec(W, 0, W.length-1, k);
	}

	/**
	 * Busca el valor k-ésimo en un intervalo de un vector
	 * definido por sus extremos inferior y superior (incluidos).
	 *
	 * @param V 	Vector donde buscar
	 * @param inf 	Límite inferior del intervalo
	 * @param sup 	Límite superior del intervalo
	 * @param k 	Posición del elemento deseado
	 * @param <T> 	Tipo del vector
	 *
	 * @return 	Valor k-ésimo del intervalo si estuviera ascendentemente ordenado
	 */
	public static <T extends Comparable <? super T>> T kesimoRec(T[] V, int inf, int sup, int k) {
		// Caso base
			// El intervalo es de tamaño 0, por lo que ambos límites del intervalon apuntan a 'k'

		// Caso intermedio
		if (inf < sup) {
			int piv = OrdenacionRapida.partir(V, V[inf], inf, sup);

			if (k == piv) {
				return V[k];	// El valor se ha encontrado al calcular el pivote

			} else if (k < piv) {
				return kesimoRec(V, inf, piv-1, k);	// Buscar en el intervalo inferior al pivote

			} else {
				return kesimoRec(V, piv+1, sup, k);	// Buscar en el intervalo superior al pivote
			}
		}

		return V[k];
    }
}
