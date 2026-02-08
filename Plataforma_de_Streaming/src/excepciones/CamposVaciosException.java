package excepciones;

public class CamposVaciosException extends Exception {

	private static final String mensaje = "Todos los campos son obligatorios.";
	
	public CamposVaciosException() {
        super(mensaje);
    }
	
    public CamposVaciosException(String mensaje) {
        super(mensaje);
    }
}