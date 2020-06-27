package zorkTrigger;

import java.util.List;

import zorkEnum.EnumDireccion;
import zorkPackage.Item;
import zorkPackage.Objeto;

public class TriggerLugar extends Trigger{

	private List<Objeto> triggerObjects;
	private EnumDireccion direccion;
	private boolean seRepite;
	

	public boolean isSeRepite() {
		return seRepite;
	}

	public void setSeRepite(boolean seRepite) {
		this.seRepite = seRepite;
	}

	public EnumDireccion getDireccion() {
		return direccion;
	}

	public void setDireccion(EnumDireccion direccion) {
		this.direccion = direccion;
	}

	public List<Objeto> getTriggerObjects() {
		return triggerObjects;
	}

	public List<Objeto> getTriggersObjects() {
		return triggerObjects;
	}

	public void setTriggerObjects(List<Objeto> trigges) {
		this.triggerObjects = trigges;
	}
	
	public void addItem(Item item) {
		this.triggerObjects.add(item);
	}
	
}
