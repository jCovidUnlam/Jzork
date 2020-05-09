package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public final class Interprete {
	
	public static Comando interpretar(ArrayList<String> cadena) {
		
		//Hay que revisar esto, si pasa uno espacio o varios espacios y nada mas podria romper
		if(cadena == null || cadena.size() == 0 || cadena.get(0) == "")
			return new Comando(Comando.Tipo.Invalido);
		
		Comando comando = new Comando();
		String verbo = cadena.get(0);
		
		//Esto significaría que se encuentra en la lista de comandos de movimientos
		if(VerbosAceptados.inMovimiento(verbo))
			comando = esDeMovimiento(cadena);
		
		
		
		//Habria que hacer lo mismo con cada tipo de verbo y pensar las excepciones de cada uno.
		
		
		return comando;
	}
	
	private static Comando esDeMovimiento(List<String> cadena) {
		
		String verbo = cadena.get(0);
		
		//Esto valida que si el usuario escribe "ir [lugar]" tome el lugar. Recuerden que el 'a' o 'hacia' ya fueron removidos
		if(verbo.equals("ir")) {
			if(cadena.size() == 1)
				return new Comando(Comando.Tipo.Invalido);
			else
				return new Comando(verbo, cadena.get(1), Comando.Tipo.Mover);
		}
		
		return new Comando(verbo, "", Comando.Tipo.Mover);
	}
	
	
	
}
