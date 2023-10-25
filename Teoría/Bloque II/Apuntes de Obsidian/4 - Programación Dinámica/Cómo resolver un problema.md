# Introducción

> [!cite] Estructura extraída del vídeo: [Clase del 11/11/2020](https://informatica.cv.uma.es/mod/page/view.php?id=399300)

> [!note] Los ejercicios se han hecho siguiendo esta estructura descrita.
> Además, todos se han hecho a partir de la misma [[T4 - Ejercicio|plantilla]].

# Pasos

## **Paso 1**: Determinar las *dimensiones relevantes*

> [!info] Encontrar *datos relevantes* analizando la información del problema.
> No todos los datos están implícitos en el enunciado, *algunos deben asumirse*.

## **Paso 2**: Determinar las *soluciones de los [[Principio de Optimalidad#^a9a8e9|casos base]]*

> [!info] Encontrar subproblemas triviales, cuya solución sea inmediata.
> En otras palabras, *aplicar el [[Principio de Optimalidad#^a9a8e9| Principio de Optimalidad]]*.

## **Paso 3**: Encontrar la *relación de recurrencia*

> [!info] Almacenar las soluciones de casos anteriores en una estructura.
> La estructura suele ser una *[[Subproblemas solapados#^4bfc4a|tabla]]*  y a veces, un *vector*.

Este paso es el más complicado y largo, pero al mismo tiempo el único que plantea una verdadera dificultad: **tener éxito en este paso significa el aprobado mínimo**.

Para hacer las cosas más fáciles lo dividiré en pequeños pasos que, completados secuencialmente, plantean una forma que considero más sencilla y metódica de resolver este apartado.

> [!attention] La clave de este paso es *haber practicado con muchos problemas distintos*.
> Lo que te permitirá identificar el problema planteado con alguno parecido a los que ya hayas hecho, **identificando la recurrencia** más cómodamente y con seguridad.

### Plantear un ejemplo particular

Si el problema ofrece un ejemplo lo suficientemente asequible para tratarlo de primera mano, esto no es necesario. Sin embargo, plantear tú mismo un ejemplo con valores más manejables cuando el ejemplo del problema es más complejo, hará que el proceso completo del paso 3 sea más llevadero y además, puedas ver y entender más fácilmente la recurrencia que necesitas encontrar.

### Definir la estructura a utilizar

Como se comentó, lo normal es usar una tabla, aunque no siempre sea necesario.

No obstante, lo importante aquí no es qué estructura usar sino cómo la vas a usar: resulta importante definir qué es cada cosa de la estructura y qué representa, para que nadie se pierda en el proceso (incluido tú).

La dificultad de este paso reside en saber cómo definir la estructura: qué datos usar, cómo 

### Representar los [[Principio de Optimalidad#^a9a8e9|casos base]]

Tras haber planteado la estructura, lo siguiente es rellenarla con los datos que son inmediatos.

### Representar los casos iterativos

> [!info] Aquí comienza la búsqueda de la *relación de recurrencia* en sí misma.
> No obstante, aunque los pasos anteriores fueran una preparación para este, deberían haberte aliviado un poco de carga y además, haberte hecho más seguro respecto a tu propia estructura (si no entiendes tu propia estructura, ¿cómo vas a rellenarla?).

Una vez obtenidos los valores por defecto, queda completar el resto de la estructura pensando lógicamente y teniendo en cuenta lo que simboliza cada elemento de la misma, definido al incio de este apartado.

### Obtener la estructura completa

Esto simplemente hace referencia a haber rellenado toda la estructura, lo que debería haber producido la solución óptima si todo ha ido bien.

Si se intuye que algo ha salido mal, analizando los pasos pequeños debería señalarte el error, que suele estar en [[4 - Programación Dinámica/Cómo resolver un problema#Representar los casos iterativos|representar los casos iterativos]].

## **Paso 4**: Generar la [[Ecuación de Bellman]]

> [!info] Encontrar y *establecer la relación de recurrencia* entre las soluciones.
> Lo que se traduce en definir la [[Ecuación de Bellman]] del problema.

## **Paso 5**: Encontrar la configuración de la *solución* (extra)

> [!attention] Este paso *no siempre* será necesario, depende del problema.
> Algunas veces la solución es simplemente un número (una cantidad, un valor...), pero otras veces, también debe ser la secuencia que produce dicho número (qué objetos, qué estados...). 
^d36d0e

Si se da el caso que se está describiendo, una vez obtenida la [[Ecuación de Bellman]] en el paso anterior, también *habrá plantear un método u algoritmo* que indique, dada esa estructura rellenada usando la ecuación, cómo reconstruimos la solución a partir de los datos.

Usando las ideas aplicadas a cómo se rellenó la estructura a lo largo del [[4 - Programación Dinámica/Cómo resolver un problema#Paso 3 Encontrar la recurrencia|paso 3]], se puede llegar a plantear cómo obtener la configuración de la solución.

> [!attention] La clave de este paso es *haber practicado con muchos problemas distintos*.
> Lo que te permitirá identificar cómo recorrer los valores de la estructura y generar una solución óptima.

# Implementación en `Java`

> [!info] Todo lo que has hecho en la parte anterior, pero ahora en `Java`
> Además, una vez encontrada la [[Ecuación de Bellman]], se trata de copiar y pegar las condiciones en un método que rellene la estructura planteada. Literalmente.
> ---
> La [[T4 - Ejercicio|plantilla]] también incluye una estructura genérica para diseñar el algoritmo en `Java`.
