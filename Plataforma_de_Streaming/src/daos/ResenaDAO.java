package daos;

import java.sql.*;
import java.util.List;

import perfiles.Usuario;
import audiovisuales.Pelicula;

import java.util.LinkedList;
import resenas.Resena;
import java.time.LocalDateTime;


public class ResenaDAO {

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
				LocalDateTime fechaHora = LocalDateTime.parse(resul.getString("FECHA_HORA"));
				
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
			                fechaHora
			                );
					listaResenas.addLast(resena);
				}
		} catch (SQLException e) {
			System.err.println("❌ Error al listar resenas: " + e.getMessage());
		}
		
		return listaResenas;
	}

	public void aprobar (Resena resena) {
		String SQL = "UPDATE RESENA SET APROBADO = true WHERE";
		try (Statement stmt = conexion.createStatement();
		 ResultSet resul = stmt.executeQuery(SQL))
		{
			//falta especificar que resena actualizar
		}
		} catch (SQLException e) {
			System.err.println("❌ Error al verificar si existe el dni: " + e.getMessage());
		}
	}

}
