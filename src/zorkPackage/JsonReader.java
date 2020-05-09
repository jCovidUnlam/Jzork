package zorkPackage;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import net.minidev.json.JSONArray;

public class JsonReader {

	public static void construirAventura (Aventura aventura, String path) {
		JSONParser parser = new JSONParser();
		try {
			Object json = parser.parse(new FileReader(path));
			String jsonString = json.toString();
			
			//Esto construye el mapa, de hacerse mas complejo en un futuro ya se manda a una funcion a parte
			aventura.setNombre(JsonPath.read(jsonString, "$.map.Nombre"));
			aventura.setDescripcion(JsonPath.read(jsonString, "$.map.Descripcion"));
			aventura.setMapa(construirMapa(jsonString));
			
			//agregarObjetos();
			agregarLugares(aventura.getMapa(), jsonString);
			

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
	
	private static void agregarLugares(Mapa mapa, String jsonString) {
		
		int i = 0;
		List<String> nombres = JsonPath.read(jsonString,"$.lugares[*].Nombre");
		List<String> descripciones = JsonPath.read(jsonString,"$.lugares[*].Descripcion");
		List<Lugar> lugares = new ArrayList<Lugar>();
		
		for (String nombre : nombres) {
			lugares.add(new Lugar(nombre, descripciones.get(i)));
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
	
	
	

}
