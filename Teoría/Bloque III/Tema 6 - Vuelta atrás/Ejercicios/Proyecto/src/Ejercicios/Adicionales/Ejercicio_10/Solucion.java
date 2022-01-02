package Ejercicios.Adicionales.Ejercicio_10;


import java.util.ArrayList;
import java.util.Arrays;

public class Solucion {

    public static void main(String[] args) {
        // Datos del problema
        boolean[][] tabla = {
                {false, true, false, true},
                {false, true, true, false},
                {true, true, false, true}
        };

        // Crear el problema
        Problema problema = new Problema(tabla);
        System.out.println("Problema:\n" + problema + "\n");


        // Calcular las soluciones
        ArrayList<int[]> soluciones = problema.resolver();
        System.out.println("Soluciones:");

        for (int[] solucion : soluciones) {
            System.out.println(Arrays.toString(solucion));
            System.out.println(problema.solucion(solucion) + "\n");
        }
    }
}
