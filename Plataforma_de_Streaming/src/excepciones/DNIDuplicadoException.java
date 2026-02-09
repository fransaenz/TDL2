package excepciones;

public class DNIDuplicadoException extends Exception {
	
private static final String mensaje = "El DNI ya está registrado.";
	
	public DNIDuplicadoException() {
        super(mensaje);
    }
	
    public DNIDuplicadoException(String msg) {
        super(msg);
    }
}