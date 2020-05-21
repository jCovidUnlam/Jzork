package zorkPackage;

public class Comando {

	public enum Tipo {
		INVALIDO,
		MOVER,
		ADQUIRIR,
		INSPECCIONAR,
		ROMPER,
		MEZCLAR,
		USAR,
		USUARIO,
		NPC,
		TRIGGER,
		ATACAR,
		OTRO,
		DESCARTAR,
	}
	
	private String nombre;
	private String nombreObjeto;
	private String nombreAfectado;
	private Tipo tipo;
	private boolean reEscanear;
	
	public Comando() {
		super();
	}


	public Comando(Tipo tipo) {
		super();
		this.tipo = tipo;
	}
	
	public Comando(String nombre, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public Comando(String nombre, String nombreObjeto, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.nombreObjeto = nombreObjeto;
		this.tipo = tipo;
		this.nombreAfectado = "";
	}
	
	public Comando(String nombre, String nombreObjeto, String nombreAfectado, Tipo tipo) {
		super();
		this.nombre = nombre;
		this.nombreObjeto = nombreObjeto;
		this.tipo = tipo;
		this.nombreAfectado = nombreAfectado;
	}
	
	public boolean isReEscanear() {
		return reEscanear;
	}
	public void setReEscanear(boolean reEscanear) {
		this.reEscanear = reEscanear;
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
	public String getNombreAfectado() {
		return nombreAfectado;
	}
	public void setNombreAfectado(String nombreAfectado) {
		this.nombreAfectado = nombreAfectado;
	}
	
	
}
