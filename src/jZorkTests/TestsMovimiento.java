package jZorkTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zorkPackage.Aventura;

class TestsMovimiento {

	Aventura aventura;
	int a;
	
	@BeforeEach
	void init() throws IOException{

	}
	
	@Test
	void testNorte() {
		System.out.println(a);
		assertEquals(a,1);
		
	}
	
	
	

}
