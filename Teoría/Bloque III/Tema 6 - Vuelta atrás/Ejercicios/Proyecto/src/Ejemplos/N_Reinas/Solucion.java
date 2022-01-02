package Ejemplos.N_Reinas;

import java.util.List;



public class Solucion {

    /**
     * Ejecuta la resolución de un tablero de sudoku.
     *
     * @param args	Argumentos de clase recibidos
     */
    public static void main(String[] args) {
        // Datos del problema
        String configuracion = "............R............";

        // Crear el problema
        Problema tablero = new Problema(configuracion);
        System.out.println("Problema planteado:\n" + tablero.tablero());

        // Calcular las soluciones
        List<Problema> soluciones = tablero.resolverTodos();
        System.out.println("Se encontraron " + soluciones.size() + " soluciones:");

        for (Problema solucion : soluciones) {
            System.out.println(solucion.tablero());
        }
    }
}
