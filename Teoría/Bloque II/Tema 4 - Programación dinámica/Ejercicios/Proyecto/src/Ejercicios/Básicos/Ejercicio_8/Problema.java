package Ejercicios.Básicos.Ejercicio_8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problema {

    private final int[][] m;
    private int[][] A;


    // --------------------------------------------------
    // Constructor
    // --------------------------------------------------

    /**
     * Constructor de la clase Problema
     *
     * @param tablero   Tablero de juego
     */
    public Problema(int[][] tablero) {
        m = tablero;
        A = rellenarA();
    }


    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------

    /**
     * Resuelve el problema.
     *
     * @return  Lista con la solución
     */
    public List<Integer> resolver() {
        List<Integer> solucion = new ArrayList<>();

        // System.out.println("Tabla A:\n" + entablar(A) + "\n");

        solucion(solucion);

        return solucion;
    }

    /**
     * Rellena la tabla A según la ecuación de Bellman descrita.
     *
     * @return Tabla A
     */
    private int[][] rellenarA() {
        A = new int[m.length][m.length];

        // Caso base (fila n-1 de 'A' es la fila n-1 de 'm')
        System.arraycopy(m[m.length - 1], 0, A[m.length - 1], 0, m.length);

        // Casos intermedios
        for (int f = m.length-2; f >= 0; f--) {
            for (int c = 0; c < m.length; c++) {
                A[f][c] = maximo(f, c) + m[f][c];
            }
        }

        return A;
    }



    private void solucion(List<Integer> solucion) {
        int F = 0, C = 0, max = 0;

        // Encontrar la puntuación final más alta (máximo de la fila 0)
        for (int c = 0; c < A.length; c++) {
            if (max < A[0][c]) {
                max = A[0][c];
                C = c;
            }
        }

        // Este corresponde al último valor de la solución
        solucion.add(m[0][C]);

        // Encontrar el resto de la solución
        while (F < m.length - 1) {
            max = maximo(F, C);
            // La casilla está pegando a la izquierda
            if (C == 0) {
                if (max == A[F+1][C]) {
                    solucion.add(m[F+1][C]);

                } else {
                    solucion.add(m[F+1][C+1]);
                    C++;
                }

            } else if (C == m.length - 1) {
                if (max == A[F+1][C]) {
                    solucion.add(m[F+1][C]);

                } else {
                    solucion.add(m[F+1][C-1]);
                    C--;
                }

            } else {
                if (max == A[F+1][C-1]) {
                    solucion.add(m[F][C-1]);
                    C--;

                } else if (max == A[F+1][C]) {
                    solucion.add(m[F+1][C]);

                } else {
                    solucion.add(m[F+1][C+1]);
                    C++;
                }
            }

            F++;
        }

        // Porque se ha rellenado desde el final
        invertir(solucion);
    }


    // --------------------------------------------------
    // Métodos auxiliares
    // --------------------------------------------------

    /**
     * Calcula el valor máximo entre las 3 casillas inferiores.
     *
     * @param f    Fila
     * @param c    Columna
     *
     * @return Valor máximo entre las 3 casillas inferiores
     */
    private int maximo(int f, int c) {
        // La casilla está pegando a la izquierda
        if (c == 0) {
            return Math.max(A[f+1][c], A[f+1][c+1]);

        // La casilla está pegando a la derecha
        } else if (c == m.length - 1) {
            return Math.max(A[f+1][c-1], A[f+1][c]);

        } else {
            // Comparación de 3 valores
            return Math.max(Math.max(A[f+1][c-1], A[f+1][c]), A[f+1][c+1]);
        }
    }

    /**
     * Invierte una lista.
     *
     * @param lista     Lista a invertir
     */
    private void invertir(List<Integer> lista) {
        Collections.reverse(lista);
    }

    /**
     * Entabla una matriz.
     *
     * @param tabla    Matriz a entablar
     *
     * @return Matriz entablada
     */
    public String entablar(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        for (int[] f : tabla) {
            for (int c = 0; c < tabla.length; c++) {
                sb.append(String.format("%3d", f[c]));
            }

            if (f != tabla[tabla.length - 1]) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }


    @Override
    public String toString() {
        return entablar(m);
    }
}
