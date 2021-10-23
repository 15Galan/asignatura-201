// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// Práctica 1: Complejidad experiemntal

import java.util.*;
import java.util.Map.Entry;


/**
 * Obtiene el orden de complejidad de un algoritmo.
 *
 * @author 	Antonio J. Galán Herrera
 * @see 	Algoritmo#f(long)
 */
public class Analizador {

	// Valores pequeños para complejidades altas y valores altos para complejidades bajas-medias
	private static final long[] n = {1,2,3,4,5,6,7,8,9,10,12,14,16,18,20,22,24,26,28,30,40,50,60,70,80,90,
								100,300,500,700,1000,3000,5000,7000,10000,30000,50000,70000,
								100000,200000,300000,500000,700000,800000,850000,900000,950000,
								1000000,1500000,2000000,2500000,3000000,3500000,5000000,7000000,
								10000000,30000000,50000000,800000000,90000000,
								100000000,500000000,999999999};
	private static final double TIEMPO_MAX = 10 * Math.pow(10, 9);	// Tiempo máximo de ejecución (nano-segundos)


	/**
	 * Ejecuta el programa principal del proyecto.
	 *
	 * @param args	Array de argumentos
	 */
	public static void main(String[] args) {
		Map<String, Datos> tabla = new TreeMap<>();		// Mapa que relaciona cada complejidad con unos tiempos
		List<Double> tiempos;							// Lista de los tiempos medios de ejecución
		int precision = 500;							// Número de repeticiones para cada entrada

		tiempos = calcularTiempos(precision, TIEMPO_MAX/2);

		guardarTiempos(tiempos, tabla);

		System.out.println(determinarComplejidad(tabla));
	}


	/**
	 * Ejecuta el algoritmo para los valores de entrada, realizando la media de varias ejecuciones de cada valor 'n'.
	 *
	 * @param repeticiones	Veces que el algoritmo se ejecutará para cada entrada
	 * @param limite		Duración aproximada de este método (nano-segundos); debe ser bastante menor que TIEMPO_MAX
	 *
	 * @return	Una lista de medias de tiempos de ejecución para cada valor de entrada del algoritmo
	 */
	private static List<Double> calcularTiempos(int repeticiones, double limite) {
		Temporizador acumulado = new Temporizador(2);		// Mide el tiempo de ejecución de este método
		List<Double> tiempos = new ArrayList<>();				// Tiempos calculados
		boolean continuar = true;								// Indicador para finalizar la ejecución de este método

		acumulado.iniciar();

		int i = 0;
		while (i < n.length && continuar) {
			double duracion = 0;

			int j = 0;

			// Calcula el tiempo de ejecución de una entrada del algoritmo
			while (j < repeticiones && continuar) {
				duracion += ejecutar(n[i]);

				if (acumulado.tiempoPasado() > limite) {
					continuar = false;
				}

				j++;
			}

			// Si aún queda tiempo de ejecución disponible, se añade el valor calculado a la lista
			if (continuar) {
				tiempos.add(duracion/repeticiones);		// Media para varios tiempos de una misma entrada
			}

			i++;
		}

		return tiempos;
	}

	/**
	 * Ejecuta el algoritmo para un determinado valor de entrada.
	 *
	 * @param n		Entrada del algoritmo
	 *
	 * @return	Tiempo que tardó el algoritmo en finalizar la ejecución
	 */
	private static long ejecutar(long n) {
		Temporizador temporizador = new Temporizador(2);	// Temporizador en nano-segundos

		temporizador.iniciar();
		Algoritmo.f(n);
		temporizador.parar();

		return temporizador.tiempoPasado();
	}

	/**
	 * Asigna a cada orden de complejidad un array de tiempos que lo identifica.
	 *
	 * @param tiempos	Valores temporales base para producir los valores temporales de las complejidades
	 * @param tabla		Tabla donde guardar el identificador del orden de complejidad y sus valores temporales
	 */
	private static void guardarTiempos(List<Double> tiempos, Map<String, Datos> tabla) {
		for (String complejidad : new String[]{"1", "NLOGN", "N", "LOGN", "N2", "N3", "2N", "NF"}) {
			tabla.put(complejidad, new Datos(adaptarMedias(complejidad, tiempos)));
		}
	}

	/**
	 * Dada una lista de tiempos, divide cada componente entre la función de su
	 * complejidad: O(1), O(log(n)), O(n), O(n·log(n))... y la almacena en una lista
	 *
	 * @param complejidad	Complejidad para la que se quiere adaptar las medias
	 * @param medias 		Lista de tiempos medios de ejecución de un algoritmo en nano-segundos
	 *
	 * @return	La lista inicial adaptada a la complejidad indicada
	 */
	private static List<Double> adaptarMedias(String complejidad, List<Double> medias) {
		List<Double> arregladas = new ArrayList<>();

		try {
			switch (complejidad) {
				case "1":
					arregladas.addAll(medias);

					break;

				case "LOGN":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / Math.log(n[i]));			// Puede dar infinito, pero no afecta
					}

					break;

				case "N":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / n[i]);
					}

					break;

				case "NLOGN":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / n[i] * Math.log(n[i]));	// Puede dar infinito, pero no afecta
					}

					break;

				case "N2":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / Math.pow((n[i]), 2));	// Puede dar NaN, pero no afecta
					}

					break;

				case "N3":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / Math.pow(n[i], 3));		// Puede dar NaN, pero no afecta
					}

					break;

				case "2N":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / Math.pow(2, n[i]));		// Puede dar NaN, pero no afecta
					}

					break;

				case "NF":
					for (int i = 0; i < medias.size(); i++) {
						arregladas.add(medias.get(i) / factorial(n[i]));		// Puede desbordar la memoria
					}
			}

		} catch (StackOverflowError e) {	// Captura el posible error para el caso NF
			arregladas = medias;
		}

		return arregladas;
	}

	/**
	 * Dado un mapa cuyas claves son los órdenes de complejidad, y sus valores asociados
	 * son objetos de la clase Datos (que contiene sus tiempos medios adaptados y su ratio),
	 * comprueba cuál de las complejidades tiende a un valor constante.
	 *
	 * @param complejidades		Mapa con todos los datos calculados para cada complejidad
	 *
	 * @return	La complejidad del mapa con el ratio más cercano a 1 (tendencia a constante)
	 */
	private static String determinarComplejidad(Map<String, Datos> complejidades) {
        String complejidad = "-";	// Resultado final
		double mejor = 100;			// Diferencia entre el ratio y 1

		Set<Entry<String, Datos>> entradas = complejidades.entrySet();

        for (Entry<String, Datos> entrada : entradas) {
        	double diferencia = Math.abs(entrada.getValue().ratio - 1);

        	// El mejor ratio es el más próximo a 1 (por encima o por debajo)
        	if (diferencia < mejor) {
        		mejor = diferencia;
        		complejidad = entrada.getKey();
			}
		}

        return complejidad;
	}


	/**
	 * Calcula el factorial de un número.
	 *
	 * @param n		Número
	 *
	 * @return 	El factorial del número indicado
	 */
	private static long factorial(long n) {
		if (n == 0) {
			return 1;

		} else {
			return n * factorial(n-1);
		}
	}
}
