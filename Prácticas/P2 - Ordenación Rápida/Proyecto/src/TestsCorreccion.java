/**
 * @author Pepe Gallardo
 * @modifiedby Jose A. Onieva
 * La implementación del alumno debe pasar este test experimental
 */

import java.util.*;

public class TestsCorreccion {

	// Comprueba experimentalmente el método resolverTodos
	public static void testResolverTodos() {
		EvaluacionExperimental eval = new EvaluacionExperimental ("Test de resolverTodos");
		eval.realizarCon("tests.txt");		
	}
	
	
	public static void main(String[] args) {
		testResolverTodos();
	}	

}
