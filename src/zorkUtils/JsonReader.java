package zorkUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.parser.JSONParser;
import com.jayway.jsonpath.JsonPath;

import zorkGraficos.LugarGrafico;
import zorkGraficos.ObjetoGrafico;
import zorkPackage.Arma;
import zorkPackage.Contenedor;
import zorkPackage.Item;
import zorkPackage.Lugar;
import zorkPackage.Mapa;
import zorkPackage.NPC;
import zorkPackage.Objeto;
import zorkPackage.Obstaculo;
import zorkPackage.PocionSalud;
import zorkPackage.Posicion;
import zorkTrigger.OpcionDialogo;
import zorkTrigger.Trigger;
import zorkTrigger.TriggerAtaque;
import zorkTrigger.TriggerConversacion;
import zorkTrigger.TriggerItem;


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
			case "conversacion":
				newTrigger = addNewConversacionTrigger(trigger);
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

	
	@SuppressWarnings("unchecked")
	private static TriggerConversacion addNewConversacionTrigger(Map<String,Object> trigger) {
		TriggerConversacion returned = new TriggerConversacion();
		
		for (Map.Entry<String,Object> entry : trigger.entrySet()) {
			
			switch (entry.getKey()) {
			case "idObjeto":
				returned.setObjetoID((String)entry.getValue());
				break;
			case "mensajeInicial":
				returned.setMensajeInicial((String)entry.getValue());
				break;
			case "mensajeSalida":
				returned.setMensajeSalida((String)entry.getValue());
				break;
			case "opciones":
				cargarOpciones((Map<String,Map<String,String>>)entry.getValue(), returned);
				break;

			default:
				break;
			}	
		}
		
		return returned;
	}
	
	private static void cargarOpciones(Map<String,Map<String,String>> valores, TriggerConversacion trigger) {
		
		for (Map.Entry<String,Map<String,String>> entry : valores.entrySet()) {
			OpcionDialogo opt = new OpcionDialogo();
			opt.setNumero(Integer.parseInt(entry.getKey()));
			for (Map.Entry<String,String> entryty : entry.getValue().entrySet()) {
				if(entryty.getKey().equals("texto"))
					opt.setTexto(entryty.getValue());
				else
					opt.setRespuesta(entryty.getValue());
			}
			trigger.addOpcion(opt);
		}	
	}
	
	private static void agregarLugares(Mapa mapa, String jsonString, List<Objeto> objetos, List<Trigger> triggers) {
		
		//Listo todo lo referente a todos los lugares.
		List<String> nombres = JsonPath.read(jsonString,"$.lugares[*].nombre");
		List<String> descripciones = JsonPath.read(jsonString,"$.lugares[*].descripcion");
		List<String> mensajesLimite = JsonPath.read(jsonString,"$.lugares[*].mensajeLimite");
		List<String> xs = JsonPath.read(jsonString,"$.lugares[*].x");
		List<String> ys = JsonPath.read(jsonString,"$.lugares[*].y");
		List<String> zs = JsonPath.read(jsonString,"$.lugares[*].z");
		List<String> paths = JsonPath.read(jsonString,"$.lugares[*].path");
		//List<String> idsLugares = JsonPath.read(jsonString,"$.lugares[*].id");
		
		int i = 0;
		for (String nombre : nombres) {
			
			//Aca guardo todos los ids del lugar.
			ArrayList<String> ids = new ArrayList<String>();
			
			Lugar lugar = new Lugar();
			LugarGrafico grafica = new LugarGrafico(paths.get(i));
			lugar.setNombre(nombre);
			
			//Agrego todos los items
		    String cadenaIds = JsonPath.read(jsonString, "$.lugares[" + i + "].items");
		    cadenaIds += " ";
		    cadenaIds += JsonPath.read(jsonString, "$.lugares[" + i + "].obstaculos");
		    cadenaIds += " ";
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
				if(ids.contains(objeto.getObjetoID())) {
					lugar.agregarObjeto(objeto);
					if(!(objeto instanceof Obstaculo))
						grafica.addSprite(objeto.getGrafica());
				}
			}
			
			//Agrego descripcion piola
			lugar.setDescripcion(descripciones.get(i));
			
			//Si tiene algun mensaje custom para el limite del mapa lo agregamos
			if(mensajesLimite != null && mensajesLimite.size() > i)
				lugar.setMensajeLimite(mensajesLimite.get(i));
			
			lugar.setGrafica(grafica);
			
			//Lo agrego al mapa con la posicion.
			mapa.addLugar(lugar, new Posicion(Integer.parseInt(xs.get(i)),Integer.parseInt(ys.get(i)),Integer.parseInt(zs.get(i))));
			i++;
		}
	}
	
	private static List<Objeto> agregarItems(String jsonString){
		List<Objeto> aux = new ArrayList<Objeto>();
		List<Map<String, Object>> itemList = JsonPath.read(jsonString,"$.items[*]");


		for (Map<String, Object> objeto : itemList) {
			
			Item newItem = new Item();
			ObjetoGrafico grafico = new ObjetoGrafico();
			
			if(objeto.get("tipo") != null)
			{
				switch((String)objeto.get("tipo")) {
				case "arma":
					newItem = new Arma();
					newItem.setDanio(Double.parseDouble(objeto.get("danio").toString()));
					break;
				case "consumible":
					switch (objeto.get("subtipo").toString()) {
					case "pocionSalud":
						PocionSalud pocion = new PocionSalud();
						pocion.setPuntosSaludRecuperados(Double.parseDouble(objeto.get("puntosSalud").toString()));
						newItem = pocion;
						break;
					default:
						break;
					}
					break;
				case "contenedor":
					Contenedor contenedor = new Contenedor();
					List<String> ids = Arrays.asList(objeto.get("contenido").toString().split(" "));
				    for (Objeto obj : aux) {
				    	if(ids.contains(obj.getObjetoID()) && obj instanceof Item)
				    		contenedor.addContenido((Item)obj);
					}
				    newItem = contenedor;
				
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
				case "rompible":
					newItem.setRompible(Boolean.parseBoolean((String)entry.getValue()));
					break;
				case "mensajeRompible":
					newItem.setMensajeRompible((String)entry.getValue());
					break;
				case "path":
					grafico.setPath((String)entry.getValue());
					break;
				case "x":
					grafico.setX(Integer.parseInt((String)entry.getValue()));
					break;
				case "y":
					grafico.setY(Integer.parseInt((String)entry.getValue()));
					break;
				default:
					break;
				}	
			}
			
			newItem.setGrafica(grafico);
			aux.add(newItem);
		}
		
		return aux;
	}
		
	private static List<Objeto> agregarNPCs(String jsonString){
		List<Objeto> aux = new ArrayList<Objeto>();
		List<Map<String, Object>> objectList = JsonPath.read(jsonString,"$.npcs[*]");
	
		
		for (Map<String, Object> objeto : objectList) {
			
			NPC newObject = new NPC();
			ObjetoGrafico grafico = new ObjetoGrafico();
			
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
					newObject.setMatable(true);
					break;
				case "matable":
					newObject.setMatable(Boolean.parseBoolean((String)entry.getValue()));
					break;
				case "path":
					grafico.setPath((String)entry.getValue());
					break;
				case "x":
					grafico.setX(Integer.parseInt((String)entry.getValue()));
					break;
				case "y":
					grafico.setY(Integer.parseInt((String)entry.getValue()));
					break;
				default:
					break;
				}	
			}

			newObject.setGrafica(grafico);
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
