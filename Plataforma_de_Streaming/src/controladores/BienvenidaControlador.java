



package controladores;

import gui.*;
import modelo.perfiles.Usuario;
import modelo.audiovisuales.util.Genero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import servicios.ServicioBusqueda;

import modelo.audiovisuales.*;
import javax.swing.JButton;

import daos.jdbc.PeliculaDAOjdbc;
import excepciones.*;

public class BienvenidaControlador {

    private VistaBienvenida vista;
    private Usuario usu;
    private PeliculaDAOjdbc p = new PeliculaDAOjdbc();
    private List<Pelicula> topDiez = new ArrayList<>();
    private ServicioBusqueda servicioBusqueda;

    public BienvenidaControlador(VistaBienvenida vista, Usuario usuario) {
        this.vista = vista;
        this.usu = usuario;
        this.vista.setNombreUsuario(usu.getNombreUsuario());
        inicializarEventos();

        iniciarCargaDatos();
    }

    private void inicializarEventos() {
        
        // BOTÓN BUSCAR
    	this.vista.addBuscarListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // Llamamos al método que contiene el try-catch
    	        manejarBusqueda();
    	    }
    	});

        // BOTÓN CERRAR SESIÓN
        this.vista.addCerrarSesionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAlLogin();
            }
        });

        // CAMBIO DE GÉNERO (JComboBox)
        this.vista.addFiltroGeneroListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenemos el género seleccionado a través de la vista
                Genero seleccionado = vista.getGeneroSeleccionado();
                actualizarTop10PorGenero(seleccionado);
            }
        });

        // BOTONES DE RESEÑAR (Lista de botones)
        for (final JButton btn : vista.getBotonesReseniar()) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Recuperamos la película que le pegamos al botón en la vista
                    Pelicula peliSeleccionada = (Pelicula) btn.getClientProperty("peliculaAsociada");
                    
                    // Se la pasamos al método
                    abrirResena(peliSeleccionada);
                }
            });
        }
    }
    private void iniciarCargaDatos() {
        // Hilo para no congelar la GUI durante la carga
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Aquí iría tu lógica de: obtenerTop10();
                    Thread.sleep(2000); // Simulación de carga
                    
                    // Una vez cargado, mostramos el contenido
                    vista.cambiarAVistaContenido();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
        
        this.servicioBusqueda = new ServicioBusqueda(p.listarPeliculas());
        try {
            this.vista.setTop10(p.getTop10());
        } catch (SQLException e) {
            vista.mostrarError("Error al cargar las películas");
        }
    }

    // --- MÉTODOS DE NAVEGACIÓN ---
    	
    	private void manejarBusqueda() {
    	    try {
    	        String titulo = vista.getTxtBuscar();

    	        if (titulo == null || titulo.isEmpty() ) {
    	            throw new CamposVaciosException();
    	        }
    	        
    	        this.vista.cambiarAVistaCarga();
    	        Pelicula encontrada = servicioBusqueda.buscarPeliculaPorTitulo(titulo);
    	        this.vista.cambiarAVistaContenido();
    	        if (encontrada == null) {
    	        	throw new Exception("La película " + titulo + " no se encuentra disponible.");
    	        }
    	        

    	        VistaBusqueda vistaBusq = new VistaBusqueda();
    	        new BusquedaControlador(vistaBusq, encontrada);
    	        vistaBusq.setVisible(true);
    	        //no cierro la principal
    	        
    	    } catch (CamposVaciosException eUno) {
        		vista.mostrarError(eUno.getMessage());
    	    } catch (Exception ex) {
    	        vista.mostrarError(ex.getMessage());
    	    }
    	}
    

    private void abrirResena(Pelicula peli) {
        VistaResena vistaRes = new VistaResena();
        new ResenaControlador(vistaRes, peli, usu);
        vistaRes.setVisible(true);
        // No cierro la principal porque quiero que la reseña sea una ventana aparte
    }

    private void volverAlLogin() {
        VistaLogin vLogin = new VistaLogin();
        new LoginControlador(vLogin);
        vLogin.setVisible(true);
        vista.salir(); // Cerramos la vista de Bienvenida
    }

    private void actualizarTop10PorGenero(Genero g) {
        System.out.println("El controlador recibió el género: " + g);
        // Tu lógica para recargar la lista de películas...
    }
    
    
    
}
