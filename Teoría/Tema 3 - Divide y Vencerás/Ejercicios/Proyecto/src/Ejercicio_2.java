import java.util.Arrays;

public class Ejercicio_2 {

    public static void main(String[] args) {
        int[] V, W, X;

        // Datos de ejemplo
        V = new int[]{1,3,5,7,8,9};
        W = new int[]{4,6,7,8,9,10};
        X = combinar(V, W);

        // Mostrar los datos y la solución esperada
        System.out.println("V   = " + Arrays.toString(V) + ";\tMed = " + V[V.length/2]);
        System.out.println("W   = " + Arrays.toString(W) + ";\tMed = " + W[W.length/2]);
        System.out.println("V+W = " + Arrays.toString(X) + ";\tMed = " + X[X.length/2]);

        // Algoritmo de búsqueda
        System.out.println("Mediana calculada: " + mediana(V, 0, V.length-1, W, 0, W.length-1) + "\n\n");


        // Datos de ejemplo
        V = new int[]{1};
        W = new int[]{9};
        X = combinar(V, W);

        // Mostrar los datos y la solución esperada
        System.out.println("V   = " + Arrays.toString(V) + ";\tMed = " + V[V.length/2]);
        System.out.println("W   = " + Arrays.toString(W) + ";\tMed = " + W[W.length/2]);
        System.out.println("V+W = " + Arrays.toString(X) + ";\tMed = " + X[X.length/2]);

        // Algoritmo de búsqueda
        System.out.println("Mediana calculada: " + mediana(V, 0, V.length-1, W, 0, W.length-1) + "\n\n");


        // Datos de ejemplo
        V = new int[]{1,3,5,11,12};
        W = new int[]{4,5,8,9,10};
        X = combinar(V, W);

        // Mostrar los datos y la solución esperada
        System.out.println("V   = " + Arrays.toString(V) + ";\tMed = " + V[V.length/2]);
        System.out.println("W   = " + Arrays.toString(W) + ";\tMed = " + W[W.length/2]);
        System.out.println("V+W = " + Arrays.toString(X) + ";\tMed = " + X[X.length/2]);

        // Algoritmo de búsqueda
        System.out.println("Mediana calculada: " + mediana(V, 0, V.length-1, W, 0, W.length-1) + "\n\n");
    }


    /**
     * Busca la mediana de la combinación de 2 vectores.
     *
     * @param V     Vector 1
     * @param vi    Límite inferior del subvector del vector 1
     * @param vs    Límite superior del subvector del vector 1
     * @param W     Vector 2
     * @param wi    Límite inferior del subvector del vector 2
     * @param ws    Límite superior del subvector del vector 2
     *
     * @return  La mediana que resultaría de combinar ambos vectores
     */
    private static int mediana(int[] V, int vi, int vs, int[] W, int wi, int ws) {
        int vm = (vi+vs)/2;     // Índice de la mediana de V
        int wm = (wi+ws)/2;     // Índice de la mediana de W
        int res = -1;

        // Caso base (vectores de tamaño 1)
        if (V.length == 1 && W.length == 1) {
            res = Math.max(V[vm], W[wm]);

        // Caso base (subvectores de tamaño 2)
        } else if ((vs - vi == 1) && (ws - wi == 1)) {
            res = Math.min(V[vs], W[ws]);

        } else if (V[vm] < W[wm]) {
            res = mediana(V, vm, vs, W, wi, wm);

        } else if (V[vm] > W[wm]) {
            res = mediana(V, vi, vm, W, wm, ws);
        }

        return res;
    }

    /**
     * Combina los elementos de 2 vectores y los ordena.
     *
     * @param V     Vector 1
     * @param W     Vector 2
     *
     * @return  Un vector resultado de la mezcla ordenada de otros dos
     */
    private static int[] combinar(int[] V, int[] W) {
        int[] mezcla = new int[V.length + W.length];

        System.arraycopy(V, 0, mezcla, 0, V.length);
        System.arraycopy(W, 0, mezcla, V.length, W.length);

        Arrays.sort(mezcla);

        return mezcla;
    }
}
