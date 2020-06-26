package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumDireccion;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkPackage.Posicion;
import zorkUtils.JsonReader;

class TestsMovimiento {

	Mapa mapa;
	
	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester movimiento",true));
		JsonReader.construirAventura(mapa, "./Recursos/testMovimiento.txt");
	}
	
	@Test
	void testPosicionInicial() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNorte() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());	
	}
	
	@Test
	void testNoHayNorte() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
	}
	
	@Test
	void testSur() {
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHaySur() {
		mapa.mover(EnumDireccion.SUR.getValue());
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void testOeste() {
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayOeste() {
		mapa.mover(EnumDireccion.OESTE.getValue());
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(4,5,1),  mapa.getPosicionActual());
	}
	
	@Test
	void testEste() {
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testNoHayEste() {
		mapa.mover(EnumDireccion.ESTE.getValue());
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void testIrArriba() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testIrAbajo() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeSubir() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testNoSePuedeBajar() {
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.NORTE.getValue());
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ABAJO.getValue());
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
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.irHacia("sotano de cabania");
		assertEquals(new Posicion(5,7,0), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.irHacia("atico tenebroso");
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testMoverseDesdeArribaConNorte() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,8,2), mapa.getPosicionActual());
	}
	
	@Test
	void testMoverseDesdeArribaSinSur() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,7,2), mapa.getPosicionActual());
	}
	
	@Test
	void testIrHaciaDesdeArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,7,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
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
