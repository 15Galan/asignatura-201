import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa una resolución de un problema de la mochila, mediante fuerza bruta.
 *
 * @author Antonio J. Galán Herrera
 * @author Github Copilot
 */
public class MochilaFB extends Mochila {

	/**
	 * Resuelve un problema de la mochila usando un algoritmo de fuerza bruta.
	 *
	 * @param problema	Problema de la mochila
	 * @return 			Solucion del problema
	 */
	public SolucionMochila resolver(ProblemaMochila problema) {
		// Datos del problema
		int[] pesos 	= problema.getPesos();
		int[] valores 	= problema.getValores();
		int[] unidades 	= problema.getUnidades();

		// Calcula todas las combinaciones de pesos de los items posibles y genera sus soluciones asociadas
		SolucionMochila[] soluciones = generarSoluciones(combinaciones(unidades), pesos, valores);

		return mejorSolucion(soluciones, problema.pesoMaximo);
	}

	// -----------------------------------------------------------------

	/**
	 * Calcula todas las combinaciones posibles de los pesos de los items de un problema de la mochila.
	 *
	 * @param pesos		Pesos de los items del problema
	 * @return 			Lista de combinaciones posibles
	 */
	private static List<int[]> combinaciones(int[] pesos) {
		List<int[]> resultado = new ArrayList<>();

		combinaciones(pesos, 0, new int[pesos.length], resultado);

		return resultado;
	}

	/**
	 * Calcula todas las combinaciones posibles de los pesos de los items de un problema de la mochila.
	 *
	 * @param pesos       	Pesos de los items del problema
	 * @param pos         	Indice del item actual
	 * @param combinacion 	Combinación actual
	 * @param resultado   	Lista de combinaciones posibles
	 */
	private static void combinaciones(int[] pesos, int pos, int[] combinacion, List<int[]> resultado) {
		if (pos == pesos.length) {
			resultado.add(combinacion.clone());

		} else {
			for (int i = 0; i <= pesos[pos]; i++) {
				combinacion[pos] = i;
				combinaciones(pesos, pos+1, combinacion, resultado);
			}
		}
	}

	/**
	 * Genera una lista de soluciones asociadas a una combinación de pesos.
	 *
	 * @param unidades		Unidades de cada item
	 * @param pesos			Pesos de cada item
	 * @param valores		Valores de cada item
	 * @return				Lista de soluciones asociadas a una combinación de pesos
	 */
	private static SolucionMochila[] generarSoluciones(List<int[]> unidades, int[] pesos, int[] valores) {
		SolucionMochila[] soluciones = new SolucionMochila[unidades.size()];

		for (int i = 0; i < unidades.size(); i++) {
			int pesoTotal = 0, valorTotal = 0;

			int[] combinacion = unidades.get(i);

			// Calcula el resto de atributos de la solución
			for (int j = 0; j < combinacion.length; j++) {
				pesoTotal  += pesos[j] * combinacion[j];
				valorTotal += valores[j] * combinacion[j];
			}

			soluciones[i] = new SolucionMochila(unidades.get(i), pesoTotal, valorTotal);	// No acepta 'int[]
		}

		return soluciones;
	}

	/**
	 * Calcula la mejor solución de un conjunto de soluciones.
	 *
	 * @param soluciones	Conjunto de soluciones
	 * @param pesoMaximo	Peso máximo de la mochila
	 * @return 				Mejor solución
	 */
	private static SolucionMochila mejorSolucion(SolucionMochila[] soluciones, int pesoMaximo) {
		SolucionMochila mejorSolucion = soluciones[0];

		for (SolucionMochila solucion : soluciones) {
			// El valor es máximo y el peso no supera la capacidad de la mochila
			if (solucion.getSumaPesos() <= pesoMaximo && solucion.getSumaValores() > mejorSolucion.getSumaValores()) {
				mejorSolucion = solucion;
			}
		}

		return mejorSolucion;
	}
}
