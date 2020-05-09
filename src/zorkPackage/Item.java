package zorkPackage;

public class Item extends Objeto{

	private boolean rompible;
	private boolean tomable;
	private String descTomable;
	
	public boolean isTomable() {
		return tomable;
	}

	public void setTomable(boolean tomable) {
		this.tomable = tomable;
	}

	public boolean isRompible() {
		return rompible;
	}

	public void setRompible(boolean rompible) {
		this.rompible = rompible;
	}
	
	public String getDescTomable() {
		return descTomable;
	}

	public void setDescTomable(String descTomable) {
		this.descTomable = descTomable;
	}
}
