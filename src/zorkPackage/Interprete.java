package zorkPackage;

import java.util.ArrayList;

import zorkStrategy.InterpreteOneWordStrategy;
import zorkStrategy.InterpreteStrategy;
import zorkStrategy.InterpreteThreeWordStrategy;
import zorkStrategy.InterpreteTwoWordsStrategy;

public final class Interprete {
	
	public static Comando interpretar(ArrayList<String> cadena) {
		
		if(cadena == null || cadena.size() == 0)
			return new Comando(Comando.Tipo.INVALIDO);
		
		InterpreteStrategy estrategy = null;
		
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
