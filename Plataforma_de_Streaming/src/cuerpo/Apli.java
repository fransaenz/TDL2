package cuerpo;


import java.util.List;
import java.util.Scanner;
import logicas.Servicio;

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
			int eleccion = switch(var) {
				case 0 -> bien = true;
				case 1 -> metodoUno(scanner);
				case 2 -> metodoDos(scanner);
				case 3 -> metodoTres(scanner);
				case 4 -> metodoCuatro(scanner);
				case 5 -> metodoCinco(scanner);
				case 6 -> metodoSeis(scanner);
				case 7 -> metodoSiete(scanner);
				default -> System.out.println("Ingreso no valido");
			};
		
		}
		
	}
	
	
	private void metodoUno(Scanner scanner) {
		System.out.println("Ingrese el nombre");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese el apellido");
		String apellido = scanner.nextLine();
		System.out.println("Ingrese el dni");
		int dni = scanner.nextInt();
		System.out.println("Ingrese un numero de tarjeta");
		int nroTarjeta = scanner.nextInt();
	}
	
	private void metodoDos(Scanner scanner) {
		//mostrar listado de personas guardadas en el sistema
		System.out.println("Ingrese el email");
		String email = scanner.nextLine();
		System.out.println("Ingrese la contrasena");
		String contrasena = scanner.nextLine();
		System.out.println("Ingrese el nombre de usuario");
		String nombreUsuario = scanner.nextLine();
		// y con los enum?
	}
	
	private void metodoTres(Scanner scanner) {
		System.out.println("Ingrese el titulo de la pelicula");
		String titulo = scanner.nextLine();
		System.out.println("Ingrese el director de la pelicula ");
		String director = scanner.nextLine();
		System.out.println("Ingrese el elenco de la pelicula");
		String elenco = scanner.nextLine();
		System.out.println("Ingrese la duracion de la pelicula");
		String duracion = scanner.nextLine();
		//y los demas datos?
	}
	
	private void metodoCuatro(Scanner scanner) {
	
	}

	private void metodoCinco(Scanner scanner) {
	
	}
	
	private void metodoSeis(Scanner scanner) {
	
	}

	private void metodoSiete(Scanner scanner) {
	
	}
}