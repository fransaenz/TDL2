package controladores;

import excepciones.CsvImportException;

public class CargadorPeliculasThread extends Thread {

    // Callback que la GUI pasará para ser notificada al terminar (onSuccess).
    private final Runnable onSuccess;

    // Callback para notificar errores (onError) — recibe Exception en la implementación.
    private final java.util.function.Consumer<Exception> onError;
    private final String rutaCsv;

    public CargadorPeliculasThread(String rutaCsv,
                                   Runnable onSuccess,
                                   java.util.function.Consumer<Exception> onError) {
        super("CargadorPeliculasThread");
        this.rutaCsv = rutaCsv;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    @Override
    public void run() {
        try {
            // Ejecuta la importación pesada en background (no bloquea el EDT).
            ImportadorPeliculas.importarCSV(rutaCsv);

            // Notificar éxito al caller. Si la callback modifica Swing components, el caller
            // debe invocar SwingUtilities.invokeLater(...) dentro de la lambda (ver explicación).
            if (onSuccess != null) onSuccess.run();

        } catch (CsvImportException e) {
            // Excepción controlada (propagada por ImportadorPeliculas)
            if (onError != null) onError.accept(e);
        } catch (Exception e) {
            // Cualquier otra excepción inesperada
            if (onError != null) onError.accept(e);
        }
    }
}