package jZorkTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumDireccion;
import zorkPackage.Item;
import zorkPackage.Lexico;
import zorkPackage.Mapa;
import zorkPackage.Objeto;
import zorkPackage.Personaje;
import zorkUtils.JsonReader;

public class TestInventario {
	
	Mapa mapa;
	Personaje personaje;

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		Lexico.cargarLexico();
		personaje = new Personaje("Tester inventario",true);
		mapa.setPersonajeActual(personaje);
		JsonReader.construirAventura(mapa, "./Recursos/TestFiles/testInventario.txt");
	}

	@Test
	public void queHayaDosObjEnLugar() {
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
	}

	@Test
	public void queSePuedaTomarObjeto() {		
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("gato"));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		assertEquals(1, mapa.getLugarActual().getObjetos().size());
	}
	
	@Test
	public void queNoSePuedaTomarObjeto() {
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
		mapa.getLugarActual().getObjeto(Arrays.asList("paracaidas"));
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
	}
	
	@Test
	public void queInventarioEsteVacio() {
		assertEquals(0, personaje.getInventario().size());		
	}

	@Test
	public void queObjAparezcaEnInventario() {
		personaje.addObjeto(mapa.getLugarActual().getItem(Arrays.asList("gato")));	
		assertEquals(1, personaje.getInventario().size());
	}
	
	@Test
	public void queSuelteObjCorrectamente() {
		//Si suelto un objeto en el bosque
		//tiene que aparecer ahí y no en otro lado
		Item i = mapa.getLugarActual().getItem(Arrays.asList("gato"));
		personaje.addObjeto(i);
		mapa.getLugarActual().removerObjeto(i);
		mapa.mover(EnumDireccion.NORTE.getValue());
		personaje.removerDeInventario(i);
		mapa.getLugarActual().agregarObjeto(i);
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(1, mapa.getLugarActual().getObjetos().size());
	}

}
