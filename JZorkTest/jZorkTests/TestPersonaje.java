package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;
import zorkUtils.JsonReader;

class TestPersonaje {

	Personaje p;
	Mapa mapa;

	@BeforeEach
	void setUp() throws Exception {
		p = new Personaje("Juanito",true);
		mapa = new Mapa();
		mapa.setPersonajeActual(p);
		JsonReader.construirAventura(mapa, "./Recursos/testPersonaje.txt");
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
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(i, p.getArmaEquipada());
	}
	
	@Test
	void danioPersonajeSinArma() {
		assertEquals(5, p.getDanio());
	}
	
	@Test
	void danioPersonajeConArma() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(15, p.getDanio());
	}
	
	@Test
	void queNoEquipeObjeto() {
		//No se deben equipar objetos que no son armas!
		//Baja a ese gato!!
		Item i = mapa.getLugarActual().getItem("gato");
		p.addObjeto(i);
		p.equiparArma("gato");
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queNoEquipeArmaSiInventarioVacio() {
		p.equiparArma("gato");
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queDesequipeArma() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		assertEquals(15, p.getDanio());
		p.desequiparArma((Arma) i);
		assertNull(p.getArmaEquipada());
	}
	
	@Test
	void queObtengaObjInvetario() {
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		assertEquals(i, p.getObjetoInventario("palo"));
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
		Item i = mapa.getLugarActual().getItem("palo");
		p.addObjeto(i);
		p.equiparArma("palo");
		p.atacar(otroP);
		assertEquals(85, otroP.getSalud());
	}
}
