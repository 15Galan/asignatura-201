---
aliases: ["Ejercicio 0X"]
tags:    [tema4/relación, estado]
---
> [!seealso] Ejercicios parecidos
> - [[]]

# Problema

> Información sobre el caso práctico, con datos y definiciones.

> [!todo] ¿Qué pide el problema?

## Ejemplo del problema

> Casos de uso del problema con datos específicos.


# Solución

## 1 - Dimensiones relevantes

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

## 3 - Encontrar la relación de recurrencia

### Plantear un ejemplo particular

> Aportar datos más sencillos de manejar para encontrar la recurrencia, suponiendo que los datos del ejemplo del problema son más complicados.

### Definir la estructura a utilizar

> Describir la estructura a utilizar: qué significa su contenido, sus cabeceras, su(s) índice(s)...

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

> Representar soluciones inmediatas o triviales del problema.

### Representar los casos iterativos

> [!cite] Muestro algunos de los primeros casos, para indicar cómo se rellenan el resto.

> Completar algunos ejemplos importantes donde se destaque las decisiones tomadas.

### Obtener la estructura completa

> Estado final de la tabla tras rellenar cada celda.

## 4 - Generar la [[Ecuación de Bellman]]

> [!question] ¿Cómo se rellenaron los casos base?
> > Acción
> 
> > Por qué.
> 
>  ---
> > Definición matemática para la [[Ecuación de Bellman]].

> [!question] ¿Cómo se rellenaron los casos iterativos?
> > Acción
> 
> > Por qué.
> 
>  ---
> > Definición matemática para la [[Ecuación de Bellman]].

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
A[i,j] = \left\{
\begin{array}{cl}
? & \text{si} & ? \\
? & \text{si} & ? \\
? & \text{si} & ? \\
\end{array}
\right.
> $$

## 5 - Encontrar la configuración de la solución (extra)

> Paso que tiene lugar cuando el problema pide específicamente una configuración de la solución.

> [!question] ¿Cómo recorro la tabla?
> > Responder a la pregunta con tus palabras, ya que esto se verá igualmente reflejado en la implementación en código `Java` de la solución.

# Implementación en `Java`

```java
/**
 * PROBLEMA:
 * Descripción del problema.
 *
 * EJEMPLO:
 * Valores con datos de prueba (tuyos o del ejercicio).
 *
 *      SOLUCIÓN: Solución de este ejemplo.
 *
 */
public class NombreProblema {
	
    // Datos del problema
    // private static <...>
	
    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación dinámica
	
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        // Llamada a las variables de clase del mismo nombre
		
		
        // Mostrar los datos del problema
        System.out.println("...");
		
		
        // Resolución
        rellenarTablaA();
		
        System.out.println("Solución: " + solucion(A));
		
		
        // Información adicional
        // System.out.println("\n--- Información adicional ---");
        // System.out.println("\nTabla A:\n" + mostrarTabla(A));
    }
	
	
    // --------------------------------------------------
    // Algoritmo de programación dinámica
    // --------------------------------------------------
	
    /**
     * Rellena la tabla 'A' según la ecuación de Bellman.
     */
    public static void rellenarTablaA() {
        // Inicializar la tabla
        // int n = <...>, m = <...>;
        A = new int[n][m];
		
        // Aplicar la ecuación de Bellman
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bellman(i, j);
            }
        }
    }
	
    /**
     * Aplica la siguiente ecuación de Bellman para una fila y columna
     * concretas de la tabla 'A'.
     *
     * A(i,j) = ?   si  ?
     * A(i,j) = ?   si  ?
     * A(i,j) = ?   si  ?
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if ("...") {
            // TODO: copiar literalmente la Ecuación de Bellman (paso 4)
			
        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            // TODO: copiar literalmente la Ecuación de Bellman (paso 4)
        }
    }
	
    /**
     * Genera la solución óptima a partir de la tabla generada.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Solución óptima
     */
    private static String solucion(int[][] A) {
        // Inicializar la solución y/o los índices
        // TODO: definir qué se va a usar para recorrer la tabla
		
        // Recorrer la tabla 'A' de alguna forma
        // TODO: implementar la forma de recorrer la tabla (paso 5*)
		
        // Devolver la solución
        return null;
    }
}
```
