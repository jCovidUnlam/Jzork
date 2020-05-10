package zorkPackage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;

public class JsonReader {

	public static void construirAventura (Aventura aventura, String path) {
		JSONParser parser = new JSONParser();
		try {
			Object json = parser.parse(new FileReader(path));
			String jsonString = json.toString();
			
			//Aventura
			aventura.setNombre(JsonPath.read(jsonString, "$.map.Nombre"));
			aventura.setDescripcion(JsonPath.read(jsonString, "$.map.Descripcion"));
			
			//Mapa
			aventura.setMapa(construirMapa(jsonString));
			
			//Items
			List<Item> items = agregarItems(jsonString);
			
			//Lugares
			agregarLugares(aventura.getMapa(), jsonString, items);
			

			List<Map<String, Object>> lugares = JsonPath.read(jsonString,"$.lugares.*");
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Mapa construirMapa(String jsonString) {
		int x = Integer.parseInt(JsonPath.read(jsonString, "$.map.x"));
		int y = Integer.parseInt(JsonPath.read(jsonString, "$.map.y"));
		int z = Integer.parseInt(JsonPath.read(jsonString, "$.map.z"));
		
		return new Mapa(new Posicion(x, y, z));
	}
	
	private static void agregarLugares(Mapa mapa, String jsonString, List<Item> items) {
		
		int i = 0;
		List<String> nombres = JsonPath.read(jsonString,"$.lugares[*].Nombre");
		List<String> descripciones = JsonPath.read(jsonString,"$.lugares[*].Descripcion");
		List<String> itemIds = JsonPath.read(jsonString, "$.lugares[*].items");
		
		List<Lugar> lugares = new ArrayList<Lugar>();
		
		for (String nombre : nombres) {
			
			List<Item> added = new ArrayList<Item>();
			List<String> auxItems = Arrays.asList(itemIds.get(i).split(" "));
			
			for (Item item : items) {
				
				String id = "{" + item.getObjetoID() + "}";
				
				//Esto haria el replace en el mapa
				if(descripciones.get(i).contains(id)) 
					descripciones.set(i, descripciones.get(i).replace(id , item.getDescripcionMapa()));
				
				if(auxItems.contains(item.getObjetoID()))
					added.add(item);
				
			}
			
			lugares.add(new Lugar(nombre, descripciones.get(i), added));
			
			
			i++;
		}
		
		i=0;
		for (Lugar lugar : lugares) {
			int j = 0;
			List<String> xs = JsonPath.read(jsonString,"$.lugares["+ i +"].Posiciones[*].x");
			List<String> ys = JsonPath.read(jsonString,"$.lugares["+ i +"].Posiciones[*].y");
			List<String> zs = JsonPath.read(jsonString,"$.lugares["+ i +"].Posiciones[*].z");
			
			for (String x : xs) {
				mapa.addLugar(lugar, new Posicion(Integer.parseInt(x),Integer.parseInt(ys.get(j)),Integer.parseInt(zs.get(j))));
				j++;
			}
			i++;
			
			
			
		}
	}
	
	private static List<Item> agregarItems(String jsonString){
		List<Item> aux = new ArrayList<Item>();

		List<Map<String, Object>> itemList = JsonPath.read(jsonString,"$.items[*]");
		
		for (Map<String, Object> item : itemList) {
			
			Item newItem = new Item();

			for (Map.Entry<String,Object> entry : item.entrySet()) {
				
				switch (entry.getKey()) {
				case "Id":
					newItem.setObjetoID((String)entry.getValue());
					break;
				case "Nombre":
					newItem.setNombre((String)entry.getValue());
					break;
				case "Descripcion":
					newItem.setDescripcion((String)entry.getValue());
					break;
				case "DescripcionMapa":
					newItem.setDescripcionMapa((String)entry.getValue());
					break;
				case "Tomable":
					newItem.setTomable(Boolean.parseBoolean((String)entry.getValue()));
					break;
				case "MensajeTomable":
					newItem.setDescTomable((String)entry.getValue());
					break;
				case "MensajeNoTomable":
					newItem.setDescNoTomable((String)entry.getValue());
					break;
				default:
					break;
				}	
			}
			
			aux.add(newItem);
		}
		
		return aux;
//		List<String> ids = JsonPath.read(jsonString,"$.items[*].id");
//		List<String> nombres = JsonPath.read(jsonString,"$.items[*].nombre");
//		List<String> descripciones = JsonPath.read(jsonString,"$.items[*].descripcion");
//		List<String> descripcionesMapa = JsonPath.read(jsonString,"$.items[*].descripcionMapa");
//		List<String> tomable = JsonPath.read(jsonString,"$.items[*].tomable");
//		List<String> tomableDesc = JsonPath.read(jsonString,"$.items[*].descripcionTomable");
		

	}
	
	
	

}
