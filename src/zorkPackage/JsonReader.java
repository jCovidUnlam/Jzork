package zorkPackage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import net.minidev.json.JSONArray;

public class JsonReader {

	public static void construirAventura (Aventura aventura, String path) {
		JSONParser parser = new JSONParser();
		try {
			Object json = parser.parse(new FileReader(path));
			String jsonString = json.toString();
			
			//Aventura
			aventura.setNombre(JsonPath.read(jsonString, "$.map.nombre"));
			aventura.setDescripcion(JsonPath.read(jsonString, "$.map.descripcion"));
			
			//Mapa
			aventura.setMapa(construirMapa(jsonString));
			
			//Objetos
			List<Objeto> objetos = new ArrayList<Objeto>();
			//Items
			objetos.addAll(agregarItems(jsonString));
			//Npcs
			objetos.addAll(agregarNPCs(jsonString));
			//Obstaculos
			objetos.addAll(agregarObstaculos(jsonString, objetos));			
			//Triggers
			List<Trigger> triggers = new ArrayList<Trigger>();
			triggers.addAll(agregarTriggers(jsonString, objetos));
			
			//Lugares
			agregarLugares(aventura.getMapa(), jsonString, objetos, triggers);
			
	
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
	
	private static void agregarLugares(Mapa mapa, String jsonString, List<Objeto> objetos, List<Trigger> triggers) {
		
		int i = 0;
		List<String> nombres = JsonPath.read(jsonString,"$.lugares[*].nombre");
		List<String> descripciones = JsonPath.read(jsonString,"$.lugares[*].descripcion");
		ArrayList<String> ids = new ArrayList<String>();
		List<String> items = JsonPath.read(jsonString, "$.lugares[*].items");
		for (String string : items) {
			ids.addAll(Arrays.asList(string.split(" ")));
		}
		//ids.addAll(JsonPath.read(jsonString, "$.lugares[*].items"));
		ids.addAll(JsonPath.read(jsonString, "$.lugares[*].obstaculos"));
		ids.addAll(JsonPath.read(jsonString, "$.lugares[*].npcs"));
//		ArrayList<String> itemIds = JsonPath.read(jsonString, "$.lugares[*].items");
//		ArrayList<String> obstaculosIds = JsonPath.read(jsonString, "$.lugares[*].obstaculos");
//		
		List<Lugar> lugares = new ArrayList<Lugar>();
		
		for (String nombre : nombres) {
			
			List<Objeto> added = new ArrayList<Objeto>();
//			List<String> auxItems = Arrays.asList(itemIds.get(i).split(" "));
//			auxItems.addAll(Arrays.asList(obstaculosIds.get(i).split(" ")));
//			
			for (Objeto item : objetos) {
				
				String id = "{" + item.getObjetoID() + "}";
				
				//Esto haria el replace en el mapa
				if(descripciones.get(i).contains(id)) 
					descripciones.set(i, descripciones.get(i).replace(id , item.getDescripcionMapa()));
				
				for (Trigger trigger : triggers) {
					if(trigger.getTriggerClassName().equals("TriggerItem"))
					{
						TriggerItem aux = (TriggerItem)trigger;
						if(aux.getObjetoID().equals(item.getObjetoID()))
							item.addTrigger(aux);
					}
				}
				
				
				if(ids.contains(item.getObjetoID()))
					added.add(item);	
			}
			
			lugares.add(new Lugar(nombre, descripciones.get(i), added));
			i++;
		}
		
		i=0;
		for (Lugar lugar : lugares) {
			int j = 0;
			List<String> xs = JsonPath.read(jsonString,"$.lugares["+ i +"].posiciones[*].x");
			List<String> ys = JsonPath.read(jsonString,"$.lugares["+ i +"].posiciones[*].y");
			List<String> zs = JsonPath.read(jsonString,"$.lugares["+ i +"].posiciones[*].z");
			
			for (String x : xs) {
				mapa.addLugar(lugar, new Posicion(Integer.parseInt(x),Integer.parseInt(ys.get(j)),Integer.parseInt(zs.get(j))));
				j++;
			}
			i++;
			
		}
	}
	
	private static List<Trigger> agregarTriggers(String jsonString, List<Objeto> objetos){
		List<Trigger> returned = new ArrayList<Trigger>();
		List<Map<String, Object>> triggerList = JsonPath.read(jsonString,"$.triggers[*]");
	
		for (Map<String, Object> trigger : triggerList) {
			
			Trigger newTrigger;
			
			switch((String)trigger.get("tipo")) {
			case "item":
				newTrigger = addNewItemTrigger(trigger, objetos);
				break;
			case "ataque":
				newTrigger = addNewAtaqueTrigger(trigger);
			default:
				newTrigger = null;
				break;
			}
			
			returned.add(newTrigger);
		}
		return returned;
	}
	
	private static TriggerItem addNewItemTrigger(Map<String,Object> trigger, List<Objeto> objetos) {
		TriggerItem returned = new TriggerItem();
		
		for (Map.Entry<String,Object> entry : trigger.entrySet()) {
			
			switch (entry.getKey()) {
			case "idObjeto":
				returned.setObjetoID((String)entry.getValue());
				break;
			case "items":
				returned.setTrigges(objetos
						.stream()
						.filter(x -> Arrays.asList(entry.getValue().toString().split(" ")).contains(x.getObjetoID()))
						.collect(Collectors.toList()));
				break;
			case "afterTriggerDesc":
				returned.setAfterTriggerDesc((String)entry.getValue());
				break;
			case "exito":
				returned.setExito(Trigger.AccionExito.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "error":
				returned.setError(Trigger.AccionError.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "after":
				returned.setAfter(Trigger.AccionFinal.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "errorTriggerDesc":
				returned.setErrorTriggerDesc((String)entry.getValue());
				break;
			default:
				break;
			}	
		}
		
		return returned;
	}
	
	private static TriggerAtaque addNewAtaqueTrigger(Map<String,Object> trigger) {
		TriggerAtaque returned = new TriggerAtaque();
		
		for (Map.Entry<String,Object> entry : trigger.entrySet()) {
			
			switch (entry.getKey()) {
			case "idObjeto":
				returned.setObjetoID((String)entry.getValue());
				break;
			case "afterTriggerDesc":
				returned.setAfterTriggerDesc((String)entry.getValue());
				break;
			case "exito":
				returned.setExito(Trigger.AccionExito.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "error":
				returned.setError(Trigger.AccionError.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "after":
				returned.setAfter(Trigger.AccionFinal.valueOf((String)entry.getValue().toString().toUpperCase()));
				break;
			case "errorTriggerDesc":
				returned.setErrorTriggerDesc((String)entry.getValue());
				break;
			default:
				break;
			}	
		}
		
		return returned;
	}
	
	private static List<Objeto> agregarItems(String jsonString){
		List<Objeto> aux = new ArrayList<Objeto>();
//		Configuration configuration = Configuration
//		        .builder()
//		        .jsonProvider(new JacksonJsonProvider())
//		        .mappingProvider(new JacksonMappingProvider())
//		        .build();
//		
//		System.out.println(Item.class);
//		
//		List<Item> allItems = JsonPath.using(configuration)
//		        .parse(jsonString)
//		        .read("$.items[*]", new TypeRef<List<Item>>() {});
//		
//		
//		
//		JSONArray items = JsonPath.read(jsonString,"$.items[*]");
//		Gson gson = new Gson();
//		
//		String ss = items.toJSONString();

//		
//	for (int i = 0; i < items.toJSONString().length(); i++) {
//			System.out.println(items.get(i));
//		Item item = gson.fromJson(items.get(i).toString(),Item.class);			
//	}
//		
//		System.out.println(items.toJSONString());
//		
		

//		for (String a : items.toJSONString()) {
//			String ss = (String)a;
//		}
		
		//List<Map<String, Object>> objetcList = new ArrayList<Map<String, Object>>();		
		List<Map<String, Object>> itemList = JsonPath.read(jsonString,"$.items[*]");
		//List<Map<String, Object>> npcList = JsonPath.read(jsonString,"$.npcs[*]");
		//objetcList.addAll(itemList);
		//objetcList.addAll(npcList);
		

		for (Map<String, Object> objeto : itemList) {
			
			Item newItem = new Item();
			
			if(objeto.get("tipo") != null)
			{
				switch((String)objeto.get("tipo")) {
				case "arma":
					newItem = new Arma();
					newItem.setDanio(Double.parseDouble(objeto.get("danio").toString()));
					break;
				}
			}
			

			for (Map.Entry<String,Object> entry : objeto.entrySet()) {
				
				switch (entry.getKey()) {
				case "id":
					newItem.setObjetoID((String)entry.getValue());
					break;
				case "nombre":
					newItem.setNombre((String)entry.getValue());
					break;
				case "descripcion":
					newItem.setDescripcion((String)entry.getValue());
					break;
				case "descripcionMapa":
					newItem.setDescripcionMapa((String)entry.getValue());
					break;
				case "tomable":
					newItem.setTomable(Boolean.parseBoolean((String)entry.getValue()));
					break;
				case "mensajeTomable":
					newItem.setMensajeTomable((String)entry.getValue());
					break;
				case "mensajeNoTomable":
					newItem.setMensajeNoTomable((String)entry.getValue());
					break;
				default:
					break;
				}	
			}
			
			aux.add(newItem);
		}
		
		return aux;
	}
		

	
	private static List<Objeto> agregarNPCs(String jsonString){
		List<Objeto> aux = new ArrayList<Objeto>();
		List<Map<String, Object>> objectList = JsonPath.read(jsonString,"$.npcs[*]");
	
		
		for (Map<String, Object> objeto : objectList) {
			
			NPC newObject = new NPC();
			
			for (Map.Entry<String,Object> entry : objeto.entrySet()) {
				
				switch (entry.getKey()) {
				case "id":
					newObject.setObjetoID((String)entry.getValue());
					break;
				case "nombre":
					newObject.setNombre((String)entry.getValue());
					break;
				case "descripcion":
					newObject.setDescripcion((String)entry.getValue());
					break;
				case "descripcionMapa":
					newObject.setDescripcionMapa((String)entry.getValue());
					break;
				case "hablar":
					newObject.setHablar((String)entry.getValue());
					break;
				case "danio":
					newObject.setDanio(Double.parseDouble(entry.getValue().toString()));
					break;
				case "salud":
					newObject.setSalud(Double.parseDouble(entry.getValue().toString()));
					break;
				default:
					break;
				}	
			}

			aux.add(newObject);
		}
		return aux;
	}
		
	
	private static List<Objeto> agregarObstaculos(String jsonString, List<Objeto> objetos){
		List<Objeto> aux = new ArrayList<Objeto>();
		List<Map<String, Object>> objectList = JsonPath.read(jsonString,"$.obstaculos[*]");
		
		for (Map<String, Object> objeto : objectList) {
			
			Obstaculo newObject = new Obstaculo();

			for (Map.Entry<String,Object> entry : objeto.entrySet()) {
				
				switch (entry.getKey()) {
				case "id":
					newObject.setObjetoID((String)entry.getValue());
					break;
				case "mensaje":
					newObject.setMensaje((String)entry.getValue());
					break;
				case "direccion":
					newObject.setDireccion((String)entry.getValue());
					break;
				case "objeto":
					newObject.setObjeto(objetos.stream().filter(x->x.getObjetoID().equals((String)entry.getValue())).findFirst().orElse(null));
					break;
				default:
					break;
				}	
			}
			
			aux.add(newObject);
		}
		return aux;
	}
	
	

}
