package zorkTrigger;

import java.util.List;

import zorkPackage.Item;
import zorkPackage.Objeto;

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
		
	
}
