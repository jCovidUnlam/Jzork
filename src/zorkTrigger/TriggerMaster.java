package zorkTrigger;

import zorkPackage.Arma;
import zorkPackage.Item;
import zorkPackage.Mapa;
import zorkPackage.Mensaje;
import zorkPackage.Objeto;
import zorkPackage.Personaje;


public final class TriggerMaster {

	public static String ejecutarTriggerItem(Mapa mapa, Objeto afectado, Item item ) {

		TriggerItem trigger = afectado.getTriggerItem();
		
		if(trigger == null)
			return Mensaje.noTieneEfecto(item.getNombre(), afectado.getNombre());
		

		if(!trigger.getTriggersObjects().contains(item)) 
		{
			switch(trigger.error)
			{
			case RESPONDER:
				return trigger.getErrorTriggerDesc();
			case ATACAR:
				afectado.atacar(mapa.getPersonajeActual());
				return trigger.getErrorTriggerDesc();
			default:
				return trigger.getErrorTriggerDesc();
			}
		}else {
			
			switch(trigger.exito)
			{
			case REMOVEROBJETO:
				mapa.getLugarActual().removerObjeto(afectado);
				break;
			case REMOVERITEM:
				mapa.getPersonajeActual().removerDeInventario(item);
				break;
			case ADQUIRIROBJETO:
				mapa.getLugarActual().removerObjeto(afectado);
				mapa.getPersonajeActual().addObjeto((Item)afectado);
				break;
			default:
				break;
			}
			
			switch(trigger.after) {
			case ADQUIRIROBJETO:
				mapa.getPersonajeActual().addObjeto((Item)afectado);
				break;
			case REMOVEROBJETO:
				mapa.getLugarActual().removerObjeto(afectado);
				break;
			case REMOVERITEM:
				mapa.getPersonajeActual().removerDeInventario(item);
				break;
			default:
				break;
			}
			
			return trigger.getExitoTriggerDesc();
		}
	}
	
	public static String ejecutarTriggerAtacar(Mapa mapa, TriggerAtaque trigger, Objeto atacado) {
		
		String msj = "";
		Personaje pj = mapa.getPersonajeActual();
		
		if(pj.getDanio() <= trigger.getDanioLimite())
		{
			switch(trigger.error) {
			case RESPONDER:
				msj += trigger.errorTriggerDesc;
				break;
			case ATACAR:
				atacado.atacar(mapa.getPersonajeActual());
				msj += trigger.errorTriggerDesc;
				msj += Mensaje.personajeAtacado(mapa.getPersonajeActual(), atacado);
				break;
			default:
				break;
			}
			
			return msj;
		}
			
		switch(trigger.exito)
		{
		case CONTRAATACAR:
			atacado.atacar(mapa.getPersonajeActual());
			msj += Mensaje.atacarObjeto(mapa.getPersonajeActual(),atacado);
			if(atacado.isMuerto()) {
	
				mapa.getLugarActual().removerObjeto(atacado);
				return msj += Mensaje.muerteObjeto(atacado);
			}
			else {
	
				atacado.atacar(mapa.getPersonajeActual());
				return Mensaje.personajeAtacado(mapa.getPersonajeActual(), atacado);
			}
		case RESPONDER:
			msj += trigger.exitoTriggerDesc;
			break;
		case REMOVEROBJETO:
			mapa.getLugarActual().removerObjeto(atacado);
			msj += trigger.exitoTriggerDesc;
			break;
		default:
			break;
		}
		
		switch (trigger.after) {
		case REMOVERARMA:
			Arma removida = mapa.getPersonajeActual().desequiparArma(mapa.getPersonajeActual().getArmaEquipada());
			mapa.getPersonajeActual().removerDeInventario(removida);
			break;

		default:
			break;
		}
	
		
		return msj;
	}
		
}
