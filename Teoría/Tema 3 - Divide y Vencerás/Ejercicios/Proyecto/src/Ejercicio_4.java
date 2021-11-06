//    █████████                ████
//   ███░░░░░███             ░░███
//  ███     ░░░   ██████   ███████   ██████   ██████
// ░███          ███░░███ ███░░███  ███░░███ ███░░███
// ░███         ░███ ░███░███ ░███ ░███████ ░███ ░███
// ░░███     ███░███ ░███░███ ░███ ░███░░░  ░███ ░███
//  ░░█████████ ░░██████ ░░████████░░██████ ░░██████
//   ░░░░░░░░░   ░░░░░░   ░░░░░░░░  ░░░░░░   ░░░░░░
// https://youtu.be/A5YDZYInF3A?t=3110
import java.util.Arrays;


public class Ejercicio_4 {

    public static void main(String[] args) {
        int[] V;

        V = new int[]{-3,5,-1,2};

        System.out.println("V = " + Arrays.toString(V));
        System.out.println("Suma Máxima: " + sumaMaxima(V));
    }


    /**
     * Calcula la suma máxima de los elementos de un vector.
     *
     * @param V     Vector del que extraer la suma máxima
     *
     * @return  La suma máxima que producen los elementos del vector
     */
    private static int sumaMaxima(int[] V) {
        int n = V.length;

        // Caso base
        if (n == 1) {
            return V[0];

        } else if (n == 0) {
            return -1;
        }

        int med = n/2;

        int[] izq = Arrays.copyOfRange(V, 0, med);
        int[] der = Arrays.copyOfRange(V, med, n-1);

        // Dividir
        int mejor = Math.max(sumaMaxima(izq), sumaMaxima(der));

        // Vencer
        int mejor_suma_izq = V[med-1];
        int suma_izq = V[med-1];

        for (int i = med-2; i > -1; i--) {
            suma_izq += V[i];
            mejor_suma_izq = Math.max(mejor_suma_izq, suma_izq);
        }

        int mejor_suma_der = V[med];
        int suma_der = V[med];

        for (int i = med+1; i < n; i++) {
            suma_der += V[i];
            mejor_suma_der = Math.max(mejor_suma_der, suma_der);
        }

        // Suma del mejor subvector que cruza la mitad del vector: mejor_suma_izq + mejor_suma_der
        return Math.max(mejor, mejor_suma_izq + mejor_suma_der);
    }
}
