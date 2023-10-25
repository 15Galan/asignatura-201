---
tags: [tema4/diapositivas, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[4 - Programación Dinámica/4.2 - Práctica/Mochila (0,1)|Mochila (0,1)]]

# Problema

Se supone un suministro ilimitado de monedas de $n$ denominaciones diferentes $d_1, d_2, \dots, d_n$.
Se quiere pagar una cantidad $M$.

> [!todo] ¿Cuál es la forma de hacerlo empleando el *número mínimo* de monedas?

# Solución

## 1 - Dimensiones relevantes

Planteadas por mí:

- $D = \{d_1, d_2, \dots, d_n\}$: conjunto de denominaciones.
- $S = \{s_1, s_2, \dots, s_n\}$: conjunto de cantidades solución.
	- $s_i$: cantidad de monedas de la denominación $d_i$.
- $V_S = \displaystyle\sum_{i=1}^{n} \left( s_i \right)$ : valor de la solución (suma de las cantidades de monedas).

Planteadas por el enunciado:

- $M$: la cantidad a pagar.
- $d_i$: la denominación $i$.
- $n$: la cantidad de denominaciones.

> [!info] Siendo $0 \leq i \leq n$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. $D = \{ \ \} = \emptyset$ pero $M \in \mathbb{N}$:
	- Si $n = 0$, entonces $S = \{ \ \} = \emptyset$ y $V_S = 0$.
2. $D = \{d_1, d_2, \dots, d_n\}$ pero $M = 0$:
	- Si $M = 0$, entonces $S = \{ \ \} = \emptyset$ y $V_S = 0$.

> [!cite] Se definirán como $\infty$ y $0$ en la estructura.
> Al contrario que en los otros [[Inicio#Diapositivas|ejercicios de las diapositivas]], aquí merece la pena usar estos valores (en lugar de todo `-` o todo `0`), porque:
> - $\infty$: a medida que se añaden soluciones, el número de monedas debe bajar; por tanto, si el $V_S$ óptimo es un mínimo, debe partir de valores grandes.
> - $0$: a medida que se añaden soluciones, el precio se va pagando; por tanto, el precio mínimo (y final) debe ser $0$.

## 3 - Encontrar la relación de recurrencia

### Plantear un ejemplo particular

Pagar un precio de $8$, con monedas cuyas denominaciones son $D = \{1, 4, 6\}$.

> [!success] La solución óptima es $S = \{0, 2, 0\}$.
> Aunque el valor no se pida, $V_S = 2$.
> - $0$ de 1
> - $2$ de 4
> - $0$ de 6.

### Definir la estructura a utilizar

> [!info] Sean $D_i$ subconjunto de $D$, y $0 \leq m \leq M$.

Planteo una **tabla** de tamaño $n+1$ x $M+1$.

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |       |       |       |       |       |       |       |       |       |
|       **{1}** |       |       |       |       |       |       |       |       |       |
|    **{1, 4}** |       |       |       |       |       |       |       |       |       |
| **{1, 4, 6}** |       |       |       |       |       |       |       |       |       |

> [!cite] $A[i,m]$ representa el *$V_S$ hasta el momento actual*.
> La *cantidad* óptima de monedas.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, los casos base es que se intente pagar un precio sin denominaciones, o que no haya denominaciones con las que pagar:

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |   ∞   |   ∞   |   ∞   |   ∞   |   ∞   |   ∞   |   ∞   |   ∞   |   ∞   |
|       **{1}** |   0   |       |       |       |       |       |       |       |       |
|    **{1, 4}** |   0   |       |       |       |       |       |       |       |       |
| **{1, 4, 6}** |   0   |       |       |       |       |       |       |       |       |

> [!cite] Casos base
> Se usarán para indicar que el precio se ha pagado.

### Representar los casos iterativos

> [!info] *Esta tabla* se rellena de izquierda a derecha y de arriba a abajo.

Fila $A[1,m]$:

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |
|       **{1}** |  *0*  |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |
|    **{1, 4}** |  *0*  |       |       |       |       |       |       |       |       |
| **{1, 4, 6}** |  *0*  |       |       |       |       |       |       |       |       |

> [!cite] Se paga con la denominación $d_1 = 1$.
> Se comprueba que puede pagarse el precio con las monedas disponibles.
> ---
> $D_1 = \{1\}$
> $S_1 = \{8, 0, 0\}$
> $V_S = 8$

Fila $A[2,m]$:

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |
|       **{1}** |  *0*  |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |
|    **{1, 4}** |  *0*  |   1   |   2   |   3   | ==1== |   2   |   3   |   4   | ==2== |
| **{1, 4, 6}** |  *0*  |       |       |       |       |       |       |       |       |

> [!cite] Se paga con la denominación $d_2 = 4$.
> $A[2,4]$: si $M = 4$ lo mejor es usar 1 moneda de $4$.
> - Se comprueba que puede pagarse el precio y usarla mejore el valor acumulado.
> 
> $A[2,8]$: si $M = 8$ lo mejor es usar 2 monedas de $4$.
> - Se comprueba que puede pagarse el precio y usarla mejore el valor acumulado.
> ---
> $D_2 = \{1, 4\}$
> $S_2 = \{0, 2, 0\}$ (porque $\{8, 0, 0\} > \{0, 2, 0\}$)
> $V_S = 2$ (porque $8 > 2$)

Fila $A[3,m]$:

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |
|       **{1}** |  *0*  |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |
|    **{1, 4}** |  *0*  |   1   |   2   |   3   |   1   |   2   |   3   |   4   |   2   |
| **{1, 4, 6}** |  *0*  |   1   |   2   |   3   |   1   |   2   | ==1== |   2   | ==2== |

> [!cite] Se paga con la denominación $d_3 = 6$.
> $A[3,6]$: si $M = 6$ lo mejor es usar 1 moneda de $6$.
> - Se comprueba que puede pagarse el precio y usarla mejore el valor acumulado.
> 
> $A[3,8]$: si $M = 8$ lo mejor es usar 2 monedas de $4$.
> - Se comprueba que puede pagarse el precio y usarla mejore el valor acumulado.
> ---
> $D_3 = \{1, 4, 6\}$
> $S_3 = \{0, 2, 0\}$ (porque $\{2, 0, 1\} > \{0, 2, 0\}$)
> $V_S = 2$ (porque $2 < 3$)

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |  *∞*  |
|       **{1}** |  *0*  |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |
|    **{1, 4}** |  *0*  |   1   |   2   |   3   |   1   |   2   |   3   |   4   |   2   |
| **{1, 4, 6}** |  *0*  |   1   |   2   |   3   |   1   |   2   |   1   |   2   | **2** |
^9649ec

> [!important] Esto indica que, *para el ejemplo creado*, $V_S = 2$.
> Es decir, que la cantidad óptima son *2 monedas*.

Sin embargo, el problema pide *la configuración solución* (cuántas monedas de cada denominación producen ese valor).

> [!cite] Cabe destacar que en este caso, se ha obtenido durante el proceso.
> La configuración es $S = \{0, 2, 0\}$.
> Sin embargo, también puede obtenerse de la propia tabla, como se verá más abajo.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Poniendo un $\infty$ o un $0$ en la fila y columna inicial.
>
> Porque representan que el precio ya se ha pagado.
>
> ---
> Es decir: $A[i,m] = 0$ cuando $i = 0 \vee m = 0$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Teniendo en cuenta que todos esos casos ocurren cuando $i,m \gt 0$...
>  ---
>  Si puede pagarse el precio, miraba qué tenía un mejor valor, usar esa moneda o no, porque si no teniendo en cuenta la moneda actual el valor no mejora, entonces usarla no es óptimo.
>  ---
>  Es decir: $A[i,m] = \min\left( A[i-1,m], A[i, m-d_i] + 1 \right)$ cuando $i,m \gt 0 \wedge d_i \leq m$.
>  ---
> Si no puede pagarse el precio, colocaba el valor $A[i-1,m]$ directamente.
>
> Porque si $d_i$ es mayor que el precio, entonces el valor $V_S$ óptimo sigue siendo el que se usó para pagar el mismo precio, pero sin $d_i$.
>  ---
> Es decir: $A[i,m] = A[i-1,m]$ cuando $i,m \gt 0 \wedge d_i \gt m$.

Habiendo analizado las preguntas anteriores, que surgen y se responden durante el proceso de colocación de valores en la tabla, se obtiene la [[Ecuación de Bellman]].

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,m] = \left\{
\begin{array}{cl}
\infty & \text{si} & i = 0 & & \\
0 & \text{si} & i \gt 0 & \wedge & m = 0\\
A[i-1,m] & \text{si} & i,m \gt 0 & \wedge & d_i \gt m \\
\min\left( A[i-1,m], A[i, m-d_i] + 1 \right) & \text{si} & i,m \gt 0 & \wedge & d_i \leq m \\
\end{array}
\right.
> $$

> [!cite] El ejercicio finaliza habiendo planteado la [[Ecuación de Bellman]].
> Los pasos descritos en el proceso aportan la validez necesaria al resultado.

## 5 - Encontrar la configuración de la solución (extra)

> [!cite] Como se mencionó [[4 - Programación Dinámica/Cómo resolver un problema#^d36d0e|anteriormente]], este es un paso circunstancial.
> El ejercicio pide **las cantidades de monedas de cada denominación**, por lo que además de decir el *cómo **genero** la solución* con la [[Ecuación de Bellman]], también debo indicar *cómo **construyo** la solución*.

> [!cite] Durante este paso seguiré usando mi ejemplo particular, por comodidad.

Partiendo de la tabla obtenida al final del [[Cambio de Monedas#Obtener la estructura completa|paso 3]], será necesario averiguar cómo desde la *celda final*, se puede obtener la configuración de la solución pedida.

![[Cambio de Monedas#^9649ec]]

- Marcaré como ==resaltado== las *denominaciones* y el *precio*.****
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

|     Tabla $A$ | ==**0**== | **1** | **2** | **3** |   ==**4**==   | **5** | **6** | **7** |   ==**8**==   |
| -------------:|:---------:|:-----:|:-----:|:-----:|:-------------:|:-----:|:-----:|:-----:|:-------------:|
|       **{ }** |    *∞*    |  *∞*  |  *∞*  |  *∞*  |      *∞*      |  *∞*  |  *∞*  |  *∞*  |      *∞*      |
|       **{1}** |    *0*    |   1   |   2   |   3   |       4       |   5   |   6   |   7   |       8       |
|    **{1, 4}** |  ==*0*==  |   1   |   2   |   3   |   ==**1**==   |   2   |   3   |   4   |   ==**2**==   |
| **{1, 4, 6}** |    *0*    |   1   |   2   |   3   |       1       |   2   |   1   |   2   |     **2**     |

> [!question] ¿Cómo recorro la tabla?
> Si la celda anterior $A[i-1,m]$ tiene un valor mayor, entonces el mejor valor es el actual, por lo que sumo un uso de la denominación de $d_i$ y me muevo a la columna del precio restante de la fila anterior (porque al haber usado una denominación, el precio cambia, pero sigo contando con dicha denominación).
>
> Si la celda tiene un valor igual, entonces ese es un valor más óptimo, porque tiene el mismo valor que ahora, pero habiendo contemplado una denominación menos: sin $d_i$.

# Implementación en `Java`

> [!attention] Este ejercicio se ha implementado de 2 formas diferentes.
> La segunda implementación es funcional, pero menos expandida que el método que uso actualmente: `rellenarTablaA()` con `bellman()` (y `mostrarTabla()`).
> La incluyo por interés

## Implementación A

```java
import java.util.Arrays;



/**
 * PROBLEMA:
 * Pagar un precio con la mínima cantidad de monedas posibles,
 * habiendo monedas de diferente valor.
 *
 * EJEMPLO 1:
 * Pagar 8 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 2 monedas (cada una de 4 u).
 *
 * EJEMPLO 2:
 * Pagar 11 unidades con monedas que valen 1, 4 o 6 unidades.
 *
 *      SOLUCIÓN: 3 monedas (una de 6 u, otra de 4 u y otra de 1 u).
 */
public class Monedas {
	
    // Datos del problema
    private static int[] D;     // Denomincaciones
    private static int M;       // Precio a pagar
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        D = new int[]{1, 4, 6};
        M = 8;
		
		
        // Mostrar los datos del problema
        System.out.println("Monedas: " + Arrays.toString(D));
        System.out.println("Precio:  " + M);
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución: " + Arrays.toString(solucion(A)));
        System.out.println("\tValor: " + valor(A));
		
		
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
        int n = D.length+1, c = M+1;    // +1: denominaciones
        A = new int[n][c];              // vacías y precio pagado
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,m) = 0                                   si  <...>
     * A(i,m) = A(i-1,m)                            si  <...>
     * A(i,m) = MIN[A(i-1,m), A(i,m-P(i-1)) + 1]    si  <...>
     *
     * @param i     Fila de la tabla 'A'
     * @param m     Columna de la tabla 'A'
     */
    private static void bellman(int i, int m) {
        // Caso base 1
        if (i == 0) {
            A[i][m] = Integer.MAX_VALUE;    // Representa '∞'
			
        // Caso base 2
        } else if (i > 0 && m == 0) {
            A[i][m] = 0;
			
        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (D[i-1] > m) {
                A[i][m] = A[i-1][m];
            } else {
                A[i][m] = Math.min(A[i-1][m], A[i][m-D[i-1]] + 1);
            }
        }
    }
	
    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    private static int[] solucion(int[][] A) {
        // Inicializar los índices
        int i = D.length, m = M;
		
        // Inicializar la solución
        int[] S = new int[D.length];    // Inicializado a '0'
		
        // Recorrer la tabla generando la solución óptima
        while (i > 0 && m > 0) {
            // Si el valor acumulado es menor que el anterior (mejor)
            if (A[i][m] < A[i-1][m]) {
                S[i-1]++;               // Se suma una moneda
                m -= D[i-1];            // Se resta el precio pagado
				
            // Si no, el valor acumulado mejor es el anterior
            } else {
                i--;                    // Se resta el item a la solución
            }
        }
		
        // Devolver la solución óptima
        return S;
    }
	
    /**
     * Genera el valor óptimo a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Valor óptimo
     */
    private static int valor(int[][] A) {
        return A[D.length][M];              // Última celda de la tabla
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
 * Genera una representación de una tabla con sus índices como cabeceras.
 */
private static String mostrarTabla(int[][] tabla) {
	StringBuilder sb = new StringBuilder();
	
	// Cabeceras
	sb.append("\t");
	
	for (int cabecera = 0; cabecera <= M; cabecera++) {
		sb.append(cabecera).append("\t");
	}
	
	// Filas
	for (int fil = 0; fil <= D.length; fil++) {
		sb.append("\n");
		
		// Cabecera de la fila
		if (fil == 0) {
			sb.append("0");
			
		} else {
			sb.append(D[fil-1]);
		}
		
		sb.append("\t");
		
		// Valores de las columnas
		for (int col = 0; col <= M; col++) {
			int valor = tabla[fil][col];
			
			// Sustituir el número 'MAX_VALUE' por '∞'
			if (valor == Integer.MAX_VALUE) {
				sb.append("∞");
				
			} else {
				sb.append(valor);
			}
			
			sb.append("\t");
		}
	}
	
	return sb.toString();
}
```

## Implementación B

Al contrario que la implementación anterior, esta es un *método*, no una clase.

```java
/**
 * Calcula la cantidad mínima de monedas que se necesitan
 * para pagar un precio, siendo las monedas de diferente valor.
 *
 * @param precio    Precio a pagar
 * @param monedas   Vector con los valores de las monedas
 *
 * @return  La cantidad mínima de monedas para pagar el precio
 */
private static int devolucionMinima(int precio, int[] monedas){
	// Crear la tabla de devoluciones
	int[][] tabla = new int[monedas.length+1][precio+1];
	
	// Rellenar la columna 0 con 'ceros'
	for (int i = 0; i < monedas.length; i++) {
		tabla[i][0] = 0;
	}
	
	// Rellenar la fila 0 (salvo la columna 0) con 'infinitos'
	for (int j = 1; j <= precio; j++) {
		tabla[0][j] = 999999;
	}
	
	for (int i = 1; i <= monedas.length ; i++) {
		for (int j = 1; j <= precio; j++) {
			if (monedas[i-1] > j) {
				tabla[i][j] = tabla[i-1][j];
				
			} else {
				// Calcular el mínimo de las 2 posiciones y guardarlo en la tabla
				tabla[i][j] = Math.min(tabla[i-1][j], tabla[i][j-monedas[i-1]]+1);
			}
		}
	}
	
	return tabla[monedas.length][precio];
}
```
