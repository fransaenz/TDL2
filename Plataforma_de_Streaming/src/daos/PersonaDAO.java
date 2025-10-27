package daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import perfiles.Persona;
import perfiles.Usuario;

public class PersonaDAO {
	
	
	private final Connection conexion;
	
	
	public PersonaDAO() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	

	public void insertar(Persona persona) {
		
		String SQL = "INSERT INTO DATOS_PERSONALES (NOMBRE, APELLIDO, DNI, NRO_TARJETA) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, persona.getNombre());
	        p_stmt.setString(2, persona.getApellido());
	        p_stmt.setInt(3, persona.getDni());
	        p_stmt.setInt(4, persona.getNroTarjeta());
	        p_stmt.setInt(5, 0);
			p_stmt.executeUpdate();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		} 
	}

	
	public List<Persona> listarPersonas(){
		
	}
	
	public void actualizarUsuario(Usuario usuario) {
		
	}
	
	
	public Persona buscarPorDNI(int dni) {
		
	}
	
	public boolean existeDNI(int dni) {
		
	}
	
}
