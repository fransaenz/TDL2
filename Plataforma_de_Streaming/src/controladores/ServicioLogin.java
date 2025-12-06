package controladores;

import daos.interfaces.UsuarioDAO;
import daos.jdbc.UsuarioDAOjdbc;
import modelo.perfiles.Usuario;

import java.util.List;

public class ServicioLogin {

    private static final UsuarioDAO usuarioDAO = new UsuarioDAOjdbc();

    public static Usuario login(String email, String password) {
        if (email == null || password == null) return null;
        
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        for (Usuario u : usuarios) {
            if (email.equals(u.getEmail()) && password.equals(u.getContrasena())) {
                return u;
            }
        }
        return null;
    }
}
