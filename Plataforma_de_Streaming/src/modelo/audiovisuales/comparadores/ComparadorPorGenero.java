package modelo.audiovisuales.comparadores;

import java.util.Comparator;

import modelo.audiovisuales.Pelicula;

public class ComparadorPorGenero implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return p1.getGenero().compareTo(p2.getGenero());
    }
}