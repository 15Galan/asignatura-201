---
tags: [tema4/examen, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Tarifa Telefónica]]

# Problema

Sea un vector de enteros $A = \{a_1, a_2, \dots, a_n\}$, definimos su «sumaducto» como el mayor valor que puede conseguirse sumando elementos y/o productos de dos elementos adyacentes.

Cada elemento solo puede estar en una suma o en un producto.

> [!todo] Proporcionar la ecuación de Bellman para el sumaducto e implementar un algoritmo de programación dinámica bottom-up según dicha ecuación.

## Ejemplo del problema

Sea $A = \{1, 3, 4, 3, 2, 2, 5, 3\}$.

> [!success] Su sumaducto es $36$, porque $1 + (3·4) + (3·2) + 2 + (5·3) = 36$.
> Esta es la solución óptima para este ejemplo.

# Solución
## 1 - Dimensiones relevantes

Planteadas por el enunciado:

- $A$: conjunto de números enteros.
- $a_i$: número entero $i$.
- $n$: número de elementos de $A$.

Planteadas por mí:

- $V_S$: valor óptimo del sumaducto.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

1. Si $A = \{ \ \} = \emptyset$ entonces $V_S = 0$.
	- No hay elementos de los que calcular el sumaducto.
2. Si $A = \{a_1\}$ entonces $V_S = a_1$.
	- Solo hay un elemento (ni se suma ni se multiplica).

## 3 - Encontrar la relación de recurrencia

### Definir la estructura a utilizar

Se empleará un **vector** de longitud $n+1$.

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |             |     |     |     |     |     |     |     |     |

> [!cite] $A[i]$ representa el sumaducto óptimo acumulado.

> [!attention] Las tablas en Markdown siempre requieren una cabecera.
> Observa que para no dejarla en blanco, simplemente he colocado identificadores acordes al estado del vector $A$ hasta el estado $i$ ($\{ \ \}, \{1\}, \{1,3\}, \dots, \{1,3,\dots,3\}$).

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |      0      |  1  |     |     |     |     |     |     |     |

### Representar los casos iterativos

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |   4  |     |     |     |     |     |     |

> [!cite] Acción
> $$
\left.
\begin{array}{cl}
\text{Suma:} & 1 + 3 & = & 4 \\
\text{Producto:} & 0 + (1·3) & = & 3 
\end{array}
\right.
> $$

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  |     |     |     |     |     |

> [!cite] Acción
> $$
\left.
\begin{array}{cl}
\text{Suma:} & 4 + 4 & = & 8 \\
\text{Producto:} & 1 + (3·4) & = & 13 
\end{array}
\right.
> $$

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  |     |     |     |     |

> [!cite] Acción
> $$
\left.
\begin{array}{cl}
\text{Suma:} & 13 + 3 & = & 16 \\
\text{Producto:} & 4 + (4·3) & = & 16 
\end{array}
\right.
> $$
> Como se obtiene el mismo sumaducto para $a_i$, no importa qué opción elegir.

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  | 19  |     |     |     |

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  | 19  | 21  |     |     |

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  | 19  | 21  | 29  |     |

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |  3  |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  | 19  | 21  | 29  | 36  |

> [!cite] Acción
> $$
\left.
\begin{array}{cl}
\text{Suma:} & 29 + 3 & = & 32 \\
\text{Producto:} & 21 + (5·3) & = & 36 
\end{array}
\right.
> $$

### Obtener la estructura completa

| Tabla $A$ | $\emptyset$ |  1  |  3  |  4  |  3  |  2  |  2  |  5  |   3    |
|:---------:|:-----------:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:------:|
|   $V_S$   |     *0*     | *1* |  4  | 13  | 16  | 19  | 21  | 29  | **36** |

> [!success] Se obtiene un sumaducto $V_S = 36$.

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> Colocando un $0$ cuando $A = \emptyset$ y $a_i$ cuando $A = \{a_i\}$.
> 
> Porque si $A$ no tiene elementos, tampoco sumaducto; y cuando solo tiene uno, el sumaducto vale lo que valga dicho elemento.
>  ---
> Es decir: $A[i] = 0$ cuando $i = 1$ y $A[i] = a_i$ cuando $i = 2$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Teniendo en cuenta que la única restricción indicada en el ejercicio es que un elemento solo puede estar en una suma o un producto, al evaluar hasta $a_i$, se debe tener en cuenta si $A_{i-1} + a_i$ (caso de $a_i$ en una suma) es mejor que $A_{i-2} + a_{i-1} · a_i$ (caso de una multiplicación).
> 
> Esto es así porque si $a_i$ está en una suma, no pasa nada especial, por lo que será el valor óptimo hasta el caso anterior ($A_{i-1}$) junto al de $a_i$; pero si $a_i$ está en una multiplicación, entonces $a_{i-1}$ lo acompaña, por lo que $a_{i-1} · a_i$ es la multiplicación, y a eso se le añade el valor óptimo hasta 2 casos anteriores ($A_{i-2}$), porque el elemento anterior ya está en la multiplicación.
>  ---
> Es decir: $A[i] = \max\Big(A_{i-1} + a_i, A_{i-2} + (a_{i-1} · a_i)\Big)$ cuando $i > 2$.

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 1 \\
a_i & \text{si} & i = 2 \\
\max\Big(A_{i-1} + a_i, A_{i-2} + (a_{i-1} · a_i)\Big) & \text{si} & i > 2 \\
\end{array}
\right.
> $$

# Implementación en `Java`

```java
package Examenes.Curso_19_20;

import java.util.Arrays;



/**
 * PROBLEMA:
 * Sea un vector de enteros 'A = {a_1, a_2, ..., a_n}', definimos
 * su «sumaducto» como el mayor valor que puede conseguirse sumando
 * elementos y/o productos de dos elementos adyacentes.
 *
 * Cada elemento solo puede estar en una suma o en un producto.
 *
 * Determinar el sumaducto de 'A'.
 *
 *
 * EJEMPLO:
 * Sea A = {1, 3, 4, 3, 2, 2, 5, 3}.
 *
 *      SOLUCIÓN: 36, porque 1 + (3·4) + (3·2) + 2 + (5·3) = 36.
 */
public class Febrero {
	
    // Datos del problema
    private static int[] A;     // Vector de enteros
    private static int n;       // Cantidad de elementos
	
    // Datos del desarrollo
    private static int[] SD;    // Vector de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        A = new int[]{1, 3, 4, 3, 2, 2, 5, 3};
        n = A.length;
		
		
        // Mostrar los datos del problema
        System.out.println("A: " + Arrays.toString(A));
		
		
        // Resolución
        rellenarVectorSD();
		
        System.out.println("\nValor: " + valor());
		
		
        // Información adicional
        System.out.println("\n--- Información adicional ---");
        System.out.println("\nVector SD:\n" + Arrays.toString(SD));
    }
	
    /**
     * Rellena el vector de programación dinámica.
     */
    private static void rellenarVectorSD() {
        SD = new int[n+1];
		
        for (int i = 0; i <= n; i++) {
            bellman(i);
        }
    }
	
	
    /**
     * Aplica la ecuación de Bellman para el elemento i.
     *
     * @param i  Índice del elemento
     */
    private static void bellman(int i) {
        // NOTA: los accesos al vector A llevan un índice menos
        // porque el vector A empieza en la posición 0.
        
        // Caso base 1
        if (i == 0) {
            SD[i] = 0;
			
        // Caso base 2
        } else if (i == 1) {
            SD[i] = A[i-1];
			
        // Caso iterativo
        } else {
            SD[i] = Math.max(SD[i-1] + A[i-1], SD[i-2] + (A[(i-1)-1] * A[i-1]));
        }
    }
	
    /**
     * Devuelve el valor del sumaducto.
     */
    private static int valor() {
        return SD[n];
    }
}
```
