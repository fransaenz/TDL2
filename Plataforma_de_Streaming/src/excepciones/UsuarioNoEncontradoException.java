package excepciones;

public class UsuarioNoEncontradoException extends Exception {
	
	private static final String mensaje = "El Usuario no esta registrado.";
	
	public UsuarioNoEncontradoException() { super(mensaje); }
	
	
    public UsuarioNoEncontradoException(String message) { super(message); }
}
