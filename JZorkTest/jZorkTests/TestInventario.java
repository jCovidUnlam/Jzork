package jZorkTests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumDireccion;
import zorkPackage.Item;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkUtils.JsonReader;

public class TestInventario {
	
	///Noe, yase que hiciste algunas pruebas de inventario en el Personaje, pero ese de Personaje
	//nos va a servir para probar otras cosas, aca deberiamos probar:
	//Agarrar un objeto de un lugar, ver que el PJ lo tenga en el inventario y el Lugar ya no lo tenga.
	//Soltar un objeto, efecto contrario, no en inventario si en lugar
	//ver que no rompa si llamo un inventario vacio.
	//ver que el numero de objetos en el invenario sea correcto.
	//Agarrar un objeto en un lugar, soltarlo en otro, volver al primer lugar y que no este, volver al otro lugar y agarrarlo... Ect.
	//Los lugares son los mismos, esto no es para joderte jaja sino para que se pueda probar que aunque suelte el objeto en la concha del pato, este ahi.
	
	//en el Lugar, esta la funcion getObjeto('nombreObjeto') que retorna el objeto o null sino lo tiene 
	
	Mapa mapa;
	Personaje personaje;

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		personaje = new Personaje("Tester inventario",true);
		mapa.setPersonajeActual(personaje);
		JsonReader.construirAventura(mapa, "./Recursos/testInventario.txt");
	}

	@Test
	public void queHayaDosObjEnLugar() {
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
	}

	@Test
	public void queSePuedaTomarObjeto() {		
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("gato"));
		assertEquals(1, mapa.getLugarActual().getObjetos().size());
	}
	
	@Test
	public void queNoSePuedaTomarObjeto() {
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
		mapa.getLugarActual().getObjeto("paracaidas");
		assertEquals(2, mapa.getLugarActual().getObjetos().size());
	}
	
	@Test
	public void queInventarioEsteVacio() {
		assertEquals(0, personaje.getInventario().size());		
	}

	@Test
	public void queObjAparezcaEnInventario() {
		personaje.addObjeto(mapa.getLugarActual().getItem("gato"));
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("gato"));		
		assertEquals(1, personaje.getInventario().size());
	}
	
	@Test
	public void queSuelteObjCorrectamente() {
		//Si suelto un objeto en el bosque
		//tiene que aparecer ahí y no en otro lado
		Item i = mapa.getLugarActual().getItem("gato");
		personaje.addObjeto(i);
		mapa.getLugarActual().removerObjeto(i);
		mapa.mover(EnumDireccion.NORTE.getValue());
		personaje.removerDeInventario(i);
		mapa.getLugarActual().agregarObjeto(i);
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(1, mapa.getLugarActual().getObjetos().size());
	}

}
