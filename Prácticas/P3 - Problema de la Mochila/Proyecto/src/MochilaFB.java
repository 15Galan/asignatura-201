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

		// Calcular las combinaciones de unidades de los ítems y sus soluciones asociadas
		SolucionMochila[] soluciones = generarSoluciones(combinaciones(unidades), pesos, valores);

		return mejorSolucion(soluciones, problema.pesoMaximo);
	}

	// -----------------------------------------------------------------

	/**
	 * Calcula todas las combinaciones posibles de las unidades de los ítems de un problema de la mochila.
	 *
	 * @param unidades		Cantidades de cada ítem del problema
	 * @return 				Lista de combinaciones posibles
	 */
	private static List<int[]> combinaciones(int[] unidades) {
		List<int[]> resultado = new ArrayList<>();

		combinaciones(unidades, 0, new int[unidades.length], resultado);

		return resultado;
	}

	/**
	 * Calcula todas las combinaciones posibles de las unidades de los ítems de un problema de la mochila.
	 *
	 * @param unidades  	Cantidades de cada ítem del problema
	 * @param pos         	Indice del ítem actual
	 * @param combinacion 	Combinación de unidades actual
	 * @param resultado   	Lista de combinaciones posibles
	 */
	private static void combinaciones(int[] unidades, int pos, int[] combinacion, List<int[]> resultado) {
		// Caso base: se ha llegado al final de la lista de ítems
		if (pos == unidades.length) {
			resultado.add(combinacion.clone());

		// Caso intermedio: se calcula una combinación de unidades para el ítem actual
		} else {
			for (int i = 0; i <= unidades[pos]; i++) {
				combinacion[pos] = i;
				combinaciones(unidades, pos+1, combinacion, resultado);
			}
		}
	}

	/**
	 * Genera una lista de soluciones asociadas a una combinación de unidades.
	 *
	 * @param combinaciones		Combinaciones de ítems
	 * @param pesos				Peso de cada ítem
	 * @param valores			Valor de cada ítem
	 * @return					Lista de soluciones asociadas a una combinación de pesos
	 */
	private static SolucionMochila[] generarSoluciones(List<int[]> combinaciones, int[] pesos, int[] valores) {
		SolucionMochila[] soluciones = new SolucionMochila[combinaciones.size()];

		for (int i = 0; i < combinaciones.size(); i++) {
			// Datos para la solución actual
			int[] combinacion = combinaciones.get(i);
			int pesoTotal = 0, valorTotal = 0;

			// Calcular el resto de atributos de la solución
			for (int j = 0; j < combinacion.length; j++) {
				pesoTotal  += pesos[j] * combinacion[j];
				valorTotal += valores[j] * combinacion[j];
			}

			// Generar una solución asociada a la combinación de unidades actual
			soluciones[i] = new SolucionMochila(combinacion, pesoTotal, valorTotal);
		}

		return soluciones;
	}

	/**
	 * Calcula la mejor solución de un conjunto de soluciones: aquella cuyo valor
	 * es máximo, y el peso de los ítems no supera la capacidad de la mochila.
	 *
	 * @param soluciones	Conjunto de soluciones
	 * @param capacidad		Peso máximo de la mochila
	 * @return 				Mochila de mayor valor, cuyo peso no supera la capacidad
	 */
	private static SolucionMochila mejorSolucion(SolucionMochila[] soluciones, int capacidad) {
		SolucionMochila mejorSolucion = soluciones[0];

		for (SolucionMochila solucion : soluciones) {
			// El valor de los ítems es máximo y el peso no supera la capacidad de la mochila
			if (solucion.getSumaPesos() <= capacidad && solucion.getSumaValores() > mejorSolucion.getSumaValores()) {
				mejorSolucion = solucion;
			}
		}

		return mejorSolucion;
	}
}
