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
	void testPosicionInicial() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNorte() {
		mapa.moverNorte();
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());	
	}
	
	@Test
	void testNoHayNorte() {
		mapa.moverNorte();
		mapa.moverNorte();
		mapa.moverNorte();
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
	}
	
	@Test
	void testSur() {
		mapa.moverSur();
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHaySur() {
		mapa.moverSur();
		mapa.moverSur();
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testOeste() {
		mapa.moverOeste();
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayOeste() {
		mapa.moverOeste();
		mapa.moverOeste();
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testEste() {
		mapa.moverEste();
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayEste() {
		mapa.moverEste();
		mapa.moverEste();
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrArriba() {
		mapa.moverNorte();
		mapa.moverNorte();
		mapa.moverArriba();
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testIrAbajo() {
		mapa.moverNorte();
		mapa.moverNorte();
		mapa.moverAbajo();
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeSubir() {
		mapa.moverNorte();
		mapa.moverNorte();
		mapa.moverArriba();
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.moverArriba();
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeBajar() {
		mapa.moverNorte();
		mapa.moverNorte();
		mapa.moverAbajo();
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
		mapa.moverAbajo();
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
//	@Test
//	void testIrHacia() {
//		mapa.irHacia("Cabania");
//		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
//	}
	

}
