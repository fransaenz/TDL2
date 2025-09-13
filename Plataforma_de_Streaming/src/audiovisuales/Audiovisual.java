package audiovisuales;

import java.util.List;

abstract class Audiovisual {
		
	String titulo;
	String director;
	String idioma;
	String resumen;
	String elenco;
	String genero;
	int duracion;
	int vistasTotales;
	double horasVistas;
	List<String> paisesRestringidos;
	//List<Resena> resena;
	//List<Subtitulo> subtitulos;
	//List<Doblaje> doblajes;
	//List<Video> videos

	public Audiovisual(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos) {
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
	}


	public List<String> getPaisesRestringidos() {
		return paisesRestringidos;
	}
	public void setPaisesRestringidos(List<String> paisesRestringidos) {
		this.paisesRestringidos = paisesRestringidos;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getElenco() {
		return elenco;
	}
	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getVistasTotales() {
		return vistasTotales;
	}
	public void setVistasTotales(int vistasTotales) {
		this.vistasTotales = vistasTotales;
	}

	public double getHorasVistas() {
		return horasVistas;
	}
	public void setHorasVistas(double horasVistas) {
		this.horasVistas = horasVistas;
	}

	public boolean permitido (String pais) {
		boolean respuesta = false;
		if (paisesRestringidos.contains(pais)) respuesta = true; 
		return respuesta;
	}

}
