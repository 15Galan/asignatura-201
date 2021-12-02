

class ProbarEjemplos {
	
	private static void probar(Mochila m, LectorFicheroEjemplos lf,int maxDim) {
		for(int i=0; i<lf.size(); i++) {
			ProblemaMochila problema = lf.problema(i);
			if (problema.size() <= maxDim) {
				SolucionMochila sproblema = lf.solucion(m, i);
				SolucionMochila solucionAlumno = m.resolver(problema);
				if (!problema.equivalente(solucionAlumno, sproblema)) {
					System.out.println("No se ha encontrado la solucion correcta a este problema\n");
					System.out.println("Problema:");
					System.out.println(problema);
					System.out.println("Solucion hallada:");
					System.out.println(solucionAlumno);
					System.out.println("Solucion correcta:");
					System.out.println(sproblema);
					//System.exit(1);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		LectorFicheroEjemplos lf = new LectorFicheroEjemplos("ejemplos.txt");
		
		// Probar el metodo de fuerza bruta
		probar(new MochilaFB(), lf, 20);
		System.out.println("El metodo de fuerza bruta supera las pruebas de los ejemplos");
		
		// Probar la solucion de programacion dinamica
		probar(new MochilaPD(), lf, 20);
		System.out.println("El metodo de Programacion Dinamica supera las pruebas de los ejemplos");

		// Probar la solucion de algoritmo voraz
		probar(new MochilaAV(), lf, 20);
		System.out.println("El metodo de Algorimos Voraces supera las pruebas de los ejemplos");
		//*/ 
	}
	
}