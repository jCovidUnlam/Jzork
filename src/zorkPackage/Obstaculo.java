package zorkPackage;

public class Obstaculo extends Objeto{

	private String mensaje;
	private String direccion;
		
	public Obstaculo(int objetoID, String nombre, String descripcion, String mensaje, String direccion) {
		super(objetoID, nombre, descripcion);
		this.mensaje = mensaje;
		this.direccion = direccion;
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
