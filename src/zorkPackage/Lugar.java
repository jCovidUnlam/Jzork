package zorkPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Lugar {
	
	private String nombre;
	private String descripcion;
	private List<Objeto> objetos = new ArrayList<Objeto>();

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
	public Item tomarItem(String nombreObjeto){
		//Busca el objeto en el lugar
		Item obj = this.objetos
				.stream()
			    .filter(x -> x instanceof Item)//Esto pregunta si es un item
			    .map (x -> (Item) x)//Esto castea
				.filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
				.findAny()
				.orElse(null);
		
		//Si el usuario flasheo colores y no hay objeto retorna nulo
		if(obj == null)
			return null;

		//Si hay, lo saca de la lista y lo devuelve.
		this.objetos.remove(obj);
		return obj;
	}
	
	public Obstaculo existeObstaculo(String direccion) {
		//Busca el obstaculo en el lugar
		return this.objetos.stream()
			    .filter(x -> x instanceof Obstaculo)//Esto pregunta si es un obstaculo
			    .map (x -> (Obstaculo) x)//Esto castea
			    .filter(x -> x.getDireccion().toLowerCase().equals(direccion))//Esta es la consulta real
				.findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public void cambiarDescripcion(Objeto objetoReferencia) {
		this.descripcion = this.descripcion.replace(objetoReferencia.getDescripcionMapa(), "");
	}
	
	public Objeto mostrarDescripcion(String nombreObjeto) {
		//Busca el objeto en el lugar, sino esta devuelve null
		try {
			Objeto obj = this.objetos
					.stream()
					.filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
					.findAny()
					.orElse(null);
			
			return obj;
		}catch(NullPointerException e){
			return null;
		}
		//Si, java me obliga a cachear si le devuelvo nulo, dios sabra el porque mierda no asgina nulo al objeto.

	}
	
	public NPC getNPC(String nombreNPC) {
		return this.objetos.stream()
			    .filter(x -> x instanceof NPC)//Esto pregunta si es un obstaculo
			    .map (x -> (NPC) x)//Esto castea
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreNPC))//Esta es la consulta real
				.findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public Objeto getObjeto(String nombreObjeto) {
		return this.objetos.stream()
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))//Esta es la consulta real
				.findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public void removerObjeto(Objeto objeto) {
		
		//Si el objeto tiene obstaculos los encuentra
		List<Obstaculo> obstaculos = this.objetos.stream()
			    .filter(x -> x instanceof Obstaculo)//Esto pregunta si es un obstaculo
			    .map (x -> (Obstaculo) x)//Esto castea
			    .filter(x -> x.getObjeto().getObjetoID().equals(objeto.getObjetoID()))//Esta es la consulta real
			    .collect(Collectors.toList());

		//Remueve obstaculos del objeto
		this.objetos.removeAll(obstaculos);
		//Remueve objeto finalmente
		this.objetos.remove(objeto);
		//Cambia descripcion del lugar
		cambiarDescripcion(objeto);
	}
	
}
