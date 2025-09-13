package audiovisuales;

import java.util.List;

class Trailer extends Audiovisual {

	public Trailer(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas, paisesRestringidos);
	}
	
}
