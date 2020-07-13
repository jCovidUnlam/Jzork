package zorkPackage;

import java.util.List;

public final class Buscador {

	public static Objeto buscarObjetoLugar(Comando cmd, Lugar lugarActual, String msj) {
		
		List<Objeto> objetos = lugarActual.getObjeto(cmd.getPalabrasClavesPrimerObjeto());
		if (objetos == null || objetos.size() == 0) {
			msj = Mensaje.noExisteObjeto();
			return null;
		}
		
		if(objetos.size() > 1) {
			msj = Mensaje.objetoDuplicado(objetos);
			return null;
		}
		
		return objetos.get(0);
	}
	
	private static Item buscarItemInventario(Comando cmd, Personaje pj, String msj) {
		List<Item> items = pj.getObjetoInventario(cmd.getPalabrasClavesPrimerObjeto());

		if (items == null || items.size() < 1) {
			msj = Mensaje.noTienesItem(pj.getNombre());
			return null;
		}
		
		if(items.size() > 1) {
			msj = Mensaje.objetoDuplicadoInventario(items);
			return null;
		}
	
		return items.get(0);
	}
	
	public static Consumible buscarConsumible(Comando cmd, Personaje pj, String msj) {
		Item buscado = buscarItemInventario(cmd, pj, msj);
		
		if(buscado == null)
			return null;
		
		if(!(buscado instanceof Consumible)) {
			msj = "No es posible consumir este item!";
			return null;
		}
		
		return (Consumible)buscado;
	}
	
    
	
}
