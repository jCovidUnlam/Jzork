package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public class Contenedor extends Item{

	private List<Item> contenido;
	
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
