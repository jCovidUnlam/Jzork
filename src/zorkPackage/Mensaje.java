package zorkPackage;

public final class Mensaje {
	
	public static String mensajeLugar(Lugar lugar) {
		String msj = "";
		if(lugar == null)
			msj = "\nNo hay lugar donde ir!";
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
		String msj = "";
		msj = "\nHas adquirido "+ item.getNombre();
		return msj;
	}
	
	public static String noTomable(Item item) {
		String msj = "";
		msj = "\n" + item.getDescTomable();
		return msj;
	}
	
	public static String existeObstaculo(Obstaculo obstaculo) {
		String msj = "";
		msj = "\n" + obstaculo.getMensaje();
		return msj;
	}
}
