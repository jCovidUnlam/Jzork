package zorkPackage;

import java.util.LinkedList;
import java.util.List;

public class Personaje {
	private String nombre;
	private List<Item> inventario;
	
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.inventario = new LinkedList<>();
	}
	
	public Personaje(String nombre, List<Item> list) {
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
	
	public List<Item> getInventario() {
		return this.inventario;
	}
	
	public void addObjeto(Item obj) {
		this.inventario.add(obj);
	}
	
	
}
