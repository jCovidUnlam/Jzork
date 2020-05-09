package zorkPackage;

public class Objeto {
	
	private int ObjetoID;
	private String nombre;
	private String descripcion;
	
	public Objeto(int objetoID, String nombre, String descripcion) {
		super();
		ObjetoID = objetoID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getObjetoID() {
		return ObjetoID;
	}

	public void setObjetoID(int objetoID) {
		ObjetoID = objetoID;
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





	
}
