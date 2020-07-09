package zorkPackage;

import java.io.IOException;

import zorkUtils.JsonReader;

public final class InitConfig {

	public static void getMapa (String characterName, String path) throws IOException {
		Mapa mapa =  new Mapa();
		mapa.setPersonajeActual( new Personaje(characterName,true));
		mapa = JsonReader.construirAventura(mapa, path);
		Lexico.cargarLexico();
		//mapa.verMapa();
		//Consola.iniciarAventura(mapa);
		Aventura gm = new Aventura(mapa);
		new Observador(gm);
		
	}
}