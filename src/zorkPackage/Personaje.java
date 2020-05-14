package zorkPackage;

import java.util.LinkedList;
import java.util.List;

public class Personaje extends Objeto{
	private String nombre;
	private List<Item> inventario;
	private Arma armaEquipada;
	
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.inventario = new LinkedList<>();
		this.setDanio(0);//Sin arma no pega
	}
	
	public Personaje(String nombre, List<Item> list) {
		super();
		this.nombre = nombre;
		this.inventario = list;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Item> getInventario() {
		return this.inventario;
	}
	
	public void addObjeto(Item obj) {
		this.inventario.add(obj);
	}
	
	public Arma getArmaEquipada() {
		return armaEquipada;
	}

	public void setArmaEquipada(Arma armaEquipada) {
		this.armaEquipada = armaEquipada;
	}

	
	@Override
	public void atacar(Objeto atacado) {
		this.recibirAtaque(this.getDanio() + this.armaEquipada.getDanio());
	}
	
	public Arma equiparArma(String nombreArma) {
		if(inventario.size() == 0)
			return null;
		
		//Busco palo
		Arma arma = this.inventario.stream()
			    .filter(x -> x instanceof Arma)//Esto pregunta si es del tipo q le pongo
			    .map (x -> (Arma) x)//Esto castea
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreArma))
			    .findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	
		//Sino hay palo retorno
		if(arma == null)
			return null;
		
		//Si hay palo equipo
		this.armaEquipada = arma;
		
		return arma;
	}
	
	public Item getObjetoInventario(String nombreObjeto) {
		return this.inventario.stream()
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))//Esta es la consulta real
				.findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public void removerDeInventario(Item removido) {
		this.inventario.remove(removido);
	}
	
	
}
