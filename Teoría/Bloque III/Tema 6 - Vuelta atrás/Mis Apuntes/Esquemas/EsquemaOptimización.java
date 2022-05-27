/**
 * Esquema de un Algoritmo de Vuelta Atrás por enumeración:
 * Encontrar la mejor solución (óptima).
 *
 * @param  solucion  Solución recibida de la iteración anterior
 * @param  optima    La mejor solución hasta esta iteración del algoritmo
 * @param  calidad   Representación de la calidad de la solución óptima actual
 *
 * @return  La mejor solución de todas las calculadas
 */
public Solucion vueltaAtras(Solucion solucion, Solucion optima, int calidad) {
    // Caso final: se recibe una solución válida
    if (esSolucion(solucion)) {
        // Comprobar si la solución recibida es mejor que la óptima actual
        if (calidad < calidad(solucion)) {
            return solucion;  // La solución recibida es mejor que la óptima

        } else {
            return optima;    // La solución recibida es peor que la óptima
        }

    // Caso intermedio: se calculan el resto de soluciones
    } else {
        Set<Candidato> candidatos = candidatos(solucion);

        while (!candidatos.empty()) {
            Candidato siguiente = seleccion(candidatos);

            candidatos.remove(siguiente);   // Se elimina el candidato elegido
            solucion.add(siguiente);        // Se añade el candidato a la solución

            Solucion otra = vueltaAtras(solucion, optima, calidad);

            // Comprobar si la solución generada es mejor que la óptima actual
            if (calidad < calidad(otra)) {
                optima = otra;              // Se actualiza la solución
                calidad = calidad(otra);    // óptima y su calidad
            }
        }

        return optima;
    }
}

/**
 * Indica si una solución recibida en una iteración del algoritmo
 * de vuelta atrás es una solución final (válida / completa).
 *
 * @param  solucion  Solución a comprobar
 */
private boolean esSolucion(Solucion solucion);

/**
 * Calcula la calidad de solución.
 *
 * @param  solucion  Solución de la que calcular su calidad
 */
private int calidad(Solucion solucion);

/**
 * Calcula los candidatos válidos para la siguiente iteración
 * de vuelta atrás a partir de una solución parcial ya obtenida.
 *
 * @param  solucion  Solución parcial
 */
private Set<Candidato> candidatos(Solucion solucion);

/**
 * Calcula el siguiente candidato para una iteración
 * del algoritmo dado conjunto de candidatos válidos.
 *
 * @param  candidatos  Conjunto de candidatos válidos
 */
private Candidato seleccion(Set<Candidato> candidatos);
