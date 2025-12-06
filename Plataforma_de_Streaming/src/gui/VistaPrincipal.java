package gui;
import javax.swing.*;
import modelo.perfiles.Usuario;

public class VistaPrincipal extends JFrame {

    public VistaPrincipal(Usuario u) {
        setTitle("Plataforma - Bienvenido " + u.getNombreUsuario());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JLabel lbl = new JLabel("Sesión iniciada correctamente", SwingConstants.CENTER);
	    add(lbl);

	    setVisible(true);
	}

	public void mostrar() {
	        setVisible(true);
	}
}
