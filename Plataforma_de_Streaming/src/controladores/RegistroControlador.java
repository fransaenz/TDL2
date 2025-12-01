package controladores;

import gui.VistaRegistro;

import java.util.List;

import daos.interfaces.UsuarioDAO;
import daos.jdbc.*;
import modelo.perfiles.Usuario;

public class RegistroControlador {

    // 1) Referencias a la vista y al modelo (DAO)
    private VistaRegistro vista;
    private UsuarioDAO u;

    // 2) Constructor: recibe la vista que controla
    public RegistroControlador(VistaRegistro vista) {
        this.vista = vista;
        this.u = new UsuarioDAOjdbc();   // acceso a BD

        // 3) Le indicamos a la vista quién es su controlador
        this.vista.setControlador(this);
    }

    // 4) Método llamado cuando el usuario aprieta "Registrarse"
    public void registrarUsuario() {

        // 5) Tomamos los datos que el usuario escribió en los JTextField
        String nombreUsuario = vista.getNombreUsuario();
        String email = vista.getEmail();
        String password = vista.getPassword();
        String password2 = vista.getPasswordRepetida();

        // 6) Validaciones (muy básicas)
        if (nombreUsuario.isBlank() || email.isBlank() || password.isBlank() || password2.isBlank()) {
            vista.mostrarMensaje("Todos los campos deben estar completos.");
            return;
        }

        if (!password.equals(password2)) {
            vista.mostrarMensaje("Las contraseñas no coinciden.");
            return;
        }

        boolean exito = false;
        List<Usuario> usuarios  = u.listarUsuarios();
        for(int i = 0; i<usuarios.size(); i++) {
        	if (usuarios.get(i).getEmail() == email) {
        		exito = true;
        		break;
        	}
        }
        
        if (exito) { 
            vista.mostrarMensaje("El e-mail ya está registrado.");
            return;
        }

        // 7) Creamos el usuario (el modelo)
        Usuario nuevo = new Usuario(nombreUsuario, email, password);

        // 8) Lo guardamos en la BD mediante el DAO
        u.insertar(nuevo);

        // 9) Mostramos un mensaje de éxito en la vista
        vista.mostrarMensaje("Usuario registrado correctamente.");

        // 10) Cerramos la ventana de registro y volvemos a la VistaLogin
        vista.cerrar();
    }
}