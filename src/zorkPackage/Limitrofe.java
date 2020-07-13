package zorkPackage;

import zorkEnum.EnumDireccion;

public class Limitrofe {

	private EnumDireccion direccion;
	private Lugar lugar;
	
	public Limitrofe(EnumDireccion direccion, Lugar lugar) {
		super();
		this.direccion = direccion;
		this.lugar = lugar;
	}
	
	public EnumDireccion getDireccion() {
		return direccion;
	}

	public Lugar getLugar() {
		return lugar;
	}
	
}
