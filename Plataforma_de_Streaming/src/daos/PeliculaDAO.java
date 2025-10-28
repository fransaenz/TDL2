package daos;

import java.sql.*;
import java.util.List;
import java.util.LinkedList;

import audiovisuales.Pelicula;
import perfiles.Persona;

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
			p_stmt.setString(3, pelicula.getGenero().toString()); 
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
				while(resul.next()) {
					
					pelicula = new Pelicula(
							resul.getString("TITULO"),
							resul.getString("DIRECTOR"),
							resul.getString("ELENCO"),
							resul.getString("GENERO")//falta transformarlo a enum,
							resul.getInt("DURACION")//falta pasarlo a tiempo,
							resul.getString("RESUMEN")
							);
					listaPeliculas.addLast(pelicula);
				}
			
			} catch (SQLException e) {
				System.err.println("❌ Error al listar personas: " + e.getMessage());
			}
		
		return listaPeliculas;
	}
	
	
	
}
