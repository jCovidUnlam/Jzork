package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.JsonReader;
import zorkPackage.Mapa;
import zorkPackage.Personaje;
import zorkPackage.Posicion;

public class TestObstaculo {

	Mapa mapa;
	public static final String NORTE = "norte";
	public static final String SUR = "sur";
	public static final String OESTE = "oeste";
	public static final String ESTE = "este";
	public static final String ABAJO = "abajo";
	public static final String ARRIBA = "arriba";

	@BeforeEach
	void init() throws IOException{
		mapa = new Mapa();
		mapa.setPersonajeActual(new Personaje("Tester obstaculos"));
		JsonReader.construirAventura(mapa, "testObstaculo.txt");
	}
	
	@Test
	void obstaculoNorte() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoSur() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(SUR);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoEste() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ESTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoOeste() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(OESTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoArriba() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoAbajo() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoNorteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta"));			
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoSurYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta sur"));			
		mapa.mover(SUR);
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoEsteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta este"));			
		mapa.mover(ESTE);
		assertEquals(new Posicion(6,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoOesteYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta oeste"));			
		mapa.mover(OESTE);
		assertEquals(new Posicion(4,5,1), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoArribaYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta arriba"));			
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,5,2), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoAbajoYavanzar() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta abajo"));			
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,5,0), mapa.getPosicionActual());
	}
	
	@Test
	void removerObstaculoSurYnoOtro() {
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.getLugarActual().removerObjeto(mapa.getLugarActual().getObjeto("puerta sur"));			
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ESTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(OESTE);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ARRIBA);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(ABAJO);
		assertEquals(new Posicion(5,5,1), mapa.getPosicionActual());
		mapa.mover(SUR);
		assertEquals(new Posicion(5,4,1), mapa.getPosicionActual());
	}
	
	@Test
	void obstaculoAtadoNPCNorte() {
		removerObstaculoNorteYavanzar();
		mapa.mover(NORTE);
		assertEquals(new Posicion(5,6,1), mapa.getPosicionActual());
	}
	
}
