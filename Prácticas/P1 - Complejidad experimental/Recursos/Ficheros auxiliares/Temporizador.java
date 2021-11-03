/**
 * @author Pepe Gallardo
 *
 * Temporizadores para medir tiempos de ejecucion
 */
public class Temporizador {

	final static int MILIS = 1;
	final static int NANO = 2;
	
	private int units; // 1 = MILIS; 2 = NANO
	private boolean andando;
	private long tiempoInicio;
	private long tiempoPasado;

	
	// Crea un temporizador
	public Temporizador() {
		this.reiniciar();
	}
	
	// Crea un temporizador
	public Temporizador(int units) {
		this.units = units;
		reiniciar();
	}

	// Lo pone a cero
	public void reiniciar() {
		andando = false;
		tiempoPasado = 0;
	}
	
	// Lo pone a andar
	public void iniciar() {
		if(!andando) {
			andando = true;
			tiempoInicio =  (units == MILIS ? System.currentTimeMillis() : System.nanoTime());
		}
	}
	
	// Detiene temporalmente el temporizador
	public void parar() {
		if(!andando)
			throw new RuntimeException("Temporizador ya parado");
		else {
			tiempoPasado +=  (units == MILIS ? System.currentTimeMillis() : System.nanoTime()) - tiempoInicio;
			andando = false;
		}
	}
	
	// Devuelve el tiempo (en nanosegundos) que el temporizador ha estado andando
	public long tiempoPasado(){
		if(andando)
			return tiempoPasado + (units == MILIS ? System.currentTimeMillis() : System.nanoTime()) - tiempoInicio;
		else 
			return tiempoPasado;
	}

}
