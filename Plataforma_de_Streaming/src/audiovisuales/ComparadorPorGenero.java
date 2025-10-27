package audiovisuales;

import java.util.Comparator;

public class ComparadorPorGenero implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return p1.getGenero().compareTo(p2.getGenero());
    }
}