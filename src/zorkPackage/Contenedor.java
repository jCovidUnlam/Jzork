package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public class Contenedor extends Item{

	private List<Item> contenido;
	private boolean abrible;
	private String mensajeAbrible;
	
	
	public String getMensajeAbrible() {
		return mensajeAbrible;
	}

	public void setMensajeAbrible(String mensajeAbrible) {
		this.mensajeAbrible = mensajeAbrible;
	}

	public boolean isAbrible() {
		return abrible;
	}

	public void setAbrible(boolean abrible) {
		this.abrible = abrible;
	}

	public Contenedor() {
		this.contenido = new ArrayList<Item>();
	}

	public List<Item> getContenido() {
		return contenido;
	}

	public void setContenido(List<Item> contenido) {
		this.contenido = contenido;
	}
	
	public void addContenido(Item item) {
		this.contenido.add(item);
	}

	
}
