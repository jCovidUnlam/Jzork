package zorkUI;

import org.apache.log4j.Logger;

import zorkLogger.LoggerHistory;
import zorkPackage.Mapa;

public final class Consola {

	/**
	 * Este metodo muestra el menu de inicio
	 */

	private final static Logger log = Logger.getLogger(Consola.class);

	public static void mostrarMensajeBienvenida() {

		String msgBienvenida[] = {
				"---------------------------------------------------------------" 
				,"           Bienvenido al grandioso mundo de JZork"};
		imprimirEnConsola(msgBienvenida);
	}

	public static void mostrarMenuInicio(String name) {

		String msgMenuInicio[] = { "---------------------------------------------------------------", 
				"Hola " + name + " gracias por elegirnos! ",
				"Para comenzar, por favor elija entre las siguientes opciones:\n", 
				"1 - Iniciar Aventura", "2 - Reglas", "3 - Salir" };
		imprimirEnConsola(msgMenuInicio);

	}

	public static void imprimirMenuAventuras() {

		String msgMenuAventura[] = {"----------------------------------------------",
				"Por favor, seleccione alguna de las Aventuras:", "1 - Aventura 1",
				"2 - Aventura 2", "3 - Volver al menu inicial" };
		imprimirEnConsola(msgMenuAventura);

	}

	/**
	 * Este metodo muestra las reglas del juego
	 */
	public static void mostrarReglas() {

		String msgReglas[] = { "--------------------------------------------------------------------------------------------------------"
				,"Estas son las reglas del juego:"    
				,"- El juego se basa en una interacción por medio de la consola, a través de ciertos comandos específicos."
				, "- Los comandos podrán escribirse con mayúscula o minúscula, solo importa que estén bien escritos!."
				, "- Si un comando está mal escrito entonces se le devolverá al jugador el mesaje de 'comando erróneo'."
				, "- Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
				, "- Los posibles comandos son:"
				, " Ver Reglas -> Se mostrarán las reglas en cualquier momento del juego"
				, " Norte -> Moverá al jugador en la dirección Norte"
				, " Sur   -> Moverá al jugador en la dirección Sur"
				, " Verbo objeto   -> Aplicara la accion sobre dicho objeto"
				, "\nPara volver al menu inicial presione 0."
				};
		imprimirEnConsola(msgReglas);
	}

	// este metodo se puede integrar con imprimir en consola porque basicamente
	// hacen lo mismo
	// pero tenemos que cambiar como mandamos los mensajes en la consola
	public static void mostrar(String msj) {
		System.out.println("\n" + msj);

		String msjCad[] = msj.split("\n");

		for (String mensaje : msjCad) {
			log.info(mensaje);
		}
	}

	public static void iniciarAventura(Mapa mapa) {

		int tam = mapa.getNombre().length();
		String encabezado = "\n";
		while(tam>=-4)
		{
			encabezado += "-";
			tam--;
		}
		
		tam = mapa.getDescripcion().length();
		String encabezado2 = "";
		while(tam>=0)
		{
			encabezado2 += "-";
			tam--;
		}
		
		String msgMenuAventura[] = { encabezado, "  " + mapa.getNombre(), encabezado2, mapa.getDescripcion() };
		imprimirEnConsola(msgMenuAventura);
	}

	public static void imprimirEnConsola(String[] mensajes) {

		LoggerHistory.loggerConfig();
		for (String mensaje : mensajes) {

			System.out.println(mensaje);
			log.info(mensaje);
		}

	}

}
