/**
 * @author Pepe Gallardo
 *
 * Temporizadores para medir tiempos de ejecuci\u00D3n
 */
public class Temporizador {

	private boolean andando;
	private long tiempoInicio;
	private long tiempoPasado;

	
	// Crea un temporizador
	public Temporizador() {
		this.resetear();
	}
	
	// Lo pone a cero
	public void resetear() {
		andando = false;
		tiempoPasado = 0;
	}
	
	// Lo pone a andar
	public void iniciar() {
		if(!andando) {
			andando = true;
			tiempoInicio = System.nanoTime();
		}
	}
	
	// Detiene temporalmente el temporizador
	public void parar() {
		if(!andando)
			throw new RuntimeException("Temporizador ya parado");
		else {
			tiempoPasado += System.nanoTime()-tiempoInicio;
			andando = false;
		}
	}
	
	// Devuelve el tiempo (en nanosegundos) que el temporizador ha estado andando
	public long tiempoPasado(){
		if(andando)
			return tiempoPasado + System.nanoTime() - tiempoInicio;
		else 
			return tiempoPasado;
	}

}
