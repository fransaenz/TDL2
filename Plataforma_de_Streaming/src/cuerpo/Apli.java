package cuerpo;


import java.util.List;
import java.util.Scanner;
import logicas.Servicio;
import java.time.Duration;
import resenas.Resena;
import util.*;
import perfiles.*;
import audiovisuales.*;
import java.util.Collections;
//Quiero saber si esta bien poner el Collections

public class Apli {

	public static void main(String[] args) {
		
		Servicio aux = new Servicio(); 
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
		
			int var = scanner.nextInt();
			switch(var) {
				case 0 -> bien = true;
				case 1 -> metodoUno(scanner);
				case 2 -> metodoDos(scanner);
				case 3 -> metodoTres(scanner);
				case 4 -> metodoCuatro(scanner);
				case 5 -> metodoCinco(scanner);
				case 6 -> metodoSeis(scanner);
				case 7 -> metodoSiete(scanner);
				default -> System.out.println("Ingreso no valido");
			}
		
		}
		
	}
	
	
	private static void metodoUno(Scanner scanner) {
		String nombre = "";
		String apellido = "";
		int dni = 0;
		int aux = 0;
		int nroTarjeta = 0;
		boolean valido = false;
		while (aux == 0) {
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
			}
			
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
			}
			while(!valido) {
				System.out.println("Ingrese el DNI");
				dni = scanner.nextInt();
				//consigo la lista de dni de la base de datos
				// if(la lista.contains(dni)){ valido = true;}
				// if (valido) {
					//System.out.println("El DNI no esta permitido ya que no es unico, ingreselo de nuevo");
					// valido = false;
				//}
			}
			System.out.println("Ingrese el numero de la tarjeta");
			nroTarjeta = scanner.nextInt();
		System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n nombre: " + nombre + 
				"\n apellido: " + apellido + "\n DNI: " + dni + "\n nro de la tarjeta: " + nroTarjeta);	
		aux = scanner.nextInt();
		} 
		Persona per = new Persona (nombre, apellido, dni, nroTarjeta);
		// ingreso persona y el dni (a su lista) a la base de datos
	}
	
	
	private static void metodoDos(Scanner scanner) {
		String email = "";
		String contrasena = "";
		String nombreUsuario = "";
		boolean var = false;
		int bla = 0;
		//consigo la lista de personas guardadas en la base de datos
		/*
		 * int aux = 0;
		 * int i = 0
		 for (i = 0; i < lista.length(); i++) {
		 	system.out.println (lista.get(i).toString);
		 	system.out.println ("¿Ésta es la persona que desea asociar al nuevo usuario? Si es correcto ingrese 1, en caso contrario ingrese 0");
		 	aux = scanner.nextInt();
		 	if (aux == 1) {
						break;
					}
			}
		  Persona per = lista.get(i);
		 */
		while(bla == 0) {
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
		}
		Usuario usu = new Usuario(email, contrasena, nombreUsuario);
		per.setPerfil(usu);
		//actualizo la persona y subo el usuario
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
	
	
	private static void metodoTres(Scanner scanner) {
		int aux = 0;
		String titulo = "";
		String director = "";
		String elenco = "";
		Duration total = Duration.ZERO;
		Genero genero = null;
		boolean ayuda = false;
		String resumen = "";
		Genero[] opciones = Genero.values();
		while (aux == 0) {
		System.out.println("Ingrese el titulo de la pelicula");
		titulo = scanner.nextLine();
		System.out.println("Ingrese un resumen de la pelicula");
		resumen = scanner.nextLine();
		System.out.println("Ingrese el director de la pelicula ");
		director = scanner.nextLine();
		System.out.println("Ingrese el elenco de la pelicula");
		elenco = scanner.nextLine();
		System.out.println("Ingrese las horas totales de la pelicula");
		long horas = scanner.nextLong();
        Duration d3 = Duration.ofHours(horas);
		System.out.println("Ingrese los minutos restantes de la pelicula");
		long minutos = scanner.nextLong();
        Duration d2 = Duration.ofMinutes(minutos);
		System.out.println("Ingrese los segundos restantes de la pelicula");
		long segundos = scanner.nextLong();
        Duration d1 = Duration.ofSeconds(segundos);
        total = d1.plus(d2).plus(d3);
        while (!ayuda) {
        System.out.println("Eliga el genero de la pelicula: ");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]); 
        }
        int auxiliar = scanner.nextInt();
        auxiliar --;
        if ((auxiliar >= 0) && (auxiliar < opciones.size()) ) {
        	genero = opciones[auxiliar];
        	ayuda = true;
        }
        else {
        	   System.out.println("El numero ingresado no es valido");
        }
        }
        System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n titulo: " + titulo + 
				"\n director: " + director + "\n resumen: '" + resumen + "' \n elenco: " + elenco + "\n horas totales: " + total + "\n genero: " + genero );	
		aux = scanner.nextInt();
		}
		Pelicula peli = new Pelicula(titulo, director,  elenco, genero, total, resumen);
		// Poner la pelicula en la base de datos
	}
	
	private static void metodoCuatro(Scanner scanner) {
		//obtener la lista de usuarios
		 System.out.println("¿Cómo querés ordenar la lista?");
	        System.out.println("1. Por nombre de usuario");
	        System.out.println("2. Por email");
	        System.out.print("Opción: ");
	        int opcion = scanner.nextInt();

	        if (opcion == 1) {
	        	Collections.sort(usuarios, new ComparadorPorNombreUsuario());
	        } else if (opcion == 2) {
	        	Collections.sort(usuarios, new ComparadorPorEmail());
	        } else {
	            System.out.println("Opción inválida");
	        }

	        System.out.println("\nUsuarios ordenados:");
	        for (Usuario u : usuarios) {
	            System.out.println(u);
	        }
	
	}

	private static void metodoCinco(Scanner scanner) {
		//obtener la lista de peliculas
		System.out.println("¿Cómo querés ordenar la lista?");
        System.out.println("1. Por titulo de la pelicula");
        System.out.println("2. Por genero de la pelicula");
        System.out.println("3. Por duracion de la pelicula");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
        	Collections.sort(peliculas, new ComparadorPorTitulo());
        } else if (opcion == 2) {
        	Collections.sort(peliculas, new ComparadorPorGenero());
        } 
        else if (opcion == 3) {
        	Collections.sort(peliculas, new ComparadorPorDuracion());
        } else {
            System.out.println("Opción inválida");
        }

        System.out.println("\nPeliculas ordenadas:");
        for (Pelicula p : peliculas) {
            System.out.println(p);
        }
	}
	
	private static void metodoSeis(Scanner scanner) {
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
		//consigo la lista de usuarios
		for (i = 0; i < usuarios.size(); i++) {
			if ((usuarios.get(i).getNombreUsuario().equals(nombreUsu)) && (usuarios.get(i).getContrasena().equals(contrasena)){
				validacion = true;
				break;
			}
		}
		if (validacion) {
			//consigo lista de peliculas
			for (j = 0; j < peliculas.size(); j++) {
	            System.out.println((j + 1) + ". " + peliculas.get(j)); 
	        }
			while (validacion) {
			 System.out.println("Eliga la pelicula: ");
			 int j = scanner.nextInt();
			 if ((j >= 0) && (j < peliculas.size()) ) {
				 validacion = false;
				 peli = peliculas.get(j);
			 }
			 else {
				 System.out.println("El numero ingresado no es valido"); 
			 }
			}
			j = 0;
			while(j == 0) {
				while (!esta) {
					System.out.println("Ingrese las estrellas de la pelicula entre 0 y 5: ");
					estrellas = scanner.nextInt();
					if ((estrellas > 5) || (estrellas < 0) ) {
						System.out.println("El numero de estrellas no es valido ");
					}
					else {
						esta = true;
					}
				}
				System.out.println("Ingrese la resena de la pelicula: ");
				texto = scanner.nextLine();
				System.out.println("Si los siguientes datos son correctos ingrese 1, en el caso contrario ingrese 0\n su resena es: " + texto + 
						"\n y le da " + estrellas + " estrellas a la pelicula");	
				j = scanner.nextInt();
			}
			Resena res = new Resena(peli, usuarios.get(i), estrellas, texto);
			//se guarda resena en la base de datos
		}
		
		
	}

	private static void metodoSiete(Scanner scanner) {
	//se consigue la lista de resenas
		boolean aux = false;
		int j = 0;
		int i = 0;
		for (j = 0; j < resenas.size(); j++) {
			if (resenas.get(i).estaAprobada() == false) {
				i++;
	            System.out.println((i) + ". " + resenas.get(j)); 
			}
        }
		while (!aux) {
			System.out.println("Ingrese el numero de resena que desea aprobar: ");
			j = scanner.nextInt();
			if ((j > resenas.size()) || (j < 1) ) {
				System.out.println("El numero de resena no es valido ");
			}
			else {
				aux = true;
			}
		}
	    System.out.println("La resena seleccionada es la siguiente: " + resenas.get(j)); 
	    System.out.println("Si la resena que desea aprobar es esa ingrese 1, en el caso contrario ingrese 0");
	    i = scanner.nextInt();
	    if (i == 1) {
	    	resenas.get(j).aprobar();
	    }
	}
	
	
}