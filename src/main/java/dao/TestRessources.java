package dao;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.util.Properties;

import org.junit.Test;

import junit.framework.TestCase;

public class TestRessources extends TestCase {

	@Test
	public void test() throws Exception {
		// lire le fichier app.properties
		Properties p = new Properties();

		try (BufferedInputStream bis = new BufferedInputStream(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"))) {
			p.load(bis);
			assertEquals("45", p.getProperty("blablabla"));
		}
		//TODO ajout d'un traitement plus strict sur les exceptiosn 
		
		// lire la clé blablabla
		// vérifier que sa valeur=45
	}

}
