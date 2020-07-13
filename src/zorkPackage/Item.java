package zorkPackage;

public class Item extends Objeto{

	private boolean tomable;
	private String mensajeTomable;
	private String mensajeNoTomable;

	public Item() {}

	public Item(boolean rompible, boolean tomable, String descTomable, String descNoTomable) {
		super();
		this.tomable = tomable;
		this.mensajeTomable = descTomable;
		this.mensajeNoTomable = descNoTomable;
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
