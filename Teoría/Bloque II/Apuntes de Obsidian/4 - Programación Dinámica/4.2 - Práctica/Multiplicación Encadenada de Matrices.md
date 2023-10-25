---
aliases: ["Ejercicio 01"]
tags:    [tema4/relación, estado/correcto]
---
# Introducción

> [!attention] Recordatorio sobre las multiplicaciones de matrices
> Sean las matrices $A$ y $B$, se verifica que: $A · B \neq B · A$.
^b49ebc

# Problema

Sean $A_1, A_2, \dots, A_n$ un conjunto de matrices que se desean multiplicar en secuencia.
Se supone que estas matrices son compatibles, esto es, la dimensión de $A_i$ es $m_i$ x $n_i$ y se cumple que $n_i = m_i + 1$.

*Nótese que al multiplicar 2 matrices de tamaños $m × n$ y $n × p$, se realizan $m · n · p$ multiplicaciones.*

El número total de multiplicaciones escalares que se realizan dependerá del modo en que se asocien las matrices para la multiplicación.

> [!todo] Implementar un algoritmo que calcule *el número óptimo de multiplicaciones escalares* necesarias para multiplicar una serie de $n$ matrices.

## Ejemplo del problema

Sean las matrices $A_1^{10 × 100}, A_2^{100 × 5} y A_3^{5 × 50}$, verifica que:

- $\left(\left( A_1 · A_2\right) · A_3\right)$ supone $10 · 100 · 5 + 10 · 5 · 50 = 7500$ multiplicaciones.
- $\left(A_1 · \left(A_2 · A_3\right) \right)$ supone $100 · 5 · 50 + 10 · 100 · 50 = 75000$ multiplicaciones.

> [!info] La solución es $V_S = 7500$.
> Esta solución es óptima.

# Solución

## 1 - Dimensiones relevantes

Planteadas por mí:

- $M = \{A_1, A_2, \dots, A_n\}$: conjunto de matrices a multiplicar.
- $V_S$: valor solución (número de multiplicaciones).

Planteadas por el enunciado:

- $A_i$: matriz $i$.
- $n_i$: filas de $A_i$.
- $m_i$: columnas de $A_i$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. $M = \{ \ \} = \emptyset$.
	- Si $|M| = 0$ entonces $V_S = 0$, porque *no hay ninguna matriz que multiplicar*.
2. $M = \{A_1\}$.
	- Si $|M| = 1$ entonces $V_S = 0$, porque *una sola matriz no se puede multiplicar*.

> [!cite] No añadiré el primer caso, porque produce el mismo efecto que el segundo.
> Al contrario que en otros ejercicios, solo se trata con una dimensión, por eso lo hago.

## 3 - Encontrar la relación de recurrencia

### Plantear un ejemplo particular

Sean las matrices $A^{2 × 3}, B^{3 × 5}, C^{5 × 4}$ y $D^{4 × 6}$.

### Definir la estructura a utilizar

> [!info] Sea $M_i$ subconjunto de $M$, y $0 \leq i,j \leq |M|$.

Planteo una **tabla** de tamaño $|M| × |M|$ (no contemplo $\emptyset$).

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:---:|
|     A     |     |     |     |     |
|     B     |  -  |     |     |     |
|     C     |  -  |  -  |     |     |
|     D     |  -  |  -  |  -  |     |

La tabla resulta *simétrica*, ya que se está usando la misma dimensión tanto para las filas como para las columnas.; además, como se representan multiplicaciones secuenciales (y por [[Multiplicación Encadenada de Matrices#^b49ebc|el recordatorio de la introducción]]), solo será necesario rellenarla en un sentido.

> [!cite] $A[i,j]$ representa el *$V_S$ hasta el momento actual*.
> Es decir, el *número de multiplicaciones escalares necesarias*.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, los casos base es que se intente multiplicar una sola matriz, o que no haya matrices que multiplicar (este caso no aporta información).

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:---:|
|     A     |  0  |     |     |     |
|     B     |  -  |  0  |     |     |
|     C     |  -  |  -  |  0  |     |
|     D     |  -  |  -  |  -  |  0  |

> [!cite] No se han incluido los casos en los que $M = \emptyset$.
> No aportan información y de esta forma la tabla es más pequeña.

### Representar los casos iterativos

> [!info] La tabla se rellena seguiendo la *diagonal* que presenta.

Diagonal de $A[1,2]$ hasta $A[3,4]$:

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:---:|
|     A     | *0* | 30  |     |     |
|     B     |  -  | *0* | 60  |     |
|     C     |  -  |  -  | *0* | 120 |
|     D     |  -  |  -  |  -  | *0* |

> [!cite] Acción
> $A_{1,2}^{2×5} \ : \ A[1,2] = A_{1,1} · B = (0) + 2 · 3 · 5 = 30$
>  ---
> $A_{2,3}^{3×4} \ : \ A[2,3] = A_{2,2} · C = (0) + 3 · 5 · 4 = 60$
>  ---
> $A_{3,4}^{5×6} \ : \ A[3,4] = A_{3,3} · A_4 = (0) + 4 · 5 · 6 = 120$

Diagonal de $A[1,3]$ hasta $A[2,4]$:

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:---:|
|     A     | *0* | 30  | ==70==  |     |
|     B     |  -  | *0* | 60  | ==132== |
|     C     |  -  |  -  | *0* | 120 |
|     D     |  -  |  -  |  -  | *0* |

> [!cite] Acción
> $A_{1,3}^{2×4} \ : \ A[1,3]$, será el mínimo de:
> - $A_{1,2} · C = (30) + 2 · 5 · 4 = 30 + 40 = 70$
> - $A · A_{2,3} = 2 · 3 · 5 + (60) = 30 + 60 = 80$
> ---
> $A_{2,4}^{5×6} \ : \ A[2,4]$, será el mínimo de:
> - $A_{2,3} · D = (60) + 3 · 4 · 6 = 60 + 72 = 132$
> - $B · A_{3,4} = 3 · 5 · 6 + (120) = 90 + 120 = 210$

Diagonal de $A[1,3]$ hasta $A[2,4]$:

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:-------:|
|     A     | *0* | 30  | 70  | ==118== |
|     B     |  -  | *0* | 60  |   132   |
|     C     |  -  |  -  | *0* |   120   |
|     D     |  -  |  -  |  -  |   *0*   |

> [!cite] Acción
> $A_{4,4}^{2×6} \ : \ A[4,4]$, será el mínimo de:
> - $A_{1,3} · D = (70) + 2 · 4 · 6 = 70 + 48 = 118$
> - $A_{1,2} · A_{3,4} = (30) + 2 · 5 · 6 + (120) = 210$
> - $A · A_{2,4} = 2 · 5 · 6 + (132) = 60 + 132 = 168$

### Obtener la estructura completa

| $A$ |  A  |  B  |  C  |  D  |
|:---------:|:---:|:---:|:---:|:-------:|
|     A     | *0* | 30  | 70  | **118** |
|     B     |  -  | *0* | 60  |   132   |
|     C     |  -  |  -  | *0* |   120   |
|     D     |  -  |  -  |  -  |   *0*   |

> [!success] La solución se encuentra en la casilla $A[1,n]$.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Con $0$ en la diagonal principal y `-` en la parte inferior de dicha diagonal.
>
> Porque una matriz no puede multiplicarse por sí misma en este problema, por lo que esa acción tedría un coste $0$; por otro lado, solo pueden multiplicarse en el mismo orden, por lo que solo es necesario calcular en una dirección.
>  ---
> Es decir: $A[i,j] = 0$ cuando $i = j$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Observaba en cada momento cuál era la combinación posible de agrupaciones de matrices a calcular. Teniendo en cuenta eso y el coste de multiplicar ambas matrices, escogía el mínimo de ellas.
> 
> Porque como cada celda representa el número mínimo de multiplicaciones acumulado al haber llegado a $A[i,j]$, las combinaciones vienen dadas por los propios índices de la tabla, por lo que al recorrerla y añadiendo el coste de multiplicaciones para añadir el caso actual, se obtiene el valor deseado.
> 
> Véase que siempre se multiplican *solo 2 matrices* (aunque una de ellas sea un producto de otras anteriores).
>  ---
> Es decir:
> $A[i,j] = \displaystyle\min_{i \leq k \lt j}\Big(\underbrace{A[i,k]}_{\text{Coste } A_i \rightarrow A_k} + \underbrace{A[k+1,j]}_{\text{Coste } A_{i+k,j} \rightarrow A_j} + \underbrace{(n_{i,k} · n_{k+1,j} · m_{k+1,j})}_{\text{Coste } A_{i,k} \ · \ A_{k+1,j}}\Big)$ cuando $i > j$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = j \\
\displaystyle\min_{i \leq k \lt j}\Big(A[i,k] + A[k+1,j] + (n_{i,k} · n_{k+1,j} · m_{k+1,j})\Big) & \text{si} & i > j \\
\end{array}
\right.
> $$

# Implementación en `Java`

```java
package Ejercicios.Basicos;



/**
 * PROBLEMA:
 * Sean 'A_1, ..., A_n' un conjunto de matrices que se desean multiplicar
 * en secuencia.
 * Se supone que estas matrices son compatibles, esto es,
 * la dimensión de A_i es 'n_i x m_i' y se cumple que m_i = n_i + 1.
 *
 * Nótese que al multiplicar 2 matrices de tamaños 'm x n' y 'n x p',
 * se realizan m·n·p multiplicaciones.
 *
 * El número total de multiplicaciones escalares que se realizan dependerá
 * del modo en que se asocien las matrices para la multiplicación.
 *
 * Implementar un algoritmo que calcule el número multiplicaciones óptimo
 * necesarias para multiplicar una serie de 'n'' matrices
 *
 *
 * EJEMPLO:
 * Sean las matrices A, B, C y D, cuyas respectivas
 * dimensiones son '2x3', '3x4', '4x5' y '5x6'.
 *
 *      SOLUCIÓN: 118 multiplicaciones
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_01 {
	
    // Datos del problema
    private static int[][] M;   // Matrices a multiplicar
    private static int n;       // Número de matrices a multiplicar
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        M = new int[][]{{2, 3}, {3, 5}, {5, 4}, {4, 6}};    // Dimensiones
        n = M.length;
		
		
        // Mostrar los datos del problema
        System.out.println("Matrices: " + mostrarMatrices());
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nValor: " + valor());
		
		
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
		
        // Aplicar la ecuación de Bellman (recorrer en diagonal)
        for (int i = 0; i < n; i++) {
            A[i][i] = 0;
        }
		
        for (int i = n-1; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        int min = Integer.MAX_VALUE;
		
        for (int k = i; k < j; k++) {
            int fI_K = M[i][0];
            int fIK_J = M[k+1][0];
            int cIK_J = M[k+1][1];
            int v = A[i][k] + A[k+1][j] + (fI_K * fIK_J * cIK_J);
			
            if (v < min) {
                min = v;
            }
        }
		
        A[i][j] = min;
    }
	
    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @return      Solución óptima
     */
    private static int valor() {
        return A[0][n-1];
    }
	
	
    // --------------------------------------------------
    // Funciones auxiliares
    // --------------------------------------------------
	
    /**
     * Crea una representación de los nombres de las matrices
     * y sus dimensiones.
     *
     * @return          Representación de las matrices
     */
    private static String mostrarMatrices() {
        StringBuilder sb = new StringBuilder();
		
        for (int i = 0; i < n ; i++) {
            sb.append((char) (65 + i));
            sb.append(" (").append(M[i][0]);
            sb.append(" x ");
            sb.append(M[i][1]).append(")");
			
            if (i < n-1) {
                sb.append(", ");
            }
        }
		
        return sb.toString();
    }
	
    /**
     * Crea una representación de una tabla con sus cabeceras.
     *
     * @param tabla     Tabla a representar
     *
     * @return      Representación de la tabla
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Cabeceras
        sb.append("\t");
		
        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append((char) (65 + cabecera)).append("\t");
        }
		
        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append((char) (65+fil)).append("\t");
			
            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
