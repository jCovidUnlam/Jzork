package zorkPackage;

public abstract class Trigger {
	
	//Esto lo tire para que salga lo que quiero hacer, dp habria que ver una mejor forma de hacerlo
	
	public enum AccionExito{
		Remover,
		ContraAtacar,
		
	}
	
	public enum AccionError{
		Responder,
		Atacar,
	}
	
	protected String afterTriggerDesc;
	protected String errorTriggerDesc;
	protected AccionExito exito;
	protected AccionError error;
	protected Objeto objetoReferencia;
	protected Lugar lugarReferencia;
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

	public Objeto getObjetoReferencia() {
		return objetoReferencia;
	}

	public void setObjetoReferencia(Objeto objetoReferencia) {
		this.objetoReferencia = objetoReferencia;
	}

	public Lugar getLugarReferencia() {
		return lugarReferencia;
	}

	public void setLugarReferencia(Lugar lugarReferencia) {
		this.lugarReferencia = lugarReferencia;
	}
	
	public String getObjetoID() {
		return objetoID;
	}

	public void setObjetoID(String objetoID) {
		this.objetoID = objetoID;
	}


}
