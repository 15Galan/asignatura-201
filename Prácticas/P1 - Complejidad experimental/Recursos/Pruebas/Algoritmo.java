package pruebas;

public class Algoritmo {

    /*
     * Para usar esta clase de forma eficiente cambia la llamada
     * "Algoritmo.f(n)" en Analizador.java por el algoritmo deseado:
     * "Algoritmo.sumaFormula(n)", "Algoritmo.raizRecursiva(x, n)"...
     */

    public Algoritmo() {

    }

    // Θ(1)
    public long sumaFormula(long n) {
        return (n*(n+1)) / 2;
    }

    // Θ(log(n))
    public long potencia(int x, long n) {
        if (n == 0) {
            return 1;

        } else {
            if(n % 2 == 0) {
                return potencia(x*x, n/2);

            } else {
                return x * potencia(x*x, n/2);
            }
        }
    }

    // Θ(n)
    public long sumaRecursiva(long n) {
        if (n == 0) {
            return 0;

        } else {
            return n + sumaRecursiva(n-1);
        }
    }

    // Θ(n²)
    public long sumaSumas(long n) {
        if(n == 0) {
            return 0;

        } else {
            return sumaRecursiva(n) + sumaSumas(n-1);
        }
    }

    // Θ(2^n)
    public float raizRecursiva(float x, long n) {
        if(n == 0) {
            return 1;

        } else {
            return ((x/ raizRecursiva(x, n-1)) + (raizRecursiva(x, n-1))) / 2;
        }
    }
}
