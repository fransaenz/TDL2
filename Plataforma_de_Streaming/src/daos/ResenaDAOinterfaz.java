package daos;

import java.util.List;

import resenas.Resena;

public interface ResenaDAOinterfaz {

	void insertar(Resena resena); //use
	List<Resena> listarResenas(); //use
	boolean aprobar (Resena resena); //use
}
