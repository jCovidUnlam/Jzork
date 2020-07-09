package zorkPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Lugar {

	private String nombre;
	private String descripcion;
	private List<Objeto> objetos;
	private String mensajeLimite;
	
	public String getMensajeLimite() {
		return mensajeLimite;
	}

	public void setMensajeLimite(String mensajeLimite) {
		this.mensajeLimite = mensajeLimite;
	}

	public Lugar() {
		objetos = new ArrayList<Objeto>();
	};
	
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
	
	public Objeto getObjeto(String nombreObjeto) {
		try {
			Objeto obj = this.objetos.stream()
					.filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
					.findAny()
					.orElse(null);
			
			return obj;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Item getItem(String nombreObjeto) {
		Objeto obj = getObjeto(nombreObjeto);
		if (obj == null || !(obj instanceof Item))
			return null;
		
		return (Item)obj;
	}
	
	public NPC getNPC(String nombreObjeto) {
		Objeto obj = getObjeto(nombreObjeto);
		if (obj == null || !(obj instanceof NPC))
			return null;
		
		return (NPC)obj;
	}

	public Obstaculo getObstaculo(String direccion) {
		return this.objetos.stream().filter(x -> x instanceof Obstaculo)
				.map(x -> (Obstaculo) x)
				.filter(x -> x.getDireccion().toLowerCase().equals(direccion))
				.findAny()
				.orElse(null);
	}

	public void removerObjeto(Objeto objeto) {
		
		List<Obstaculo> obstaculos = null;
		
		try {
			 obstaculos = this.objetos
						.stream()
						.filter(x -> x instanceof Obstaculo)
						.map(x -> (Obstaculo) x)
						.filter(x -> x.getObjeto().getObjetoID().equals(objeto.getObjetoID()))
						.collect(Collectors.toList());
		}
		catch(Exception e)
		{
			obstaculos = null;
		}
		
		if(obstaculos != null)
			this.objetos.removeAll(obstaculos);
		
		if(objeto.getDescripcionMapa() != null)
			cambiarDescripcion(objeto);
	
		this.objetos.remove(objeto);
	}
	
	private void cambiarDescripcion(Objeto objetoReferencia) {
		this.descripcion = this.descripcion.replace(objetoReferencia.getDescripcionMapa(), "");
	}

	public void agregarObjeto(Objeto objeto) {
		this.objetos.add(objeto);
		this.descripcion += objeto.getDescripcionMapa();
	}
	
	public Contenedor getContenedor(String nombreObjeto) {
		Objeto obj = getObjeto(nombreObjeto);
		if (obj == null || !(obj instanceof Contenedor))
			return null;
		
		return (Contenedor)obj;
	}
	
	public String romperObjeto(Contenedor objeto) {
		
		for (Item item : objeto.getContenido()) {
			this.objetos.add(item);
		}
		
		removerObjeto(objeto);
		return objeto.getDescRompible();
	}

}
