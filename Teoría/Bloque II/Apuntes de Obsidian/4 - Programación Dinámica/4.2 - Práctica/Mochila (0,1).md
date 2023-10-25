---
tags: [tema4/diapositivas, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Cambio de Monedas]]
> - [[Desequilibrio Mínimo]]
> - [[Ladrón de Joyas]]

# Problema

Sean $n$ items de pesos conocidos $p_1, p_2, \dots, p_n$ y de valores $v_1, v_2, \dots, v_n$, y dada una mochila de capacidad $C$.

- Se considera que los pesos de los items y capacidad de la mochila son enteros positivos.
- Se considera que los valores de los items son números reales.

> [!todo] Encontrar el subconjunto de items de más valor que caben en la mochila.

# Solución
## 1 - Dimensiones relevantes

Planteadas por mí:

- $X = \{x_1, x_2, \dots, x_n\}$: conjunto de items.
- $S = \{s_1, s_2, \dots, s_m\} \subseteq X$: conjunto de ítems solución, formado por ítems de $X$.
- $V_S = \displaystyle\sum_{i=1}^{m} \left( s_i \right)$: valor de la solución (suma de los valores de los items).

Ofrecidas por el enunciado:

- $C$: la capacidad de la mochila.
- $p_i$: el peso de $x_i$.
- $v_i$: el valor de $x_i$.
- $n$: la cantidad de objetos.

> [!info] Siendo $0 \leq i,m \leq n$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. $X = \{ \ \} = \emptyset$ pero $C \in \mathbb{N}$ :
	- Si $n = 0$, entonces $S = \{ \ \} = \emptyset$ y $V_S = 0$.
2. $X = \{x_1, x_2, \dots, x_n\}$ pero $C = 0$ :
	- Si $C = 0$, entonces $S = \{ \ \} = \emptyset$ y $V_S = 0$.

> [!cite] Se definirán como `-` en la estructura, porque *no se puede tratar con $\emptyset$*.
> También podría definirse como `0`, interpretando que pueden añadirse 0 items a una mochila sin capacidad, o bien introducir 0 items si no hay items que meter.

## 3 - Encontrar la recurrencia

### Plantear un ejemplo particular

Una mochila de capacidad $C = 5$ y estos ítems:

| **$x_i$** | **$p_i$** | **$v_i$** |
|:---------:|:---------:|:---------:|
|   **1**   |     2     |    12     |
|   **2**   |     1     |    10     |
|   **3**   |     3     |    20     |
|   **4**   |     2     |    15     |

> [!success] La solución óptima es $S = \{1,2,4\}$.
> Aunque el valor no se pida, $V_S = 37$.

### Definir la estructura a utilizar

> [!info] Sean $X_i$, $P_i$ y $V_i$ subconjuntos de $X$, $P$ y $V$ respectivamente, y $0 \leq c \leq C$.

Planteo una **tabla** de tamaño $n+1$ x $C+1$.

|        Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| ----------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|          **{ }** |       |       |       |       |       |       |
|          **{1}** |       |       |       |       |       |       |
|       **{1, 2}** |       |       |       |       |       |       |
|    **{1, 2, 3}** |       |       |       |       |       |       |
| **{1, 2, 3, 4}** |       |       |       |       |       |       |

> [!cite] $A[i,c]$ representa el *$V_S$ hasta el momento actual*.
>
> ---
> Dada dicha definción, se destaca que $X_1 \neq 1$, porque:
> - $X_i: X_0 = \{ \ \}, X_1 = \{1\}, \dots, X_4 = \{1,2,3,4\} = X$.
> - $x_i: \ x_0 = \nexists, \ x_1 = 1, \ x_2 = 2, \ \dots, \ x_4 = 4$

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, los casos base es que se intenten meter items en una mochila sin capacidad, o que no haya items que meter:

|        Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| ----------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|          **{ }** |   -   |   -   |   -   |   -   |   -   |   -   |
|          **{1}** |   -   |       |       |       |       |       |
|       **{1, 2}** |   -   |       |       |       |       |       |
|    **{1, 2, 3}** |   -   |       |       |       |       |       |
| **{1, 2, 3, 4}** |   -   |       |       |       |       |       |

> [!cite] Casos base
> Se usarán para indicar que la mochila se ha llenado.

### Representar los casos iterativos

> [!info] *Esta tabla* se rellena de izquierda a derecha y de arriba a abajo.

Fila $A[1,\_]$:

|        Tabla $A$ | **0** | **1** | **2**  | **3** | **4** | **5** |
| ----------------:|:-----:|:-----:|:------:|:-----:|:-----:|:-----:|
|          **{ }** |  *-*  |  *-*  |  *-*   |  *-*  |  *-*  |  *-*  |
|          **{1}** |  *-*  |   0   | ==12== |  12   |  12   |  12   |
|       **{1, 2}** |  *-*  |       |        |       |       |       |
|    **{1, 2, 3}** |  *-*  |       |        |       |       |       |
| **{1, 2, 3, 4}** |  *-*  |       |        |       |       |       |

> [!cite] Se mete un item nuevo la mochila.
> $A[1,2]$: si $C = 2$ lo mejor es meter $x_1 : (p_1, v_1) = (2, 12)$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
> ---
> $X_1 = \{1\}$
> $S_1 = \{1\}$ (mete $x_1$)
> $C = 3$

Fila $A[2,\_]$:

|        Tabla $A$ | **0** | **1**  | **2** | **3**  | **4** | **5** |
| ----------------:|:-----:|:------:|:-----:|:------:|:-----:|:-----:|
|          **{ }** |  *-*  |  *-*   |  *-*  |  *-*   |  *-*  |  *-*  |
|          **{1}** |  *-*  |   0    |  12   |   12   |  12   |  12   |
|       **{1, 2}** |  *-*  | ==10== |  12   | ==22== |  22   |  22   |
|    **{1, 2, 3}** |  *-*  |        |       |        |       |       |
| **{1, 2, 3, 4}** |  *-*  |        |       |        |       |       |

> [!cite] Se mete un item nuevo la mochila.
> $A[2,1]$: si $C = 1$ lo mejor es meter $x_2 : (p_2, v_2) = (1, 10)$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
>
> $A[2,3]$: si $C = 3$ lo mejor es añadir $x_2 : (p_2, v_2) = (1, 10)$ a $S_1$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
> ---
> $X_2 = \{1,2\}$
> $S_2 = \{2\}$ (mete $x_2$ porque $\{1\} \lt \{2\}$)
> $S_2 = \{1,2\}$ (añade $x_2$ porque $\{1\} \lt \{1,2\}$)
> $C = 2$

Fila $A[3,\_]$:

|        Tabla $A$ | **0** | **1** | **2** | **3** | **4**  | **5**  |
| ----------------:|:-----:|:-----:|:-----:|:-----:|:------:|:------:|
|          **{ }** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |  *-*   |
|          **{1}** |  *-*  |   0   |  12   |  12   |   12   |   12   |
|       **{1, 2}** |  *-*  |  10   |  12   |  22   |   22   |   22   |
|    **{1, 2, 3}** |  *-*  |  10   |  12   |  22   | ==30== | ==32== |
| **{1, 2, 3, 4}** |  *-*  |       |       |       |        |        |

> [!cite] Se mete un nuevo item en la mochila.
> $A[3,4]$:  si $C = 4$ lo mejor es añadir $x_3 : (p_3, v_3) = (3, 20)$ a $S_2$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
>
> $A[3,5]$: si $C = 5$ lo mejor es añadir $x_3 : (p_3, v_3) = (3, 20)$ a $S_2$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
> ---
> $X_3 = \{1,2,3\}$
> $S_3 = \{2,3\}$ (añade $x_3$ porque $\{1,2\} \lt \{2,3\}$)
> $S_3 = \{1,3\}$ (añade $x_3$ porque $\{1,2\} \lt \{1,3\}$)
> $C = 2$

Fila $A[4,\_]$:

|        Tabla $A$ | **0** | **1** | **2**  | **3**  | **4** | **5**  |
| ----------------:|:-----:|:-----:|:------:|:------:|:-----:|:------:|
|          **{ }** |  *-*  |  *-*  |  *-*   |  *-*   |  *-*  |  *-*   |
|          **{1}** |  *-*  |   0   |   12   |   12   |  12   |   12   |
|       **{1, 2}** |  *-*  |  10   |   12   |   22   |  22   |   22   |
|    **{1, 2, 3}** |  *-*  |  10   |   12   |   22   |  30   |   32   |
| **{1, 2, 3, 4}** |  *-*  |  10   | ==15== | ==25== |  30   | ==37== |

> [!cite] Se mete un nuevo item en la mochila.
> $A[4,2]$: si $C = 2$ lo mejor es meter $x_4 : (p_4, v_4) = (2, 15)$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
>
> $A[4,3]$: si $C = 3$ lo mejor es añadir $x_4 : (p_4, v_4) = (2, 15)$ a $S_3$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
>   
> $A[4,5]$: si $C = 5$ lo mejor es añadir $x_4 : (p_4, v_4) = (2, 15)$ a $S_3$.
> - Se comprueba que el objeto cabe y su valor mejora el valor acumulado.
> ---
> $X_4 = \{1,2,3,4\}$
> $S_4 = \{4\}$ (mete $x_4$ porque $\{2\} \lt \{4\}$)
> $S_4 = \{1,4\}$ (añade $x_4$ porque $\{1,2\} \lt \{1,4\}$)
> $S_4 = \{1,2,4\}$ (añade $x_4$ porque $\{1,2\} \lt \{1,2,4\}$)
> $C = 0$

### Obtener la tabla completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

|        Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5**  |
| ----------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:------:|
|          **{ }** |  *-*  |  *-*  |  *-*  |  *-*  |  *-*  |  *-*   |
|          **{1}** |  *-*  |   0   |  12   |  12   |  12   |   12   |
|       **{1, 2}** |  *-*  |  10   |  12   |  22   |  22   |   22   |
|    **{1, 2, 3}** |  *-*  |  10   |  12   |  22   |  30   |   32   |
| **{1, 2, 3, 4}** |  *-*  |  10   |  15   |  25   |  30   | **37** |
^327e2a

> [!important] Esto indica que, *para el ejemplo creado*, $V_S = 37$.
> Es decir, que el valor óptimo de la mochila es 37.

Sin embargo, el problema pide *la configuración solución* (qué objetos producen ese valor).

> [!cite] Cabe destacar que en este caso, se ha obtenido durante el proceso.
> La configuración es $S = \{1,2,4\}$.
> Sin embargo, también puede obtenerse de la propia tabla, como se verá más abajo.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Poniendo un `-` (o un $0$) en la fila y columna inicial.
>
> Porque representan que la mochila está llena o que no hay más ítems que meter.
>
> ---
> Es decir: $A[i,c] = 0$ cuando $i = 0 \vee c = 0$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Teniendo en cuenta que todos esos casos ocurren cuando $i,c \gt 0$...
>  ---
>  Si hay espacio en la mochila, miraba el valor $A[i-1,c-p_i]$: si metiendo el item mejoraba ese valor, lo añadía y reducía la capacidad de la mochila en función de $p_i$; en caso contrario, no añado el item, por lo que colocaba el mismo valor que $A[i-1,c]$.
>
> Porque hay que comprobar qué es mejor: tener capacidad disponible en la mochila y rellenarlo ($A[i-1,c-p_i] + v_i$) o bien mantener la mochila como estaba ($A[i-1,c]$) y añadir otro item más tarde.
>  ---
>  Es decir: $A[i,c] = \max\left( A[i-1,c-p_i] + v_i, A[i-1,c] \right)$ cuando $i,j \gt 0$.
>  ---
> Si no hay espacio en la mochila, colocaba el valor $A[i-1,c]$ directamente.
>
> Porque si $x_i$ no cabe, entonces el valor $V_S$ óptimo sigue siendo el que tuviera la mochila con esa capacidad, pero sin $x_i$.
>  ---
> Es decir: $A[i,c] = A[i-1,c]$ cuando $i,j \gt 0 \wedge p_i \gt c$.

Habiendo analizado las preguntas anteriores, que surgen y se responden durante el proceso de colocación de valores en la tabla, se obtiene la [[Ecuación de Bellman]].

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 0 & \vee & c = 0 \\
A[i-1,c] & \text{si} & i,j \gt 0 & \wedge & p_i \gt c \\
\max\left( A[i-1,c-p_i] + v_i, A[i-1,c] \right) & \text{si} & i,j \gt 0 & \wedge & p_i \leq c \\
\end{array}
\right.
> $$

> [!cite] El ejercicio finaliza habiendo planteado la [[Ecuación de Bellman]].
> Los pasos descritos en el proceso aportan la validez necesaria al resultado.

## 5 - Encontrar la configuración de la solución (extra)

> [!cite] Como se mencionó [[4 - Programación Dinámica/Cómo resolver un problema#^d36d0e|anteriormente]], este es un paso circunstancial.
> El ejercicio pide **los items**, por lo que además de decir el *cómo **genero** la solución* con la [[Ecuación de Bellman]], también debo indicar *cómo **construyo** la solución*.

> [!cite] Durante este paso seguiré usando mi ejemplo particular, por comodidad.

Partiendo de la tabla obtenida al final del [[4 - Programación Dinámica/4.2 - Práctica/Mochila (0,1)#Obtener la tabla completa|paso 3]], será necesario averiguar cómo desde la *celda final*, se puede obtener la configuración de la solución pedida.

![[4 - Programación Dinámica/4.2 - Práctica/Mochila (0,1)#^327e2a]]

- Marcaré como ==resaltado== los *objetos* y sus *valores*.
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

|            Tabla $A$ | **0** | **1** |   ==**2**==   |   ==**3**==   | **4** | ==**5**==  |
| --------------------:|:-----:|:-----:|:-------------:|:-------------:|:-----:|:----------:|
|              **{ }** |  *-*  |  *-*  |      *-*      |      *-*      |  *-*  |     *-*    |
|          **{==1==}** |  *-*  |   0   |  ==**12**==   |      12       |  12   |     12     |
|       **{1, ==2==}** |  *-*  |  10   |      12       |  ==**22**==   |  22   |     22     |
|        **{1, 2, 3}** |  *-*  |  10   |      12       |    **22**     |  30   |     32     |
| **{1, 2, 3, ==4==}** |  *-*  |  10   |      15       |      25       |  30   | ==**37**== |

> [!question] ¿Cómo recorro la tabla?
> Si la celda anterior $A[i-1,c]$ tiene un valor:
> - Menor, entonces el mejor valor es el actual, por lo que añado este item a la solución y me muevo a la columna de la capacidad restante de la fila anterior (porque al haber metido el item, la capacidad cambia y ya no cuento ese item).
> - Igual, entonces ese es un valor más óptimo, porque tiene el mismo valor que ahora, pero habiendo contemplado un item menos: sin $x_i$.

# Implementación en `Java`

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * PROBLEMA:
 * Sea una mochila con una determinada capacidad y un conjunto
 * de obejtos que poseen un peso y valor concretos cada uno.
 *
 * Se desea encontrar qué objetos se pueden colocar en la mochila,
 * de forma que su valor sea máximo.
 *
 * EJEMPLO:
 * Una mochila de capacidad 5, con objetos cuyos atributos son:
 * - Pesos:     P = {2, 1, 3, 2}
 * - Valores:   V = {12, 10, 20, 15}
 *
 *      SOLUCIÓN: {1, 2, 4} cuyo valor es 37
 *
 */
public class Mochila_01 {
	
    // Datos del problema
    private static int[] P;     // Pesos de los objetos
    private static int[] V;     // Valores de los objetos
    private static int C;       // Capacidad de la mochila
	
    // Datos del desarrollo
    private static int[][] A;   // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        P = new int[]{ 2,  1,  3,  2};
        V = new int[]{12, 10, 20, 15};
        C = 5;
		
		
        // Mostrar los datos del problema
        System.out.println("Pesos:     " + Arrays.toString(P));
        System.out.println("Valores:   " + Arrays.toString(V));
        System.out.println("Capacidad: " + C);
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("\nSolución:  " + solucion(A));
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
        int n = P.length+1, c = C+1;    // +1: items vacíos y mochila llena
        A = new int[n][c];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila
     * y columna concretas de la tabla 'A'.
     *
     * A(i,j) = 0                                           si  <...>
     * A(i,j) = A(i-1,c)                                    si  <...>
     * A(i,j) = MAX[A(i-1,c), A(i-1,c-P(i-1)) + V(i-1)]     si  <...>
     *
     * @param i     Fila de la tabla 'A'
     * @param c     Columna de la tabla 'A'
     */
    private static void bellman(int i, int c) {
        // Caso base
        if (i == 0 || c == 0) {
            A[i][c] = 0;
			
        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (P[i-1] > c) {
                A[i][c] = A[i-1][c];
            } else {
                A[i][c] = Math.max(A[i-1][c], A[i-1][c-P[i-1]] + V[i-1]);
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
    private static List<Integer> solucion(int[][] A) {
        // Inicializar los índices
        int i = P.length, c = C;
		
        // Inicializar la solución
        List<Integer> S = new ArrayList<>();
		
        // Recorrer la tabla generando la solución óptima
        while (i > 0 && c > 0) {
            // Si el valor acumulado es mejor que el anterior
            if (A[i][c] != A[i-1][c]) {
                S.add(i);               // Se añade el item a la solución
                c -= P[i-1];            // Se ajusta el peso de la mochila
            }
			
            i--;                        // Se descarta el item
        }
		
        // Ordenar la solución
        S.sort(Integer::compareTo);     // Porque se añadieron al revés
        
        // Devolver la solución óptima
        return S;
    }
	
    /**
     * Indica el valor óptimo de la solución
     * a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Valor óptimo
     */
    private static int valor(int[][] A) {
        return A[P.length][C];              // Última celda de la tabla
    }
}
```

> [!cite] A continuación dejo un método propio para visualizar la tabla.
> No es necesario, pero lo uso para *debbugear* mi implementación.

```java
/**
 * Genera una representación de una tabla con sus índices como cabeceras.
 */
private static String mostrarTabla(int[][] tabla) {
	StringBuilder sb = new StringBuilder();
	
	// Cabeceras
	sb.append("\t");
	
	for (int cabecera = 0; cabecera <= C; cabecera++) {
		sb.append(cabecera).append("\t");
	}
	
	// Filas
	for (int fil = 0; fil <= P.length; fil++) {
		sb.append("\n");
		
		// Cabecera de la fila
		if (fil == 0) {
			sb.append("∅");     // Conjunto de items vacío
		} else {
			sb.append(fil);     // Índice del item
		}
		
		sb.append("\t");
		
		// Valores de las columnas
		for (int col = 0; col <= C; col++) {
			sb.append(tabla[fil][col]).append("\t");
		}
	}
	
	return sb.toString();
}
```
