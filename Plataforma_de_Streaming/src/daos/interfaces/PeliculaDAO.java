package daos.interfaces;

import java.util.List;

import modelo.audiovisuales.Pelicula;

public interface PeliculaDAO {

	void insertar(Pelicula pelicula); //use
	List<Pelicula> listarPeliculas(); //use
	Pelicula buscarPorId(int id); //use
}
