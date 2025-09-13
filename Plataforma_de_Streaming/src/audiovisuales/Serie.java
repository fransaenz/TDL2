package audiovisuales;

import java.util.List;

class Serie extends Audiovisual{
	
	int cantTemporadas;
	List <Temporada> temporadas;
	Trailer trailer;
	
	public Serie(String titulo, String director, String idioma, String resumen, String elenco, String genero,
			int duracion, int vistasTotales, double horasVistas, List<String> paisesRestringidos, int cantTemporadas,
			List<Temporada> temporadas, Trailer trailer) {
		super(titulo, director, idioma, resumen, elenco, genero, duracion, vistasTotales, horasVistas,
				paisesRestringidos);
		this.cantTemporadas = cantTemporadas;
		this.temporadas = temporadas;
		this.trailer = trailer;
	}

	public int getCantTemporadas() {
		return cantTemporadas;
	}

	public void setCantTemporadas(int cantTemporadas) {
		this.cantTemporadas = cantTemporadas;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

}
