package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public final class Interprete {
	
	public static Comando interpretar(List<String> list) {
		
		//Esto lo hago porque sino no es posible remover un elemento de la lista
		ArrayList<String> cadena = new ArrayList<String>(list);
		
		//Hay que revisar esto, si pasa uno espacio o varios espacios y nada mas podria romper
		if(cadena == null || cadena.size() == 0 || cadena.get(0) == "")
			return new Comando(Comando.Tipo.Invalido);
		
		//Esto saca vacios por ahora.
		removerErrores(cadena);
		//Hay que ver si esto aplica, si nos lo dejan hacer, creeria es lo mas comodo.
		removerArticulos(cadena);
		
		Comando comando = new Comando();
		String verbo = cadena.get(0);
		
		//Esto significaría que se encuentra en la lista de comandos de movimientos
		if(VerbosAceptados.inMovimiento(verbo))
			comando = esDeMovimiento(cadena);
		
		//Habria que hacer lo mismo con cada tipo de verbo y pensar las excepciones de cada uno.
		
		
		return comando;
	}

	private static void removerErrores(ArrayList<String> cadena) {
		
		List<String> articulos = new ArrayList<String>() {
			/**
			 * Esto me lo hace agregar sino tira warninig.... anda a saber que mierda es
			 */
			private static final long serialVersionUID = 2L;

			{
				add(" ");
				add("");
			}		
		};
		
		cadena.removeAll(articulos);
	}
	
	private static void removerArticulos(ArrayList<String> cadena) {
		
		List<String> articulos = new ArrayList<String>() {
			/**
			 * Esto me lo hace agregar sino tira warninig.... anda a saber que mierda es
			 */
			private static final long serialVersionUID = 1L;

			{
				add("el");
				add("la");
				add("ellos");
				add("ellas");
				add("hacia");
				add("a");
				add(" ");
				add("");
			}		
		};
		
		cadena.removeAll(articulos);
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
