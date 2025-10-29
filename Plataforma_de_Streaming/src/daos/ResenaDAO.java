package daos;

import java.sql.*;
import java.util.List;
import java.util.LinkedList;

import perfiles.Usuario;
import audiovisuales.Pelicula;
import resenas.Resena;
import java.time.LocalDateTime;


public class ResenaDAO implements ResenaDAOinterfaz{

	private final Connection conexion;
	
	
	public ResenaDAO() {
		conexion = ConexionBD.getInstancia().conectarBD();
	}
	
	
	public void insertar(Resena resena) {
		
		String SQL = "INSERT INTO RESENA (CALIFICACION, COMENTARIO, APROBADO, FECHA_HORA, ID_USUARIO, ID_PELICULA) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement p_stmt = conexion.prepareStatement(SQL);
			p_stmt.setInt(1, resena.getEstrellas());
	        p_stmt.setString(2, resena.getTexto());
	        p_stmt.setBoolean(3, resena.estaAprobada());
	        p_stmt.setString(4, resena.getFechaHora().toString());
	        p_stmt.setInt(5, resena.getAutor().getId());
	        p_stmt.setInt(6, resena.getContenidoResenado().getId());
			p_stmt.executeUpdate();
			p_stmt.close();
		} catch (SQLException e) {
            System.err.println("❌ Error al insertar resena: " + e.getMessage());
		} 
	}
	
	public List<Resena> listarResenas(){
		
		List<Resena> listaResenas = new LinkedList<Resena>();
		String SQL= "SELECT * FROM RESENA";
		
		try (Statement stmt = conexion.createStatement();
				 ResultSet resul = stmt.executeQuery(SQL))
			{
				Resena resena;
				UsuarioDAO usrDAO = new UsuarioDAO();
				PeliculaDAO peliDAO = new PeliculaDAO();
				
				while(resul.next()) {
					
					int idUsuario = resul.getInt("ID_USUARIO");
					int idPelicula = resul.getInt("ID_PELICULA");
					
					Usuario autor = usrDAO.buscarPorId(idUsuario);
					Pelicula contResenado = peliDAO.buscarPorId(idPelicula);
					
					resena = new Resena(
							contResenado,
							autor,
							resul.getInt("CALIFICACION"),
			                resul.getString("COMENTARIO"),
			                LocalDateTime.parse(resul.getString("FECHA_HORA"))
							);
					resena.setId(resul.getInt("ID"));
					listaResenas.addLast(resena);
					
					if (resul.getBoolean("APROBADO")) resena.aprobar();
					else						 	  resena.desaprobar();
				}
		} catch (SQLException e) {
			System.err.println("❌ Error al listar resenas: " + e.getMessage());
		}
		
		return listaResenas;
	}

	public boolean aprobar (Resena resena) {
		String SQL = "UPDATE RESENA SET APROBADO = 1 WHERE ID = ?";
		try (PreparedStatement p_stmt = conexion.prepareStatement(SQL))
		{
			p_stmt.setInt(1, resena.getId());
			int filas = p_stmt.executeUpdate();
			return filas >0;
		
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
			return false;
		}
	}

}
