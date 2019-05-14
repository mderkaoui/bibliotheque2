package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class Calcul2Test {

	@Test
	public void testSoustraire() {
		assertEquals(3, Calcul2.soustraire(5, 2));
	}

}
