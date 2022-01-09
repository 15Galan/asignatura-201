package Ejercicios.Básicos.Ejercicio_8;

public class Solucion {

    public static void main(String[] args) {
        // Datos del problema
        int[][] tablero = {{1,4,8,3}, {1,5,2,10}, {8,2,7,9}, {3,5,2,1}};

        // Crear problema
        Problema problema = new Problema(tablero);
        System.out.println("Problema planteado:\n" + problema + "\n");

        // Crear solución
        System.out.println("Solución:\n" + problema.resolver());
    }
}
