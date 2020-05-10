package zorkPackage;

public class Objeto {
	
	private String ObjetoID;
	private String nombre;
	private String descripcion;
	private String descripcionMapa;
	
	public Objeto() {};
	
	public Objeto(String objetoID, String nombre, String descripcion, String descripcionMapa) {
		super();
		ObjetoID = objetoID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionMapa = descripcionMapa;
	}

	public String getObjetoID() {
		return ObjetoID;
	}

	public void setObjetoID(String objetoID) {
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
	public String getDescripcionMapa() {
		return descripcionMapa;
	}

	public void setDescripcionMapa(String descripcionMapa) {
		this.descripcionMapa = descripcionMapa;
	}





	
}
