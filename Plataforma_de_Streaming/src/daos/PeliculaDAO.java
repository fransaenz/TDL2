package daos;

import java.sql.*;
import java.time.Duration;
import java.util.List;
import java.util.LinkedList;

import audiovisuales.Pelicula;
import perfiles.Persona;
import perfiles.Usuario;
import util.Genero;

public class PeliculaDAO {

	private final Connection conexion;
	
	public PeliculaDAO() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	
	public void insertar(Pelicula pelicula) {
		
		String SQL = "INSERT INTO PELICULA (TITULO, DIRECTOR, GENERO, DURACION, ELENCO, RESUMEN) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, pelicula.getTitulo());
			p_stmt.setString(2, pelicula.getDirector());
			p_stmt.setString(3, pelicula.getGenero().name()); 
			p_stmt.setInt(4, (int) pelicula.getDuracion().toMinutes());
			p_stmt.setString(5, pelicula.getElenco());
			p_stmt.setString(6, pelicula.getResumen());
			p_stmt.executeUpdate();
			p_stmt.close();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar resena: " + e.getMessage());
		} 
	}
	
	public List<Pelicula> listarPeliculas(){
		List<Pelicula> listaPeliculas = new LinkedList<Pelicula>();
		String SQL= "SELECT * FROM PELICULA";
		
		try (Statement stmt = conexion.createStatement();
				 ResultSet resul = stmt.executeQuery(SQL))
			{
				Pelicula pelicula;
				Genero genero = Genero.valueOf(resul.getString("GENERO").toUpperCase());
				Duration duracion; //= //tal tal tal
				while(resul.next()) {
					
					pelicula = new Pelicula(
	        				resul.getString("TITULO"),
	        				resul.getString("DIRECTOR"),
	        				resul.getString("ELENCO"),
	        				genero,
	        				duracion,
	        				resul.getString("RESUMEN")
	            			);
					listaPeliculas.addLast(pelicula);
				}
			
			} catch (SQLException e) {
				System.err.println("❌ Error al listar personas: " + e.getMessage());
			}
		
		return listaPeliculas;
	}
	
	public Pelicula buscarPorId(int id) {
	    String SQL = "SELECT * FROM PELICULA WHERE ID = ?";
	    try (PreparedStatement p_stmt = conexion.prepareStatement(SQL)) 
	    {
	        p_stmt.setInt(1, id);
	        try (ResultSet resul = p_stmt.executeQuery())
	        {
	        	Pelicula peli;
	        	Genero genero = Genero.valueOf(resul.getString("GENERO").toUpperCase());
	        	Duration duracion; //= //tal tal tal
	        	if (resul.next()) {
	        		peli = new Pelicula(
	        				resul.getString("TITULO"),
	        				resul.getString("DIRECTOR"),
	        				resul.getString("ELENCO"),
	        				genero,
	        				duracion,
	        				resul.getString("RESUMEN")
	            			);
	        		peli.setId(resul.getInt("ID"));
	        		return peli;
	        	}
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar usuario: " + e.getMessage());
	    }
	    return null;
	}
	
}
