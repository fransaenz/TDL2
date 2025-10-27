package perfiles;

import java.util.Comparator;

public class ComparadorPorEmail implements Comparator<Usuario> {
    @Override
    public int compare(Usuario u1, Usuario u2) {
        return u1.getEmail().compareToIgnoreCase(u2.getEmail());
    }

}
