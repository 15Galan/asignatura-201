
import java.util.ArrayList;

/**
 * Clase auxiliar para devolver una solucion del problema del Mochila
 * @author Conejo, Onieva & Miguel García
 */
class SolucionMochila {

	protected final static int SIN_CALCULAR = Integer.MIN_VALUE;

	protected ArrayList<Integer> solucion=null;	
	protected int sumaPesos = SIN_CALCULAR;
	protected int sumaValores = SIN_CALCULAR;

	public SolucionMochila() {
		solucion=null;	
		sumaPesos = SIN_CALCULAR;
		sumaValores = SIN_CALCULAR;
	}

	public SolucionMochila(int[] solucion, int sumaPesos, int sumaValores) {
		setSolucion(ArrayUtils.toArray(solucion));
		setSumaPesos(sumaPesos);
		setSumaValores(sumaValores);
	}
	public SolucionMochila(ArrayList<Integer> solucion, int sumaPesos, int sumaValores) {
		setSolucion(solucion);
		setSumaPesos(sumaPesos);
		setSumaValores(sumaValores);
	}	
	public void setSolucion(ArrayList<Integer> solucion) {
		this.solucion = solucion;
	}
	public ArrayList<Integer> getSolucion() {
		 return solucion;
	}

	public void setSumaPesos(int sumaPesos) {
		this.sumaPesos =sumaPesos;
	}
	public int getSumaPesos() {
		 return sumaPesos;
	}
	
	public void setSumaValores(int sumaValores) {
		this.sumaValores =sumaValores;
	}
	public int getSumaValores() {
		 return sumaValores;
	}

	
	public boolean equals(Object obj){
		boolean equals = false;
		if (obj!=null && obj instanceof SolucionMochila) {
			SolucionMochila sm = (SolucionMochila) obj;
			equals = (   this.solucion!=null && solucion!=null 
					  && this.solucion.size() == sm.solucion.size() 
					  && this.sumaPesos == sm.sumaPesos 
					  && this.sumaValores == sm.sumaValores);
			for(int i=0; equals && i<solucion.size(); i++) {
				equals = equals && ArrayUtils.equals(solucion, sm.solucion);
			}
		}
		return equals;
	}
	
	public String toString() {
		String str = "";
		if (solucion!=null && solucion.size()>0) {
			str += solucion.get(0);
			for(int i=1; i<solucion.size(); i++) {
				str += ", "+solucion.get(i);
			}
			if (sumaPesos!=SIN_CALCULAR && sumaValores!=SIN_CALCULAR) {
				str += "\n";
				str += "peso total "+sumaPesos + "; valor total "+sumaValores + ";";
			}
		} else {
			str = "SINCALCULAR";
		}
		return str;
	}
	
}
