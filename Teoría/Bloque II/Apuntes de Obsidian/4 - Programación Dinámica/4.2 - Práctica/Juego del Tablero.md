---
aliases: ["Ejercicio 08"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Camino de Coste Mínimo de un Tablero]]

# Problema

Queremos jugar a un juego en el que tenemos un tablero $n × n$ donde en cada casilla aparece un número natural.

El juego consiste en elegir una casilla de la  ́ultima fila ($n$) y movernos desde ella hasta una casilla de la primera fila ($1$), donde los únicos movimientos legales consisten en moverse, sin salirse del tablero, de una casilla a una de las tres casillas de la fila superior alcanzables en vertical (↑) o en diagonal (↖ o ↗).

La cantidad ganada será la suma de los valores en las casillas del tablero por las que pasamos en este recorrido.

> [!todo] Encontrar el camino óptimo y su puntuación.

## Ejemplo del problema

Sea el tablero siguiente:

|       |       |     |     |
|:-----:|:-----:|:---:|:---:|
| **1** |   4   |  8  |  3  |
| **1** |   5   |  2  | 10  |
| **8** |   2   |  7  |  9  |
|   3   | **5** |  2  |  1  |

> [!info] Una solución es $S = [5, 8, 1, 1]$.
> Esta no es la solución óptima.

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

No obstante, teniendo en cuenta la futura estructura a plantear, el caso base tendría que ser que «estamos en la primera fila de todas», al comienzo del juego y de la toma de decisiones.

Se verá más claro en el siguiente apartado.

## 3 - Encontrar la relación de recurrencia

> [!attention] Los índices del tablero son diferentes a lo habitual.
> Normalmente, los problemas planteados indican tablas que: se rellenan de arriba a abajo y de izquierda a derecha, empezando en los índices $(0,0)$; sin embargo, este problema indica que el tablero se rellenó de izquierda a derecha pero de abajo a arriba,  empezando los índices en $(1,1)$.

### Plantear un ejemplo particular

Usaré el ejemplo planteado por el problema.

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

Como se indicó anteriormente, el caso base es que el tablero sea de tamaño $1 × 1$.

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|      |      |     |        |
|      |      |     |        |
|      |      |     |        |
|  3   |  5   |  2  |   1    |

> [!cite] No hay siguiente casilla a la que avanzar, por lo que los valores son iguales.

### Representar los casos iterativos

Fila $A[2,\_]$:

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|      |      |     |        |
|      |      |     |        |
|  13  |  7   | 12  |   11   |
| *3*  | *5*  | *2* |  *1*   |

> [!cite] Observo desde cada casilla, cuál de las casillas alcanzables suma más valor.
> $13 = \max(8 + 3, 8 + 5)$
> $7 = \max(2 + 3, 2 + 5, 2 + 2)$
> $2 = \max(7 + 5, 7 + 2, 7 + 1)$
> $11 = \max(9 + 2, 9 + 1)$

Repitiendo el mismo proceso para el resto de filas, se obtiene:

Fila $A[3,\_]$:

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|      |      |     |        |
|  14  |  18  | 14  |   22   |
|  13  |  7   | 12  |   11   |
| *3*  | *5*  | *2* |  *1*   |

Fila $A[4,\_]$:

| esta | fila | no  | cuenta |
|:----:|:----:|:---:|:------:|
|  19  |  22  | **30**  |   25   |
|  14  |  18  | 14  |   22   |
|  13  |  7   | 12  |   11   |
| *3*  | *5*  | *2* |  *1*   |
^51deda

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

![[Juego del Tablero#^51deda]]

> [!important] Esto indica que, *para el ejemplo del problema*, $V_S = 30$.
> Es decir, que la puntuación óptima del tablero es 30 puntos.

> [!success] El camino óptimo ofrece una puntuación de $V_S = 30$ puntos.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Se copiaron los elementos de la primera fila del tablero ($T[1,\_]$).
> 
> Porque son todas las opciones disponibles para un jugador en el primer turno, ya que puede empezar en cualquier casilla de esa fila.
> 
> ---
> Es decir: $A[i,j] = T[i,j]$ cuando $i = 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Observo el valor de la casilla actual y los de las casillas por las que el jugador pudo avanzar para llegar hasta esta. Sumo a la casilla actual la puntuación más alta de dichas casillas.
> 
> Porque el camino está condicionado como máximo, a tres direcciones, por lo que si miro cuál es la dirección mejor de la que puede venir un jugador, en el momento de haber llegado a la casilla actual, llevará la puntuación que llevara acumulada por esa casilla, y ahora además, la de la casilla actual.
> 
> ---
> Es decir: $A[i,j] = \displaystyle\max_{j-1 \leq k \leq j+1}\Big(A[i+1,k] + T[i,j]\Big)$ cuando $i > 1$.

> [!attention] Hay que tener en cuenta los límites del tablero (y la tabla A).
> Por eso, en la [[Ecuación de Bellman]] aparece este último caso dividido en 3:
> - Cuando la casilla está en el borde izquierdo.
> - Cuando la casilla no está en un borde.
> - Cuando la casilla está en el borde derecho.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
T[i,j] & \text{si} & i = 1 \\
\displaystyle\max_{j \leq k \leq j+1}\Big(A[i+1,k] + T[i,j]\Big) & \text{si} & i > 1 & \wedge & j = 1 \\
\displaystyle\max_{j-1 \leq k \leq j}\Big(A[i+1,k] + T[i,j]\Big) & \text{si} & i > 1 & \wedge & j = n \\
\displaystyle\max_{j-1 \leq k \leq j+1}\Big(A[i+1,k] + T[i,j]\Big) & \text{si} & i > 1 \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

Partiendo de la tabla obtenida al final del [[Juego del Tablero#3 - Encontrar la relación de recurrencia|paso 3]], será necesario averiguar cómo desde la *celda final*, se puede obtener la configuración de la solución pedida.

![[Juego del Tablero#^51deda]]

- Marcaré como ==resaltado== las *casillas del camino solución*.****
- Marcaré como **negrita** las *casillas inspeccionadas*.

| esta |    fila     |     no     |   cuenta   |
|:----:|:-----------:|:----------:|:----------:|
|  19  |     22      | ==**30**== |     25     |
|  14  |   **18**    |   **14**   | ==**22**== |
|  13  |      7      | ==**12**== |   **11**   |
| *3*  | ==***5***== |  ***2***   |  ***1***   |

> [!question] ¿Cómo recorro la tabla?
> Sabiendo que el $V_S$ es el máximo valor de la última fila de la tabla A y las restricciones de movimiento de un jugador, bastaría con recorrer la tabla A al revés, empezando con la casilla con $V_S$ y siguiendo las mismas restricciones de movimiento.

Por tanto, como ya se conoce el camino, basta con replicarlo en el tablero para saber qué casillas lo componen:

|     |       |       |        |
|:---:|:-----:|:-----:|:------:|
|  1  |   4   | ==8== |   3    |
|  1  |   5   |   2   | ==10== |
|  8  |   2   | ==7== |   9    |
|  3  | ==5== |   2   |   1    |

> [!success] Finalmente, se obtiene que el camino óptimo es $S = [5, 7, 10, 8]$.
> Para ser exactos, el camino debería indicar las casillas recorridas, yo estoy usando los valores para que sea más visual.
> ---
> Las casillas recorridas: $S = \Big[(1,2), (2,3), (3,4), (4,3)\Big]$.

# Implementación en `Java`

```java
package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * Queremos jugar a un juego en el que se tiene un tablero 'n x n' donde
 * en cada casilla aparece un número natural.
 * El juego consiste en elegir una casilla de la última fila 'n' y ascender
 * mediante los movimientos {↖, ↑, ↗} hasta llegar a una casilla de la
 * primera fila.
 * La cantidad ganada será la suma de los valores de las casillas
 * recorridas.
 *
 * Determinar cómo hay que jugar para maximizar los puntos.
 */
public class Ejercicio_08 {
	
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
			{1, 4, 8,  3},
			{1, 5, 2, 10},
			{8, 2, 7,  9},
			{3, 5, 2,  1}
        };
		
		
        // Mostrar datos del problema
        System.out.println("Tablero:\n" + mostrarTablero(T));
		
		
        // Resolución
        rellenarTablaA();
		
        int[] S = solucion();
		
        System.out.println("\nSolución: " + Arrays.toString(S));
        System.out.println("\tValor: " + valor(S));
		
		
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
        for (int i = n-1; i >= 0; i--) {    // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = t(i,j)                                      si  i = n
     * A(i,j) = MAX[j <= k <= j+1][(A(i+1,k)) + t(i,j)]     si  <...>
     * A(i,j) = MAX[j-1 <= k <= j][(A(i+1,k)) + t(i,j)]     si  <...>
     * A(i,j) = MAX[j-1 <= k <= j+1][(A(i+1,k)) + t(i,j)]   si  <...>
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    public static void bellman(int i, int j) {
        // Caso base
        if (i == n-1) {
            A[i][j] = T[i][j];
			
        // Caso iterativo
        } else {
            int max = 0;
			
            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está dentro del tablero
                if (0 <= k && k < n) {
                    int aux = A[i+1][k] + T[i][j];
					
                    if (aux > max) {
                        max = aux;
                    }
                }
            }
			
            A[i][j] = max;
        }
    }
	
	/**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @return      Solución óptima
     */
    public static int[] solucion() {
        // Inicializar los índices
        int c = 0;                      // Columna del tablero
		
        // Guardar la columna del máximo valor posible
        for (int j = 0; j < n; j++) {
            if (A[0][c] < A[0][j]) {
                c = j;                  // Se actualiza la columna
            }                           // del valor máximo anterior
        }
		
        // Inicializar la solución
        int[] solucion = new int[n];
        solucion[n-1] = T[0][c];        // Casilla mejor puntuada
		
        // Recorrer la tabla A
        for (int i = 1; i < n; i++) {
            int j = c;
			
            for (int k = j-1; k <= j+1; k++) {
                // Si 'k' está en del tablero (las 3 sentencias de la E.B)
                if (0 <= k && k < n) {
                    // Guardar el valor máximo de los posibles
                    if (A[i][j] < A[i][k]) {
                        solucion[(n - 1) - i] = T[i][k];
                        c = k;
                    }
                }
            }
        }
		
        return solucion;
    }
	
	/**
     * Calcula el valor de una solución.
     *
     * @param S     Solución
     *
     * @return      Valor
     */
    private static int valor(int[] S) {
        int valor = 0;
		
        for (int s : S) {
            valor += s;
        }
		
        return valor;
    }
}
```

> [!cite] A continuación dejo un método propio para visualizar la tabla.
> No es necesario, pero lo uso para *debbugear* mi implementación.

```java
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
		sb.append("\n").append(n-fil).append("\t");
		
		for (int col = 0; col < n; col++) {
			sb.append(tabla[fil][col]).append("\t");
		}
	}
	
	return sb.toString();
}
```
