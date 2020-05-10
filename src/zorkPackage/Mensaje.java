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
		return "\nNo hay lugar donde ir!";
	}
	
	public static String noExisteObjeto() {
		return "\nEse objeto no está en este lugar.";
	}
	
	public static String tomarItem(Item item) { 
		return "Has adquirido "+ item.getNombre();
	}
	
	public static String noTomable(Item item) {
		return item.getDescTomable();
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
}
