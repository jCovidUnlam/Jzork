package zorkPackage;

import java.util.Arrays;

public final class VerbosAceptados {
	
	//Esta lista contiene los verbos aceptables a la hora de tomar un objeto.
	private static final String[] adquirible = {"tomar","obtener","agarrar","adquirir"
			, /*No sean mal pensados con estas ultimas*/"recoger","coger"};
	
	//Esta lista contiene los verbos aceptables a la hora de romper/golpear algo que tenga efecto.
	private static final String[] rompibles = {"romper","destruir","golpear","patear","estallar"};
	
	//Esta lista contiene los verbos aceptables a la hora de consumir algo.
	private static final String[] consumible = {"tomar","beber","ingerir","comer"};
	
	//Esta lista contiene los verbos aceptables a la hora de inspeccionar algo que tendra algun efecto.
	private static final String[] inspeccionable = {"inspeccionar", "mirar", "observar", "ver"};
	
	//Esta lista contiene los verbos aceptables a la hora de usar algo que este en el inventario o no
	private static final String[] usable = {"usar","utilizar"};
	
	private static final String[] movimiento = {"norte","sur","este","oeste","arriba","abajo","sureste","noreste","suroeste","noroeste","ir"};
	
	private static final String[] usuario = {"inventario","reglas","estado","equipar"};
	
	private static final String[] NPC = {"hablar", "charlar"};
	
	private static final String[] ataque = {"atacar", "golpear", "achurrar", "darle"};
	
	public static boolean inAtacar(String verbo) {
		return Arrays.asList(ataque).contains(verbo);
	}
	
	public static boolean inNPC(String verbo) {
		return Arrays.asList(NPC).contains(verbo);
	}
	
	public static boolean inMovimiento(String verbo) {
		return Arrays.asList(movimiento).contains(verbo);
	}
	
	public static boolean inUsuario(String verbo) {
		return Arrays.asList(usuario).contains(verbo);
	}
	
	public static boolean inAdquirible(String verbo) {
		return Arrays.asList(adquirible).contains(verbo);
	}
	
	public static boolean inRompible(String verbo) {
		return Arrays.asList(rompibles).contains(verbo);
	}
	
	public static boolean inConsumible(String verbo) {
		return Arrays.asList(consumible).contains(verbo);
	}
	
	public static boolean inInspeccionable(String verbo) {
		return Arrays.asList(inspeccionable).contains(verbo);
	}
	
	public static boolean inUsable(String verbo) {
		return Arrays.asList(usable).contains(verbo);
	}
	
}
