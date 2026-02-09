package controladores;

import modelo.audiovisuales.Pelicula;
import modelo.audiovisuales.util.Genero;
import daos.interfaces.PeliculaDAO;
import daos.jdbc.PeliculaDAOjdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import excepciones.CsvImportException;

import java.time.LocalDate;


public class ImportadorPeliculas implements Runnable{

	private final String rutaCSV = "recursos/movies_database.csv";
	
	public static final List<Pelicula> peliculasEnMemoria = new ArrayList<>();

	public void run() {
		try {
			importarCSV();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void importarCSV() throws FileNotFoundException, IOException {
		
		peliculasEnMemoria.clear();
		
		List<String> errores = new ArrayList<>();
	        
		
		try (BufferedReader br = new BufferedReader(new FileReader(rutaCsv))){
	       
	    	String linea = br.readLine(); //leo y descarto la primera linea, es decir el encabezado, que no tiene datos
	        
	    	PeliculaDAO pDao = new PeliculaDAOjdbc();
	    	
	    	int nroLinea = 1;
	    	
	    	while ((linea = br.readLine()) != null) {
	    		
	    		nroLinea++;
	    		
	    		try {
	    		
	    			List<String> campos = splitearLineaCSV(linea);
	    		
	    			if (campos.size() < 9) {
	    				throw new CsvImportException("Error de formato: se esperaban 9 columnas pero llegaron "
	    				+ campos.size() + ". Línea: " + linea);
	    			}
	    		
	    			String releaseDate  = campos.get(0);
	    			String title        = campos.get(1);
	    			String overview     = campos.get(2);
	    			String popularity   = campos.get(3);
	    			String voteCount    = campos.get(4);
	    			String voteAverage  = campos.get(5);
	    			String language     = campos.get(6);
	    			String genre        = campos.get(7);
	    			String poster       = campos.get(8);
	        
	    			String primerGenero = genre.split(",")[0].trim(); //elijo el primer genero
	        
	    			int anio;
	    			try {
	    				anio = LocalDate.parse(releaseDate).getYear();
	    			} catch (Exception e) {
	    				throw new CsvImportException("Línea " + nroLinea + ": Fecha inválida (" + releaseDate + ")", e);
	    			}
	            
	    			float ratingPromedio;
	    			try {
	    				ratingPromedio = Float.parseFloat(voteAverage);
	    			} catch (Exception e) {
	    				throw new CsvImportException("Línea " + nroLinea + ": Rating inválido: " + voteAverage + ")", e);
	    			}
	            
	    			Genero genero = convertirGenero(primerGenero);
	            
	    			Pelicula p = new Pelicula(anio, title, overview, ratingPromedio, genero, poster); //no uso popularity, voteCount ni language porque no son necesarios

	    			pDao.insertar(p);     		// guardo pelicula en la BD
	    			peliculasEnMemoria.add(p);  // guardo pelicula en la memoria
	    		
	    		} catch (CsvImportException e){
	    			errores.add(e.getMessage());
	    		}
	    	}
	    }
	    ordenarPorRatingDesc();
	    
	    if (!errores.isEmpty()) {
	        System.out.println("Errores detectados durante la importación:");
	        errores.forEach(System.out::println);
	    }
	}

	private static List<String> splitearLineaCSV(String linea) {
	    List<String> campos = new ArrayList<>();
	    char[] chars = linea.toCharArray(); //convierto la linea en vector de char

	    boolean dentroComillas = false; 
	    String campo = ""; 
	    
	    for (char c : chars) 
	    { 
	    	
            if (c == '"')  //si el caracter actual son comillas
            {
                dentroComillas = !dentroComillas; // cambio el valor de dentroComillas
            }
            
            else if (c == ',' && !dentroComillas)  //si estamos en una coma y no entre comillas
            {
            	campos.add(campo); //agrego el campo
                campo = ""; //reinicio campo   
            }
            
            else //si sigo dentro de las comillas
            {
                campo += c; // concateno caracter al campo actual
            }
            
        }

        campos.add(campo); // último campo
        return campos;
	}

	
	private static Genero convertirGenero(String texto) {

	    texto = texto.trim().toLowerCase();

	    switch (texto) {

	        case "action"		   : return Genero.ACCION;

	        case "animation"	   : return Genero.ANIMADA;

	        case "adventure"	   : return Genero.AVENTURA;

	        case "science fiction" : return Genero.CIENCIA_FICCION;

	        case "comedy" 		   : return Genero.COMEDIA;

	        case "drama"  		   : return Genero.DRAMA;

	        case "family"		   : return Genero.FAMILIA;

	        case "fantasy"		   : return Genero.FANTASIA;

	        case "war" 			   : return Genero.GUERRA;

	        case "horror"		   : return Genero.HORROR;

	        case "mystery"		   : return Genero.MISTERIO;

	        case "music"		   : return Genero.MUSICA;

	        case "crime"		   : return Genero.POLICIAL;

	        case "thriller" 	   : return Genero.THRILLER;

	        default 	   		   : return Genero.DESCONOCIDO;
	    
	    }

	}
	
	public static void ordenarPorRatingDesc() {
	    peliculasEnMemoria.sort(
	        (p1, p2) -> Float.compare(p2.getEstrellasPromedio(), p1.getEstrellasPromedio())
	    );
	}

	public static List<Pelicula> obtenerTop10() {

	    if (peliculasEnMemoria.size() <= 10)
	        return new ArrayList<>(peliculasEnMemoria);

	    return new ArrayList<>(peliculasEnMemoria.subList(0, 10));
	}

}

