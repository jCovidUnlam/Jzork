package zorkStrategy;

import zorkPackage.Comando;
import zorkPackage.Mapa;
import zorkPackage.Mensaje;

public class ComandoCeroObjetosStrategy implements ComandoStrategy{

	private Mapa mapa;

	public ComandoCeroObjetosStrategy(Mapa mapa) {
		super();
		this.mapa = mapa;
	}

	@Override
	public String ejectuar(Comando cmd) {
		
		String resultado = "";
		
		switch (cmd.getTipo()) {
		case MOVER:
			resultado = ejecutarComandoSimpleMover(cmd); 
			break;
		case USUARIO:
			resultado = ejecutarComandoSimpleUsuario(cmd);
			break;
		case SALIDA:
			resultado = ejecutarExitGame(cmd);
			break;
		default:
			break;
		}
		
		return resultado;
	}

	private String ejecutarComandoSimpleUsuario(Comando cmd) {
		
		switch (cmd.getVerbo()) {
		case "inventario":
			return Mensaje.mostrarInventario(mapa.getPersonajeActual());
		case "estado":
			return Mensaje.estadoPersonaje(mapa.getPersonajeActual());
		case "reglas":
			return Mensaje.getReglas();
		default:
			return Mensaje.comandoErroneo();
		}
	}
	
	private String ejecutarComandoSimpleMover(Comando cmd) {
		return this.mapa.mover(cmd.getVerbo());
	}
	
	private String ejecutarExitGame(Comando cmd) {
		return "ENDGAMESALIDA.";
	}
	
	
	
}
