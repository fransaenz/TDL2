package controladores;

import gui.VistaResena;
import modelo.audiovisuales.Pelicula;
import modelo.perfiles.Usuario;
import modelo.resenas.Resena;

import javax.swing.JOptionPane;

import excepciones.CamposVaciosException;
import daos.jdbc.*;
import daos.interfaces.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResenaControlador {

    private VistaResena vista;
    private Pelicula pelicula;
    private Usuario usu;

    public ResenaControlador(VistaResena vista, Pelicula pelicula, Usuario usu) {
        this.vista = vista;
        this.pelicula = pelicula;
        this.usu = usu;

        // Mandamos el título a la vista apenas inicia
        this.vista.setTitulo(this.pelicula.getTitulo());

        //Activamos el escuchador del botón
        this.inicializarEventos();
    }

    private void inicializarEventos() {
        // Evento para el botón Guardar
        this.vista.addGuardarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarResena();
            }
        });
    }

    private void procesarResena() {
        try {
            // leemos los datos desde la vista
            int estrellas = vista.getCalificacion();
            String comentario = vista.getComentario();

            // Validación básica, que se haya seleccionado al menos una estrella
            if (estrellas == 0) {
                throw new CamposVaciosException();
            }

            if (comentario.isEmpty()) {
            	throw new CamposVaciosException();
            }

            dejarResena(estrellas, comentario);

            vista.mostrarMensaje("Se registró correctamente su calificación.\nMuchas Gracias");
           
            vista.salir();

        } catch (CamposVaciosException eUno) {
        	vista.mostrarError(eUno.getMessage());
        } catch (Exception eDos) {
        	vista.mostrarError(eDos.getMessage());
        }
    }

  
    public void dejarResena(int estrellas, String comentario) throws Exception {
    	
    	ResenaDAO r = new ResenaDAOjdbc();
    	try {

            Resena res = new Resena(this.pelicula, this.usu, estrellas, comentario);

            usu.agregarResena(res);
            //Insertamos en la base de datos a través del DAO
            r.insertar(res);
            
            System.out.println("Reseña insertada con éxito en la BD para: " + pelicula.getTitulo());

        } catch (Exception e) {
            // Si el DAO falla, lanzamos la excepción hacia el controlador
            throw new Exception("Error al guardar en la base de datos: " + e.getMessage());
        }
    	
    	
        System.out.println("Nota: " + estrellas + " | Comentario: " + comentario);
    }
}