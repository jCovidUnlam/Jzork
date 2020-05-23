package zorkPackage;

import java.io.IOException;

import zorkUI.Consola;
import zorkUtils.JsonReader;

public final class Aventura {

	public static void getMapa (String characterName, String path) throws IOException {
		Mapa mapa =  new Mapa();
		mapa.setPersonajeActual( new Personaje(characterName));
		mapa = JsonReader.construirAventura(mapa, path);
		Lexico.cargarLexico();
		mapa.verMapa();
		Consola.iniciarAventura(mapa);
		GameMaster gm = new GameMaster(mapa);
		new Observador(gm);
		
	}
}
