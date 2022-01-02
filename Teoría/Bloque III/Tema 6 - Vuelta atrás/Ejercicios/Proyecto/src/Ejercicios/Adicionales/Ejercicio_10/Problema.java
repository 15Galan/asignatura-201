package Ejercicios.Adicionales.Ejercicio_10;

import java.util.ArrayList;



/**
 * Modificación: Un trabajador siempre tiene un trabajo.
 * TODO - Corregir la resolución del problema.
 *
 * @author Antonio J. Galán Herrera
 */
public class Problema {

    private final int n;            // Filas, que representan operarios (i)
    private final int m;            // Columnas, que representan trabajos (t)

    private final boolean[][] T;    // Matriz que asigna los operarios a sus trabajos


    // -------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------

    /**
     * Constructor de la clase.
     *
     * @param tabla     Tabla de trabajos
     */
    public Problema(boolean[][] tabla) {
        n = tabla.length;
        m = tabla[0].length;
        T = tabla;
    }


    // -------------------------------------------------------------
    // Algoritmo de vuelta atrás
    // -------------------------------------------------------------

    /**
     * Comprueba si la solución es factible.
     */
    public ArrayList<int[]> resolver() {
        ArrayList<int[]> soluciones = new ArrayList<>();
        int[] solucion = new int[n];

        resolver(solucion, soluciones, 0);

        return soluciones;
    }

    /**
     * Comprueba si la solución es factible.
     *
     * @param i     Operario
     */
    public void resolver(int[] solucion, ArrayList<int[]> soluciones, int i) {
        if (esSolucion(i)){
            // Guardar la solución con las demás
            soluciones.add(solucion.clone());

        } else {
            // Generar los candidatos
            for (int j = 0; j < m; j++) {
                // Si es factible, se añade a la solución y se avanza recursivamente
                if (esCandidato(solucion, i, j)) {
                    solucion[i] = j;
                    resolver(solucion, soluciones, i+1);
                }
            }
        }
    }


    // -------------------------------------------------------------
    // Métodos auxiliares
    // -------------------------------------------------------------

    /**
     * Comprueba si la solución es factible.
     *
     * @param i     Operario
     *
     * @return TRUE si es factible, FALSE en caso contrario
     */
    public boolean esSolucion(int i){
        return i == n;  // Se rellenó el vector de solución
    }

    /**
     * Comprueba si el candidato es factible.
     *
     * @param solucion  Solución
     * @param operario  Operario
     * @param trabajo   Trabajo
     *
     * @return TRUE si es factible, FALSE en caso contrario
     */
    public boolean esCandidato(int[] solucion, int operario, int trabajo) {
        return T[operario][trabajo] && !contiene(solucion, operario, trabajo);
    }

    /**
     * Comprueba si la solución contiene un operario y un trabajo.
     *
     * @param solucion  Solución
     * @param operario  Operario
     * @param trabajo   Trabajo
     *
     * @return TRUE si contiene, FALSE en caso contrario
     */
    public boolean contiene(int[] solucion, int operario, int trabajo) {
        int i = 0;

        while (i < operario && solucion[i] != trabajo) {
            i++;
        }

        return i < operario;
    }

    /**
     * Genera una representación de una solución en forma de tabla.
     *
     * @param configuracion     Solución
     *
     * @return Tabla de trabajos
     */
    public String solucion(int[] configuracion){
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        for (int i = 0; i < m; i++) {
            sb.append("\t").append(i);
        }

        sb.append("\n");

        // Filas
        for (int i = 0; i < n; i++) {
            sb.append(i).append("\t");

            for (int j = 0; j < m; j++) {
                if (T[i][j]) {
                    if (configuracion[i] == j) {
                        sb.append("O");

                    } else {
                        sb.append("X");
                    }

                } else {
                    sb.append("·");
                }

                sb.append("\t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Genera una representación del problema en forma de tabla.
     *
     * @return Tabla de trabajos
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        for (int i = 0; i < m; i++) {
            sb.append("\t").append(i);
        }

        sb.append("\n");

        // Filas
        for (int i = 0; i < n; i++) {
            sb.append(i).append("\t");

            for (int j = 0; j < m; j++) {
                if (T[i][j]) {
                    sb.append("X");

                } else {
                    sb.append("·");
                }

                sb.append("\t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
