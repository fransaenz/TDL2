package modelo.perfiles;

import java.util.List;

import modelo.audiovisuales.*;
import modelo.audiovisuales.util.*;
import modelo.resenas.Resena;

import java.util.LinkedList;


public class Usuario extends Perfil {
	
	
	private int id;
	
	/*private List<VistoHasta> continuarViendo;*/
	
	//private List<Audiovisual> favoritos;
	
	//private List<Audiovisual> historial;
	
	private List<Resena> misResenas;
	
	private Pais pais;
	private Genero preferencia;
	private Idioma idioma;
	
	/*private Plan plan*/
	
	/*
	public Usuario(String email, String contrasena, String nombreUsuario) {
		super(email, contrasena, nombreUsuario);
		this.favoritos = null;
		this.historial = null; 
		this.misResenas = null;
	}
	*/
	public Usuario(String email, String contrasena, String nombreUsuario) {
		super(email, contrasena, nombreUsuario);
		this.misResenas = null;
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	public List<Resena> getMisResenas() {
		return misResenas;
	}




	public void setMisResenas(List<Resena> misResenas) {
		this.misResenas = misResenas;
	}




	public Pais getPais() {
		return pais;
	}




	public void setPais(Pais pais) {
		this.pais = pais;
	}




	public Genero getPreferencia() {
		return preferencia;
	}




	public void setPreferencia(Genero preferencia) {
		this.preferencia = preferencia;
	}




	public Idioma getIdioma() {
		return idioma;
	}




	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}




	public Resena crearResena(Pelicula av, int estrellas, String texto) {
		if (av == null) throw new IllegalArgumentException("Seleccione un audiovisual válido.");
	    Resena res = new Resena(av,this,estrellas,texto);
	    return res;
	}
	
	public void agregarResena(Resena res) {
	    if (misResenas == null) misResenas = new LinkedList<Resena>();
	    misResenas.addLast(res);
	}
	
	/*
	public void crearLista(String titulo) {
		String t = titulo;
		List<Audiovisual> t = new LinkedList<Audiovisual>();
	}
	
	public void sumarAudiovisual(Audiovisual av, LinkedList<Audiovisual> lista){
		if (av == null) throw new IllegalArgumentException("Seleccione un audiovisual válido.");
	    lista.addLast(av);
	}
	
	public void eliminarAudiovisual(Audiovisual av, List<Audiovisual> lista){
		lista.remove(av);
	}
	
	public void eliminarPosicion(int pos, List<Audiovisual> lista){
		lista.remove(pos);
	}
	*/
	
	
	/*public void verCatalogo(Catalogo c) {
		c.getContenido();
	}
	
	public Audiovisual elegirQueMirar() {
		
	}
	
	public void mandarAReproductor(Audiovisual av) {
		
	}
	*/
	
	
	@Override
	public String toString() {
		return "ID de usuario: " + id + ", " + super.toString();
	}
   
}
