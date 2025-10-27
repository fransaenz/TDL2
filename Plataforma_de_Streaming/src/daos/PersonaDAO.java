package daos;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import perfiles.Persona;
import perfiles.Usuario;

public class PersonaDAO {

	public void insertar(Persona persona) {
		Connection conexion = ConexionBD.getInstancia().conectarBD();
		String SQL = "INSERT INTO DATOS_PERSONALES (NOMBRE, APELLIDO, DNI, NRO_TARJETA) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, persona.getNombre());
	        p_stmt.setString(2, persona.getApellido());
	        p_stmt.setInt(3, persona.getDni());
	        p_stmt.setInt(4, persona.getNroTarjeta());
			p_stmt.executeUpdate();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
		} 
	}

}
