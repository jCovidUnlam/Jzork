package zorkPackage;

import java.util.List;
import java.util.LinkedList;

public class Personaje {
	private String nombre;
	private List<Objeto> objetos;
	
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.objetos = new LinkedList<>();
	}
	
	public Personaje(String nombre, List<Objeto> list) {
		super();
		this.nombre = nombre;
		this.objetos = list;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void addObjeto(Objeto obj) {
		this.objetos.add(obj);
	}
	
	
}
