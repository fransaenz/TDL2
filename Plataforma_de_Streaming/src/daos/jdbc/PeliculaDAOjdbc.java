package daos.jdbc;

import java.sql.*;
import java.util.List;

import daos.conexion.ConexionBD;
import daos.interfaces.PeliculaDAO;
import modelo.audiovisuales.Pelicula;
import modelo.audiovisuales.util.Genero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.time.Duration;

public class PeliculaDAOjdbc implements PeliculaDAO{

	private final Connection conexion;
	
	public PeliculaDAOjdbc() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	
	public void insertar(Pelicula pelicula) {
		
		String SQL = "INSERT INTO PELICULA (TITULO, GENERO, RESUMEN, ESTRELLAS_PROMEDIO, ANIO, POSTER) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			p_stmt.setString(1, pelicula.getTitulo());
			p_stmt.setString(2, pelicula.getGenero().name());
			p_stmt.setString(3, pelicula.getResumen());
			p_stmt.setFloat(4, pelicula.getEstrellasPromedio());
			p_stmt.setInt(5, pelicula.getAnio());
			p_stmt.setString(6, pelicula.getPoster());
			
			p_stmt.executeUpdate();
			
			try(ResultSet keys = p_stmt.getGeneratedKeys()){
				if (keys.next()) {
					int id = keys.getInt(1);
	            	pelicula.setId(id);
	        	}
			}
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar pelicula: " + e.getMessage());
		} 
	}
	
	public List<Pelicula> listarPeliculas(){
		List<Pelicula> listaPeliculas = new LinkedList<Pelicula>();
		String SQL= "SELECT * FROM PELICULA";
		
		try (Statement stmt = conexion.createStatement();
				 ResultSet resul = stmt.executeQuery(SQL))
			{
				Pelicula pelicula;
				
				while(resul.next()) {
					
					pelicula = new Pelicula(
							resul.getInt("ANIO"),
							resul.getString("TITULO"),
							resul.getString("RESUMEN"),
							resul.getFloat("ESTRELLAS_PROMEDIO"),
							Genero.valueOf(resul.getString("GENERO").toUpperCase()),
	        				resul.getString("POSTER")
	            			);
					
					pelicula.setId(resul.getInt("ID"));
					listaPeliculas.addLast(pelicula);
				}
			
			} catch (SQLException e) {
				System.err.println("❌ Error al listar peliculas: " + e.getMessage());
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
	        	Pelicula peli;;
	        	
	        	if (resul.next()) {
	        		peli = new Pelicula(
							resul.getInt("ANIO"),
							resul.getString("TITULO"),
							resul.getString("RESUMEN"),
							resul.getFloat("ESTRELLAS_PROMEDIO"),
							Genero.valueOf(resul.getString("GENERO").toUpperCase()),
	        				resul.getString("POSTER")
	            			);
	        		peli.setId(resul.getInt("ID"));
	        		return peli;
	        	}
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar pelicula: " + e.getMessage());
	    }
	    return null;
	}
	
	
	

}
