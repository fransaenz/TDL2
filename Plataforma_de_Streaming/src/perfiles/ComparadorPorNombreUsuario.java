package perfiles;

import java.util.Comparator;

public class ComparadorPorNombreUsuario implements Comparator<Usuario> {
	@Override
    public int compare(Usuario u1, Usuario u2) {
        return u1.getNombreUsuario().compareToIgnoreCase(u2.getNombreUsuario());
    }
}
