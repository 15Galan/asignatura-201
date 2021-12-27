import java.io.*;
import java.util.*;

/**
 * @author Pepe Gallardo
 *
 */
abstract public class EvaluacionExperimental {

	String nombreEval; // Nombre para la prueba

	public EvaluacionExperimental(String nombre) {
		nombreEval = nombre;
	}
	


	/**
	 * @param scmc : una instancia del problema
	 * @param sols : la lista con todas sus soluciones
	 * @return     : true si la implementaci\u00D3n de TableroSudoku pasa la comprobaci\u00D3n
	 * 				 para la instancia @ts
	 */
	abstract public boolean comprobacion(TableroSudoku ts, List<TableroSudoku> sols);
	
	
	/**
	 * @param fichero : el nombre de un fichero de texto (generado con 
	 *                  el método @vuelcaEjemplos) con instancias resueltas.
	 *                  
	 * Realiza una evaluaci\u00D3n experimental del problema, realizando
	 * la comprobaci\u00D3n especificada con el método @comprobaci\u00D3n
	 * para todas las instancias en el fichero.                 
	 */
	public void realizarCon(String fichero){
		BufferedReader br;
		String tablero;
		boolean ok = true;
		int numSols;
		
		try {
			br = new BufferedReader(new FileReader(fichero));
			
			while((tablero=br.readLine()) != null) {
				
				// Leer soluciones
				List<TableroSudoku> sols = new LinkedList<TableroSudoku>();
				numSols = Integer.parseInt(br.readLine());
				for (int i=0; i<numSols; i++) 
			         sols.add(new TableroSudoku(br.readLine()));

				br.readLine();
	
				// Solucionar la instancia
				if(!comprobacion(new TableroSudoku(tablero), sols))
					ok = false;
			}
			br.close();
			System.out.println(nombreEval+" "+ (ok ? "correcto" : "INCORRECTO!!"));
			
		} catch (IOException e) {
			throw new RuntimeException("Fallo de E/S");
		}	
	}
	
}
