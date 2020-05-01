package zorkPackage;

public final class Mensaje {
	
	public static String mensajeLugar(Lugar lugar) {
		String msj = "";
		if(lugar == null)
			msj = "\nNo hay lugar donde ir!";
		else {
			msj += "\n########################\n";
			msj += lugar.getNombre() + "\n";
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
	
	public static String tomarObjeto(Objeto obj) {
		String msj = "";
		msj = "\nHas adquirido "+ obj.getNombre();
		return msj;
	}
	
	public static String noTomable(Objeto obj) {
		String msj = "";
		msj = "\n" + obj.getDescTomable();
		return msj;
	}
}
