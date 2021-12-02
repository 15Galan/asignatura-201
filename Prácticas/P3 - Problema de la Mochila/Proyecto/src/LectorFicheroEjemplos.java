
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

class LectorFicheroEjemplos {
	
	HashMap<Integer, ProblemaMochila> problemas;
	HashMap<Integer, SolucionMochila> solucionesFB;
	HashMap<Integer, SolucionMochila> solucionesPD;
	HashMap<Integer, SolucionMochila> solucionesAV;
	
	
	private  String readLine(BufferedReader br) throws IOException {
		String line = "";
		while (br.ready() && line.equals("")) {
			line = br.readLine(); // lee una linea del fichero
			line = line.trim(); // le quita los espacios en blanco al final, si los hay
		}
		return line;
	}
	
	/**
	 * Lee los problemas y su solucion desde un fichero de ejemplos  
	 * @param fileName, nombre del fichero que contiene los ejemplos
	 */
	public LectorFicheroEjemplos(String fileName) {
		problemas = new HashMap<Integer, ProblemaMochila>();
		solucionesFB = new HashMap<Integer, SolucionMochila>();
		solucionesPD = new HashMap<Integer, SolucionMochila>();
		solucionesAV = new HashMap<Integer, SolucionMochila>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int i=0;
			while (br.ready()) {
				String line = readLine(br);
				if (!line.equals("")) {
					ProblemaMochila pm = null;
					SolucionMochila smFB = null;
					SolucionMochila smPD = null;
					SolucionMochila smAV = null;
					int pesoMaximo = ProblemaMochila.SIN_CALCULAR;
					int[] pesos = ArrayUtils.leerArrayInt(line);
					line = readLine(br);  // lee la linea de valores
					int[] valores =  ArrayUtils.leerArrayInt(line); 
					
					line = readLine(br); //lee la linea de unidades
					int[] unidades = ArrayUtils.leerArrayInt(line);
					
					
					line = readLine(br);  // lee la linea de peso maximo
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(line).useDelimiter("[^0-9]+");
					if (sc.hasNextInt()) {
						pesoMaximo = sc.nextInt();
					}
					pm = new ProblemaMochila(pesos, valores,unidades, pesoMaximo);
					line = br.readLine();  // lee la linea de la solucion por fuerza bruta
					smFB = leerSolucion(pm, line);							
					line = readLine(br);  // lee la linea de la solucion por programacion dinamica
					smPD = leerSolucion(pm, line);							
					line = readLine(br);  // lee la linea de la solucion por algoritmos voraces
					smAV = leerSolucion(pm, line);							
					if (pm!=null && smFB!=null && smPD!=null && smAV!=null) {
					    problemas.put(new Integer(i), pm);
					    solucionesFB.put(new Integer(i), smFB);
					    solucionesPD.put(new Integer(i), smPD);
					    solucionesAV.put(new Integer(i), smAV);
					} else {
						// System.out.println("Error leyendo el fichero "+ fileName);
					}
					i++;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error leyendo el fichero "+ fileName);
			e.printStackTrace(System.out);
		}
		
	}


	private SolucionMochila leerSolucion(ProblemaMochila pm, String line) {
		SolucionMochila sm = null;
		if (line != null && !line.equals("")) {
			int[] solucion  =  ArrayUtils.leerArrayInt(line); 
			int sumaPesos = pm.sumaPesos(solucion);
			int sumaValores = pm.sumaValores(solucion);
			sm = new SolucionMochila(solucion,sumaPesos, sumaValores);
		}
		return sm;
	}
	
	public int size() {
		int size=0;
		if (problemas!=null) {
			size = problemas.size();
		}
		return size;
	}


	public ProblemaMochila problema(int nProb) {
		ProblemaMochila m = null;
		if (problemas!=null && nProb < problemas.size()) {
			m = problemas.get(nProb);
		}
		return m;
	}

	public SolucionMochila solucionFB(int nProb) {
		SolucionMochila sm = null;
		if (solucionesFB!=null && nProb < solucionesFB.size()) {
			sm = solucionesFB.get(nProb);
		}
		return sm;
	}

	public SolucionMochila solucionPD(int nProb) {
		SolucionMochila sm = null;
		if (solucionesPD!=null && nProb < solucionesPD.size()) {
			sm = solucionesPD.get(nProb);
		}
		return sm;
	}

	public SolucionMochila solucionAV(int nProb) {
		SolucionMochila sm = null;
		if (solucionesAV!=null && nProb < solucionesAV.size()) {
			sm = solucionesAV.get(nProb);
		}
		return sm;
	}


	public SolucionMochila solucion(Mochila m, int nProb) {
		if (m instanceof MochilaFB) {
			return solucionFB(nProb);
		} else if  (m instanceof MochilaPD) {
			return solucionPD(nProb);
		} else if  (m instanceof MochilaAV) {
			return solucionAV(nProb);
		}
		return null;
	}

	
}