package zorkPackage;

import zorkEnum.EnumConsumible;

public abstract class Consumible extends Item{

	private EnumConsumible tipo;

	public EnumConsumible getTipo() {
		return tipo;
	}

	public void setTipo(EnumConsumible tipo) {
		this.tipo = tipo;
	}

	public abstract String consumir(Personaje pj);
	

}
