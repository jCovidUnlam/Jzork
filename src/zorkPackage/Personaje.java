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
	
	public Item getObjetoInventario(String nombreObjeto) {
		return this.inventario.stream()
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))//Esta es la consulta real
				.findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public void removerDeInventario(Item removido) {
		this.inventario.remove(removido);
	}
	
	
}
