package zorkPackage;

public class PocionSalud extends Consumible{

	private double puntosSaludRecuperados;
	
	public double getPuntosSaludRecuperados() {
		return puntosSaludRecuperados;
	}

	public void setPuntosSaludRecuperados(double puntosSaludRecuperados) {
		this.puntosSaludRecuperados = puntosSaludRecuperados;
	}

	@Override
	public String consumir(Personaje pj) {
		pj.setSalud(pj.getSalud() + this.puntosSaludRecuperados);
		pj.removerDeInventario(this);
		return Mensaje.consumirPocionSalud(this.puntosSaludRecuperados);
	}
}
