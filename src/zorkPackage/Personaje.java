package zorkPackage;

import java.util.LinkedList;
import java.util.List;

public class Personaje extends Objeto{
	private String nombre;
	private List<Item> inventario;
	private Posicion posicionActual;
	private Arma armaEquipada;
	
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.inventario = new LinkedList<>();
		this.posicionActual = new Posicion(0,0,0);
		this.setDanio(5);//Sin arma pega esto
		this.setSalud(100);
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
	
	public Posicion getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(Posicion posicionActual) {
		this.posicionActual = posicionActual;
	}
	
	public void irNorte() {
		this.posicionActual.setY(this.posicionActual.getY() + 1);
	}
	
	public void irSur() {
		this.posicionActual.setY(this.posicionActual.getY() - 1);
	}
	
	public void irEste() {
		this.posicionActual.setX(this.posicionActual.getX() + 1);
	}
	
	public void irOeste() {
		this.posicionActual.setX(this.posicionActual.getX() - 1);
	}
	
	public void irAbajo() {
		this.posicionActual.setZ(this.posicionActual.getZ() - 1);
	}
	
	public void irArriba() {
		this.posicionActual.setZ(this.posicionActual.getZ() + 1);
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
	
		//Sino hay palo retorno nulo y el GM devuelve mensaje
		if(arma == null)
			return null;
		
		//Si hay palo equipo y seteo danio
		this.setDanio(this.getDanio() + arma.getDanio());
		this.armaEquipada = arma;
		
		return arma;
	}
	
	public Arma desequiparArma(Arma arma) {
		//Esto puede crecer mucho, supongamos que tengo mas de un arma o un escudo...
		//Por ahora sirve asi
		this.setDanio(this.getDanio() - this.armaEquipada.getDanio());
		this.armaEquipada = null;
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
