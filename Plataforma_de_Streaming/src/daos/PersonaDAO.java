package daos;

import java.util.List;

import modelo.perfiles.Persona;
import modelo.perfiles.Usuario;

public interface PersonaDAO {

	void insertar(Persona persona); //use

    List<Persona> listarPersonas(); //use

    boolean actualizarUsuario(Usuario usuario, Persona persona); //use

    boolean existeDNI(int dni); //use
	
}
