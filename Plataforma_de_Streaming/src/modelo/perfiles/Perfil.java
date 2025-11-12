package modelo.perfiles;

public abstract class Perfil {


	public Perfil(String email, String contrasena, String nombreUsuario) {
		this.email = email;
		this.contrasena = contrasena;
		this.nombreUsuario = nombreUsuario;
	}

	
	
	private String email;
	
	private String contrasena;
	
	private String nombreUsuario;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	@Override
	public String toString() {
		return "email: " + email + ", contrasena: " + contrasena + ", nombreUsuario: " + nombreUsuario;
	}
}
