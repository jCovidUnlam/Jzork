package zorkTrigger;

import java.util.ArrayList;
import java.util.List;

public class TriggerConversacion extends Trigger{

	private String mensajeInicial;
	private List<OpcionDialogo> opciones;
	private String mensajeSalida;
	private String nombreNpc;

	public TriggerConversacion() {
		super();
		this.opciones = new ArrayList<OpcionDialogo>();
	}
	
	public String getMensajeInicial() {
		return mensajeInicial;
	}

	public void setMensajeInicial(String mensajeInicial) {
		this.mensajeInicial = mensajeInicial;
	}

	public String getMensajeSalida() {
		return mensajeSalida;
	}

	public void setMensajeSalida(String mensajeSalida) {
		this.mensajeSalida = mensajeSalida;
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
	
	public String getNombreNpc() {
		return nombreNpc;
	}

	public void setNombreNpc(String nombreNpc) {
		this.nombreNpc = nombreNpc;
	}
}
