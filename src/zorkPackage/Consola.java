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
				+ "\n- El juego se basa en una interacción por medio de la consola, a través de ciertos comandos específicos."
				+ "\n- Los comandos podrán escribirse con mayúscula o minúscula, solo importa que estén bien escritos!."
				+ "\n- Si un comando está mal escrito entonces se le devolverá al jugador el mesaje de 'comando erróneo'."
				+ "\n- Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
				+ "\n- Los posibles comandos son:"
				+ "\n Ver Reglas -> Se mostrarán las reglas en cualquier momento del juego"
				+ "\n Norte -> Moverá al jugador en la dirección Norte"
				+ "\n Sur   -> Moverá al jugador en la dirección Sur"
				+ "");
	}
	
	public static void mostrar(String msj) {
		System.out.println("\n" + msj);
	}
	
	public static void mostrarComandoErroneo() {
		System.out.println("\nComando erróneo!");
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
