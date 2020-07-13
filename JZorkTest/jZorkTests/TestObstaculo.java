package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumDireccion;
import zorkPackage.Lexico;
import zorkPackage.Mapa;
import zorkPackage.Objeto;
import zorkPackage.Personaje;
import zorkPackage.Posicion;
import zorkUtils.JsonReader;

public class TestObstaculo {

	Mapa mapa;

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		Lexico.cargarLexico();
		mapa.setPersonajeActual(new Personaje("Tester obstaculos",true));
		JsonReader.construirAventura(mapa, "./Recursos/TestFiles/testObstaculo.txt");
	}
	
	@Test
	void obstaculoNorte() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoSur() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}

	@Test
	void obstaculoEste() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}

	@Test
	void obstaculoOeste() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}

	@Test
	void obstaculoArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}

	@Test
	void obstaculoAbajo() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoNorteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto(Arrays.asList("puerta")).get(0));
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoSurYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta sur".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoEsteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta este".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoOesteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta oeste".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(4,5,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoArribaYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta arriba".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,5,2), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoAbajoYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta abajo".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,5,0), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoSurYnoOtro() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		List<Objeto> obj = mapa.getLugarActual().getObjeto(Arrays.asList("puerta sur".split(" ")));
		mapa.getLugarActual().removerObjeto(obj.get(0));
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoNorteYnoOtro() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto(Arrays.asList("puerta")).get(0));
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}
	
	
	
	@Test
	void obstaculoAtadoNPCNorte() {
		removerObstaculoNorteYavanzar();
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}
	
	
	
}
