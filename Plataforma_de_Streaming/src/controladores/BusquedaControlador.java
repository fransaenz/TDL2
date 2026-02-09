package controladores;

import gui.VistaBusqueda;
import modelo.audiovisuales.Pelicula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusquedaControlador {

    private VistaBusqueda vista;

    public BusquedaControlador(VistaBusqueda vista, Pelicula pelicula) {
        this.vista = vista;

        // 1. Extraer datos de la película y cargarlos en la vista
        // Asumo que Pelicula tiene los métodos getTitulo(), getAnio() y getResumen()
        this.vista.cargarDatos(
            pelicula.getTitulo(), 
            pelicula.getAnio(), 
            pelicula.getResumen()
        );

        // 2. Inicializar los eventos
        this.inicializarEventos();
    }

    private void inicializarEventos() {
        // Evento para el botón Continuar usando Clase Anónima
        this.vista.addContinuarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerramos solo esta ventanita
                vista.salir();
            }
        });
    }
}