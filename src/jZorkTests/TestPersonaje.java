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
		mapa = new Mapa();
		mapa.setPersonajeActual(p);
		JsonReader.construirAventura(mapa, "testMovimiento.txt");
		Item i = mapa.getLugarActual().getItem("gato");
		p.addObjeto(i);
		p.equiparArma("gato");
		assertEquals(i, p.getArmaEquipada());
	}

}
