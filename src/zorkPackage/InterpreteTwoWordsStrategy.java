package zorkPackage;

import java.util.List;

public class InterpreteTwoWordsStrategy implements InterpreteStrategy{

	private List<String> cadena;
	private boolean tieneSegundaPalabra;
	
	public InterpreteTwoWordsStrategy(List<String> cadena) {
		this.cadena = cadena;
		this.tieneSegundaPalabra = true;
	}
	
	public InterpreteTwoWordsStrategy(List<String> cadena, boolean tieneSegundaPalabra) {
		this.cadena = cadena;
		this.tieneSegundaPalabra = tieneSegundaPalabra;
	}
	
	@Override
	public Comando interpretar() {
		
		Comando cmd = null;
		String verbo = this.cadena.get(0);
		
		///ACA ME FALTA UNA LISTA DE PALABRAS QUE ENCAJEN EN DOS LISTAS PARA PASAR AL GM
		//EJEMPLO TOMAR(PUEDE SER TOMAR AGUA O TOMAR GATO Y ES MUY DISTINTO)
		
		if(Lexico.inMovimiento(verbo))
			cmd = new Comando(verbo, Comando.Tipo.MOVER);
		
		if(Lexico.inAdquirible(verbo))
			cmd = new Comando(verbo, Comando.Tipo.ADQUIRIR);
		
		if(Lexico.inUsuario(verbo))
			cmd = new Comando(verbo, Comando.Tipo.USUARIO);
		
		if(Lexico.inInspeccionable(verbo))
			cmd = new Comando(verbo, Comando.Tipo.INSPECCIONAR);
		
		if(Lexico.inNPC(verbo))
			cmd = new Comando(verbo, Comando.Tipo.NPC);
		
		if(Lexico.inUsable(verbo))
			cmd = new Comando(verbo, Comando.Tipo.USAR);
		
		if(Lexico.inAtacar(verbo))
			cmd = new Comando(verbo, Comando.Tipo.ATACAR);
		
		if(Lexico.inDescartar(verbo))
			cmd = new Comando(verbo, Comando.Tipo.DESCARTAR);
		
		if(cmd == null)
			return new Comando(Comando.Tipo.INVALIDO);
		
		if(this.tieneSegundaPalabra == false)
			cmd.setReEscanear(true);
		else
			cmd.setNombreObjeto(cadena.get(1));
		
		return cmd;
	}

}
