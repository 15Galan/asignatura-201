package Ejemplos;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Pagar un precio con la mínima cantidad de monedas posibles, habiendo monedas de diferente valor.
 *
 * EJEMPLO 1:
 * Pagar 8 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 2 monedas (cada una de 4 u).
 *
 * EJEMPLO 2:
 * Pagar 11 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 3 monedas (una de 6 u, otra de 4 u y otra de 1 u).
 */
public class Monedas {

    public static void main(String[] args) {
        int precio = 11;
        int[] denominaciones = {1,4,6};

        System.out.println("Pagar " + precio + " unidades con monedas de " + Arrays.toString(denominaciones) + " unidades.");
        System.out.println("Solución: " + devolucionMinima(precio, denominaciones));
    }

    /**
     * Calcula la cantidad mínima de monedas que se necesitan
     * para pagar un precio, siendo las monedas de diferente valor.
     *
     * @param precio    Precio a pagar
     * @param monedas   Vector con los valores de las monedas
     *
     * @return  La cantidad mínima de monedas para pagar el precio
     */
    private static int devolucionMinima(int precio, int[] monedas){

        // Crear la tabla de devoluciones
        int[][] tabla = new int[monedas.length+1][precio+1];

        // Rellenar la columna 0 con 'ceros'
        for (int i = 0; i < monedas.length; i++) {
            tabla[i][0] = 0;
        }

        // Rellenar la fila 0 (salvo la columna 0) con 'infinitos'
        for (int j = 1; j <= precio; j++) {
            tabla[0][j] = 999999;
        }

        for (int i = 1; i <= monedas.length ; i++) {
            for (int j = 1; j <= precio; j++) {
                if (monedas[i-1] > j) {
                    tabla[i][j] = tabla[i-1][j];

                } else {
                    // Calcular el mínimo de las 2 posiciones y guardarlo en la tabla
                    tabla[i][j] = Math.min(tabla[i-1][j], tabla[i][ j-monedas[i-1] ]+1);
                }
            }
        }

        return tabla[monedas.length][precio];
    }
}
