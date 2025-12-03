package gui;

import controladores.MainControlador;
import excepciones.*;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblMensaje;
    private JButton btnLogin;
    private JButton btnRegistro;

    public VistaLogin() {

        // CONFIGURACIÓN DE LA VENTANA
        setTitle("Inicio - Login");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // TÍTULO SUPERIOR
        JLabel lblTitulo = new JLabel("Iniciar Sesión");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(4, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        lblMensaje = new JLabel("", SwingConstants.CENTER);

        panelCentro.add(new JLabel("E-mail:"));
        panelCentro.add(txtEmail);
        panelCentro.add(new JLabel("Contraseña:"));
        panelCentro.add(txtPassword);

        add(panelCentro, BorderLayout.CENTER);

        // PANEL DE BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnLogin = new JButton("Ingresar");
        btnRegistro = new JButton("Registrarse");

        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);

        add(panelBotones, BorderLayout.SOUTH);
        add(lblMensaje, BorderLayout.SOUTH);

        // ACCIONES DE BOTONES
        btnLogin.addActionListener(e -> intentarLogin());

        btnRegistro.addActionListener(e -> {
            new VistaRegistro();
            dispose();
        });

        setVisible(true);
    }


    // MÉTODO QUE VALIDA E INTENTA EL LOGIN
    private void intentarLogin() {

        try {
            String email = txtEmail.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();

            // 1) VALIDAMOS CAMPOS VACÍOS
            if (email.isEmpty() || pass.isEmpty()) {
                throw new CamposVaciosException("Debe completar ambos campos.");
            }

            // 2) VALIDAMOS FORMATO DEL EMAIL
            if (!email.contains("@") || !email.contains(".")) {
                throw new EmailInvalidoException("Formato de email incorrecto.");
            }

            // 3) LLAMAMOS AL CONTROLADOR
            var usuario = MainControlador.getInstance().login(email, pass);

            // 4) SI VOLVIÓ NULO → USUARIO NO EXISTE
            if (usuario == null) {
                throw new UsuarioNoEncontradoException("Usuario o contraseña incorrectos.");
            }

            // 5) LOGIN EXITOSO
            lblMensaje.setText("Acceso concedido.");
            new VistaPrincipal();
            dispose();

        } catch (CamposVaciosException | EmailInvalidoException | UsuarioNoEncontradoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                    "Error en Login", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error inesperado: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Mostrar mensajes informativos
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mostrar errores
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Limpiar los campos de texto
    public void limpiarCampos() {
        txtEmail.setText("");
        txtPassword.setText("");
    }

    // Cerrar la ventana actual
    public void salir() {
        dispose();
    }
    
    public String getCampoEmail() {
        return txtEmail.getText().trim();
    }

    public String getCampoPassword() {
        return new String(txtPassword.getPassword()).trim();
    }
    
    public void cerrar() {
        this.dispose();
    }
    
    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }
}