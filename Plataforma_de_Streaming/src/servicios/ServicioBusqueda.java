package servicios;

import java.util.List;
import modelo.audiovisuales.Pelicula;

public class ServicioBusqueda {
    
    private List<Pelicula> todasLasPelis;

    // Al crear el servicio, le pasamos la lista de la base de datos o DAO
    public ServicioBusqueda(List<Pelicula> todasLasPelis) {
        this.todasLasPelis = todasLasPelis;
    }

    public Pelicula buscarPeliculaPorTitulo(String tituloBuscado) {
        // Validación de seguridad
        if (tituloBuscado == null || tituloBuscado.isEmpty()) {
            return null;
        }

        String busquedaCorta = tituloBuscado.toLowerCase().trim();

        for (Pelicula peli : todasLasPelis) {
            String tituloPeli = peli.getTitulo().toLowerCase();

            // Lógica de comparación
            if (tituloPeli.equals(busquedaCorta) || tituloPeli.startsWith(busquedaCorta)) {
                return peli;
            }
        }
        return null;
    }
}