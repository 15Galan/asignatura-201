---
aliases: ["Ejercicio Complementario 10"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Central Nuclear]]

# Problema

Tienes la misión de controlar la calefacción central de una estación antártica; para ello has recopilado información sobre las preferencias de tus compañeros en cada momento del día:

Sea $c_{i,j}$ el confort global de los miembros de la base si a la hora $i$ ($1 \leq i \leq H$) la temperatura es de $j$ grados.

Al comienzo del día la temperatura se fija automáticamente a $T$ grados y a partir de ahí puedes modificarla en como máximo $K$ grados en cada paso/hora (pero nunca puedes salirte del
rango $[T_\min, T_\max]$.

> [!todo] Debes determinar qué temperatura tener en cada momento de manera que se maximice la satisfacción (confort) de toda la base.

# Solución
## 1 - Dimensiones relevantes

Datos relevantes:

- El día empieza en la temperatura $T$.
- Se puede modificar $T \pm K$ grados/hora como máximo, y sin salirse de $[T_\min, T_\max]$.

Planteadas por el problema:

- $c_{i,j}$: confort global a la hora $i$ con la temperatura $j$.
- $H$: número de horas.
- $T$: temperatura por defecto (en grados).
- $K$: grados máximos a cambiar cada hora.

Planteadas por mí:

- $S = \{s_1, s_2, \dots, s_H\}$: vector solución.
- $s_i$: temperatura a tener en la hora $i$.
- $V_S$: máxima satisfacción (confort acumulado) obtenida.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

#todo

## 3 - Encontrar la relación de recurrencia

### Plantear un ejemplo particular

Sea una estación donde se trabajan $H = 5$ horas, cuya temperatura inicial es $T = 3$ grados y que permite variar $K = 1$ grado cada hora, donde los límites de temperatura son $[T_\min, T_\max] = [0,6]$, se realizó un histórico para conocer el confort del personal:

|  $c$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |   0   |   0   |   1   |   2   |   3   |   3   |   3   |
| **2** |   1   |   1   |   2   |   3   |   1   |   4   |   4   |
| **3** |   3   |   3   |   4   |   4   |   4   |   3   |   3   |
| **4** |   3   |   3   |   3   |   3   |   1   |   1   |   1   |
| **5** |   3   |   2   |   2   |   1   |   1   |   0   |   0   |

### Definir la estructura a utilizar

Se usará una **tabla** de tamaño $H+1$ x $|[T_\min, T_\max]|$.

- $H+1$ para el caso base en el que el horario de trabajo aún no empezó.

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |       |       |       |       |       |       |       |
| **1** |       |       |       |       |       |       |       |
| **2** |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |
| **5** |       |       |       |       |       |       |       |

> [!cite] $A[i,j]$ representa la satisfacción (confort acumulado) del equipo.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |   -   |   -   |   -   |   -   |   -   |   -   |   -   |
| **1** |       |       |       |       |       |       |       |
| **2** |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |
| **5** |       |       |       |       |       |       |       |

### Representar los casos iterativos

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -   |   -   |   1   |   2   |   3   |   -   |   -   |
| **2** |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |
| **5** |       |       |       |       |       |       |       |

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |
| **2** |   -   |   2   |   4   |   6   |   4   |   7   |   -   |
| **3** |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |
| **5** |       |       |       |       |       |       |       |

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -   |   -   |   1   |   2   |   3   |   -   |   -   |
| **2** |   -   |   2   |   4   |   6   |   4   |   7   |   -   |
| **3** |   5   |   7   |  10   |  10   |  11   |  10   |  10   |
| **4** |       |       |       |       |       |       |       |
| **5** |       |       |       |       |       |       |       |

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -   |   -   |   1   |   2   |   3   |   -   |   -   |
| **2** |   -   |   2   |   4   |   6   |   4   |   7   |   -   |
| **3** |   5   |   7   |  10   |  10   |  11   |  10   |  10   |
| **4** |  10   |  13   |  13   |  14   |  12   |  12   |  11   |
| **5** |       |       |       |       |       |       |       |

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -   |   -   |   1   |   2   |   3   |   -   |   -   |
| **2** |   -   |   2   |   4   |   6   |   4   |   7   |   -   |
| **3** |   5   |   7   |  10   |  10   |  11   |  10   |  10   |
| **4** |  10   |  13   |  13   |  14   |  12   |  12   |  11   |
| **5** |  12   |  15   |  16   |  15   |  15   |  12   |  12   |

### Obtener la estructura completa

|  $A$  | **0**  | **1** | **2**  | **3** | **4** | **5** | **6** |
|:-----:|:------:|:-----:|:------:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*   |  *-*  |  *-*   |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -    |   -   |   1    |   2   |   3   |   -   |   -   |
| **2** |   -    |   2   |   4    |   6   |   4   |   7   |   -   |
| **3** |   5    |   7   |   10   |  10   |  11   |  10   |  10   |
| **4** |   10   |  13   |   13   |  14   |  12   |  12   |  11   |
| **5** | **16** |  15   | **16** |  15   |  15   |  12   |  12   |

> [!important] Para este ejemplo, $V_S = 16$.

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Rellenando la primera fila con $0$ (simbolizado como `-`).
> 
> Porque sirve como inicio del problema, donde la temperatura inicial es $T$.
>  ---
> Es decir: $A[i,j] = 0$ cuando $i = 0$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Como para cada fila las opciones se expanden hasta $j-K$ y $j+K$ columnas, se observa para cada $A[i,j]$ a rellenar, el valor de $A[i-1, j-K]$ hasta $A[i-1,j+K]$ (los límites anteriores posibles de cambio de temperatura). Además, una vez calculada la mejor satisfacción anterior, le sumo el confort que produce la celda.
> 
> Porque si desde $A[i,j]$ se puede avanzar a $A[i+1, j-K] \dots A[i+1,j+K]$, entonces el propio valor de $A[i,j]$ ha tenido que venir de $A[i-1, j-K] \dots A[i-1,j+K]$.
> 
> También debe tenerse encuenta que $j \pm K \in [T_\min, T_\max]$, por lo que el recorrido de búsqueda variará dependiendo de si la celda recorrida está en un límite de la proia tabla o no.
>  ---
> Es decir $A[i,j] = \displaystyle\max_{\max(T_\min, j-K) \leq k \leq \max(j+K, T_\max)} \Big( A[i-1,k] \Big) + c[i,j]$ cuando $i > 0$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 1 \\
\displaystyle\max_{\max(T_\min, j-K) \leq k \leq \max(j+K, T_\max)} \Big( A[i-1,k] \Big) + c[i,j] & \text{si} & i > 1 \\
\end{array}
\right.
> $$

> [!note] [[Ecuación de Bellman]] para el problema planteado (ejercicio resuelto en el [[Libro de Ejercicios Resueltos|Libro]]):
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 1 \\
\displaystyle\max_{\max(T_\min, j-K) \leq k \leq \max(j+K, T_\max)} \Big( A[i-1,k] + c[i,k] \Big) & \text{si} & i > 1 \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

> [!attention] El ejemplo que planteé produce 2 soluciones posibles, mostraré ambas.

|  $A$  | **0**  | **1** | **2**  | **3** | **4** | **5** | **6** |
|:-----:|:------:|:-----:|:------:|:-----:|:-----:|:-----:|:-----:|
| **0** |  *-*   |  *-*  |  *-*   |  *-*  |  *-*  |  *-*  |  *-*  |
| **1** |   -    |   -   |   1    |   2   |   3   |   -   |   -   |
| **2** |   -    |   2   |   4    |   6   |   4   |   7   |   -   |
| **3** |   5    |   7   |   10   |  10   |  11   |  10   |  10   |
| **4** |   10   |  13   |   13   |  14   |  12   |  12   |  11   |
| **5** | **16** |  15   | **16** |  15   |  15   |  12   |  12   |

- Marcaré como ==resaltado== las *temperaturas*.
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

**Solución óptima 1/2:**

|  $A$  | ==**0**== | ==**1**== | ==**2**== | ==**3**== | ==**4**== | **5** | **6** |
|:-----:|:---------:|:---------:|:---------:|:---------:|:---------:|:-----:|:-----:|
| **0** |    *-*    |    *-*    |    *-*    |    *-*    |    *-*    |  *-*  |  *-*  |
| **1** |     -     |     -     |     1     |     2     |   **3**   |   -   |   -   |
| **2** |     -     |     2     |     4     |   **6**   |     4     |   7   |   -   |
| **3** |     5     |     7     |  **10**   |    10     |    11     |  10   |  10   |
| **4** |    10     |  **13**   |    13     |    14     |    12     |  12   |  11   |
| **5** |  **16**   |    15     |    16     |    15     |    15     |  12   |  12   |

> [!success] $S = \{4, 3, 2, 1, 0\}$.

**Solución óptima 2/2:**

|  $A$  | **0** | **1** | ==**2**== | ==**3**== | ==**4**== | ==**5**== | **6** |
|:-----:|:-----:|:-----:|:---------:|:---------:|:---------:|:---------:|:-----:|
| **0** |  *-*  |  *-*  |    *-*    |    *-*    |    *-*    |    *-*    |  *-*  |
| **1** |   -   |   -   |     1     |     2     |   **3**   |     -     |   -   |
| **2** |   -   |   2   |     4     |     6     |     4     |   **7**   |   -   |
| **3** |   5   |   7   |    10     |    10     |  **11**   |    10     |  10   |
| **4** |  10   |  13   |    13     |  **14**   |    12     |    12     |  11   |
| **5** |  16   |  15   |  **16**   |    15     |    15     |    12     |  12   |

> [!success] $S = \{4, 5, 4, 3, 2\}$.

> [!question] ¿Cómo recorro la tabla?
> Una vez obtenida la tabla completa, el valor de satisfacción máximo se encuentra en la última fila, siendo el máximo valor de esta.
> Bastará con recorrer la tabla hacia atrás eligiendo el valor máximo entre los límites disponibles que ofrezca $K$.
> 
> Si hay más de un valor máximo (iguales), refleja que puede haber más de una solución que produzca una satisfacción máxima.

# Implementación en `Java`

```java
package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Tienes la misión de controlar la calefacción central de una estación
 * antártica; para ello has recopilado información sobre las preferencias
 * de tus compañeros en cada momento del día:
 *
 * Sea 'c(i,j)' el confort global de los miembros de la base si
 * a la hora 'i' (1 <= i <= H) la temperatura es de 'j' grados.
 *
 * Al comienzo del día la temperatura se fija automáticamente a 'T' grados
 * y a partir de ahí puedes modificarla en como máximo 'K' grados en cada
 * paso/hora (pero nunca puedes salirte del rango [T_min, T_max].
 *
 * Debes determinar qué temperatura tener en cada momento de manera que
 * se maximice la satisfacción (confort) de toda la base.
 *
 *
 * EJEMPLO:
 * Sea una estación donde se trabajan H = 5 horas, cuya temperatura inicial
 * es T = 3 grados y que permite variar K = 1 grados cada hora, donde los
 * límites de temperatura son [T_min, T_max] = [0, 6], se realizó un histórico
 * para conocer el confort del personal:
 *
 * - Propuesta en la resolución (porque es una tabla).
 *
 *      SOLUCIÓN: Propuesta en la resolución (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_10 {
    // Datos del problema
    private static int H;       // Número de horas
    private static int T;       // Temperatura inicial
    private static int K;       // Número de grados que se pueden variar cada hora
	
    private static int T_min;   // Temperatura mínima
    private static int T_max;   // Temperatura máxima
	
    private static int[][] c;   // Confort/hora de cada miembro de la base
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     */
    public static void main(String[] args) {
        // Datos del problema
        H = 5; T = 3; K = 1;
        T_min = 0; T_max = 6;
		
        c = new int[][]{
            {0, 0, 1, 2, 3, 3, 3},
            {1, 1, 2, 3, 1, 4, 4},
            {3, 3, 4, 4, 4, 3, 3},
            {3, 3, 3, 3, 1, 1, 1},
            {3, 2, 2, 1, 1, 0, 0}
        };
		
		
        // Mostrar datos del problema
        System.out.println("H: " + H);
        System.out.println("T: " + T);
        System.out.println("K: " + K);
        System.out.println("Límites: [" + T_min + ", " + T_max + "]");
        System.out.println("Confort:\n" + mostrarTabla(c));
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---\n");
        System.out.println("Tabla A:\n" + mostrarTabla(A));
		
        // Información de depuración
        // System.out.println("\n\n--- Información de depuración ---\n");
        // System.out.println(debug);
    }
	
	
    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------
	
    /**
     * Rellena la tabla de programación dinámica.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[H+1][T_max+1];
        debug = "";
		
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= T_max; j++) {
                A[i][j] = 0;
            }
        }
		
        for (int i = 1; i <= H; i++) {
            for (int j = T_min; j <= T_max; j++) {
                debug += bellman(i, j);
            }
        }
    }
	
    /**
     * Resuelve el problema de Bellman.
     *
     * @param i     Hora actual.
     * @param j     Temperatura actual.
     */
    private static String bellman(int i, int j) {
        // Caso base
        if (i == 0) {
            A[i][j] = c[i][j];
			
        // Caso iterativo
        } else {
            int max = -1;
            int inf = Math.max(T_min, j-K);     // Límite inferior de la fila
            int sup = Math.min(j+K, T_max);     // Límite superior de la fila
			
            // Encontrar el máximo entre los límites
            for (int k = inf; k <= sup; k++) {
                int valor = A[i-1][k] + c[i-1][j];
				
                if (valor > max) {
                    max = valor;
                }
            }
			
            A[i][j] = max;
        }
		
        // Debug
        return sb + "\n" + mostrarTabla(A) + "\n\n";
    }
	
    private static int[] solucion() {
        // Inicializar los índices
        int j = -1;
		
        // Encontrar la posición del valor máximo
        int max = -1;
        for (int k = T_min; k <= T_max; k++) {
            int valor = A[H][k];
			
            if (valor > max) {
                max = valor;
                j = k;
            }
        }
		
        // Inicializar la solución
        int[] S = new int[H];
		
        // Recorrer la tabla A
        for (int i = H; 0 < i; i--) {
            max = -1;                           // Reciclo la variable
            int inf = Math.max(T_min, j-K);     // Límite inferior de la fila
            int sup = Math.min(j+K, T_max);     // Límite superior de la fila
			
            S[i-1] = j;     // Añado el valor porque ya tengo el 'j' anterior
			
            for (int k = inf; k <= sup; k++) {
                int valor = A[i-1][k];
				
                if (valor > max) {
                    max = valor;
                    j = k;
                }
            }
        }
		
        return S;
    }
	
    private static int valor() {
        int valor = 0;
		
        for (int j = 0; j < T_max; j++) {
            if (A[H][j] > valor) {
                valor = A[H][j];
            }
        }
		
        return valor;
    }
	
	
    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------
	
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Cabeceras
        sb.append("\t");
		
        for (int i = 0; i < tabla[0].length; i++) {
            sb.append(i).append("\t");
        }
		
        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append("\n").append(i).append("\t");
			
            for (int j = 0; j < tabla[i].length; j++) {
                if (tabla[i][j] == 0) {
                    sb.append("-");
					
                } else {
                    sb.append(tabla[i][j]);
                }
				
                sb.append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
