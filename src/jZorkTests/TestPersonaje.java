package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;

class TestPersonaje {

	Personaje p;

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
	void testInventarioVacio() {
		assertEquals(0, p.getInventario().size());
	}
	
	@Test 
	void testInventarioConObjetos() {
		p.addObjeto(new Item());
		p.addObjeto(new Item());
		assertEquals(2, p.getInventario().size());
	}

}
