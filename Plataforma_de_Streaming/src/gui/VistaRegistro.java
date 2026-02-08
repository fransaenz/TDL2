package gui;

//import controladores.MainControlador;
//import excepciones.*;
import javax.swing.*;
import javax.swing.border.*;

//import controladores.RegistroControlador;
//import daos.interfaces.PersonaDAO;
//import daos.jdbc.PersonaDAOjdbc;

import java.awt.*;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame {

    private JTextField txtEmail = new JTextField();
    private JTextField txtUsuario = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private JLabel lblNombre = new JLabel("Nombre:");
    private JTextField txtNombre = new JTextField();
    private JLabel lblApellido = new JLabel("Apellido:");
    private JTextField txtApellido = new JTextField();
    private JLabel lblDni = new JLabel("DNI:");
    private JTextField txtDni = new JTextField();
    private JTextField txtNroTarjeta = new JTextField();
    private JLabel lblPassword = new JLabel("Contraseña:");
    private JLabel lblEmail = new JLabel("Email:");
    private JButton btnRegistro = new JButton("Registrarse");
    private JButton btnVolver = new JButton("← Volver");
    private JLabel lblUsuario = new JLabel("Nombre de Usuario:");
    private JLabel lblTarjeta = new JLabel("Nro tarjeta:");
    
    // Tipografía
    private Font fuenteTituloSeccion = new Font("Calibri", Font.BOLD, 16);
    private Font fuenteCuerpoTexto = new Font("Calibri", Font.PLAIN, 14);

    // Colores
    private Color colorDelFondo = new Color(235, 245, 255);
    private Color colorDelBoton = new Color(0, 33, 71);
    private Color colorEtiquetaBoton = new Color(255, 200, 0); //Color.WHITE;

   
    public VistaRegistro() {

        // configuracion de la ventana
        setTitle("Registro en FLUXER");
        setSize(950, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(colorDelFondo);
        setContentPane(contentPane);

        // ================= BARRA SUPERIOR =================
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        pnlSuperior.setBackground(colorDelFondo);
        pnlSuperior.setBorder(new EmptyBorder(20, 30, 10, 30));

        // ---- BOTÓN VOLVER (izquierda) ----
        btnVolver.setFont(fuenteTituloSeccion);
        btnVolver.setBackground(colorDelBoton);
        btnVolver.setForeground(colorEtiquetaBoton);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnVolver.setPreferredSize(new Dimension(100, 40));
        btnVolver.setMargin(new Insets(8, 18, 8, 18));
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnVolver.setEnabled(true);
        
        pnlSuperior.add(btnVolver, BorderLayout.WEST);

        // ---- LOGO (espacio reservado) ----
        JPanel pnlLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlLogo.setBackground(colorDelFondo);

        ImageIcon logoApp = new ImageIcon("src/resources/LogoAppHorizontal.png"); //Fluxer
        Image logoEscalado = logoApp.getImage().getScaledInstance(280, 70, Image.SCALE_SMOOTH);
        JLabel lblLogo = new JLabel(new ImageIcon(logoEscalado));

        pnlLogo.add(lblLogo);
        pnlSuperior.add(pnlLogo, BorderLayout.CENTER);

        contentPane.add(pnlSuperior, BorderLayout.NORTH);

        //Formulario
        JPanel pnlContenido = new JPanel(new BorderLayout());
        pnlContenido.setBackground(colorDelFondo);
        
        JPanel pnlImagen = new JPanel(new GridBagLayout());
        pnlImagen.setBackground(colorDelFondo);
    	pnlImagen.setPreferredSize(new Dimension(400, 0));
        
    	ImageIcon imagen = new ImageIcon("src/resources/Logo Popcorn Caricatura.png"); // fran lagarto
    	Image imagenEscalada = imagen.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Escala la imagen
    	
    	JLabel lblImagen = new JLabel(new ImageIcon(imagenEscalada));
    	lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        JPanel pnlCentro = new JPanel(new GridBagLayout());
        pnlCentro.setBackground(colorDelFondo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;


        // Inicialización de campos
        txtEmail = new JTextField();
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDni = new JTextField();
        txtNroTarjeta = new JTextField();

        Dimension sizeCampo = new Dimension(300, 34);

        //EMAIL
        gbc.gridy = 0;

        // Label
        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblEmail, gbc);

        // Campo
        gbc.gridx = 1;
        gbc.weightx = 0;
        pnlCentro.add(configurarCampo(txtEmail, sizeCampo), gbc);

        //USUARIO
        gbc.gridy = 1;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtUsuario, sizeCampo), gbc);

        //PASSWORD
        gbc.gridy = 2;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtPassword, sizeCampo), gbc);

        //NOMBRE
        gbc.gridy = 3;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblNombre, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtNombre, sizeCampo), gbc);

        //APELLIDO
        gbc.gridy = 4;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblApellido, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtApellido, sizeCampo), gbc);

        //DNI
        gbc.gridy = 5;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblDni, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtDni, sizeCampo), gbc);

        //TARJETA
        gbc.gridy = 6;

        gbc.gridx = 0;
        gbc.weightx = 0;
        pnlCentro.add(lblTarjeta, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        pnlCentro.add(configurarCampo(txtNroTarjeta, sizeCampo), gbc);

        //BOTON REGISTRO
        btnRegistro.setFont(fuenteTituloSeccion);
        btnRegistro.setBackground(colorDelBoton);
        btnRegistro.setForeground(colorEtiquetaBoton);
        btnRegistro.setFocusPainted(false);
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistro.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnRegistro.setPreferredSize(new Dimension(180, 42));

        btnRegistro.setEnabled(true);
        
        // Ubicación en la grilla
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;              // ocupa las dos columnas
        gbc.weightx = 0;
        gbc.insets = new Insets(25, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        pnlCentro.add(btnRegistro, gbc);

        pnlContenido.add(pnlCentro, BorderLayout.WEST);
        pnlContenido.add(pnlImagen, BorderLayout.EAST);

        contentPane.add(pnlContenido, BorderLayout.CENTER);
    }

 
    
 // Cerrar la ventana actual
    public void salir() {
        dispose();
    }
    
    public String getCampoEmail() {
        return txtEmail.getText().trim();
    }

    public void setCampoEmail(String email) {
        txtEmail.setText(email);
    }

    // --- PASSWORD ---
    public String getCampoPassword() {
        return new String(txtPassword.getPassword()).trim();
    }

    public void setCampoPassword(String password) {
        txtPassword.setText(password);
    }

    // --- NOMBRE ---
    public String getCampoNombre() {
        return txtNombre.getText().trim();
    }

    public void setCampoNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    // --- APELLIDO ---
    public String getCampoApellido() {
        return txtApellido.getText().trim();
    }

    public void setCampoApellido(String apellido) {
        txtApellido.setText(apellido);
    }

    // --- DNI ---
    public String getCampoDni() {
        return txtDni.getText().trim();
    }

    public void setCampoDni(String dni) {
        txtDni.setText(dni);
    }

    // --- NRO TARJETA (Si lo usas) ---
    public String getCampoNroTarjeta() {
        return txtNroTarjeta.getText().trim();
    }

    public void setCampoNroTarjeta(String nroTarjeta) {
        txtNroTarjeta.setText(nroTarjeta);
    }

 // --- NOMBRE DE USUARIO ---
    public String getCampoUsuario() {
        return txtUsuario.getText().trim();
    }

    public void setCampoUsuario(String usuario) {
        txtUsuario.setText(usuario);
    }
    
    
	// Mostrar mensajes
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mostrar errores
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void addRegistroListener(ActionListener l) {
        btnRegistro.addActionListener(l);
    }

    public void addVolverListener(ActionListener l) {
        btnVolver.addActionListener(l);
    }

    private JComponent configurarCampo(JComponent campo, Dimension size) {
        campo.setFont(fuenteCuerpoTexto);
        campo.setPreferredSize(size);
        return campo;
    }
   
/*
    public static void main(String[] args) {

        // Asegura que Swing se ejecute en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                VistaRegistro vista = new VistaRegistro();

                vista.setVisible(true);
            }
        });
    }
    
*/
    
}