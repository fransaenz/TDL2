package daos.interfaces;

import java.util.List;

import modelo.perfiles.Usuario;

public interface UsuarioDAO {
	
	void insertar(Usuario usuario); //use
	List<Usuario> listarUsuarios(); //use
	Usuario buscarPorId(int id); //use
}
