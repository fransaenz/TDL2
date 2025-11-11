package daos;

import java.sql.*;

public class ConexionBD {
	
	//constructor privado
	private ConexionBD() {
		try {
	        conexion = DriverManager.getConnection(URL);
	        System.out.println("✅ Conexión a BD establecida correctamente.");
	    } catch (java.sql.SQLException e) {
	    System.out.println("❌ Error al conectar con la BD: " + e.getMessage());
	    } 
	}
	
	
	private static Connection conexion = null;
	private static final  ConexionBD INSTANCE = new ConexionBD();
	private static final String URL = "jdbc:sqlite:Plataforma_de_Streaming.DB";
	
	 
	//metodos
	
	public static ConexionBD getInstancia(){
		return INSTANCE;
	}
	
	public Connection conectarBD() {
		return conexion;
    }
	
	public void desconectarBD() {
		if(INSTANCE != null)
			try {
				if (conexion != null && !conexion.isClosed()) {
					conexion.close();
					System.out.println("🔒 Conexión cerrada correctamente.");
				}
			} catch (SQLException e) {
				System.err.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
		}
	}
	
	
	private static void creacionDeTablasEnBD(Connection connection) throws SQLException 
	{ 
	  Statement stmt = connection.createStatement(); 
	  
      	String sql = "CREATE TABLE IF NOT EXISTS PELICULA (" +
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
              "TITULO TEXT(100) NOT NULL, " +
              "DIRECTOR TEXT(100) NOT NULL, " +
              "GENERO TEXT(100) NOT NULL, " +
              "DURACION INTEGER NOT NULL, " +
              "ELENCO TEXT(500) NOT NULL, " +
              "RESUMEN TEXT(500) NOT NULL" +
              ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS USUARIO (" +
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
              "NOMBRE_USUARIO TEXT NOT NULL, " +
              "EMAIL TEXT NOT NULL, " +
              "CONTRASENA TEXT NOT NULL" +
              ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS DATOS_PERSONALES (" +
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
              "NOMBRE TEXT(100) NOT NULL, " +
              "APELLIDO TEXT(100) NOT NULL, " +
              "DNI INTEGER NOT NULL, " +
              "NRO_TARJETA INTEGER NOT NULL, " +
              "ID_USUARIO INTEGER, " +
              "CONSTRAINT PERSONA_USUARIO_FK FOREIGN KEY (ID_USUARIO) " +
              "REFERENCES USUARIO(ID)" +
              ");";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE IF NOT EXISTS RESENA (" +
              "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
              "CALIFICACION INTEGER NOT NULL, " +
              "COMENTARIO TEXT(500), " +
              "APROBADO INTEGER DEFAULT 1 NOT NULL, " +
              "FECHA_HORA TEXT(100) NOT NULL, " +
              "ID_USUARIO INTEGER NOT NULL, " +
              "ID_PELICULA INTEGER NOT NULL, " +
              "CONSTRAINT RESENA_USUARIO_FK FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID), " +
              "CONSTRAINT RESENA_PELICULA_FK FOREIGN KEY (ID_PELICULA) REFERENCES PELICULA(ID)" +
              ");";
	  stmt.executeUpdate(sql); 
	  stmt.close(); 		 
	} 
	
	public void inicializarBD() {
		   try {
	            creacionDeTablasEnBD(conexion);
	        } catch (SQLException e) {
	            System.err.println("❌ Error al crear tablas: " + e.getMessage());
	        }
	}
	
}