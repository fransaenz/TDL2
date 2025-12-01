package controladores;

import javax.swing.SwingUtilities;
import controladores.LoginControlador;
import gui.*;

public class MainControlador {
    public static void main(String[] args) {
        // Iniciar la UI en el EDT
        SwingUtilities.invokeLater(() -> {
            new LoginControlador();
        });
    }
}
