package zorkPackage;

import java.util.List;

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
		}
		
		return msj;
	}
	
	public static String fueraLimite() {
		return "No hay lugar donde ir!";
	}
	
	public static String noExisteObjeto() {
		return "Ese objeto no est� en este lugar.";
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
	
	public static String inventario(List<Item> items, String nombrePJ) {
		String msj = "El inventario de " + nombrePJ + " es:";
		
		for (Item item : items) {
			msj += "\n - " + item.getNombre(); 
		}
		
		return msj;
	}
	
	public static String mostrarObjeto(Objeto objeto)
	{
		return objeto.getDescripcion();
	}
	
	public static String comandoErroneo() {
		return "Comando err�neo!";
	}
	
	public static String noExisteNPC() {
		return "No existe ese NPC en este lugar!";
	}
	
	public static String ncpMensaje(NPC npc) {
		return npc.getNombre() + "responde: " + npc.getHablar();
	}
	
	public static String noTienesItem(String nombrePersonaje) {
		return nombrePersonaje + ", no tienes nada parecido en tu inventario";
	}
	
	public static String noTieneEfecto(String nombreItem, String nombreAfectado) {
		return "El item " + nombreItem + " no tiene efecto sobre " + nombreAfectado;
	}
}
