package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaBusqueda extends JFrame {

    private JLabel lblTitulo = new JLabel("", SwingConstants.CENTER);
    private JLabel lblAnio = new JLabel("", SwingConstants.CENTER);
    private JTextArea txtResumen = new JTextArea();
    private JButton btnContinuar = new JButton("Continuar");
    private String titulo;

    // Estilo (Consistente con tus otras vistas)
    private Color colorDelFondo = new Color(235, 245, 255);
    private Color colorDelBoton = new Color(0, 33, 71);
    private Color colorEtiquetaBoton = new Color(255, 200, 0);

    public VistaBusqueda() {
        
    	setTitle("FLUXER - " + titulo);
        setSize(475, 300); 
        setLocationRelativeTo(null);
        setResizable(false);
        // Usamos DISPOSE para que no se cierre toda la app al cerrar esta ventanita
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBackground(colorFondo);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // --- PARTE SUPERIOR: Título y Año ---
        JPanel pnlNorte = new JPanel(new GridLayout(2, 1));
        pnlNorte.setOpaque(false);

        lblTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        lblAnio.setFont(new Font("Calibri", Font.ITALIC, 14));

        pnlNorte.add(lblTitulo);
        pnlNorte.add(lblAnio);
        contentPane.add(pnlNorte, BorderLayout.NORTH);

        // --- CENTRO: Resumen ---
        txtResumen.setLineWrap(true);
        txtResumen.setWrapStyleWord(true);
        txtResumen.setEditable(false);
        txtResumen.setBackground(colorDelFondo);
        txtResumen.setFont(new Font("Calibri", Font.PLAIN, 14));
        
        // Un scroll por si el resumen es muy largo
        JScrollPane scroll = new JScrollPane(txtResumen);
        scroll.setBorder(null); 
        contentPane.add(scroll, BorderLayout.CENTER);

        // --- SUR: Botón ---
        JPanel pnlSur = new JPanel();
        pnlSur.setOpaque(false);
        
        btnContinuar.setBackground(colorDelBoton);
        btnContinuar.setForeground(colorEtiquetaBoton);
        btnContinuar.setFocusPainted(false);
        btnContinuar.setPreferredSize(new Dimension(120, 35));
        btnContinuar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        pnlSur.add(btnContinuar);
        contentPane.add(pnlSur, BorderLayout.SOUTH);
    }

    // Métodos para que el controlador cargue los datos
    public void cargarDatos(String titulo, int anio, String resumen) {
        lblTitulo.setText(titulo.toUpperCase());
        lblAnio.setText("Año: " + anio);
        txtResumen.setText(resumen);
    }

    public void addContinuarListener(ActionListener l) {
        btnContinuar.addActionListener(l);
    }

    public void salir() {
        this.dispose();
    }
    
    
    public static void main(String[] args) {

        // Asegura que Swing se ejecute en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                VistaBusqueda vista = new VistaBusqueda();

                vista.setVisible(true);
            }
        });
    }
}