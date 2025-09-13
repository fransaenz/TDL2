package audiovisuales;

import java.util.List;

class Documental extends Audiovisual{
	
	Documental precuela;
	Documental secuela;
	Trailer trailer;
	
	public Documental(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Documental precuela,
			Documental secuela, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.precuela = precuela;
		this.secuela = secuela;
		this.trailer = trailer;
	}

	public Documental getPrecuela() {
		return precuela;
	}

	public void setPrecuela(Documental precuela) {
		this.precuela = precuela;
	}

	public Documental getSecuela() {
		return secuela;
	}

	public void setSecuela(Documental secuela) {
		this.secuela = secuela;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}
	
}
