import java.util.ArrayList;


/**
 * Clase que representa una resolución de un problema de la mochila, mediante un algoritmo voraz.
 *
 * @author Antonio J. Galán Herrera
 * @author Github Copilot
 */
public class MochilaAV extends Mochila {

	// Resuelve el problema de la mochila utilizando un algoritmo voraz.
	// Recibe:		un problema ProblemaMochila con ítems y capacidad de la mochila.
	// Devuelve: 	una solución SolucionMochila con los ítems de la mochila.
    public SolucionMochila resolver(ProblemaMochila problema) {
        ArrayList<Integer> solucion = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>(problema.getItems());
        int capacidad = problema.getPesoMaximo();
        int peso = 0;
        int valor = 0;

        // Se recorren los ítems de mayor a menor valor/peso.
        for (Item item : items) {
            // Si el ítem cabe en la mochila, se añade.
            if (item.peso + peso <= capacidad) {
                solucion.add(item.index);
                peso += item.peso;
                valor += item.valor;
            }
        }

        return new SolucionMochila(solucion, valor, peso);
    }
}
