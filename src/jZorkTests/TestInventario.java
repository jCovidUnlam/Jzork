package jZorkTests;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.JsonReader;
import zorkPackage.Mapa;
import zorkPackage.Personaje;

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

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester inventario"));
		JsonReader.construirAventura(mapa, "testInventario.txt");
	}
	
	@Test
	void tomarObjeto() {
		
		//Eso trae todos los objetos del lugar actual
		System.out.println(mapa.getLugarActual().getObjetos());
		//Esto, busca un objeto o devuelve null
		System.out.println(mapa.getObjeto("gato"));
		
		
	}
}
