---
tags: [tema4/diapositivas, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Transformación de Cadenas]]

# Introducción

En biología, a veces, es necesario comparar el ADN de diferentes organismos.
Una *cadena de ADN* está formada por una secuencia de moléculas (bases) denotadas por su inicial $\{A,C,G,T\}$.

Para medir la similitud entre 2 cadenas pueden establecerse distintos criterios, siendo uno de ellos calcular la *subsecuencia común más larga ($SCL$)*.

# Problema

Sean 2 secuencias $X = \langle x_1, x_2, \dots, x_n \rangle$ e $Y = \langle y_1, y_2, \dots, y_m \rangle$.

> [!todo] Encontrar la subsecuencia común más larga.

## Datos de ejemplo

Sean las secuencias $S_1$ y $S_2$:

- $S_1$ = ACCGGTCGAGTGCGCGGAAGCCGGCCGAA
- $S_2$ = GTCGTTCGGAATGCCGTTGCTCTGTAAA

Una subsecuencia común de $S_1$ y $S_2$ es **CTCTGAA**.

- $S_1$ = AC==C==GG==TC==GAG==TG==CGCGG==AA==GCCGGCCGAA
- $S_2$ = GT==C==GT==TC==GGAA==TG==CCGTTGCTCTGT==AA==A

> [!info] Una solución es $S = \langle CTCTGAA \rangle$.
> Esta no es la solución óptima.

# Solución
## 1 - Dimensiones relevantes

Ofrecidas por el enunciado:

- $x_i$: el elemento $i$ de la secuencia $X$.
- $y_j$: el elemento $j$ de la secuencua $Y$.
- $n$: la longitud de la secuencia $X$.
- $m$: la longitud de la secuencia $Y$.
- $SCL$: la subsecuencia más larga (solución).

> [!info] Siendo $0 \leq i \leq n$ y $0 \leq j \leq m$.
> Además, $i \neq j$ y $n \neq m$.

## 2 - Soluciones de [[Principio de Optimalidad#^a9a8e9|casos base]]

> [!cite] Asumo que todas las cadenas son enteras y positivas.
> El problema no aporta esa información.

1. $X = \langle \ \rangle$ e $Y = \langle y_1, y_2, \dots, y_m \rangle$.
	- Si $n = 0$, entonces $SCL = S_2$.
2. $X = \langle x_1, x_2, \dots, x_n \rangle$ e $Y = \langle \ \rangle$.
	- Si $m = 0$, entonces $SCL = S_1$.

## 3 - Representación de las soluciones de los [[Principio de Optimalidad#^a9a8e9|casos base]]

> [!cite] Este paso sirve para identificar la recurrencia producida al rellenar la tabla.
> Como es evidente, la dificultad es **cómo rellenar la estructura** elegida, pero puede sacarse con algo de lógica y visualizando bien la situación.

### Plantear un ejemplo particular

Sean las secuencias $X = ABCBDAB$ e $Y = BDCABA$.

> [!success] La solución óptima es $SCL = BCBA$.

### Definir la estructura a utilizar

Basándome en el siguiente concepto:
> [!info] Sean $X_i$ e $Y_j$ subcadenas de $X$ e $Y$ respectivamente.
> Siendo $0 \leq i \leq n$ y $0 \leq j \leq m$.

Planteo una **tabla** de tamaño $n+1$ x $m+1$:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |       |       |       |       |       |       |       |
|   **A**   | **1**  |       |       |       |       |       |       |       |
|   **B**   | **2**  |       |       |       |       |       |       |       |
|   **C**   | **3**  |       |       |       |       |       |       |       |
|   **B**   | **4**  |       |       |       |       |       |       |       |
|   **D**   | **5**  |       |       |       |       |       |       |       |
|   **A**   | **6**  |       |       |       |       |       |       |       |
|   **B**   | **7**  |       |       |       |       |       |       

> [!cite] $C[i,j]$ representa *la longitud de la $SCL$* hasta el momento actual.
> Los números $i$, $j$ representan la *longitud de las subcadenas $X$ e $Y$* respectivamente.
> ---
> Dada dicha definción, se destaca que $X_0 \neq A$, porque:
> - $X_i: \ X_0 = \langle \ \rangle, \ X_1 = \langle A \rangle, \ X_2 = \langle AB \rangle, \ \dots, \ X_7 = \langle ABCBDAB \rangle = X$.
> - $x_i: \ x_0 = \nexists, \ x_1 = A, \ x_2 = B, \ \dots, \ x_7 = B$.

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Como se indicó anteriormente, los casos base es que alguna cadena tenga longitud 0, por tanto, la tabla quedaría de la siguiente forma:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |   0   |   0   |   0   |   0   |   0   |   0   | 0     |
|   **A**   | **1**  |   0   |       |       |       |       |       |       |
|   **B**   | **2**  |   0   |       |       |       |       |       |       |
|   **C**   | **3**  |   0   |       |       |       |       |       |       |
|   **B**   | **4**  |   0   |       |       |       |       |       |       |
|   **D**   | **5**  |   0   |       |       |       |       |       |       |
|   **A**   | **6**  |   0   |       |       |       |       |       |       |
|   **B**   | **7**  |   0   |       |       |       |       |       |       |

> [!cite] Casos base
> Se usarán para indicar que se ha encontrado la $SCL$.
> ---
> $X_0 = \langle \ \rangle$
> $Y_0 = \langle \ \rangle$

### Representar los casos iterativos

> [!cite] Muestro algunos de los primeros casos, para indicar cómo se rellenan el resto.

> [!info] *Esta tabla* se rellena de izquierda a derecha y de arriba a abajo.

Fila $C[1,j]$:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  | *0*   |
|   **A**   | **1**  |  *0*  |   0   |   0   |   0   | ==1== |   1   | ==1== |
|   **B**   | **2**  |  *0*  |       |       |       |       |       |       |
|   **C**   | **3**  |  *0*  |       |       |       |       |       |       |
|   **B**   | **4**  |  *0*  |       |       |       |       |       |       |
|   **D**   | **5**  |  *0*  |       |       |       |       |       |       |
|   **A**   | **6**  |  *0*  |       |       |       |       |       |       |
|   **B**   | **7**  |  *0*  |       |       |       |       |       |       |

> [!cite] $x_1 = y_4 = y_6 = A$
> $C[1,4]$: se encuentra una coincidencia.
> - Se comprueban coincidencias previas y se suma la coincidencia actual.
> 
> $C[1,6]$: se encuentra una segunda coincidencia.
> - Se comprueban coincidencias previas y se suma la coincidencia actual.
> ---
> $X$ = ==A==BCBDAB
> $Y$ = BDC==A==BA (1ª coincidencia)
> $Y$ = BDCAB==A== (2ª coincidencia)
> ---
> $X_1 = \langle A \rangle$
> $Y_4 = \langle BCDA \rangle$
> $Y_6 = \langle BCDABA \rangle$

Fila $C[2,j]$:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  | *0*   |
|   **A**   | **1**  |  *0*  |   0   |   0   |   0   |   1   |   1   | 1     |
|   **B**   | **2**  |  *0*  | ==1== |   1   |   1   |   1   | ==2== | 2     |
|   **C**   | **3**  |  *0*  |       |       |       |       |       |       |
|   **B**   | **4**  |  *0*  |       |       |       |       |       |       |
|   **D**   | **5**  |  *0*  |       |       |       |       |       |       |
|   **A**   | **6**  |  *0*  |       |       |       |       |       |       |
|   **B**   | **7**  |  *0*  |       |       |       |       |       |       |

> [!cite] $x_2 = y_1 = y_5 = B$.
> $C[2,1]$: se encuentra una coincidencia.
> - Se comprueban coincidencias previas y se suma la coincidencia actual.
> 
> $C[2,5]$: se encuentra una segunda coincidencia.
> - Se comprueban coincidencias previas y se suma la coincidencia actual.
> ---
> $X$ = A==B==CBDAB
> $Y$ = ==B==DCABA (1ª coincidencia)
> $Y$ = BDCA==B==A (2ª coincidencia)
> ---
> $X_2 = \langle AB \rangle$
> $Y_1 = \langle B \rangle$
> $Y_5 = \langle BCDAB \rangle$

Fila $C[3,j]$:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  | *0*   |
|   **A**   | **1**  |  *0*  |   0   |   0   |   0   |   1   |   1   | 1     |
|   **B**   | **2**  |  *0*  |   1   |   1   |   1   |   1   |   2   | 2     |
|   **C**   | **3**  |  *0*  |   1   |   1   | ==2== |   2   |   2   | 2     |
|   **B**   | **4**  |  *0*  |       |       |       |       |       |       |
|   **D**   | **5**  |  *0*  |       |       |       |       |       |       |
|   **A**   | **6**  |  *0*  |       |       |       |       |       |       |
|   **B**   | **7**  |  *0*  |       |       |       |       |       |       |

> [!cite] $x_3 = y_3 = C$.
> $C[3,3]$: se encuentra una coincidencia.
> - Se comprueban coincidencias previas y se suma la coincidencia actual.
> ---
> $X$ = AB==C==BDAB
> $Y$ = BD==C==ABA (coincidencia)
> ---
> $X_3 = \langle ABC \rangle$
> $Y_3 = \langle BDC \rangle$

### Obtener la tabla completa

Una vez repetido el proceso anterior para todas las filas, se obtiene la tabla siguiente:

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  | *0*   |
|   **A**   | **1**  |  *0*  |   0   |   0   |   0   |   1   |   1   | 1     |
|   **B**   | **2**  |  *0*  |   1   |   1   |   2   |   2   |   2   | 2     |
|   **C**   | **3**  |  *0*  |   1   |   1   |   2   |   2   |   2   | 2     |
|   **B**   | **4**  |  *0*  |   1   |   2   |   2   |   2   |   3   | 3     |
|   **D**   | **5**  |  *0*  |   1   |   2   |   2   |   2   |   3   | 3     |
|   **A**   | **6**  |  *0*  |   1   |   2   |   2   |   3   |   3   | 4     |
|   **B**   | **7**  |  *0*  |   1   |   2   |   2   |   3   |   4   | **4** |

> [!important] Esto indica que, *para el ejemplo creado*, $|SCL| = 4$.
> Es decir, que la subcadena más larga entre $X$ e $Y$ mide *4 moléculas*.

## 4 - Generar la [[Ecuación de Bellman]]

Mientras se ha rellenado la tabla, uno observa que el proceso descrito sigue las siguientes condiciones:

> [!question] ¿Cómo se rellenaron los casos base?
> Poniendo un $0$ en la fila y columna inicial.
> 
> Porque representan la longitud de una subcadena vacía.
> 
> ---
> Es decir: $C[i,j] = 0$ cuando $i = 0 \vee j = 0$.

> [!question] ¿Cómo se rellenaron los casos iterativos?
> Teniendo en cuenta que todos esos casos ocurren cuando $i,j > 0$...
>  ---
> Si la molécula coincidía, miraba el valor de $C[i-1,j-1]$ -la longitud de la $SCL$ óptima anterior- y le añadía una unidad más, la de la coincidencia que acababa de suceder.
> 
> Porque como las subcadenas $X_i$ e $Y_j$ se van alargando, la $SCL$ óptima anterior será aquella para la que no se tuvieron en cuenta las moléculas $x_i$ ni $y_j$ (no se había llegado).
>  ---
> Es decir: $C[i,j] = C[i-1,j-1] + 1$ cuando $i,j > 0 \ \wedge \ x_i = y_i$.
>  ---
> Si la molécula no coincidía, comparaba los valores $C[i-1,j]$ -la longitud de la $SCL$ sin $x_i$- y $C[i,j-1]$ -la longitud de la $SCL$ sin $y_i$- y colocaba el mayor
> 
> Porque como no hay coincidencia, la $SCL$ óptima seguirá siendo una de las anteriores, la más larga sin las moléculas $x_i$ o $y_j$.
>  ---
> Es decir: $C[i,j] = \max\big(C[i-1, j], C[i,j-1]\big)$ cuando $i,j > 0 \ \wedge \ x_i \neq y_i$.

Habiendo analizado las preguntas anteriores, que surgen y se responden durante el proceso de colocación de valores en la tabla, se obtiene la [[Ecuación de Bellman]].

> [!success] [[Ecuación de Bellman]] para el problema planteado:
> $$
C[i,j] = \left\{
\begin{array}{cl}
0 & \text{si} & i = 0 & \vee & j = 0 \\
C[i-1, j-1] + 1 & \text{si} & i,j > 0 & \wedge & x_i = y_i \\
\max\big(C[i-1, j], C[i,j-1]\big) & \text{si} & i,j > 0 & \wedge & x_i \neq y_i \\ 
\end{array}
\right.
> $$

> [!cite] El ejercicio finaliza habiendo planteado la [[Ecuación de Bellman]].
> Los pasos descritos en el proceso aportan la validez necesaria al resultado.

## 5 - Encontrar la configuración de la solución (extra)

> [!cite] Como se mencionó [[4 - Programación Dinámica/Cómo resolver un problema#^d36d0e|anteriormente]], este es un paso circunstancial.
> El ejercicio pide **la subcadena**, por lo que además de decir el *cómo **genero** la solución* con la [[Ecuación de Bellman]], también debo indicar *cómo **construyo** la solución*.

> [!cite] Durante este paso seguiré usando mi ejemplo particular, por comodidad.

Partiendo de la tabla obtenida al final del [[Subsecuencia Común Más Larga#3 - Representación de las soluciones de los Principio de Optimalidad a9a8e9 casos base|paso 3]], será necesario averiguar cómo desde la *celda final*, se puede obtener la configuración de la solución pedida.

| Tabla $C$ |        |       | **B** | **D** | **C** | **A** | **B** | **A** |
|:---------:|:------:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:| ----- |
|           | i \\ j | **0** | **1** | **2** | **3** | **4** | **5** | **6** |
|           | **0**  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  |  *0*  | *0*   |
|   **A**   | **1**  |  *0*  |   0   |   0   |   0   |   1   |   1   | 1     |
|   **B**   | **2**  |  *0*  |   1   |   1   |   2   |   2   |   2   | 2     |
|   **C**   | **3**  |  *0*  |   1   |   1   |   2   |   2   |   2   | 2     |
|   **B**   | **4**  |  *0*  |   1   |   2   |   2   |   2   |   3   | 3     |
|   **D**   | **5**  |  *0*  |   1   |   2   |   2   |   2   |   3   | 3     |
|   **A**   | **6**  |  *0*  |   1   |   2   |   2   |   3   |   3   | 4     |
|   **B**   | **7**  |  *0*  |   1   |   2   |   2   |   3   |   4   | **4** |

- Marcaré como ==resaltado== las *moléculas* y sus *valores* coincidentes.
- Marcaré como **negrita** el *camino* seguido desde el final al inicio de la tabla.

| Tabla $C$ |        |         | ==**B**== | **D** | ==**C**== | **A** | ==**B**== | ==**A**== |
|:---------:|:------:|:-------:|:---------:|:-----:|:---------:|:-----:|:---------:| --------- |
|           | i \\ j |  **0**  |   **1**   | **2** |   **3**   | **4** |   **5**   | **6**     |
|           | **0**  |   *0*   |    *0*    |  *0*  |    *0*    |  *0*  |    *0*    | *0*       |
|   **A**   | **1**  | ***0*** |     0     |   0   |     0     |   1   |     1     | 1         |
| ==**B**== | **2**  |   *0*   | ==**1**== | **1** |     1     |   1   |     2     | 2         |
| ==**C**== | **3**  |   *0*   |     1     |   1   | ==**2**== | **2** |     2     | 2         |
| ==**B**== | **4**  |   *0*   |     1     |   2   |     2     |   2   | ==**3**== | 3         |
|   **D**   | **5**  |   *0*   |     1     |   2   |     2     |   2   |   **3**   | 3         |
| ==**A**== | **6**  |   *0*   |     1     |   2   |     2     |   3   |     3     | ==**4**== |
|   **B**   | **7**  |   *0*   |     1     |   2   |     2     |   3   |     4     | **4**     |

> [!question] ¿Cómo recorro la tabla?
> Si la celda es una coincidencia ($x_i = y_j$), la guardo y me muevo a la $SCL$ óptima anterior, que será aquella que no contenga ni $x_i$ ni $y_j$, por lo que me muevo a $C[i-1,j-1]$.
> 
> Si la celda no es una coincidencia ($x_i \neq y_j$), no la necesito y me muevo:
> 1. Compruebo el valor de la misma celda en $i-1$, porque si es el mismo, entonces esa $SCL$ es mejor que la actual por haberla logrado antes, sin $x_i$.
> 2. Compruebo el valor de la celda anterior en $i$, porque si es el mismo, entonces esa $SCL$ es mejor que la actual por haberla logrado antes, sin $y_j$.
> - Si no se cumple ninguna, no habría entrado, porque entonces es una coincidencia.

# Implementación en `Java`

```java
/**
 * PROBLEMA:
 * Encontrar la subsecuencia común más larga entre 2 cadenas de ADN.
 *
 * EJEMPLO:
 * - X = ACCGGTCGAGTGCGCGGAAGCCGGCCGAA
 * - Y = GTCGTTCGGAATGCCGTTGCTCTGTAAA
 *
 *      SOLUCIÓN: GTCGTCGGAAGCCGGCCGAA
 *
 */
public class Subsecuencia {
	
    // Datos del problema
    private static String X, Y;     // Cadenas de ADN
	
    // Datos del desarrollo
    private static int[][] A;       // Tabla de programación dinámica
	
    /**
     * Resuelve el problema descrito para un ejemplo concreto.
     *
     * @param args  Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Datos del problema
        X = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";    // Ejemplos de las
        Y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";     // diapositivas
        // X = "ABCBDAB";
        // Y = "BDCABA";
		
        // Mostrar los datos del problema
        System.out.println("X = " + X);
        System.out.println("Y = " + Y);
		
		
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
        int n = X.length()+1, m = Y.length()+1;    // +1: subcadenas vacías
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
     * A(i,j) = 0                           si  i = 0 || j = 0
     * A(i,j) = A(i-1,j-1) + 1              si  i,j > 0 && x[i] == y[j]
     * A(i,j) = MAX[A(i-1,j), A(i,j-1)]     si  i,j > 0 && x[i] != y[j]
     *
     * @param i     Fila de la tabla 'A'
     * @param j     Columna de la tabla 'A'
     */
    private static void bellman(int i, int j) {
        // Caso base
        if (i == 0 || j == 0) {
            A[i][j] = 0;
			
        // Caso "recursivo" (no se usa 'bellman()', sino 'A[][]')
        } else {
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                A[i][j] = A[i-1][j-1] + 1;
            } else {
                A[i][j] = Math.max(A[i-1][j], A[i][j-1]);
            }
        }
    }
	
    /**
     * Devuelve la SCL entre 2 cadenas de ADN.
     *
     * @param A     Tabla de la ecuación de Bellman
     *
     * @return      Subsecuencia común más larga
     */
    private static String solucion(int[][] A) {
        // Inicializar los índices
        int i = X.length(), j = Y.length();
		
        // Inicializar la solución
        StringBuilder SCL = new StringBuilder();
		
        // Recorrer la tabla 'A' hacia atrás
        while (i > 0 && j > 0) {
            // Las moléculas coinciden
            if (X.charAt(i-1) == Y.charAt(j-1)) {
                SCL.append(X.charAt(i-1));          // Añadir la molécula a la SCL
                i--;
                j--;                                // Disminuir los índices
				
            // Las moléculas no coinciden
            } else {
                // Se comprueba hacia dónde se mueve el recorrido
                if (A[i-1][j] >= A[i][j]) {
                    i--;
					
                } else {
                    j--;
                }
            }
        }
		
        // Devolver la solución
        return SCL.reverse().toString();    // Se han añadido al revés
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
        return A[X.length()][Y.length()];   // Última celda
    }
}
```

> [!success] La implementación es correcta y encuentra la $SCL$ adecuadamente.
> El ejercicio pide **encontrar la subsecuencia más larga**, por lo que sería necesario:
> - Un método que genere [[Subsecuencia Común Más Larga#Tabla rellena|la tabla del paso 3]], aplicando la [[Ecuación de Bellman]].
> - Un método que reconstruya la solución a partir de dicha tabla.

> [!cite] A continuación dejo un método propio para visualizar la tabla.
> No es necesario, pero lo uso para *debbugear* mi implementación.

```java
/**
 * Genera una representación de una tabla con sus índices como cabeceras.
 */
private static String mostrarTabla(int[][] tabla) {
	StringBuilder sb = new StringBuilder();
	
	// Cabeceras
	sb.append("\t").append("\t").append("∅").append("\t");
	
	for (int j = 0; j < Y.length(); j++) {
		sb.append(Y.charAt(j)).append("\t");    // Moléculas de Y
	}
	
	sb.append("\n").append("\t").append("\t");
	
	for (int cabecera = 0; cabecera <= Y.length(); cabecera++) {
		sb.append(cabecera).append("\t");       // Tamaño de subcadenas de Y
	}
	
	// Filas
	for (int fil = 0; fil <= X.length(); fil++) {
		sb.append("\n");
		
		// Cabecera de la fila
		if (fil == 0) {
			sb.append("∅");                 // Subcadena X vacía
			
		} else {
			sb.append(X.charAt(fil-1));     // Moléculas de X
		}
		
		sb.append("\t").append(fil).append("\t");   // Tamaño de subcadenas de X
		
		// Valores de las columnas
		for (int col = 0; col <= Y.length(); col++) {
			sb.append(tabla[fil][col]).append("\t");
		}
	}
	
	return sb.toString();
}
```
