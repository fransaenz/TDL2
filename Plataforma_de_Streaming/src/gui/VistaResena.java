package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaResena extends JFrame {

    private JLabel lblTitulo = new JLabel("", SwingConstants.CENTER);
    private JButton[] estrellas = new JButton[5];
    private JTextArea txtComentario = new JTextArea();
    private JButton btnGuardar = new JButton("Guardar");
    private int calificacionSeleccionada = 0;

    // Colores y Tipografía (Consistentes)
    private Color colorDelFondo = new Color(235, 245, 255);
    private Color colorDelBoton = new Color(0, 33, 71);
    private Color colorEtiquetaBoton = new Color(255, 200, 0);
    private Font fuenteTituloSeccion = new Font("Calibri", Font.BOLD, 18);
    private Font fuenteCuerpoTexto = new Font("Calibri", Font.PLAIN, 14);

    public VistaResena() {
        setTitle("Nueva Reseña");
        setSize(475, 400); // Un poco más alta para el área de comentario
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBackground(colorDelFondo);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
/*
        // --- PARTE SUPERIOR: Título y Estrellas ---
        JPanel pnlSuperior = new JPanel(new GridLayout(2, 1, 5, 5));
        pnlSuperior.setOpaque(false);

        lblTitulo.setFont(fuenteTituloSeccion);
        
        // Panel de Calificación
        JPanel pnlEstrellas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlEstrellas.setOpaque(false);
        pnlEstrellas.add(new JLabel("Calificación: "));
        
        inicializarEstrellas(pnlEstrellas);

        pnlSuperior.add(lblTitulo);
        pnlSuperior.add(pnlEstrellas);
        contentPane.add(pnlSuperior, BorderLayout.NORTH);

*/
     // --- PARTE SUPERIOR: Título y Estrellas ---
     // Usamos un Box vertical para apilar los componentes a la izquierda
     Box pnlSuperior = Box.createVerticalBox();
     pnlSuperior.setOpaque(false);

     // Título a la izquierda
     lblTitulo.setFont(fuenteTituloSeccion);
     lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
     lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);

     // Panel de Calificación (FlowLayout.LEFT para que empiece a la izquierda)
     JPanel pnlEstrellas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
     pnlEstrellas.setOpaque(false);
     pnlEstrellas.setAlignmentX(Component.LEFT_ALIGNMENT);

     JLabel lblCalif = new JLabel("Calificación: ");
     lblCalif.setFont(fuenteCuerpoTexto);
     pnlEstrellas.add(lblCalif);

     inicializarEstrellas(pnlEstrellas);

     pnlSuperior.add(lblTitulo);
     pnlSuperior.add(Box.createVerticalStrut(10)); // Espacio entre título y estrellas
     pnlSuperior.add(pnlEstrellas);

     contentPane.add(pnlSuperior, BorderLayout.NORTH);
     ////////////////////////

        // --- CENTRO: Comentario ---
        JPanel pnlCentro = new JPanel(new BorderLayout(5, 5));
        pnlCentro.setOpaque(false);
        JLabel lblComentario = new JLabel("Comentario:");
        lblComentario.setFont(fuenteCuerpoTexto);
        
        txtComentario.setLineWrap(true);
        txtComentario.setWrapStyleWord(true);
        txtComentario.setFont(fuenteCuerpoTexto);
        JScrollPane scroll = new JScrollPane(txtComentario);
        
        pnlCentro.add(lblComentario, BorderLayout.NORTH);
        pnlCentro.add(scroll, BorderLayout.CENTER);
        contentPane.add(pnlCentro, BorderLayout.CENTER);

        // --- SUR: Botón Guardar ---
        JPanel pnlSur = new JPanel();
        pnlSur.setOpaque(false);
        btnGuardar.setBackground(colorDelBoton);
        btnGuardar.setForeground(colorEtiquetaBoton);
        btnGuardar.setPreferredSize(new Dimension(120, 35));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlSur.add(btnGuardar);
        contentPane.add(pnlSur, BorderLayout.SOUTH);
    }

    private void inicializarEstrellas(JPanel pnl) {
        for (int i = 0; i < 5; i++) {
            final int indice = i + 1;
            estrellas[i] = new JButton("★");
            
            // 1. Estrellas más grandes (de 24 pasamos a 32 o más)
            estrellas[i].setFont(new Font("Serif", Font.PLAIN, 32));
            
            estrellas[i].setContentAreaFilled(false);
            estrellas[i].setBorderPainted(false);
            estrellas[i].setFocusPainted(false);
            estrellas[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // 2. Quitamos el espacio entre ellas (Insects en 0)
            estrellas[i].setMargin(new Insets(0, 0, 0, 0));
            estrellas[i].setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));

            estrellas[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarEstrellas(indice);
                }
            });
            pnl.add(estrellas[i]);
        }
    }

    private void actualizarEstrellas(int rating) {
        calificacionSeleccionada = rating;
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                estrellas[i].setForeground(colorEtiquetaBoton); // Amarillo
            } else {
                estrellas[i].setForeground(Color.GRAY);
            }
        }
    }

    // Métodos para el Controlador
    public void setTitulo(String titulo) { lblTitulo.setText(titulo.toUpperCase()); } 
    public int getCalificacion() { return calificacionSeleccionada; }
    public String getComentario() { return txtComentario.getText().trim(); }
    public void addGuardarListener(ActionListener l) { btnGuardar.addActionListener(l); }
    public void salir() { this.dispose(); }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    public static void main(String[] args) {

        // Asegura que Swing se ejecute en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                VistaResena vista = new VistaResena();

                vista.setVisible(true);
            }
        });
    }
}