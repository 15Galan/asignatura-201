---
tags: [tema5/examen, estado/correcto]
---
# Problema

Dado un array unidimensional de tamaño $n$, cumple que:

 - Cada componente tiene un policía ($P$) o un ladrón ($L$).
 - Cada policía puede atrapar a un solo ladrón.
 - Un policía no puede atrapar a un ladrón que está a más distancia que $K$.
 
 Se pide encontrar el máximo número de ladrones que pueden ser atrapados.
 
> [!todo] Diseñar un algoritmo voraz que, dado el array y el valor $K$:
> 1. Determine el máximo número de ladrones que pueden ser atrapados.
> 2. Construya otro array donde para cada posición:
> 	- Si es un policía, indique la posición del ladrón que captura.
> 	- Si es un ladrón, indique si fue capturado (`-1`) o no (`1`).

## Ejemplo

Sea el array $A = \{P, L, P, L, L, P\}$ y $K = 3$.

> [!info] Solución válida: $S = \{3, -1, 1, -1, -1, 4\}$, se capturan 3 ladrones.

# Solución
## 1 - Plantear el *vector solución*

Sea $S = \{s_1, \dots, s_i, \dots, s_n\}$ el vector solución:

- $i$ : posición de un policía o de un ladrón.
- $s_i$ : si $a_i = P$, el ladrón que captura; si $a_i = L$ si fue capturado ($-1$) o no ($1$).
- $n$ : longitud del vector $A$.

## 2 - Defininir los *componentes*

### ¿Puede no tener solución?

No, dado que se pide la *cantidad de ladrones atrapados*, en el peor caso, la cantidad calculada puede ser $0$, pero nunca un valor no concluyente.

### ¿Cómo se determina el *heurístico que plantea el mejor candidato*?

Tratar de capturar al ladrón más a la izquierda que no haya sido capturado, con el policía más a la izquierda que no haya capturado a un ladrón.

### ¿Cómo se determina un *candidato*?

Sean $p,l \in [0,n]$ índices que determinan la posición de un policía y un ladrón en el array $A$ respectivamente, y dos listas $P_L$ y $L_L$ que contienen las posiciones de los policías y ladrones respectivamente.

Los candidatos son sublistas de $P_L$ y $L_L$ donde ya se ha evaluado el policía $p$ o el ladrón $l$.

### ¿Cómo se determina una *solución completa*?

Cuando $p = n \vee l = n$, porque entonces se ha llegado al final del recorrido del array y ya se han establecido qué ladrones fueron capturados.

# Implementación en `Java`

```java
package Parciales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * @author Antonio J. Galán Herrera
 */
public class Parcial_2018 {

    // Datos del problema
    private static char[] escenario;    // Vector de los policías y ladrones
    private static int K;               // Distancia max para capturar ladrones
	
	
    /**
     * Método que inicializa el escenario de los policías y los ladrones.
     *
     * @param args  null
     */
    public static void main(String[] args) {
        // Datos del problema
        escenario = new char[] {'P', 'L', 'P', 'L', 'L', 'P'};
        K = 3;
		
        // Mostrar problema
        System.out.println("Escenario:\t" + Arrays.toString(escenario));
		
		
        // Resolver problema
        int[] solucion = algoritmoVoraz();
        int L = contarCapturas(solucion);
		
        // Mostrar solución
        System.out.println("\nSolución:\t" + Arrays.toString(solucion));
        System.out.println("Capturas:\t" + L);
    }
	
	
    // ------------------------------------------------------------------------
    // Algoritmo voraz
    // ------------------------------------------------------------------------
	
    /**
     * HEURÍSTICA:
     * Tratar de capturar al ladrón más a la izquierda que no haya sido capturado,
     * con el policía más a la izquierda que no haya capturado a un ladrón.
     *
     * @return  Vector de capturas
     */
    public static int[] algoritmoVoraz() {
        int n = escenario.length;
		
        // Inicializar la solución
        int[] solucion = new int[n];
		
		// Se parte de la base donde ningún ladrón fue capturado
        for (int i = 0; i < n; i++) {
            solucion[i] = -1;
        }
		
        // Inicializar los punteros
        List<Integer> policias = new ArrayList<>();     // Índices de los policías
        List<Integer> ladrones = new ArrayList<>();     // Índices de los ladrones
		
        for (int i = 0; i < escenario.length; i++) {
            if (escenario[i] == 'P') {
                policias.add(i);
				
            } else {
                ladrones.add(i);
            }
        }
		
        // Comenzar a resolver
        int p = 0, l = 0;       // Índices de los policías y los ladrones
		
        while (p < policias.size() && l < ladrones.size()) {
            int P = policias.get(p);    // Policía actual
            int L = ladrones.get(l);    // Ladrón actual
			
            // Comprobar si el ladrón actual puede ser capturado
            if (Math.abs(P - L) <= K) {
                solucion[P] = L;
				
                p++; l++;   // Pasar a los siguientes policía y ladrón
				
            } else {
				
                /*
                Un ladrón está fuera de rango, por lo que no puede ser capturado
                y debe actualizarse el puntero que esté más a la izquierda.
				
                Esto es así porque como se está planteando recorrer el vector de
                izquierda a derecha, ambos punteros solo pueden avanzar; por tanto,
                lo que queda por tener en cuenta es qué puntero está más alejado
                (a la izquierda), para acercarlo más: si se acerca un ladrón, el
                policía actual quizás pueda atraparlo; si se acerca un policía,
                el ladrón actual quizás pueda ser atrapado.
                 */
				
                // Comprobar qué puntero está más a la izquierda
                if (P < L) {
                    p++;    // Pasar al siguiente policía
					
                } else {
                    l++;    // Pasar al siguiente ladrón
                }
            }
        }
		
        return solucion;
    }
	
    /**
     * Método que devuelve el número de ladrones capturados en una solución.
     *
     * @param capturas  Vector solución del algoritmo voraz
     *
     * @return  Número de ladrones capturados
     */
    public static int contarCapturas(int[] capturas) {
        int res = 0;
		
        for (int i : capturas) {
            if (0 <= i) {
                res++;
            }
        }
		
        return res;
    }
}
```
