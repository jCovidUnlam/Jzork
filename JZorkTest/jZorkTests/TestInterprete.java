package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.*;
import zorkUtils.JsonReader;

class TestInterprete {
	
	Mapa mapa;
	Personaje p;
	Interprete i;
	ArrayList<String> frase;

	@BeforeEach
	public void setUp() throws Exception {
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester Interprete"));
		JsonReader.construirAventura(mapa, "./Recursos/testInterprete.txt");
		i = new Interprete();
		Lexico.cargarLexico();
		frase = new ArrayList<String>();
	}

	@Test
	public void queSeMuevaAlSur() {
		frase.add("sur");
		assertEquals(new Comando("sur", Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
		
	@Test
	public void queSeMuevaAlNorte() {
		frase.add("norte");
		assertEquals(new Comando("norte", Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
	
	@Test
	public void queFuncioneComandoEstado() {
		frase.add("estado");
		assertEquals(new Comando("estado", Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queFuncioneComandoReglas() {
		frase.add("reglas");
		assertEquals(new Comando("reglas", Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queTomeArma() {
		frase.add("tomar");
		frase.add("palo");
		assertEquals(new Comando("tomar", "palo", null, Comando.Tipo.ADQUIRIR), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoInvalido() {
		assertEquals(new Comando(Comando.Tipo.INVALIDO), Interprete.interpretar(frase));
	}
	
	
	@Test
	public void queSeaComandoMovimiento() {
		frase.add("ir");
		frase.add("norte");
		assertEquals(new Comando("ir", "norte", null, Comando.Tipo.MOVER), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoEquiparUsuario() {
		frase.add("equipar");
		frase.add("palo");
		assertEquals(new Comando("equipar", "palo", null, Comando.Tipo.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoInspeccionable() {
		frase.add("inspeccionar");
		frase.add("alrededor");
		assertEquals(new Comando("inspeccionar", "alrededor", null, Comando.Tipo.INSPECCIONAR), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDeNPC() {
		frase.add("hablar");
		frase.add("Alf");
		assertEquals(new Comando("hablar", "Alf", null, Comando.Tipo.NPC), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDeUsable() {
		frase.add("usar");
		frase.add("palo");
		assertEquals(new Comando("usar", "palo", null, Comando.Tipo.USAR), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoAtacar() {
		frase.add("atacar");
		frase.add("Alf");
		assertEquals(new Comando("atacar", "Alf", null, Comando.Tipo.ATACAR), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDescartar() {
		frase.add("descartar");
		frase.add("palo");
		assertEquals(new Comando("descartar", "palo", null, Comando.Tipo.DESCARTAR), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoNoValido() {
		frase = null;
		assertEquals(new Comando(Comando.Tipo.INVALIDO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoTrigger() {
		frase.add("usar");
		frase.add("palo");
		frase.add("Alf");		
		assertEquals(new Comando("usar", "palo", "Alf", Comando.Tipo.TRIGGER), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoTriggerInvalido() {
		frase.add("mirar");
		frase.add("palo");
		frase.add("Alf");
		assertEquals(new Comando(Comando.Tipo.INVALIDO), Interprete.interpretar(frase));
	}
}
