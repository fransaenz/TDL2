package controladores;

import java.io.IOException;

import javax.swing.SwingUtilities;
import daos.conexion.ConexionBD;
import gui.VistaLogin;

public class MainControlador {
    public static void main(String[] args) {
        
    	ConexionBD.getInstancia().inicializarBD();
    	
    	//cargo peliculas del CSV
    	
        try {
			ImportadorPeliculas.importarCSV("src/recursos/movies_database.csv");
		} catch (IOException e) { 
			System.out.println("Error de lectura del CSV");
		}
    	
    	SwingUtilities.invokeLater(() -> {
            VistaLogin vista = new VistaLogin();   // Crear la vista
            new LoginControlador(vista);           // Pasar la vista al controlador
            vista.setVisible(true);                // Mostrar la vista
        });
    }
}