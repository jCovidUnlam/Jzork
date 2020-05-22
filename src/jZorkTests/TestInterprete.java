package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;

class TestInterprete {
	
	Mapa mapa;
	Personaje p;
	Interprete i;
	ArrayList<String> frase;

	@BeforeEach
	public void setUp() throws Exception {
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester Interprete"));
		JsonReader.construirAventura(mapa, "testInterprete.txt");
		i = new Interprete();
		Lexico.cargarLexico();
		frase = new ArrayList<String>();
	}

	@Test
	public void testMovimientoSimpleSur() {
		frase.add("sur");
		assertEquals(new Comando("sur", Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
		
	@Test
	public void testMovimientoSimpleNorte() {
		frase.add("norte");
		assertEquals(new Comando("norte", Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
	
	@Test
	public void testUsuarioSimpleestado() {
		frase.add("estado");
		assertEquals(new Comando("estado", Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void testUsuarioSimpleReglas() {
		frase.add("reglas");
		assertEquals(new Comando("reglas", Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void testTomarArma() {
		frase.add("tomar");
		frase.add("palo");
		assertEquals(new Comando("tomar", "palo", null, Comando.Tipo.ADQUIRIR), Interprete.interpretar(frase));
	}
	
	@Test
	public void testInvalido() {
		assertEquals(new Comando(Comando.Tipo.INVALIDO), Interprete.interpretar(frase));
	}
	
	
	@Test
	public void testIrDireccion() {
		frase.add("ir");
		frase.add("norte");
		assertEquals(new Comando("ir", "norte", null, Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
	
	@Test
	public void testUsuario() {
		frase.add("equipar");
		frase.add("palo");
		assertEquals(new Comando("equipar", "palo", null, Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void testInspeccionable() {
		frase.add("inspeccionar");
		frase.add("alrededor");
		assertEquals(new Comando("inspeccionar", "alrededor", null, Comando.Tipo.INSPECCIONAR), Interprete.interpretar(frase));
	}
	
	@Test
	public void testNPC() {
		frase.add("hablar");
		frase.add("Alf");
		assertEquals(new Comando("hablar", "Alf", null, Comando.Tipo.NPC), Interprete.interpretar(frase));
	}
}
