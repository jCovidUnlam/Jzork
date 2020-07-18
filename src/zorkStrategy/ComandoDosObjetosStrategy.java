package zorkStrategy;

import java.util.List;

import zorkPackage.Comando;
import zorkPackage.Item;
import zorkPackage.Mapa;
import zorkPackage.Mensaje;
import zorkPackage.Objeto;
import zorkTrigger.TriggerMaster;

public class ComandoDosObjetosStrategy implements ComandoStrategy{

	private Mapa mapa;

	public ComandoDosObjetosStrategy(Mapa mapa) {
		super();
		this.mapa = mapa;
	}
	
	@Override
	public String ejectuar(Comando cmd) {
		
		String resultado = "";
		
		switch (cmd.getTipo()) {
		case USAR:
			resultado = ejecutarTriggerItem(cmd); 
			break;
		default:
			break;

		}
		
		return resultado;
	}
	
	public String ejecutarTriggerItem(Comando comando) {


		List<Item> item = mapa.getPersonajeActual().getObjetoInventario(comando.getPalabrasClavesPrimerObjeto());
		
		if (item == null || item.size() == 0)
			return Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());
		
		if(item.size() > 1)
			return Mensaje.objetoDuplicadoInventario(item);

		List<Objeto> afectado = mapa.getLugarActual().getObjeto(comando.getPalabrasClavesSegundoObjeto());
		
		if (afectado == null || afectado.size() == 0)
			return Mensaje.noExisteObjeto();
		
		if(afectado.size() > 1)
			return Mensaje.objetoDuplicado(afectado);

		return TriggerMaster.ejecutarTriggerItem(mapa, afectado.get(0), item.get(0));
	}
	
}
