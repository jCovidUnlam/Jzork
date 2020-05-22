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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((nombreAfectado == null) ? 0 : nombreAfectado.hashCode());
		result = prime * result + ((nombreObjeto == null) ? 0 : nombreObjeto.hashCode());
		result = prime * result + (reEscanear ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comando other = (Comando) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (nombreAfectado == null) {
			if (other.nombreAfectado != null)
				return false;
		} else if (!nombreAfectado.equals(other.nombreAfectado))
			return false;
		if (nombreObjeto == null) {
			if (other.nombreObjeto != null)
				return false;
		} else if (!nombreObjeto.equals(other.nombreObjeto))
			return false;
		if (reEscanear != other.reEscanear)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
	
	
}
