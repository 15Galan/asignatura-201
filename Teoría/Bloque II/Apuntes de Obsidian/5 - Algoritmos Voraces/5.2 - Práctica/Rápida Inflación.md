---
tags: [tema5/examen, estado/correcto]
---
# Problema

En un contexto de rápida inflación, debemos adquirir $n$ ítems cuyo precio inicial $C$ (siendo $0 < C$) es para todos el mismo.

Sin embargo, sólo podemos comprar un ítem cada semana y el precio del $i$-ésimo ítem se incrementa en progresión geométrica con tasa $T_i$ (siendo $1 < T_i$).

Así, si compramos el ítem $i$ en la semana $t$ (siendo $0 \leq t \leq n-1$), su precio será $C·(T_i)^t$.  

> [!todo] Se pide diseñar un algoritmo voraz que dadas las tasas $T_0, \dots, T_{n-1}$ y el valor $C$:
> Determine en qué orden deben hacerse las compras y qué valor total tendrán, de modo que dicho valor sea mínimo.
> - En concreto, para cada semana $t$, el algoritmo debe devolver un vector $I$ donde $I_t$ es el índice del ítem que debe comprarse en dicha semana $t$.

# Solución

## 1 - Plantear el *vector solución*

Sea $S = \{s_1, \dots, s_i, \dots, s_n\}$ el vector solución:

- $i$ : orden en el que comprar los items.
- $s_i$ : índice del ítem de $I$ que comprar.
- $n$ : cantidad de objetos a comprar.

## 2 - Defininir los *componentes*

### ¿Puede no tener solución?

No, porque como se pide un valor de compra, dicho valor puede ser mínimo o no, pero nunca un valor no concluyente.

### ¿Cómo se determina el *heurístico que plantea el mejor candidato*?

*Ordenando los ítems de mayor a menor precio* se consigue que *el ítem más caro tenga la tasa más barata*, por lo que el precio acumulado que se va arrastrando es mínimo.

Para cada semana, el valor de la compra resulta: 

$$
\small
\begin{array}{cl}
\text{Compra \#} 0 : & C·(T_n)^{t_0} & \text{El más barato} \\
\text{Compra \#} 1 : & C·(T_n)^{t_0} + C·(T_{n-1})^{t_1} & \text{Más caro que el primero} \\
\text{Compra \#} 2 : & C·(T_n)^{t_0} + C·(T_{n-1})^{t_1} + C·(T_{n-2})^{t_2} & \text{Más caro que el segundo y el primero} \\
\vdots & \vdots & \vdots \\
\text{Compra \#} n : & C·(T_n)^{t_0} + C·(T_{n-1})^{t_1} + \dots + C·(T_0)^{t_n} & \text{El más caro} \\
\end{array}
$$

Al final, se trata de comprar primero los ítems más caros para que tengan una tasa  
menor y su precio no se infle demasiado para que el valor final sea mínimo.  
  
Dicho mínimo surge de la fórmula...

$$
\text{Total} = n·\big(C·(T_n)^{t_0}\big) + (n-1)·\big(C·(T_{n-1})^{t_1}\big) + \dots + 1·\big(C·(T_0)^{t_n}\big)
$$

...que se obtiene al sumar el valor de todas las compras:  
  
$$
\small
\text{Total} = \underbrace{C·(T_n)^{t_0}}_\text{Compra \#0} + \underbrace{C·(T_n)^{t_0} + C·(T_{n-1})^{t_1}}_\text{Compra \#1} + \dots + \underbrace{C·(T_n)^{t_0} + C·(T_{n-1})^{t_1} + \dots + C·(T_0)^{t_n}}_\text{Compra \#n}
$$

### ¿Cómo se determina un *candidato*?

Sea $S$ el conjunto solución de ítems, e $I$ el conjunto de ítems, los candidatos son $I - S$.
Porque se compran todos los ítems, pero en un orden concreto.

### ¿Cómo se determina una *solución completa*?

Cuando se introduce el último valor en $S$, porque entonces se ha llegado al final del recorrido del array y ya se ha establecido el orden de compra de todos los items.

# Implementación en `Java`

```java
package Parciales;

import java.util.Arrays;



/**
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2014 {
	
    // Datos del problema
    private static int n;       // Número de ítems
    private static int[] T;     // Tasas de inflación
    private static int C;       // Precio inicial de cada ítem
	
	
    /**
     * Método que inicializa y resuelve el problema
     * para un ejemplo concreto.
     *
     * @param args  null
     */
    public static void main(String[] args) {
        // Datos del problema
        T = new int[] {3, 2, 4};
        C = 100;
        n = T.length;
		
        // Mostrar el problema
        System.out.println("Tasas:\t" + Arrays.toString(T) + "\n");
        System.out.println("Precio:\t" + C + "\n");
		
		
        // Resolver el problema
        int[] solucion  = algoritmoVoraz();
        int valor       = calcularCompra(solucion);
		
        // Mostrar la solución
        System.out.println("Solución:\t" + Arrays.toString(solucion));
        System.out.println("Compra:\t\t" + valor + " euros");
    }
	
	
    // ----------------------------------------------------------------
    // Algoritmo voraz
	// ----------------------------------------------------------------
	
    /*
    HEURÍSTICA:
    Ordenando los ítems de mayor a menor precio, se consigue que el ítem
    más caro tenga la tasa más barata, por lo que el precio acumulado que
    se va arrastrando es mínimo.
    
    Para cada semana, el valor de la compra resulta:
	
	- Explicado arriba -
     */
	
    /**
     * Resuelve el problema de comprar 'n' ítems.
     *
     * @return  Vector de ítems
     */
    public static int[] algoritmoVoraz() {
        // Inicializar la solución con una copia de las tasas
        int[] tasas = Arrays.copyOf(T, n);
        int[] solucion = new int[n];
		
        ordenar(tasas);
		
        for (int i = 0; i < n; i++) {
            solucion[i] = buscar(T, tasas[i]);
        }
		
        return solucion;
    }
	
    /**
     * Calcula el valor total de una solución.
     *
     * @param items     Vector de ítems
     *
     * @return  Valor total de la compra
     */
    public static int calcularCompra(int[] items) {
        int total = 0;
		
        for (int i = 0; i < n; i++) {
            total += C * Math.pow(T[items[i]], i);
        }
		
        return total;
    }
	
	
    // ----------------------------------------------------------------
    // Métodos auxiliares
    // ----------------------------------------------------------------
	
    /**
     * Ordena los elementos de un vector de menor a menor.
     *
     * @param vector  Vector a ordenar
     */
    private static void ordenar(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[i] < vector[j]) {
                    int aux = vector[i];
                    vector[i] = vector[j];
                    vector[j] = aux;
                }
            }
        }
    }
	
	
    /**
     * Busca un elemento en un vector.
     *
     * @param vector    Vector donde buscar
     * @param elemento  Elemento a buscar
     *
     * @return  Índice del elemento en el vector
     */
    private static int buscar(int[] vector, int elemento) {
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == elemento) {
                return i;
            }
        }
		
        return -1;
    }
}
```
