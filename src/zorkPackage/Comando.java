package zorkPackage;

import java.util.List;

import zorkEnum.EnumComando;
import zorkTrigger.Trigger;
import zorkTrigger.TriggerConversacion;

public class Comando {
	
	private String verbo;
	private EnumComando tipo;
	private boolean reEscanear;
	private int cantidadObjetos;
	private List<String> palabrasClavesPrimerObjeto;
	private List<String> palabrasClavesSegundoObjeto;
	private TriggerConversacion trigger;
	
	public Comando() {
		super();
	}

	public Comando(EnumComando tipo) {
		super();
		this.tipo = tipo;
	}
	
	public Comando(String verbo, EnumComando tipo) {
		super();
		this.verbo = verbo;
		this.tipo = tipo;
	}
	
	public Comando(String verbo, EnumComando tipo, int cantidadObjetos) {
		super();
		this.verbo = verbo;
		this.tipo = tipo;
		this.cantidadObjetos = cantidadObjetos;
	}
	
	public Comando(String verbo, EnumComando tipo, List<String> palabrasClavesPrimerObjeto) {
		super();
		this.verbo = verbo;
		this.tipo = tipo;
		this.cantidadObjetos = 1;
		this.palabrasClavesPrimerObjeto = palabrasClavesPrimerObjeto;
	}
	
	public Comando(String verbo, EnumComando tipo, List<String> palabrasClavesPrimerObjeto,
			List<String> palabrasClavesSegundoObjeto) {
		super();
		this.verbo = verbo;
		this.tipo = tipo;
		this.cantidadObjetos = 2;
		this.palabrasClavesPrimerObjeto = palabrasClavesPrimerObjeto;
		this.palabrasClavesSegundoObjeto = palabrasClavesSegundoObjeto;
	}
	

	public boolean isReEscanear() {
		return reEscanear;
	}
	public void setReEscanear(boolean reEscanear) {
		this.reEscanear = reEscanear;
	}
	public String getVerbo() {
		return verbo;
	}
	public void setVerbo(String nombre) {
		this.verbo = nombre;
	}

	public EnumComando getTipo() {
		return tipo;
	}
	public void setTipo(EnumComando tipo) {
		this.tipo = tipo;
	}
	public List<String> getPalabrasClavesPrimerObjeto() {
		return palabrasClavesPrimerObjeto;
	}

	public void setPalabrasClavesPrimerObjeto(List<String> palabrasClavesPrimerObjeto) {
		this.palabrasClavesPrimerObjeto = palabrasClavesPrimerObjeto;
	}
	
	public List<String> getPalabrasClavesSegundoObjeto() {
		return palabrasClavesSegundoObjeto;
	}

	public void setPalabrasClavesSegundoObjeto(List<String> palabrasClavesSegundoObjeto) {
		this.palabrasClavesSegundoObjeto = palabrasClavesSegundoObjeto;
	}

	public int getCantidadObjetos() {
		return cantidadObjetos;
	}

	public void setCantidadObjetos(int cantidadObjetos) {
		this.cantidadObjetos = cantidadObjetos;
	}
	
	public TriggerConversacion getTrigger() {
		return trigger;
	}

	public void setTrigger(TriggerConversacion trigger) {
		this.trigger = trigger;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidadObjetos;
		result = prime * result + ((palabrasClavesPrimerObjeto == null) ? 0 : palabrasClavesPrimerObjeto.hashCode());
		result = prime * result + ((palabrasClavesSegundoObjeto == null) ? 0 : palabrasClavesSegundoObjeto.hashCode());
		result = prime * result + (reEscanear ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((verbo == null) ? 0 : verbo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comando other = (Comando) obj;
		if (cantidadObjetos != other.cantidadObjetos)
			return false;
		if (palabrasClavesPrimerObjeto == null) {
			if (other.palabrasClavesPrimerObjeto != null)
				return false;
		} else if (!palabrasClavesPrimerObjeto.equals(other.palabrasClavesPrimerObjeto))
			return false;
		if (palabrasClavesSegundoObjeto == null) {
			if (other.palabrasClavesSegundoObjeto != null)
				return false;
		} else if (!palabrasClavesSegundoObjeto.equals(other.palabrasClavesSegundoObjeto))
			return false;
		if (reEscanear != other.reEscanear)
			return false;
		if (tipo != other.tipo)
			return false;
		if (verbo == null) {
			if (other.verbo != null)
				return false;
		} else if (!verbo.equals(other.verbo))
			return false;
		return true;
	}

	
	
	
}
