package excepciones;

public class EmailInvalidoException extends Exception {

    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
}