package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.JsonReader;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkPackage.Posicion;

class TestsMovimiento {

	Mapa mapa;

	
	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester movimiento"));
		JsonReader.construirAventura(mapa, "testMovimiento.txt");
	}
	
	@Test
	void testNorte() {
		
		Posicion actual = mapa.getPosicionActual();
		mapa.moverNorte();
		Posicion norte = mapa.getPosicionActual();
		
		actual.setY(actual.getY() + 1);
		
		assertEquals(actual, norte);
		
	}
	
	
	

}
