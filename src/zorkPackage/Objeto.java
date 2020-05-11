package zorkPackage;

import java.util.List;

public class Objeto {
	
	private String id;
	private String nombre;
	private String descripcion;
	private String descripcionMapa;
	private List<Trigger> triggers;
	
	public Objeto() {};
	
	public Objeto(String objetoID, String nombre, String descripcion, String descripcionMapa) {
		super();
		id = objetoID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionMapa = descripcionMapa;
	}
	
	public void addTrigger(Trigger trigger) {
		this.triggers.add(trigger);
	}

	public String getObjetoID() {
		return id;
	}

	public void setObjetoID(String objetoID) {
		id = objetoID;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionMapa() {
		return descripcionMapa;
	}

	public void setDescripcionMapa(String descripcionMapa) {
		this.descripcionMapa = descripcionMapa;
	}





	
}
