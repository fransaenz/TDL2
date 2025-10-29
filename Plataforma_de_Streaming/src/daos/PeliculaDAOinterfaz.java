package daos;

import java.util.List;

import audiovisuales.Pelicula;

public interface PeliculaDAOinterfaz {

	void insertar(Pelicula pelicula); //use
	List<Pelicula> listarPeliculas(); //use
	Pelicula buscarPorId(int id); //use
}
