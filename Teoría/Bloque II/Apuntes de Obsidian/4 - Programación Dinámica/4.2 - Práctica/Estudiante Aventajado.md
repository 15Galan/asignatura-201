---
aliases: ["Ejercicio 03"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Entidades Financieras]]
> - [[Vacunas de Libertonia]]

# Problema

Un estudiante aventajado intenta organizar sus horas de estudio para maximizar la nota acumulada que obtendrá en las $n$ asignaturas del cuatrimestre; para ello, analiza los temarios y obtiene una estimación $c_{i,j}$ de la nota numérica que obtendría en la asignatura $i$ si le dedicara $j$ de las $h$ horas de estudio de las que dispone.

> [!todo] Su objetivo ahora es determinar cuántas horas debe dedicar a cada asignatura.

# Solución
## 1 - Dimensiones relevantes

Planteadas por el enunciado:

- $A = \{a_1, a_2, \dots, a_n\}$: conjunto de asignaturas.
- $n$: cantidad de asignaturas.
- $h$: cantidad de horas de estudio disponible.
- $C$: tabla de estimaciones de notas.
- $c_{i,j}$: estimación de la nota obtenida estudiando $a_i$ durante $h_j$ horas.

Planteadas por mí:

- $S = \{s_1, s_2, \dots, s_n\}$: conjunto solución.
- $s_i$: número de horas dedicadas a la asignatura $a_i$.
- $V_S = \displaystyle\sum_{i=1}^n \Big( c_{i,s_i} \Big)$: valor de la solución, suma de las notas estimadas según las horas de la solución $S$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

No voy a contar el caso en el que $A = \{ \ \} = \emptyset$, solo el caso en el que hay una sola asignatura.

![[Estudiante Aventajado#^e960aa]]

## 3 - Encontrar la relación de recurrencia

### Plantear un ejemplo particular

Un alumno tiene las asignaturas $A = \{A,B,C,D\}$ y $h = 7$ horas disponibles.

|  $C$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |   0   |   3   |   4   |   6   |   8   |   9   |  10   |  10   |
| **B** |   1   |   5   |   6   |   8   |  10   |  10   |  10   |  10   |
| **C** |   0   |   4   |   4   |   5   |   6   |   7   |   9   |  10   |
| **D** |   0   |   2   |   4   |   7   |   7   |   8   |   8   |   9   |

### Definir la estructura a utilizar

Planteo una **tabla** de tamaño $n$ x $h+1$.

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |       |       |       |       |       |       |       |       |
| **B** |       |       |       |       |       |       |       |       |
| **C** |       |       |       |       |       |       |       |       |
| **D** |       |       |       |       |       |       |       |       |

> [!cite] $A[i,j]$ representa una *calificación común acumulada*.
> Esto es la suma de calificaciones estimadas en $H$. Sacar un $5$ en un examen y un $7$ en otro sería una nota común de $12$, no es que se haya sacado un $12$ algún examen, ni que se haya estudiado $12$ horas (porque en este caso, solo se disponen de $7$ horas).

> [!attention] Podría haber contemplado el caso en el que no hay asignaturas.
> Sin embargo, este caso no aporta nada, ya que la fila en la que se tiene una sola asignatura en cuenta, sería exactamente la misma; por tanto, se puede empezar directamente ahí.
^e960aa

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |   0   |   3   |   4   |   6   |   8   |   9   |  10   |  10   |
| **B** |       |       |       |       |       |       |       |       |
| **C** |       |       |       |       |       |       |       |       |
| **D** |       |       |       |       |       |       |       |       |

> [!cite] Dada 1 sola asignatura, $A$, todas las horas se dedican a la misma.

### Representar los casos iterativos

Fila $A[2,\_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |  *0*  |  *3*  |  *4*  |  *6*  |  *8*  |  *9*  | *10*  | *10*  |
| **B** |   1   |   5   |   8   |   9   |  11   |  13   |  14   |  16   |
| **C** |       |       |       |       |       |       |       |       |
| **D** |       |       |       |       |       |       |       |       |

> [!cite] Dadas 2 asignaturas, $A$ y $B$, se reparten las horas entre ellas.
> - $\vdots$
> - $A[2,3] = 8$ porque con $2$ h, lo mejor es estudiar $1_A$ y $1_B$.
> - $A[2,4] = 9$ porque con $3$ h, lo mejor es estudiar $1_A$ y $2_B$.
> - $\vdots$
> - $A[2,8] = 16$ porque con $7$ h, lo mejor es estudiar $3_A$ y $4_B$.

Fila $A[3,\_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |  *0*  |  *3*  |  *4*  |  *6*  |  *8*  |  *9*  | *10*  | *10*  |
| **B** |   1   |   5   |   8   |   9   |  11   |  13   |  14   |  16   |
| **C** |   1   |   5   |   9   |  12   |  13   |  15   |  17   |  18   |
| **D** |       |       |       |       |       |       |       |       |

> [!cite] Dadas 3 asignaturas, $A$, $B$ y $C$, se reparten las horas entre ellas.
> - $\vdots$
> - $A[3,3] = 9$ porque con $2$ h, lo mejor es estudiar $1_{A,B}$ y $1_C$.
> - $A[3,4] = 12$ porque con $3$ h, lo mejor es estudiar $2_{A,B}$ y $1_B$.
> - $\vdots$
> - $A[3,8] = 16$ porque con $7$ h, lo mejor es estudiar $6_{A,B}$ y $1_B$.

Fila $A[4,\_]$:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **A** |  *0*  |  *3*  |  *4*  |  *6*  |  *8*  |  *9*  | *10*  | *10*  |
| **B** |   1   |   5   |   8   |   9   |  11   |  13   |  14   |  16   |
| **C** |   1   |   5   |   9   |  12   |  13   |  15   |  17   |  18   |
| **D** |   1   |   5   |   9   |  12   |  14   |  16   |  19   |  20   |

> [!cite] Dadas 3 asignaturas, $A$, $B$ y $C$, se reparten las horas entre ellas.
> - $\vdots$
> - $A[4,4] = 12$ porque con $3$ h, lo mejor es estudiar $2_{A,B,C}$ y $1_D$.
> - $A[4,5] = 14$ porque con $4$ h, lo mejor es estudiar $3_{A,B,C}$ y $1_D$.
> - $\vdots$
> - $A[4,8] = 20$ porque con $7$ h, lo mejor es estudiar $4_{A,B,C}$ y $3_D$.

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7**  |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|
| **A** |  *0*  |  *3*  |  *4*  |  *6*  |  *8*  |  *9*  | *10*  |  *10*  |
| **B** |   1   |   5   |   8   |   9   |  11   |  13   |  14   |   16   |
| **C** |   1   |   5   |   9   |  12   |  13   |  15   |  17   |   18   |
| **D** |   1   |   5   |   9   |  12   |  14   |  16   |  19   | **20** |

> [!important] Esto indica que, *para el ejemplo creado*, $V_S = 20$.
> Es decir, que el estudiante obtiene una nota común de $20$ puntos.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Copiando la misma fila de valores en el caso de estudiar una sola asignatura.
> 
> Porque es trivial pensar que si se tiene una sola asignatura, todas las horas se le dediquen a la misma.
> 
> ---
>Es decir: $A[i,j] = H[i,j]$ cuando $i = 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Calculando el mejor valor (máximo) entre todas las combinaciones de horas posibles anteriores, con la nota obtenida al añadir la asignatura $i$.
> 
> Porque $A[i-1,j]$ contempla la puntuación óptima con las asignaturas anteriores, por lo que falta añadirle la nota que se obtendría con esta nueva asignatura $i$; no obstante, además hay que tener en cuenta cómo repartir las horas, algo que ya sucedió en $A[i-1,j]$, de modo que se van realizando las combinaciones pertinentes entre "horas empleadas en $A[i-1,j]$ asignaturas y el resto en $C[i,j]$ asignatura".
>  ---
>Es decir: $A[i,j] = \displaystyle\max_{0 \leq k \leq j}\Big(A[i-1,j-k] + C[i,k]\Big)$ cuando $i > 1$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
C[i,j] & \text{si} & i = 1 \\
\displaystyle\max_{1 \leq k \leq j}\Big(A[i-1,j-k] + C[i,k]\Big) & \text{si} & i > 1 \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

> [!attention] No resulta muy viable sacar la combinación de horas desde la tabla $A$.
> No obstante, paralelamente, rellenando una tabla similar pero apuntando en esta ocasión las horas acumuladas, puede obtenerse fácilmente.

Partiendo de una **tabla** de tamaño $n$ x $h+1$:

| $\text{aux}$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|    **A**     |       |       |       |       |       |       |       |       |
|    **B**     |       |       |       |       |       |       |       |       |
|    **C**     |       |       |       |       |       |       |       |       |
|    **D**     |       |       |       |       |       |       |       |       |

Cuyos casos base son los siguientes:

| $\text{aux}$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|    **A**     |   0   |   1   |   2   |   3   |   4   |   5   |   6   |   7   |
|    **B**     |       |       |       |       |       |       |       |       |
|    **C**     |       |       |       |       |       |       |       |       |
|    **D**     |       |       |       |       |       |       |       |       |

Se rellena la tabla siguiendo la [[Ecuación de Bellman]] definida en el paso anterior, pero en lugar de almacenar el valor calculado (suma de las celdas de $A$ y $C$), se almacena el valor de la $k$, pues indica el número de horas correspondiente a dicho valor de nota óptima calculada.

Así, esta tabla no recoge la nota acumulada, sino el tiempo acumulado (horas), obteniendo los siguientes datos:

| $\text{aux}$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|    **A**     |   0   |   1   |   2   |   3   |   4   |   5   |   6   |   7   |
|    **B**     |   0   |   1   |   1   |   1   |   1   |   1   |   1   |   3   |
|    **C**     |   0   |   0   |   1   |   1   |   1   |   1   |   1   |   1   |
|    **D**     |   0   |   0   |   0   |   0   |   1   |   2   |   3   |   3   |

Finalmente puede extraerse los valores concretos recorriendo la tabla hacia atrás y restando el valor empleado en cada ocasión:

| $\text{aux}$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** |
|:------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|    **A**     |   0   |   1   | **2** |   3   |   4   |   5   |   6   |   7   |
|    **B**     |   0   |   1   |   1   | **1** |   1   |   1   |   1   |   3   |
|    **C**     |   0   |   0   |   1   |   1   | **1** |   1   |   1   |   1   |
|    **D**     |   0   |   0   |   0   |   0   |   1   |   2   |   3   | **3** |

> [!question] ¿Cómo recorro la tabla?
> Partiendo de la casilla de la tabla $A$ en la que se encuentra el valor óptimo, que coincide con la misma casilla de la cantidad óptima de horas, se observa que contando con la asignatura $i$ se emplean $\text{aux}[i,j]$ horas, por lo que se restan de las horas acumuladas hasta entonces $j$ y se comrpueba en la fila anterior $i-1$.
> Así hasta haber cubierto todas las asignaturas.

# Implementación en `Java`

> [!cite] Esta implementación incluye los algoritmos para las tablas $A$ y $\text{aux}$.

```java
package Ejercicios.Basicos;

import java.util.Arrays;



/**
 * Un estudiante aventajado intenta organizar sus 'h' horas de estudio
 * para maximizar la nota acumulada que obtendrá en las 'n' asignaturas
 * del cuatrimestre.
 * Para ello, analiza los temarios y obtiene una estimación 'c(i,j)' de la nota
 * numérica que obtendría en la asignatura 'i' dedicándole 'j' horas de estudio.
 *
 * Su objetivo ahora es determinar cuántas horas debe dedicar a cada asignatura.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_03 {
	
    // Datos del problema
    private static int[][] C;   // Tabla de notas estimadas
    private static int h;       // Número de horas de estudio
    private static int n;       // Número de asignaturas
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
    private static int[][] aux; // Tabla de horas/asignatura
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        C = new int[][] {                   // Ejemplo del profesor Julio
            {0, 3, 4, 6,  8,  9, 10, 10},   // Asignatura 1
            {1, 5, 6, 8, 10, 10, 10, 10},   // Asignatura 2
            {0, 4, 4, 5,  6,  7,  9, 10},   // Asignatura 3
            {0, 2, 4, 7,  7,  8,  8,  9}    // Asignatura 4
        };
		
        h = C[0].length;
        n = C.length;
		
		
        // Mostrar los datos del problema
        System.out.println("Asignaturas: " + n);
        System.out.println("Horas de estudio: " + h);
        System.out.println("Notas estimadas:\n" + mostrarTabla(C));
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
        System.out.println("\nTabla Auxiliar:\n" + mostrarTabla(aux));
    }
	
	
    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[n][h];
        aux = new int[n][h];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < h; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = c(i,j)                                  si  i = 0
     * A(i,j) = MAX[0 <= k <= j](A(i-1,k) + c(i,l-k))   si  i > 0
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        aux[i][j] = 0;
		
        // Caso base
        if (i == 0) {
            A[i][j] = C[i][j];  // Nota estimada acumulada
            aux[i][j] = j;      // Horas dedicadas acumuladas
		
        // Caso iterativo
        } else {
            int max = 0;    // Máxima nota acumulada
            int kax = 0;    // Máxima hora dedicada acumulada
			
            for (int k = 0; k <= j; k++) {
                int aux = A[i-1][j-k] + C[i][k];
				
                if (max < aux) {
                    max = aux;
                    kax = k;
                }
            }
			
            A[i][j] = max;
            aux[i][j] = kax;
        }
    }
	
    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @return      Solución óptima
     */
    private static int[] solucion() {
        int[] S = new int[n];           // Vector con la distribución de horas
        int resto = h-1;                // Se resta porque 'h' son las columnas
		
        for (int i = n-1; i >= 0; i--) {
            S[i] = aux[i][resto];
            resto -= aux[i][resto];
        }
		
        return S;
    }
	
    /**
     * Indica el valor óptimo de la solución
     * a partir de la tabla generada.
     *
     * @return      Valor óptimo
     */
    private static int valor() {
        return A[n-1][h-1];
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
        sb.append("\t");
		
        for (int cabecera = 0; cabecera < tabla[0].length; cabecera++) {
            sb.append(cabecera).append("\t");
        }
		
        // Filas
        for (int fil = 0; fil < tabla.length; fil++) {
            sb.append("\n").append((char) (65 + fil)).append("\t");    // Letra
			
            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
