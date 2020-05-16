package jZorkTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import zorkPackage.*;

class TestsMovimiento {

	@Before
	public void init() throws IOException {
		
		final Aventura aventura = new Aventura("Tester Movimiento", "testMovimiento.txt");
	
	}
	
	@Test
	public void moverNorte() {
		
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
