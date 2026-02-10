package excepciones;

public class EmailDuplicadoException extends Exception {
	
private static final String mensaje = "El Email ya está registrado.";
	
	public EmailDuplicadoException() {
        super(mensaje);
    }
	
    public EmailDuplicadoException(String msg) {
        super(msg);
    }
}