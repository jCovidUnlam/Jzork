package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;
import zorkUtils.JsonReader;

class TestPersonaje {

	Personaje p;
	Mapa mapa;

	@BeforeEach
	void setUp() throws Exception {
		mapa = new Mapa();
		Lexico.cargarLexico();
		p = new Personaje("Juanito",true);
		mapa.setPersonajeActual(p);
		JsonReader.construirAventura(mapa, "./Recursos/TestFiles/testPersonaje.txt");
	}

	@Test
	void queNombrePersonajeSeaCorrecto() {
		assertEquals("Juanito", p.getNombre());
	}
	
	@Test 
	void queNombrePersonajeSeaIncorrecto() {
		assertNotEquals("Jose", p.getNombre());
	}
	
	@Test
	void queCambieNombrePersonaje() {
		p.setNombre("Pedro");
		assertEquals("Pedro", p.getNombre());
	}
	
	@Test
	void queElInventarioInicieVacio() {
		assertEquals(0, p.getInventario().size());
	}
	
	@Test 
	void quePersonajeAgregueObjeto() {
		p.addObjeto(new Item());
		assertEquals(1, p.getInventario().size());
	}
	
	@Test
	void queEquipeArma() {
		assertNull(p.getArmaEquipada());
		Item i = mapa.getLugarActual().getItem(Arrays.asList("palo"));
		p.addObjeto(i);
		p.equiparArma(Arrays.asList("palo"));
		assertEquals(i, p.getArmaEquipada());
	}
	
	@Test
	void danioPersonajeSinArma() {
		assertEquals(5, p.getDanio());
	}
	
	@Test
	void danioPersonajeConArma() {
		Item i = mapa.getLugarActual().getItem(Arrays.asList("palo"));
		p.addObjeto(i);
		p.equiparArma(Arrays.asList("palo"));
		assertEquals(15, p.getDanio());
	}
	
	@Test
	void queNoEquipeObjetoQueNoSeaArma() {
		Item i = mapa.getLugarActual().getItem(Arrays.asList("gato"));
		p.addObjeto(i);
		p.equiparArma(Arrays.asList("gato"));
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queNoEquipeArmaSiInventarioVacio() {
		p.equiparArma(Arrays.asList("gato"));
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queDesequipeArma() {
		Item i = mapa.getLugarActual().getItem(Arrays.asList("palo"));
		p.addObjeto(i);
		p.equiparArma(Arrays.asList("palo"));
		assertEquals(15, p.getDanio());
		p.desequiparArma((Arma) i);
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queObtengaObjInvetario() {
		Item i = mapa.getLugarActual().getItem(Arrays.asList("palo"));
		p.addObjeto(i);
		assertEquals(i, p.getObjetoInventario(Arrays.asList("palo")).get(0));
	}
	
	@Test
	void queResteSaludPersonaje() {
		p.recibirAtaque(50);
		assertEquals(50, p.getSalud());
	}
	
	@Test
	void quePersAtaqueAOtroPers() {
		Personaje otroP = new Personaje("Pedro",true);
		p.atacar(otroP);
		assertEquals(95, otroP.getSalud());
	}
	
	@Test
	void quePersAtaqueAOtroPersConArma() {
		Personaje otroP = new Personaje("Pedro",true);
		Item i = mapa.getLugarActual().getItem(Arrays.asList("palo"));
		p.addObjeto(i);
		p.equiparArma(Arrays.asList("palo"));
		p.atacar(otroP);
		assertEquals(85, otroP.getSalud());
	}
}
