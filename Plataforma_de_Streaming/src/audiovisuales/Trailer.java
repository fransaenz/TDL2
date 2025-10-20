package audiovisuales;

import java.util.List;
import java.time.Duration;
import util.*;

/**
 * Clase que representa un tráiler, que es un tipo de Audiovisual.
 * Hereda todos los atributos de Audiovisual como título, director,
 * idioma, resumen, elenco, género, duración, vistas y países restringidos.
 * 
 * @author Lucas, Francisco
 * @version 2.0
 */

public class Trailer extends Audiovisual {

	/**
     * Crea una nueva instancia de Trailer.
     * 
     * @param titulo título del tráiler
     * @param director director del tráiler
     * @param idioma idioma original
     * @param resumen breve sinopsis
     * @param elenco actores principales
     * @param genero género audiovisual
     * @param duracion duración del trailer
     * @param vistasTotales número total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de países donde el tráiler está restringido
     */
	
	public Trailer(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas, paisesRestringidos);
	}
	
}
