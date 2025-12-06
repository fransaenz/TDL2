package modelo.perfiles.comparadores;

import java.util.Comparator;

import modelo.perfiles.Usuario;

public class ComparadorPorEmail implements Comparator<Usuario> {
    @Override
    public int compare(Usuario u1, Usuario u2) {
        return u1.getEmail().compareToIgnoreCase(u2.getEmail());
    }

}
