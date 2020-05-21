package zorkPackage;

import org.apache.log4j.Logger;

public final class Consola {

	/**
	 * Este metodo muestra el menu de inicio
	 */

	private final static Logger log = Logger.getLogger(Consola.class);

	public static void mostrarMensajeBienvenida() {

		String msgBienvenida[] = { "\\n########################", "     Mundo de JZork" };
		imprimirEnConsola(msgBienvenida);

	}

	public static void mostrarMenuInicio(String name) {

		String msgMenuInicio[] = { "\\n########################", "Bienvenidx a JZork " + name + "!",
				"Por favor, elija entre las siguientes opciones:\n", "1 - Iniciar Aventura", "2 - Reglas",
				"3 - Salir"};
		imprimirEnConsola(msgMenuInicio);

//		System.out.println("\n########################\n");
//		System.out.println("Bienvenidx a JZork " + name + "!");
//		System.out.println("Por favor, elija entre las siguientes opciones:\n");
//		System.out.println("1 - Iniciar Aventura");
//		System.out.println("2 - Reglas");
//		System.out.println("3 - Salir");
	}

	public static void imprimirMenuAventuras() {

		String msgMenuAventura[] = { "\nPor favor, seleccione alguna de las Aventuras:", "1 - Aventura 1",
				"2 - Aventura 2", "3 - Volver al menu inicial" };
		imprimirEnConsola(msgMenuAventura);
//		System.out.println("\nPor favor, seleccione alguna de las Aventuras:");
//		System.out.println("1 - Aventura 1");
//		System.out.println("2 - Aventura 2");
//		System.out.println("3 - Volver al menu inicial");
	}

	/**
	 * Este metodo muestra las reglas del juego
	 */
	public static void mostrarReglas() {

		String msgReglas[] = { "\nEstas son las reglas del juego:"
				+ "\n- El juego se basa en una interacci�n por medio de la consola, a trav�s de ciertos comandos espec�ficos."
				+ "\n- Los comandos podr�n escribirse con may�scula o min�scula, solo importa que est�n bien escritos!."
				+ "\n- Si un comando est� mal escrito entonces se le devolver� al jugador el mesaje de 'comando err�neo'."
				+ "\n- Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
				+ "\n- Los posibles comandos son:"
				+ "\n Ver Reglas -> Se mostrar�n las reglas en cualquier momento del juego"
				+ "\n Norte -> Mover� al jugador en la direcci�n Norte"
				+ "\n Sur   -> Mover� al jugador en la direcci�n Sur" + "" };
		imprimirEnConsola(msgReglas);

//		System.out.println("\nEstas son las reglas del juego:"
//				+ "\n- El juego se basa en una interacci�n por medio de la consola, a trav�s de ciertos comandos espec�ficos."
//				+ "\n- Los comandos podr�n escribirse con may�scula o min�scula, solo importa que est�n bien escritos!."
//				+ "\n- Si un comando est� mal escrito entonces se le devolver� al jugador el mesaje de 'comando err�neo'."
//				+ "\n- Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
//				+ "\n- Los posibles comandos son:"
//				+ "\n Ver Reglas -> Se mostrar�n las reglas en cualquier momento del juego"
//				+ "\n Norte -> Mover� al jugador en la direcci�n Norte"
//				+ "\n Sur   -> Mover� al jugador en la direcci�n Sur" + "");
	}

	public static void mostrar(String msj) {
		System.out.println("\n" + msj);
	}

	public static void iniciarAventura(Mapa mapa) {
		
		String msgMenuAventura[] = {"\n########################","     " + mapa.getNombre(),"########################\n",mapa.getDescripcion()};
		imprimirEnConsola(msgMenuAventura);
		
//		System.out.println("\n########################");
//		System.out.println("     " + mapa.getNombre());
//		System.out.println("########################\n");
//		System.out.println(mapa.getDescripcion());
	}

	public static void imprimirEnConsola(String[] mensajes) {

		LoggerHistory.loggerConfig();

		for (String mensaje : mensajes) {
			System.out.println(mensaje);
			log.info(mensaje);
		}

	}

}
