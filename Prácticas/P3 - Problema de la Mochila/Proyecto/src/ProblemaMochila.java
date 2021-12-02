
import java.util.ArrayList;

public class ProblemaMochila {
	
	protected final static int SIN_CALCULAR = Integer.MIN_VALUE;

	protected ArrayList<Item> items = null;
	protected int pesoMaximo = SIN_CALCULAR;
	
	/**
	 * Construye un problema de la mochila
	 * @param pesos
	 * @param valores
	 * @param pesoMaximo
	 */
	private void init(int[] pesos, int[] valores,int[] unidades, int pesoMaximo) {
		if (pesos!=null && valores!=null && pesos.length == valores.length && pesoMaximo >= 0) {
			this.pesoMaximo = pesoMaximo;
			items = new ArrayList<Item>(pesos.length);
			for (int i=0; i<pesos.length; i++) {
				Item item = new Item(i, pesos[i], valores[i], unidades[i]);
				items.add(item);
			}
		}
	}
	

	/**
	 * Construye un problema de la mochila
	 * @param pesos
	 * @param valores
	 * @param pesoMaximo
	 */
	public ProblemaMochila(int[] pesos, int[] valores, int[] unidades, int pesoMaximo) {
		init(pesos, valores,unidades, pesoMaximo);
	}

	/**
	 *  Construye un problema aleatorio 
	 * @param numero de items del problema
	 * @param relacion entre el pesoMaximo y el pesoTotal de los items (0 < relacion < 1)
	 */
	public ProblemaMochila(int size, double relacion) {
		int[] pesos = ArrayUtils.generaArray(size, 1, size);
		int[] valores = ArrayUtils.generaArray(size, 1, size);
		int[] unidades = ArrayUtils.generaArray(size, 1, size);
		int sumaTotalPesos = 0;
		for(int i=0; i<pesos.length; i++) {
			sumaTotalPesos += (unidades[i]*pesos[i]);
		}
		pesoMaximo = (int) (sumaTotalPesos * relacion);
		init(pesos, valores,unidades,pesoMaximo);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Item> getItems() {
		return ((ArrayList<Item>) items.clone());
	}

	public int getPesoMaximo() {
		return pesoMaximo;
	}

	/**
	 * Devuelve el peso i-esimo
	 * @param i
	 * @return
	 */
	public int getPeso(int i) {
		return items.get(i).peso;
	}
	
	/**
	 * Devuelve el valor i-esimo
	 * @param i
	 * @return
	 */
	public int getValor(int i) {
		return items.get(i).valor;
	}
	/**
	 * Devuelve el numero de objetos de tipo i-esimo
	 * @param i
	 * @return
	 */
	
	public int getUnidad(int i) {
		return items.get(i).unidades;
	}
	/**
	 * Devuelve el vector de pesos completo
	 * @return
	 */
	public int[] getPesos() {
		int[] pesos = null;
		if (items!=null) {
			pesos = new int[items.size()];
			for(int i=0; i<items.size(); i++) {
				pesos[i] = getPeso(i);
			}
		}
		return pesos;
	}
	
	/**
	 * Devuelve el vector de valores completo
	 * @return
	 */
	public int[] getValores() {
		int[] valores = null;
		if (items!=null) {
			valores = new int[items.size()];
			for(int i=0; i<items.size(); i++) {
				valores[i] = getValor(i);
			}
		}
		return valores;
	}
	
	/**
	 * Devuelve el vector de unidades completo
	 * @return
	 */
	public int[] getUnidades() {
		int[] unidades = null;
		if (items!=null) {
			unidades = new int[items.size()];
			for(int i=0; i<items.size(); i++) {
				unidades[i] = getUnidad(i);
			}
		}
		return unidades;
	}
	
	
	
	/**
	 * @param solucion, un array de la forma (x1,x2,...xk) donde k <= n
	 * @return Devuelve la suma de pesos correspondiente a una solucion total o parcial al problema.
	 */
	public int sumaPesos(int[] solucion) {
		int sumaPesos = 0;
		if (solucion!=null && items!=null && solucion.length <= items.size()) {
			for(int i=0; i<solucion.length; i++) {
				sumaPesos += (solucion[i] * getPeso(i));
			}
		}
		return sumaPesos;
	}
	
	/**
	 * @param solucion, un array de la forma (x1,x2,...xk) donde k <= n
	 * @return Devuelve la suma de valores correspondiente a una solucion total o parcial al problema.
	 */
	public int sumaValores(int[] solucion) {
		int sumaValores = 0;
		if (solucion!=null && items!=null && solucion.length <= items.size()) {
			for(int i=0; i<solucion.length; i++) {
				sumaValores += (solucion[i] * getValor(i));
			}
		}
		return sumaValores;
	}
	
	/**
	 * Devuelve el numero de items del problema.
	 */
	public int size() {
		int size = 0;
		if (items!=null) {
			size = items.size();
		}
		return size;
	}
	
    /**
     * Salida normalizada
     */
	public String toString() {
		String str = "";
		if ( size()>0 && pesoMaximo > 0) {
			str += "PESOS      : "+ ArrayUtils.toString(getPesos()) + "\n";
			str += "VALORES    : "+ ArrayUtils.toString(getValores()) + "\n";
			str += "UNIDADES   : "+ ArrayUtils.toString(getUnidades()) + "\n";
			str += "MAXIMO     : "+ pesoMaximo;
		}
		return str;
	}


	public boolean equivalente(SolucionMochila sm1, SolucionMochila sm2) {
		boolean equivalente = false;
		if (sm1.getSolucion() !=null && sm2.getSolucion()!=null && sm1.sumaValores == sm2.sumaValores) { // comprueba si los resultados son iguales
			int[] solucion1 = ArrayUtils.toInt(sm1.getSolucion());
			int[] solucion2 = ArrayUtils.toInt(sm2.getSolucion());
			int sumaValores1 = sumaValores(solucion1);
			int sumaValores2 = sumaValores(solucion2);
			//int sumaPesos1 = sumaPesos(solucion1);
			//int sumaPesos2 = sumaPesos(solucion2);
			if (sumaValores1 == sumaValores2) { // Comprueba si las soluciones suman realmente lo mismo
				equivalente = true;
			}
		}
		return equivalente;
	}




}