---
tags: [tema4/examen, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Embarcaderos]]

# Problema

Tenemos una red de sensores con $n$ motas en la que cada mota $m_i$ sólo puede transmitir datos a alguna mota $m_j$ si $i < j$, $\forall i, j \in \{1, \dots, n\}$.

Una tabla $C$ almacena en cada posición $c_{i,j}$ el tiempo necesario para transmitir un paquete de datos desde $m_i$ hasta $m_j$ directamente.

En algunos casos, el tiempo de transmisión más corto de un paquete para algún par $(m_i, m_j)$ no será $c_{i,j}$, sino el obtenido al sumar los tiempos acumulados pasando por motas $m_k$ intermedias, con $i < k < j$.

> [!todo] Queremos obtener el mínimo tiempo de transmisión para cada $(m_i, m_j)$.
> Al ser un control, este ejercicio vale **5 puntos** (un apartado no lo incluí).
> 1. **[2 puntos]** Definir una *ecuación de recurrencia ([[Ecuación de Bellman|Bellman]])* para el problema.
> 2. **[2 puntos]** Implementar un *algoritmo basado en la ecuación anterior*, que resuelva el problema; y determinar su porden de *complejidad temporal* en el peor caso.

## Ejemplo del problema

|    $C$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |     0     |     3     |     6     |     9     |    12     |
| **$m_2$** |     -     |     0     |     2     |     2     |     8     |
| **$m_3$** |     -     |     -     |     0     |     1     |     5     |
| **$m_4$** |     -     |     -     |     -     |     0     |     2     |
| **$m_5$** |     -     |     -     |     -     |     -     |     0     |

# Solución
## 1 - Dimensiones relevantes

Planteadas por mí:
- $S$: tabla solución con el tiempo mínimo en cada celda.

Planteadas por el enunciado:
- $n$: cantidad de motas.
- $i$: mota de origen.
- $j$: mota de destino.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. Transmitir de una mota a sí misma.
	- Si $i = j$ entonces $S[i,j] = 0$.

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |           |           |           |           |           |
| **$m_2$** |           |           |           |           |           |
| **$m_3$** |           |           |           |           |           |
| **$m_4$** |           |           |           |           |           |
| **$m_5$** |           |           |           |           |           |

> [!cite] $A[i,j]$ representa el tiempo mínimo para trasnsmitir desde $i$ hasta $j$.
> Sea con motas intermedias o no.

> [!attention] Debido a la naturaleza del problema, al final, $A = S$.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, el caso base es que una mota transmita para sí misma. Además, también se tendrá en cuenta en este paso que no se puede transmitir hacia una mota anterior:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |     0     |           |           |           |           |
| **$m_2$** |     -     |     0     |           |           |           |
| **$m_3$** |     -     |     -     |     0     |           |           |
| **$m_4$** |     -     |     -     |     -     |     0     |           |
| **$m_5$** |     -     |     -     |     -     |     -     |     0     |

### Representar los casos iterativos

> [!info] *Esta tabla* se rellena de izquierda a derecha y de arriba a abajo.

Fila $A[5,\_]$:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |    *0*    |           |           |           |           |
| **$m_2$** |     -     |    *0*    |           |           |           |
| **$m_3$** |     -     |     -     |    *0*    |           |           |
| **$m_4$** |     -     |     -     |     -     |    *0*    |           |
| **$m_5$** |     -     |     -     |     -     |     -     |    *0*    |

> [!cite] Estando en $D$ solo puede navegarse al último embarcadero, $E$.

Fila $A[4,\_]$:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |    *0*    |           |           |           |           |
| **$m_2$** |     -     |    *0*    |           |           |           |
| **$m_3$** |     -     |     -     |    *0*    |           |           |
| **$m_4$** |     -     |     -     |     -     |    *0*    |     2      |
| **$m_5$** |     -     |     -     |     -     |     -     |    *0*    |

> [!cite] Estando en $C$ puede navegarse tanto a $D$ como a $E$.
> Desde $E$, el mejor camino será aquel que tenga menor coste de aquellos que conecten directamente con él, contando hasta $C$.
> - $C \rightarrow D \rightarrow E$: lo que cueste $C \rightarrow D$ y el coste mínimo $D \rightarrow E$.
> - $D \rightarrow E$: lo que cueste $D \rightarrow E$ y el coste mínimo $E \rightarrow E$ (que es 0).
> ---
> Desde $D$, el mejor camino será aquel que tenga menor coste de aquellos que conecten directamente con él, contando hasta $C$.
> - $C \rightarrow D$: lo que cueste $C \rightarrow D$ y el coste mínimo $C \rightarrow C$ (que es 0).

Fila $A[3,\_]$:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |    *0*    |           |           |           |           |
| **$m_2$** |     -     |    *0*    |           |           |           |
| **$m_3$** |     -     |     -     |    *0*    |     1     |     3     |
| **$m_4$** |     -     |     -     |     -     |    *0*    |     2     |
| **$m_5$** |     -     |     -     |     -     |     -     |    *0*    |

Fila $A[2,\_]$:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |    *0*    |           |           |           |           |
| **$m_2$** |     -     |    *0*    |     2     |     2     |     4     |
| **$m_3$** |     -     |     -     |    *0*    |     1     |     3     |
| **$m_4$** |     -     |     -     |     -     |    *0*    |     1     |
| **$m_5$** |     -     |     -     |     -     |     -     |    *0*    |

Fila $A[1,\_]$:

|    $A$    | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|
| **$m_1$** |    *0*    |     3     |     5     |     5     |     7     |
| **$m_2$** |     -     |    *0*    |     2     |     2     |     4     |
| **$m_3$** |     -     |     -     |    *0*    |     1     |     3     |
| **$m_4$** |     -     |     -     |     -     |    *0*    |     1     |
| **$m_5$** |     -     |     -     |     -     |     -     |    *0*    |

### Obtener la estructura completa

Una vez completado el proceso anterior, se obtiene la tabla siguiente:

| $A$ | **$m_1$** | **$m_2$** | **$m_3$** | **$m_4$** | **$m_5$** |
|:----------:|:-----:|:-----:|:-----:|:-----:|:-----:|
|   **$m_1$**    |  *0*  |   4   |   3   |   5   |   6   |
|   **$m_2$**    |   -   |  *0*  |   1   |   2   |   3   |
|   **$m_3$**    |   -   |   -   |  *0*  |   2   |   3   |
|   **$m_4$**    |   -   |   -   |   -   |  *0*  |   1   |
|   **$m_5$**    |   -   |   -   |   -   |   -   |  *0*  |

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando un $0$ en las celdas donde la mota de origen y destino era la misma.
>
> Porque las motas no transmiten para sí mismas.
>
> ---
> Es decir: $A[i,j] = 0$ cuando $i = j$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> La clave fue pensar en «¿cómo he llegado aquí?» más que «¿cómo llego al destino?».
>
> Estando en $A[i,j]$, miraba todas las motas $i$ disponibles y su tiempo mínimo hasta $j$, a las que le añadía el tiempo correspondiente para terminar de llegar a $i$.
>  ---
> Es decir: $A[i,j] = \displaystyle\min_{i \lt k \leq j} \Big( \underbrace{C[i][k]}_{\text{Coste para llegar a i}} + \underbrace{A[k][j]}_{\text{Coste mínimo hasta j}} \Big)$ cuando $i > j$.

> [!success] **2 puntos** | [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = j \\
\displaystyle\min_{i \lt k \leq j} \Big( C[i][k] + A[k][j] \Big) & \text{si} & i > 0 \\
\end{array}
\right.
> $$

# Implementación en `Java`

> [!attention] Esta es la implementación de [[Embarcaderos]]
> Ambos son el mismo ejercicio en esencia, por lo que el código es el mismo.
> Bastaría con cambiar los datos de la variable `T` (que aquí es la tabla $C$).

![[Embarcaderos#^53dbe6]]
