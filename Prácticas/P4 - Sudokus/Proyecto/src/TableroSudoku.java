import java.util.*;


/**
 * Clase que representa el estado de un tablero de sudoku.
 *
 * @author Antonio J. Galán Herrera, Github Copilot
 */
public class TableroSudoku implements Cloneable {
	
	// Datos del estado del tablero
	protected static final int MAXVALOR = 9;
	protected static final int FILAS	= 9;
	protected static final int COLUMNAS	= 9;
							 
	protected static Random r = new Random();
	
	protected int[][] celdas;


	/**
	 * Ejecuta la resolución de un tablero de sudoku.
	 *
	 * @param args	Argumentos de clase recibidos
	 */
	public static void main(String[] args) {
		String configuracion = ".4....36263.941...5.7.3.....9.3751..3.48.....17..62...716.9..2...96.......312..9.";
		TableroSudoku sudoku = new TableroSudoku(configuracion);
		List<TableroSudoku> tableros = sudoku.resolverTodos();

		System.out.println(sudoku);
		System.out.println(tableros.size());

		for (TableroSudoku tablero : tableros) {
			System.out.println(tablero);
		}
	}


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea un tablero con todas sus celdas a 0.
	 */
	public TableroSudoku() {
		celdas = new int[FILAS][COLUMNAS];
	}

	/**
	 * Constructor que crea un tablero haciendo una copia de otro.
	 *
	 * @param tablero	Tablero a copiar
	 */
	public TableroSudoku(TableroSudoku tablero) {
		TableroSudoku otro = (TableroSudoku) tablero.clone();
		this.celdas = otro.celdas;
	}

	/**
	 * Crea un tablero a partir de una configuración inicial.
	 *
	 * @param configuracion		Cadena de caracteres que representa el estado de un tablero
	 */
    public TableroSudoku(String configuracion) {
    	this();

    	if(configuracion.length() == FILAS*COLUMNAS) {
			for(int f = 0; f < FILAS; f++) {
				for (int c = 0; c < COLUMNAS; c++) {
					char ch = configuracion.charAt(f*FILAS + c);
					celdas[f][c] = (Character.isDigit(ch) ? Integer.parseInt(Character.toString(ch)) : 0);
				}
			}

    	} else {
			throw new RuntimeException("Construcción de sudoku no válida.");
		}		
    }


	// -----------------------------------------------------------------
	// Métodos auxiliares
	// -----------------------------------------------------------------

	/**
	 * Resuelve un tablero de sudoku.
	 *
	 * @return	Lista con todos los tableros generados resueltos
	 */
	public List<TableroSudoku> resolverTodos() {
		List<TableroSudoku> soluciones  = new LinkedList<>();
		resolverTodos(soluciones, 0, 0);
		return soluciones;
	}

	/**
	 * Resuelve un tablero de sudoku.
	 *
	 * @param soluciones	Lista con todos los tableros generados resueltos
	 * @param fila			Fila de la celda a comprobar
	 * @param columna		Columna de la celda a comprobar
	 */
	protected void resolverTodos(List<TableroSudoku> soluciones, int fila, int columna) {
		// TODO
	}

	/**
	 * Indica si una celda está vacía.
	 *
	 * @param fila		Fila de la celda a comprobar
	 * @param columna	Columna de la celda a comprobar
	 *
	 * @return	TRUE si la celda está vacía; FALSE en caso contrario
	 */
	protected boolean estaLibre(int fila, int columna) {
		return celdas[fila][columna] == 0;
	}

	/**
	 * Cuenta el número de casillas libres en el tablero.
	 *
	 * @return	El número de casillas libres en el tablero.
	 */
	protected int numeroDeLibres() {
		int libres = 0;

		// Recorrer el tablero contando las celdas con 0.
	    for (int f = 0; f < FILAS; f++) {
			for (int c = 0; c < COLUMNAS; c++) {
				if (estaLibre(f, c)) {
					libres++;
				}
			}
		}

	    return libres;
	}

	/**
	 * Cuenta el número de casillas con un valor en el tablero.
	 *
	 * @return	El número de casillas con un valor en el tablero.
	 */
	protected int numeroDeFijos() {
		return FILAS*COLUMNAS - numeroDeLibres();
	}

	/**
	 * Comprueba si un valor ya está en una fila.
	 *
	 * @param fila		Fila a comprobar
	 * @param valor		Valor a comprobar
	 *
	 * @return TRUE si el valor ya está en la fila; FALSE en caso contrario.
	 */
	protected boolean estaEnFila(int fila, int valor) {
		// TODO
		return false;
	}

	/**
	 * Comprueba si un valor ya está en una columna.
	 *
	 * @param columna 	Columna a comprobar
	 * @param valor 	Valor a comprobar
	 *
	 * @return TRUE si el valor ya está en la columna; FALSE en caso contrario.
	 */
	protected boolean estaEnColumna(int columna, int valor) {
		// TODO
		return false;
	}

	/**
	 * Comprueba si un valor ya está en un subtablero.
	 *
	 * @param fila 		Fila de la celda a comprobar
	 * @param columna 	Columna de la celda a comprobar
	 * @param valor 	Valor a comprobar
	 *
	 * @return TRUE si el valor ya está en el subtablero; FALSE en caso contrario.
	 */
	protected boolean estaEnSubtablero(int fila, int columna, int valor) {
		// TODO
		return false;		
	}

	/**
	 * Comprueba si es posible colocar un valor en una celda.
	 * Es decir, si el valor no está en la fila, la columna o el subtablero.
	 *
	 * @param fila 		Fila de la celda a comprobar
	 * @param columna 	Columna de la celda a comprobar
	 * @param valor 	Valor a comprobar
	 *
	 * @return TRUE si se puede colocar el valor en la celda; FALSE en caso contrario.
	 */
	protected boolean sePuedePonerEn(int fila, int columna, int valor) {
		// TODO
		return false;
	}


	// -----------------------------------------------------------------
	// Métodos sobreescritos
	// -----------------------------------------------------------------

	/**
	 * Realiza una copia en profundidad del objeto.
	 * @see java.lang.Object#clone()
	 *
	 * @return	Una copia del objeto
	 */
	@Override
	public Object clone()  {
		TableroSudoku clon;

		try {
			clon = (TableroSudoku) super.clone();
			clon.celdas = new int[FILAS][COLUMNAS];

			for(int i = 0; i < celdas.length; i++) {
				System.arraycopy(celdas[i], 0, clon.celdas[i], 0, celdas[i].length);
			}

		} catch (CloneNotSupportedException e) {
			clon = null;
		}

		return clon;
	}

	/**
	 * Establece las condiciones de igualdad para 2 instancias de esta clase.
	 * @see java.lang.Object#equals(java.lang.Object)
	 *
	 * @return TRUE si la instancia recibida es igual; FALSE en caso contrario
	 */
	@Override
	public boolean equals(Object objeto) {
		// Comprobar que el objeto recibido es un TableroSudoku
		if (objeto instanceof TableroSudoku otro) {

			// Comprobar que las filas de ambos tableros son iguales
			for(int f = 0; f < FILAS; f++) {
				if (!Arrays.equals(this.celdas[f], otro.celdas[f])) {
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

		for(int f = 0; f < FILAS; f++) {
			for(int c = 0; c < COLUMNAS; c++) {
				s.append(celdas[f][c] == 0 ? "." : String.format("%d", celdas[f][c]));
			}
		}

		return s.toString();
	}
}
