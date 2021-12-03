/**
 * Clase que representa una resolución de un problema de la mochila, mediante programación dinámica.
 *
 * @author Antonio J. Galán Herrera
 * @author Github Copilot
 */
public class MochilaPD extends Mochila {

	private int capacidad;
	private int[] pesos;
	private int[] valores;

	/**
	 * Resolver el problema de la mochila usando programación dinámica.
	 *
	 * @param problema		Problema de la mochila
	 * @return				Solución del problema
	 */
	public SolucionMochila resolver(ProblemaMochila problema) {
		// Datos del problema
		capacidad	= problema.getPesoMaximo();
		pesos 		= problema.getPesos();
		valores 	= problema.getValores();

		// Datos para la tabla
		int filas 		= problema.getPesos().length + 1;
		int columnas 	= problema.getPesoMaximo() + 1;
		int[][] tabla 	= new int[filas][columnas];

		// Generar tabla
		inicializarTabla(tabla, filas, columnas);
		rellenarTabla(tabla, filas, columnas);

		// Generar vector de la solución
		int[] combinacion = encontrarCombinacion(tabla, filas, columnas);

		// Mostrar tabla (debug)
		// mostrarTabla(tabla, problema.getPesos(), problema.getPesoMaximo());

		return generarSolucion(tabla, combinacion);
	}

	/**
	 * Rellena la primera fila y la primera columna de una tabla con 0.
	 *
	 * @param tabla		Tabla a rellenar
	 * @param filas		Número de filas de la tabla
	 * @param columnas	Número de columnas de la tabla
	 */
	private void inicializarTabla(int[][] tabla, int filas, int columnas) {
		// Inicializar la primera fila a 0
		for (int i = 0; i < filas; i++) {
			tabla[i][0] = 0;
		}

		// Inicializar la primera columna a 0
		for (int i = 0; i < columnas; i++) {
			tabla[0][i] = 0;
		}
	}

	/**
	 * Rellena una tabla con los valores de la solución.
	 *
	 * @param tabla		Tabla a rellenar
	 * @param filas		Número de filas de la tabla
	 * @param columnas	Número de columnas de la tabla
	 */
	private void rellenarTabla(int[][] tabla, int filas, int columnas) {
		for (int i = 1; i < filas; i++) {
			for (int j = 1; j < columnas; j++) {
				tabla[i][j] = elegirValor(tabla, i, j);
			}
		}
	}

	/**
	 * Coloca un valor en una posición de la tabla.
	 *
	 * @param tabla		Tabla a rellenar
	 * @param f			Fila donde colocar el valor
	 * @param c			Columna don colocar el valor
	 * @return			Valor colocado en la posición
	 */
	private int elegirValor(int[][] tabla, int f, int c) {
		int valor;

		if (pesos[f-1] <= c) {
			valor = Math.max(tabla[f-1][c], tabla[f-1][c - pesos[f-1]] + valores[f-1]);

		} else {
			valor = tabla[f-1][c];
		}

		return valor;
	}

	/**
	 * Genera la combinación de items a elegir.
	 *
	 * @param tabla		Tabla
	 * @param filas		Número de filas
	 * @return			Vector con la combinación de ítems
	 */
	private int[] encontrarCombinacion(int[][] tabla, int filas, int columnas) {
		int[] res = new int[valores.length];

		// Contadores
		int f = filas-1;		// Empieza en la última fila de la tabla
		int c = columnas-1;		// Empieza en la última columna de la tabla

		while (0 < f) {
			if (tabla[f][c] != tabla[f-1][c]) {
				res[f-1] = 1;
				c -= pesos[f-1];
			}

			f--;
		}

		return res;
	}

	/**
	 * Calcula la solución a partir de una tabla.
	 *
	 * @param tabla		Tabla de la solución
	 * @param res		Vector de items a elegir
	 * @return			Solución asociada a un problema de la mochila
	 */
	private SolucionMochila generarSolucion(int[][] tabla, int[] res) {
		int pesoTotal = 0;

		// Calcular el peso de la solución
		for (int i = 0; i < res.length; i++) {
			if (res[i] == 1) {
				pesoTotal += valores[i];
			}
		}

		// Generar una solución con la combinación, el peso y el valor de la última fila y columna de la tabla
		return new SolucionMochila(ArrayUtils.toArray(res), pesoTotal, tabla[valores.length][capacidad]);
	}


	/**
	 * Mostrar por pantalla la tabla generada dinámicamente con las cabeceras.
	 *
	 * @param tabla			Tabla
	 * @param pesos			Cabecera de las filas
	 * @param capacidad		Valor máximo de la cabecera de las columnas
	 */
	public void mostrarTabla(int[][] tabla, int[] pesos, int capacidad) {
		// Imprimir una línea divisoria tan larga como la tabla
		System.out.print("\n--------------------------------------------------------\n");

		System.out.print("\t");

		for (int i = 0; i <= capacidad; i++) {
			System.out.print(i + "\t");
		}

		System.out.println();

		for (int i = 0; i < tabla.length-1; i++) {
			System.out.print(pesos[i] + "\t");

			for (int j = 0; j < tabla[i].length; j++) {
				System.out.print(tabla[i][j] + "\t");
			}

			// Mostrar separador final junto a la tabla
			if (i == tabla.length - 2) {
				System.out.print("\n--------------------------------------------------------\n");

			} else {
				System.out.println();
			}
		}
	}
}
