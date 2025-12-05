package gui;

import controladores.MainControlador;
import excepciones.*;
import javax.swing.*;

import controladores.RegistroControlador;
import daos.interfaces.PersonaDAO;
import daos.jdbc.PersonaDAOjdbc;

import java.awt.*;

public class VistaRegistro extends JFrame {

    private JTextField txtEmail;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private RegistroControlador controlador;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JTextField txtNroTarjeta;
    PersonaDAO p = new PersonaDAOjdbc();

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

        JPanel panelCentro = new JPanel(new GridLayout(10, 1, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        txtEmail = new JTextField();
        txtUsuario = new JTextField();
        txtPassword = new JPasswordField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtDni = new JTextField();
        txtNroTarjeta = new JTextField();

        panelCentro.add(new JLabel("E-mail:"));
        panelCentro.add(txtEmail);

        panelCentro.add(new JLabel("Nombre de Usuario:"));
        panelCentro.add(txtUsuario);

        panelCentro.add(new JLabel("Contraseña:"));
        panelCentro.add(txtPassword);
        
        panelCentro.add(new JLabel("Nombre:"));
        panelCentro.add(txtNombre);

        panelCentro.add(new JLabel("Apellido:"));
        panelCentro.add(txtApellido);

        panelCentro.add(new JLabel("DNI:"));
        panelCentro.add(txtDni);

        panelCentro.add(new JLabel("Nro de Tarjeta:"));
        panelCentro.add(txtNroTarjeta);

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
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String dniStr = txtDni.getText().trim();
            String nroTarjetaStr = txtNroTarjeta.getText().trim();

            // VALIDACIÓN DE VACÍOS
            if (email.isEmpty() || usuario.isEmpty() || pass.isEmpty() ||
                nombre.isEmpty() || apellido.isEmpty() ||
                dniStr.isEmpty() || nroTarjetaStr.isEmpty()) {

                throw new CamposVaciosException("Todos los campos son obligatorios.");
            }

            // CONVERTIR A INT
            int dni = Integer.parseInt(dniStr);
            int nroTarjeta = Integer.parseInt(nroTarjetaStr);

            // VALIDAR FORMATO EMAIL
            if (controlador.validarEmail(email)) {
                throw new EmailInvalidoException("El email ingresado no es válido.");
            }

            // PEDIMOS AL CONTROLADOR QUE REGISTRE
            boolean exito = controlador.registrarUsuario(email, usuario, pass,
                    nombre, apellido, dni, nroTarjeta);
            if (p.existeDNI(dni)) {
                throw new DNIDuplicadoException("El DNI ya está registrado.");
            }
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

    public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public void setControlador(RegistroControlador controlador) {
		this.controlador = controlador;
	}

	private void volverALogin() {
        new VistaLogin();
        dispose();
    }
    
 // Cerrar la ventana actual
    public void salir() {
        dispose();
    }
    
    public void cerrar() {
        this.dispose();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword()).trim();
    }
    
    public String getNombreUsuario() {
        return new String(txtUsuario.getText()).trim();
    }
    
    
 // Mostrar mensajes informativos
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Mostrar errores
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}