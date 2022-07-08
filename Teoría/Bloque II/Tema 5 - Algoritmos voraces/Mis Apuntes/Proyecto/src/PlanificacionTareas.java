import java.util.LinkedList;
import java.util.List;


/**
 * Supongamos que disponemos de 𝒏 trabajadores y 𝒏 tareas.
 *
 * Sea 𝒃𝒊𝒋 > 𝟎 el coste de asignarle el trabajo 𝒋 al trabajador 𝒊.
 *
 * Una asignación de tareas puede ser expresada como una asignación de
 * los valores 𝟎 o 𝟏 a las variables 𝒙𝒊𝒋, donde 𝒙𝒊𝒋 = 𝟎 significa que al
 * trabajador 𝒊 no le han asignado la tarea 𝒋, y 𝒙𝒊𝒋 = 𝟏 indica que sí.
 *
 * Una asignación válida es aquella en la que a cada trabajador solo le
 * corresponde una tarea y cada tarea está asignada a un trabajador.
 *
 * Dada una asignación válida, definimos el coste 𝑪(𝒙) de dicha asignación como:
 * 𝑪(𝒙) = ∑ ∑ (𝒙𝒊𝒋 · 𝒃𝒊𝒋)
 */
public class PlanificacionTareas {

    // Datos del problema
    private static final int n = 3;


    /**
     * Método que inicializa el escenario del problema.
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] b = new int[][] {{16,20,18}, {11,15,17}, {17,1,20}};
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
     * Genera las asignaciones de las tareas.
     *
     * @param b     Matriz de costes
     *
     * @return    Vector de asignaciones
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
     * Obtener la lista de tareas disponibles.
     *
     * @param lista     Lista de candidatos
     *
     * @return  Índice de la tarea con el coste más bajo
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
     * Obtener la tarea con el coste más bajo.
     *
     * @param candidatos    Lista de candidatos
     * @param i             Índice de la tarea
     * @param b             Matriz de costes
     *
     * @return  Índice de la tarea con el coste más bajo
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
