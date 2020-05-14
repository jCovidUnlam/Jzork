package zorkPackage;

public final class TriggerMaster {

	public static String EjecutarTriggerItem(Aventura aventura, Comando comando, Objeto afectado, Item item ) {

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
				afectado.atacar(aventura.getPersonaje());// Alf ataca
				return trigger.getErrorTriggerDesc();// Retorna mensaje
			default:
				return trigger.getErrorTriggerDesc();
			}
		}else {
			
			switch(trigger.exito)
			{
			case REMOVEROBJETO:
				aventura.getMapa().removerObjeto(afectado);
				break;
			default:
				break;
			}
			
			switch(trigger.after) {
			case REMOVERITEM:
				aventura.getPersonaje().removerDeInventario(item);
			default:
				break;
			}
			
			return trigger.getAfterTriggerDesc();
			
		}
	}
	
}
