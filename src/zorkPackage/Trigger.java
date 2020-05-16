package zorkPackage;

public abstract class Trigger {
	
	//Esto lo tire para que salga lo que quiero hacer, dp habria que ver una mejor forma de hacerlo
	
	
	public enum AccionExito{
		REMOVEROBJETO,
		CONTRAATACAR,
		RESPONDER,
	}
	
	public enum AccionError{
		RESPONDER,
		ATACAR,
		NINGUNA,
	}
	
	public enum AccionFinal{
		REMOVERITEM,
		RESPONDER,
		NINGUNA,
	}
	
	protected String afterTriggerDesc;
	protected String errorTriggerDesc;
	protected AccionExito exito;
	protected AccionError error;
	protected AccionFinal after;
	protected String objetoID;
	
	public Trigger() {}

	public String getAfterTriggerDesc() {
		return afterTriggerDesc;
	}

	public void setAfterTriggerDesc(String afterTriggerDesc) {
		this.afterTriggerDesc = afterTriggerDesc;
	}

	public String getErrorTriggerDesc() {
		return errorTriggerDesc;
	}

	public void setErrorTriggerDesc(String errorTriggerDesc) {
		this.errorTriggerDesc = errorTriggerDesc;
	}

	public AccionExito getExito() {
		return exito;
	}

	public void setExito(AccionExito exito) {
		this.exito = exito;
	}

	public AccionError getError() {
		return error;
	}

	public void setError(AccionError error) {
		this.error = error;
	}

	public String getObjetoID() {
		return objetoID;
	}

	public void setObjetoID(String objetoID) {
		this.objetoID = objetoID;
	}
	public AccionFinal getAfter() {
		return after;
	}

	public void setAfter(AccionFinal after) {
		this.after = after;
	}

	
	public String getTriggerClassName() {
		return this.getClass().getSimpleName();
	}


}
