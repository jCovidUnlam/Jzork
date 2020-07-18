package zorkPackage;

import java.util.List;

import zorkEnum.EnumComando;

public final class Interprete {

	public static Comando interpretar(List<String> cadena) {
		
		if(cadena == null || cadena.size() == 0)
			return new Comando(EnumComando.INVALIDO);
		
		Comando cmd = ReconocerVerbo(cadena);
		
		if(cmd.getCantidadObjetos() == 1)
		{
			try {
				Lexico.removerCaracterDeUnion(cadena);
				cmd.setPalabrasClavesPrimerObjeto(cadena.subList(1, cadena.size()));
				if(cmd.getPalabrasClavesPrimerObjeto().size() == 0)
					cmd.setReEscanear(true);
			}
			catch (Exception e) {
				cmd.setReEscanear(true);
			}
		}
		
		if(cmd.getCantidadObjetos() == 2) {
			try {
				String separador = getCharacterUnion(cadena);
				cmd.setPalabrasClavesPrimerObjeto(cadena.subList(1, cadena.indexOf(separador)));
				cmd.setPalabrasClavesSegundoObjeto(cadena.subList(cadena.indexOf(separador) + 1, cadena.size()));
				if(cmd.getPalabrasClavesPrimerObjeto().size() == 0 || cmd.getPalabrasClavesSegundoObjeto().size() == 0)
					return new Comando(EnumComando.INVALIDO);
			}
			catch (Exception e) {
				return new Comando(EnumComando.INVALIDO);
			}
		}
		

		return cmd;
	}
	
	
	private static Comando ReconocerVerbo(List<String> cadena) {
		
		String verbo = cadena.get(0);
		
		if(Lexico.inSalida(verbo))
			return new Comando(verbo, EnumComando.SALIDA, 0);
			
		if(Lexico.inMovimientoSimple(verbo))
			return new Comando(verbo, EnumComando.MOVER, 0);
		
		if(Lexico.inUsuarioSimple(verbo))
			return new Comando(verbo, EnumComando.USUARIO, 0);
		
		if(Lexico.inMovimiento(verbo))
			return new Comando(verbo, EnumComando.MOVER, 1);
		
		if(Lexico.inAdquiribles_Consumibles(verbo))
			return new Comando(verbo, EnumComando.ADQUIRIR_CONSUMIR, 1);
		
		if(Lexico.inAdquirible(verbo))
			return new Comando(verbo, EnumComando.ADQUIRIR, 1);
		
		if(Lexico.inConsumible(verbo))
			return new Comando(verbo, EnumComando.CONSUMIR, 1);
		
		if(Lexico.inUsuario(verbo))
			return new Comando(verbo, EnumComando.USUARIO, 1);
		
		if(Lexico.inInspeccionable(verbo))
			return new Comando(verbo, EnumComando.INSPECCIONAR, 1);
		
		if(Lexico.inNPC(verbo))
			return new Comando(verbo, EnumComando.NPC, 1);
		
		if(Lexico.inAtacar(verbo))
			return new Comando(verbo, EnumComando.ATACAR, 1);
		
		if(Lexico.inDescartar(verbo))
			return new Comando(verbo, EnumComando.DESCARTAR, 1);
		
		if(Lexico.inRompible(verbo))
			return new Comando(verbo, EnumComando.ROMPER, 1);
		
		if(Lexico.inAbrirbles(verbo))
			return new Comando(verbo, EnumComando.ABRIR, 1);
		
		if(Lexico.inUsable(verbo))
			return new Comando(verbo, EnumComando.USAR, evaluarCantidadPalabras(cadena));
		
		
		return new Comando(EnumComando.INVALIDO);
	}
	
	private static int evaluarCantidadPalabras(List<String> cadena) {
		if(Lexico.noPoseeCaracterDeUnion(cadena))
			return 1;
		
		return 2;
	}
	
	private static String getCharacterUnion(List<String> cadena) {
		for (String string : Lexico.caracteresUnion) {
			if(cadena.contains(string))
				return string;
		}
		
		return "";
	}
	
	
}
