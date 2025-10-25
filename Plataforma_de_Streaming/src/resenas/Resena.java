package resenas;

import audiovisuales.Audiovisual;

import perfiles.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Resena {
	
	
	
	public Resena(Audiovisual contenidoResenado, Usuario autor, int estrellas, String texto) {
		this.contenidoResenado = contenidoResenado;
		this.autor = autor;
		this.estrellas = estrellas;
		this.texto = texto;
		this.aprobada = false;
		this.fechaHora = LocalDateTime.now();
	}
	
	

	private Audiovisual contenidoResenado;
	
	private Usuario autor;
	
	private int estrellas;
	
	private String texto;

	private boolean aprobada;
	
	private LocalDateTime fechaHora;
	
	/*getters setters*/
	
	public Audiovisual getContenidoResenado() {
		return contenidoResenado;
	}

	public void setContenidoResenado(Audiovisual contenidoResenado) {
		if (contenidoResenado == null) {
            throw new IllegalArgumentException("El contenido reseñado no puede ser nulo.");
		}
		this.contenidoResenado = contenidoResenado;
	}

	
	public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor; // puede ser null para reseñas anónimas
    }
	
	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		 if (estrellas < 1 || estrellas > 5) {
	            throw new IllegalArgumentException("La calificación debe ser de entre 1 y 5 estrellas.");
	        }
		this.estrellas = estrellas;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public boolean estaAprobada() {
        return aprobada;
    }
	
	 public LocalDateTime getFechaHora() {
	        return fechaHora;
	    }
	 
	/*mas metodos*/
	 
	 public void aprobar() {
		 this.aprobada = true;
	 }

	 public void desaprobar() {
		 this.aprobada = false;
	 }
	 
	 @Override
	 public String toString() {
		 String autorStr = (autor != null) ? autor.getNombreUsuario() : "Anónimo";
		 String fechaStr = this.fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		 return String.format("%s - %d★\n%s\n(autor: %s) (fecha: %s) (aprobada: %s)",
	                contenidoResenado.getTitulo(),
	                estrellas,
	                texto.isEmpty() ? "(sin comentario)" : texto,
	                autorStr,
	                fechaStr,
	                aprobada ? "sí" : "no");
	 }
	 
	 
}
