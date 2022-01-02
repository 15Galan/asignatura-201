package Ejemplos.N_Reinas;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Clase que representa el estado de un tablero de ajedrez del problema de las N-Reinas.
 * TODO - Corregir la resolución del problema.
 *
 * @author Antonio J. Galán Herrera, Github Copilot
 */
public class Problema implements Cloneable {

	// Datos del estado del tablero
	protected static int FILAS		= 5;	// Número de filas del tablero (por defecto, 5)
	protected static int COLUMNAS	= 5;	// Número de columnas del tablero (por defecto, 5)

	// Contenido del tablero
	protected int[][] tablero;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea un tablero con todas sus celdas vacías (su valor es 0).
	 */
	public Problema() {
		tablero = new int[FILAS][COLUMNAS];
	}

	/**
	 * Crea un tablero con todas sus celdas vacías (su valor es 0).
	 */
	public Problema(int filas, int columnas) {
		FILAS = filas;
		COLUMNAS = columnas;

		tablero = new int[FILAS][COLUMNAS];
	}

	/**
	 * Constructor que crea un tablero haciendo una copia de otro.
	 *
	 * @param tablero	Tablero a copiar
	 */
	public Problema(Problema tablero) {
		Problema copia = (Problema) tablero.clone();
		this.tablero = copia.tablero;
	}

	/**
	 * Crea un tablero a partir de una configuración inicial.
	 *
	 * @param configuracion		Cadena de caracteres que representa el estado de un tablero
	 */
    public Problema(String configuracion) {
		this();

		double longitud = Math.sqrt(configuracion.length());

		if(longitud == Math.floor(longitud)) {
			FILAS 		= (int) longitud;
			COLUMNAS 	= (int) longitud;

			tablero = new int[FILAS][COLUMNAS];

			for(int f = 0; f < FILAS; f++) {
				for (int c = 0; c < COLUMNAS; c++) {
					if (configuracion.charAt(f*FILAS + c) == '.') {
						tablero[f][c] = 0;

					} else {
						tablero[f][c] = 1;
					}
				}
			}

    	} else {
			throw new RuntimeException("Construcción no válida.");
		}
    }


	// -----------------------------------------------------------------
	// Algoritmo de vuelta atrás
	// -----------------------------------------------------------------

	/**
	 * Resuelve el problema de las N-Reinas.
	 *
	 * @return Una lista con todas las soluciones posibles
	 */
	public LinkedList<Problema> resolverTodos() {
		LinkedList<Problema> soluciones = new LinkedList<>();
		resolverTodos(soluciones, 0, 0);

		return soluciones;
	}

	/**
	 * Resuelve el problema de las N-Reinas.
	 *
	 * @param soluciones 	Lista donde se guardarán las soluciones
	 * @param fila 			Fila de la iteración actual
	 * @param columna 		Columna de la iteración actual
	 */
	private void resolverTodos(LinkedList<Problema> soluciones, int fila, int columna) {


		if (esSolucion(fila)) {
			soluciones.add(new Problema(this));

		} else {
			// No se ha encontrado una solución.
			int sigFil	= fila;
			int sigCol 	= (columna + 1) % COLUMNAS;

			// Se comprueba si se ha llegado al final de la fila.
			if (sigCol == 0) {
				sigFil++;
			}

			if (!estaLibre(fila, columna)) {
				resolverTodos(soluciones, sigFil, sigCol);

			} else {
				// Se intenta colocar una reina en la casilla actual
				if (sePuedePonerEn(fila, columna)) {
					tablero[fila][columna] = 1;					// Se añade la reina
					resolverTodos(soluciones, sigFil, sigCol);	// Se continúa con la siguiente casilla
					tablero[fila][columna] = 0;				    // Se elimina la reina (siguiente solución)
				}

				if (sigFil < FILAS) {
					resolverTodos(soluciones, sigFil, sigCol);        // Se continúa con la siguiente casilla
				}
			}
		}
	}


	// -----------------------------------------------------------------
	// Métodos auxiliares
	// -----------------------------------------------------------------

	/**
	 * Indica si el tablero es una solución.
	 *
	 * @param fila 	Fila de la iteración actual
	 *
	 * @return TRUE si el tablero es una solución, FALSE en caso contrario
	 */
	private boolean esSolucion(int fila) {
		for (int f = 0; f < FILAS; f++) {
			for (int c = 0; c < COLUMNAS; c++) {
				if (sePuedePonerEn(f, c)) {
					return false;
				}
			}
		}

		return fila == FILAS;
	}

	/**
	 * Comprueba si es posible colocar un valor en una celda.
	 * Es decir, si el valor no está en la fila, la columna ni el subtablero.
	 *
	 * @param fila 		Fila de la celda a comprobar
	 * @param columna 	Columna de la celda a comprobar
	 *
	 * @return TRUE si se puede colocar el valor en la celda; FALSE en caso contrario.
	 */
	private boolean sePuedePonerEn(int fila, int columna) {
		return estaLibre(fila, columna)
				&& !(comidaEnFila(fila)
				||   comidaEnColuma(columna)
				||   comidaEnDiagonal(fila, columna));
	}

	/**
	 * Indica si una celda está libre.
	 *
	 * @param fila		Fila de la celda a comprobar
	 * @param columna	Columna de la celda a comprobar
	 *
	 * @return TRUE si la celda está libre; FALSE en caso contrario
	 */
	private boolean estaLibre(int fila, int columna) {
		return tablero[fila][columna] == 0;
	}

	/**
	 * Comprueba si un valor ya está en una fila.
	 *
	 * @param fila		Fila a comprobar
	 *
	 * @return TRUE si el valor ya está en la fila; FALSE en caso contrario
	 */
	private boolean comidaEnFila(int fila) {
		// Recorre toda la fila buscando el valor
		for (int c = 0; c < COLUMNAS; c++) {
			if (tablero[fila][c] == 1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Comprueba si un valor ya está en una columna.
	 *
	 * @param columna 	Columna a comprobar
	 *
	 * @return TRUE si el valor ya está en la columna; FALSE en caso contrario
	 */
	private boolean comidaEnColuma(int columna) {
		// Recorre toda la columna buscando el valor
		for (int f = 0; f < FILAS; f++) {
			if (tablero[f][columna] == 1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Indica si ya hay una reina en cualquier casilla en diagonal a la indicada.
	 *
	 * @param fila		Fila de la celda a comprobar
	 * @param columna	Columna de la celda a comprobar
	 *
	 * @return TRUE si ya hay una reina en la diagonal; FALSE en caso contrario
	 */
	private boolean comidaEnDiagonal(int fila, int columna) {
		int f = fila, c = columna;
		boolean comida = false;

		// Diagonal sup-izq
		while (0 <= f && 0 <= c && !comida) {
			if (tablero[f][c] == 1) {
				comida = true;

			} else {
				f--;
				c--;
			}
		}

		f = fila;
		c = columna;

		// Diagonal sup-der
		while (0 <= f && c < COLUMNAS && !comida) {
			if (tablero[f][c] == 1) {
				comida = true;

			} else {
				f--;
				c++;
			}
		}

		f = fila;
		c = columna;

		// Diagonal inf-izq
		while (f < FILAS && 0 <= c && !comida) {
			if (tablero[f][c] == 1) {
				comida = true;

			} else {
				f++;
				c--;
			}
		}

		f = fila;
		c = columna;

		// Diagonal inf-der
		while (f < FILAS && c < COLUMNAS && !comida) {
			if (tablero[f][c] == 1) {
				comida = true;

			} else {
				f++;
				c++;
			}
		}

		return comida;
	}

	/**
	 * Genera una representación del estado del tablero de forma más visual.
	 *
	 * @return	Un tablero en forma de filas y columnas
	 */
	public String tablero() {
		return tablero(-1, -1, "");
	}

	/**
	 * Genera una representación del estado del tablero de forma más visual.
	 * Permite colocar un caracter manualmente en las coordenadas descritas.
	 *
	 * @param fila		Fila de la marca
	 * @param columna	Columna de la marca
	 * @param marca		Caracter especial
	 *
	 * @return	Un tablero en forma de filas y columnas
	 */
	public String tablero(int fila, int columna, String marca) {
		StringBuilder tabla = new StringBuilder();

		// Recorrer el tablero
		for (int f = 0; f < FILAS; f++) {
			for (int c = 0; c < COLUMNAS; c++) {

				// Guardar el contenido de una casilla
				if (f == fila && c == columna) {
					tabla.append(marca);	// Casilla marcada por el usuario

				} else if (tablero[f][c] == 1) {
					tabla.append("R");		// Casilla con una reina

				} else {
					tabla.append("·");		// Casilla vacía
				}

				// Espacio entre columnas
				tabla.append(" ");
			}

			// Salto de línea entre filas
			tabla.append("\n");
		}

		return tabla.toString();
	}


	// -----------------------------------------------------------------
	// Métodos sobrescritos
	// -----------------------------------------------------------------

	/**
	 * Realiza una copia en profundidad del objeto.
	 * @see Object#clone()
	 *
	 * @return	Una copia del objeto
	 */
	@Override
	public Object clone()  {
		Problema clon;

		try {
			clon = (Problema) super.clone();
			clon.tablero = new int[FILAS][COLUMNAS];

			for(int i = 0; i < tablero.length; i++) {
				System.arraycopy(tablero[i], 0, clon.tablero[i], 0, tablero[i].length);
			}

		} catch (CloneNotSupportedException e) {
			clon = null;
		}

		return clon;
	}

	/**
	 * Establece las condiciones de igualdad para 2 instancias de esta clase.
	 * @see Object#equals(Object)
	 *
	 * @return TRUE si la instancia recibida es igual; FALSE en caso contrario
	 */
	@Override
	public boolean equals(Object objeto) {
		// Comprobar que el objeto recibido es un TableroSudoku
		if (objeto instanceof Problema otro) {

			// Comprobar que las filas de ambos tableros son iguales
			for(int f = 0; f < FILAS; f++) {
				if (!Arrays.equals(this.tablero[f], otro.tablero[f])) {
					return false;
				}
			}

			return true;

		} else {
			return false;
		}
	}

	/**
	 * Genera una representación del estado del tablero.
	 * El resultado es una configuración del tablero.
	 *
	 * @return	Representación del estado del tablero
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		for (int f = 0; f < FILAS; f++) {
			for (int c = 0; c < COLUMNAS; c++) {
				s.append(tablero[f][c] == 0 ? "." : String.format("%d", tablero[f][c]));
			}
		}

		return s.toString();
	}
}
