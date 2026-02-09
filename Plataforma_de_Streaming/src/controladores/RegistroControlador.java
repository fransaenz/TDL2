package controladores;

import gui.VistaLogin;
import gui.VistaRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import javax.swing.JOptionPane;

import daos.interfaces.PersonaDAO;
import daos.interfaces.UsuarioDAO;
import daos.jdbc.*;
import excepciones.CamposVaciosException;
import excepciones.DNIDuplicadoException;
import excepciones.DatosInvalidosException;
import excepciones.EmailInvalidoException;
import modelo.perfiles.*;

public class RegistroControlador {


    private VistaRegistro vista;
    private UsuarioDAO u;
    private PersonaDAO p;	 

    public RegistroControlador(VistaRegistro vista) {
        this.vista = vista;
        this.u = new UsuarioDAOjdbc(); 
        this.p = new PersonaDAOjdbc();
        inicializarEventos();
    }
    
    
    private void inicializarEventos() {
        // Evento para el botón Registrar (Clase Anónima)
        this.vista.addRegistroListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intentarRegistro();
            }
        });

        // Evento para el botón Volver (Clase Anónima)
        this.vista.addVolverListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverALogin();
            }
        });
    }
    
    
    private void intentarRegistro() {
        try {

            String email = vista.getCampoEmail();
            String pass = vista.getCampoPassword();
            String nombre = vista.getCampoNombre();
            String apellido = vista.getCampoApellido();
            String dniStr = vista.getCampoDni();
            String nroTarjetaStr = vista.getCampoNroTarjeta();
            String usuario = vista.getCampoUsuario();
            

            // VALIDACIÓN DE VACÍOS
            if (email.isEmpty() || usuario.isEmpty() || pass.isEmpty() ||
                nombre.isEmpty() || apellido.isEmpty() ||
                dniStr.isEmpty() || nroTarjetaStr.isEmpty()) {

                throw new CamposVaciosException();
            }

            if (!soloLetras(nombre)) {
                throw new DatosInvalidosException("El nombre no puede contener números.");
            }

            if (!soloLetras(apellido)) {
                throw new DatosInvalidosException("El apellido no puede contener números.");
            }
            
            if (!esNumerico(dniStr) || dniStr.length() < 7 || dniStr.length() > 8) {
                throw new DatosInvalidosException("El DNI debe ser un número de entre 7 y 8 dígitos.");
            }

            if (!esNumerico(nroTarjetaStr)) {
                throw new DatosInvalidosException("El número de tarjeta debe contener solo números.");
            }
            
            
            // CONVERTIR A INT
            int dni = Integer.parseInt(dniStr);
            int nroTarjeta = Integer.parseInt(nroTarjetaStr);
            
            if (p.existeDNI(dni)) {
                throw new DNIDuplicadoException();
            }
            
            // VALIDAR FORMATO EMAIL
            if (!esEmailBasico(email)) { 
                throw new EmailInvalidoException();
            }

            // PEDIMOS AL CONTROLADOR QUE REGISTRE
            boolean exito = registrarUsuario(email, usuario, pass,
                    nombre, apellido, dni, nroTarjeta);
            
            if (exito) { //Resolvi el error sacanco el !, no se si esta bien esta idea. 
                throw new Exception("Error al registrar el nuevo usuario.");
            }

            vista.mostrarMensaje("Registro completado correctamente.");

            VistaLogin vistaLogin = new VistaLogin();
            new LoginControlador(vistaLogin);
            vistaLogin.setVisible(true);
            vista.salir();

        } catch (CamposVaciosException eUno) {
        	vista.mostrarError(eUno.getMessage());
    	} catch (EmailInvalidoException eDos) {
    	    vista.mostrarError(eDos.getMessage());
    	} catch (DatosInvalidosException eTres) {
    	    vista.mostrarError(eTres.getMessage());
    	} catch (DNIDuplicadoException eCuatro) {
    	    vista.mostrarError(eCuatro.getMessage());
        } catch (Exception ex) {
        	vista.mostrarError(ex.getMessage());
        }
    }

    
    
    	public boolean registrarUsuario (String email, String nombreUsuario, String contrasena, String nombre, String apellido, int dni, int nroTarjeta) {
    	Usuario usu = new Usuario(email, contrasena, nombreUsuario);
    	PersonaDAO p = new PersonaDAOjdbc();
		u.insertar(usu);
		Persona per = new Persona (nombre,apellido, dni,nroTarjeta, usu);
		p.insertar(per);
		
		return p.actualizarUsuario(usu, per);
    	}
    
    public static boolean esEmailBasico(String email) {
        int arroba = email.indexOf('@');
        int ultimaArroba = email.lastIndexOf('@');
        
        if (arroba == -1 || arroba != ultimaArroba) {
            return false;
        }

        String antes = email.substring(0, arroba);
        String despues = email.substring(arroba + 1);

        return !antes.isEmpty() && !despues.isEmpty();
    }
    
    private void volverALogin() {

        VistaLogin vistaLogin = new VistaLogin();
        new LoginControlador(vistaLogin);
        vistaLogin.setVisible(true);
        vista.salir();
    }
    
    
    public static boolean esNumerico(String texto) {

        if (texto == null || texto.isEmpty()) {
            return false;
        }

        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isDigit(texto.charAt(i))) {
                return false; // encontró algo que no es número
            }
        }
        return true; // todos son dígitos
    }
    
    public static boolean soloLetras(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (Character.isDigit(texto.charAt(i))) {
                return false; // encontró un número
            }
        }
        return true; // no encontró ningún número
    }
}