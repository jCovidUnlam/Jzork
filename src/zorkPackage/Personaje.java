package zorkPackage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Personaje extends Objeto{
	private String nombre;
	private List<Item> inventario;
	private Posicion posicionActual;
	private Arma armaEquipada;
	private Boolean esPrincipal;
	
	public Personaje(String nombre, Boolean esPrincipal) {
		super();
		this.nombre = nombre;
		this.inventario = new LinkedList<>();
		this.posicionActual = new Posicion(0,0,0);
		this.setDanio(5);
		this.setSalud(100);
		this.esPrincipal = esPrincipal;
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
	
	public Boolean getEsPrincipal() {
		return esPrincipal;
	}

	public void setEsPrincipal(Boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	
	public void ir(Posicion pos) {
		this.posicionActual.setX(this.posicionActual.getX() + pos.getX());
		this.posicionActual.setY(this.posicionActual.getY() + pos.getY());
		this.posicionActual.setZ(this.posicionActual.getZ() + pos.getZ());
	}
		
	public Arma desequiparArma(Arma arma) {
		this.setDanio(this.getDanio() - this.armaEquipada.getDanio());
		this.armaEquipada = null;
		return arma;
	}
	
	public List<Item> equiparArma(List<String> keyWords) {

		List<Item> items = getObjetoInventario(keyWords);
		if(items == null || items.size() < 1 || !(items.get(0) instanceof Arma))
			return null;
		
		if(items.size() > 1)
			return items;
		
		Arma arma = (Arma)items.get(0);
		
		if(this.armaEquipada != null)
			desequiparArma(arma);
		
		this.setDanio(5 + arma.getDanio());
		this.armaEquipada = arma;
		
		return items;
	}
	
	public List<Item> getObjetoInventario(List<String> keyWords) {
		
		if(this.inventario.size() == 0)
			return null;
		
		List<Item> objetos = this.inventario;
		List<Item> resultado = new ArrayList<>(objetos);
		
		int i = 0;
		do {
			
			for (Objeto objeto : objetos) {
				if(objeto.getKeyWordsNombre().size() <= i || !objeto.getKeyWordsNombre().get(i).equals(keyWords.get(i)))
					resultado.remove(objeto);
			}
			
			objetos = new ArrayList<>(resultado);

			i++;
			
		}while(i < keyWords.size() && resultado.size() > 1);

		
		if(resultado.size() > 1) {
			for (Objeto objeto : objetos)  {
				if(objeto.getKeyWordsNombre().size() > keyWords.size())
					resultado.remove(objeto);
			}
		}
		
		if(resultado.size() == 0)
		{
			objetos = this.inventario;
			resultado = new ArrayList<>(objetos);
			i = 0;
			
			do {
				
				for (Objeto objeto : objetos) {
					if(objeto.getKeyWordsNombre().size() <= i || !objeto.getKeyWordsNombre().contains(keyWords.get(i)))
						resultado.remove(objeto);
				}
				
				objetos = new ArrayList<>(resultado);

				i++;
				
			}while(i < keyWords.size() && resultado.size() > 1);
		}
		
		return resultado;
	}
	
	public void removerDeInventario(Item removido) {
		this.inventario.remove(removido);
	}
	
	
}
