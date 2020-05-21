package zorkPackage;

import java.util.List;

public class InterpreteOneWordStrategy implements InterpreteStrategy {
	
	private List<String> cadena;
	
	public InterpreteOneWordStrategy(List<String> cadena) {
		super();
		this.cadena = cadena;
	}

	@Override
	public Comando interpretar() {
		
		String verbo = this.cadena.get(0);
		
		if(Lexico.inMovimientoSimple(verbo))
			return new Comando(verbo,Comando.Tipo.MOVER);
		
		if(Lexico.inUsuarioSimple(verbo))
			return new Comando(verbo,Comando.Tipo.USUARIO);
		
		return new InterpreteTwoWordsStrategy(this.cadena, false).interpretar();
	}
}
