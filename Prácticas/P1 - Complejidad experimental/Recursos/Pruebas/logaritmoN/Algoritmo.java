package pruebas.logaritmoN;

public class Algoritmo {

	public static synchronized int f(int b, long n){
        if(n == 0){
            return 1;

        }else if(n % 2 == 0){
            return f(b*b,n/2);

        }else{
            return b*f(b*b,n/2);
        }
    }
}