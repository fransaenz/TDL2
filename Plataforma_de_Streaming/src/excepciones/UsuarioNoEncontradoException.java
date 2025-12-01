package excepciones;

public class UsuarioNoEncontradoException extends Exception {
	public UsuarioNoEncontradoException() { super(); }
    public UsuarioNoEncontradoException(String message) { super(message); }
}
