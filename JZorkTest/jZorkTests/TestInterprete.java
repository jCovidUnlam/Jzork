package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumComando;
import zorkEnum.EnumDireccion;
import zorkPackage.Comando;
import zorkPackage.Interprete;
import zorkPackage.Lexico;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkUtils.JsonReader;

class TestInterprete {
	
	Mapa mapa;
	Personaje p;
	Interprete i;
	ArrayList<String> frase;

	@BeforeEach
	public void setUp() throws Exception {
		mapa = new Mapa();
		Lexico.cargarLexico();
		mapa.setPersonajeActual(new Personaje("Tester Interprete",true));
		JsonReader.construirAventura(mapa, "./Recursos/TestFiles/testInterprete.txt");
		i = new Interprete();
		frase = new ArrayList<String>();
	}

	@Test
	public void queSeMuevaAlSur() {
		frase.add(EnumDireccion.SUR.getValue());
		assertEquals(new Comando(EnumDireccion.SUR.getValue(), EnumComando.MOVER), Interprete.interpretar(frase));
	}
		
	@Test
	public void queSeMuevaAlNorte() {
		frase.add(EnumDireccion.NORTE.getValue());
		assertEquals(new Comando(EnumDireccion.NORTE.getValue(), EnumComando.MOVER), Interprete.interpretar(frase));
	}
	
	@Test
	public void queFuncioneComandoEstado() {
		frase.add("estado");
		assertEquals(new Comando("estado", EnumComando.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queFuncioneComandoReglas() {
		frase.add("reglas");
		assertEquals(new Comando("reglas", EnumComando.USUARIO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queTomeArma() {
		frase.add("tomar");
		frase.add("palo");
		assertEquals(new Comando("tomar", EnumComando.ADQUIRIR, Arrays.asList("palo")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoInvalido() {
		assertEquals(new Comando(EnumComando.INVALIDO), Interprete.interpretar(frase));
	}
	
	
	@Test
	public void queSeaComandoMovimiento() {
		frase.add("ir");
		frase.add(EnumDireccion.NORTE.getValue());
		assertEquals(new Comando("ir", EnumComando.MOVER, Arrays.asList(EnumDireccion.NORTE.getValue())), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoEquiparUsuario() {
		frase.add("equipar");
		frase.add("palo");
		assertEquals(new Comando("equipar", EnumComando.USUARIO, Arrays.asList("palo")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoInspeccionable() {
		frase.add("inspeccionar");
		frase.add("alrededor");
		assertEquals(new Comando("inspeccionar", EnumComando.INSPECCIONAR, Arrays.asList("alrededor")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDeNPC() {
		frase.add("hablar");
		frase.add("Alf");
		assertEquals(new Comando("hablar", EnumComando.NPC,  Arrays.asList("Alf")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDeUsable() {
		frase.add("usar");
		frase.add("palo");
		assertEquals(new Comando("usar", EnumComando.USAR, Arrays.asList("palo")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoAtacar() {
		frase.add("atacar");
		frase.add("Alf");
		assertEquals(new Comando("atacar", EnumComando.ATACAR, Arrays.asList("Alf")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoDescartar() {
		frase.add("descartar");
		frase.add("palo");
		assertEquals(new Comando("descartar", EnumComando.DESCARTAR, Arrays.asList("palo")), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoNoValido() {
		frase = null;
		assertEquals(new Comando(EnumComando.INVALIDO), Interprete.interpretar(frase));
	}
	
	@Test
	public void queSeaComandoTrigger() {
		frase.add("usar");
		frase.add("palo");
		frase.add("en");
		frase.add("Alf");		
		assertEquals(new Comando("usar", EnumComando.USAR, Arrays.asList("palo"), Arrays.asList("Alf")), Interprete.interpretar(frase));
	}
	

}
