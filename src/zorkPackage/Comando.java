package zorkPackage;

public class Comando {
	String nombre;
	String nombreObjeto;


	public Comando(String nombre, String nombreObjeto) {
		super();
		this.nombre = nombre;
		this.nombreObjeto = nombreObjeto;
	}

	public String getNombreObjeto() {
		return nombreObjeto;
	}

	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
}
