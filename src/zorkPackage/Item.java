package zorkPackage;

public class Item extends Objeto{

	private boolean rompible;
	private String descRompible;
	private boolean tomable;
	private String descTomable;
	private String descNoTomable;

	public Item() {}

	public Item(boolean rompible, String descRompible, boolean tomable, String descTomable, String descNoTomable) {
		super();
		this.rompible = rompible;
		this.descRompible = descRompible;
		this.tomable = tomable;
		this.descTomable = descTomable;
		this.descNoTomable = descNoTomable;
	}

	public boolean isRompible() {
		return rompible;
	}

	public void setRompible(boolean rompible) {
		this.rompible = rompible;
	}

	public String getDescRompible() {
		return descRompible;
	}

	public void setDescRompible(String descRompible) {
		this.descRompible = descRompible;
	}

	public boolean isTomable() {
		return tomable;
	}

	public void setTomable(boolean tomable) {
		this.tomable = tomable;
	}

	public String getDescTomable() {
		return descTomable;
	}

	public void setDescTomable(String descTomable) {
		this.descTomable = descTomable;
	}

	public String getDescNoTomable() {
		return descNoTomable;
	}

	public void setDescNoTomable(String descNoTomable) {
		this.descNoTomable = descNoTomable;
	};
	

}
