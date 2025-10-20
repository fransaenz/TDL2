package perfiles;

public class Persona {


	public Persona(String nombre, String apellido, String dni, int nroTarjeta, Perfil perfil) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.nroTarjeta = nroTarjeta;
		this.perfil = perfil;
	}

	
	
	private String nombre;
	
	private String apellido;
	
	private String dni;
	
	private int nroTarjeta;
	
	private enum Pais {ARGENTINA, BRASIL, URUGUAY}
	
	private Perfil perfil;

	
	
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(int nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	};
	
	

}
