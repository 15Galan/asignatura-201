---
fuente: https://es.wikipedia.org/wiki/Programaci%C3%B3n_din%C3%A1mica
---
# Introducción

> [!info] Definición
> Método para *reducir el tiempo de ejecución de un algoritmo* mediante la utilización de [[Subproblemas solapados|subproblemas solapados]] y de [[Principio de Optimalidad#Subestructura óptima|subestructuras óptimas]].

# Enfoques

> [!info] Top-down
> El problema se divide en subproblemas, y estos se resuelven recordando las soluciones por si fueran necesarias nuevamente.

Combinación de *[[Memoización|memoización]]* y *recursión*.

> [!info] Bottom-up
> Todos los problemas que puedan ser necesarios se resuelven de antemano y después se usan para resolver las soluciones a problemas mayores.

Este enfoque es ligeramente mejor en consumo de espacio y llamadas a funciones, pero a veces resulta poco intuitivo encontrar todos los subproblemas necesarios para resolver uno dado.
