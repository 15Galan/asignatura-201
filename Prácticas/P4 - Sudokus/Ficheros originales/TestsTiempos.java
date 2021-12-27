import java.util.*;

/**
 * @author Pepe Gallardo
 * 
 * Muestra gr\u00E1ficas con el comportamiento experimental de la implementaci\u00D3n
 */

public class TestsTiempos {

	
	static Map<Integer,List<Long>> map;

	public static void testResolverTodos() {
		
		EvaluacionExperimental eval = 
			new EvaluacionExperimental ("Test de tiempos") {
				public boolean comprobacion(TableroSudoku ts, List<TableroSudoku> sols) {
					
					Temporizador t = new Temporizador();
					int numFijos = ts.numeroDeFijos();
					
					t.resetear();
					t.iniciar();
					List<TableroSudoku> miSols = ts.resolverTodos();
					t.parar();
					
					
					
					List<Long> ls = map.get(numFijos);
					if(ls==null) {
						ls = new ArrayList<Long>();
						map.put(numFijos,ls);
					}
					ls.add(t.tiempoPasado());

					
					return true;
				}
			};		
		eval.realizarCon("tests.txt");		
	}
	
	
		
	public static void testVueltaAtras() {
		
		map = new TreeMap<Integer,List<Long>>();
		testResolverTodos();
		Grafica gr  = new Grafica( "Tiempo para distintos fijos iniciales"
                , "Nº de fijos" 
                , "millisegundos"
                , "%.0f"
                , "%.2f"
                );

		Grafica.Linea ln = gr.new Linea("Vuelta atr\u00E1s");
		
		
		for(int numFijos : map.keySet()) {
			List<Long> ls = map.get(numFijos);
			long suma=0;
			for(long t : ls)
				suma += t;
			
			double media = (double) suma  / ls.size();
			ln.addData((double) numFijos, media*1e-6);
		}
		
	}

	public static void main(String args[]) {
		testVueltaAtras();
	}

	
}
