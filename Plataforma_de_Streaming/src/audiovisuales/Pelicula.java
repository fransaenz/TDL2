package audiovisuales;

import java.util.List;


/**
 * Clase que representa una pelicula, que es un tipo de Audiovisual.
 * Puede tener una pelicula precuela, una secuela y un tráiler asociado.
 * 
 * @author Francisco, Lucas 
 * @version 1.0
 */


public class Pelicula extends Audiovisual{
	
	 /** Pelicula que sirve como precuela de este */
	Pelicula precuela;
	 /** Pelicula que sirve como secuela de este */
	Pelicula secuela;
    /** Tráiler asociado a la pelicula */
	Trailer trailer;
	
	 /**
     * Crea una nueva instancia de Pelicula.
     * 
     * @param titulo título de la película
     * @param director director de la película
     * @param idioma idioma original
     * @param resumen breve sinopsis
     * @param elenco actores principales
     * @param genero género audiovisual
     * @param duracion duración de la pelicula
     * @param vistasTotales número total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de países donde la película está restringida
     * @param precuela película que sirve como precuela (puede ser null)
     * @param secuela película que sirve como secuela (puede ser null)
     * @param trailer tráiler promocional asociado
     */
	public Pelicula(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Pelicula precuela,
			Pelicula secuela, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.precuela = precuela;
		this.secuela = secuela;
		this.trailer = trailer;
	}

	 /**
     * Devuelve la precuela de esta película
     * 
     * @return película precuela, o null si no tiene
     */
    public Pelicula getPrecuela() {
        return precuela;
    }

    /**
     * Establece la precuela de esta película
     * 
     * @param precuela nueva película precuela
     */
    public void setPrecuela(Pelicula precuela) {
        this.precuela = precuela;
    }

    /**
     * Devuelve la secuela de esta película
     * 
     * @return película secuela, o null si no tiene
     */
    public Pelicula getSecuela() {
        return secuela;
    }

    /**
     * Establece la secuela de esta película
     * 
     * @param secuela nueva película secuela
     */
    public void setSecuela(Pelicula secuela) {
        this.secuela = secuela;
    }

    /**
     * Devuelve el tráiler de la película
     * 
     * @return tráiler asociado
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Establece el tráiler de la película
     * 
     * @param trailer nuevo tráiler a asociar
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }


    /**
     * Establece el tráiler de la pelicula
     * 
     */
    //public void agregarTrailer(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
    
    /**
     * Establece una precuela de la pelicula
     * 
     */
    //public void agregarPrecuela(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
   
    /**
     * Establece una secuela de la pelicula
     * 
     */
    //public void agregarSecuela(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}

}
