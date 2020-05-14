package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public final class Interprete {
	
	public static Comando interpretar(ArrayList<String> cadena) {
		
		Comando comando = new Comando(Comando.Tipo.INVALIDO);

		//Hay que revisar esto, si pasa uno espacio o varios espacios y nada mas podria romper
		if(cadena == null || cadena.size() == 0)
			return comando;//En esta instancia es invalido
		
		String verbo = cadena.get(0);
		
		//Esto significaría que se encuentra en la lista de comandos de movimientos
		if(VerbosAceptados.inMovimiento(verbo))
			comando = esDeMovimiento(cadena);
		
		if(VerbosAceptados.inAdquirible(verbo))
			comando = esDeAdquirir(cadena);
		
		if(VerbosAceptados.inUsuario(verbo))
			comando = esDeUsuario(cadena);
		
		if(VerbosAceptados.inInspeccionable(verbo))
			comando = esDeInspeccionar(cadena);
		
		if(VerbosAceptados.inNPC(verbo))
			comando = esDeNPC(cadena);
		
		if(VerbosAceptados.inUsable(verbo))
			comando = esDeUsar(cadena);
		
		//Habria que hacer lo mismo con cada tipo de verbo y pensar las excepciones de cada uno.
		
		
		return comando;
	}
	
	private static Comando esDeMovimiento(List<String> cadena) {
		
		String verbo = cadena.get(0);
		
		//Esto valida que si el usuario escribe "ir [lugar]" tome el lugar. Recuerden que el 'a' o 'hacia' ya fueron removidos
		if(verbo.equals("ir")) {
			if(cadena.size() == 1)
				return new Comando(Comando.Tipo.INVALIDO);
			else
				return new Comando(verbo, cadena.get(1), Comando.Tipo.MOVER);
		}
		
		return new Comando(verbo, "", Comando.Tipo.MOVER);
	}
	
	private static Comando esDeAdquirir(List<String> cadena) {
		
		//No existen hasta donde entiendo, comandos de este tipo que no involucren al menos 2 palabras
		if(cadena.size() < 2)
			return new Comando(Comando.Tipo.INVALIDO);
		
		String verbo = cadena.get(0);
		String objeto = cadena.get(1);
		
		return new Comando(verbo, objeto, Comando.Tipo.ADQUIRIR);
	}
	
	private static Comando esDeUsuario(List<String> cadena)
	{
		//Por el momento, solo veo la primer palabra, no hay excepciones por ahora
		String verbo = cadena.get(0);
		String objeto = "";
		if(cadena.size() > 1)
			objeto = cadena.get(1);
		
		return new Comando(verbo, objeto, Comando.Tipo.USUARIO);
	}
	
	private static Comando esDeInspeccionar(List<String> cadena) {
		
		//Aca pasa el comando y el objeto si o si por ahora, si pasa algo mas esta mal
		if(cadena.size() < 2)
			return new Comando(Comando.Tipo.INVALIDO);
		
		String verbo = cadena.get(0);
		String objeto = cadena.get(1);
		
		return new Comando(verbo, objeto, Comando.Tipo.INSPECCIONAR);
	}
	
	private static Comando esDeNPC(List<String> cadena) {
		
		if(cadena.size() < 2)
			return new Comando(Comando.Tipo.INVALIDO);
		
		String verbo = cadena.get(0);
		String objeto = cadena.get(1);
		
		return new Comando(verbo, objeto, Comando.Tipo.NPC);
	}
	
	private static Comando esDeUsar(List<String> cadena) {
		
		//Siempre tiene que tener 2
		if(cadena.size() < 2)
			return new Comando(Comando.Tipo.INVALIDO);
		
		String verbo = cadena.get(0);
		String objeto = cadena.get(1);
		
		//Usar pocion, usar mapa, usar algo que no requiera interaccion con otra cosa
		if(cadena.size() == 2)
			return new Comando(verbo, objeto, Comando.Tipo.USAR);
		
		//Este es el caso de usar cuaqluier cosa sobre otro objeto
		//Usar palo npc - usar serrucho ventana
		String afectado = cadena.get(2);
		return new Comando(verbo, objeto, afectado, Comando.Tipo.TRIGGER);
	}
	
	
}
