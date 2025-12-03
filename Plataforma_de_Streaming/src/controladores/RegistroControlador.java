package controladores;

import gui.VistaRegistro;

import java.util.List;

import daos.interfaces.PersonaDAO;
import daos.interfaces.UsuarioDAO;
import daos.jdbc.*;
import modelo.perfiles.*;

public class RegistroControlador {

	
	
    // 1) Referencias a la vista y al modelo (DAO)
    private VistaRegistro vista;
    private UsuarioDAO u; //Este dato y los metodos en los que lo uso tienen que ir a modelo

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

        // 6) Validaciones (muy básicas)
        if (nombreUsuario.isBlank() || email.isBlank() || password.isBlank()) {
            vista.mostrarMensaje("Todos los campos deben estar completos.");
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
    
    // Tengo que sumar el metodo registrarUsuario(email, usuario, pass); Copiar el mismo que del main anterior
    
    	public boolean registrarUsuario (String email, String nombreUsuario, String contrasena, String nombre, String apellido, int dni, int nroTarjeta) {
    	Usuario usu = new Usuario(email, contrasena, nombreUsuario);
    	PersonaDAO p = new PersonaDAOjdbc();
		u.insertar(usu);
		Persona per = new Persona (nombre,apellido, dni,nroTarjeta, usu);
		p.insertar(per);
		
		return p.actualizarUsuario(usu, per);
    	}
    
    public boolean validarEmail (String email) {
    	boolean var = false;
		if (esEmailBasico(email)) {
			var = true;	            
		}
		return var;
    }
    
    public static boolean esEmailBasico(String email) {
        int arroba = email.indexOf('@');
        int ultimaArroba = email.lastIndexOf('@');
        
        if (arroba == -1 || arroba != ultimaArroba) {
            return false;
        }

        String antes = email.substring(0, arroba);
        String despues = email.substring(arroba + 1);

        return !antes.isEmpty() && !despues.isEmpty();
    }
}