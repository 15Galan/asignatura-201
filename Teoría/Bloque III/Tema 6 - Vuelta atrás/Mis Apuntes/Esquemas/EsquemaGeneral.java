/**
 * Esquema general de un Algoritmo de Vuelta Atrás:
 * Encuentra una sola solución.
 *
 * @param  solucion  Solución recibida de otras iteraciones
 *
 * @return  Una solución a un problema, calculada por vuelta atrás
 */
public Solucion vueltaAtras(Solucion solucion) {
    // Caso final: se recibe una solución válida
    if (esSolucion(solucion)) {
        return solucion;

    // Caso intermedio: se calculan el resto de soluciones
    } else {
        Set<Candidato> candidatos = candidatos(solucion);
        Solucion parcial = null;

        while (!candidatos.empty() && parcial == null) {
            Candidato siguiente = seleccion(candidatos);

            candidatos.remove(siguiente);   // Se elimina el candidato elegido
            solucion.add(siguiente);        // Se añade el candidato a la solución

            parcial = vueltaAtras(solucion);
        }

        return parcial;
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
