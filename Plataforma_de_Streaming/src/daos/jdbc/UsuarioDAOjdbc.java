package daos.jdbc;

import java.sql.*;
import java.util.List;

import daos.conexion.ConexionBD;
import daos.interfaces.UsuarioDAO;
import modelo.perfiles.Usuario;

import java.util.LinkedList;

public class UsuarioDAOjdbc implements UsuarioDAO{

	private final Connection conexion;
	
	
	public UsuarioDAOjdbc() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	
	
	public int insertar(Usuario usuario) {

		String SQL = "INSERT INTO USUARIO (NOMBRE_USUARIO, EMAIL, CONTRASENA) VALUES (?, ?, ?)";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)){
			p_stmt.setString(1, usuario.getNombreUsuario());
	        p_stmt.setString(2, usuario.getEmail());
	        p_stmt.setString(3, usuario.getContrasena());
	        p_stmt.executeUpdate();
			
			try(ResultSet keys = p_stmt.getGeneratedKeys()){
				if (keys.next()) {
					int id = keys.getInt(1);
	            	usuario.setId(id);
	            	return id;
	        	}
			}
			
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		}
		
		return -1;
	}
	
	public List<Usuario> listarUsuarios() {
		
		List<Usuario> listaUsuarios = new LinkedList<Usuario>();
		String SQL= "SELECT * FROM USUARIO";
		
		try (Statement stmt = conexion.createStatement();
				 ResultSet resul = stmt.executeQuery(SQL))
		{
			Usuario usuario;
			while(resul.next()) {
				usuario = new Usuario(
						resul.getString("EMAIL"),
						resul.getString("CONTRASENA"),
						resul.getString("NOMBRE_USUARIO")
				);
				listaUsuarios.addLast(usuario);
			}
		
		} catch (SQLException e) {
			System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
	
	public Usuario buscarPorId(int id) {
	    String SQL = "SELECT * FROM USUARIO WHERE ID = ?";
	    try (PreparedStatement p_stmt = conexion.prepareStatement(SQL)) 
	    {
	        p_stmt.setInt(1, id);
	        try (ResultSet resul = p_stmt.executeQuery())
	        {
	        	Usuario usr;
	        	if (resul.next()) {
	        		usr = new Usuario(
	        				resul.getString("NOMBRE_USUARIO"),
	        				resul.getString("EMAIL"),
	        				resul.getString("CONTRASENA")
	        				);
	        		usr.setId(resul.getInt("ID"));
	        		return usr;
	        	}
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar usuario: " + e.getMessage());
	    }
	    return null;
	}
	
	
	
	
	public boolean existeEmail(String email) {
		String SQL = "SELECT * FROM USUARIO WHERE EMAIL = ?";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL))
		{
			p_stmt.setString(1, email);
			try (ResultSet resul = p_stmt.executeQuery())
			{
					return resul.next();
			}
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el Email: " + e.getMessage());
			return false;
		}
	}
	
}
