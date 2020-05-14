package zorkPackage;

public class Arma extends Item{

	private double danio;
	private boolean equipada;

	public boolean isEquipada() {
		return equipada;
	}

	public void setEquipada(boolean equipada) {
		this.equipada = equipada;
	}

	public double getDanio() {
		return danio;
	}

	public void setDanio(double danio) {
		this.danio = danio;
	}
	
	
}
