package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public class Lugar {
	
	private String nombre;
	private String descripcion;
	private List<Item> items = new ArrayList<Item>();
	private List<Obstaculo> obstaculos = new ArrayList<Obstaculo>();

	public Lugar(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Lugar(String nombre, String descripcion, List<Item> items) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.items = items;

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
	public List<Item> getObjetos() {
		return items;
	}
	public void setObjetos(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * Esta funcion busca el objeto por su nombre en el lugar.
	 * @param nombreObjeto representa el nombre del objeto que se busca.
	 * @return El objeto buscado o nulo en caso de no encontrarlo.
	 */
	public Item tomarItem(String nombreObjeto){
		//Busca el objeto en el lugar
		Item obj = this.items
				.stream().filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
				.findFirst()
				.orElse(null);
		//Si el usuario flasheo colores y no hay objeto retorna nulo
		if(obj == null)
			return null;
		//Si hay, lo saca de la lista y lo devuelve.
		this.items.remove(obj);
		return obj;
	}
	
	public Obstaculo existeObstaculo(String direccion) {
		//Busca el obstaculo en el lugar
		return this.obstaculos
				.stream().filter(x -> x.getDireccion().toLowerCase().equals(direccion))
				.findFirst()
				.orElse(null);
	}
	
	public void cambiarDescripcion(Item item) {
		this.descripcion = this.descripcion.replace(item.getDescripcionMapa(), "");
	}
	
	
}
