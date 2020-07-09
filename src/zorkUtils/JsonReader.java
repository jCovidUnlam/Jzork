package zorkUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;

import zorkEnum.EnumDireccion;
import zorkPackage.Arma;

import zorkPackage.Item;
import zorkPackage.Lugar;
import zorkPackage.Mapa;
import zorkPackage.NPC;
import zorkPackage.Objeto;
import zorkPackage.Obstaculo;
import zorkPackage.Posicion;
import zorkTrigger.Trigger;
import zorkTrigger.TriggerAtaque;
import zorkTrigger.TriggerItem;
import zorkTrigger.TriggerLugar;


public final class JsonReader {

	public static Mapa construirAventura(Mapa mapa, String path) {
		
		JSONParser parser = new JSONParser();
		try {
			Object json = parser.parse(new FileReader(path));
			String jsonString = json.toString();
			
			//Mapa
			mapa = construirMapa(mapa, jsonString);
			
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
			agregarLugares(mapa, jsonString, objetos, triggers);
		
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapa;
	}
	
	private static Mapa construirMapa(Mapa mapa, String jsonString) {

		mapa.setNombre(JsonPath.read(jsonString, "$.map.nombre"));
		mapa.setDescripcion(JsonPath.read(jsonString, "$.map.descripcion"));
		
		//Set tamanio del mapa
		int xSize = Integer.parseInt(JsonPath.read(jsonString, "$.map.xSize"));
		int ySize = Integer.parseInt(JsonPath.read(jsonString, "$.map.ySize"));
		int zSize = Integer.parseInt(JsonPath.read(jsonString, "$.map.zSize"));
		mapa.setTamanio(xSize, ySize, zSize);
		//
		
		//Set posicion actual
		int x = Integer.parseInt(JsonPath.read(jsonString, "$.map.x"));
		int y = Integer.parseInt(JsonPath.read(jsonString, "$.map.y"));
		int z = Integer.parseInt(JsonPath.read(jsonString, "$.map.z"));
		mapa.setPosicionActual(new Posicion(x,y,z));
		//
		
		return mapa;
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
				break;
			case "movimiento":
				newTrigger = addNewLugarTrigger(trigger);
				break;
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
				returned.setTriggerObjects(objetos
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
			case "exitoTriggerDesc":
				returned.setExitoTriggerDesc((String)entry.getValue());
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
			case "exitoTriggerDesc":
				returned.setExitoTriggerDesc((String)entry.getValue());
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
			case "danioLimite":
				returned.setDanioLimite(Double.parseDouble(entry.getValue().toString()));
			default:
				break;
			}	
		}
		
		return returned;
	}

	private static TriggerLugar addNewLugarTrigger(Map<String,Object> trigger) {
		TriggerLugar returned = new TriggerLugar();
		
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
			case "exitoTriggerDesc":
				returned.setExitoTriggerDesc((String)entry.getValue());
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
			case "movimiento":
				returned.setDireccion(EnumDireccion.valueOf((String)entry.getValue().toString().toUpperCase()));
			default:
				break;
			}	
		}
		
		return returned;
	}
	
	private static void agregarLugares(Mapa mapa, String jsonString, List<Objeto> objetos, List<Trigger> triggers) {
		
		//Listo todo lo referente a todos los lugares.
		List<String> nombres = JsonPath.read(jsonString,"$.lugares[*].nombre");
		List<String> descripciones = JsonPath.read(jsonString,"$.lugares[*].descripcion");
		List<String> mensajesLimite = JsonPath.read(jsonString,"$.lugares[*].mensajeLimite");
		List<String> xs = JsonPath.read(jsonString,"$.lugares[*].x");
		List<String> ys = JsonPath.read(jsonString,"$.lugares[*].y");
		List<String> zs = JsonPath.read(jsonString,"$.lugares[*].z");
		
		int i = 0;
		for (String nombre : nombres) {
			
			//Aca guardo todos los ids del lugar.
			ArrayList<String> ids = new ArrayList<String>();
			
			Lugar lugar = new Lugar();
			lugar.setNombre(nombre);
			
			//Agrego todos los items
		    String cadenaIds = JsonPath.read(jsonString, "$.lugares[" + i + "].items");
		    cadenaIds += " ";//Esto separa... una villereada mas, una menos
		    cadenaIds += JsonPath.read(jsonString, "$.lugares[" + i + "].obstaculos");
		    cadenaIds += " ";//Esto separa... una villereada mas, una menos
		    cadenaIds += JsonPath.read(jsonString, "$.lugares[" + i + "].npcs");
			ids.addAll(Arrays.asList(cadenaIds.split(" ")));
			
			for (Objeto objeto : objetos) {
				
				String id = "{" + objeto.getObjetoID() + "}";
				
				//Esto haria el replace en el mapa
				if(descripciones.get(i).contains(id)) 
					descripciones.set(i, descripciones.get(i).replace(id , objeto.getDescripcionMapa()));
				
				//Agrego triggers a los items.... esto podria ir en otro lado pero bue
				for (Trigger trigger : triggers) {
					if(trigger.getObjetoID().equals(objeto.getObjetoID()))
						objeto.addTrigger(trigger);
				}
				
				//finalmente, agrego el objeto al lugar
				if(ids.contains(objeto.getObjetoID()))
					lugar.agregarObjeto(objeto);	
			}
			
			//Agrego descripcion piola
			lugar.setDescripcion(descripciones.get(i));
			
			//Si tiene algun mensaje custom para el limite del mapa lo agregamos
			if(mensajesLimite != null && mensajesLimite.size() > i)
				lugar.setMensajeLimite(mensajesLimite.get(i));
			
			//Lo agrego al mapa con la posicion.
			mapa.addLugar(lugar, new Posicion(Integer.parseInt(xs.get(i)),Integer.parseInt(ys.get(i)),Integer.parseInt(zs.get(i))));
			i++;
		}
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
					newObject.setMatable(true);//Si le agrego salud, puede morir.
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
				case "nombre":
					newObject.setNombre((String)entry.getValue());
					break;
				case "descripcion":
					newObject.setDescripcion((String)entry.getValue());
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
