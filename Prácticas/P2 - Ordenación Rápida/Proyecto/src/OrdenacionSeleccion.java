/**
 * @author Pepe Gallardo
 * @modifiedby Jose A. Onieva
 * @modifiedby Ricardo Conejo
 * Implementación de otros métodos de ordenación para comparar tiempos de ejecución
 */

public class OrdenacionSeleccion extends Ordenacion {

	// Implementación de ordenación por selección (para comparar tiempos experimentalmente)
	public static <T extends Comparable<? super T>> void ordenar(T v[]) {
		for(int i=0; i<v.length-1; i++) {
			int posMin = i;
			for (int j=i+1; j<v.length; j++) 
				if(v[posMin].compareTo(v[j])>0)
					posMin = j;
			intercambiar(v, i, posMin);	
		}		
	}	
}
