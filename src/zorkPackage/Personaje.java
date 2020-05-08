package zorkPackage;

import java.util.LinkedList;
import java.util.List;

public class Personaje {
	private String nombre;
	private List<Objeto> inventario;
	
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.inventario = new LinkedList<>();
	}
	
	public Personaje(String nombre, List<Objeto> list) {
		super();
		this.nombre = nombre;
		this.inventario = list;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addObjeto(Objeto obj) {
		this.inventario.add(obj);
	}
	
	
}
