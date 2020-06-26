package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkEnum.EnumDireccion;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkPackage.Posicion;
import zorkUtils.JsonReader;

public class TestObstaculo {

	Mapa mapa;

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester obstaculos",true));
		JsonReader.construirAventura(mapa, "./Recursos/testObstaculo.txt");
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
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta"));
		mapa.mover(EnumDireccion.NORTE.getValue());
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoSurYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta sur"));
		mapa.mover(EnumDireccion.SUR.getValue());
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoEsteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta este"));
		mapa.mover(EnumDireccion.ESTE.getValue());
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoOesteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta oeste"));
		mapa.mover(EnumDireccion.OESTE.getValue());
		assertEquals(new Posicion(4,5,1), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoArribaYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta arriba"));
		mapa.mover(EnumDireccion.ARRIBA.getValue());
		assertEquals(new Posicion(5,5,2), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoAbajoYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta abajo"));
		mapa.mover(EnumDireccion.ABAJO.getValue());
		assertEquals(new Posicion(5,5,0), mapa.getPosicionActual());
	}

	@Test
	void removerObstaculoSurYnoOtro() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta sur"));
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
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta"));
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
