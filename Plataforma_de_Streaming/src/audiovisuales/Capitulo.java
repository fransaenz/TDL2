package audiovisuales;

import java.util.List;

class Capitulo extends Audiovisual {
	
	Trailer trailer;
	
	public Capitulo(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.trailer = trailer;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}
	
}
