package dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class CalculTest extends TestCase {

	public CalculTest() {
		System.out.println("inside constructor");
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("setup before class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("tear down after");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}

	@Test
	public void testSomme() {
		System.out.println("== testSomme");
		assertEquals(3, Calcul.somme(2, 1));
	}

	@Test
	public void testMultiplier() {
		System.out.println("== testMultiplier");
		//fail("Not yet implemented");
	}

}
