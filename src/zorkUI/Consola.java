package zorkUI;

import org.apache.log4j.Logger;
import zorkLogger.LoggerHistory;
import zorkPackage.Mapa;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Consola {

	private final static Logger log = Logger.getLogger(Consola.class);

	public static void mostrarMensajeBienvenida() {

		List<String> msgBienvenida = new ArrayList<>();
		msgBienvenida.add("---------------------------------------------------------------");
		msgBienvenida.add("           Bienvenido al grandioso mundo de JZork");

		imprimirEnConsola(msgBienvenida);
	}
	
	/**
	 * Este metodo muestra el menu de inicio
	 */
	public static void mostrarMenuInicio(String name) {

		List<String> msgMenuInicio = new ArrayList<>();
		msgMenuInicio.add("---------------------------------------------------------------");
		msgMenuInicio.add("Hola " + name + " gracias por elegirnos! ");
		msgMenuInicio.add("Para comenzar, por favor elija entre las siguientes opciones:\n");
		msgMenuInicio.add("1 - Iniciar Aventura");
		msgMenuInicio.add("2 - Reglas");
		msgMenuInicio.add("3 - Salir");

		imprimirEnConsola(msgMenuInicio);

	}
	
	public static void imprimirMenuAventuras() {

		List<String> msgMenuAventura = new ArrayList<>();
		msgMenuAventura.add("----------------------------------------------");
		msgMenuAventura.add("Por favor, seleccione alguna de las Aventuras:");

		File[] files = new File("./Recursos/Aventuras/").listFiles();
		for (File file : files) {
			msgMenuAventura.add(file.getName());
		}

		msgMenuAventura.add("3 - Volver al menu inicial");

		imprimirEnConsola(msgMenuAventura);
	}

	/**
	 * Este metodo muestra las reglas del juego
	 */
	public static void mostrarReglas() {

		List<String> msgReglas = new ArrayList<String>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			add("--------------------------------------------------------------------------------------------------------");
			
			add("Estas son las reglas del juego:");
			add("\nEl juego se basa en una interaccion por medio de la consola, a traves de ciertos comandos especificos.");
			add("Los comandos podran escribirse con mayuscula o minuscula, solo importa que esten bien escritos!.");
			add("Si un comando esta mal escrito entonces se le devolvera al jugador el mesaje de 'comando erroneo'.");
			add("Todos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'");
			add("Los posibles comandos son:");
			add("   - Ver Reglas: (Se mostraran las reglas en cualquier momento del juego)");
			add("   - Movimientos: (El jugador se podra desplazar y realizar las siguientes acciones)");
			add("      + norte, sur, este, oeste, arriba abajo");
			add("      + ir, subir, bajar, escalar, trepar");
			add("      + izquierda, derecha, frente, atras");
			add("   - Jugador: ");
			add("      + estado, inventario, reglas");
			add("      + equipar");
			add("      + hablar, charlar, interactuar");
			add("      + atacar, achurrar, darle");
			add("   - Objetos: (ejecutar 'objeto accion' y dependiendo del tipo de objeto, el jugador podra realizar diversas acciones)");
			add("      + tomar, obtener, agarrar, adquirir");
			add("      + romper, destruir, golpear, patear, estallar");
			add("      + tomar, beber, ingerir, comer");
			add("      + insepeccionar, mirar, observar, ver");
			add("      + usar, utilizar");
			add("      + soltar, descartar, desechar, tirar");
			add("\nPara volver al menu inicial presione 0.");
		}};

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
		
		List<String> msgMenuAventura = new ArrayList<>();
		msgMenuAventura.add(encabezado + " " + mapa.getNombre() + encabezado);
		msgMenuAventura.add(mapa.getDescripcion());

		imprimirEnConsola(msgMenuAventura);
	}

	public static void imprimirEnConsola(List<String> mensajes) {

		LoggerHistory.loggerConfig();
		for (String mensaje : mensajes) {

			System.out.println(mensaje);
			log.info(mensaje);
		}
	}

}
