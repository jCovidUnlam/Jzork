package zorkPackage;

import java.util.ArrayList;
import java.util.List;

import zorkPackage.Comando.Tipo;

public final class Interprete {
	
	public static Comando interpretar(ArrayList<String> cadena) {
		
		Comando comando = new Comando(Comando.Tipo.INVALIDO);
		InterpreteStrategy estrategy = null;
		
		if(cadena == null || cadena.size() == 0)
			return comando;
		
		if(cadena.size() == 1)
			estrategy = new InterpreteOneWordStrategy(cadena);
		else if(cadena.size() == 2)
		{
			estrategy = new InterpreteTwoWordsStrategy(cadena);
		}
		else {
			estrategy = new InterpreteThreeWordStrategy(cadena);
		}
		
		return estrategy.interpretar();
	}
}
