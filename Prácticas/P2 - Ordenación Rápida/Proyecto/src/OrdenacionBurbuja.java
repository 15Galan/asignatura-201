/**
 * Implementación de otros métodos de ordenación para comparar tiempos de ejecución
 *
 * @author Pepe Gallardo
 *
 * @modifiedby Jose A. Onieva
 * @modifiedby Ricardo Conejo
 */

public class OrdenacionBurbuja extends Ordenacion {
	
    // Implementación de ordenación por burbuja (para comparar tiempos experimentalmente)
	public static <T extends Comparable<? super T>> void ordenar(T[] V) {
		boolean seguir = true;
		for(int i=V.length-1; seguir; i--) {
			seguir = false;
			for(int j=0; j<i; j++)
				if(V[j].compareTo(V[j+1])>0) {
					intercambiar(V, j, j+1);
					seguir = true;
				}
		}
	}
}
