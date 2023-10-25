---
fuente: https://es.wikipedia.org/wiki/Algoritmo_voraz
---
> [!info] Definición
> Estrategia de búsqueda por la que se sigue una *heurística* consistente en *elegir la opción óptima en cada paso local* con la *esperanza* de llegar a una solución general óptima.

# Esquema

Dado un conjunto finito de entradas $C$, un algoritmo voraz devuelve un conjunto $S$ (seleccionados) tal que $S \subseteq C$ y que además cumple con las restricciones del problema inicial.

A cada conjunto $S$ que satisfaga las restricciones se le suele denominar *prometedor*, y si este además logra que la función objetivo se minimice o maximice (según corresponda) diremos que $S$ es una solución óptima.

# Características

## Condiciones

Una solución de un [[Algoritmo Voraz]] verifica:

- *Factible*: debe satisfacer las restricciones del problema.
- *Localmente óptima*: es la mejor solución disponible, con el conocimiento hasta el momento.
- *Irrevocable*: una vez elegida la solución, no puede modificarse más adelante.

## Componentes

Un [[Algoritmo Voraz]] está compuesto de:

- **Función de terminación**: indica si una solución elegida es final o parcial.
- **Función de generación**: produce posibles candidatos de soluciones a elegir.
- **Función de selección**: escoge la mejor solución.
- **Función objetivo**: mide la calidad de la solución final encontrada.

### Ejemplo

```java
public static TSolucion voraz(TProblema p){
	private List<TSolucion> lista;
	private boolean factible = true;
	private TSolucion sol;
	
	sol = solucionVacia(p);     // Primera solución (caso base)
	
	while (!completa(sol,P) && factible){   // F. Terminación ('completa(sol,P)')
		lista = generarSoluciones(sol);     // F. Generación
		
		if (lista.isEmpty()) {
			factible = false;
			
		} else {
			// Construir la siguiente solución óptima
			sol = extender(sol, p, seleccionar(lista, p));     // F. Selección
		}
	}
	
	if (!factible) {
		return null;
		
	} else {
		return sol;
	}
}
```

# Funcionamiento

El algoritmo escoge en cada paso al mejor elemento $x \in C$ posible, conocido como el elemento más prometedor.

Se elimina ese elemento del conjunto de candidatos ($C \leftarrow C \setminus \{x\}$) y acto seguido, comprueba si la inclusión de este elemento en el conjunto de elementos seleccionados ($S \cup \{x\}$) produce una solución factible.

- **Sí**: se incluye ese elemento en $S$.
- **No**: se descarta el elemento.

Iteramos el bucle, comprobando si el conjunto de seleccionados es una solución y, si no es así, pasando al siguiente elemento del conjunto de candidatos.
