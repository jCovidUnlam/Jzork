package zorkPackage;

public final class Consola {
	
	/**
	 * Este metodo muestra el menu de inicio
	 */
	public static void mostrarMenuInicio() {
		System.out.println("\n########################");
		System.out.println("     Mundo de JZork");
		System.out.println("########################\n");
		System.out.println("Bienvenido a JZork Aventurero!");
		System.out.println("Por favor, elija entre las siguientes opciontes:\n");
		System.out.println("1 - Iniciar Aventura");
		System.out.println("2 - Reglas");
		System.out.println("3 - Salir");
	}
	
	public static void imprimirMenuAventuras() {
		System.out.println("\nPor favor, seleccione alguna de las Aventuras:");
		System.out.println("1 - Aventura 1");
		System.out.println("2 - Aventura 2");
		System.out.println("3 - Volver al menu inicial");
	}
	
	/**
	 * Este metodo muestra las reglas del juego
	 */
	public static void mostrarReglas() {
		System.out.println("\nEstas son las reglas del juego:"
				+ "\n- El juego se basa en una interacci�n por medio de la consola, a trav�s de ciertos comandos espec�ficos."
				+ "\n- Los comandos podr�n escribirse con may�scula o min�scula, solo importa que est�n bien escritos!."
				+ "\n- Si un comando est� mal escrito entonces se le devolver� al jugador el mesaje de 'comando err�neo'."
				+ "\n- Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
				+ "\n- Los posibles comandos son:"
				+ "\n Ver Reglas -> Se mostrar�n las reglas en cualquier momento del juego"
				+ "\n Norte -> Mover� al jugador en la direcci�n Norte"
				+ "\n Sur   -> Mover� al jugador en la direcci�n Sur"
				+ "");
	}
	
	public static void mostrar(String msj) {
		System.out.println("\n" + msj);
	}
	
	public static void mostrarComandoErroneo() {
		System.out.println("\nComando err�neo!");
	}
	
	public static void iniciarAventura(Aventura aventura) {
		System.out.println("\n########################");
		System.out.println("     " + aventura.getNombre());
		System.out.println("########################\n");
		System.out.println(aventura.getDescripcion());
	}
	
	/*public static void mostrarLugar(Lugar lugar) {
		
		if(lugar == null)
			System.out.println("\nNo hay lugar donde ir!");
		else {
			System.out.println("\n########################");
			System.out.println(lugar.getNombre());
			System.out.println("########################");
			System.out.println("\n" + lugar.getDescripcion());
		}
	}*/
}
