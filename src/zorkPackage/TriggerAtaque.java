package zorkPackage;

public class TriggerAtaque extends Trigger{

	private double danioLimite;
	private String msjMuerte;
	
	public TriggerAtaque() {};
	
	public double getDanioLimite() {
		return danioLimite;
	}
	public void setDanioLimite(double danioLimite) {
		this.danioLimite = danioLimite;
	}
	public String getMsjMuerte() {
		return msjMuerte;
	}
	public void setMsjMuerte(String msjMuerte) {
		this.msjMuerte = msjMuerte;
	}

	
}
