---
tags: [tema4/examen, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Embarcaderos]]
> - [[Motas WiFi]]

# Problema

Dado un vector de números naturales $\{a1, \dots, a_n\}$, donde cada elemento $a_i$ representa el número máximo de pasos que se pueden avanzar desde ese elemento.

Si un elemento es $0$, entonces no puede moverse a través de ese elemento.

> [!todo] Diseñar e implementar un algoritmo para devolver el número mínimo de saltos para llegar al final del vector (comenzando desde el primer elemento).

## Ejemplo del problema

Sea el vector $\{1, 3, 5, 9, 4, 3, 8, 1, 2, 6, 9\}$.

> [!info] Una solución válida podría ser $V_S = 3$.
> Saltar de la posición #1 a la posición #2, de ahí a la posición #4 y de ahí a la última posición.

# Solución

## 1 - Dimensiones relevantes

Planteadas por el enunciado:
- $V = \{a_1, a_2, \dots, a_n\}$: vector de números naturales.
- $n$: cantidad de números naturales.

Planteadas por mí:
- $V_S$: valor de la solución (número mínimo de saltos a realizar).

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

- No se puede saltar del elemento $i$ al elemento $i$, ya se está en él.
- Tampoco se puede saltar de un elemento $i$ a uno $j$ siempre que $j < i$.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Se usará una **tabla** de tamaño $n$ x $n$.

| $A$    | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
| ------ | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ------ | ------ |
| **1**  |       |       |       |       |       |       |       |       |       |        |        |
| **2**  |       |       |       |       |       |       |       |       |       |        |        |
| **3**  |       |       |       |       |       |       |       |       |       |        |        |
| **4**  |       |       |       |       |       |       |       |       |       |        |        |
| **5**  |       |       |       |       |       |       |       |       |       |        |        |
| **6**  |       |       |       |       |       |       |       |       |       |        |        |
| **7**  |       |       |       |       |       |       |       |       |       |        |        |
| **8**  |       |       |       |       |       |       |       |       |       |        |        |
| **9**  |       |       |       |       |       |       |       |       |       |        |        |
| **10** |       |       |       |       |       |       |       |       |       |        |        |
| **11** |       |       |       |       |       |       |       |       |       |        |        |

> [!cite] $A[i,j]$ representa el número de saltos desde la posición $i$ hasta la posición $j$.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |   0   |       |       |       |       |       |       |       |       |        |        |
| **2**  |   -   |   0   |       |       |       |       |       |       |       |        |        |
| **3**  |   -   |   -   |   0   |       |       |       |       |       |       |        |        |
| **4**  |   -   |   -   |   -   |   0   |       |       |       |       |       |        |        |
| **5**  |   -   |   -   |   -   |   -   |   0   |       |       |       |       |        |        |
| **6**  |   -   |   -   |   -   |   -   |   -   |   0   |       |       |       |        |        |
| **7**  |   -   |   -   |   -   |   -   |   -   |   -   |   0   |       |       |        |        |
| **8**  |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   0   |       |        |        |
| **9**  |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   0   |        |        |
| **10** |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   0    |        |
| **11** |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -   |   -    |   0    |

### Representar los casos iterativos

Fila $A[10,\_]$:

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |  *0*  |       |       |       |       |       |       |       |       |        |        |
| **2**  |  *-*  |  *0*  |       |       |       |       |       |       |       |        |        |
| **3**  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |       |        |        |
| **4**  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |        |        |
| **5**  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |        |        |
| **6**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |        |        |
| **7**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |        |        |
| **8**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |        |        |
| **9**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |        |        |
| **10** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*   |   1    |
| **11** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *0*   |

> [!cite] Acción
> Saltar de $a_{10} = 6$ a $a_{11} = 9$ directamente.
> Porque estando en $a_{10}$ puedo saltar $6$ posiciones y $a_{11}$ está a menos distancia.

Fila $A[9,\_]$:

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |  *0*  |       |       |       |       |       |       |       |       |        |        |
| **2**  |  *-*  |  *0*  |       |       |       |       |       |       |       |        |        |
| **3**  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |       |        |        |
| **4**  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |        |        |
| **5**  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |        |        |
| **6**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |        |        |
| **7**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |        |        |
| **8**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |        |        |
| **9**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1    |   1    |
| **10** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*   |   1    |
| **11** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *0*   |

> [!cite] Acción
> Saltar de $a_{9} = 2$ a $a_{10} = 6$ directamente.
> Porque estando en $a_{9}$ puedo saltar $2$ posiciones y $a_{10}$ está a menos distancia.
> 
> Saltar de $a_{9} = 2$ a $a_{11} = 9$ directamente.
> Porque estando en $a_{9}$ puedo saltar $2$ posiciones y $a_{11}$ está a menos distancia.

Fila $A[8,\_]$:

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |  *0*  |       |       |       |       |       |       |       |       |        |        |
| **2**  |  *-*  |  *0*  |       |       |       |       |       |       |       |        |        |
| **3**  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |       |        |        |
| **4**  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |        |        |
| **5**  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |        |        |
| **6**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |        |        |
| **7**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |        |        |
| **8**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   2    |   2    |
| **9**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1    |   1    |
| **10** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*   |   1    |
| **11** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *0*   |

> [!cite] Acción
> Saltar de $a_{8} = 1$ a $a_{9} = 2$ directamente.
> Porque estando en $a_{8}$ puedo saltar $1$ posición y $a_{9}$ está justo a esa distancia.
> 
> Saltar de $a_{8} = 1$ a $a_{10} = 6$ en 2 saltos.
> Porque estando en $a_{9}$ puedo saltar $1$ posición, pero $a_{10}$ está a más distancia, por lo que es necesario evaluar el mínimo de saltos desde todas las $a_i$ anteriores que saltaban a $a_j$: esto resulta en el salto actual a $a_j$ y los saltos de $a_j$ a $a_{10}$.
> 
> Saltar de $a_{8} = 1$ a $a_{11} = 9$ en 2 saltos.
> Porque estando en $a_{8}$ puedo saltar $1$ posición, pero $a_{11}$ está a más distancia, por lo que es necesario evaluar el mínimo de saltos desde todas las $a_i$ anteriores que saltaban a $a_j$: esto resulta en el salto actual a $a_j$ y los saltos de $a_j$ a $a_{11}$.

Fila $A[7,\_]$:

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |  *0*  |       |       |       |       |       |       |       |       |        |        |
| **2**  |  *-*  |  *0*  |       |       |       |       |       |       |       |        |        |
| **3**  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |       |        |        |
| **4**  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |       |        |        |
| **5**  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |       |        |        |
| **6**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |       |       |       |        |        |
| **7**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1    |   1    |
| **8**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   2    |   2    |
| **9**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1    |   1    |
| **10** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*   |   1    |
| **11** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *0*   |

$\vdots$

### Obtener la estructura completa

|  $A$   | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** | **10** | **11** |
|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1**  |  *0*  |   1   |   2   |   2   |   2   |   3   |   3   |   3   |   3   |   3    | **3**  |
| **2**  |  *-*  |  *0*  |   1   |   1   |   1   |   2   |   2   |   2   |   2   |   2    |   2    |
| **3**  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1   |   1   |   1   |   2   |   2    |   2    |
| **4**  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1   |   1   |   1   |   1    |   1    |
| **5**  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1   |   1   |   2    |   2    |
| **6**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1   |   2    |   2    |
| **7**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   1   |   1    |   1    |
| **8**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1   |   2    |   2    |
| **9**  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*  |   1    |   1    |
| **10** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *0*   |   1    |
| **11** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *0*   |

> [!success] Se realizan $3$ saltos como mínimo.

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando $0$s cuando $i = j$ y `-` cuando $j < i$.
>
> Porque no se puede saltar de una posición a la misma, ni a ninguna anterior.
>
>  ---
> Es decir: $A[i,j] = 0$ cuando $i \geq j$ (aunque se represente diferente cuando $j < i$).

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Observaba si desde $a_i$ podía saltar hasta $a_j$ directamente, posible solo cuando $i + a_i$ es mayor que la distancia a alcanzar, que es $j$.
> 
> Si eso no pasa, entonces debo dar más de un salto desde $a_i$ hasta $a_j$, pasando por alguna(s) posición(es) intermedia(s). Observo qué cantidad de saltos existen para cualquier $a_i$ anterior que alcance $a_j$ y escojo la mínima, añadiendo por supuesto el salto actual.
>
>  ---
> Es decir: $A[i,j] = 1$ cuando $s \geq j$ y $A[i,j] = \min_{i \lt k \leq s}\Big(A[k,j] + 1\Big)$ cuando $s < j$.
> - donde $s = i + a_i$, que representa el salto más largo posible para $i$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = j \\
1 & \text{si} & s \geq j \\
A[i,j] = \min_{i \lt k \leq s}\Big(A[k,j] + 1\Big) & \text{si} & s \lt j \\
\end{array}
\qquad \text{donde } s = i + a_i
\right.
> $$

# Implementación en `Java`

```java
package Examenes.Curso_19_20;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Dado un vector de números naturales {a_1, ..., a_n}, donde cada elemento 'a_i'
 * representa el número máximo de pasos que se pueden avanzar desde ese elemento.
 *
 * Si un elemento es 0, entonces no puede moverse a través de ese elemento.
 *
 * EJEMPLO:
 * Sea el vector {1, 3, 5, 9, 4, 3, 8, 1, 2, 6, 9}.
 *
 *      SOLUCIÓN: 3 es una solución válida (#0 -> #1 -> #3 -> #10).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Febrero {

    // Datos del problema
    private static int[] V;     // Vector de números naturales
    private static int n;       // Número de elementos del vector

    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación dinámica
    private static String debug;    // Mensaje de depuración


    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        V = new int[]{1, 3, 5, 9, 4, 3, 8, 1, 2, 6, 9};
        n = V.length;


        // Mostrar los datos del problema
        System.out.println("V: " + Arrays.toString(V));


        // Resolución
        rellenarTablaA();

        System.out.println("Valor: " + valor());


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
    public static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[n][n];
        debug = "";

        // Aplicar la ecuación de Bellman
        for (int i = n-1; 0 <= i; i--) {       // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                debug += bellman(i, j);
            }
        }
    }

    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                   si  i = j
     * A(i,j) = 1                                   si  i + V(i) >= j
     * A(i,j) = MIN[i < k <= i+V(i)](1 + A(k,j))    si  i + V(i) < j
     *
     * @param i Fila de la tabla 'A'
     * @param j Columna de la tabla 'A'
     */
    private static String bellman(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nA(").append(i).append(", ").append(j).append("):\t");

        // Caso base 1
        if (i == j) {
            A[i][j] = 0;

        // Caso base 2
        } else if (i + V[i] >= j) {
            A[i][j] = 1;

        // Caso iterativo
        } else {
            int min = Integer.MAX_VALUE;
            int salto = i + V[i];

            for (int k = i+1; k <= salto; k++) {
                int valor = 1 + A[k][j];

                if (valor < min) {
                    min = valor;
				}
            }

            A[i][j] = min;
        }
    }

    /**
     * Devuelve el valor de la solución.
     */
    public static int valor() {
        return A[0][n-1];
    }


    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------

    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();

        // Cabeceras
        for (int j = 0; j < tabla[0].length; j++) {
            sb.append("\t").append(j);
        }

        sb.append("\n");

        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append(i).append("\t");

            // Valores de las columnas
            for (int j = 0; j < tabla[0].length; j++) {

                // Mostrar los casos no-relevantes como '-'
                if (i > j) {
                    sb.append("-");

                } else {
                    sb.append(tabla[i][j]);
                }

                sb.append("\t");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
```
