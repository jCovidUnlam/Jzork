package zorkPackage;

public class Objeto {
	
	private String nombre;
	private String descripcion;
	private boolean tomable;
	private boolean rompible;
	private boolean inspeccionable;
	private String descTomable;

	public Objeto(String nombre, String descripcion, boolean tomable, boolean rompible, boolean inspeccionable, String descTomable) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tomable = tomable;
		this.rompible = rompible;
		this.inspeccionable = inspeccionable;
		this.descTomable = descTomable;
	}
	
	public Objeto() {
		
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

	public boolean isTomable() {
		return tomable;
	}

	public void setTomable(boolean tomable) {
		this.tomable = tomable;
	}

	public boolean isRompible() {
		return rompible;
	}

	public void setRompible(boolean rompible) {
		this.rompible = rompible;
	}

	public boolean isInspeccionable() {
		return inspeccionable;
	}

	public void setInspeccionable(boolean inspeccionable) {
		this.inspeccionable = inspeccionable;
	}
	
	public String getDescTomable() {
		return descTomable;
	}

	public void setDescTomable(String descTomable) {
		this.descTomable = descTomable;
	}



	
}
