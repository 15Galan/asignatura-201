/**
 * Clase que representa una resolución de un problema de la mochila, mediante un algoritmo voraz.
 *
 * @author Antonio J. Galán Herrera, Rocío Montalvo Lafuente
 */
public class MochilaAV extends Mochila {

    /**
     * Resolver el problema de la mochila usando un algoritmo voraz.
     *
     * @param problema		Problema de la mochila
     * @return				Solución del problema
     */
    public SolucionMochila resolver(ProblemaMochila problema) {
        // Ordenar los items de mayor a menor densidad mediante una función lambda
        problema.items.sort(
            (item1, item2) -> {
                double densidad1 = ((double)item1.valor)/((double)item1.peso);
                double densidad2 = ((double)item2.valor)/((double)item2.peso);
                int res;

                // Sustituir esta secuencia por 'Double.compare(densidad2, densidad1)' hace que
                // falle la complejidad del test de SIETTE, pese a la recomendación de IntelliJ
                if (densidad1 > densidad2) {
                    res = -1;
                } else if (densidad1 < densidad2) {
                    res = 1;
                } else {
                    res = 0;
                }

                return res;
            }
        );

        // Los ítems deben ordenarse previamente para un funcionamiento correcto del algoritmo
        return algoritmoVoraz(problema);
    }

    // -----------------------------------------------------------------

    /**
     * Calcula (aproximadamente) la mejor solución del problema indicado.
     *
     * @param problema  Problema de la mochila
     * @return          Solución del problema
     */
    private SolucionMochila algoritmoVoraz(ProblemaMochila problema) {
        // Datos del problema
        int capacidad   = problema.getPesoMaximo();

        // Datos para el algoritmo
        int[] solucion  = new int[problema.items.size()];
        int peso        = 0;
        int valor       = 0;

        // Introducir los ítems en la mochila mientras quepan
        for (Item item : problema.items) {
            int i = item.index;
            int unidades = item.unidades;

            // Introducir tantas unidades de ese ítem mientras quepan
            while (0 <= capacidad - peso - item.peso && solucion[i] < unidades) {
                solucion[i]++;
                peso += item.peso;
                valor += item.valor;
            }
        }

        return new SolucionMochila(solucion, peso, valor);
    }
}
