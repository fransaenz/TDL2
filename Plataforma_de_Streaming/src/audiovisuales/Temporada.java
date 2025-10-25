package audiovisuales;

import java.util.List;
import java.time.Duration;
import util.*;

/**
 * Clase que representa una temporada de una serie, que es un tipo de Audiovisual.
 * Contiene información sobre la cantidad de capítulos, la lista de capítulos
 * y un tráiler asociado.
 * 
 * @author Francisco, Lucas
 * @version 1.0
 */

public class Temporada extends Audiovisual{
	
	/** Cantidad de capítulos en la temporada */
    private int cantCapitulos;

    /** Lista de capítulos que componen la temporada */
    private List<Capitulo> capitulos;

    /** Tráiler asociado a la temporada */
    private Trailer trailer;

    /**
     * Crea una nueva instancia de Temporada.
     * 
     * @param titulo título de la temporada
     * @param director director de la temporada
     * @param idioma idioma original
     * @param resumen breve sinopsis
     * @param elenco actores principales
     * @param genero género audiovisual
     * @param duracion duración total de la temporada
     * @param vistasTotales número total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de países donde la temporada está restringida
     * @param cantCapitulos cantidad de capítulos
     * @param capitulos lista de objetos Capitulo que componen la temporada
     * @param trailer tráiler promocional asociado
     */
	public Temporada(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos, int cantCapitulos,
			List<Capitulo> capitulos, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.cantCapitulos = cantCapitulos;
		this.capitulos = capitulos;
		this.trailer = trailer;
	}
	
    /**
     * Devuelve la cantidad de capítulos de la temporada
     * 
     * @return número de capítulos
     */
    public int getCantCapitulos() {
        return cantCapitulos;
    }

    /**
     * Establece la cantidad de capítulos de la temporada
     * 
     * @param cantCapitulos nueva cantidad de capítulos
     */
    public void setCantCapitulos(int cantCapitulos) {
        this.cantCapitulos = cantCapitulos;
    }

    /**
     * Devuelve la lista de capítulos de la temporada
     * 
     * @return lista de capítulos
     */
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    /**
     * Establece la lista de capítulos de la temporada
     * 
     * @param capitulos nueva lista de capítulos
     */
    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    /**
     * Devuelve el tráiler de la temporada
     * 
     * @return tráiler asociado
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Establece el tráiler de la temporada
     * 
     * @param trailer nuevo tráiler a asociar
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }


	/**
     * Establece el tráiler de la temporada
     * 
     */
    //public void agregarTrailer(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
    
	
}
