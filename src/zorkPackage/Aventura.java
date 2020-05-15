package zorkPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Aventura {

	private String nombre;
	private String descripcion;
	private Mapa mapa;
	private Personaje personaje;

	public Aventura(String characterName, String path) throws IOException {
		super();
		this.personaje = new Personaje(characterName);
		JsonReader.construirAventura(this, path);
		//cargamos todas las palabras aceptadas
		cargarLexico();

		// Esto muestra masomenos el mapa que se genero para ver que sea el correcto
		mapa.verMapa();
		//
		GameMaster gm = new GameMaster(this);
		new Observador(gm);
	}

	public static void cargarLexico() throws IOException {
		
		
	    //File archivo = new File(‪‪);
		FileReader fr = new FileReader("./lexico.txt");
		BufferedReader bf = new BufferedReader(fr);
		String linea;

		while ((linea = bf.readLine()) != null) {

		
			String[] lexico = linea.split(";");
	
			EnumTipoLexico tipolexico = EnumTipoLexico.valueOf(lexico[0].toUpperCase());
			
			switch (tipolexico) {
			case ADQUIRIBLES:
				VerbosAceptados.adquirible = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.adquirible,0,lexico.length-1);
				break;
			case ROMPIBLES:
				VerbosAceptados.rompibles = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.rompibles,0,lexico.length-1);
				break;
			case CONSUMIBLE:
				VerbosAceptados.consumible = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.consumible,0,lexico.length-1);
				break;
			case INSPECCIONABLE:
				VerbosAceptados.inspeccionable = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.inspeccionable,0,lexico.length-1);
				break;
			case USABLE:
				VerbosAceptados.usable = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.usable,0,lexico.length-1);
				break;
			case MOVIMIENTO:
				VerbosAceptados.movimiento = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.movimiento,0,lexico.length-1);
				break;
			case USUARIO:
				VerbosAceptados.usuario = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.usuario,0,lexico.length-1);
				break;
			case NPC:
				VerbosAceptados.NPC = new String[lexico.length-1];
				System.arraycopy(lexico,1,VerbosAceptados.NPC,0,lexico.length-1);
				break;
			default:
				break;
			}

		}

		bf.close();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

}
