package daos;

import java.sql.*;
import java.util.List;
import java.util.LinkedList;

import perfiles.Persona;
import perfiles.Usuario;

public class PersonaDAO {
	
	
	private final Connection conexion;
	
	
	public PersonaDAO() {
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
	        p_stmt.setInt(5, persona.getPerfil().getId());
			p_stmt.executeUpdate();
			p_stmt.close();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar usuario: " + e.getMessage());
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
				listaPersonas.addLast(persona);
			}
		
		} catch (SQLException e) {
			System.err.println("❌ Error al listar personas: " + e.getMessage());
		}
		
		return listaPersonas;
	}
	
	public void actualizarUsuario(Usuario usuario, Persona persona) {
		String SQL = "UPDATE DATOS_PERSONALES SET ID_USUARIO = usuario.getID() WHERE .... =";
		try (Statement stmt = conexion.createStatement();
		 ResultSet resul = stmt.executeQuery(SQL))
		{
			
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
		}
	}
	
	public boolean existeDNI(int dni) {
		
		String dniStr = /*hay que convertir dni a String creo*/;
		String SQL = "SELECT DNI FROM DATOS_PERSONALES DNI= dni";
		try (Statement stmt = conexion.createStatement();
		 ResultSet resul = stmt.executeQuery(SQL))
		{
			boolean existe = false;
			if(tal cosa) existe = true;
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
		}
	
}
