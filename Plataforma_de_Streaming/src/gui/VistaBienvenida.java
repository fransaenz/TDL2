package gui;

import javax.swing.*;
import javax.swing.border.*;

import modelo.audiovisuales.Pelicula;
import modelo.audiovisuales.util.Genero;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaBienvenida extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel pnlPrincipal = new JPanel(cardLayout);

    // Componentes de la Vista de Contenido
    private JLabel lblUsuario = new JLabel("Usuario");
    private JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    private JTextField txtBuscar = new JTextField();
    private JButton btnBuscar = new JButton("Buscar");
    private JComboBox<Genero> comboGeneros = new JComboBox<>(Genero.values());
    private List<JButton> botonesReseniar = new ArrayList<>();
    private List<Pelicula> top10 = new ArrayList<>();

 
	// Tipografía
    private Font fuenteTituloSeccion = new Font("Calibri", Font.BOLD, 16);

    // Colores
    private Color colorDelFondo = new Color(235, 245, 255);
    private Color colorDelBoton = new Color(0, 33, 71);
    private Color colorEtiquetaBoton = new Color(255, 200, 0); //Color.WHITE;

    public VistaBienvenida() {
        setTitle("Bienvenido a FLUXER");
        setSize(950, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 1. Crear Pantalla de Carga
        pnlPrincipal.add(crearPanelCarga(), "CARGA");

        // 2. Crear Pantalla de Contenido
        pnlPrincipal.add(crearPanelContenido(), "CONTENIDO");

        add(pnlPrincipal);
        // Por defecto mostramos carga
        cardLayout.show(pnlPrincipal, "CARGA");
        //cardLayout.show(pnlPrincipal, "CONTENIDO");
    }

    private JPanel crearPanelCarga() {
        JPanel pnlCarga = new JPanel(new GridBagLayout());
        pnlCarga.setBackground(colorDelFondo);
        
        JLabel lblCargando = new JLabel("Cargando...", SwingConstants.CENTER);
        lblCargando.setFont(fuenteTituloSeccion);
        
        // Asumiendo que tienes el gif en recursos
        JLabel lblGif = new JLabel(new ImageIcon("src/resources/tuerca.gif"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0;
        pnlCarga.add(lblGif, gbc);
        gbc.gridy = 1;
        pnlCarga.add(lblCargando, gbc);
        
        return pnlCarga;
    }

    private JPanel crearPanelContenido() {
        JPanel pnlContenido = new JPanel(new BorderLayout());
        pnlContenido.setBackground(colorDelFondo);

        // --- HEADER (Parte de arriba) ---
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setPreferredSize(new Dimension(0, 150));
        pnlHeader.setBackground(colorDelFondo);

        // Imagen 3/4
        JLabel lblBanner = new JLabel(new ImageIcon(new ImageIcon("src/resources/banner.png")
                .getImage().getScaledInstance(800, 120, Image.SCALE_SMOOTH)));
        pnlHeader.add(lblBanner, BorderLayout.WEST);

        // Panel Usuario y Buscador (1/4)
        aplicarEstiloBoton(btnCerrarSesion, 130, 40);
        aplicarEstiloBoton(btnBuscar, 100, 40);
        JPanel pnlDerecha = new JPanel(new GridLayout(3, 1, 5, 5));
        pnlDerecha.setBackground(colorDelFondo);
        pnlDerecha.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel pnlFila1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlFila1.add(lblUsuario);
        pnlFila1.add(btnCerrarSesion);
        pnlFila1.setBackground(colorDelFondo);
        
        JPanel pnlFila2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtBuscar.setPreferredSize(new Dimension(150, 25));
        pnlFila2.add(txtBuscar);
        pnlFila2.add(btnBuscar);
        pnlFila2.setBackground(colorDelFondo);

        pnlDerecha.add(pnlFila1);
        pnlDerecha.add(pnlFila2);
        pnlHeader.add(pnlDerecha, BorderLayout.EAST);

        // --- CUERPO (Tabla de películas) ---
        // Usamos un JScrollPane porque 10 filas no entrarán cómodamente
        JPanel pnlGrid = new JPanel(new GridLayout(11, 1)); // 1 fila nombres + 10 pelis
        
        // Fila de encabezados
        pnlGrid.add(crearFilaEncabezado());

        // Simulamos 10 filas (esto después se llenará con datos reales)
        for(int i = 0; i < 10; i++) {
            JPanel fila = crearFilaPelicula(top10.get(i));
            pnlGrid.add(fila);
        }

        JScrollPane scroll = new JScrollPane(pnlGrid);
        pnlContenido.add(pnlHeader, BorderLayout.NORTH);
        pnlContenido.add(scroll, BorderLayout.CENTER);

        return pnlContenido;
    }

    private JPanel crearFilaEncabezado() {
        JPanel pnl = new JPanel(new GridLayout(1, 5));
        pnl.setBackground(colorDelBoton);
        String[] nombres = {"Poster", "Título", "Género", "Resumen", "     "};
        for(String n : nombres) {
            JLabel lbl = new JLabel(n, SwingConstants.CENTER);
            lbl.setForeground(colorEtiquetaBoton);
            lbl.setFont(fuenteTituloSeccion);
            if(n.equals("Género")) {
                JPanel p = new JPanel(new FlowLayout());
                p.setOpaque(false);
                p.add(lbl); p.add(comboGeneros);
                pnl.add(p);
            } else {
                pnl.add(lbl);
            }
        }
        return pnl;
    }

    private JPanel crearFilaPelicula(Pelicula peli) {
        JPanel pnl = new JPanel(new GridLayout(1, 5));
        pnl.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

        pnl.add(new JLabel(new ImageIcon(peli.getPoster()))); // Poster
        pnl.add(new JLabel(peli.getTitulo(), SwingConstants.CENTER)); // Titulo
        pnl.add(new JLabel(peli.getGenero().toString(), SwingConstants.CENTER)); // Genero
        pnl.add(new JLabel(peli.getResumen(), SwingConstants.CENTER)); // Resumen
        pnl.setBackground(colorDelFondo);
        
        JButton btnReseniar = new JButton("Reseñar");
        botonesReseniar.add(btnReseniar);
        pnl.add(btnReseniar);

        return pnl;
    }

    // Métodos para el Controlador
    public void cambiarAVistaContenido() {
        cardLayout.show(pnlPrincipal, "CONTENIDO");
    }
    
    public void cambiarAVistaCarga() {
        cardLayout.show(pnlPrincipal, "CARGA");
    }

    public void addCerrarSesionListener(ActionListener l) { btnCerrarSesion.addActionListener(l); }
    public void addBuscarListener(ActionListener l) { btnBuscar.addActionListener(l); }
    public List<JButton> getBotonesReseniar() { return botonesReseniar; }
    
    public Genero getGeneroSeleccionado() {
        return (Genero) comboGeneros.getSelectedItem();
    }

    public void addFiltroGeneroListener(ActionListener l) {
        comboGeneros.addActionListener(l);
    }
    
    public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setNombreUsuario(String nombre) {
		this.lblUsuario.setText(nombre);
	}

	public String getTxtBuscar() {
		return txtBuscar.getText().trim();
	}


	public List<Pelicula> getTop10() {
		return top10;
	}

	public void setTop10(List<Pelicula> top10) {
		this.top10 = top10;
	}

	// Mostrar mensajes
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mostrar errores
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
	
	public void salir() { dispose(); }

    private void aplicarEstiloBoton(JButton boton, int ancho, int alto) {
        boton.setFont(fuenteTituloSeccion);
        boton.setBackground(colorDelBoton);
        boton.setForeground(colorEtiquetaBoton);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        boton.setPreferredSize(new Dimension(ancho, alto));
        boton.setMargin(new Insets(8, 18, 8, 18));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setEnabled(true);
    }


public static void main(String[] args) {

    // Asegura que Swing se ejecute en el Event Dispatch Thread
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {

            VistaBienvenida vista = new VistaBienvenida();

            vista.setVisible(true);
        }
    });
}
}