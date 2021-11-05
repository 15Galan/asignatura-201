public class Ejercicio_1 {

    public static void main(String[] args) {
        int[] V;

        // V[i] = i en la mitad superior
        V = new int[]{-3, -1, 0, 1, 3, 5, 8, 10};
        System.out.println("Prueba 1: " + buscar(V, 0, V.length-1));

        // V[i] = i en la mitad inferior
        V = new int[]{-3, -1, 2, 4, 5, 7, 8, 10};
        System.out.println("Prueba 2: " + buscar(V, 0, V.length-1));

        // V[i] = i nunca se cumple
        V = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Prueba 3: " + buscar(V, 0, V.length-1));
    }

    /**
     * Busca un número que cumpla V[i] = i en el subvector indicado por los límites.
     *
     * @param V     Vector donde buscar
     * @param inf   Límite inferior del subvector
     * @param sup   Límite superior del subvector
     *
     * @return  El índice i que produce V[i] = i, o bien -1 si no se produce
     */
    private static int buscar(int[] V, int inf, int sup) {
        int res = -1, med = (inf+sup)/2;

        if (inf <= sup) {
            if (V[med] < med) {
                res = buscar(V, med + 1, sup);

            } else if (med < V[med]) {
                res = buscar(V, inf, med - 1);

            } else if (V[med] == med) {
                res = med;
            }
        }

        return res;
    }
}
