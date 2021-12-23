
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class ArrayUtils {
	
	/**
	 * Convierte un int[] en un ArrayList<Integer>
	 */
	public static int[] toInt(ArrayList<Integer> arrayList) {
		int[] array = null;
		if (arrayList!=null) {
			array = new int[arrayList.size()];
			for(int i=0; i<arrayList.size(); i++) {
				array[i] = arrayList.get(i).intValue();
			}
		}
		return array;
	}
	/**
	 * Convierte un Integer[] en un ArrayList<Integer>
	 */
	private static int[] toInt(Integer[] arrayInteger) {
		int[] array = null;
		if (arrayInteger!=null) {
			array = new int[arrayInteger.length];
			for(int i=0; i<arrayInteger.length; i++) {
				if (arrayInteger[i]!=null) {
					array[i] = arrayInteger[i].intValue();
				}
			}
		}
		return array;
	}

	/**
	 * Convierte un ArrayList<Integer> en un int[]
	 */
	public static ArrayList<Integer> toArray(int[] array) {
		ArrayList<Integer> arrayList = null;
		if (array!=null) {
			arrayList = new ArrayList<Integer>(array.length);
			for(int i=0; i<array.length; i++) {
				arrayList.add(new Integer(array[i]));
			}
		}
		return arrayList;
	}

	/**
	 * Imprime en una linea el array de enteros separados por comas
	 */
	public static String toString(int[] array) {
		String str = "";
		if (array!=null && array.length>0) {
			str += array[0];
			for(int i=1; i<array.length; i++) {
				str += ", "+array[i];
			}
		}
		return str;
	}
	/**
	 * Imprime en una linea el array de enteros separados por comas
	 */
	public static String toString(Integer[] arrayInteger) {
		int[] array = toInt(arrayInteger);
		return toString(array);
	}
	public static String toString(ArrayList<Integer> arrayInteger) {
		int[] array = toInt(arrayInteger);
		return toString(array);
	}	


	/**
	 * Imprime en varias linea el array de enteros separados por comas
	 */
	public static String toString(int[][] array) {
		String str = "";
		if (array!=null) {
			for(int i=1; i<array.length; i++) {
				str += toString(array[i]) + "\n";
			}
		}
		return str;
	}
	
	/**
	 * Salida de una matriz por filas, con las columnas separadas por comas.
	 */
	public static String toString(Integer[][] array) {
		String str = "";
		if (array!=null) {
			for(int i=1; i<array.length; i++) {
				str += toString(array[i]) + "\n";
			}
		}
		return str;
	}

	/**
	 * Genera un array de longitud dim cuyos valores son enteros comprendidos en el intervalo (min,max)  
	 * @param dim, numero de elementos del array
	 * @param min, menor valor posible
	 * @param min, mayor valor posible
	 * @return, array de enteros
	 */
	public static int[] generaArray(int dim, int min, int max) {
		Random random = new Random();
		int rango = 1; 
		if (min<max) {
			rango = max-min;
		}
		int[] array = null;
		if (dim>0) {
			array = new int[dim];
			for(int i=0; i<dim; i++) {
				array[i] = min + random.nextInt(rango);
			}
		}
		return array;
	}


	/**
	 * Lee un vector de enteros
	 */
	protected static int[] leerArrayInt(String line) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(line).useDelimiter("[^0-9]+");
		while (sc.hasNextInt()) {
			arrayList.add(new Integer(sc.nextInt()));
		}
		return toInt(arrayList);
	}
	
	
	/**
	 * Comprueba que dos ArrayList<Integer> tengan los mismos valores
	 * @param arrayList1
	 * @param arrayList2
	 * @return
	 */
	public static boolean equals(ArrayList<Integer> arrayList1, ArrayList<Integer> arrayList2) {
		boolean resultado = false;
		if (arrayList1.size() == arrayList2.size()) {
			int[] array1 = toInt(arrayList1);
			int[] array2 = toInt(arrayList2);
			resultado = equals(array1, array2);
		}
		return resultado;
	}
	
	/**
	 * Comprueba que dos arrays de enteros tengan los mismos valores
	 * @param array1
	 * @param array2
	 * @return
	 */
	private static boolean equals(int[] array1, int[] array2) {
		boolean resultado = false;
		if (array1.length == array2.length) {
			resultado = true;
			for(int i=0; resultado && i<array1.length; i++) {
				resultado = resultado && array1[i] == array2[i];
			}
		}
		return resultado;
	}
	

}