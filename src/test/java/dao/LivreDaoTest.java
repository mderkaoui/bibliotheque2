package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import beans.Livre;
import beans.enums.Genre;

public class LivreDaoTest {

	@Test
	public void testReadAllConnectionBoolean() {
		try {
			Connection cnx = ConnectionDB.getConnection();
			List<Livre> lv = LivreDao.readAll(cnx, true);
			assertNotEquals(0, lv.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testReadAllIntIntConnectionBoolean() {
		try {
			Connection cnx = ConnectionDB.getConnection();
			//find avec une limitation de résultats
			List<Livre> lv = LivreDao.readAll(0, 5, cnx, true);
			assertEquals(5, lv.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCount() throws Exception {
		Connection cnx = ConnectionDB.getConnection();
		long nb = LivreDao.count(cnx, true);
		assertNotEquals(0, nb);
		//ou mettre un equals avec le nombre réel en bdd
		//attention : ce nombre ne sera plus valable après des insertions
	}

	@Test
	public void testFindById() throws Exception {
		Connection cnx = ConnectionDB.getConnection();
		Livre lv = LivreDao.findById(43, cnx, true);
		assertEquals("JAVA ", lv.getTitre());
		assertEquals("Moh", lv.getAuteur());
	}

	@Test
	public void testInsertAndUpdateAndDelete() throws Exception {
		Connection cnx = ConnectionDB.getConnection();
		//Test insertion		
		Livre liv = new Livre("Games of Java", "Mehdi", new Date(), Genre.FANTAISIE);
		LivreDao.insert(liv, cnx, false);
		assertNotEquals(0, liv.getId());//après insertion, l'id n'est pas à 0 car auto_increment
		
		//Test modification
		liv.setTitre("Games of Maven");
		int nb = LivreDao.update(liv, cnx, false);
		assertNotEquals(0, nb); // ou assertEquals(1,nb);
		
		//Test recherche : on recherche celui inséré/modifié précédemment et vérifier que le titre correspond
		Livre livTrouve = LivreDao.findById(liv.getId(), cnx, true);
		assertEquals(liv.getTitre(), livTrouve.getTitre());
	}
}
