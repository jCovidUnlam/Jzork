package zorkUI;

import org.apache.log4j.Logger;

import zorkLogger.LoggerHistory;
import zorkPackage.Mapa;

public final class Consola {

	private final static Logger log = Logger.getLogger(Consola.class);

	public static void mostrarMensajeBienvenida() {

		String msgBienvenida[] = {
				"---------------------------------------------------------------" 
				,"           Bienvenido al grandioso mundo de JZork"};
		imprimirEnConsola(msgBienvenida);
	}
	
	/**
	 * Este metodo muestra el menu de inicio
	 */
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
				,"\nEl juego se basa en una interaccion por medio de la consola, a traves de ciertos comandos especificos."
				, "Los comandos podran escribirse con mayuscula o minuscula, solo importa que esten bien escritos!."
				, "Si un comando esta mal escrito entonces se le devolvera al jugador el mesaje de 'comando erroneo'."
				, "Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'"
				, "Los posibles comandos son:"
				, "   - Ver Reglas: (Se mostraran las reglas en cualquier momento del juego)"
				, "   - Movimientos: (El jugador se podra desplazar y realizar las siguientes acciones)"
				, "      + norte, sur, este, oeste, arriba abajo"
				, "      + ir, subir, bajar, escalar, trepar" 
				, "      + izquierda, derecha, frente, atras" 
				, "   - Jugador: "
				, "      + estado, inventario, reglas"
				, "      + equipar"
				, "      + hablar, charlar, interactuar"
				, "      + atacar, achurrar, darle"
				, "   - Objetos: (ejecutar 'objeto accion' y dependiendo del tipo de objeto, el jugador podra realizar diversas acciones)"
				, "      + tomar, obtener, agarrar, adquirir"
				, "      + romper, destruir, golpear, patear, estallar"
				, "      + tomar, beber, ingerir, comer"
				, "      + insepeccionar, mirar, observar, ver"
				, "      + usar, utilizar"
				, "      + soltar, descartar, desechar, tirar"
				, "\nPara volver al menu inicial presione 0."
				};
		imprimirEnConsola(msgReglas);
	}


	public static void mostrar(String msj) {
		System.out.println("\n" + msj);

		String msjCad[] = msj.split("\n");

		for (String mensaje : msjCad) {
			log.info(mensaje);
		}
	}

	public static void iniciarAventura(Mapa mapa) {

		int tam = mapa.getNombre().length();
		String encabezado = "";
		while(tam>=-4)
		{
			encabezado += "-";
			tam--;
		}
		
		String msgMenuAventura[] = { "\n", encabezado, "  " + mapa.getNombre(), encabezado, mapa.getDescripcion()};
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
