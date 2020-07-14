package zorkPackage;

import java.util.List;

public final class Mensaje {
	
	private static String fileName;
	
	public static String getFileName() {
		return Mensaje.fileName;
	}

	public static void setFileName(String fileName) {
		Mensaje.fileName = fileName;
	}

	public static String mensajeLugar(Lugar lugar) {
		
		if(lugar == null)
			return "No hay lugar donde ir!";
		
		String msj = "";
		String encabezado = "";
		int tam = lugar.getNombre().length();
		
		while(tam>=-4)
		{
			encabezado += "-";
			tam--;
		}
		encabezado += "\n";
		msj += encabezado;
		msj += "  " + lugar.getNombre() + "\n";
		msj += encabezado;
		msj += "\n" + lugar.getDescripcion();
	
		
		return msj;
	}
	
	public static String fueraLimite(String mensajeLimite) {
		if(mensajeLimite == null || mensajeLimite.equals(""))
			return "No hay lugar donde ir!";
		
		return mensajeLimite;
	}
	
	public static String noExisteObjeto() {
		return "Ese objeto no esta en este lugar.";
	}
	
	public static String tomarItem(Item item) { 
		return "Has adquirido "+ item.getNombre();
	}
	
	public static String noTomable(Item item) {
		return item.getMensajeNoTomable();
	}
	
	public static String existeObstaculo(Obstaculo obstaculo) {
		return obstaculo.getMensaje();
	}
	
	public static String mostrarInventario(Personaje personaje) {
		String msj = "El inventario de " + personaje.getNombre() + " es:";
		
		for (Item item : personaje.getInventario())
			msj += "\n - " + item.getNombre(); 
		
		return msj;
	}
	
	public static String estadoPersonaje(Personaje personaje) {
		Arma armaEquipada = personaje.getArmaEquipada();
		String msj = "El estado de " + personaje.getNombre() + " es: \n";
		msj += "Salud: " + personaje.getSalud() + "\n";
		if(armaEquipada == null)
			msj += "Danio: " + personaje.getDanio();
		else {
			msj += "Danio: " + armaEquipada.getDanio();			
			msj += "\nArma equipada: " + personaje.getArmaEquipada().getNombre();
		}
		return msj;
	}
	
	public static String armaEquipada(Arma arma) {
		return "El arma: " + arma.getNombre() + " ha sido equipada.";
	}
	
	public static String mostrarObjeto(Objeto objeto)
	{
		return objeto.getDescripcion();
	}
	
	public static String comandoErroneo() {
		return "Comando erroneo!";
	}
	
	public static String noExisteNPC() {
		return "No existe ese NPC en este lugar!";
	}
	
	public static String ncpMensaje(NPC npc) {
		return npc.getNombre() + " responde: " + npc.getHablar();
	}
	
	public static String noTienesItem(String nombrePersonaje) {
		return nombrePersonaje + ", no tienes nada parecido en tu inventario";
	}
	
	public static String noTieneEfecto(String nombreItem, String nombreAfectado) {
		return "El item " + nombreItem + " no tiene efecto sobre " + nombreAfectado;
	}
	
	public static String noEsAtacable(String nombreObjeto) {
		return nombreObjeto + " no es atacable!";
	}
	
	public static String personajeAtacado(Personaje personaje, Objeto atacante) {
		return atacante.getNombre() + " te ha atacado causandote un danio de " + atacante.getDanio(); 
	}
	
	public static String soltoItem(Item item) {
		return "Has soltado el item:" + item.getNombre();
	}
	
	public static String atacarObjeto(Objeto atacante, Objeto atacado) {
		return atacante.getNombre() + " ha atacado a " + atacado.getNombre() + " causandole " 
				+ atacante.getDanio() + " puntos de danio.";
	}
	
	public static String contraAtaqueRecibido(Objeto atacante, Personaje personaje) {
		return atacante.getNombre() + " contraataca causandote " + atacante.getDanio() +
				" puntos de danio. Tu salud actual es de: " + personaje.getSalud() + ".";
	}
	
	public static String muerteObjeto(Objeto muerto) {
		return muerto.getNombre() + " ha muerto!";
	}
	
	public static String yaEstaMuerto(String nombreObjeto) {
		return nombreObjeto + " ya ha muerto, puedes dejar de golpearlo? No seas sangriento.";
	}
	
	public static String pretungarJugador(String verbo) {
		return "Que es lo que desea " + verbo + "?";
	}
	
	public static String noEsRompible(String nombreObjeto) {
		return nombreObjeto + " no se puede romper!.";
	}
	
	public static String endGameSuccess(String nombrePersonaje, int cantidadMovimientos) {
		return nombrePersonaje + " Felicitaciones has completado la aventura!\n" +
				"Podras ver todas tus acciones en el file: " + Mensaje.fileName +
				"\nTu cantidad de movimientos ha sido de: " + cantidadMovimientos;
	}
	
	public static String endGameMuerte(String nombrePersonaje, int cantidadMovimientos) {
		return nombrePersonaje + " ha muerto!\n" +
				"Podras ver todas tus acciones en el file: " + Mensaje.fileName +
				"\nTu cantidad de movimientos ha sido de: " + cantidadMovimientos;
	}
	
	public static String endGameSalida(String nombrePersonaje, int cantidadMovimientos) {
		return nombrePersonaje + " ha abandonado como un cobarde!\n" +
				"Aun asi, te dejaremos ver todas tus acciones en el file: " + Mensaje.fileName +
				"\nTu cantidad de movimientos ha sido de: " + cantidadMovimientos;
	}
	
	public static String getReglas() {
		return "--------------------------------------------------------------------------------------------------------" +
			"\nEstas son las reglas del juego:"    +
			"\nEl juego se basa en una interaccion por medio de la consola, a traves de ciertos comandos especificos." +
			 "\nLos comandos podran escribirse con mayuscula o minuscula, solo importa que esten bien escritos!." +
			 "\nSi un comando esta mal escrito entonces se le devolvera al jugador el mesaje de 'comando erroneo'." +
			 "\nTodos los comandos que involucren otro objeto deben describirlo claramente: 'Tomar palo'" +
			 "\nLos posibles comandos son:" +
			 "\n   - Ver Reglas: (Se mostraran las reglas en cualquier momento del juego)" +
			 "\n   - Movimientos: (El jugador se podra desplazar y realizar las siguientes acciones)" +
			 "\n      + norte, sur, este, oeste, arriba abajo" +
			 "\n      + ir, subir, bajar, escalar, trepar" +
			 "\n      + izquierda, derecha, frente, atras" +
			 "\n   - Jugador: " +
			 "\n      + estado, inventario, reglas" +
			 "\n      + equipar" +
			 "\n      + hablar, charlar, interactuar" +
			 "\n      + atacar, achurrar, darle" +
			 "\n   - Objetos: (ejecutar 'objeto accion' y dependiendo del tipo de objeto, el jugador podra realizar diversas acciones)" +
			 "\n      + tomar, obtener, agarrar, adquirir" +
			 "\n      + romper, destruir, golpear, patear, estallar" +
			 "\n      + tomar, beber, ingerir, comer" +
			 "\n      + insepeccionar, mirar, observar, ver" +
			 "\n      + usar, utilizar" +
			 "\n      + soltar, descartar, desechar, tirar" +
			 "\nPara volver al menu inicial presione 0.";
	}
	
	public static String objetoDuplicado(List<Objeto> objetos) {
		String msj = "Existe más de un objeto con ese nombre en el mapa, debes ser mas específico!\n";	
		msj += "Los objetos son:\n";
		for (Objeto objeto : objetos) {
			msj += "-" + objeto.getNombre() + "\n";
		}
		
		return msj;
	}
	
	public static String objetoDuplicadoInventario(List<Item> objetos) {
		String msj = "Existe más de un objeto con ese nombre en el mapa, debes ser mas específico!\n";	
		msj += "Los objetos son:\n";
		for (Objeto objeto : objetos) {
			msj += "-" + objeto.getNombre() + "\n";
		}
		
		return msj;
	}
	
	public static String noHayNadieAtacar() {
		return "En este lugar no hay nada parecido que puedas atacar.";
	}
	
	public static String consumirPocionSalud(double puntosSalud) {
		return "Has consumido una pocion de salud para recuperar " + puntosSalud + " puntos de salud!.\n";
	}
}
