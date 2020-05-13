package zorkPackage;

import java.util.List;

public class TriggerItem extends Trigger{

	List<Objeto> triggerObjects;
	
	public TriggerItem() {};
	
	public List<Objeto> getTrigges() {
		return triggerObjects;
	}

	public void setTrigges(List<Objeto> trigges) {
		this.triggerObjects = trigges;
	}
	
	public void addItem(Item item) {
		this.triggerObjects.add(item);
	}
	
	public Object onTrigger(Item item) {
		if(!triggerObjects.contains(item))
			return this.error;
		
		return this.exito; 
	}
	
//	public Object onTriggerExito() {
//		
//		switch(this.exito)
//		{
//		//En este caso, tiene que remover este objeto del lugar.
//		case Remover:
//			return this.objetoReferencia;
//		default:
//		//En este caso, solo recibe la respuesta del after trigger.
//		case Responder:
//			return null;
//		}
//		
//	}
//	
//	public Object onTriggerError() {
//		switch(this.error)
//		{
//		//En este caso, solo le responde con un error.
//		case Responder:
//			return this.errorTriggerDesc;
//		default:
//			break;
//		}
//		
//		return "";
//	}
	
	//Esto puede ser mas complejo, por ahora devuelvo mensaje standard de after trigger.
	//El GM deberia llamarlo solo si tiene exito.
	public Object afterTrigger() {
		return this.afterTrigger();
	}
	
	
	
	
}
