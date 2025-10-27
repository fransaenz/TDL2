package daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import perfiles.Usuario;

public class UsuarioDAO {

	public void insertar(Usuario usuario) {
		Connection conexion = ConexionBD.getInstancia().conectarBD();
		String SQL = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENA) VALUES (?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, usuario.getNombreUsuario());
	        p_stmt.setString(2, usuario.getEmail());
	        p_stmt.setString(3, usuario.getContrasena());
			p_stmt.executeUpdate();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		} 
	}
	
	public List<Usuario> listar() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String SQL= "SELECT * FROM USUARIO";
		
		return listaUsuarios;
	}
	
}
