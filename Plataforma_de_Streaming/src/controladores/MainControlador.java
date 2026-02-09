package controladores;

import javax.swing.SwingUtilities;
import gui.VistaLogin;

public class MainControlador {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaLogin vista = new VistaLogin();   // Crear la vista
            new LoginControlador(vista);           // Pasar la vista al controlador
            vista.setVisible(true);                // Mostrar la vista
        });
    }
}