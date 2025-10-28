package daos;

import java.util.List;

import perfiles.Persona;
import perfiles.Usuario;

public interface PersonaDAOinterfaz {

	void insertar(Persona persona); //use

    List<Persona> listarPersonas(); //use

    void actualizarUsuario(Usuario usuario, Persona persona); //use

    boolean existeDNI(int dni); //use
	
}
