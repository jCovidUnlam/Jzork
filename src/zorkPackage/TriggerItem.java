package zorkPackage;

import java.util.List;

public class TriggerItem extends Trigger{

	List<Item> triggerItems;
	
	public TriggerItem() {};
	

	public List<Item> getTrigges() {
		return triggerItems;
	}

	public void setTrigges(List<Item> trigges) {
		this.triggerItems = trigges;
	}
	
	public void addItem(Item item) {
		this.triggerItems.add(item);
	}
	
	public String onTrigger(Item item) {
		if(!triggerItems.contains(item))
			return onTriggerError();
		
		afterTrigger();
		return this.afterTriggerDesc;
	}
	
	public String onTriggerError() {
		switch(this.error)
		{
		case Responder:
			return this.errorTriggerDesc;
		default:
			break;
		}
		
		return "";
	}
	
	public void afterTrigger() {
		switch(this.exito)
		{
		case Remover:
			this.lugarReferencia.cambiarDescripcion(objetoReferencia);//......Yase que esto es mal
			this.lugarReferencia.getObjetos().remove(this.objetoReferencia);//Anda a saber si esto lo quita
			break;
		default:
			break;
		}
	}
	
	
	
	
}
