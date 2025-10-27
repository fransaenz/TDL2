package daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import audiovisuales.Pelicula;

public class PeliculaDAO {

	private final Connection conexion;
	
	public PeliculaDAO() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	
	public void insertar(Pelicula pelicula) {
		
		String SQL = "INSERT INTO RESENA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, pelicula.getTitulo());
			p_stmt.setString(2, pelicula.getDirector());
			p_stmt.setString(3, pelicula.getGenero().toString()); 
			p_stmt.setInt(4, (int) pelicula.getDuracion().toMinutes());
			p_stmt.setString(5, pelicula.getElenco());
			p_stmt.setString(6, pelicula.getResumen());
			p_stmt.executeUpdate();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar resena: " + e.getMessage());
		} 
	}
	
	public List<Pelicula> listarPeliculas(){
		
	}
	
	
	
}
