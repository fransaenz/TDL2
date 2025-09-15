package audiovisuales;

import java.util.List;


/**
 * Representa un capitulo audiovisual que extiende de Audiovisual
 * y que puede tener un tráiler asociado.
 * 
 * @author Francisco, Lucas
 * @version 1.0
 */


public class Capitulo extends Audiovisual {
	
	  /** Trailer asociado al capitulo */
	Trailer trailer;
	
	/**
     * Crea una nueva instancia de Capitulo.
     * 
     * @param titulo titulo del capitulo
     * @param director director del capitulo
     * @param idioma idioma original del capitulo
     * @param resumen breve sinopsis del capitulo
     * @param elenco actores principales del capitulo
     * @param genero genero audiovisual (ej. drama, comedia, etc.)
     * @param duracion duracion del capitulo
     * @param vistasTotales numero total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de paises donde el capitulo no se puede ver
     * @param trailer trailer del capitulo
     */
	
	public Capitulo(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.trailer = trailer;
	}

	
	/**
     * Devuelve el trailer del capitulo.
     * 
     * @return el trailer del capitulo
     */

	public Trailer getTrailer() {
		return trailer;
	}

	/**
     * Establece el trailer del capítulo.
     * 
     * @param trailer nuevo trailer a asociar
     */
	
	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}
	
	 /**
     * Establece el tráiler del documental
     * 
     */
    //public void agregarTrailer(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
    
}
