package zorkPackage;

public class Consumible{

	public enum TipoConsumible{
		
		CURACION,
		MEZCLA,
		
	}
	
	private TipoConsumible tipo;


	public TipoConsumible getTipo() {
		return tipo;
	}


	public void setTipo(TipoConsumible tipo) {
		this.tipo = tipo;
	}

	public Consumible(TipoConsumible tipo) {
		super();
		this.tipo = tipo;
	}
	
	

}
