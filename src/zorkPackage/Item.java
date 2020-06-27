package zorkPackage;

public class Item extends Objeto{

	private boolean rompible;
	private String descRompible;
	private boolean tomable;
	private String mensajeTomable;
	private String mensajeNoTomable;

	public Item() {}

	public Item(boolean rompible, String descRompible, boolean tomable, String descTomable, String descNoTomable) {
		super();

		this.descRompible = descRompible;
		this.tomable = tomable;
		this.mensajeTomable = descTomable;
		this.mensajeNoTomable = descNoTomable;
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
	public String getMensajeTomable() {
		return mensajeTomable;
	}

	public void setMensajeTomable(String mensajeTomable) {
		this.mensajeTomable = mensajeTomable;
	}

	public String getMensajeNoTomable() {
		return mensajeNoTomable;
	}

	public void setMensajeNoTomable(String mensajeNoTomable) {
		this.mensajeNoTomable = mensajeNoTomable;
	}



}
