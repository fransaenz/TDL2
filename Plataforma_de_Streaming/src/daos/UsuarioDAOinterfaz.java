package daos;

import java.util.List;

import perfiles.Usuario;

public interface UsuarioDAOinterfaz {
	
	void insertar(Usuario usuario); //use
	List<Usuario> listarUsuarios(); //use
	
}
