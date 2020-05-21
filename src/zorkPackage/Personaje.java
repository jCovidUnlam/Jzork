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
		this.setDanio(5);
		this.setSalud(100);
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
	
	public void ir(Posicion pos) {
		this.posicionActual.setX(this.posicionActual.getX() + pos.getX());
		this.posicionActual.setY(this.posicionActual.getY() + pos.getY());
		this.posicionActual.setZ(this.posicionActual.getZ() + pos.getZ());
	}
	
	public Arma equiparArma(String nombreArma) {
		if(inventario.size() == 0)
			return null;
		
		Arma arma = this.inventario.stream()
			    .filter(x -> x instanceof Arma)
			    .map (x -> (Arma) x)
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreArma))
			    .findAny()
				.orElse(null);
		
		if(arma == null)
			return null;
		
		if(this.armaEquipada != null)
			desequiparArma(arma);
		
		this.setDanio(5 + arma.getDanio());
		this.armaEquipada = arma;
		
		return arma;
	}
	
	public Arma desequiparArma(Arma arma) {
		this.setDanio(this.getDanio() - this.armaEquipada.getDanio());
		this.armaEquipada = null;
		return arma;
	}
	
	public Item getObjetoInventario(String nombreObjeto) {
		return this.inventario.stream()
			    .filter(x -> x.getNombre().toLowerCase().equals(nombreObjeto))
				.findAny()
				.orElse(null);
	}
	
	public void removerDeInventario(Item removido) {
		this.inventario.remove(removido);
	}
	
	
}
