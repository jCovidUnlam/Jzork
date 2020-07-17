package zorkPackage;

import zorkEnum.EnumComando;
import zorkGraficos.PantallaGrafica;
import zorkStrategy.ComandoCeroObjetosStrategy;
import zorkStrategy.ComandoDosObjetosStrategy;
import zorkStrategy.ComandoStrategy;
import zorkStrategy.ComandoUnObjetoStrategy;
import zorkUI.Consola;

public class Aventura {

	private Mapa mapa;
	private boolean endGame;
	private PantallaGrafica pantalla;
	
	public Aventura(Mapa mapa) {
		super();
		this.mapa = mapa;
		endGame = false;
		Consola.iniciarAventura(mapa);
		Consola.mostrar(Mensaje.mensajeLugar(mapa.getLugarActual()));
		pantalla = new PantallaGrafica(mapa.getLugarActual().getGrafica());
		//pantalla.setAlwaysOnTop(true);
	}
	
	public PantallaGrafica getPantalla() {
		return pantalla;
	}

	public void setPantalla(PantallaGrafica pantalla) {
		this.pantalla = pantalla;
	}

	public boolean isEndGame() {
		return endGame;
	}
	
	public void ejecutar(Comando cmd) {
		
		if(cmd.getTipo() == EnumComando.INVALIDO) {
			Consola.mostrar(Mensaje.comandoErroneo());
			return;
		}
		
		ComandoStrategy strategy = null;
			
		if(cmd.isReEscanear() == true) {
			if(cmd.getTipo().equals(EnumComando.NPC))
				Consola.mostrar("Con quién desea hablar?");
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
				
		this.mapa.setCantidadMovimientos(this.mapa.getCantidadMovimientos() + 1);
		String respuesta = strategy.ejectuar(cmd);
		respuesta = evaluarEndGame(respuesta);
		
		Consola.mostrar(respuesta);
		
		InitConfig.getGm().getPantalla().getPanel().actualizarPantalla();
	}
	
	private String evaluarEndGame(String cadena) {
		
		if(cadena.contains("ENDGAMESALIDA.")) {
			cadena = cadena.replaceAll("ENDGAMESALIDA.", "");
			this.endGame = true;
			cadena += "\n\n" + Mensaje.endGameSalida(mapa.getPersonajeActual().getNombre(), mapa.getCantidadMovimientos());
		}
		
		if(cadena.contains("ENDGAME.")) {
			cadena = cadena.replaceAll("ENDGAME.", "");
			this.endGame = true;
			cadena += "\n\n" + Mensaje.endGameMuerte(mapa.getPersonajeActual().getNombre(), mapa.getCantidadMovimientos());
		}
		
		if(cadena.contains("ENDGAMESUCCESS.")) {
			cadena = cadena.replaceAll("ENDGAMESUCCESS.", "");
			this.endGame = true;
			cadena += "\n\n" + Mensaje.endGameSuccess(mapa.getPersonajeActual().getNombre(), mapa.getCantidadMovimientos());
		}
		
		return cadena;
	}


}
