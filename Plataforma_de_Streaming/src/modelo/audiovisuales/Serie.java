package modelo.audiovisuales;

import java.util.List;

import modelo.audiovisuales.util.*;

import java.time.Duration;

/**
 * Clase que representa una serie, que es un tipo de Audiovisual.
 * Contiene información sobre la cantidad de temporadas, la lista de
 * temporadas y un tráiler asociado.
 * 
 * @author Lucas, Francisco
 * @version 2.0
 */

public class Serie extends Audiovisual{
	
	 /** Cantidad de temporadas de la serie */
    private int cantTemporadas;

    /** Lista de temporadas de la serie */
    private List<Temporada> temporadas;

    /** Tráiler asociado a la serie */
    private Trailer trailer;

    /**
     * Crea una nueva instancia de Serie.
     * 
     * @param titulo título de la serie
     * @param director director de la serie
     * @param idioma idioma original
     * @param resumen breve sinopsis
     * @param elenco actores principales
     * @param genero género audiovisual
     * @param duracion duración de la serie, valor total
     * @param vistasTotales número total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de países donde la serie está restringida
     * @param cantTemporadas cantidad de temporadas de la serie
     * @param temporadas lista de objetos Temporada que componen la serie
     * @param trailer tráiler promocional asociado
     */
    
	public Serie(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos, int cantTemporadas,
			List<Temporada> temporadas, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.cantTemporadas = cantTemporadas;
		this.temporadas = temporadas;
		this.trailer = trailer;
	}

	/**
     * Devuelve la cantidad de temporadas de la serie
     * 
     * @return número de temporadas
     */
    public int getCantTemporadas() {
        return cantTemporadas;
    }

    /**
     * Establece la cantidad de temporadas de la serie
     * 
     * @param cantTemporadas nueva cantidad de temporadas
     */
    public void setCantTemporadas(int cantTemporadas) {
        this.cantTemporadas = cantTemporadas;
    }

    /**
     * Devuelve la lista de temporadas de la serie
     * 
     * @return lista de temporadas
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Establece la lista de temporadas de la serie
     * 
     * @param temporadas nueva lista de temporadas
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    /**
     * Devuelve el tráiler de la serie
     * 
     * @return tráiler asociado
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Establece el tráiler de la serie
     * 
     * @param trailer nuevo tráiler a asociar
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }


}
