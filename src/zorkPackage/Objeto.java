package zorkPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import zorkTrigger.Trigger;
import zorkTrigger.TriggerAtaque;
import zorkTrigger.TriggerItem;
import zorkTrigger.TriggerLugar;

public class Objeto {
	
	private String id;
	private String nombre;
	private List<String> keyWordsNombre;
	private String descripcion;
	private String descripcionMapa;
	private List<Trigger> triggers;
	private double salud;
	private double danio;
	private boolean muerto;
	private boolean matable;
	private boolean rompible;
	private String mensajeRompible;
	
	public Objeto(){
		this.triggers = new ArrayList<Trigger>();
		this.salud = 1;
		this.danio = 0;
		this.muerto = false;
		this.matable = false;
		this.rompible = false;
	};
		
	public Objeto(String objetoID, String nombre, String descripcion, String descripcionMapa) {
		super();
		this.id = objetoID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descripcionMapa = descripcionMapa;
		this.triggers = new ArrayList<Trigger>();
	}
	
	public void addTrigger(Trigger trigger) {
		this.triggers.add(trigger);
	}

	public String getObjetoID() {
		return id;
	}

	public void setObjetoID(String objetoID) {
		id = objetoID;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		ArrayList<String> cadena = new ArrayList<String>(Arrays.asList(nombre.toLowerCase().split(" ")));
		Lexico.removerAtributos(cadena);
		Lexico.removerErrores(cadena);
		this.keyWordsNombre = cadena;
	}
	
	public List<String> getKeyWordsNombre() {
		return keyWordsNombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcionMapa() {
		return descripcionMapa;
	}

	public void setDescripcionMapa(String descripcionMapa) {
		this.descripcionMapa = descripcionMapa;
	}
	
	public double getSalud() {
		return salud;
	}
	public boolean isMuerto() {
		return muerto;
	}
	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}

	public void setSalud(double salud) {
		this.salud = salud;
	}
	
	public void restarSalud(double danio) {
		this.salud -= danio;
		if(this.salud <= 0)
			this.setMuerto(true);
	}

	public double getDanio() {
		return danio;
	}

	public void setDanio(double danio) {
		this.danio = danio;
	}
	
	public boolean isMatable() {
		return matable;
	}

	public void setMatable(boolean matable) {
		this.matable = matable;
	}

	public void atacar(Objeto atacado) {
		if(atacado.getSalud() != 0)
			atacado.recibirAtaque(this.danio);
	}
	
	public void recibirAtaque(double danio) {
		this.restarSalud(danio);
	}
	
	public boolean isRompible() {
		return rompible;
	}

	public void setRompible(boolean rompible) {
		this.rompible = rompible;
	}
	
	public String getMensajeRompible() {
		return mensajeRompible;
	}

	public void setMensajeRompible(String mensajeRompible) {
		this.mensajeRompible = mensajeRompible;
	}

	public TriggerAtaque getTriggerAtaque() {
		//Lo primero que hace es buscar si tiene un trigger de ataque
		return this.triggers.stream()
			    .filter(x -> x instanceof TriggerAtaque)//Esto pregunta para q filter por tipo
			    .map (x -> (TriggerAtaque) x)//Esto castea
			    .findAny()// Esto devuelve si existe al menos un trigger. Por favor que no haya mas de 1 porque me vuelvo puto
				.orElse(null);//Sino encuentra, retorna null.
	}

	public TriggerItem getTriggerItem() {
		//Si tiene mas de un triggerItem, hay q pensar como separarlo............ ni en pedo.
		return this.triggers.stream()
			    .filter(x -> x instanceof TriggerItem)//Esto pregunta si es un obstaculo
			    .map (x -> (TriggerItem) x)//Esto castea
			    .findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
	
	public TriggerLugar getTriggerMovimiento() {
		//Si tiene mas de un triggerItem, hay q pensar como separarlo............ ni en pedo.
		return this.triggers.stream()
			    .filter(x -> x instanceof TriggerLugar)//Esto pregunta si es un obstaculo
			    .map (x -> (TriggerLugar) x)//Esto castea
			    .findAny()// Esto devuelve si existe al menos un obstaculo. Por favor que no haya mas de 1 en una misma direccion jaja
				.orElse(null);//Sino encuentra, retorna null.
	}
}
