---
aliases: ["Ejercicio 07"]
tags:    [tema4/relación, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[4 - Programación Dinámica/4.2 - Práctica/Mochila (0,1)|Mochila (0,1)]]

# Problema

Dado un conjunto de $n$ objetos, cada uno con un peso $P = \{p_1, p_2, \dots, p_n\}$, supongamos que queremos repartir los objetos en dos montones diferentes, de manera que queden lo más equilibrados posible en peso.

Para conocer el desequilibrio mínimo queremos aplicar un algoritmo de Programación Dinámica.

> [!todo] Encontrar la solución y su desequilibrio mínimo.

## Ejemplo del problema

Sean unos pesos $P = \{2, 1, 3, 4\}$.

> [!info] Una solución es $S = \Big\{\{1,2,3\}, \{4\}\Big\}$.
> Esta solución no es óptima.

# Solución

## 1 - Dimensiones relevantes

Planteada por mí:

- $T = \displaystyle\sum^{n}_{i = 1} \Big( p_i \Big)$: peso total a repartir.
- $M = \displaystyle\frac{T}{2}$: peso ideal de un montón para que ambos estén equilibrados.
- $S = \{A,B\}$: conjunto solución (donde $A \cup B = P$ tal que $A \cap B = \emptyset$).
	- $A$, $B$: montones.
- $V_S = |A_T - B_T|$: desequilibrio mínimo (donde $X_T = \displaystyle\sum^{m}_{i = 1} \Big( x_i \Big), \quad 1 \leq m \leq n$) .

Planteadas por el enunciado:

- $P$: conjunto de pesos.
- $n$: cantidad de pesos.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

Ya se ha repartido todo el peso.

- Si $P_T = 0$ entonces $V_S = 0$.

## 3 - Encontrar la relación de recurrencia

> [!attention] Puede interpretarse como un [[4 - Programación Dinámica/4.2 - Práctica/Mochila (0,1)|Problema de la Mochila]]
> Mochilla: consiste en rellenar una mochila de capacidad $C$ con objetos.
> ---
> Aquí, esa capacidad podría ser la de un montón, de forma que los pesos que no se usen en ese, automáticamente formarían el otro montón.
> 
> La pregunta es, ¿qué capacidad tiene el montón?
> 
> Será la mitad de la suma de todos los pesos disponibles, porque se aspira a que los montones sean lo menos dispares posible.

> [!attention] Para el desarrollo, uso el vector *$P$ ordenado de menor a mayor*

### Definir la estructura a utilizar

> [!info] Sea $P_i$ subconjunto de $P$, y $0 \leq i \leq n$ y $0 \leq m \leq M$.

Planteo una **tabla** de tamaño $n+1$ x $M+1$.

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |       |       |       |       |       |       |
|       **{1}** |       |       |       |       |       |       |
|     **{1,2}** |       |       |       |       |       |       |
|   **{1,2,3}** |       |       |       |       |       |       |
| **{1,2,3,4}** |       |       |       |       |       |       |

> [!cite] $A[i,m]$ representa si *$m$ puede obtenerse con elementos de $P_i$*.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|              $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| ----------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|          **{ }** |   -   |   -   |   -   |   -   |   -   |   -   |
|          **{1}** |   -   |       |       |       |       |       |
|       **{1, 2}** |   -   |       |       |       |       |       |
|    **{1, 2, 3}** |   -   |       |       |       |       |       |
| **{1, 2, 3, 4}** |   -   |       |       |       |       |       |

### Representar los casos iterativos

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  | ==✔== |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |       |       |       |       |       |
|   **{1,2,3}** |  *✘*  |       |       |       |       |       |
| **{1,2,3,4}** |  *✘*  |       |       |       |       |       |

> [!cite] Solo hay un elemento en $P_1$.

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  |   ✔   |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |   ✔   |   ✔   | ==✔== |   ✘   |   ✘   |
|   **{1,2,3}** |  *✘*  |       |       |       |       |       |
| **{1,2,3,4}** |  *✘*  |       |       |       |       |       |

> [!cite] $3$ puede obtenerse como $1 + 2$.

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  |   ✔   |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✘   |   ✘   |
|   **{1,2,3}** |  *✘*  |   ✔   |   ✔   |   ✔   | ==✔== | ==✔== |
| **{1,2,3,4}** |  *✘*  |       |       |       |       |       |

> [!cite] $4$ puede obtenerse como $1 + 3$.<br>$5$ puede obtenerse como $2 + 3$.

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  |   ✔   |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✘   |   ✘   |
|   **{1,2,3}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   |   ✔   |
| **{1,2,3,4}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   | ==✔== |

> [!cite] $4$ puede obtenerse como $1 + 3$.<br>$5$ puede obtenerse como $2 + 3$.

### Obtener la estructura completa

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  |   ✔   |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✘   |   ✘   |
|   **{1,2,3}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   |   ✔   |
| **{1,2,3,4}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   | **✔** |

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Se colocó un *NO* en aquellas casillas que no tienen sentido, como: usar un $p_i$ de un conjunto vacío para cubrir un peso $m$, o bien usar un $p_i$ para cubrir un peso de 0. 
> 
> Se usarán como casos base, indicando que ya se ha cubierto el peso requerido.
>  ---
> Es decir: $A[i,m] = ✘$ cuando $i = 0 \vee m = 0$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Se colocó un *SÍ* en aquellas celdas donde puede obtenerse $M[j]$ usando los valores de $P[i]$.
> 
> La forma de comprobarlo es simplemente obervar si la suma de todos los valores hasta $P[i]$ supera o iguala a $M[j]$.
>  ---
> Es decir: $A[i,m] = ✔$ cuando $\displaystyle\sum_{k = 1}^i \Big( P[k] \Big) \geq M[j]$.

> [!failure] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
\displaystyle\max_{0 \leq k \leq j}\Big( P[j] \Big) & \text{si} & i = 0 \\
\displaystyle\max_{0 \leq k \leq j}\Big( A[i-1,k] \Big) + P[j] & \text{si} & i > 0 \\
\end{array}
\right.
> $$

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
✘ & \text{si} & i = 1 & \vee & j = 1 \\
\displaystyle\sum_{k = 1}^i \Big( P[k] \Big) \geq M[j] & \text{si} & i,j > 1 & &  \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

|     Tabla $A$ | **0** | **1** | **2** | **3** | **4** | **5** |
| -------------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|       **{ }** |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |  *✘*  |
|       **{1}** |  *✘*  |   ✔   |   ✘   |   ✘   |   ✘   |   ✘   |
|     **{1,2}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✘   |   ✘   |
|   **{1,2,3}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   |   ✔   |
| **{1,2,3,4}** |  *✘*  |   ✔   |   ✔   |   ✔   |   ✔   | **✔** |

- Marcaré como ==resaltado== los *pesos* y sus *valores*.
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

|       Tabla $A$ |  ==**0**==  | **1** | ==**2**== | **3** | **4** | ==**5**== |
| ---------------:|:-----------:|:-----:|:---------:|:-----:|:-----:|:---------:|
|         **{ }** | ==***✘***== |  *✘*  |    *✘*    |  *✘*  |  *✘*  |    *✘*    |
|         **{1}** |     *✘*     |   ✔   |     ✘     |   ✘   |   ✘   |     ✘     |
|   **{1,==2==}** |     *✘*     |   ✔   | ==**✔**== |   ✔   |   ✘   |     ✘     |
| **{1,2,==3==}** |     *✘*     |   ✔   |     ✔     |   ✔   |   ✔   | ==**✔**== |
|   **{1,2,3,4}** |     *✘*     |   ✔   |     ✔     |   ✔   |   ✔   |   **✔**   |

> [!success] $S = \{A, B\} = \{\{2, 3\}, \{1, 4\}\}$.
> La tabla $A$ refleja el montón $A$ y por lo comentado durante el desarrollo:
> Si $A = \{2, 3\}$, como $B = P - A$, entonces $B = \{1, 4\}$.

> [!question] ¿Cómo recorro la tabla?
> Partiendo de $A[i,j] = A[n,M]$:
> Compruebo si $A[i-1,j]$ es ✔.
> - Si lo es, entonces esa solución es mejor (porque se obtiene $M[j]$ con menos pesos).
>   Me muevo ahí.
> - Si no lo es, entonces $P[i] \in A$ porque $P[i]$ hace que se obtenga $M[j]$.
>   Añado el peso a la solución y se lo resto a la cantidad a obtener del montón, obteniendo $x$.
>   Me muevo a $A[i-1, x]$.
