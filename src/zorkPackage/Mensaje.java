package zorkPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		///femenino/masculino
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
	
	public static String endGameSuccess(String nombrePersonaje) {
		return nombrePersonaje + " Felicitaciones has completado la aventura!\n" +
				"Podras ver todas tus acciones en el file: " + Mensaje.fileName;
	}
	
	public static String endGameMuerte(Personaje personaje) {
		return personaje.getNombre() + " ha muerto!\n" +
				"Podras ver todas tus acciones en el file: " + Mensaje.fileName;
	}
	
}
