package zorkTrigger;

import java.util.List;

import zorkPackage.Item;
import zorkPackage.Objeto;

public class TriggerLugar extends Trigger{

	private List<Objeto> triggerObjects;
	private int contador;
	private boolean ejecutar;
	
	public List<Objeto> getTriggersObjects() {
		return triggerObjects;
	}

	public void setTriggerObjects(List<Objeto> trigges) {
		this.triggerObjects = trigges;
	}
	
	public void addItem(Item item) {
		this.triggerObjects.add(item);
	}
	
	public int getContador() {
		return this.contador; 
	}
	
	public void restarContador() {
		this.contador--;
	}
	
	public void setEjecutar(boolean flag) {
		this.ejecutar = flag;
	}
}
