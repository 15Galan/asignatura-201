import java.util.Random;

/**
 * Generadores aleatorios de vectores de enteros con distintas distribuciones
 *
 * @author Pepe Gallardo
 */

public class GeneradoresVectorEnteros {
	// Generador de números aleatorios
	private static final Random aleat = new Random();
	
	// Genera un vector con distribución aleatoria
	public static GeneradorVector<Integer> aleatorio = new GeneradorVector<Integer>() {
		public Integer [] nuevoVector(int tamano) {
			Integer[] v = new Integer[tamano];
			
			for(int i=0; i<tamano; i++)
				v[i] = aleat.nextInt(10*tamano);
			return v;		
		}
	};
	
	// Genera un vector aleatorio pero ordenado ascendentemente
	public static GeneradorVector<Integer> ordenadoAscendente = new GeneradorVector<Integer>() {
		public Integer [] nuevoVector(int tamano) {
			Integer[] v = new Integer[tamano];
			if(tamano>0)
				v[0] = aleat.nextInt(100);
			for(int i=1; i<tamano; i++)
				v[i] = v[i-1] + aleat.nextInt(10);
			return v;		
		}
	};

	// Genera un vector aleatorio pero ordenado descendentemente
	public static GeneradorVector<Integer> ordenadoDescendente = new GeneradorVector<Integer>() {
		public Integer [] nuevoVector(int tamano) {
			Integer v[] = new Integer[tamano];
			if(tamano>0)
				v[0] = aleat.nextInt(10*tamano);
			for(int i=1; i<tamano; i++)
				v[i] = v[i-1] - aleat.nextInt(10);
			return v;		
		}
	};	
	
	// Genera un vector aleatorio "casi" ordenado ascendentemente
	public static GeneradorVector<Integer> casiOrdenadoAscendente = new GeneradorVector<Integer>() {
		public Integer [] nuevoVector(int tamano) {
			Integer v[] = new Integer[tamano];

			for(int i=0; i<tamano; i++)
				v[i] = i + aleat.nextInt(10);
			return v;		
		}
	};
	
}
