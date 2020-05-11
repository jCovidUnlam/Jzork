package zorkPackage;

public class Obstaculo extends Objeto{

	private boolean estado;
	private String mensaje;
	private String direccion;
	private Objeto objeto;
	
	public Obstaculo() {};
		
	public Obstaculo(String mensaje, String direccion) {
		this.mensaje = mensaje;
		this.direccion = direccion;
		this.estado = true;
	}
	
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Objeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
