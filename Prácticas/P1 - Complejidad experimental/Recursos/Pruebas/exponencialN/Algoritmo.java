package pruebas.exponencialN;

public class Algoritmo {

    public static synchronized int f(int x, long n) {
        if (n == 0) {
            return 1;

        } else {
            return (x / (f(x, n - 1))) + (f(x, n - 1));
        }
    }
}