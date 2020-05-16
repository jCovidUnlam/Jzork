package zorkPackage;

public class TriggerAtaque extends Trigger{

	private double danioLimite;
	private double danioRecibido;
	private String msjMuerte;
	
	public TriggerAtaque() {};
	
	public double getDanioLimite() {
		return danioLimite;
	}
	public void setDanioLimite(double danioLimite) {
		this.danioLimite = danioLimite;
	}
	public double getDanioRecibido() {
		return danioRecibido;
	}
	public void setDanioRecibido(double danioRecibido) {
		this.danioRecibido = danioRecibido;
	}
	public String getMsjMuerte() {
		return msjMuerte;
	}
	public void setMsjMuerte(String msjMuerte) {
		this.msjMuerte = msjMuerte;
	}

	
}
