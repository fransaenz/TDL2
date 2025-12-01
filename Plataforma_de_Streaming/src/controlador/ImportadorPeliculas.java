package controlador;

import modelo.audiovisuales.Pelicula;
import daos.interfaces.PeliculaDAO;
import daos.jdbc.PeliculaDAOjdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;
import java.util.regex.Pattern;

import excepciones.CsvImportException;

public class ImportadorPeliculas {

	public static final List<Pelicula> peliculasEnMemoria = new ArrayList<>();
	
	private static final Pattern CSV_SPLIT_PATTERN = Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

	 public static void importarCSV(String rutaCsv) throws CsvImportException {
	        peliculasEnMemoria.clear();
	        Path path = Path.of(rutaCsv);
	        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {

	            String linea;
	            boolean primera = true;
	            while ((linea = br.readLine()) != null) {
	                // Saltar encabezado (primera línea)
	                if (primera) { primera = false; continue; }
	                if (linea.isBlank()) continue;

	                // Split respetando comillas
	                String[] campos = CSV_SPLIT_PATTERN.split(linea, -1);

	                // Validamos que tengamos al menos 9 campos según el CSV proporcionado
	                if (campos.length < 9) {
	                    // se lanza excepción propia con info útil
	                    throw new CsvImportException("Línea con formato inválido (campos menores a 9): " + linea);
	                }

	                // Mapear campos (índices basados en movies_database.csv)
	                String releaseDate = campos[0].trim();
	                String title       = quitarComillas(campos[1]).trim();
	                String overview    = quitarComillas(campos[2]).trim();
	                String popularity  = campos[3].trim();
	                String voteCount   = campos[4].trim();
	                String voteAverage = campos[5].trim();
	                String language    = campos[6].trim();
	                String genre       = quitarComillas(campos[7]).trim();
	                String poster      = quitarComillas(campos[8]).trim();

	                // Adaptar género: tomar primer género si hay varios
	                String generoFinal = "Sin Género";
	                if (!genre.isBlank()) {
	                    String[] gParts = genre.split(",");
	                    generoFinal = gParts[0].trim();
	                }

	                // Extraer año (Release_Date formato YYYY-MM-DD). Si falla, dejamos 0 (nullable lógico).
	                int anio = 0;
	                try {
	                    if (releaseDate != null && releaseDate.length() >= 4) {
	                        anio = Integer.parseInt(releaseDate.substring(0, 4));
	                    }
	                } catch (NumberFormatException ignored) {
	                    anio = 0;
	                }

	                // Parsear rating (Vote_Average)
	                float rating = 0f;
	                try {
	                    if (!voteAverage.isBlank()) rating = Float.parseFloat(voteAverage);
	                } catch (NumberFormatException ignored) {
	                    rating = 0f;
	                }

	                // Crear la entidad Pelicula (asegurate que tu model.Pelicula tenga este constructor)
	                Pelicula p = new Pelicula(title, generoFinal, rating, anio, poster);

	                // Persistir con DAO (usa PreparedStatement dentro del DAO; DAO debe manejar SQLExceptions)
	                try {
	                    PeliculaDAO.insertar(p);
	                } catch (Exception daoEx) {
	                    // Si falla persistencia, encapsulamos y lanzamos CsvImportException
	                    throw new CsvImportException("Error al insertar película en BD: " + title, daoEx);
	                }

	                // Añadir a la caché en memoria
	                peliculasEnMemoria.add(p);
	            }

	            // Ordenar por rating_promedio descendente
	            peliculasEnMemoria.sort(Comparator.comparing(Pelicula::getRatingPromedio).reversed());

	        } catch (IOException e) {
	            // Re-lanzar como excepción propia para que el controlador/Thread lo capture y la GUI lo maneje.
	            throw new CsvImportException("Error de E/S leyendo CSV: " + rutaCsv, e);
	        }
	    }

	    // Método utilitario para quitar comillas envolventes si existen.
	    private static String quitarComillas(String s) {
	        if (s == null) return "";
	        s = s.trim();
	        if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
	            return s.substring(1, s.length() - 1);
	        }
	        return s;
	    }

	    // Devuelve top N (usamos 10 en la GUI). Protegemos con subList safe.
	    public static List<Pelicula> obtenerTopN(int n) {
	        int size = Math.min(n, peliculasEnMemoria.size());
	        return new ArrayList<>(peliculasEnMemoria.subList(0, size));
	    }

	    public static List<Pelicula> obtenerTop10() {
	        return obtenerTopN(10);
	    }
	
}
