
//No lo usamos por ahora je

package audiovisuales;

import java.util.List;

/**
 * Clase que representa el catalogo, que es una lista de Audiovisuales 
 * que contiene la plataforma de streaming.
 * 
 * @author Lucas, Francisco
 * @version 1.0
 */
public class Catalogo {
	/** Lista de audiovisuales que tiene la plataforma */
    private List<Audiovisual> contenido;
	
	/**
     * Crea una nueva instancia de Trailer.
     */
	public Catalogo() {
	}

	
	/**
	 * Devuelve la lista de contenidos audiovisuales.
	 *
	 * @return una lista del contenido.
	 */
	public List<Audiovisual> getContenido() {
		return contenido;
	}

	
	/**
	 * Establece la lista de contenidos audiovisuales.
	 *
	 * @param contenido una lista de Audiovisual que reemplazará el contenido actual.
	 */
	public void setContenido(List<Audiovisual> contenido) {
		this.contenido = contenido;
	}
	
	
	/**
	 * Agrega un elemento audiovisual a la lista de contenidos.
	 *
	 * @param aux el objeto Audiovisual que se desea agregar al contenido.
	 */
	public void agregarContenido(Audiovisual aux) {
		this.contenido.add(aux);
	}
	
	
	/**
	 * Elimina un elemento audiovisual de la lista de contenidos, si está presente.
	 *
	 * @param aux el objeto Audiovisual que se desea eliminar del contenido.
	 */
	public void eliminarContenido(Audiovisual aux) {
		if (aux != null && this.contenido.contains(aux)) {
			this.contenido.remove(aux);
		}
	}

}
