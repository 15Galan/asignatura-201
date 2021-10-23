// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// Práctica 1: Complejidad experiemntal

import java.util.List;


/**
 * Esta clase simula una tupla que almacena los tiempos medios de ejecución de un algoritmo adaptados a una
 * complejidad y calcula un ratio entre los 2 últimos tiempos para determinar si tiende a un valor constante.
 *
 * @author Antonio J. Galán Herrera
 */
public class Datos {

    public final List<Double> tiempos;      // Tiempos medios de ejecución del algoritmo para un orden de complejidad
    public final double ratio;              // Valor que determina una constante (cuanto más cercano a 1, mejor)


    public Datos(List<Double> tiempos) {
        this.tiempos = tiempos;
        ratio = calcularRatio();
    }

    /**
     * Calcula el ratio para los 2 últimos valores de la lista.
     *
     * @return  Un ratio
     */
    private double calcularRatio() {
        if (tiempos.isEmpty()) {
            return 0;

        } else if (tiempos.size() == 1) {
            return 1;

        } else {
            return tiempos.get(tiempos.size()-2) / tiempos.get(tiempos.size()-1);
        }
    }
}
