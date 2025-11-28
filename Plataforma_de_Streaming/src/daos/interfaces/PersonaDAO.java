package daos.interfaces;

import java.util.List;

import modelo.perfiles.Persona;
import modelo.perfiles.Usuario;

public interface PersonaDAO {

	void insertar(Persona persona); //use

    List<Persona> listarPersonasSinUsuario(); //use

    boolean actualizarUsuario(Usuario usuario, Persona persona); //use

    boolean existeDNI(int dni); //use
	
}
