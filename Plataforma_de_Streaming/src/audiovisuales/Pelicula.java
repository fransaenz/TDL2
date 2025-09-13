package audiovisuales;

import java.util.List;

class Pelicula extends Audiovisual{
	
	Pelicula precuela;
	Pelicula secuela;
	Trailer trailer;
	
	public Pelicula(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Pelicula precuela,
			Pelicula secuela, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.precuela = precuela;
		this.secuela = secuela;
		this.trailer = trailer;
	}

	public Pelicula getPrecuela() {
		return precuela;
	}

	public void setPrecuela(Pelicula precuela) {
		this.precuela = precuela;
	}

	public Pelicula getSecuela() {
		return secuela;
	}

	public void setSecuela(Pelicula secuela) {
		this.secuela = secuela;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

}
