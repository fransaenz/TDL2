package controladores;

import java.util.List;

import daos.interfaces.UsuarioDAO;
import daos.jdbc.UsuarioDAOjdbc;
import gui.*;
import modelo.perfiles.Usuario;

public class LoginControlador {

    private VistaLogin vista;
    private UsuarioService usuarioService;

    public LoginControlador(VistaLogin vista) {
        this.vista = vista;
        this.usuarioService = new UsuarioService();
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnLogin().setOnAction(e -> manejarLogin());
        vista.getBtnRegistro().setOnAction(e -> abrirRegistro());
    }

    private void manejarLogin() {
        String email = vista.getCampoEmail().getText();
        String password = vista.getCampoPassword().getText();

        if (email.isEmpty() || password.isEmpty()) {
            vista.mostrarError("Debe completar todos los campos.");
            return;
        }

        Usuario encontrado = null;
        UsuarioDAO u = new UsuarioDAOjdbc();
        List<Usuario> usuarios  = u.listarUsuarios();
        for(int i = 0; i<usuarios.size(); i++) {
        	if (usuarios.get(i).getEmail() == email) {
        		encontrado = usuarios.get(i);
        		break;
        	}
        }

        if (encontrado == null) {
            vista.mostrarError("El usuario no existe.");
            return;
        }

        if (!encontrado.getPassword().equals(password)) {
            vista.mostrarError("Contraseña incorrecta.");
            return;
        }

        vista.mostrarMensaje("Inicio de sesión exitoso.");
        new VistaBienvenida(encontrado).mostrar();
        vista.cerrar();
    }

    private void abrirRegistro() {
        VistaRegistro registro = new VistaRegistro();
        registro.mostrar();
        vista.cerrar();
    }
}