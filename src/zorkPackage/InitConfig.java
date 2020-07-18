package zorkPackage;

import java.io.IOException;

import zorkUtils.JsonReader;

public final class InitConfig {
	static Aventura gm;

	public static void getMapa (String characterName, String path) throws IOException {
		Mapa mapa =  new Mapa();
		Lexico.cargarLexico();
		mapa.setPersonajeActual( new Personaje(characterName,true));
		mapa = JsonReader.construirAventura(mapa, path);
		gm = new Aventura(mapa);
		new Observador(gm);
		
	}

	public static Aventura getGm() {
		return gm;
	}

	public static void setGm(Aventura gm) {
		InitConfig.gm = gm;
	}
	
	
}
