package daos.interfaces;

import java.util.List;

import modelo.resenas.Resena;

public interface ResenaDAO {

	void insertar(Resena resena); //use
	List<Resena> listarResenas(); //use
	boolean aprobar (Resena resena); //use
}
