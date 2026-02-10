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
    private JPanel pnlGridPeliculas;
    
    // Componentes de la Vista de Contenido
    private JLabel lblUsuario = new JLabel("Usuario");
    private JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    private JTextField txtBuscar = new JTextField();
    private JButton btnBuscar = new JButton("Buscar");
    private JComboBox<Object> comboGeneros = new JComboBox<>();
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

        //Creo Pantalla de Carga
        pnlPrincipal.add(crearPanelCarga(), "CARGA");

        //Creo Pantalla de Contenido
        pnlPrincipal.add(crearPanelContenido(), "CONTENIDO");

        add(pnlPrincipal);
        // Por defecto mostramos carga
        cardLayout.show(pnlPrincipal, "CARGA");

        comboGeneros.addItem("GENERAL"); // La opción String
        for (Genero g : Genero.values()) {
            comboGeneros.addItem(g);     // Las opciones del Enum
        }
        
    }

    private JPanel crearPanelCarga() {
        JPanel pnlCarga = new JPanel(new GridBagLayout());
        pnlCarga.setBackground(Color.WHITE);
        
        JLabel lblCargando = new JLabel("Cargando...", SwingConstants.CENTER);
        lblCargando.setFont(new Font("Calibri", Font.BOLD, 24));
   
        JLabel lblGif = new JLabel(new ImageIcon("src/recursos/Loading.gif"));

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

        //Parte de arriba
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setPreferredSize(new Dimension(0, 150));
        pnlHeader.setBackground(colorDelFondo);

        // Imagen 3/4
        JLabel lblBanner = new JLabel(new ImageIcon(new ImageIcon("src/recursos/NombreApp.jpeg")
                .getImage().getScaledInstance(400, 80, Image.SCALE_SMOOTH)));
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

        //Tabla de películas
        pnlGridPeliculas = new JPanel(new GridBagLayout());
        pnlGridPeliculas.setBackground(colorDelFondo);
        
        // Solo agregamos el encabezado inicialmente
        pnlGridPeliculas.add(crearFilaEncabezado());


        JScrollPane scroll = new JScrollPane(pnlGridPeliculas);
        scroll.getVerticalScrollBar().setUnitIncrement(16); 
     	scroll.setBorder(null);
        pnlContenido.add(pnlHeader, BorderLayout.NORTH);
        pnlContenido.add(scroll, BorderLayout.CENTER);

        return pnlContenido;
    }

    private JPanel crearFilaEncabezado() {
    	JPanel pnl = new JPanel(new GridBagLayout());
    	pnl.setBackground(colorDelBoton);
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.BOTH;
    	gbc.weighty = 1.0;

    	String[] nombres = {"Poster", "Título", "Género", "Resumen", "     "};
    	double[] pesos = {0.1, 0.2, 0.15, 0.45, 0.1}; 

    	for(int i = 0; i < nombres.length; i++) {
    	    gbc.weightx = pesos[i];
    	    JLabel lbl = new JLabel(nombres[i], SwingConstants.CENTER);
    	    lbl.setForeground(colorEtiquetaBoton);
    	    lbl.setFont(fuenteTituloSeccion);
    	    
    	    if(nombres[i].equals("Género")) {
    	        JPanel p = new JPanel(new FlowLayout());
    	        p.setOpaque(false);
    	        p.add(lbl); p.add(comboGeneros);
    	        pnl.add(p, gbc);
    	    } else {
    	        pnl.add(lbl, gbc);
    	    }
    	}
        return pnl;
    }

    
    private JPanel crearFilaPelicula(Pelicula peli) {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        pnl.setBackground(colorDelFondo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre celdas
        double[] pesos = {0.1, 0.2, 0.15, 0.45, 0.1}; 

        //Poster
        gbc.weightx = pesos[0];
        JLabel lblPoster = new JLabel("", SwingConstants.CENTER);
        lblPoster.setPreferredSize(new Dimension(70, 100));

        lblPoster.setText("<html><center style='color: gray;'>Imagen No<br>Disponible</center></html>");
        lblPoster.setFont(new Font("Arial", Font.BOLD, 10));
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        descargarPoster(lblPoster, peli.getPoster());

        pnl.add(lblPoster, gbc);
        
        //Título
        gbc.weightx = pesos[1];
        pnl.add(new JLabel("<html><body style='width: 120px;'><center>"+peli.getTitulo()+"</center></body></html>", SwingConstants.CENTER), gbc);
        
        //Género
        gbc.weightx = pesos[2];
        pnl.add(new JLabel(peli.getGenero().toString(), SwingConstants.CENTER), gbc);
        
        //Resumen
        gbc.weightx = pesos[3];
        JLabel lblResumen = new JLabel("<html><body style='width: 320px; text-align: justify; padding: 5px;'>" 
                                     + peli.getResumen() + "</body></html>", SwingConstants.CENTER);
        pnl.add(lblResumen, gbc);
        
        //Botón Reseñar
        gbc.weightx = pesos[4];
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER; 
        
        JButton btnReseniar = new JButton("Reseñar");
        aplicarEstiloBoton(btnReseniar, 100, 30); 
        btnReseniar.putClientProperty("peliculaAsociada", peli);
        
        botonesReseniar.add(btnReseniar); 
        pnl.add(btnReseniar, gbc);

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
    
    public Object getGeneroSeleccionado() {
        return comboGeneros.getSelectedItem();
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
	    Dimension dim = new Dimension(ancho, alto);
	    boton.setFont(fuenteTituloSeccion);
	    boton.setBackground(colorDelBoton);
	    boton.setForeground(colorEtiquetaBoton);
	    boton.setBorder(BorderFactory.createLineBorder(colorEtiquetaBoton, 1));

	    boton.setPreferredSize(dim);
	    boton.setMinimumSize(dim);
	    boton.setMaximumSize(dim);
	    
	    boton.setFocusPainted(false);
	    boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

    public void actualizarTablaPeliculas(List<Pelicula> peliculas) {
        this.top10 = peliculas;
        botonesReseniar.clear();
        pnlGridPeliculas.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Encabezado
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pnlGridPeliculas.add(crearFilaEncabezado(), gbc);
        // Filas
        for (Pelicula peli : peliculas) {
            gbc.gridy++;
            pnlGridPeliculas.add(crearFilaPelicula(peli), gbc);
        }

        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        JPanel pnlEspaciador = new JPanel();
        pnlEspaciador.setOpaque(false);
        pnlGridPeliculas.add(pnlEspaciador, gbc);

        pnlGridPeliculas.revalidate();
        pnlGridPeliculas.repaint();
    }

private void descargarPoster(JLabel lblPoster, String urlPoster) {

    if (urlPoster == null || urlPoster.isEmpty() || urlPoster.equalsIgnoreCase("N/A")) {
        return; // queda el texto "Imagen no disponible"
    }

    Thread hilo = new Thread(() -> {
        try {
            java.net.URL url = new java.net.URI(urlPoster.trim()).toURL();
            java.net.HttpURLConnection conn =
                    (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            java.io.InputStream is = conn.getInputStream();
            java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(is);

            if (img != null) {
                Image escalada = img.getScaledInstance(60, 90, Image.SCALE_SMOOTH);
                ImageIcon icono = new ImageIcon(escalada);

                SwingUtilities.invokeLater(() -> {
                    lblPoster.setText("");
                    lblPoster.setBorder(null);
                    lblPoster.setIcon(icono);
                });
            }

        } catch (Exception e) {
            System.out.println("No se pudo cargar poster: " + urlPoster);
        }
    });

    hilo.setDaemon(true);
    hilo.start();
}


}