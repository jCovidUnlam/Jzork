package zorkPackage;

public class Item extends Objeto{

	private boolean rompible;
	private String descRompible;
	private boolean tomable;
	private String descTomable;

	public Item(int objetoID, String nombre, String descripcion, boolean rompible, String descRompible, boolean tomable,
			String descTomable) {
		super(objetoID, nombre, descripcion);
		this.rompible = rompible;
		this.descRompible = descRompible;
		this.tomable = tomable;
		this.descTomable = descTomable;
	}

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
	public String getDescRompible() {
		return descRompible;
	}

	public void setDescRompible(String descRompible) {
		this.descRompible = descRompible;
	}
}
