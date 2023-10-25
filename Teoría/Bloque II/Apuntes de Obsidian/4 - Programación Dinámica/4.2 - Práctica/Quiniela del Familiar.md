---
tags: [tema4/relación, estado/correcto]
---
# Problema

Un desconocido (que dice ser familiar suyo, aunque no se parece en nada a Vd.) se presenta ante Vd. montado en un DeLorean y le da un anuario con todos los resultados de la quiniela del año próximo.

Sabemos también el premio $r_i$ que habrá en la semana $i$ ($1 \leq i \leq n$) si jugamos la quiniela ganadora en dicha semana.

Para no levantar sospechas, decidimos que no podremos ganar más de una vez cada $K > 0$ semanas (en otras palabras, deben transcurrir al menos $K − 1$ semanas entre dos premios consecutivos).

> [!todo] Determinar cómo hay que jugar para maximizar la ganancia.

## Ejemplo del problema

Sea el anuario $R = \{4, 10, 3, 5, 9, 6, 4, 4, 7, 8\}$ con $K = 4$.

# Solución
## 1 - Dimensiones relevantes

Planteadas por mí:

- $p$: número de premios posibles teniendo en cuenta $n$ y el margen de sospecha $K$.
- $S = \{s_1, s_2, \dots, s_k\} \subset R$: solución con los premios elegidos.
- $V_S = \displaystyle\sum_{i = 1}^k \Big(s_i\Big)$: valor de la solución (suma de los premios).

Planteadas por el problema:

- $R = \{r_1, r_2, \dots, r_n\}$: conjunto (quiniela) con los premios semanales.
- $r_i$: premio de la semana $i$.
- $n$: cantidad de premios.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. El anuario solo contiene una semana.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Una **tabla** de tamaño $p$ x $n+1$.

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |       |       |       |       |       |       |       |       |       |       |
| **2** |       |       |       |       |       |       |       |       |       |       |
| **3** |       |       |       |       |       |       |       |       |       |       |

> [!cite] $A[i,j]$ representa el *beneficio acumulado* con premios.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|  $A$  | **0** | **1**  | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9** |
|:-----:|:-----:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |   4   | ==10== |  10   |  10   |  10   |  10   |  10   |  10   |  10   |  10   |
| **2** |       |        |       |       |       |       |       |       |       |       |
| **3** |       |        |       |       |       |       |       |       |       |       |

> [!cite] Resulta que $10$ es el premio más grande de todo el anuario.

### Representar los casos iterativos

|  $A$  | **0** | **1** | **2** | **3** | **4**  | **5**  | **6** | **7** | **8**  | **9**  |
|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|:-----:|:-----:|:------:|:------:|
| **1** |   4   |  10   |  10   |  10   |   10   |   10   |  10   |  10   |   10   |   10   |
| **2** |   4   |  10   |  10   |  10   | ==13== | ==16== |  16   |  16   | ==17== | ==18== |
| **3** |       |       |       |       |        |        |       |       |        |        |

> [!cite] A partir de aquí ya hay $2$ premios y puede tenerse en cuenta $K$ de forma seria.
> $13 = 4 + 9$ porque $4$ fue el premio anterior y $9$ el primer premio disponible.
> $16 = 10 + 6$ porque $10$ fue el premio anterior y $6$ el primer premio disponible.
> Lo mismo ocurre con $17$ y $18$.

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8**  | **9**  |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
| **1** |   4   |  10   |  10   |  10   |  10   |  10   |  10   |  10   |   10   |   10   |
| **2** |   4   |  10   |  10   |  10   |  13   |  16   |  16   |  16   |   17   |   18   |
| **3** |   4   |  10   |  10   |  10   |  13   |  16   |  16   |  16   | ==20== | ==24== |

> [!cite] A partir de aquí ya hay $2$ premios y puede tenerse en cuenta $K$ de forma seria.
> $20 = 13 + 7$ porque $13$ fue el premio anterior y $7$ el primer premio disponible.
> $24 = 16 + 8$ porque $16$ fue el premio anterior y $8$ el primer premio disponible.

### Obtener la estructura completa

|  $A$  | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** | **9**  |
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|
| **1** |   4   |  10   |  10   |  10   |  10   |  10   |  10   |  10   |  10   |   10   |
| **2** |   4   |  10   |  10   |  10   |  13   |  16   |  16   |  16   |  17   |   18   |
| **3** |   4   |  10   |  10   |  10   |  13   |  16   |  16   |  16   |  20   | **24** |

> [!important] Esto indica que el beneficio óptimo $V_S = 24$.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> El caso base era que se pudiera escoger un solo premio, por lo que se copió el anuario, manteniendo siempre el valor máximo encontrado.
> ---
> Es decir: $A[i,j] = \displaystyle\max_{1 \leq k \leq j}\Big(r_k\Big)$ cuando $i = 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Comprobaba el beneficio máximo de la fila anterior hasta la fila actual, pero teniendo en cuenta el margen de sopsecha $K$; una vez hecho eso, le añadía el beneficio del premio actual.
> 
> Porque la elección óptima se produjo en la fila anterior, por lo que lo único que falta es añadirle el beneficio actual. 
>  ---
> Es decir: $A[i,j] = \displaystyle\max_{1 \leq k \leq j-K}\Big(A[i-1,k]\Big) + r_j$ cuando $i > 1$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
\displaystyle\max_{1 \leq k \leq j}\Big(r_k\Big) & \text{si} & i = 0 \\
\displaystyle\max_{1 \leq k \leq j-K}\Big(A[i-1,k]\Big) + r_j & \text{si} & i \gt 1 \\
\end{array}
\right.
> $$

> [!attention] Observa que $1 \leq k \leq j-K$, lo que no tendría sentido para $j < K$.
> Esto lo que produce es que no exista el máximo en esas ocasiones, por lo que el valor sería solo $r_j$.
> Igualmente, al implementar el algoritmo en `Java`, el centinela del máximo empezaría en 0 y no ocurriría nada.

## 5 - Encontrar la configuración de la solución (extra)

|  $A$  | **0** | ==**1**==  | **2** | **3** | **4** | ==**5**==  | **6** | **7** | **8** | ==**9**==  |
|:-----:|:-----:|:----------:|:-----:|:-----:|:-----:|:----------:|:-----:|:-----:|:-----:|:----------:|
| **1** |   4   | ==**10**== | *10*  | *10*  | *10*  |     10     |  10   |  10   |  10   |     10     |
| **2** |   4   |     10     |  10   |  10   |  13   | ==**16**== | *16*  | *16*  | *17*  |     18     |
| **3** |   4   |     10     |  10   |  10   |  13   |     16     |  16   |  16   |  20   | ==**24**== |

> [!question] ¿Cómo recorro la tabla?
> Empezando en la última celda de la última fila, recorro la tabla hacia atrás, teniendo en cuenta:
> Siendo el beneficio óptimo el valor en $A[p,n+1]$, busco hacia atrás en la fila mientras el valor óptimo sea igual (porque entonces, el óptimo está más adelante).
> Si lo encuentro, almaceno el premio $i$ en la solución, le resto su beneficio al beneficio óptimo y repito el proceso en la columna obtenida, de la fila anterior.
> Cuando he llenado la solución de tamaño $p$, he terminado.

# Implementación en `Java`

```java
package Ejercicios.Complementarios;

import java.util.Arrays;



/**
 * Un desconocido (que dice ser familiar suyo, aunque no se parece en nada a ti)
 * se presenta ante ti montado en un DeLorean y te da un anuario con todos los
 * resultados de la quiniela del próximo año.
 * Sabemos también el premio 'r_i' que habrá en la semana 'i' (0 <= i <= 'n') si
 * se juega la quiniela ganadora en dicha semana.
 * Para no levantar sospechas, decidimos que no podremos ganar más de una vez
 * cada 'K' semanas consecutivas (0 < 'K'); es decir, debe haber al menos 'K-1'
 * semanas entre cada premio.
 *
 * Determinar cómo hay que jugar para maximizar la ganancia.
 *
 *
 * @author Antonio J. Galán Herrera
 */
public class Ejercicio_04 {

    // Datos del problema
    private static int[] r;     // Vector de premios semanales
    private static int n;       // Cantidad de semanas (y premios)
    private static int K;       // Semanas de margen para no ser sospechoso

    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
    private static int p;       // Cantidad de premios posibles según 'n' y 'K'
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        r = new int[] {4, 10, 3, 5, 9, 6, 4, 4, 7, 8};
        n = r.length;
        K = 4;
		
        // Datos del desarrollo
        p = premiosMaximos();   // Esta vez, falta cuántas filas tendrá 'A'
		
		
        // Mostrar los datos del problema
        System.out.println("Premios semanales: " + Arrays.toString(r));
        System.out.println("Margen de sospecha: " + K);
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:  " + Arrays.toString(solucion()));
        System.out.println("\tValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }
	
	
    // ------------------------------------------------------------------------
    // Algoritmo de programación dinámica
    // ------------------------------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    private static void rellenarTablaA() {
        // Inicializar la tabla
        A = new int[p][n];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < n; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = MAX[0 <= k <= j](r[j])                  si  j < K
     * A(i,j) = MAX[0 <= k <= j-K](A(i-1,k)) + r(j)     si  K <= j
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base: si se ganan 0 premios, el beneficio acumulado es 0
        if (i == 0) {
            int max = 0;
			
            // Encontrar el máximo hasta la semana actual
            for (int k = 0; k <= j; k++) {
                if (max < r[k]) {
                    max = r[k];
                }
				
                A[i][j] = max;
            }
			
        // Caso iterativo
        } else {
            int max = 0;
			
            for (int k = 0; k <= j-K; k++) {
                int aux = A[i-1][k];
				
                if (max < aux) {
                    max = aux;
                }
            }
			
            A[i][j] = max + r[j];
        }
    }
	
    /**
     * Calcula qué premios deben ganarse usando el anuario.
     *
     * @return  Vector con los premios a los que apuntar, en orden
     */
    private static int[] solucion() {
        int[] S = new int[p];           // Vector con la distribución de premios
        int beneficio = valor();        // Beneficio máximo
		
        // Recorrer la tabla 'A' desde la solución (última fila) hasta el inicio
        for (int i = S.length-1; i >= 0; i--) {
            int k = 0;
			
            // Encontrar la semana en la que se gana el beneficio del premio 'i'
            while (A[i][k] < beneficio) {
                k++;
            }
			
            S[i] = r[k];        // Se añade el premio a la solución
            beneficio -= r[k];  // Se resta el beneficio al beneficio total
        }
		
        return S;
    }
	
    /**
     * Calcula el máximo beneficio que se puede obtener
     * con el anuario y la restricción de semanas 'K'.
     *
     * Se corresponde con la última celda de la tabla 'A'.
     *
     * @return  Máximo beneficio
     */
    private static int valor() {
        return A[p-1][n-1];
    }
	
	
    // ------------------------------------------------------------------------
    // Funciones auxiliares
    // ------------------------------------------------------------------------
	
    /**
     * Calcula el máximo número de premios que se pueden obtener de un anuario.
     *
     * @return  El máximo número de premios que se pueden obtener de un anuario
     */
    private static int premiosMaximos() {
        int premios = 0;
        int recorrido = 0;
		
        do {
            premios++;
            recorrido += K;
			
        } while (recorrido < n);
		
        return premios;
    }
	
	
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
            sb.append("\n").append(fil+1).append("\t");
			
            for (int col = 0; col < tabla[0].length; col++) {
                sb.append(tabla[fil][col]).append("\t");
            }
        }
		
        return sb.toString();
    }
}
```
