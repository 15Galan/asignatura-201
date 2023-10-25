---
tags: [tema4/examen, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Estudiante Aventajado]]

# Introducción

> [!abstract] Este parcial incluye un ejemplo con una columna $M = 0$.
> No como en el parcial de [[Inicio#2019 - 2020|2019-2020]], que presenta el mismo ejercicio y ejemplo.

Esta columna se utiliza para facilitar el proceso de programación dinámica, y se llega a esta conclusión tras intentar rellenar la tabla $A$ por primera vez, como un caso base (véase [[#Representar los Principio de Optimalidad a9a8e9 casos base|los casos base]] de este problema), ya que sería posible que se repartieran $0$ € a una entidad financiera $e_i$.

# Problema

Consideremos $n$ entidades financieras y que cada una ofrece un interés anual diferente según la cantidad de dinero aportada.

Así $I(i,j)$ es el interés que nos dará la entidad financiera $i$ al cabo de un año si depositamos la cantidad $j$; donde $i$ varía desde 1 hasta $n$ (número de bancos) y $j$ desde 1 hasta $M$ (cantidad en euros).

> [!todo] Se pretende distribuir $M$ euros entre las $n$ entidades de forma que el interés total obtenido sea máximo.

## Ejemplo del problema

Sea la siguiente tabla de $4$ entidades y sus intereses para para $M = 7$ euros:

|  $I$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |  0.0  |  0.3  |  0.4  |  0.6  |  0.8  |  0.9  |  1.0  |  1.0  |
| **2** |  0.0  |  0.5  |  0.6  |  0.8  |  1.0  |  1.0  |  1.0  |  1.0  |
| **3** |  0.0  |  0.4  |  0.4  |  0.5  |  0.6  |  0.7  |  0.9  |  1.0  |
| **4** |  0.0  |  0.2  |  0.4  |  0.7  |  0.7  |  0.8  |  0.8  |  0.9  |

# Solución

## 1 - Dimensiones relevantes

Planteadas por el problema:

- $E = \{e_1, e_2, \dots, e_n\}$: entidades financieras.
- $n$: cantidad de entidades.
- $M$: cantidad de dinero a repartir.
- $I_{i,j}$: interés obtenido al invertir $j$ euros en $e_i$.

Planteadas por mí:

- $S = \{s_1, s_2, \dots, e_n\}$: vector solución.
- $s_i$: cantidad de euros a invertir en $e_i$.
- $V_S$: valor máximo de inversión estimada.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. $M = 0$ pero $E \neq \emptyset$.
	- Si $M = 0$ entonces $S = \emptyset$ y $V_S = 0$.
2. $M > 0$ pero $E = \{e_1\}$.
	- Si solo hay una entidad, entonces todo el dinero se usa en la misma.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Una **tabla** de tamaño $n$ x $M+1$ (igual que la tabla $I$).

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |       |       |       |       |       |       |       |       |
| **2** |       |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |       |

> [!cite] $A[i,j]$ representa el $V_S$ acumulado hasta el momento.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |  0.0  |  0.3  |  0.4  |  0.6  |  0.8  |  0.9  |  1.0  |  1.0  |
| **2** |       |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |       |

> [!attention] La primera columna también debería ser un caso base.
> No obstante, como el propio ejemplo ya la coloca por mí, no la voy a *incluir*.

### Representar los casos iterativos

> [!seealso] Se rellena de una forma similar al problema del [[Estudiante Aventajado]].

Fila $A[2, \_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** | *0.0* | *0.3* | *0.4* | *0.6* | *0.8* | *0.9* | *1.0* | *1.0* |
| **2** |  0.0  |  0,5  |  0,8  |  0,9  |  1,1  |  1,3  |  1,4  |  1,6  |
| **3** |       |       |       |       |       |       |       |       |
| **4** |       |       |       |       |       |       |       |       |

Fila $A[3, \_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** | *0.0* | *0.3* | *0.4* | *0.6* | *0.8* | *0.9* | *1.0* | *1.0* |
| **2** |  0.0  |  0,5  |  0,8  |  0,9  |  1,1  |  1,3  |  1,4  |  1,6  |
| **3** |  0.0  |  0,5  |  0,9  |  1,2  |  1,3  |  1,5  |  1,7  |  1,8  |
| **4** |       |       |       |       |       |       |       |       |

Fila $A[4, \_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** | *0.0* | *0.3* | *0.4* | *0.6* | *0.8* | *0.9* | *1.0* | *1.0* |
| **2** |  0.0  |  0,5  |  0,8  |  0,9  |  1,1  |  1,3  |  1,4  |  1,6  |
| **3** |  0.0  |  0,5  |  0,9  |  1,2  |  1,3  |  1,5  |  1,7  |  1,8  |
| **4** |  0.0  |  0,5  |  0,9  |  1,2  |  1,4  |  1,6  |  1,9  |   2   |

### Obtener la estructura completa

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** | *0.0* | *0.3* | *0.4* | *0.6* | *0.8* | *0.9* | *1.0* | *1.0* |
| **2** |  0.0  |  0,5  |  0,8  |  0,9  |  1,1  |  1,3  |  1,4  |  1,6  |
| **3** |  0.0  |  0,5  |  0,9  |  1,2  |  1,3  |  1,5  |  1,7  |  1,8  |
| **4** |  0.0  |  0,5  |  0,9  |  1,2  |  1,4  |  1,6  |  1,9  | **2** |

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Copiando los valores de $I[1,j]$ en $A[1,j]$.
>
> Porque es el caso en el que solo hay un banco, por lo que no es necesario decidir.
>  ---
> Es decir: $A[i,j] = I[i,j]$ cuando $i = 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Calculando el mejor valor (máximo) entre todas las combinaciones de intereses posibles anteriores, con el interés obtenido al añadir el banco $i$.
> 
> Porque $A[i-1,j]$ contempla el interés óptimo con los bancos anteriores, por lo que falta añadirle el interés que se obtendría con este nuevo banco $i$; no obstante, además hay que tener en cuenta cómo repartir el dinero, algo que ya sucedió en $A[i-1,j]$, de modo que se van realizando las combinaciones pertinentes entre "dinero repartido en $A[i-1,j]$ bancos y el resto en $C[i,j]$ asignatura".
>  ---
> Es decir: $A[i,j] = \displaystyle\max_{i \leq k \leq j}\Big(A[i-1,k] + I[i,j-k]\Big)$ cuando $i > 1$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
I[i,j] & \text{si} & i = 1 \\
\displaystyle\max_{i \leq k \leq j}\Big(A[i-1,k] + I[i,j-k]\Big) & \text{si} & i > 1 \\
\end{array}
\right.
> $$

> [!attention] La [[Ecuación de Bellman]] es idéntica a la del problema del [[Estudiante Aventajado]]
> Observa que en realidad las fórmulas cuando $i > 1$ de ambos ejercicios, aunque ligeramente distintas, son equivalentes; porque las combinaciones se pueden obtener de derecha a izquierda, o de izquierda a derecha.
> $\displaystyle\max_{i \leq k \leq j}\Big(A[i-1,k] + X[i,j-k]\Big) \equiv \displaystyle\max_{i \leq k \leq j}\Big(A[i-1,j-k] + X[i,j]\Big)$

## 5 - Encontrar la configuración de la solución (extra)

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** | *0.0* | *0.3* | *0.4* | *0.6* | *0.8* | *0.9* | *1.0* | *1.0* |
| **2** |  0.0  |  0,5  |  0,8  |  0,9  |  1,1  |  1,3  |  1,4  |  1,6  |
| **3** |  0.0  |  0,5  |  0,9  |  1,2  |  1,3  |  1,5  |  1,7  |  1,8  |
| **4** |  0.0  |  0,5  |  0,9  |  1,2  |  1,4  |  1,6  |  1,9  | **2** |

|  $A$  | **0** | **1** | ==**2**== | ==**3**== | **4** | ==**5**== | **6** | ==**7**== |
|:-----:|:-----:|:-----:|:---------:|:---------:|:-----:|:---------:|:-----:|:---------:|
| **1** | *0.0* | *0.3* | ***0.4*** |   *0.6*   | *0.8* |   *0.9*   | *1.0* |   *1.0*   |
| **2** |  0.0  |  0,5  |    0,8    |  **0,9**  |  1,1  |    1,3    |  1,4  |    1,6    |
| **3** |  0.0  |  0,5  |    0,9    |    1,2    |  1,3  |  **1,5**  |  1,7  |    1,8    |
| **4** |  0.0  |  0,5  |    0,9    |    1,2    |  1,4  |    1,6    |  1,9  |   **2**   |

> [!success] La solución para el ejemplo es $S = \{2, 1, 2, 2\}$.

> [!question] ¿Cómo recorro la tabla?
> Empezando por $A[n][M+1]$ que es la celda con el valor óptimo:
> 1. Le resto el valor obtenido a $j$, que resulta $x$.
> 2. Me desplazo a la fila anterior, a la columna $x$.
> 3. Repito la operación hasta haber llegado al banco $1$.

# Implementación en `Java`

```java
package Examen;

import java.text.DecimalFormat;
import java.util.Arrays;



/**
 * Consideremos 'n' entidades financieras y que cada una ofrece un interés
 * anual diferente según la cantidad de dinero aportada.
 * Así 'interes(i,j)' es el interés que nos dará la entidad financiera 'i'
 * al cabo de un año si depositamos la cantidad 'j'; donde 'i' varía desde
 * 1 hasta 'n' (número de bancos) y 'j' desde 1 hasta 'M' (cantidad en euros).
 *
 * Se pretende distribuir 'M' euros entre las 'n' entidades
 * de forma que el interés total obtenido sea máximo.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2020 {

    // Datos del problema
    private static double[][] I;    // Tabla que del interés anual de cada banco
    private static int n;           // Número de bancos
    private static int M;           // Cantidad de euros a depositar
	
    // Datos del desarrollo
    private static double[][] A;    // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        I = new double[][] {    // Ejemplo del parcial
            {0.0, 0.3, 0.4, 0.6, 0.8, 0.9, 1.0, 1.0},  // Entidad 1
            {0.0, 0.5, 0.6, 0.8, 1.0, 1.0, 1.0, 1.0},  // Entidad 2
            {0.0, 0.4, 0.4, 0.5, 0.6, 0.7, 0.9, 1.0},  // Entidad 3
            {0.0, 0.2, 0.4, 0.7, 0.7, 0.8, 0.8, 0.9}   // Entidad 4
        };
		
        // I = new double[][] {    // Ejemplo de Diego
        //     {0.0, 0.1, 0.2, 0.4, 0.6, 0.7},  // Entidad 1
        //     {0.0, 0.1, 0.3, 0.4, 0.5, 0.8},  // Entidad 2
        //     {0.0, 0.0, 0.2, 0.5, 0.7, 0.8}   // Entidad 3
        // };
		
        M = I[0].length;    // Número de columnas
        n = I.length;       // Número de filas
		
		
        // Mostrar los datos del problema
        System.out.println("Dinero: " + (M-1));     // Porque hay 1 columna más
        System.out.println("Bancos: " + n);
        System.out.println("Intereses:\n" + mostrarTabla(I));
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("Tabla A:\n" + mostrarTabla(A) + "\n");
    }
	
	
    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new double[n][M];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < M; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = I(i,j)                                   si  i = 0
     * A(i,j) = MAX[0 <= k <= j][A(i-1,k) + I(i,j-k)]    si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == 0) {
            A[i][j] = I[i][j];
			
        // Caso iterativo
        } else {
            double max = 0.0;
			
            for (int k = 0; k <= j; k++) {
                double aux = A[i-1][k] + I[i][j-k];
				
                if (max < aux) {
                    max = aux;
                }
            }
			
            A[i][j] = max;
        }
    }
	
    /**
     * Calcula la cantidad de euros a invertir en cada banco.
     *
     * @return  Vector con la cantidad 'm' de euros a invertir en la entidad 'i'
     */
    private static int[] solucion() {
        int[] S = new int[n];           // Euros a invertir en cada banco
        double[] D = new double[n];     // Interés óptimo de cada entidad
        int resto = M-1;                // Cantidad restante de euros a depositar
		
        // Calcular el interés obtenido en cada entidad para 'm' euros
        for (int i = n-1; i >= 0; i--) {
            D[i] = A[i][resto];
            resto -= A[i][resto];
        }
		
        int ingresado = 0;  // Cantidad de euros ingresados en total
		
        // Calcular la cantidad de 'm' euros obtenida en cada entidad
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M; j++) {
                // Si el interés está en la distribución,
                // se han metido 'm' euros en la entidad 'i'
                if (A[i][j] == D[i]) {
                    int m = j - ingresado;
					
                    S[i] = m;
                    ingresado = j;          // Se actualiza el total de ingresos
                }
            }
        }
		
        return S;
    }
	
    /**
     * Calcula el interés máximo obtenido al repartir 'M' euros entre las
     * 'n' entidades.
     *
     * @return  El valor de la tabla 'A' que representa el interés máximo
     */
    private static double valor() {
        return A[n-1][M-1];
    }
	
	
    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------
	
    /**
     * Genera una representación de una tabla con sus índices como cabeceras.
     */
    private static String mostrarTabla(double[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Cabeceras
        sb.append("\t");
		
        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t\t");
        }
		
        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append(fil+1).append("\t");
			
            for (int col = 0; col < tabla[0].length; col++) {
                DecimalFormat df = new DecimalFormat("#.##");   // 2 decimales
                sb.append(df.format(tabla[fil][col])).append("\t\t");
            }
        }
		
        return sb.toString();
    }
}
```
