/**
 * @author Pepe Gallardo
 * @modifiedby Jose A. Onieva
 * @modifiedby Ricardo Conejo
 * Implementación de otros métodos de ordenación para comparar tiempos de ejecución
 */


public class OrdenacionMezcla extends Ordenacion {
	
    // Implementación de ordenación por mezcla (para comparar tiempos experimentalmente)
    public static <T extends Comparable<? super T>> void ordenar(T v[]) {
    	ordMezclaRec(v, 0, v.length);
    } 

    public static <T extends Comparable<? super T>> void ordMezclaRec(T v[], int izq, int der) {
        int n = der - izq;        

        if (n<=1) 
        	return; // Caso base
        else {        
	        int cen = izq + n/2; 
	        ordMezclaRec(v, izq, cen); 
	        ordMezclaRec(v, cen, der); 
	
	        // mezcla
	        @SuppressWarnings("unchecked")
	        T aux[] = (T []) new Comparable[n];
	
	        int i = izq, j = cen;
	        for (int k=0; k<n; k++) {
	            if (i==cen) aux[k] = v[j++];
	            else if (j==der) aux[k] = v[i++];
	            else if (v[j].compareTo(v[i]) < 0) aux[k] = v[j++];
	            else aux[k] = v[i++];
	        }
	        // Copiar el vector ordenado
	        for (int k=0; k<n; k++) 
	            v[izq+k] = aux[k];
        }        
    } 

    }
