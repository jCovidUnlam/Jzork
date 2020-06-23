package zorkTrigger;

import zorkPackage.Item;
import zorkPackage.Mapa;
import zorkPackage.Mensaje;
import zorkPackage.Objeto;
import zorkPackage.Personaje;

public final class TriggerMaster {

	public static String EjecutarTriggerItem(Mapa mapa, Objeto afectado, Item item ) {

		TriggerItem trigger = afectado.getTriggerItem();
		
		if(trigger == null)
			return Mensaje.noTieneEfecto(item.getNombre(), afectado.getNombre());
		
		//Si llego hata aca, entocnes el trigger corre!
		if(!trigger.getTrigges().contains(item)) //Si el item que esta usando el usaurio no tiene efecto en el objeto
		{
			switch(trigger.error)
			{
			case RESPONDER:
				return trigger.getErrorTriggerDesc();
			case ATACAR:
				afectado.atacar(mapa.getPersonajeActual());// Alf ataca
				return trigger.getErrorTriggerDesc();// Retorna mensaje
			default:
				return trigger.getErrorTriggerDesc();
			}
		}else {
			
			switch(trigger.exito)
			{
			case REMOVEROBJETO:
				mapa.getLugarActual().removerObjeto(afectado);
				break;
			default:
				break;
			}
			
			switch(trigger.after) {
			case REMOVERITEM:
				mapa.getPersonajeActual().removerDeInventario(item);
			default:
				break;
			}
			
			return trigger.getAfterTriggerDesc();
		}
	}
	
	public static String EjecutarTriggerAtacar(Mapa mapa, TriggerAtaque trigger, Objeto atacado) {
		
		String msj = "";
		Personaje pj = mapa.getPersonajeActual();
		
		if(pj.getDanio() <= trigger.getDanioLimite())
		{
			switch(trigger.error) {
			case RESPONDER:
				return trigger.errorTriggerDesc;
			default:
				break;
			}
		}
			
		switch(trigger.exito)
		{
		case CONTRAATACAR:
			atacado.atacar(mapa.getPersonajeActual());
			msj += Mensaje.atacarObjeto(mapa.getPersonajeActual(),atacado);
			if(atacado.isMuerto()) {
				//Si el atacado muere, se va del lugar... esto podriamos ver como hacerlo.
				mapa.getLugarActual().removerObjeto(atacado);
				return msj += Mensaje.muerteObjeto(atacado);
			}
			else {
				//Sino se muere, te la da.
				atacado.atacar(mapa.getPersonajeActual());
				return Mensaje.personajeAtacado(mapa.getPersonajeActual(), atacado);
			}
		case RESPONDER:
			msj += trigger.exitoTriggerDesc;
		default:
			break;
		}
	
		
		return msj;
	}
	
}