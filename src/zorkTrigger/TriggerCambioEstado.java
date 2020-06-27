package zorkTrigger;

import zorkPackage.Objeto;

public class TriggerCambioEstado extends Trigger{

	private Objeto objeto;
	private boolean tomable;
	
	public Objeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	public boolean isTomable() {
		return tomable;
	}
	public void setTomable(boolean tomable) {
		this.tomable = tomable;
	}
	
	
}
