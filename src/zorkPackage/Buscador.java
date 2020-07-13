package zorkPackage;

import java.util.List;

public final class Buscador {

	private static Item encontrarItemInventario(Comando cmd, Mapa mapa, String msj) {
		List<Item> items = mapa.getPersonajeActual().getObjetoInventario(cmd.getPalabrasClavesPrimerObjeto());

		if (items == null || items.size() < 1) {
			msj = Mensaje.noTienesItem(mapa.getPersonajeActual().getNombre());
			return null;
		}
		
		if(items.size() > 1) {
			msj = Mensaje.objetoDuplicadoInventario(items);
			return null;
		}
	
		return items.get(0);
	}
	
	public static Consumible buscarConsumible(Comando cmd, Mapa mapa, String msj) {
		Item buscado = encontrarItemInventario(cmd, mapa, msj);
		
		if(buscado == null)
			return null;
		
		if(!(buscado instanceof Consumible)) {
			msj = "No es posible consumir este item!";
			return null;
		}
		
		return (Consumible)buscado;
	}
	
}
