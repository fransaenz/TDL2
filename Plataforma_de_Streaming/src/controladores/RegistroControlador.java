package controladores;

import gui.VistaRegistro;

import java.util.List;
import java.util.stream.Collectors;

import daos.interfaces.PersonaDAO;
import daos.interfaces.UsuarioDAO;
import daos.jdbc.*;
import excepciones.DNIDuplicadoException;
import excepciones.EmailInvalidoException;
import excepciones.UsuarioYaExisteException;
import modelo.perfiles.*;

public class RegistroControlador {

	
	
    // 1) Referencias a la vista y al modelo (DAO)
    private VistaRegistro vista;
    private UsuarioDAO udao; //Este dato y los metodos en los que lo uso tienen que ir a modelo
    private PersonaDAO pdao;
    
    
    // 2) Constructor: recibe la vista que controla
    public RegistroControlador(VistaRegistro vista) {
        this.vista = vista;
        this.udao = new UsuarioDAOjdbc();   // acceso a BD
        this.pdao = new PersonaDAOjdbc();
        // 3) Le indicamos a la vista quién es su controlador
        this.vista.setControlador(this);
    }

    
    
// 4) Método llamado cuando el usuario aprieta "Registrarse"
public boolean registrarUsuario(String email, String nombreUsuario, String contrasena,
            					String nombre, String apellido, int dni, int nroTarjeta) throws EmailInvalidoException, UsuarioYaExisteException, DNIDuplicadoException {

    	
	if (email == null || nombreUsuario == null || contrasena == null) {
        throw new IllegalArgumentException("Argumentos nulos.");
    }
    if (!esEmailBasico(email)) {
        throw new EmailInvalidoException("Email con formato inválido.");
    }

   
    List<Usuario> usuarios = udao.listarUsuarios();

    for (Usuario u : usuarios) {
        if (email.equals(u.getEmail())) {
            throw new UsuarioYaExisteException("El email ya está registrado.");
        }
        if (nombreUsuario.equals(u.getNombreUsuario())) {
            throw new UsuarioYaExisteException("El nombre de usuario ya existe.");
        }
    }

    if (pdao.existeDNI(dni)) {
        throw new DNIDuplicadoException("El DNI ya está registrado.");
    }

    
    Usuario usu = new Usuario(email, contrasena, nombreUsuario);
    
    int idUsuario = udao.insertar(usu);
    
    Persona per = new Persona(nombre, apellido, dni, nroTarjeta, usu);
    
    int idPersona = pdao.insertar(per);

    boolean ok = pdao.actualizarUsuario(usu, per);
    if (!ok) {
        throw new RuntimeException("Error al actualizar relación usuario-persona");
    }
    return true;

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