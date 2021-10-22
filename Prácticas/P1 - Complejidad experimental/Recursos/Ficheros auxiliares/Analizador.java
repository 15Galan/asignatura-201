
public class Analizador {
	
	/* 
	 * NOTA IMPORTANTE
	 * 
	 * Esta clase se proporciona solamente para ilustrar el formato de
	 * salida que deberia tener la solucion a este ejericio.
	 * Esta clase debe modificarse completamente para cumplir 
	 * mínimamente los requisitos de esta practica.
	 * Notese que ni siquiera esta completa......
	 */
	
	public static String masCercano(double ratio) {
			if (ratio < 1.5) {                      // aprox 1.0
				return "1";							
			} else if (1 <= ratio && ratio < 3.0) { // aprox 2.0
				return "N";
			} else if (3 <= ratio && ratio < 6.0) { // aprox 4.0
				return "N2";
			} else if (6 <= ratio && ratio < 10.0) { // aprox 8.0
				return "N3";
			} else { 								 // otras
				return "NF";
			}
	}
	
	public static void main(String arg[]) {
		int n1 = 100000;
		int n2 = 200000;
		Temporizador t = new Temporizador();
		t.iniciar();
		Algoritmo.f(n1);
		t.parar();
		long t1 = t.tiempoPasado();
		t.reiniciar();
		t.iniciar();
		Algoritmo.f(n2);
		t.parar();
		long t2 = t.tiempoPasado();
		double ratio = (double)t2/t1;
		System.out.println(masCercano(ratio));
	}
}
