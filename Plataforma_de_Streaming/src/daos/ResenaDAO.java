package daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import resenas.Resena;

public class ResenaDAO {

	public void insertar(Resena resena) {
		Connection conexion = ConexionBD.getInstancia().conectarBD();
		String SQL = "INSERT INTO RESENA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setInt(1, resena.getEstrellas());
	        p_stmt.setString(2, resena.getTexto());
	        p_stmt.setBoolean(3, resena.estaAprobada());
	        p_stmt.set(4, resena.getFechaHora());
	        p_stmt.setInt(5, resena.getAutor.getID());
	        p_stmt.setInt(6, resena.getContenidoResenado.getID());
			p_stmt.executeUpdate();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		} 
	}


}
