package audiovisuales;

import java.util.List;
import java.time.Duration;
import util.*;

/**
 * Clase que representa un documental, que es un tipo de Audiovisual.
 * Puede tener un documental precuela, una secuela y un tráiler asociado.
 * 
 * @author Lucas, Francisco
 * @version 2.0
 */

public class Documental extends Audiovisual{
	
	 /** Documental que sirve como precuela de este */
    private Documental precuela;

    /** Documental que sirve como secuela de este */
    private Documental secuela;

    /** Tráiler asociado al documental */
    private Trailer trailer;
    
    /** Lista de todas las precuelas del Documental */
    private List<Documental> ListaPrecuelas;

    
    /** Lista de todas las secuelas del Documental */
    private List<Documental> ListaSecuelas;

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
	public Documental(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos, Documental precuela,
			Documental secuela, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.precuela = precuela;
		if (precuela != null) {
			ListaPrecuelas.add(precuela);
		}
		this.secuela = secuela;
		if (secuela != null) {
			ListaSecuelas.add(secuela);
		}
		this.trailer = trailer;
	}
	
	/**
     * Crea una nueva instancia de Documental, sin precuela ni secuela ni trailer.
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
     */
	public Documental(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
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
     * Establece una precuela del documental
     * 
     */
    public void AgregarPrecuela(Documental pre) {
    	ListaPrecuelas.add(pre);
    }

    /**
     * Establece una secuela del documental
     * 
     */
    public void AgregarSecuela(Documental se) {
    	ListaSecuelas.add(se);
    }
    
}
