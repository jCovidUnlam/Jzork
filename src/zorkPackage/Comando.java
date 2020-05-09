package zorkPackage;

public class Comando {

	public enum Tipo {
		Invalido,
		Mover,
		Tomar,
		Ver,
		Romper,
		Mezclar,
		Usar,
	}
	
	private String nombre;
	private String nombreObjeto;
	private Tipo tipo;
	
	public Comando() {
		super();
	}
	
	public Comando(Tipo tipo) {
		super();
		this.tipo = tipo;
	}
	
	public Comando(String nombre, String nombreObjeto, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.nombreObjeto = nombreObjeto;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreObjeto() {
		return nombreObjeto;
	}
	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
}
