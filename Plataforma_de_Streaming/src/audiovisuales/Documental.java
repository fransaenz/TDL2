package audiovisuales;

import java.util.List;


/**
 * Clase que representa un documental, que es un tipo de Audiovisual.
 * Puede tener un documental precuela, una secuela y un tráiler asociado.
 * 
 * @author Lucas, Francisco
 * @version 1.0
 */

public class Documental extends Audiovisual{
	
	 /** Documental que sirve como precuela de este */
    private Documental precuela;

    /** Documental que sirve como secuela de este */
    private Documental secuela;

    /** Tráiler asociado al documental */
    private Trailer trailer;

    /**
     * Crea una nueva instancia de Documental.
     * 
     * @param titulo título del documental
     * @param director director del documental
     * @param idioma idioma original
     * @param resumen breve sinopsis
     * @param elenco actores principales
     * @param genero género audiovisual
     * @param duracion duración del documental
     * @param vistasTotales número total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de países donde el documental está restringido
     * @param precuela documental que sirve como precuela (puede ser null)
     * @param secuela documental que sirve como secuela (puede ser null)
     * @param trailer tráiler promocional asociado
     */
	public Documental(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Documental precuela,
			Documental secuela, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.precuela = precuela;
		this.secuela = secuela;
		this.trailer = trailer;
	}

	/**
     * Devuelve la precuela de este documental
     * 
     * @return documental precuela, o null si no tiene
     */
    public Documental getPrecuela() {
        return precuela;
    }

    /**
     * Establece la precuela de este documental
     * 
     * @param precuela nuevo documental precuela
     */
    public void setPrecuela(Documental precuela) {
        this.precuela = precuela;
    }

    /**
     * Devuelve la secuela de este documental
     * 
     * @return documental secuela, o null si no tiene
     */
    public Documental getSecuela() {
        return secuela;
    }

    /**
     * Establece la secuela de este documental
     * 
     * @param secuela nuevo documental secuela
     */
    public void setSecuela(Documental secuela) {
        this.secuela = secuela;
    }

    /**
     * Devuelve el tráiler del documental
     * 
     * @return tráiler asociado
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Establece el tráiler del documental
     * 
     * @param trailer nuevo tráiler a asociar
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }
	
    /**
     * Establece el tráiler del documental
     * 
     */
    //public void agregarTrailer(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
    
    /**
     * Establece una precuela del documental
     * 
     */
    //public void agregarPrecuela(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}
   
    /**
     * Establece una secuela del documental
     * 
     */
    //public void agregarSecuela(String titulo, String director, String idioma, String resumen, String elenco, String genero, int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos){}

    
}
