package zorkPackage;

import zorkEnum.EnumComando;
import zorkStrategy.ComandoCeroObjetosStrategy;
import zorkStrategy.ComandoDosObjetosStrategy;
import zorkStrategy.ComandoStrategy;
import zorkStrategy.ComandoUnObjetoStrategy;
import zorkUI.Consola;

public class Aventura {

	private Mapa mapa;
	private boolean endGame;
	
	public Aventura(Mapa mapa) {
		super();
		this.mapa = mapa;
		endGame = false;
		Consola.iniciarAventura(mapa);
		Consola.mostrar(Mensaje.mensajeLugar(mapa.getLugarActual()));
	}
	
	public boolean isEndGame() {
		return endGame;
	}
	
	public void ejecutar2(Comando cmd) {
		
		if(cmd.getTipo() == EnumComando.INVALIDO) {
			Consola.mostrar(Mensaje.comandoErroneo());
			return;
		}
		
		ComandoStrategy strategy = null;
			
		if(cmd.isReEscanear() == true) {
			if(cmd.getTipo().equals(EnumComando.NPC))
				Consola.mostrar("Con qui�n desea hablar?");
			else
				Consola.mostrar(Mensaje.pretungarJugador(cmd.getVerbo()));
			
			return;
		}
		
		switch (cmd.getCantidadObjetos()) {
		case 0:
			strategy = new ComandoCeroObjetosStrategy(mapa);
			break;
		case 1:
			strategy = new ComandoUnObjetoStrategy(mapa);
			break;
		case 2:
			strategy = new ComandoDosObjetosStrategy(mapa);
			break;
		default:
			break;
		}
				
		String respuesta = strategy.ejectuar(cmd);
		evaluarEndGame(respuesta);
		
		Consola.mostrar(respuesta);
	}
	
	private String evaluarEndGame(String cadena) {
		if(cadena.contains("ENDGAME.")) {
			cadena = cadena.replaceAll("ENDGAME.", "");
			this.endGame = true;
			cadena += "\n\n" + Mensaje.endGameMuerte(mapa.getPersonajeActual().getNombre());
		}
		
		if(cadena.contains("ENDGAMESUCCESS.")) {
			cadena = cadena.replaceAll("ENDGAMESUCCESS.", "");
			this.endGame = true;
			cadena += "\n\n" + Mensaje.endGameSuccess(mapa.getPersonajeActual().getNombre());
		}
		
		return cadena;
	}

}
