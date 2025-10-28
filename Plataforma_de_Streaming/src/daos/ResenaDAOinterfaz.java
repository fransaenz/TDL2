package daos;

import java.util.List;

import resenas.Resena;

public interface ResenaDAOinterfaz {

	void insertar(Resena resena); //use
	List<Resena> listarResenas(); //use
	void aprobar (Resena resena); //use
}
