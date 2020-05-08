package zorkPackage;

import java.util.ArrayList;
import java.util.List;

public class Lugar {
	
	private String nombre;
	private String descripcion;
	private List<Objeto> objetos;
	private List<Obstaculo> obstaculos;
	
	public Lugar(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Lugar(String nombre, String descripcion, List<Objeto> objetos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.objetos = objetos;

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
	public List<Objeto> getObjetos() {
		return objetos;
	}
	public void setObjetos(ArrayList<Objeto> objetos) {
		this.objetos = objetos;
	}

	/**
	 * Esta funcion busca el objeto por su nombre en el lugar.
	 * @param nombreObjeto representa el nombre del objeto que se busca.
	 * @return El objeto buscado o nulo en caso de no encontrarlo.
	 */
	public Objeto tomarObjeto(String nombreObjeto){
		//Busca el objeto en el lugar
		Objeto obj = this.objetos
				.stream().filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
				.findFirst()
				.orElse(null);
		//Si el usuario flasheo colores y no hay objeto retorna nulo
		if(obj == null)
			return null;
		//Si hay, lo saca de la lista y lo devuelve.
		this.objetos.remove(obj);
		return obj;
	}
	
	
}
