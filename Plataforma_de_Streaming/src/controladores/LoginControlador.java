package controladores;

import java.util.List;

import javax.swing.JOptionPane;

import daos.interfaces.UsuarioDAO;
import daos.jdbc.UsuarioDAOjdbc;
import gui.*;
import modelo.perfiles.Usuario;

public class LoginControlador {

    private VistaLogin vista;

    public LoginControlador(VistaLogin vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
    	vista.getBtnLogin().addActionListener(e -> manejarLogin());
    	vista.getBtnRegistro().addActionListener(e -> abrirRegistro());
    }

    private void manejarLogin() {
        String email = vista.getCampoEmail();
        String password = vista.getCampoPassword();

        if (email.isEmpty() || password.isEmpty()) {
            vista.mostrarError("Debe completar todos los campos.");
            return;
        }

        Usuario encontrado = null;
        UsuarioDAO u = new UsuarioDAOjdbc();
        List<Usuario> usuarios  = u.listarUsuarios();
        for(int i = 0; i<usuarios.size(); i++) {
        	if (usuarios.get(i).getEmail().equals(email)) {
        		encontrado = usuarios.get(i);
        		break;
        	}
        }

        if (encontrado == null) {
            vista.mostrarError("El usuario no existe.");
            return;
        }

        if (!encontrado.getContrasena().equals(password)) {
            vista.mostrarError("Contraseña incorrecta.");
            return;
        }

        vista.mostrarMensaje("Inicio de sesión exitoso.");
        new VistaPrincipal(encontrado).mostrar();
        vista.cerrar();
    }

    private void abrirRegistro() {
        VistaRegistro registro = new VistaRegistro();
        new RegistroControlador(registro);
        registro.setVisible(true);
        vista.cerrar();
    }
    
 
}