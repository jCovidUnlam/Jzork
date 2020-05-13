package zorkPackage;

public class Aventura {
	
	private String nombre;
	private String descripcion;
	private Mapa mapa;
	private Personaje personaje;
	
	public Aventura(String characterName, String path) {
		super();
		this.personaje = new Personaje(characterName);
		JsonReader.construirAventura(this,path);
		//Esto muestra masomenos el mapa que se genero para ver que sea el correcto
		mapa.verMapa();
		//
		GameMaster gm = new GameMaster(this);
		new Observador(gm);
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

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	
	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}
	
	
	
}
