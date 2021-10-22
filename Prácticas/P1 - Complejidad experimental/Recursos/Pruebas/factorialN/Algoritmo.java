package pruebas.factorialN;

public class Algoritmo {

    public static synchronized long f(long n) {
		  for(int i = 0; i < n; i++) {
			  f(n-1);
		  }

		  return n;
	}
}
