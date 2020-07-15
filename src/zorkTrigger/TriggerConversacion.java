package zorkTrigger;

import java.util.ArrayList;
import java.util.List;

public class TriggerConversacion extends Trigger{

	private String titulo;
	private List<OpcionDialogo> opciones;

	
	public TriggerConversacion() {
		super();
		this.opciones = new ArrayList<OpcionDialogo>();
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<OpcionDialogo> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OpcionDialogo> opciones) {
		this.opciones = opciones;
	}

	public void addOpcion(OpcionDialogo opt) {
		this.opciones.add(opt);
	}
}
