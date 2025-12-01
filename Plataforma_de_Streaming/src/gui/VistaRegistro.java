package gui;

import controladores.MainController;
import exceptions.CamposVaciosException;
import exceptions.EmailInvalidoException;
import exceptions.UsuarioYaExisteException;

import javax.swing.*;

import controladores.RegistroControlador;

import java.awt.*;

public class VistaRegistro extends JFrame {

    private JTextField txtEmail;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    public VistaRegistro() {

        setTitle("Registrarse");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Crear Cuenta Nueva");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(6, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        txtEmail = new JTextField();
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();

        panelCentro.add(new JLabel("E-mail:"));
        panelCentro.add(txtEmail);

        panelCentro.add(new JLabel("Nombre de Usuario:"));
        panelCentro.add(txtUsuario);

        panelCentro.add(new JLabel("Contraseña:"));
        panelCentro.add(txtPassword);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnVolver = new JButton("Volver");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> intentarRegistro());
        btnVolver.addActionListener(e -> volverALogin());

        setVisible(true);
    }

    private void intentarRegistro() {
        try {

            String email = txtEmail.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();

            // VALIDAR CAMPOS VACÍOS
            if (email.isEmpty() || usuario.isEmpty() || pass.isEmpty()) {
                throw new CamposVaciosException("Todos los campos son obligatorios.");
            }

            // VALIDAR FORMATO EMAIL
            if (!email.contains("@") || !email.contains(".")) {
                throw new EmailInvalidoException("El email ingresado no es válido.");
            }

            // PEDIMOS AL CONTROLADOR QUE REGISTRE
            boolean exito = MainController.getInstance().registrarUsuario(email, usuario, pass);

            if (!exito) {
                throw new UsuarioYaExisteException("El usuario o email ya está registrado.");
            }

            // REGISTRO EXITOSO
            JOptionPane.showMessageDialog(this,
                    "Registro completado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            new VistaLogin();
            dispose();

        } catch (CamposVaciosException | EmailInvalidoException | UsuarioYaExisteException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error en Registro",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error inesperado: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverALogin() {
        new VistaLogin();
        dispose();
    }
}