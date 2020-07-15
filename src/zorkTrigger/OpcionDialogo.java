package zorkTrigger;

import java.util.ArrayList;
import java.util.List;

public class OpcionDialogo {
	
	private int numero;
	private String texto;
	private String respuesta;
	private List<OpcionDialogo> opciones;
	
	public OpcionDialogo() {
		super();
		this.opciones = new ArrayList<OpcionDialogo>();
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public List<OpcionDialogo> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<OpcionDialogo> opciones) {
		this.opciones = opciones;
	}
	
	
	
}
