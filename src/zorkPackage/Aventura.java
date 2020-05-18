package zorkPackage;

import java.io.IOException;

public final class Aventura {

	public static Mapa getMapa (String characterName, String path) throws IOException {
		Mapa mapa =  new Mapa();
		mapa.setPersonajeActual( new Personaje(characterName));
		mapa = JsonReader.construirAventura(mapa, path);
		Lexico.cargarLexico();//Cargamos archio lexico
		mapa.verMapa();// Esto muestra masomenos el mapa que se genero para ver que sea el correcto
		GameMaster gm = new GameMaster(mapa);
		new Observador(gm);
		
		return mapa;
	}
}
