import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * @author Pepe Gallardo
 * @modifiedby Jose A. Onieva
 * @modifiedby Ricardo Conejo
 * clases para generar y comprobar tets experimentales
 */



public class EvaluacionExperimental {

	String nombreEval; // Nombre para la prueba

	public EvaluacionExperimental(String nombre) {
		nombreEval = nombre;
	}
	
	// Escribe un vector en un PrintWriter
	public static <T> void escribeVector(T v[], PrintWriter pw) {
		pw.println(v.length);
		for(int i=0; i<v.length; i++)
			pw.print(v[i]+" ");
		pw.println();
	}
	
	// Lee un vector de un BufferedReader
	public static Integer[] leeVector(BufferedReader br) throws IOException {
		String line = br.readLine();
		if (line==null)
			return null;
		

		Integer v[] = new Integer[Integer.parseInt(line)];
		
		line = br.readLine();
		
		StringTokenizer st = new StringTokenizer(line," ");
		int i = 0;
		while(st.hasMoreTokens()) 
			v[i++] = Integer.parseInt(st.nextToken());

		return v;
				
	}
	
	public static void vuelcaEjemplos(File fichero) {
		int numTests = 10;
		int minTam = 5, maxTam = 100, stepTam = 5;
		
		try {
			PrintWriter pw = new PrintWriter(fichero);
			for(int l=minTam; l<=maxTam; l+=stepTam) {				
					for(int i=0; i<numTests; i++) {
						
						Integer vEnt[] = GeneradoresVectorEnteros.aleatorio.nuevoVector(l);
						Integer vCp[] = vEnt.clone();
						Integer vAux[] = vEnt.clone();
						
 						escribeVector(vEnt,pw);
 				
 						Arrays.sort(vEnt);
 						
 						escribeVector(vEnt,pw);
        				pw.println(); 						
 						
 						for(int j=0;j<l-1;j++){
 							vAux = vCp.clone();
 							OrdenacionRapida.ordRapidaRec(vAux, 0, j);
 							escribeVector(vAux,pw);
 	        				pw.println(); 
 						}
 							
 
        				pw.println();	
					}	
			}

			pw.close();
		} catch (IOException e) {
			throw new RuntimeException("Fallo de E/S");
		}
	}
	
	/**
	 * @param v      : un vector a ordenar
	 * @param vOrd   : el vector @v ordenado por el alumno
	 * @return       : true si la implementación del algoritmo de ordenación pasa la comprobación
	 * 				   para la instancia @v
	 */
	public static <T extends Comparable<? super T>> boolean comprobacion(T v[], T vOrd[]) {
		T vOrdOK[] = v.clone();
		Arrays.sort(vOrdOK);
		return comprobacion(v, vOrdOK, vOrd);
	}
	
	/**
	 * @param v      : un vector a ordenar
	 * @param vOrdOK : la solución correcta
	 * @param vOrd   : el vector @v ordenado por el alumno
	 * @return       : true si la implementación del algoritmo de ordenación pasa la comprobación
	 * 				   para la instancia @v
	 */
	public static <T> boolean comprobacion(T v[], T vOrdOK[], T vOrd[]) {
		boolean ok = true;
		
		if(!Arrays.equals(vOrd,vOrdOK)) {
			System.out.println("Fallo en la evaluación experimental");
			System.out.println("Con el vector v =   "+Ordenacion.vectorAString(v));
			System.out.println("Devuelve:           "+Ordenacion.vectorAString(vOrd));
			System.out.println("Debería devolver:   "+Ordenacion.vectorAString(vOrdOK));
			System.out.println();
			ok = false;
		}				
		
		return ok;
	}
	
	public static <T> boolean comprobacionParcial(int l, T v[], T vOrdOK[], T vOrd[]) {
		boolean ok = true;
		
		if(!Arrays.equals(vOrd,vOrdOK)) {
			System.out.println("Fallo en la evaluación experimental");
			System.out.println("La invocación OrdenacionRapida.ordena(v,"+l+");");
			System.out.println("Con el vector v =   "+Ordenacion.vectorAString(v));
			System.out.println("Devuelve:           "+Ordenacion.vectorAString(vOrd));
			System.out.println("Debería devolver:   "+Ordenacion.vectorAString(vOrdOK));
			System.out.println();
			ok = false;
		}				
		
		return ok;
	}
	
	/**
	 * @param fichero : el nombre de un fichero de texto (generado con 
	 *                  el método @vuelcaEjemplos) con instancias resueltas.
	 *                  
	 * Realiza una evaluación experimental del problema, realizando
	 * la comprobación especificada con los métodos @comprobación
	 * y @comprobaciónParcial para todas las instancias en el fichero.                 
	 */
	public void realizarCon(String fichero){
		BufferedReader br;
		boolean ok = true;
		Integer v[], vOrdOK[], vOrd[];
		
		
		try {
			br = new BufferedReader(new FileReader(fichero));
			
			while(ok && (v=leeVector(br)) != null) {
				
				// Leer solución
				vOrdOK = leeVector(br);				
				br.readLine();        // Elimina la separaci—n
				
				vOrd = v.clone();
	
				// Solucionar la instancia
				OrdenacionRapida.ordenar(vOrd);
				if(!comprobacion(v,vOrdOK,vOrd)) {
					ok = false;
					break;
				}	
				
				for(int j=0;j<v.length-1;j++){
					vOrdOK = leeVector(br);					
					br.readLine();
					
					vOrd = v.clone();
					// Solucionar parcialmente la instancia
					OrdenacionRapida.ordRapidaRec(vOrd,0,j);
					if(!comprobacionParcial(j,v,vOrdOK,vOrd)){
						ok = false;
						break;
					}
				}
				br.readLine();							
			}
			br.close();
			System.out.println(nombreEval+" "+ (ok ? "correcto" : "INCORRECTO!!"));
			if(!ok) {
				System.exit(1);
			}

			
		} catch (IOException e) {
			throw new RuntimeException("Fallo de E/S");
		}	
	}	
	
	
	public static void main(String args[]) {
		File fich = new File("tests.txt");
		if(fich.exists())
			throw new RuntimeException("\nEl fichero \""+fich.getAbsolutePath()+"\" ya existe. No deberías modificarlo.");
		else {
			vuelcaEjemplos(fich);
			System.out.println("Ejemplos generados en el fichero \""+fich.getAbsolutePath()+"\"");
		}
	}
}
