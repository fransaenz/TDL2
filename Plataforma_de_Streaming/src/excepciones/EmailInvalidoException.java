package excepciones;

public class EmailInvalidoException extends Exception {

	private static final String mensaje = "El formato del Email no es válido.";
	
	public EmailInvalidoException() {
        super(mensaje);
    }

    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
}