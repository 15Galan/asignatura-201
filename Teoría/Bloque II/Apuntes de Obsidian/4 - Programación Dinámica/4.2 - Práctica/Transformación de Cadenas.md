---
aliases: ["Ejercicio Complementario 08"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Subsecuencia Común Más Larga]]
> - [[Antecesor Común Ancestral]]

# Problema

Sean $u$ y $v$ dos cadenas de caracteres, se desea transformar $u$ en $v$ con el mínimo número de
operaciones elementales del tipo siguiente:

- Eliminar un carácter.
- Añadir un carácter.
- Cambiar un carácter.

> [!todo] Escribir un algoritmo de programación dinámica que calcule
> - El número mínimo de operaciones necesarias para transformar $u$ en $v$.
> - Cuáles son dichas operaciones. 

## Ejemplo del problema

Sean las cadenas $u = \text{abbac}$ y $v = \text{abcdc}$, se puede pasar de $u$ a $v$ en 3 pasos:

1. Eliminar $\text{b}$ en la posición 3: $\text{abbac} \rightarrow \text{abac}$.
2. Añadir $\text{d}$ en la posición 4: $\text{abac} \rightarrow \text{abadc}$.
3. Cambiar $\text{a}$ por $\text{c}$ en la posición 3: $\text{abadc} \rightarrow \text{abcdc}$.

> [!info] Una solución es $S = \{(\text{E: b},3), (\text{A: d},4), (\text{C: a} \rightarrow \text{c},3)\}$.
> Esta solución no es óptima.
> - La solución óptima es $S = \{(\text{C: b} \rightarrow \text{c},3), (\text{C: a} \rightarrow \text{d},4)$; es decir, $V_S = 2$.

# Solución
## 1 - Dimensiones relevantes

Planteadas por el enunciado:

- $U = \{u_1, \dots, d_n\}$: cadena $u$ y sus caracteres.
- $V = \{v_1, \dots, v_m\}$: cadena $v$ y sus caracteres.
- $n$: longitud de $u$.
- $m$: longitud de $v$.

Planteadas por mí:

- $S = \{s_i, \dots, s_{V_S}\}$: vector solución (operaciones a realizar).
- $V_S$: valor de la solución (número mínimo de operaciones).

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. Si $U = V = \emptyset$ entonces $V_S = 0$ porque no hay que hacer operaciones.
2. Si $U = \emptyset$ pero $V \neq \emptyset$ entonces $V_S = m$ porque se añaden $m$ caracteres.
3. 2. Si $U \neq \emptyset$ pero $V = \emptyset$ entonces $V_S = n$ porque se eliminan $n$ caracteres.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Se usará una **tabla** de tamaño $n+1$ x $m+1$ y una **tabla auxiliar** para construir la solución.

|       $A$       | **$\emptyset$** | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:---------------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |                 |       |       |       |       |       |
|      **a**      |                 |       |       |       |       |       |
|      **b**      |                 |       |       |       |       |       |
|      **b**      |                 |       |       |       |       |       |
|      **a**      |                 |       |       |       |       |       |
|      **c**      |                 |       |       |       |       |       |

> [!cite] $A[i,j]$ representa el número mínimo de operaciones para obtener la cadena $v_0 \dots v_j$ a partir de $u_0 \dots u_i$.
> Es decir, $i$ es el último carácter analizado de $u$ y $j$ es el último carácter analizado de $v$.

|  $\text{aux}$   | **$\emptyset$** | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:---------------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |                 |       |       |       |       |       |
|      **a**      |                 |       |       |       |       |       |
|      **b**      |                 |       |       |       |       |       |
|      **b**      |                 |       |       |       |       |       |
|      **a**      |                 |       |       |       |       |       |
|      **c**      |                 |       |       |       |       |       |

> [!cite] $\text{aux}[i,j]$ contiene las operaciones que cumplan $A[i,j]$.
> El contenido de estas celdas puede ser texto, y se rellena al mismo tiempo que $A$.
> Su única función es almacenar en $\text{aux}[i,j]$ la decisión tomada al calcular $A[i,j]$.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |      0      |   1   |   2   |   3   |   4   |   5   |
|      **a**      |      1      |       |       |       |       |       |
|      **b**      |      2      |       |       |       |       |       |
|      **b**      |      3      |       |       |       |       |       |
|      **a**      |      4      |       |       |       |       |       |
|      **c**      |      5      |       |       |       |       |       |

> [!cite] La cadena vacía me obliga a añadir/eliminar el caracter necesario.

### Representar los casos iterativos

> [!attention] Solo rellenaré $A$ porque $\text{aux}$ viene esplícitamente indicado.

> [!info] Representación
> Representaré como $u:\cdots,u_i$ y $v:\cdots,v_j$ las evaluaciones de cada celda, donde:
> $$
\begin{array}{rcl}
u: & u_1, u_2, \cdots \ | \ u_i \\
v: & v_1, v_2, \cdots \ | \ v_j \\
\end{array}
> $$
> 
> Es decir, los últimos caracteres de $u$ y $v$ ($u_i$ y $v_j$), con todo lo anterior ($\cdots$).

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |       |       |       |       |       |
|      **b**      |     *3*     |       |       |       |       |       |
|      **a**      |     *4*     |       |       |       |       |       |
|      **c**      |     *5*     |       |       |       |       |       |

> [!cite] Acción
> Si tengo $u:\cdots,a$ y $v:\cdots,a$, coinciden, por lo que no hago operaciones (es decir, el número de operaciones es el mismo que al evaluar $u:\cdots$ y $v:\cdots$.
> Si tengo $u:\cdots,a$ y $v:\cdots,b$, no coinciden, por lo que miro en los casos anteriores cuál tiene menos operaciones y le añado la de ahora.
> $$
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots & b \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots & a \\
v: & \cdots &  \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots &  \\
\end{array}
> $$
> 
> $\vdots$
> 
>  Si tengo $u:\cdots,a$ y $v:\cdots,c$, no coinciden, por lo que miro en los casos anteriores cuál tiene menos operaciones y le añado la de ahora.
> $$
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots & c \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots & a \\
v: & \cdots &  \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots &  \\
\end{array}
> $$

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |   1   |   0   |   1   |   2   |   3   |
|      **b**      |     *3*     |       |       |       |       |       |
|      **a**      |     *4*     |       |       |       |       |       |
|      **c**      |     *5*     |       |       |       |       |       |

> [!cite] Acción
> Si tengo $u:\cdots,b$ y $v:\cdots,a$, no coinciden, por lo que miro en los casos anteriores cuál tiene menos operaciones y le añado la de ahora.
> $$
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots & a \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots & b \\
v: & \cdots &  \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots &  \\
\end{array}
> $$
> Si tengo $u:\cdots,b$ y $v:\cdots,b$, coinciden, por lo que no hago operaciones.
> 
> $\vdots$
> 
>  Si tengo $u:\cdots,b$ y $v:\cdots,c$, no coinciden, por lo que miro en los casos anteriores cuál tiene menos operaciones y le añado la de ahora.
> $$
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots & c \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots & b \\
v: & \cdots &  \\
\end{array}
\qquad\qquad
\begin{array}{rcl}
u: & \cdots &  \\
v: & \cdots &  \\
\end{array}
> $$

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |   1   |   0   |   1   |   2   |   3   |
|      **b**      |     *3*     |   1   |   1   |   1   |   2   |   3   |
|      **a**      |     *4*     |       |       |       |       |       |
|      **c**      |     *5*     |       |       |       |       |       |

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |   1   |   0   |   1   |   2   |   3   |
|      **b**      |     *3*     |   1   |   1   |   1   |   2   |   3   |
|      **a**      |     *4*     |   3   |   2   |   2   |   2   |   3   |
|      **c**      |     *5*     |       |       |       |       |       |

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |   1   |   0   |   1   |   2   |   3   |
|      **b**      |     *3*     |   1   |   1   |   1   |   2   |   3   |
|      **a**      |     *4*     |   3   |   2   |   2   |   2   |   3   |
|      **c**      |     *5*     |   4   |   3   |   2   |   3   |   2   |

### Obtener la estructura completa

|       $A$       | $\emptyset$ | **a** | **b** | **c** | **d** | **c** |
|:---------------:|:-----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **$\emptyset$** |     *0*     |  *1*  |  *2*  |  *3*  |  *4*  |  *5*  |
|      **a**      |     *1*     |   0   |   1   |   2   |   3   |   4   |
|      **b**      |     *2*     |   1   |   0   |   1   |   2   |   3   |
|      **b**      |     *3*     |   1   |   1   |   1   |   2   |   3   |
|      **a**      |     *4*     |   3   |   2   |   2   |   2   |   3   |
|      **c**      |     *5*     |   4   |   3   |   2   |   3   | **2** |

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando un $0$ en la posición $A[0,0]$ y calculando los cambios necesarios en la cadena $u$ cuando esta es una cadena vacía y cuando $v$ es una cadena vacía, que será $1$ cambio (el actual) junto a los anteriores.
> 
> Porque contemplando los casos de las cadenas vacías, puede revisarse en todo momento los casos anteriores, llegando como límite, a cuando no hay nada que comparar.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Teniendo en cuenta si los caracteres coinciden o no, se harán $0$ cambios o bien, se harán los cambios mínimos hasta haber llegado al punto actual de estudio de la cadena.
> 
> Porque al coincidir los caracteres, entonces solo habrá que hacer los mismos cambios hasta la evaluación anterior, sin los caracteres $i$ ni $j$ (porque son los añadidos actualmente); mientras que si no coinciden, deberá hacerse el cambio de ahora ($+1$), además del mínimo de cambios que haya habido antes:
> - cuando no estaba el carácter $i$.
> - cuando no estaba el carácter $j$.
> - cuando no estaban ni $i$ ni $j$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 0 \wedge j = 0 \\
A[i-1,j] + 1 & \text{si} & i = 0 \wedge j > 0 \\
A[i,j-1] + 1 & \text{si} & i > 0 \wedge j = 0 \\
A[i-1,j-1] & \text{si} & U[i] = V[j] \\
\min\Big(A[i-1,j], \ A[i-1,j-1], \ A[i,j-1]\Big) + 1 & \text{si} & U[i] \neq V[j] \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

Esta tabla se habrá rellenado al mismo tiempo que $A$, simplemente indicando en cada posición qué operación se ha tenido en cuenta a la hora de calcular cierto $A[i,j]$.

> [!question] ¿Cómo recorro la tabla?
> Rellenando la tabla $\text{aux}$ al mismo tiempo que la tabla $A$, no es necesario reconstruir la solución, ya que se encuentra en la misma posición que el valor óptimo de la tabla $A$: es decir, en $\text{aux}[n,m]$.

> [!cite] Esto se ve mucho más claro en el código del siguiente apartado.

# Implementación en `Java`

```java
package Ejercicios.Complementarios;



/**
 * PROBLEMA:
 * Sean 'u' y 'v' dos cadenas de caracteres, se desea transformar 'u' en 'v'
 * con el mínimo número de operaciones elementales del tipo siguiente:
 * - Eliminar un carácter.
 * - Añadir un carácter.
 * - Cambiar un carácter.
 *
 * EJEMPLO:
 * Sean u = "abbac" y v = "abcdc".
 *
 *      SOLUCIÓN: 2.
 *      Se hacen dos operaciones de cambio: U[2] -> c y U[3] -> d.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_08 {
    // Datos del problema
    private static String U;    // Cadena U
    private static String V;    // Cadena V
    private static int n;       // Longitud de la cadena U
    private static int m;       // Longitud de la cadena V
	
    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación
    private static String[][] aux;  // Tabla auxiliar
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        U = "abbac";    n = U.length();
        V = "abcdc";    m = V.length();
		
		
        // Mostrar los datos del problema
        System.out.println("Cadena u: " + U);
        System.out.println("Cadena v: " + V);
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:\t(la posición inicial es 0)\n" + solucion());
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }
	
    /**
     * Rellena la tabla A con los valores correspondientes.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla A
        A = new int[n+1][m+1];
		
        // Inicializar la tabla auxiliar
        aux = new String[n+1][m+1];
		
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                aux[i][j] = "";
            }
        }
		
        // Rellenar la tabla A
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la ecuación de Bellman para calcular el valor de la tabla A.
     *
     * @param i  Posición de la cadena u
     * @param j  Posición de la cadena v
     */
    private static void bellman(int i, int j) {
        // Caso base 1
        if (i == 0 && j == 0) {
            A[i][j] = 0;
            aux[i][j] = "";
			
        // Caso base 2
        } else if (i == 0) {
            A[i][j] = A[i][j-1] + 1;
            aux[i][j] = "";
			
        // Caso base 3
        } else if (j == 0) {
            A[i][j] = A[i-1][j] + 1;
            aux[i][j] = "";
			
        // Caso iterativo
        } else {
            // Los caracteres coinciden, no se hace nada
            if (U.charAt(i-1) == V.charAt(j-1)) {
                A[i][j] = A[i-1][j-1];
                aux[i][j] = aux[i-1][j-1];
				
            // Los caracteres no coinciden, se hace una operación de cambio
            } else {
                // A[i][j] = Math.min(Math.min(A[i-1][j], A[i][j-1]), A[i-1][j-1]) + 1;
				
                int min;
                String op = "";

                if (A[i-1][j] < A[i][j-1]) {
                    min = A[i-1][j];
                    op = aux[i-1][j] + "- Se elimina '" + U.charAt(i-1) + "' en la posición " + (i-1) + ".\n";
					
					
                } else {
                    min = A[i][j-1];
                    op = aux[i][j-1] + "- Se añade '" + V.charAt(j-1) + "' en la posición " + (j-1) + ".\n";

                }
				
                if (A[i-1][j-1] < min) {
                    min = A[i-1][j-1];
                    op = aux[i-1][j-1] + "- Se cambia '" + U.charAt(i-1) + "' por '" + V.charAt(j-1) + "' en la posición " + (i-1) + ".\n";

                }
				
                A[i][j] = min + 1;
                aux[i][j] = op;
            }
        }
    }
	
    private static String solucion() {
        return aux[n][m];
    }
	
    /**
     * Devuelve el valor de la solución.
     *
     * @return  Valor de la solución
     */
    private static int valor() {
        return A[n][m];
    }
	
    /**
     * Devuelve una representación en cadena de la tabla A.
     *
     * @return  Representación en cadena de la tabla A
     */
    private static String mostrarTabla(int[][] tabla) {
        StringBuilder sb = new StringBuilder();
		
        // Cabeceras
        sb.append("\t").append("∅").append("\t");
		
        for (int h = 0; h < m; h++) {
            sb.append(V.charAt(h)).append("\t");    // Caracteres de v
        }
		
        // Filas
        for (int f = 0; f < n+1; f++) {
            sb.append("\n");
			
            // Cabecera de la fila
            if (f == 0) {
                sb.append("∅");             // Subcadena u vacía
				
            } else {
                sb.append(U.charAt(f-1));   // Caracteres de u
            }
			
            sb.append("\t");
			
            // Valores de las columnas
            for (int c = 0; c < m+1; c++) {
                sb.append(tabla[f][c]).append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
