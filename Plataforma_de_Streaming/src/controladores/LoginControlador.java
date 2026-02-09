package controladores;

import java.util.List;

import javax.swing.JOptionPane;

import daos.interfaces.UsuarioDAO;
import daos.jdbc.UsuarioDAOjdbc;
import gui.*;
import modelo.perfiles.Usuario;
import java.awt.event.*;
import excepciones.*;

public class LoginControlador {

    private VistaLogin vista;

    public LoginControlador(VistaLogin vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        // Evento Login con Clase Anónima
        this.vista.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // El try-catch suele ir dentro del método que ejecuta la acción
                manejarLogin();
            }
        });

        // Evento Registro con Clase Anónima
        this.vista.addRegistroListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });
    } 
    
    private void manejarLogin() {
    	try {
    		String email = vista.getCampoEmail();
    		String password = vista.getCampoPassword();

    		if (email.isEmpty() || password.isEmpty()) {
    			throw new CamposVaciosException();
    		}

    		if (!esEmailBasico(email)) {
    			throw new EmailInvalidoException();
    		}
    		
    		Usuario encontrado = null;
    		UsuarioDAO u = new UsuarioDAOjdbc();
    		List<Usuario> usuarios  = u.listarUsuarios();
    		for(int i = 0; i<usuarios.size(); i++) {
    			if (usuarios.get(i).getEmail().equals(email)) {
    				encontrado = usuarios.get(i);
    				break;
    			}
    		}

    		if (encontrado == null) {
    			throw new UsuarioNoEncontradoException();
    		}

    		if (!encontrado.getContrasena().equals(password)) {
    			throw new DatosInvalidosException("La contraseña es incorrecta.");
    		}

    		vista.mostrarMensaje("Inicio de sesión exitoso.");
    		VistaBienvenida vistaBienvenida = new VistaBienvenida();
    		new BienvenidaControlador(vistaBienvenida, encontrado);
    		vistaBienvenida.setVisible(true);
    		vista.salir();
    		
    	} catch (CamposVaciosException eUno) {
    		vista.mostrarError(eUno.getMessage());
    	} catch (EmailInvalidoException eDos) {
    	    vista.mostrarError(eDos.getMessage());
    	} catch (UsuarioNoEncontradoException eTres) {
    	    vista.mostrarError(eTres.getMessage());
    	} catch (DatosInvalidosException eCuatro) {
    	    vista.mostrarError(eCuatro.getMessage());
        }
    }
    

    private void abrirRegistro() {

        VistaRegistro vistaRegistro = new VistaRegistro();
        new RegistroControlador(vistaRegistro);
        vistaRegistro.setVisible(true);
        vista.salir();
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
}