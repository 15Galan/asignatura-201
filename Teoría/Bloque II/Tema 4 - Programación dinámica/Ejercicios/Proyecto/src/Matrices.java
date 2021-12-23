/**
 * @author Guillermo López García
 */
public class Matrices {

    public static void main(String[] args) {
        int[] d = {10, 100, 5, 50};

        System.out.println("Mínimo número de multiplicaciones secuenciales:");

        // Memoización
        int n = d.length - 1;
        int[][] M = new int[n][n];

        // Rellenar toda la tabla con -1 para indicar que está vacía
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = -1;
            }
        }

        System.out.println("\t* Enfoque 'Bottom-up'   : " + prodSecMatriz(d));
        System.out.println("\t* Enfoque 'Memoización' : " + prodSecMatrizMem(d, M, 0, n-1));
    }


    /**
     * Calcula el número mínimo de multiplicaciones encadenadas
     * de matrices aplicando un enfoque 'Bottom-Up'.
     *
     * @param dim   Array de dimensiones de las matrices compatibles
     *
     * @return  TODO
     */
    public static int prodSecMatriz(int[] dim) {
        int n = dim.length-1;
        int[][] M = new int[n][n];

        // Inicializar la diagonal de la tabla a cero
        for (int i = 0; i < n; i++) {
            M[i][i] = 0;
        }

        // i < j
        int k, min, actual;

        for (int diagonal = 1; diagonal < n; diagonal++) {
            for (int i = 0; i < n - diagonal; i++) {
                k = i + diagonal;
                min = Integer.MAX_VALUE;

                for (int j = i; j < k; j++) {
                    actual = M[i][j] + M[j+1][k] + dim[i]*dim[j+1]*dim[k+1];

                    if (actual < min) {
                        min = actual;
                    }
                }

                M[i][k] = min;
            }
        }

        return M[0][n-1];
    }

    /**
     * Calcula el número mínimo de multiplicaciones encadenadas
     * de matrices aplicando un enfoque de 'Memoización'.
     *
     * @param dim   Array con las dimensiones de las matrices compatibles
     * @param M     Tabla del algoritmo
     * @param f     Fila de la tabla M
     * @param c     Columna de la tabla M
     *
     * @return  TODO
     */
    public static int prodSecMatrizMem(int[] dim, int[][] M, int f, int c) {
        // Solución calculada previamente
        if (M[f][c] != -1) {
            return M[f][c];
        }

        // fila = columna
        if (f == c) {
            M[f][c] = 0;

        // fila < columna
        } else {
            int min = Integer.MAX_VALUE, actual;    // El mínimo empieza siendo máximo

            for (int i = f; i < c; i++) {
                actual = prodSecMatrizMem(dim, M, f, i) + prodSecMatrizMem(dim, M, i+1, c) + dim[f]*dim[i+1]*dim[c+1];

                if (actual < min) {
                    min = actual;
                }
            }

            M[f][c] = min;
        }

        return M[f][c];
    }
}
