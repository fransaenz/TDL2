package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class VistaLogin extends JFrame {

    // Componentes Gráficos
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private JButton btnLogin = new JButton("Login");
    private JButton btnRegistro = new JButton("Registrarse");
    private JLabel lblPassword = new JLabel("Contraseña: ");
    private JLabel lblEmail = new JLabel("Email: ");

    // Tipografía
    private Font fuenteTituloSeccion = new Font("Calibri", Font.BOLD, 16);
    private Font fuenteCuerpoTexto = new Font("Calibri", Font.PLAIN, 14);

    // Colores
    private Color colorDelFondo = new Color(235, 245, 255);
    private Color colorDelBoton = new Color(0, 33, 71);
    private Color colorEtiquetaBoton = new Color(255, 200, 0); //Color.WHITE;

    public VistaLogin() {
    	

    	// configuracion de la ventana
    	setTitle("Bienvenido a FLUXER");
        setSize(950, 520); //Tamaño inicial
        setLocationRelativeTo(null);	//Centra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    	
    	setLayout(new BorderLayout()); // Divide en 5 zonas
    	setBackground(colorDelFondo);	// Pone el color del fondo
    	
    	
    	//Formulario del Login
    	JPanel pnlCentro = new JPanel(new GridBagLayout()); 
    	pnlCentro.setBackground(colorDelFondo);
    	pnlCentro.setBorder(new EmptyBorder(20, 40, 20, 40));
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.insets = new Insets(10, 10, 10, 10); //espacio externo entre componentes
    	gbc.anchor = GridBagConstraints.WEST; //alineación izquierda
    	gbc.fill = GridBagConstraints.HORIZONTAL; //los componentes se estiran horizontalmente
    	
    	lblEmail.setFont(fuenteTituloSeccion);
    	lblPassword.setFont(fuenteTituloSeccion);
    	// Tamaño fijo para los campos
    	txtEmail.setFont(fuenteCuerpoTexto);
    	txtEmail.setPreferredSize(new Dimension(200, 30));
    	txtPassword.setFont(fuenteCuerpoTexto);
    	txtPassword.setPreferredSize(new Dimension(200, 30));
    	
    	//Nombre de la app
    	ImageIcon iconoHeader = new ImageIcon("src/resources/header.png");
    	Image imgHeader = iconoHeader.getImage().getScaledInstance(300, 80, Image.SCALE_SMOOTH);
    	JLabel lblImagenHeader = new JLabel(new ImageIcon(imgHeader));
    	lblImagenHeader.setHorizontalAlignment(SwingConstants.CENTER);
    	//Ubico la imagen en la grilla
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = 2;
    	gbc.insets = new Insets(0, 0, 20, 0);

    	pnlCentro.add(lblImagenHeader, gbc);
    	
    	//
    	JLabel lblTitulo = new JLabel("Login"); // etiqueta de texto
    	lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Fuente grande y en negrita
    	lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); //Centra el texto
    	// Posición del título en la grilla
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.gridwidth = 2;
    	//Margenes
    	gbc.insets = new Insets(0, 0, 30, 0);
    	//agrego el título con todas estas reglas
    	pnlCentro.add(lblTitulo, gbc);
    	
    	gbc.gridwidth = 1;
    	gbc.insets = new Insets(5, 5, 5, 5);
    	
    	//Etiqueta Email
    	gbc.gridx = 0; //Columna izquierda
    	gbc.gridy = 2;
    	gbc.weightx = 0;
    	//Agrego la etiqueta del Email
    	pnlCentro.add(lblEmail, gbc);
    	
    	// Campo Email
    	gbc.gridx = 1; //Columna derecha
    	gbc.weightx = 1.0;
    	//Agrego el campo del Email
    	pnlCentro.add(txtEmail, gbc);
    	
    	//Etiqueta passwd
    	gbc.gridx = 0; //Columna izquierda
    	gbc.gridy = 3;
    	gbc.weightx = 0;
    	//Agrego la etiqueta del Passwd
    	pnlCentro.add(lblPassword, gbc);
    	
    	
    	//Campo Passwd
    	gbc.gridx = 1;
    	gbc.weightx = 1.0;
    	//Agrego el campo del Passwd
    	pnlCentro.add(txtPassword, gbc);
    	
    	//------------------------------------
    	
    	
    	//Panel de botones
    	JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0)); //15 px de separación horizontal
    	pnlBotones.setBackground(colorDelFondo);
    	pnlBotones.setBorder(new EmptyBorder(20, 0, 0, 0)); //Margen superior para separar del formulario
    	
    	//Estilo de los botones
    	btnLogin.setFocusPainted(false); //Elimino el borde feo
    	btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25)); //Agrando el botón sin cambiar la fuente
    	btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR)); // el cursor cambia al pasar por arriba
        btnLogin.setFont(fuenteTituloSeccion);
        btnLogin.setBackground(colorDelBoton);
        btnLogin.setForeground(colorEtiquetaBoton);
    	
    	
    	btnRegistro.setPreferredSize(new Dimension(120, 40)); // 
        btnRegistro.setFocusPainted(false);
        btnRegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistro.setFont(fuenteTituloSeccion);
        btnRegistro.setBackground(colorDelBoton);
        btnRegistro.setForeground(colorEtiquetaBoton);
        btnRegistro.setBorder(BorderFactory.createLineBorder(colorDelBoton));
        
        JPanel pnlLogin = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlLogin.setBackground(colorDelFondo);
        pnlLogin.add(btnLogin);

        
    	//Botones en la grilla
    	gbc.gridx = 0;
    	gbc.gridy = 4;
    	gbc.gridwidth = 2;
    	gbc.insets = new Insets(20, 0, 10, 0);
    	gbc.anchor = GridBagConstraints.CENTER; // queda centrado
    	
    	pnlCentro.add(pnlLogin, gbc);
    	
    	JLabel lblRegistroTexto = new JLabel("¿Todavía no estás registrado?");
    	lblRegistroTexto.setFont(fuenteCuerpoTexto);
    	lblRegistroTexto.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	gbc.gridx = 0;
    	gbc.gridy = 5;
    	gbc.gridwidth = 2;
    	gbc.insets = new Insets(20, 0, 5, 0);

    	pnlCentro.add(lblRegistroTexto, gbc);
    	
    	gbc.gridy = 6;
    	gbc.fill = GridBagConstraints.NONE; // No se estira

    	pnlCentro.add(btnRegistro, gbc);
    	
    	
    	// Agrego al layout principal
    	add(pnlCentro, BorderLayout.CENTER);
    	
    	//------------------------------------
    	
    	
    	// Panel de la imagen, lado derecho
    	JPanel pnlImagen = new JPanel(new BorderLayout());
    	pnlImagen.setBackground(colorDelFondo);
    	pnlImagen.setPreferredSize(new Dimension(400, 0));
    
    	ImageIcon imagen = new ImageIcon("src/resources/Logo Popcorn Caricatura.png"); // Carga la imagen
    	Image imagenEscalada = imagen.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Escala la imagen
    	
    	JLabel lblImagen = new JLabel(new ImageIcon(imagenEscalada));
    	lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
    	// Agrego al layout principal
    	pnlImagen.add(lblImagen, BorderLayout.CENTER); // Se centra dentro del panel
    	add(pnlImagen, BorderLayout.EAST); //Se pone el panel en el lado izquierdo
    	
    	
    }
    
    
    public void addLoginListener(ActionListener l) {
        btnLogin.addActionListener(l);
    }

    public void addRegistroListener(ActionListener l) {
        btnRegistro.addActionListener(l);
    }

    public String getCampoEmail() {
        return txtEmail.getText().trim();
    }

    public void setCampoEmail(String email) {
        txtEmail.setText(email);
    }

    public String getCampoPassword() {
        return new String(txtPassword.getPassword()).trim();
    }

    public void setCampoPassword(String password) {
        txtPassword.setText(password);
    }

    public void limpiarCampos() {
        txtEmail.setText("");
        txtPassword.setText("");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    // cierre de la ventana
    public void salir() {
        dispose();
    }
    
    
}


/* 
    public static void main(String[] args) {

        // Asegura que Swing se ejecute en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                VistaLogin vista = new VistaLogin();

                vista.setVisible(true);
            }
        });
    }
}
       
*/