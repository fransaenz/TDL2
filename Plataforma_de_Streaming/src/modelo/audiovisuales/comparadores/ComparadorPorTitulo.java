package modelo.audiovisuales.comparadores;

import java.util.Comparator;

import modelo.audiovisuales.Pelicula;

public class ComparadorPorTitulo implements Comparator<Pelicula> {
    @Override
    public int compare(Pelicula p1, Pelicula p2) {
        return p1.getTitulo().compareTo(p2.getTitulo());
    }
}