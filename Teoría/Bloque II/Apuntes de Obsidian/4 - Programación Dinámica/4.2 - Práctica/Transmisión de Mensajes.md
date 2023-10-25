---
aliases: ["Ejercicio 06"]
tags:    [tema4/relación, estado/incompleto]
---
# Problema

Como oficial de comunicaciones un crucero de combate, su labor es la de gestionar las comunicaciones de la manera más eficiente.

Supóngase que se quiere transmitir un mensaje $S = s_1, s_2, \dots, s_m$ dado como una cadena de $m$ símbolos. A tal fin, dispone de $r$ códigos distintos.

Sea $b_{i,j}$ el número de bits necesarios para codificar el $i$-ésimo símbolo en el $j$-ésimo código.

Inicialmente el trasmisor del puente está fijado al código #1 pero puede ser cambiado durante la transmisión cuando se desee; para ello se necesita enviar un código de control compuesto de $C_{i,j}$ bits si se desea cambiar del código $i$ actual al código $j$.

> [!todo]  Su objetivo es determinar cómo enviar el mensaje empleando el número mínimo de bits.

# Solución
## 1 - Dimensiones relevantes

Planteadas por mí:

- $B = \{b_1, b_2, \dots, v_n\}$: vector solución.
- $b_i$: código a emplear para enviar el símbolo $s_i$.
- $V_B$: valor de la solución (número de bits de la transmisión).

Planteadas por el problema:

- $S = \{s_1, s_2, \dots, s_m\}$: mensaje $S$ compuesto de símbolos $s_1$.
- $R = \{r_1, r_2, \dots, r_r\}$: códigos diferentes disponibles.
- $C = \Big\{\{c_{1,1}, c_{1,2}, \dots, c_{1,r}\}, \dots, \{c_{r,1}, c_{r,2}, \dots, c_{r,r}\}\Big\}$: bits que añadir a la transmisión para cambiar de código.
- $m$: cantidad de símbolos de un mensaje.
- $r$: cantidad de códigos distintos.
- $c_{i,j}$: cantidad de bits para cambiar del $r_i$ al $r_j$.
- $b_{i,j}$ cantidad de bits para enviar el $s_i$ con el código $r_j$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. $S = \{ \ \}$.
	- Si el mensaje no tiene símbolos, entonces $V_B = 0$.

## 3 - Encontrar la relación de recurrencia
### Plantear un ejemplo particular

Sea el mensaje $S = \{M, a, ñ, a, n, a\}$, $r = 4$ y $C$ presentada a continuación:

|  $C$  | **1** | **2** | **3** | **4** |
|:-----:|:-----:|:-----:|:-----:|:-----:|
| **1** |   0   |   8   |   4   |   6   |
| **2** |   8   |   0   |   3   |   4   |
| **3** |   4   |   3   |   0   |   9   |
| **4** |   6   |   4   |   9   |   0   |

|  $b$  | **1** | **2** | **3** | **4** |
|:-----:|:-----:|:-----:|:-----:|:-----:|
| **M** |   8   |   3   |   1   |   7   |
| **a** |   6   |   2   |   5   |   3   |
| **ñ** |   4   |   8   |   6   |   9   |
| **a** |   5   |  10   |   7   |  14   |
| **n** |   1   |  20   |  15   |  12   |
| **a** |  13   |  14   |   2   |   3   |

### Definir la estructura a utilizar

Planteo una **tabla** de tamaño $m+1$ x $r$:

|                    $A$ | **1** | **2** | **3** | **4** |
| ----------------------:|:-----:|:-----:|:-----:|:-----:|
|              $\{ \ \}$ |       |       |       |       |
|                $\{M\}$ |       |       |       |       |
|             $\{M, a\}$ |       |       |       |       |
|          $\{M, a, ñ\}$ |       |       |       |       |
|       $\{M, a, ñ, a\}$ |       |       |       |       |
|    $\{M, a, ñ, a, n\}$ |       |       |       |       |
| $\{M, a, ñ, a, n, a\}$ |       |       |       |       |

> [!cite] $A[i,j]$ representa la cantidad mínima de bits empleados para transmitir.
> Cabe destacar que el enunciado establece que se empieza en el código #1 ($r_1$) y que además, cambiar de código supone bits adicionales, por lo que es de esperar que cada celda de esta tabla contenga una suma del valor de otra celda de $b$ y de $C$.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

|                    $A$ | **1** | **2** | **3** | **4** |
| ----------------------:|:-----:|:-----:|:-----:|:-----:|
|              $\{ \ \}$ |   0   |   0   |   0   |   0   |
|                $\{M\}$ |       |       |       |       |
|             $\{M, a\}$ |       |       |       |       |
|          $\{M, a, ñ\}$ |       |       |       |       |
|       $\{M, a, ñ, a\}$ |       |       |       |       |
|    $\{M, a, ñ, a, n\}$ |       |       |       |       |
| $\{M, a, ñ, a, n, a\}$ |       |       |       |       |

> [!cite] El mensaje aún no ha empezado a transmitirse.
> Puede interpretarse como la *cadena vacía* a la que concatenarle los símbolos a enviar.

### Representar los casos iterativos

> [!cite] Muestro algunos de los primeros casos, para indicar cómo se rellenan el resto.

Fila $A[2,\_]$:

|                    $A$ | **1** | **2** | **3** | **4** |
| ----------------------:|:-----:|:-----:|:-----:|:-----:|
|              $\{ \ \}$ |  *0*  |  *0*  |  *0*  |  *0*  |
|                $\{M\}$ |   8   |  8   |   5   |   5   |
|             $\{M, a\}$ |       |       |       |       |
|          $\{M, a, ñ\}$ |       |       |       |       |
|       $\{M, a, ñ, a\}$ |       |       |       |       |
|    $\{M, a, ñ, a, n\}$ |       |       |       |       |
| $\{M, a, ñ, a, n, a\}$ |       |       |       |       |

> [!cite] Acción
> Compruebo hasta $j$ cuál es el mínimo $b_{2,j}$, el mínimo $A[1,j]$ y los bits de cambio de código $c_{2,j}$, y los sumo para ver cuál es el valor que arrastro.
> $$
\text{Código: \#} 1 \qquad
\left.
\begin{array}{cl}
j \leq 1: & 0 + 8 + 0 & = & 8 & \rightarrow & \min = 8 \\
j \leq 2: & 0 + 3 + 8 & = & 11 & \rightarrow & \min = 8 \\
j \leq 3: & 0 + 1 + 4 & = & 5 & \rightarrow & \min = 5 \leftarrow \\
j \leq 4: & 0 + 7 + 6 & = & 13 & \rightarrow & \min = 5
\end{array}
\right.
> $$

Fila $A[3,\_]$:

|                    $A$ | **1** | **2** | **3** | **4** |
| ----------------------:|:-----:|:-----:|:-----:|:-----:|
|              $\{ \ \}$ |  *0*  |  *0*  |  *0*  |  *0*  |
|                $\{M\}$ |   8   |   8   |   5   |   5   |
|             $\{M, a\}$ |  18   |  16   |  10   |  10   |
|          $\{M, a, ñ\}$ |       |       |       |       |
|       $\{M, a, ñ, a\}$ |       |       |       |       |
|    $\{M, a, ñ, a, n\}$ |       |       |       |       |
| $\{M, a, ñ, a, n, a\}$ |       |       |       |       |

> [!cite] Acción
> Compruebo hasta $j$ cuál es el mínimo $b_{2,j}$, el mínimo $A[1,j]$ y los bits de cambio de código $c_{2,j}$, y los sumo para ver cuál es el valor que arrastro.
> $$
\text{Código: \#} 3 \qquad
\left.
\begin{array}{cl}
j \leq 1: & 8 + 6 + 4 & = & 18 & \rightarrow & \min = 18 \\
j \leq 2: & 8 + 2 + 3 & = & 16 & \rightarrow & \min = 16 \\
j \leq 3: & 5 + 5 + 0 & = & 10 & \rightarrow & \min = 10 \leftarrow \\
j \leq 4: & 5 + 3 + 9 & = & 17 & \rightarrow & \min = 10
\end{array}
\right.
> $$

### Obtener la estructura completa

Una vez repetido el proceso anterior para todas las filas, se obtiene la tabla siguiente:

|                    $A$ | **1** | **2** | **3** | **4**  |
| ----------------------:|:-----:|:-----:|:-----:|:------:|
|              $\{ \ \}$ |  *0*  |  *0*  |  *0*  |  *0*   |
|                $\{M\}$ |   8   |   8   |   5   |   5    |
|             $\{M, a\}$ |  18   |  16   |  10   |   10   |
|          $\{M, a, ñ\}$ |  26   |  26   |  16   |   16   |
|       $\{M, a, ñ, a\}$ |  35   |  35   |  23   |   23   |
|    $\{M, a, ñ, a, n\}$ |  40   |  40   |  37   |   37   |
| $\{M, a, ñ, a, n, a\}$ |  57   |  57   |  39   | **39** |

> [!success] Esto indica que la transmisión mínima es de $V_B = 39$ bits.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando un $0$ en la primera fila.
> 
> Porque al inicio, cuando el mensaje aún no empezó a trnamisitirse, no se necesitan bits.
> 
>  ---
> Es decir: $A[i,j] = 0$ cuando $i = 1$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Como se quiere minimizar el número de bits a emplear: primero, debe saberse cuántos se habrían empleado cuando no se tenía en cuenta el símbolo actual a enviar; segundo, saber cuál sería la longitud añadida en el paso actual (si cambia de código o no); tercero, ver cuál de las combinaciones de todo lo anterior es la mínima.
>  ---
> Es decir: $A[i,j] = \displaystyle\min_{1 \leq k \leq j}\Big(A[i-1,k] + b[i,k] + C[j,k] \Big)$ cuando $i > 1$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 1 \\
\displaystyle\min_{1 \leq k \leq j}\Big(A[i-1,k] + b[i,k] + C[j,k] \Big) & \text{si} & i > 1 \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

> [!question] ¿Cómo recorro la tabla?
> Primero busco la primera instancia del valor mínimo en la fila (ya que si antes se obtuvo un valor mínimo, entonces el caso actual es menos óptimo); cuando encuentro lo encuentro, entonces ese es el código usado para el símbolo.
> 
> Repito el proceso con cada fila hasta llegar al caso en el que el mensaje es la cadena vacía.

|                    $A$ | **1** | **2** | **3** | **4**  |
| ----------------------:|:-----:|:-----:|:-----:|:------:|
|              $\{ \ \}$ |  *0*  |  *0*  |  *0*  |  *0*   |
|                $\{M\}$ |   8   |   8   |   5   |   5    |
|             $\{M, a\}$ |  18   |  16   |  10   |   10   |
|          $\{M, a, ñ\}$ |  26   |  26   |  16   |   16   |
|       $\{M, a, ñ, a\}$ |  35   |  35   |  23   |   23   |
|    $\{M, a, ñ, a, n\}$ |  40   |  40   |  37   |   37   |
| $\{M, a, ñ, a, n, a\}$ |  57   |  57   |  39   | **39** |

- Marcaré como ==resaltado== las *decisiones* que verifican que se usa ese código.****
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

|                    $A$ | **1** | **2** |   **3**    | **4**  |
| ----------------------:|:-----:|:-----:|:----------:|:------:|
|              $\{ \ \}$ |  *0*  |  *0*  |  ***0***   |  *0*   |
|                $\{M\}$ |   8   |   8   | ==**5**==  |   5    |
|             $\{M, a\}$ |  18   |  16   | ==**10**== |   10   |
|          $\{M, a, ñ\}$ |  26   |  26   | ==**16**== |   16   |
|       $\{M, a, ñ, a\}$ |  35   |  35   | ==**23**== |   23   |
|    $\{M, a, ñ, a, n\}$ |  40   |  40   | ==**37**== |   37   |
| $\{M, a, ñ, a, n, a\}$ |  57   |  57   | ==**39**== | **39** |

> [!success] La solución para este ejemplo, es $S = \{3, 3, 3, 3, 3, 3\}$.
> Es decir, que envía $M$ usando $\#3$, $a$ usando $\#3$... así hasta el último símbolo.
