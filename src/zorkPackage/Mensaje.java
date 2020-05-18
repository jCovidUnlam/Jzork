package zorkPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Mensaje {
	
	public static String mensajeLugar(Lugar lugar) {
		String msj = "";
		if(lugar == null)
			msj = "No hay lugar donde ir!";
		else {
			msj += "\n########################\n";
			msj += "     " + lugar.getNombre() + "\n";
			msj += "########################\n";
			msj += "\n" + lugar.getDescripcion();
			msj += "\nObjetos: ";
			for(Objeto obj : lugar.getObjetos())
				msj += obj.getNombre() + " - ";
		}
		
		return msj;
	}
	
	public static String fueraLimite() {
		return "No hay lugar donde ir!";
	}
	
	public static String noExisteObjeto() {
		return "Ese objeto no está en este lugar.";
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
			msj += "Daño: " + personaje.getDanio();
		else {
			msj += "Daño: " + armaEquipada.getDanio();			
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
		return "Comando erróneo!";
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
		return atacante.getNombre() + " te ha atacado causandote un daño de " + atacante.getDanio(); 
	}
	
	public static String soltoItem(Item item) {
		return "Has soltado el item:" + item.getNombre();
	}
	
	public static String atacarObjeto(Objeto atacante, Objeto atacado) {
		///femenino/masculino
		return atacante.getNombre() + " ha atacado a " + atacado.getNombre() + " causandole " 
				+ atacante.getDanio() + " puntos de daño.";
	}
	
	public static String contraAtaqueRecibido(Objeto atacante, Personaje personaje) {
		return atacante.getNombre() + " contraataca causandote " + atacante.getDanio() +
				" puntos de daño. Tu salud actual es de: " + personaje.getSalud() + ".";
	}
	
	public static String muerteObjeto(Objeto muerto) {
		return muerto.getNombre() + " ha muerto!";
	}
	
	public static String endGameMuerte(Personaje personaje) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date today = new Date();
		
		return personaje.getNombre() + " has muerto en la pelea!/n" +
				"podrás ver todas tus acciones en el file 'Historial_"+
				personaje.getNombre() + "_" + formatter.format(today) + "'.";
	}
	
	public static String yaEstaMuerto(String nombreObjeto) {
		return nombreObjeto + " ya ha muerto, puedes dejar de golpearlo? No seas sangriento.";
	}
	
}
