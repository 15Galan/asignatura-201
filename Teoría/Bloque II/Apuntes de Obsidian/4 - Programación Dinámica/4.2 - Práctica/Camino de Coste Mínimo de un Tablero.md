---
aliases: ["Ejercicio Complementario 11"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Juego del Tablero]]

# Problema

Dada una tabla de tamaño $n × n$ de números naturales, se pretende resolver el problema de obtener un camino de la casilla $(1,1)$ a la casilla $(n, n)$ que minimice la suma de los valores de las casillas por las que pasa.

En cada casilla $(i, j)$ habrá solo dos posibles movimientos: ir hacia abajo $(i + 1, j)$, o hacia la derecha $(i, j + 1)$.

> [!todo] Se desea resolver el problema usando programación dinámica.

## Ejemplo del problema

Sea el tablero siguiente:

|     |     |     |     |
|:---:|:---:|:---:|:---:|
|  2  |  8  |  3  |  4  |
|  5  |  3  |  4  |  5  |
|  1  |  2  |  2  |  1  |
|  3  |  4  |  6  |  5  |

# Solución
## 1 - Dimensiones relevantes

Planteadas por mí:

- $S$: camino solución.
- $V_S$: valor del camino solución.

Planteadas por el enunciado:

- $T$: tablero.
- $n$: tamaño del tablero.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

El problema indica que los tableros planteados tienen un tamaño $n × n$, lo que podría llevar a pensar que si el tablero mide $1 × 1$, entonces $S = [T_{1,1}]$.

No obstante, teniendo en cuenta la futura estructura a plantear, el caso base tendría que ser que «estamos en la primera fila de todas», al comienzo del problema y de la toma de decisiones.

Se verá más claro en el siguiente apartado.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Sea $T_i$ subconjunto de $T$, y $1 \leq i \leq n$.

Planteo una **tabla** de tamaño $n × n$.

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|      |      |     |        |
|      |      |     |        |
|      |      |     |        |
|      |      |     |        |

> [!cite] $A[i,j]$ representa el *$V_S$ hasta el momento actual*.

> [!attention] Markdown no permite crear tablas sin cabecera.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|  2   |  10  | 13  |   17   |
|  7   |      |     |        |
|  8   |      |     |        |
|  11  |      |     |        |

> [!cite] Se rellena la primera fila y columna atendiendo al coste de cada desplazamiento.

### Representar los casos iterativos

Fila $A[2,\_]$:

| esta | fila |  no  | cuenta |
|:----:|:----:|:----:|:------:|
| *2*  | *10* | *13* |  *17*  |
| *7*  |  10  |  14  |   19   |
| *8*  |      |      |        |
| *11* |      |      |        |

> [!cite] Observo desde cada casilla, cuál de las casillas origen suma menos valor.

Repitiendo el mismo proceso para el resto de filas, se obtiene:

Fila $A[3,\_]$:

| esta | fila |  no  | cuenta |
|:----:|:----:|:----:|:------:|
| *2*  | *10* | *13* |  *17*  |
| *7*  |  10  |  14  |   19   |
| *8*  |  10  |  12  |   13   |
| *11* |      |      |        |

Fila $A[4,\_]$:

| esta | fila |  no  | cuenta |
|:----:|:----:|:----:|:------:|
| *2*  | *10* | *13* |  *17*  |
| *7*  |  10  |  14  |   19   |
| *8*  |  11  |  12  |   13   |
| *11* |  14  |  18  |   18   |

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

| esta | fila |  no  | cuenta |
|:----:|:----:|:----:|:------:|
| *2*  | *10* | *13* |  *17*  |
| *7*  |  10  |  14  |   19   |
| *8*  |  11  |  12  |   13   |
| *11* |  14  |  18  | **18** |

> [!important] Esto indica que $V_S = 18$.
> Es decir, que la suma mínima óptima del camino es 18 puntos.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Se colocaron los valores respectivos a la primera columna y fila de la tabla, ateniendo a los desplazamientos hasta el final de las mismas (es decir, si el camino hubiera seguido toda la columna o toda la fila).
> 
> Porque al inicio, esos valores sirven como guía al resto de celdas iterativas, ya que resulta muy cómodo comprobar para cada celda cuál es la celda menor de la que puede venir.
>  ---
> Es decir:
> - $A[i,j] = T[i,j]$ cuando $i = 1 \vee j = 1$.
> - $A[i,j] = A[i-1,j] + T[i,j]$ cuando $i > 1 \wedge j = 1$.
> - $A[i,j] = A[i,j-1] + T[i,j]$ cuando $i = 1 \wedge j > 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Observo el valor de la casilla actual y los de las casillas por las que el jugador pudo avanzar para llegar hasta esta. Sumo a la casilla actual la puntuación más alta de dichas casillas.
> 
> Porque el camino está condicionado como máximo, a dos direcciones, por lo que si miro cuál es la mejor dirección de la que puede venir un jugador, en el momento de haber llegado a la casilla actual, llevará la puntuación que llevara acumulada por esa casilla, y ahora además, la de la casilla actual.
>  ---
> Es decir: $A[i,j] = \displaystyle\min\Big(A[i-1,k], A[i,j-1]\Big) + T[i,j]$ cuando $i > 1 \wedge j > 1$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
T[i,j] & \text{si} & i = 1 & \wedge & j = 1 \\
A[i-1,j] + T[i,j] & \text{si} & i > 1 & \wedge & j = 1 \\
A[i,j-1] + T[i,j] & \text{si} & i = 1 & \wedge & j > 1 \\
\displaystyle\min\Big(A[i-1,k], A[i,j-1]\Big) + T[i,j] & \text{si} & i > 1 & \wedge & j > 1 \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

Partiendo de la tabla obtenida al final del [[Camino de Coste Mínimo de un Tablero#3 - Encontrar la relación de recurrencia|paso 3]], será necesario averiguar cómo desde la *celda final*, se puede obtener la configuración de la solución pedida.

| esta | fila |  no  | cuenta |
|:----:|:----:|:----:|:------:|
| *2*  | *10* | *13* |  *17*  |
| *7*  |  10  |  14  |   19   |
| *8*  |  11  |  12  |   13   |
| *11* |  14  |  18  | **18** |

- Marcaré como ==resaltado== las *casillas del camino solución*.****
- Marcaré como **negrita** las *casillas inspeccionadas*.

|   esta    |  fila  |   no   |   cuenta   |
|:---------:|:------:|:------:|:----------:|
| ==**2**== |   10   |   13   |     17     |
|   **7**   |   10   |   14   |     19     |
|   **8**   | **11** | **12** |   **13**   |
|    11     |   14   |   18   | ==**18**== |

> [!question] ¿Cómo recorro la tabla?
> Sabiendo que el $V_S$ es el mínimo valor de la tabla A y las restricciones de movimiento de un jugador, bastaría con recorrer la tabla A al revés, empezando con la casilla con $V_S$ y siguiendo las mismas restricciones de movimiento, eligiendo siempre la casilla con menor valor.

Por tanto, como ya se conoce el camino, basta con replicarlo en el tablero para saber qué casillas lo componen:

|       |       |       |       |
|:-----:|:-----:|:-----:|:-----:|
| ==2== |   8   |   3   |   4   |
| ==5== |   3   |   4   |   5   |
| ==1== | ==2== | ==2== | ==1== |
|   3   |   4   |   6   | ==5== |

> [!success] Finalmente, se obtiene que el camino óptimo es $S = [2, 5, 1, 2, 2, 1, 5]$.
> Para ser exactos, el camino debería indicar las casillas recorridas, yo estoy usando los valores para que sea más visual.
> ---
> Las casillas recorridas: $S = \Big[(1,1), (2,1), (3,1), \dots, (4,4)\Big]$.

# Implementación en `Java`

```java
package Ejercicios.Complementarios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * PROBLEMA:
 * Dada una tabla de tamaño 'n x n' de números naturales, se pretende resolver
 * el problema de obtener un camino de la casilla (1,1) a la casilla (n, n)
 * que minimice la suma de los valores de las casillas por las que pasa.
 *
 * En cada casilla (i, j) habrá solo dos posibles movimientos:
 * ir hacia abajo (i+1, j), o hacia la derecha (i, j+1).
 *
 * Se desea resolver el problema usando programación dinámica.
 *
 * EJEMPLO:
 * Planteado abajo (porque es una tabla).
 *
 *      SOLUCIÓN: Planteado abajo (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_11 {
	
    // Datos del problema
    private static int[][] T;   // Tablero de juego
    private static int n;       // Longitud del tablero (n x n)
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[][] {
            {2, 8, 3, 4},
            {5, 3, 4, 5},
            {1, 2, 2, 1},
            {3, 4, 6, 5}
        };
		
		
        // Mostrar datos del problema
        System.out.println("Tablero:\n" + mostrarTablero(T));
		
		
        // Resolución
        rellenarTablaA();
		
        List<Integer> S = solucion(A);
		
        System.out.println("\nSolución: " + S);
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }
	
	
    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        n = T.length;
        A = new int[n][n];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = t(i,j)                              si  i = 1 && j = 1
     * A(i,j) = A(i-1,j) + t(i,j)                   si  i > 1 && j = 1
     * A(i,j) = A(i-1,j) + t(i,j)                   si  i = 1 && j > 1
     * A(i,j) = MIN[(A(i-1,j) + A(i,j-1)] + t(i,j)  si  i > 1
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    public static void bellman(int i, int j) {
        // Caso base
        if (i == 0 && j == 0) {
            A[i][j] = T[i][j];
			
        } else if (i > 0 && j == 0) {
            A[i][j] = A[i-1][j] + T[i][j];
			
        } else if (i == 0 && j > 0) {
            A[i][j] = A[i][j-1] + T[i][j];
			
        } else {
            A[i][j] = Math.min(A[i-1][j], A[i][j-1]) + T[i][j];
        }
    }
	
    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    public static List<Integer> solucion(int[][] A) {
        // Inicializar los índices
        int i = n-1, j = n-1;
		
        // Inicializar la solución
        List<Integer> S = new ArrayList<>();
        S.add(T[i][j]);                         // Añadir la celda destino
		
        // Recorrer la tabla A
        while (0 < i || 0 < j) {
            // Comprobar la dirección del paso previo
            if (0 < i && A[i-1][j] < A[i][j]) {     // Arriba
                i--;
				
            } else {                                // Izquierda
                j--;
            }
			
            // Añadir el valor de la casilla a la solución
            S.add(T[i][j]);
        }
		
        // Invertir la lista
        Collections.reverse(S);     // Se añadieron al revés
		
        return S;
    }
	
    /**
     * Calcula el valor de una solución.
     *
     * @return      Valor
     */
    private static int valor() {
        return A[n-1][n-1];
    }
	
	
    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------
	
    /**
     * Genera una representación de un tablero.
     *
     * @param tablero    Tablero a mostrar
     *
     * @return Tablero como una tabla
     */
    public static String mostrarTablero(int[][] tablero) {
        StringBuilder sb = new StringBuilder();
		
        // Recorrer el tablero
        for (int[] f : tablero) {
            // Recorrer la fila
            for (int i : f) {
                sb.append(String.format("%3d", i));
            }
			
            if (f != tablero[tablero.length-1]) {
                sb.append("\n");
            }
        }
		
        return sb.toString();
    }
	
    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Cabeceras
        sb.append("\t");
		
        for (int cabecera = 1; cabecera <= n; cabecera++) {
            sb.append(cabecera).append("\t");
        }
		
        // Filas
        for (int fil = 0; fil < n; fil++) {
            sb.append("\n").append(fil+1).append("\t");
			
            for (int col = 0; col < n; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
