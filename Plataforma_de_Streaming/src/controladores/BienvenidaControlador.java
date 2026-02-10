package controladores;

import gui.*;
import modelo.perfiles.Usuario;
import modelo.resenas.Resena;
import modelo.audiovisuales.util.Genero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import servicios.ServicioBusqueda;

import modelo.audiovisuales.*;
import javax.swing.JButton;
import excepciones.*;

public class BienvenidaControlador {

    private VistaBienvenida vista;
    private Usuario usu;
    private ServicioBusqueda servicioBusqueda;
    private List<Pelicula> top10 = new ArrayList<>();


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
    	        // Llamamos al método
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

        this.vista.addFiltroGeneroListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object seleccionado = vista.getGeneroSeleccionado();

                if (seleccionado instanceof Genero) {
                    // Si es un género real, filtramos normalmente
                    actualizarTop10PorGenero((Genero) seleccionado);
                } else if ("GENERAL".equals(seleccionado)) {
                	vista.setTop10(top10);
                }
            }
        });
        }
    
    private void iniciarCargaDatos() {

        // Mostrar pantalla de carga
        vista.cambiarAVistaCarga();

        ImportadorPeliculas importador = new ImportadorPeliculas();

        Thread hiloImportacion = new Thread(importador, "HiloImportacionPeliculas");

        Thread hiloCoordinador = new Thread(() -> {
            try {

                hiloImportacion.start();

                hiloImportacion.join();

                top10 = ImportadorPeliculas.obtenerTop10();

                javax.swing.SwingUtilities.invokeLater(() -> {
                	
                    vista.actualizarTablaPeliculas(top10);
                    vincularEventosBotonesReseniar();
                    this.servicioBusqueda = new ServicioBusqueda(ImportadorPeliculas.getPeliculasenmemoria());
                    vista.cambiarAVistaContenido();
                });

            } catch (InterruptedException e) {
                javax.swing.SwingUtilities.invokeLater(() ->
                    vista.mostrarError("La carga de películas fue interrumpida")
                );
            }
        }, "HiloCoordinador");

        hiloCoordinador.start();
    }

    private void vincularEventosBotonesReseniar() {
        for (final JButton btn : vista.getBotonesReseniar()) {
            
            // Quitamos listeners previos por si acaso para no duplicar
            for (ActionListener al : btn.getActionListeners()) {
                btn.removeActionListener(al);
            }
            

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Obtenemos el objeto Pelicula que adjuntamos al botón en la Vista
                    Pelicula peliSeleccionada = (Pelicula) btn.getClientProperty("peliculaAsociada");
                    abrirResena(peliSeleccionada);
                }
            });
        }
    }

    	
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
        try {
        	if(!(usu.getMisResenas().isEmpty())) {
        		for (Resena r : usu.getMisResenas()) {
        			if (r.getContenidoResenado().getId() == peli.getId()) {
        				throw new Exception("Opcion desactivada");
        			}
        		}
        	}
            VistaResena vistaRes = new VistaResena();
            new ResenaControlador(vistaRes, peli, usu);
            vistaRes.setVisible(true);
            // No cierro la principal porque quiero que la reseña sea una ventana aparte
            
        } catch (Exception ex) {
        	vista.mostrarError(ex.getMessage());
        }
    }
    
  
    
    
    
    private void volverAlLogin() {
        VistaLogin vLogin = new VistaLogin();
        new LoginControlador(vLogin);
        vLogin.setVisible(true);
        vista.salir(); // Cerramos la vista de Bienvenida
    }

    private void actualizarTop10PorGenero(Genero g) {
        System.out.println("El controlador recibió el género: " + g);
        List<Pelicula> topDiezGenero = ImportadorPeliculas.filtrarPorGenero(g);
        vista.actualizarTablaPeliculas(topDiezGenero);
        vincularEventosBotonesReseniar();
    }
    
    
    
}
