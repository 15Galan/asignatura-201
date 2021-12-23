import java.util.LinkedList;
import java.util.List;

public class Ejercicio_5 {

    private static final int n = 3;

    public static void main(String[] args) {
        int[][] b = new int[][] {{16,20,18},{11,15,17},{17,1,20}};
        List<Integer> solucion = asignaciones(b);

        // Algoritmo voraz
        int coste = 0, i = 0;

        for (int j : solucion) {
            coste += b[i][j];
            i++;
        }

        // Solución voraz
        System.out.println("Solución 'Voraz'");
        System.out.println("* Asignación:\t" + solucion);
        System.out.println("* Coste:\t\t" + coste);

        // Solución óptima
        System.out.println("\nSolución 'Óptima'");
        System.out.println("* Asignación:\t" + "[2, 0, 1]]");
        System.out.println("* Coste:\t\t" + 30);
    }

    /**
     *
     * @param b
     * @return
     */
    private static List<Integer> asignaciones(int[][] b) {
        List<Integer> solucion = new LinkedList<>();

        int i = 0;

        while (i < n) {
            List<Integer> candidatos = tareasDisponibles(solucion);
            solucion.add(obtenerMejorTarea(candidatos, i, b));

            i++;
        }

        return solucion;
    }

    /**
     *
     * @param lista
     * @return
     */
    private static List<Integer> tareasDisponibles(List<Integer> lista) {
        List<Integer> disponibles = new LinkedList<>();

        for (int j = 0; j < n; j++) {
            if (!lista.contains(j)) {
                disponibles.add(j);
            }
        }

        return disponibles;
    }

    /**
     *
     * @param candidatos
     * @param i
     * @param b
     * @return
     */
    private static Integer obtenerMejorTarea(List<Integer> candidatos, int i, int[][] b) {
        int mejor = candidatos.get(0);

        for (int candidato : candidatos) {
            if (b[i][candidato] < b[i][mejor]) {
                mejor = candidato;
            }
        }

        return mejor;
    }
}
