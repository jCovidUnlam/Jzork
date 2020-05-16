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
	
	public static String inventario(List<Item> items, String nombrePJ) {
		String msj = "El inventario de " + nombrePJ + " es:";
		
		for (Item item : items) {
			msj += "\n - " + item.getNombre(); 
		}
		
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
		return npc.getNombre() + "responde: " + npc.getHablar();
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
	
	public static String sinArma(String nombrePersonaje)
	{
		String msj = nombrePersonaje + " nota que no posee un arma, no va a ensuciarse las manos...\n";
		return msj += "Es conveniente que te equipes un arma de tu inventario antes de atacar.";
	}
	
}
