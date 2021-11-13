// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// 		Práctica 2: Ordenación rápida
// 			Apartado (b)


/**
 * Ordena un vector de elementos de forma ascendente.
 *
 * @author 		Antonio J. Galán Herrera
 */
public class OrdenacionRapidaBarajada extends OrdenacionRapida {

    /**
     * Permuta aleatoriamente los elementos de un vector y lo ordena ascendentemente
     * Este método sirve para comparar tiempos experimentalmente.
     *
     * @param V     Vector a reordenar
     * @param <T>   Tipo del vector
     */
	public static <T extends Comparable<? super T>> void ordenar(T[] V) {
        barajar(V);
        ordRapidaRec(V, 0, V.length-1);
    }

    /**
     * Reordena aleatoriamente los datos de un vector
     *
     * @param V Vector a reordenar
     * @param <T> Tipo del vector
     */
    private static <T> void barajar(T[] V) {
        for (int i = V.length-1; i > 0; i--) {
            intercambiar(V, i, aleat.nextInt(i+1));
        }
    }
}
