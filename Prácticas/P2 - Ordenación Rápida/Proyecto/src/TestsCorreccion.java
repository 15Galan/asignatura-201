/**
 * La implementación del alumno debe pasar este test experimental
 *
 * @author Pepe Gallardo
 *
 * @modifiedby Jose A. Onieva
 */

public class TestsCorreccion {

	// Comprueba experimentalmente el método resolverTodos (posible fallo E/S)
	public static void testResolverTodos() {
		EvaluacionExperimental eval = new EvaluacionExperimental ("Test de resolverTodos");
		eval.realizarCon("tests.txt");		
	}
	
	
	public static void main(String[] args) {
		testResolverTodos();
	}	

}
