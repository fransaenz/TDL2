package app;


import java.util.List;
import java.util.Scanner;

import java.time.Duration;
import java.util.Collections;

import daos.conexion.*;
import daos.interfaces.PeliculaDAO;
import daos.interfaces.PersonaDAO;
import daos.interfaces.ResenaDAO;
import daos.interfaces.UsuarioDAO;
import daos.jdbc.PeliculaDAOjdbc;
import daos.jdbc.PersonaDAOjdbc;
import daos.jdbc.ResenaDAOjdbc;
import daos.jdbc.UsuarioDAOjdbc;
import modelo.audiovisuales.*;
import modelo.audiovisuales.comparadores.ComparadorPorDuracion;
import modelo.audiovisuales.comparadores.ComparadorPorGenero;
import modelo.audiovisuales.comparadores.ComparadorPorTitulo;
import modelo.audiovisuales.util.*;
import modelo.perfiles.*;
import modelo.perfiles.comparadores.ComparadorPorEmail;
import modelo.perfiles.comparadores.ComparadorPorNombreUsuario;
import modelo.resenas.Resena;


public class Apli {

	public static void main(String[] args) {
		
		ConexionBD.getInstancia().inicializarBD();
		
		Scanner scanner = new Scanner(System.in);
		boolean bien = false;
		
		while(bien != true) {
			System.out.println("Si desea registrar datos personales ingrese 1"); 
			System.out.println("Si desea registrar un usuario ingrese 2");
			System.out.println("Si desea registrar una pelicula ingrese 3"); 
			System.out.println("Si desea listar los usuarios ingrese 4");
			System.out.println("Si desea listar las peliculas ingrese 5");
			System.out.println("Si desea registrar una resenia ingrese 6");	
			System.out.println("Si desea aprobar una resenia ingrese 7");
			System.out.println("Si desea salir ingrese 0");
		
			while (!scanner.hasNextInt()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese el numero de la opcion que quiere");
	        }
			int var = scanner.nextInt();
			switch(var) {
				case 0 -> bien = metodoCero();
				case 1 -> registrarDatosPersonales(scanner);
				case 2 -> registrarUsuario(scanner);
				case 3 -> registrarPelicula(scanner);
				case 4 -> listarUsuarios(scanner);
				case 5 -> listarPeliculas(scanner);
				case 6 -> registrarResenia(scanner);
				case 7 -> aprobarResenia(scanner);
				default -> System.out.println("Ingreso no valido");
			}
		
		}
		
	}
	
	
	private static boolean metodoCero() {
		ConexionBD.getInstancia().desconectarBD();
		return true;
		
	}
		
	
	private static void registrarDatosPersonales(Scanner scanner) {
		scanner.nextLine();
		String nombre = "";
		String apellido = "";
		int dni = 0;
		int aux = 0;
		int nroTarjeta = 0;
		boolean valido = false;
		PersonaDAO p = new PersonaDAOjdbc();
		while (aux != 1) {
			valido = false;
			while (!valido) {
				System.out.println("Ingrese el nombre");
				nombre = scanner.nextLine();
				for (int i = 0; i < nombre.length(); i++) {
					if (Character.isDigit(nombre.charAt(i))) {
						valido = true;
						break;
					}
				}
				if (valido) {
					System.out.println("El nombre no esta permitido ya que contiene un numero, ingreselo de nuevo");
					valido = false;
				}
				
				else {
					System.out.println("El nombre esta permitido");
					valido = true;
				}
			}
			
			valido = false;
			
			while (!valido) {
				System.out.println("Ingrese el apellido");
				apellido = scanner.nextLine();
				for (int i = 0; i < apellido.length(); i++) {
					if (Character.isDigit(apellido.charAt(i))) {
						valido = true;
						break;
					}
				}
				if (valido) {
					System.out.println("El apellido no esta permitido ya que contiene un numero, ingreselo de nuevo");
					valido = false;
				}
				
				else {
					System.out.println("El apellido esta permitido");
					valido = true;
				}
			}
			
			valido = false;
			
			while(!valido) {
				System.out.println("Ingrese el DNI");
				while (!scanner.hasNextInt()) {
		            System.out.println("Error: debe ingresar un numero entero.");
		            scanner.next(); 
		            System.out.println("Ingrese el DNI");
		        }
				dni = scanner.nextInt();
				if(p.existeDNI(dni)){ valido = true;}
				if (valido) {
					System.out.println("El DNI no esta permitido ya que no es unico, ingreselo de nuevo");
					 valido = false;
				}
				else {
					System.out.println("El DNI esta permitido");
					 valido = true;
				}
			}
			System.out.println("Ingrese el numero de la tarjeta");
			while (!scanner.hasNextInt()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese el numero de la tarjeta");
	        }

	        nroTarjeta = scanner.nextInt();
		System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n nombre: " + nombre + 
				"\n apellido: " + apellido + "\n DNI: " + dni + "\n nro de la tarjeta: " + nroTarjeta);	
		while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un numero entero.");
            scanner.next(); 
            System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n nombre: " + nombre + 
    				"\n apellido: " + apellido + "\n DNI: " + dni + "\n nro de la tarjeta: " + nroTarjeta);
        }
		aux = scanner.nextInt();
		scanner.nextLine();
		}
		Persona per = new Persona (nombre, apellido, dni, nroTarjeta);
		p.insertar(per);
	}
	
	
	private static void registrarUsuario(Scanner scanner) {
		scanner.nextLine();
		PersonaDAO p = new PersonaDAOjdbc();
		UsuarioDAO u = new UsuarioDAOjdbc();
		String email = "";
		String contrasena = "";
		String nombreUsuario = "";
		boolean var = false;
		int bla = 0;
		List<Persona> personas  = p.listarPersonas();
		if (personas.size() == 0) {
			System.out.println("Error, no hay personas registradas.");
			return;
		}
		int aux = 0;
		int i = 0;
		for (i = 0; i < personas.size(); i++) {
			if (personas.get(i).getPerfil() == null) {
				System.out.println("La persona numero " + (i+1) + " es: " + personas.get(i).toString());
			}
		}
		System.out.println("Ingrese el numero de persona que quiera vincular: ");
		while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un numero entero.");
            scanner.next(); 
            System.out.println("Ingrese el numero de persona que quiera vincular: ");
        }
		aux = scanner.nextInt();
		scanner.nextLine();
		aux--;
		Persona per = personas.get(aux);
		while(bla == 0) {
			var = false;
			while(!var) {
				System.out.println("Ingrese el email");
				email = scanner.nextLine();
				if (!esEmailBasico(email)) {
		            System.out.println("El email no tiene un formato valido.");
		        }
				else {
					var = true;
				}
			}

		System.out.println("Ingrese la contrasena");
		contrasena = scanner.nextLine();
		System.out.println("Ingrese el nombre de usuario");
		nombreUsuario = scanner.nextLine();
		System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n email: " + email +  
				"\n contrasena: " + contrasena + "\n nombre del usuario: " + nombreUsuario);	
		bla = scanner.nextInt();
		scanner.nextLine();
		}
		Usuario usu = new Usuario(email, contrasena, nombreUsuario);
		if(p.actualizarUsuario(usu, per))
		{
			System.out.println("El usuario se asigno a la persona correctamente");
			u.insertar(usu);
		} else {
			System.out.println("El usuario se asigno a la persona correctamente");
		}
		
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
	
	
	private static void registrarPelicula(Scanner scanner) {
		scanner.nextLine();
		PeliculaDAO pe = new PeliculaDAOjdbc();
		int aux = 0;
		String titulo = "";
		String director = "";
		String elenco = "";
		Duration total = Duration.ZERO;
		Genero genero = null;
		boolean ayuda = false;
		String resumen = "";
		Genero[] opciones = Genero.values();
		while (aux != 1) {
			System.out.println("Ingrese el titulo de la pelicula");
			titulo = scanner.nextLine();
			System.out.println("Ingrese un resumen de la pelicula");
			resumen = scanner.nextLine();
			System.out.println("Ingrese el director de la pelicula ");
			director = scanner.nextLine();
			System.out.println("Ingrese el elenco de la pelicula");
			elenco = scanner.nextLine();
			System.out.println("Ingrese las horas totales de la pelicula");
			while (!scanner.hasNextLong()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese las horas totales de la pelicula");
	        }
			long horas = scanner.nextLong();
				Duration d3 = Duration.ofHours(horas);
			System.out.println("Ingrese los minutos restantes de la pelicula");
			while (!scanner.hasNextLong()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese los minutos restantes de la pelicula");
	        }
			long minutos = scanner.nextLong();
	        Duration d2 = Duration.ofMinutes(minutos);
			System.out.println("Ingrese los segundos restantes de la pelicula");
			while (!scanner.hasNextLong()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese los segundos restantes de la pelicula");
	        }
			long segundos = scanner.nextLong();
	        Duration d1 = Duration.ofSeconds(segundos);
	        total = d1.plus(d2).plus(d3);
	        ayuda = false;
	        while (!ayuda) {
		        System.out.println("Eliga el genero de la pelicula: ");
		        for (int i = 0; i < opciones.length; i++) {
		        	System.out.println((i + 1) + ". " + opciones[i]); 
		        }
		        while (!scanner.hasNextInt()) {
		            System.out.println("Error: debe ingresar un numero entero.");
		            scanner.next(); 
		            System.out.println("Eliga el genero de la pelicula: ");
		        }
		        int auxiliar = scanner.nextInt();
		        auxiliar --;
		        if ((auxiliar >= 0) && (auxiliar < opciones.length) ) {
		        	genero = opciones[auxiliar];
		        	ayuda = true;
		        }
		        else {
		        	System.out.println("El numero ingresado no es valido");
		        }
	        }
        System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n titulo: " + titulo + 
				"\n director: " + director + "\n resumen: '" + resumen + "' \n elenco: " + elenco + "\n horas totales: " + total + "\n genero: " + genero );	
        while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un numero entero.");
            scanner.next(); 
            System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n titulo: " + titulo + 
    				"\n director: " + director + "\n resumen: '" + resumen + "' \n elenco: " + elenco + "\n horas totales: " + total + "\n genero: " + genero );
        }
        aux = scanner.nextInt();
		scanner.nextLine();
		}
		Pelicula peli = new Pelicula(titulo, director,  elenco, genero, total, resumen);
		pe.insertar(peli);
	}
	
	private static void listarUsuarios(Scanner scanner) {
		scanner.nextLine();
		UsuarioDAO u = new UsuarioDAOjdbc();
		List<Usuario> usuarios  = u.listarUsuarios();
		if (usuarios.size() == 0) {
			System.out.println("Error, no hay usuarios registrados.");
			return;
		}
		 System.out.println("¿Cómo querés ordenar la lista?");
	        System.out.println("1. Por nombre de usuario");
	        System.out.println("2. Por email");
	        System.out.print("Opción: ");
	        while (!scanner.hasNextInt()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.print("Opción: ");
	        }
	        int opcion = scanner.nextInt();

	        if (opcion == 1) {
	        	Collections.sort(usuarios, new ComparadorPorNombreUsuario());
	        	System.out.println("\nUsuarios ordenados:");
	        	System.out.println("");
		        for (Usuario usu : usuarios) {
		            System.out.println(usu);
		            System.out.println("");
		        }
	        } else if (opcion == 2) {
	        	Collections.sort(usuarios, new ComparadorPorEmail());
	        	System.out.println("\nUsuarios ordenados:");
	        	System.out.println("");
		        for (Usuario usu : usuarios) {
		            System.out.println(usu);
		            System.out.println("");
		        }
	        } else {
	            System.out.println("Opción inválida");
	        }
	
	}

	private static void listarPeliculas(Scanner scanner) {
		scanner.nextLine();
		PeliculaDAO pe = new PeliculaDAOjdbc();
		List<Pelicula> peliculas  = pe.listarPeliculas();
		if (peliculas.size() == 0) {
			System.out.println("Error, no hay peliculas registradas.");
			return;
		}
		System.out.println("¿Cómo querés ordenar la lista?");
        System.out.println("1. Por titulo de la pelicula");
        System.out.println("2. Por genero de la pelicula");
        System.out.println("3. Por duracion de la pelicula");
        System.out.print("Opción: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un numero entero.");
            scanner.next(); 
            System.out.print("Opción: ");
        }
        int opcion = scanner.nextInt();

        if (opcion == 1) {
        	Collections.sort(peliculas, new ComparadorPorTitulo());
        	System.out.println("\nPeliculas ordenadas:");
        	System.out.println("");
            for (Pelicula p : peliculas) {
                System.out.println(p);
                System.out.println("");
            }
        } else if (opcion == 2) {
        	Collections.sort(peliculas, new ComparadorPorGenero());
        	System.out.println("\nPeliculas ordenadas:");
        	System.out.println("");
            for (Pelicula p : peliculas) {
                System.out.println(p);
                System.out.println("");
            }
        } 
        else if (opcion == 3) {
        	Collections.sort(peliculas, new ComparadorPorDuracion());
        	System.out.println("\nPeliculas ordenadas:");
        	System.out.println("");
            for (Pelicula p : peliculas) {
                System.out.println(p);
                System.out.println("");
            }
        } else {
            System.out.println("Opción inválida");
        }

        
	}
	
	private static void registrarResenia(Scanner scanner) {
		scanner.nextLine();
		PeliculaDAO pe = new PeliculaDAOjdbc();
		UsuarioDAO u = new UsuarioDAOjdbc();
		ResenaDAO r = new ResenaDAOjdbc();
		boolean validacion = false;
		Pelicula peli = null;
		int i = 0;
		int j = 0;
		boolean esta = false;
		int estrellas = 0;
		String texto = "";
		System.out.println("Ingrese el nombre de usuario: ");
		String nombreUsu = scanner.nextLine();
		System.out.println("Ingrese la contrasena del usuario: ");
		String contrasena = scanner.nextLine();
		List<Usuario> usuarios  = u.listarUsuarios();
		if (usuarios.size() == 0) {
			System.out.println("Error, no hay usuarios registrados.");
			return;
		}
		for (i = 0; i < usuarios.size(); i++) {
			if ((usuarios.get(i).getNombreUsuario().equals(nombreUsu)) && (usuarios.get(i).getContrasena().equals(contrasena))){
				validacion = true;
				break;
			}
		}
		if (!validacion) {
		    System.out.println("Datos ingresados incorrectos.");
		}
		else {
			System.out.println("Datos ingresados correctos.");
			List<Pelicula> peliculas  = pe.listarPeliculas();
			if (peliculas.size() == 0) {
				System.out.println("Error, no hay peliculas registradas.");
				return;
			}
			for (j = 0; j < peliculas.size(); j++) {
	            System.out.println((j + 1) + ". " + peliculas.get(j)); 
	        }
			while (validacion) {
				System.out.println("Seleccione el numero de la pelicula a reseñar: ");
				while (!scanner.hasNextInt()) {
		            System.out.println("Error: debe ingresar un numero entero.");
		            scanner.next(); 
		            System.out.println("Seleccione el numero de la pelicula a reseñar: ");
		        }
				j = scanner.nextInt();
				if ((j > 0) && (j <= peliculas.size()) ) {
					validacion = false;
					peli = peliculas.get((j-1));
				}
				else {
					System.out.println("El numero ingresado no es valido"); 
				}
			}
			j = 0;
			while(j == 0) {
				esta = false;
				while (!esta) {
					System.out.println("Ingrese las estrellas de la pelicula entre 0 y 5: ");
					while (!scanner.hasNextInt()) {
			            System.out.println("Error: debe ingresar un numero entero.");
			            scanner.next(); 
			            System.out.println("Ingrese las estrellas de la pelicula entre 0 y 5: ");
			        }
					estrellas = scanner.nextInt();
					if ((estrellas > 5) || (estrellas < 0) ) {
						System.out.println("El numero de estrellas no es valido ");
					}
					else {
						esta = true;
					}
				}
				scanner.nextLine();
				System.out.println("Ingrese la resena de la pelicula: ");
				texto = scanner.nextLine();
				System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n su resena es: " + texto + 
						"\n y le da " + estrellas + " estrellas a la pelicula");	
				while (!scanner.hasNextInt()) {
		            System.out.println("Error: debe ingresar un numero entero.");
		            scanner.next(); 
		            System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n su resena es: " + texto + 
							"\n y le da " + estrellas + " estrellas a la pelicula");
		        }
				j = scanner.nextInt();
				scanner.nextLine();
			}
			Resena res = usuarios.get(i).crearResena(peli, estrellas, texto);
			r.insertar(res);
		}
		
		
	}

	private static void aprobarResenia(Scanner scanner) {
		scanner.nextLine();
		ResenaDAO r = new ResenaDAOjdbc();
		List<Resena> resenas  = r.listarResenas();
		if (resenas.size() == 0) {
			System.out.println("Error, no hay resenias registradas.");
			return;
		}
		boolean aux = false;
		int j = 0;
		int i = 0;
		for (j = 0; j < resenas.size(); j++) {
			if (resenas.get(j).estaAprobada() == false) {
	            System.out.println((j+1) + ". " + resenas.get(j)); 
			}
        }
		while (!aux) {
			System.out.println("Ingrese el numero de resena que desea aprobar: ");
			while (!scanner.hasNextInt()) {
	            System.out.println("Error: debe ingresar un numero entero.");
	            scanner.next(); 
	            System.out.println("Ingrese el numero de resena que desea aprobar: ");
	        }
			j = scanner.nextInt();
			j--;
			if ((j > resenas.size()) || (j < 0) || (resenas.get(j).estaAprobada() != false)) {
				System.out.println("El numero de resena no es valido ");
			}
			else {
				aux = true;
			}
		}
	    System.out.println("La resena seleccionada es la siguiente: " + resenas.get(j)); 
	    System.out.println("Si la resena que desea aprobar es esa ingrese 1, en el caso contrario ingrese 0");
	    while (!scanner.hasNextInt()) {
            System.out.println("Error: debe ingresar un numero entero.");
            scanner.next(); 
            System.out.println("Si la resena que desea aprobar es esa ingrese 1, en el caso contrario ingrese 0");
        }
	    i = scanner.nextInt();
	    if (i == 1) {
	    	if(r.aprobar (resenas.get(j))) {
	    		System.out.println("Se actualizo la resena");
	    	}
	    	else{
	    		System.out.println("No se actualizo la resena");
	    	}
	    	
	    }
	    else {
	    	System.out.println("No se actualizo la resena");
	    }
	}
	
	
}