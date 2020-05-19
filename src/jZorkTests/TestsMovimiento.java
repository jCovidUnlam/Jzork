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
	public static final String NORTE = "norte";
	public static final String SUR = "sur";
	public static final String OESTE = "oeste";
	public static final String ESTE = "este";
	public static final String ABAJO = "abajo";
	public static final String ARRIBA = "arriba";

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
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());	
	}
	
	@Test
	void testNoHayNorte() {
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
	}
	
	@Test
	void testSur() {
		mapa.mover(SUR);
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHaySur() {
		mapa.mover(SUR);
		mapa.mover(SUR);
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testOeste() {
		mapa.mover(OESTE);
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayOeste() {
		mapa.mover(OESTE);
		mapa.mover(OESTE);
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testEste() {
		mapa.mover(ESTE);
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayEste() {
		mapa.mover(ESTE);
		mapa.mover(ESTE);
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrArriba() {
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testIrAbajo() {
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeSubir() {
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeBajar() {
		mapa.mover(NORTE);
		mapa.mover(NORTE);
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaNorte() {
		mapa.irHacia("cabania");
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaSur() {
		mapa.irHacia("bosque encantado");
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaOeste() {
		mapa.irHacia("claro templado");
		assertEquals(new Posicion(4,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaEste() {
		mapa.irHacia("risco peligroso");
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaAbajo() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.irHacia("sotano de cabania");
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.irHacia("atico tenebroso");
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testMoverseDesdeArribaConNorte() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,8,2), mapa.getPosicionActual());
	}
	
	@Test
	void testMoverseDesdeArribaSinSur() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(SUR);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaDesdeArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.irHacia("interior cabania");
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrNoExisteLugar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.irHacia("");
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrNoSaltearLugares() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.irHacia("interior cabania");
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaLugarActual() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.irHacia("bosque");
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
}
