package zorkStrategy;

import java.util.ArrayList;
import java.util.List;

import zorkPackage.Arma;
import zorkPackage.Buscador;
import zorkPackage.Comando;
import zorkPackage.Consumible;
import zorkPackage.Contenedor;
import zorkPackage.Item;
import zorkPackage.Mapa;
import zorkPackage.Mensaje;
import zorkPackage.NPC;
import zorkPackage.Objeto;
import zorkTrigger.TriggerAtaque;
import zorkTrigger.TriggerMaster;

public class ComandoUnObjetoStrategy implements ComandoStrategy{
	
	private Mapa mapa;

	public ComandoUnObjetoStrategy(Mapa mapa) {
		super();
		this.mapa = mapa;
	}
	
	@Override
	public String ejectuar(Comando cmd) {
		
		String resultado = "";
		
		switch (cmd.getTipo()) {
		case MOVER:
			resultado = ejecutarComandoMover(cmd); 
			break;
		case USUARIO:
			resultado = ejecutarComandoUsuario(cmd);
			break;
		case INSPECCIONAR:
			resultado = ejecutarComandoInspeccionar(cmd);
		default:
			break;
		case ADQUIRIR:
			resultado = ejecutarTomarItem(cmd);
			break;
		case DESCARTAR:
			resultado = ejectuarSoltarItem(cmd);
			break;
		case NPC:
			resultado = ejecutarHablarNPC(cmd);
			break;
		case ATACAR:
			resultado = ejecutarAtacarObjeto(cmd);
			break;
		case USAR:
			resultado = ejectuarUsarObjeto(cmd);
			break;
		case CONSUMIR:
			resultado = ejecutarConsumirObjeto(cmd);
			break;
		case ROMPER:
			resultado = ejecutarRomperObjeto(cmd);
			break;
		}
		
		
		return resultado;
	}
	

	
	public String ejecutarComandoMover(Comando cmd) {
		return mapa.irHacia(cmd.getPalabrasClavesPrimerObjeto());
	}
	
	public String ejecutarComandoUsuario (Comando cmd) {
		
		switch (cmd.getVerbo()) {
		case "equipar":
			List<Item> armas = mapa.getPersonajeActual().equiparArma(cmd.getPalabrasClavesPrimerObjeto());

			if (armas == null)
				return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());
			
			if(armas.size() > 1)
				return Mensaje.objetoDuplicadoInventario(armas);
			
		    return Mensaje.armaEquipada((Arma)armas.get(0));
			
		default:
			return Mensaje.comandoErroneo();
		}
	}
	
	private String ejecutarComandoInspeccionar(Comando cmd) {

		switch (cmd.getPalabrasClavesPrimerObjeto().get(0)) {
		case "alrededor":
			return Mensaje.mensajeLugar(mapa.getLugarActual());
		case "reglas":
			return Mensaje.getReglas();
		case "inventario":
			return Mensaje.mostrarInventario(mapa.getPersonajeActual());
		default:
			List<Objeto> obj = new ArrayList<Objeto>(); 
			List<Item> items = new ArrayList<Item>();
			
			///Primero evalua si esta en el lugar.
			obj = mapa.getLugarActual().getObjeto(cmd.getPalabrasClavesPrimerObjeto());
			
			if(obj.size() == 1)
				return Mensaje.mostrarObjeto(obj.get(0));
			
			if(obj.size() > 1)
				return Mensaje.objetoDuplicado(obj);
			
			//No existe en lugar, entonces evalua en el inventario.
			if (obj.size() == 0)
				items = mapa.getPersonajeActual().getObjetoInventario(cmd.getPalabrasClavesPrimerObjeto()); // Si no existe, busca en el inventario
			
			//cuando items retornaba en null rompia. 
			if(items!=null && items.size() == 1)
				return Mensaje.mostrarObjeto(items.get(0));
			
			if(items!=null && items.size() > 1)
				return Mensaje.objetoDuplicadoInventario(items);
			
	
			return Mensaje.noExisteObjeto();
		}


	}

	private String ejecutarTomarItem(Comando cmd) {
		Item item = mapa.getLugarActual().getItem(cmd.getPalabrasClavesPrimerObjeto());

		if (item == null)
			return Mensaje.noExisteObjeto();

		if (item.isTomable() == false)
			return item.getMensajeNoTomable();

		mapa.getLugarActual().removerObjeto(item);
		mapa.getPersonajeActual().addObjeto(item);
		return item.getMensajeTomable();
	}

	private String ejectuarSoltarItem(Comando cmd) {
		List<Item> items = mapa.getPersonajeActual().getObjetoInventario(cmd.getPalabrasClavesPrimerObjeto());

		if (items == null || items.size() < 1)
			return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());
		
		if(items.size() > 1)
			return Mensaje.objetoDuplicadoInventario(items);
		

		mapa.getPersonajeActual().removerDeInventario(items.get(0));
		mapa.getLugarActual().agregarObjeto(items.get(0));

		return Mensaje.soltoItem(items.get(0));
	}
	
	private String ejecutarHablarNPC(Comando cmd) {

		NPC npc = mapa.getLugarActual().getNPC(cmd.getPalabrasClavesPrimerObjeto());
		if (npc == null)
			return Mensaje.noExisteNPC();

		return Mensaje.ncpMensaje(npc);
	}

	private String ejecutarAtacarObjeto(Comando cmd) {

		List<Objeto> objs = mapa.getLugarActual().getObjeto(cmd.getPalabrasClavesPrimerObjeto());
		if (objs == null || objs.size() == 0)
			return Mensaje.noHayNadieAtacar();
		
		Objeto atacado = objs.get(0);
		
		if(atacado.isMuerto())
			return Mensaje.yaEstaMuerto(atacado.getNombre());
		
		if(!atacado.isMatable())
			return Mensaje.noEsAtacable(atacado.getNombre());
		
		if(atacado.getSalud() == 0)
			return Mensaje.noEsAtacable(atacado.getNombre());

		TriggerAtaque trigger = atacado.getTriggerAtaque();
		if (trigger != null)
			return TriggerMaster.EjecutarTriggerAtacar(mapa, trigger, atacado);
			
		// Si no hay ninguna secuencia loca al atacar, es simplemente ida y vuelta de golpes.
		String msj = "";
		mapa.getPersonajeActual().atacar(atacado);
		msj += Mensaje.atacarObjeto(mapa.getPersonajeActual(), atacado);
		
		if(atacado.isMuerto()) {
			mapa.getLugarActual().removerObjeto(atacado);
			msj += "\n";
			return msj += Mensaje.muerteObjeto(atacado);
		}
		else {
			atacado.atacar(mapa.getPersonajeActual());
			msj += "\n";
			msj += Mensaje.contraAtaqueRecibido(atacado, mapa.getPersonajeActual());
			if(mapa.getPersonajeActual().isMuerto()) {
				msj += "\n";
				msj += "ENDGAME.";
			}
		}

		return msj;
	}

	private String ejectuarUsarObjeto(Comando cmd) {
		
		
		
		//Aca hay que analizar que es el objeto que esta a la derecha.
		
		return "";
	}

	public String ejecutarRomperObjeto(Comando cmd) {
		String msj = "";
		Objeto buscado = Buscador.buscarObjetoLugar(cmd, mapa.getLugarActual(), msj);
		
		if(buscado == null)
			return msj;
		
		if(buscado.isRompible() == false)
			return Mensaje.noEsRompible(buscado.getNombre());
		
		if(buscado instanceof Contenedor)
			for (Item item : ((Contenedor) buscado).getContenido()) {
					mapa.getPersonajeActual().addObjeto(item);				
			}
		
		mapa.getLugarActual().removerObjeto(buscado);

		return buscado.getMensajeRompible();

	}
	
	public String ejecutarConsumirObjeto(Comando cmd) {
		String msj = "";
		Consumible buscado = Buscador.buscarConsumible(cmd, mapa.getPersonajeActual(), msj);
		
		if(buscado == null)
			return msj;
		
		msj = buscado.consumir(mapa.getPersonajeActual());
		msj += Mensaje.estadoPersonaje(mapa.getPersonajeActual());
		return msj;
	}
}
