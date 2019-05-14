package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Livre;
import beans.enums.Genre;

public class LivreDao {

	public static List<Livre> readAll(Connection cnx, boolean closeCnx) throws Exception {
		List<Livre> livreList = new ArrayList<>();
		String sql = "SELECT id, titre, auteur, annee, genre FROM t_livres";
		PreparedStatement st = cnx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			livreList.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getDate("annee"),
					Genre.values()[rs.getInt("genre")]));
		}
		rs.close();

		if (closeCnx)
			cnx.close();

		return livreList;
	}
	
	public static List<Livre> readAll(int start, int max, Connection cnx, boolean closeCnx) throws Exception {
		List<Livre> livreList = new ArrayList<>();
		String sql = "SELECT id, titre, auteur, annee, genre FROM t_livres LIMIT ?,?";
		PreparedStatement st = cnx.prepareStatement(sql);
		st.setInt(1, start);
		st.setInt(2, max);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			livreList.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getDate("annee"),
					Genre.values()[rs.getInt("genre")]));
		}
		rs.close();

		if (closeCnx)
			cnx.close();

		return livreList;
	}
	
	public static long count(Connection cnx, boolean closeCnx) throws Exception {
		long nb = 0;
		String sql = "SELECT COUNT(id) FROM t_livres";
		PreparedStatement st = cnx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			nb = rs.getInt(1); //nb = rs.getInt("COUNT(id)");
		}
		rs.close();

		if (closeCnx)
			cnx.close();

		return nb;
	}
	

	public static Livre findById(int id, Connection cnx, boolean closeCnx) throws Exception {
		Livre lv = null;
		String sql = "SELECT id, titre, auteur, annee, genre FROM t_livres WHERE id=?";
		PreparedStatement st = cnx.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			lv = new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getDate("annee"),
					Genre.values()[rs.getInt("genre")]);
		}
		rs.close();

		if (closeCnx)
			cnx.close();

		return lv;
	}

	public static void insert(Livre livre, Connection cnx, boolean closeCnx) throws Exception {
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO t_livres(titre, auteur, annee, genre) VALUES(?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, livre.getTitre());
		ps.setString(2, livre.getAuteur());
		ps.setDate(3, new java.sql.Date(livre.getAnnee().getTime()));
		ps.setInt(4, livre.getGenre().ordinal());
		int resInsert = ps.executeUpdate();
		if (resInsert != 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				livre.setId(rs.getInt(1));
			}
		}
		if (closeCnx)
			cnx.close();
	}

	public static int delete(int id, Connection cnx, boolean closeCnx) throws Exception {
		PreparedStatement ps = cnx.prepareStatement("DELETE FROM t_livres WHERE id=?");
		ps.setInt(1, id);
		int res = ps.executeUpdate();
		if (closeCnx)
			cnx.close();
		return res;
	}
	
	public static int update(Livre livre, Connection cnx, boolean closeCnx) throws Exception {
		PreparedStatement ps = cnx.prepareStatement("UPDATE t_livres SET titre=?, auteur=?, annee=?, genre=? WHERE id=?");

		ps.setString(1, livre.getTitre());
		ps.setString(2, livre.getAuteur());
		ps.setDate(3, new java.sql.Date(livre.getAnnee().getTime()));
		ps.setInt(4, livre.getGenre().ordinal());
		ps.setInt(5, livre.getId());
		
		int res = ps.executeUpdate();
		
		if (closeCnx)
			cnx.close();
		return res;
	}

}
