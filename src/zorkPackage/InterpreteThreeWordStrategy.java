package zorkPackage;

import java.util.List;

public class InterpreteThreeWordStrategy implements InterpreteStrategy {

	private List<String> cadena;
	
	public InterpreteThreeWordStrategy(List<String> cadena) {
		super();
		this.cadena = cadena;
	}
	
	@Override
	public Comando interpretar() {

		String verbo = this.cadena.get(0);
		String objeto = this.cadena.get(1);
		String afectado = this.cadena.get(2);
		
		if(Lexico.inUsable(verbo))
			return new Comando(verbo, objeto, afectado, Comando.Tipo.TRIGGER);
		
		return new Comando(Comando.Tipo.INVALIDO);
	}

}
