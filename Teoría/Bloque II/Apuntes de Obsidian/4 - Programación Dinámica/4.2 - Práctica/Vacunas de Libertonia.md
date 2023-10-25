---
tags: [tema4/examen, estado/correcto]
---
> [!seealso] Ejercicios parecidos
> - [[Estudiante Aventajado]]
> - [[Entidades Financieras]]

# Problema

El Ministerio de Sanidad de Libertonia ha adquirido $D$ dosis de una vacuna para una cierta enfermedad infecciosa y debe repartirlas entre cada una de las $n$ regiones del país.

Usando modelos matemáticos, los técnicos del Ministerio determinan que si se emplean $i$ dosis en la región $j$-ésima habrá $c_{i,j}$ contagios en dicha región.

> [!todo] Se desea determinar el reparto de vacunas que minimiza en número total de contagios en el país.

# Solución

> [!WARNING] Se resuelve igual que los del tipo [[Estudiante Aventajado]] o [[Entidades Financieras]]
> Solo que en lugar de buscar un máximo como en los ejemplos anteriores (mejor nota, mejor inversión), se busca un mínimo (menores contagios).
> 
> Se usaría una **tabla** de dimensiones $n$ x $D$ en la que cada celda $A[i,j]$ representa el menor valor acumulado de contagios.
