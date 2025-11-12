package daos.jdbc;

import java.sql.*;
import java.util.List;

import daos.conexion.ConexionBD;
import daos.interfaces.PersonaDAO;
import modelo.perfiles.Persona;
import modelo.perfiles.Usuario;

import java.util.LinkedList;

public class PersonaDAOjdbc implements PersonaDAO{
	
	
	private final Connection conexion;
	
	
	public PersonaDAOjdbc() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	

	public void insertar(Persona persona) {
		
		String SQL = "INSERT INTO DATOS_PERSONALES (NOMBRE, APELLIDO, DNI, NRO_TARJETA, ID_USUARIO) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setString(1, persona.getNombre());
	        p_stmt.setString(2, persona.getApellido());
	        p_stmt.setInt(3, persona.getDni());
	        p_stmt.setInt(4, persona.getNroTarjeta());
	        // si la persona aún no tiene usuario asociado → insertar NULL
	        if (persona.getPerfil() == null) {
	            p_stmt.setNull(5, java.sql.Types.INTEGER);
	        } else {
	            p_stmt.setInt(5, persona.getPerfil().getId());
	        }
			p_stmt.executeUpdate();
			p_stmt.close();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar datos personales: " + e.getMessage());
		}
		
	}

	public List<Persona> listarPersonas(){
		
		List<Persona> listaPersonas = new LinkedList<Persona>();
		String SQL= "SELECT * FROM DATOS_PERSONALES";
		
		try (Statement stmt = conexion.createStatement();
			 ResultSet resul = stmt.executeQuery(SQL))
		{
			Persona persona;
			while(resul.next()) {
				
				persona= new Persona(
						resul.getString("NOMBRE"),
						resul.getString("APELLIDO"),
						resul.getInt("DNI"),
						resul.getInt("NRO_TARJETA")
						);
				persona.setId(resul.getInt("ID"));
				listaPersonas.addLast(persona);
			}
		
		} catch (SQLException e) {
			System.err.println("❌ Error al listar personas: " + e.getMessage());
		}
		
		return listaPersonas;
	}
	
	public boolean actualizarUsuario(Usuario usuario, Persona persona) {
		String SQL = "UPDATE DATOS_PERSONALES SET ID_USUARIO = ? WHERE ID = ?";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL))
		{	
			p_stmt.setInt(1, usuario.getId());
			p_stmt.setInt(2, persona.getId());
			int filas = p_stmt.executeUpdate();
			return filas >0;
			
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
			return false;
		}
	}
	
	public boolean existeDNI(int dni) {
		String SQL = "SELECT * FROM DATOS_PERSONALES WHERE DNI = ?";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL))
		{
			p_stmt.setInt(1, dni);
			try (ResultSet resul = p_stmt.executeQuery())
			{
					return resul.next();
			}
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
			return false;
		}
	}
}