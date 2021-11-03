package pruebas.NlogaritmoN;

public class Algoritmo {
	private static int[] a;
	private static int n;

	public Algoritmo() {

	}

	public static synchronized void f(long n) {
		int[] a = new int[(int) n];

		for(int i = a.length-1; i >= 0; i--) {
			a[i] = i;
		}

		ordenar(a);
	}
	
	
	private static void construirHeap(int[] a){
		n = a.length-1;

		for(int i = n/2; i >= 0; i--){
			maximoHeap(a, i);
		}
	}
	    
	private static void maximoHeap(int[] a, int i){
		int left = 2 * i;
		int right = 2 * i + 1;
		int largest;

		if(left <= n && a[left] > a[i]){
			largest = left;
		}else{
			largest = i;
		}
	        
		if(right <= n && a[right] > a[largest]){
			largest = right;
		}
		if(largest != i){
			intercambiar(i, largest);
			maximoHeap(a, largest);
		}
	}
	    
	private static void intercambiar(int i, int j){
		int t = a[i];

		a[i] = a[j];
		a[j] = t;
	}
	    
	private static void ordenar(int[] a0){
		a = a0;

		construirHeap(a);
	        
		for(int i = n; i > 0; i--){
			intercambiar(0, i);
			n = n - 1;
			maximoHeap(a, 0);
		}
	}
}
