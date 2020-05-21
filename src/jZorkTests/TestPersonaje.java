package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;

class TestPersonaje {

	Personaje p;
	Mapa mapa;

	@BeforeEach
	void setUp() throws Exception {
		p = new Personaje("Juanito");
		mapa = new Mapa();
		mapa.setPersonajeActual(p);
		JsonReader.construirAventura(mapa, "testPersonaje.txt");
	}

	@Test
	void testNombreCorrecto() {
		assertEquals("Juanito", p.getNombre());
	}
	
	@Test 
	void testNombreIncorrecto() {
		assertNotEquals("Jose", p.getNombre());
	}
	
	@Test
	void testCambiarNombre() {
		p.setNombre("Pedro");
		assertEquals("Pedro", p.getNombre());
	}
	
	@Test
	void testInventarioVacio() {
		assertEquals(0, p.getInventario().size());
	}
	
	@Test 
	void testInventarioConObjetos() {
		p.addObjeto(new Item());
		p.addObjeto(new Item());
		assertEquals(2, p.getInventario().size());
	}
	
	@Test
	void testArmaEquipada() {
		assertNull(p.getArmaEquipada());
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(i, p.getArmaEquipada());
	}
	
	@Test
	void testDanioSinArma() {
		assertEquals(5, p.getDanio());
	}
	
	@Test
	void testDanioConArma() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(15, p.getDanio());
	}
	
	@Test
	void testNoEquiparObjeto() {
		//No se deben equipar objetos que no son armas!
		//Baja a ese gato!!
		Item i = mapa.getLugarActual().getItem("gato");
		p.addObjeto(i);
		p.equiparArma("gato");
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void testEquiparArmaInventarioVacio() {
		Item i = mapa.getLugarActual().getItem("gato");
		p.equiparArma("gato");
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void testDesequiparArma() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(15, p.getDanio());
		p.desequiparArma((Arma) i);
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void testObtenerObjInventario() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		assertEquals(i, p.getObjetoInventario("palo"));
	}
	
	@Test
	void testRestarSaludPersonaje() {
		p.recibirAtaque(50);
		assertEquals(50, p.getSalud());
	}
	
	@Test
	void testAtacarAOtroPersonaje() {
		Personaje otroP = new Personaje("Pedro");
		p.atacar(otroP);
		assertEquals(95, otroP.getSalud());
	}
	
	@Test
	void testAtacarAOtroPConArma() {
		Personaje otroP = new Personaje("Pedro");
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		p.atacar(otroP);
		assertEquals(85, otroP.getSalud());
	}
}
