import java.util.*;
/**
 * @author Pepe Gallardo
 * 
 * La implementación del alumno debe pasar este test experimental
 */
public class TestsCorreccion {

	// Comprueba experimentalmente el m�todo resolverTodos
	public static void testResolverTodos() {
		EvaluacionExperimental eval = 
			new EvaluacionExperimental ("Test de resolverTodos") {
				public boolean comprobacion(TableroSudoku ts, List<TableroSudoku> sols) {

					List<TableroSudoku> miSols = ts.resolverTodos();
					if(!miSols.containsAll(sols)) {
						System.out.println("Fallo en la evaluación experimental:");
						System.out.println("Las siguientes soluciones de la instancia "+ts+" no son encontradas por la implementación:");
						sols.removeAll(miSols);
						System.out.println(sols);
						return false;
					} else if (!sols.containsAll(miSols)) {
						System.out.println("Fallo en la evaluación experimental:");
						System.out.println("Las siguientes soluciones encontradas por la implementación no son soluciones de la instancia "+ts);
						miSols.removeAll(sols);
						System.out.println(miSols);
						return false;
					} else 
						return true;
				}
			};		
		eval.realizarCon("tests.txt");		
	}
	
	
	public static void main(String[] args) {
		testResolverTodos();
	}	

}
