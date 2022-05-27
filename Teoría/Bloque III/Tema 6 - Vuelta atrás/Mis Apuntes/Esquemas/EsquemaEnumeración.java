/**
 * Esquema de un Algoritmo de Vuelta Atrás por enumeración:
 * Encontrar todas las soluciones.
 *
 * @param  soluciones  Lista / Conjunto de todas las soluciones obtenidas
 * @param  solucion    Solución recibida de otras iteraciones
 */
public void vueltaAtras(Set<Solucion> soluciones, Solucion solucion) {
    // Caso final: se recibe una solución válida
    if (esSolucion(solucion)) {
        soluciones.add(solucion);

    // Caso intermedio: se calculan el resto de soluciones
    } else {
        Set<Candidato> candidatos = candidatos(solucion);

        while (!candidatos.empty()) {
            Candidato siguiente = seleccion(candidatos);

            candidatos.remove(siguiente);   // Se elimina el candidato elegido
            solucion.add(siguiente);        // Se añade el candidato a la solución

            vueltaAtras(soluciones, solucion);
        }

        // Este método es 'void' porque las soluciones se almacenan
        // en el parámetro de entrada 'soluciones' (lista / conjunto)
    }
}

/**
 * Indica si una solución reibida en una iteración del algoritmo
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
