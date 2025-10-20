package resenas;

import audiovisuales.Audiovisual;

import perfiles.Usuario;

public class Resena {
	
	
	
	public Resena(Audiovisual contenidoResenado, int estrellas, String texto) {
		super();
		this.contenidoResenado = contenidoResenado;
		this.estrellas = estrellas;
		this.texto = texto;
	}
	
	

	private Audiovisual contenidoResenado;
	
	private Usuario autor;
	
	private int estrellas;
	
	private String texto;

	private boolean aprobada;
	
	
	
	public Audiovisual getContenidoResenado() {
		return contenidoResenado;
	}

	public void setContenidoResenado(Audiovisual contenidoResenado) {
		this.contenidoResenado = contenidoResenado;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
