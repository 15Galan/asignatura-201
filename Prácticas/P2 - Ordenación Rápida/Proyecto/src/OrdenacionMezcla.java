/**
 * Implementación de otros métodos de ordenación para comparar tiempos de ejecución
 *
 * @author Pepe Gallardo
 *
 * @modifiedby Jose A. Onieva
 * @modifiedby Ricardo Conejo
 */


public class OrdenacionMezcla extends Ordenacion {
	
    // Implementación de ordenación por mezcla (para comparar tiempos experimentalmente)
    public static <T extends Comparable<? super T>> void ordenar(T[] V) {
    	ordMezclaRec(V, 0, V.length);
    } 

    public static <T extends Comparable<? super T>> void ordMezclaRec(T[] V, int izq, int der) {
        int n = der - izq;        

        if (n<=1)
        	return; // Caso base
        else {
	        int cen = izq + n/2; 
	        ordMezclaRec(V, izq, cen);
	        ordMezclaRec(V, cen, der);
	
	        // mezcla
	        @SuppressWarnings("unchecked")
			T[] aux = (T []) new Comparable[n];
	
	        int i = izq, j = cen;
	        for (int k=0; k<n; k++) {
	            if (i==cen) aux[k] = V[j++];
	            else if (j==der) aux[k] = V[i++];
	            else if (V[j].compareTo(V[i]) < 0) aux[k] = V[j++];
	            else aux[k] = V[i++];
	        }
	        // Copiar el vector ordenado
	        for (int k=0; k<n; k++)
	            V[izq+k] = aux[k];
        }        
    }
}
