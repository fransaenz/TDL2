package perfiles;

import audiovisuales.*;

import resenas.Resena;

import java.util.List;


public class Usuario extends Perfil {
	
	
	
	public Usuario(String email, String contrasena, String nombreUsuario) {
		super(email, contrasena, nombreUsuario);
		this.favoritos = null;
		this.historial = null; 
		this.misResenas = null;
	}
	
	

	private enum Idioma {ESPANOL, INGLES, PROTUGUES}
	
	private enum Preferencias{COMEDIA, SUSPENSO, TERROR, DRAMA, ACCION, CIENCIA_FICCION };
	
	private enum Pais {ARGENTINA, BRASIL, URUGUAY};
	
	/*private List<VistoHasta> continuarViendo;*/
	
	private List<Audiovisual> favoritos;
	
	private List<Audiovisual> historial;
	
	private List<Resena> misResenas;
	
	/*private Plan plan*/
	
	
	
	public Resena crearResena() {
		
	}
	
	public void agregarResena(Audiovisual av, Resena res) {
		
	}
	
	public void crearLista(String titulo) {
		
	}
	
	public void sumarAudiovisual(Audiovisual av, List<Audiovisual> lista){
		
	}
	
	public void eliminarAudiovisual(Audiovisual av, List<Audiovisual> lista){
		
	}
	
	public void eliminarPosicion(int pos, List<Audiovisual> lista){
		
	}
	
	public void verCatalogo() {
		
	}
	
	public Audiovisual elegirQueMirar() {
		
	}
	
	public void mandarAReproductor(Audiovisual av) {
		
	}
	
	
}
