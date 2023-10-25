---
aliases: ["Ejercicio 05"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Motas WiFi]]

# Problema

A lo largo de un río en el que sólo se puede navegar en una dirección se encuentran $n$ embarcaderos.

Conocemos el coste $T[i,j]$ para ir directamente del embarcadero $i$ al embarcadero $j$ (y que puede ser más barato o más caro que un recorrido entre $i$ y $j$ con paradas intermedias).

> [!todo] Se desea encontrar el coste mínimo para viajar entre cualesquiera de embarcaderos $i$ y $j$.

## Ejemplo del problema

La siguiente tabla muestra los costes de los trayectos:

| $T_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |   0   |   4   |   3   |   5   |   8   |
|   **B**    |   -   |   0   |   1   |   2   |   5   |
|   **C**    |   -   |   -   |   0   |   2   |   4   |
|   **D**    |   -   |   -   |   -   |   0   |   1   |
|   **E**    |   -   |   -   |   -   |   -   |   0   |


# Solución

## 1 - Dimensiones relevantes

Planteadas por mí:

- $S$: tabla solución con el coste mínimo en cada celda.

Planteadas por el enunciado:

- $n$: cantidad de embarcaderos.
- $i$: embarcadero de origen.
- $j$: embarcadero de destino.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. Viajar de un embarcadero a él mismo.
	- Si $i = j$ entonces $S[i,j] = 0$.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |       |       |       |       |       |
|   **B**    |       |       |       |       |       |
|   **C**    |       |       |       |       |       |
|   **D**    |       |       |       |       |       |
|   **E**    |       |       |       |       |       |

> [!cite] $A[i,j]$ representa el coste mínimo para navegar desde $i$ hasta $j$.
> Sea con paradas intermedias o no.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, el caso base es que se intente navegar al mismo embarcadero en el que se está. Además, también se tendrá en cuenta en este paso que no se puede navegar hacia atrás:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |   0   |       |       |       |       |
|   **B**    |   -   |   0   |       |       |       |
|   **C**    |   -   |   -   |   0   |       |       |
|   **D**    |   -   |   -   |   -   |   0   |       |
|   **E**    |   -   |   -   |   -   |   -   |   0   |

### Representar los casos iterativos

> [!info] *Esta tabla* se rellena de izquierda a derecha y de arriba a abajo.

Fila $A[5,\_]$:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |       |       |       |       |
|   **B**    |   -   |  *0*  |       |       |       |
|   **C**    |   -   |   -   |  *0*  |       |       |
|   **D**    |   -   |   -   |   -   |  *0*  |   1    |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

> [!cite] Estando en $D$ solo puede navegarse al último embarcadero, $E$.

Fila $A[4,\_]$:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |       |       |       |       |
|   **B**    |   -   |  *0*  |       |       |       |
|   **C**    |   -   |   -   |  *0*  |   2   |   3   |
|   **D**    |   -   |   -   |   -   |  *0*  |   1   |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

> [!cite] Estando en $C$ puede navegarse tanto a $D$ como a $E$.
> Desde $E$, el mejor camino será aquel que tenga menor coste de aquellos que conecten directamente con él, contando hasta $C$.
> - $C \rightarrow D \rightarrow E$: lo que cueste $C \rightarrow D$ y el coste mínimo $D \rightarrow E$.
> - $D \rightarrow E$: lo que cueste $D \rightarrow E$ y el coste mínimo $E \rightarrow E$ (que es 0).
> ---
> Desde $D$, el mejor camino será aquel que tenga menor coste de aquellos que conecten directamente con él, contando hasta $C$.
> - $C \rightarrow D$: lo que cueste $C \rightarrow D$ y el coste mínimo $C \rightarrow C$ (que es 0).

Fila $A[3,\_]$:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |       |       |       |       |
|   **B**    |   -   |  *0*  |       |       |       |
|   **C**    |   -   |   -   |  *0*  |   2   |   3   |
|   **D**    |   -   |   -   |   -   |  *0*  |   1   |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

Fila $A[2,\_]$:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |       |       |       |       |
|   **B**    |   -   |  *0*  |   1   |   2   | 3      |
|   **C**    |   -   |   -   |  *0*  |   2   |   3   |
|   **D**    |   -   |   -   |   -   |  *0*  |   1   |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

Fila $A[1,\_]$:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |   4   |   3   |   5   |   6   |
|   **B**    |   -   |  *0*  |   1   |   2   |   3   |
|   **C**    |   -   |   -   |  *0*  |   2   |   3   |
|   **D**    |   -   |   -   |   -   |  *0*  |   1   |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

| $A_{i, j}$ | **A** | **B** | **C** | **D** | **E** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **A**    |  *0*  |   4   |   3   |   5   |   6   |
|   **B**    |   -   |  *0*  |   1   |   2   |   3   |
|   **C**    |   -   |   -   |  *0*  |   2   |   3   |
|   **D**    |   -   |   -   |   -   |  *0*  |   1   |
|   **E**    |   -   |   -   |   -   |   -   |  *0*  |

> [!success] Esta es la solución.
> Ya que el problema plantea conocer el coste mínimo entre cualquier $i$ y $j$, esta tabla cumple precisamente eso.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando un $0$ en las celdas donde el embarcadero de origen y destino era el mismo.
> 
> Porque si ya estás en un embarcadero, no puedes navegar a él.
> 
> ---
> Es decir: $A[i,j] = 0$ cuando $i = j$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> La clave fue pensar en «¿cómo he llegado aquí?» más que «¿cómo llego al destino?».
> 
> Estando en $A[i,j]$, miraba todos los embarcaderos $i$ disponibles y su coste mínimo hasta $j$, a los que le añadía el coste correspondiente para terminar de llegar a $i$.
>  ---
> Es decir: $A[i,j] = \displaystyle\min_{i \lt k \leq j} \Big( \underbrace{T[i][k]}_{\text{Coste para llegar a i}} + \underbrace{A[k][j]}_{\text{Coste mínimo hasta j}} \Big)$ cuando $i > j$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = j \\
\displaystyle\min_{i \lt k \leq j} \Big( T[i][k] + A[k][j] \Big) & \text{si} & i > 0 \\
\end{array}
\right.
> $$

# Implementación en `Java`

```java
package Ejercicios.Basicos;



/**
 * PROBLEMA:
 * A lo largo de un río que solo se puede navegar en una dirección,
 * se encuentran 'n' embarcaderos.
 * Se concoce el coste 'T(i,j)' para ir del embarcadero 'i' al 'j',
 * que puede ser más barato o más caro que un recorrido de 'i' a 'j'
 * con paradas intermedias.
 *
 * Se desea encontrar el coste mínimo para viajar entre cualesquiera
 * de los embarcaderos 'i' y 'j'.
 *
 * EJEMPLO:
 * Propuesto en los datos (porque es una tabla).
 *
 *      SOLUCIÓN: Propuesta en la resolución (porque es una tabla).
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_05 {
	
    // Datos del problema
    private static int[][] T;   // Costes de trayectos
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[][]{
            {0, 4, 3, 5, 8},
            {0, 0, 1, 2, 5},
            {0, 0, 0, 2, 4},
            {0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0}
        };
		
		
        // Mostrar los datos del problema
        System.out.println("Costes de trayectos:\n" + mostrarTabla(T));
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("Solución:\n" + mostrarTabla(A));
    }
	
	
    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    public static void rellenarTablaA() {
        // Inicializar la tabla
        int n = T.length;
        A = new int[n][n];
		
        // Aplicar la ecuación de Bellman
        for (int i = n-1; 0 <= i; i--) {       // Esta vez, de abajo a arriba
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                  si  i = j
     * A(i,j) = MIN[i < k <= j][T(i,k) + A(k,j)]   si  i > j
     *
     * @param i Fila de la tabla 'A'
     * @param j Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == j) {
            A[i][j] = 0;
			
        // Caso iterativo
        } else {
            int min = Integer.MAX_VALUE;
			
            for (int k = i+1; k <= j; k++) {
                int valor = T[i][k] + A[k][j];
				
                if (valor < min) {
                    min = valor;
                }
            }
			
            A[i][j] = min;
        }
    }
	
	
    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------
	
    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Uso letras para identificar a los embarcaderos
		
        // Cabeceras
        for (int j = 0; j < tabla[0].length; j++) {
            sb.append("\t").append((char) (65 + j));    // Embarcadero destino
        }
		
        sb.append("\n");
		
        // Filas
        for (int i = 0; i < tabla.length; i++) {
            sb.append((char) (65 + i)).append("\t");    // Embarcadero origen
			
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
^53dbe6
