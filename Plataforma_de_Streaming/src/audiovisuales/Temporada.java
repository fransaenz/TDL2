package audiovisuales;

import java.util.List;

class Temporada extends Audiovisual{

	int cantCapitulos;
	List<Capitulo> capitulos;
	Trailer trailer;
	
	public Temporada(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, int cantCapitulos,
			List<Capitulo> capitulos, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.cantCapitulos = cantCapitulos;
		this.capitulos = capitulos;
		this.trailer = trailer;
	}

	public int getCantCapitulos() {
		return cantCapitulos;
	}

	public void setCantCapitulos(int cantCapitulos) {
		this.cantCapitulos = cantCapitulos;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

}
