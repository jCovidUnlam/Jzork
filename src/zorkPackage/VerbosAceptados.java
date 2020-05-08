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
	private static final String[] inspeccionable = {"inspeccionar", "mirar", "observar"};
	
	//Esta lista contiene los verbos aceptables a la hora de usar algo que este en el inventario o no
	private static final String[] usable = {"usar","utilizar"};
	
	
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
