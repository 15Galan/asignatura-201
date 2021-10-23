// UNIVERSIDAD DE MÁLAGA
// --------------------------------------------------
// Escuela Técnica Superior de Ingeniería Informática
// --------------------------------------------------
// Análisis y Diseño de Algoritmos
// Práctica 1: Complejidad experiemntal

import java.util.List;


/**
 * Esta clase almacena una complejidad, unos tiempos medios
 * adaptados a ella y calcula un ratio entre los 2 últimos
 * tiempos para determinar si tiende a un valor constante
 *
 * @author Antonio J. Galán Herrera
 */
public class Datos {

    private final String nombre;          // Identificador de la complejidad ("1", "LOGN", "N", "NLOGN"...)
    private final List<Double> tiempos;   // Tiempos medios de ejecución del algoritmo partidos la complejidad
    private final double ratio;           // Valor que determina una constante: cuanto más cercano a 1, mejor


    public Datos(String nombre, List<Double> tiempos) {
        this.nombre = nombre;
        this.tiempos = tiempos;

        ratio = calcularRatio();
    }


    public String getNombre() {
        return nombre;
    }

    public List<Double> getTiempos() {
        return tiempos;
    }

    public double getRatio() {
        return ratio;
    }

    /**
     * Calcula el ratio para los 2 últimos valores de la lista
     *
     * @return  Un ratio
     */
    private double calcularRatio() {
        if(tiempos.isEmpty()) {
            return 0;

        } else if(tiempos.size() == 1) {
            return 1;

        } else {
            return tiempos.get(tiempos.size()-2) / tiempos.get(tiempos.size()-1);
        }
    }
}
