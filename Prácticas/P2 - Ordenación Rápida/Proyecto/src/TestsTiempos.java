
/**
 * @author Pepe Gallardo
 * 
 * Muestra gr�ficas con el comportamiento experimental de la implementaci�n
 * frente a otros m�todos de ordenaci�n
 * 
 */

public class TestsTiempos {

	public static double aMilisegs(long n) {
		return (1e-6*(double) n);
	}
	
	public static void testResolverTodos(GeneradorVector<Integer> generador, String subtitulo) {
	
		int numTests = 20; //n�mero de tests para cada tama�o
		int minTam = 0, maxTam = 2000, saltoTam = 25; //tama�os m�nimos, m�ximos y saltos
		
		final Grafica gr  = new Grafica ( "Tiempo de ordenaci\u00f3n para distintos tama\u00f1os de vector"
				                 		, subtitulo
				                 		, "Tama\u00f1o vector" 
				                 		, "Milisegundos"
				                 		, "%.0f"
				                 		, "%.2f"
                						);

		abstract class Resolutor {
			Temporizador tempo;
			Grafica.Linea ln;
			
			Resolutor(String label) {
				tempo = new Temporizador();
				ln = gr.new Linea(label);
			}
			
			abstract void ordena(Integer v[]);
			
			void ejecutaCon (Integer v[]) {
				Integer vAux[] = v.clone();						
				tempo.iniciar();
				ordena(vAux);
				tempo.parar();				
			}			
		}	
		
	
		Resolutor resolutores[] = {
               new Resolutor("Ord Burbuja") { 
				void ordena(Integer v[]) { OrdenacionBurbuja.ordenar(v); } }
             , new Resolutor("Ord Selecci\u00f3n") { 
				void ordena(Integer v[]) { OrdenacionSeleccion.ordenar(v); } }
             , new Resolutor("Ord Inserci\u00f3n") { 
				void ordena(Integer v[]) { OrdenacionInsercion.ordenar(v); } }
             , new Resolutor("Ord Mezcla") { 
				void ordena(Integer v[]) { OrdenacionMezcla.ordenar(v); } }
             , new Resolutor("Ord R\u00e1pida") { 
				void ordena(Integer v[]) { OrdenacionRapida.ordenar(v); } }
             , new Resolutor("Ord R\u00e1pida (barajada)") { 
				void ordena(Integer v[]) { OrdenacionRapidaBarajada.ordenar(v); } }
             , new Resolutor("Ord Java") { 
				void ordena(Integer v[]) { OrdenacionJava.ordenar(v); } }
			 };
			
		
		for(int n=minTam; n<=maxTam; n+=saltoTam) {
			
			// resetear temporizadores
			for (Resolutor r : resolutores) 
				r.tempo.reiniciar();
				
			// ejecutar ordenaciones
			for(int i=0; i<numTests; i++) 
					for (Resolutor r : resolutores) 
						r.ejecutaCon(generador.nuevoVector(n));			
			
			// registrar las medias de tiempo para cada algoritmo
			for (Resolutor r : resolutores) 
				r.ln.anadeDatos((double) n, aMilisegs(r.tempo.tiempoPasado())/numTests);						
		}
		
	}

	public static void main(String args[]) {
		testResolverTodos( GeneradoresVectorEnteros.casiOrdenadoAscendente
				         , "Datos inicialmente casi ordenados ascendentemente");		
		testResolverTodos( GeneradoresVectorEnteros.aleatorio
				         , "Datos inicialmente ordenados aleatoriamente");
		testResolverTodos( GeneradoresVectorEnteros.ordenadoDescendente
				         , "Datos inicialmente ordenados descendentemente");			
	}

	
}
