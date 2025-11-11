package modelo.perfiles;

public class Persona {


	public Persona(String nombre, String apellido, int dni, int nroTarjeta, Usuario perfil) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.nroTarjeta = nroTarjeta;
		this.perfil = perfil;
	}
	
	public Persona(String nombre, String apellido, int dni, int nroTarjeta) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.nroTarjeta = nroTarjeta;
		this.perfil = null;
	}

	private int id;
	
	private String nombre;
	
	private String apellido;
	
	private int dni;
	
	private int nroTarjeta;
	
	private Usuario perfil;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Usuario getPerfil() {
		return perfil;
	}

	public void setPerfil(Usuario perfil) {
		this.perfil = perfil;
	}

	public String toString() {
		return "Persona [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", nroTarjeta=" + nroTarjeta
				+ "]";
	};
	
	

}
