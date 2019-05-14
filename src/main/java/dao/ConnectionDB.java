package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

	private ConnectionDB() {
		
	}
	//Visibilité [mots-clé] typeRetour nomMethode(params)
	public       static     Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Properties p = new Properties();
			//on charge le fichier se trouvant dans java/main/resources
			p.load(Thread.currentThread()
						.getContextClassLoader()
						.getResourceAsStream("conf.properties"));
			//on accède aux valeurs par clé (méthode getProperty)
			Class.forName(p.getProperty("driver"));
			Connection cnx = DriverManager.getConnection(p.getProperty("url"), 
														p.getProperty("user"),
														p.getProperty("mdp"));
			return cnx;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
