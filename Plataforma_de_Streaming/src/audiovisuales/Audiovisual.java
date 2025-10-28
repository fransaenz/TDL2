package audiovisuales;

import resenas.Resena;
import java.util.List;
import java.time.Duration;
import util.*;


/**
 * Clase abstracta que representa un contenido audiovisual, con informacion
 * general como titulo, director, idioma, elenco, genero, duracion, vistas, países restringidos, etc.
 * 
 * @author Lucas, Francisco
 * @version 2.0+
 */

public abstract class Audiovisual {
	
	 /** Titulo del audiovisual */
    private String titulo;

    /** Director del audiovisual */
    private String director;

    /** Idioma original del audiovisual */
    private Idioma idioma;

    /** Un resumen del audiovisual */
    private String resumen;

    /** Actores principales */
    private String elenco;

    /** Genero del audiovisual (ej. drama, comedia, etc.) */
    private Genero genero;

    /** Duracion del audiovisual */
    private Duration duracion;

    /** Numero total de visualizaciones */
    private int vistasTotales;

    /** Total de horas vistas */
    private Duration horasVistas;

    /** Lista de paises donde el audiovisual esta restringido */
    private List<Pais> paisesRestringidos;
    
    /** Lista de resenas del audiovisual */
	List<Resena> resenas;
    
    /** Lista de subtitulos disponibles para el audiovisual */
	//List<Subtitulo> subtitulos;
    
    /** Lista de doblajes disponibles para el audiovisual */
	//List<Doblaje> doblajes;
    
    /** Lista de videos disponibles para el audiovisual */
	//List<Video> videos

    
    /**
     * Crea una nueva instancia de Audiovisual.
     * 
     * @param titulo titulo del audiovisual
     * @param director director del audiovisual
     * @param Idioma idioma original
     * @param resumen resumen del audiovisual
     * @param elenco actores principales
     * @param Genero genero audiovisual
     * @param duracion duracion del audiovisual
     * @param vistasTotales numero total de visualizaciones
     * @param horasVistas total de horas vistas
     * @param paisesRestringidos lista de paises restringidos
     * y faltan las 4 variables que estan comentadas 
     */
	public Audiovisual(String titulo, String director, Idioma idioma, String resumen, String elenco, Genero genero,
			Duration duracion, int vistasTotales, Duration horasVistas, List<Pais> paisesRestringidos) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.idioma = idioma;
		this.resumen = resumen;
		this.elenco = elenco;
		this.genero = genero;
		this.duracion = duracion;
		this.vistasTotales = vistasTotales;
		this.horasVistas = horasVistas;
		this.paisesRestringidos = paisesRestringidos; 
		this.resenas = null;
	}

	
	public Audiovisual(String titulo, String director, String elenco, Genero genero,
			Duration duracion, String resumen) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.elenco = elenco;
		this.genero = genero;
		this.duracion = duracion;
		this.resumen = resumen; 
		this.resenas = null;
	}
	
	public Audiovisual(String titulo, String director, Idioma idioma, String elenco, Genero genero,
			Duration duracion, List<Pais> paisesRestringidos) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.idioma = idioma;
		this.elenco = elenco;
		this.genero = genero;
		this.duracion = duracion;
		this.paisesRestringidos = paisesRestringidos; 
		this.resenas = null;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	/**
     * Devuelve la lista de paises donde esta restringido el audiovisual
     * 
     * @return lista de paises restringidos
     */
    public List<Pais> getPaisesRestringidos() {
        return paisesRestringidos;
    }

    /**
     * Establece la lista de paises restringidos
     * 
     * @param paisesRestringidos nueva lista de paises
     */
    public void setPaisesRestringidos(List<Pais> paisesRestringidos) {
        this.paisesRestringidos = paisesRestringidos;
    }

    /**
     * Devuelve el titulo del audiovisual
     * 
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el titulo del audiovisual
     * 
     * @param titulo nuevo titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve el director del audiovisual
     * 
     * @return director
     */
    public String getDirector() {
        return director;
    }

    /**
     * Establece el director del audiovisual
     * 
     * @param director nuevo director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Devuelve el idioma original
     * 
     * @return idioma
     */
    public Idioma getIdioma() {
        return idioma;
    }

    /**
     * Establece el idioma original
     * 
     * @param idioma nuevo idioma
     */
    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    /**
     * Devuelve el resumen del audiovisual
     * 
     * @return resumen
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Establece el resumen del audiovisual
     * 
     * @param resumen nuevo resumen
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * Devuelve el elenco de actores principales
     * 
     * @return elenco
     */
    public String getElenco() {
        return elenco;
    }

    /**
     * Establece el elenco de actores principales
     * 
     * @param elenco nuevo elenco
     */
    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    /**
     * Devuelve el genero del audiovisual
     * 
     * @return genero
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Establece el genero del audiovisual
     * 
     * @param genero nuevo genero
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Devuelve la duracion
     * 
     * @return duracion
     */
    public Duration getDuracion() {
        return duracion;
    }

    /**
     * Establece la duracion
     * 
     * @param duracion nueva duracion
     */
    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve el numero total de visualizaciones
     * 
     * @return vistas totales
     */
    public int getVistasTotales() {
        return vistasTotales;
    }

    /**
     * Establece el numero total de visualizaciones
     * 
     * @param vistasTotales nuevo total de vistas
     */
    public void setVistasTotales(int vistasTotales) {
        this.vistasTotales = vistasTotales;
    }

    /**
     * Devuelve el total de horas vistas
     * 
     * @return horas vistas
     */
    public Duration getHorasVistas() {
        return horasVistas;
    }

    /**
     * Establece el total de horas vistas
     * 
     * @param horasVistas nuevo total de horas vistas
     */
    public void setHorasVistas(Duration horasVistas) {
        this.horasVistas = horasVistas;
    }

    /**
     * Indica si el audiovisual esta restringido en un pais dado
     * 
     * @param pais pais a consultar
     * @return true si esta restringido, false si se permite
     */
    public boolean permitido(Pais pais) {
        boolean respuesta = false;
        if (paisesRestringidos.contains(pais)) respuesta = true; 
        return respuesta;
    }
    
    /**
     * Agrega una resenia a la lista de resenias del Audiovisual
     * 
     * @param resenia es la resenia que se quiere sumar a la lista
     */
    public void sumarResena(Resena resenia) {
    	resenas.add(resenia);
    }

    /**
     * Devuelve la lista de resenias del audiovisual
     * 
     * @return lista de resenias
     */
	public List<Resena> getResenas() {
		return resenas;
	}

	/**
     * Establece la lista de resenias
     * 
     * @param resenas nueva lista de resenias
     */
	public void setResenas(List<Resena> resenas) {
		this.resenas = resenas;
	}
}