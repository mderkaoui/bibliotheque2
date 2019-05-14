package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Livre;
import beans.User;
import beans.enums.Genre;
import beans.enums.Role;

public class UserDao {
	
	public static List<User> findAll(Connection cnx, boolean closeCnx) throws Exception {
		List<User> users = new ArrayList<>();
		String sql = "SELECT id, login, password, role FROM t_users";
		PreparedStatement st = cnx.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			users.add(new User(rs.getInt("id"),
							   rs.getString("login"), 
							   rs.getString("password"),
							   Role.values()[rs.getInt("role")]));
		}
		rs.close();
		if (closeCnx)
			cnx.close();

		return users;
	}
	
	public static User findByLogin(String login, Connection cnx, boolean closeCnx) throws Exception {
		User u = null;
		String sql = "SELECT id, login, password, role FROM t_users WHERE login=?";
		PreparedStatement st = cnx.prepareStatement(sql);
		st.setString(1,login);
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			u = new User(rs.getInt("id"),
							   rs.getString("login"), 
							   rs.getString("password"),
							   Role.values()[rs.getInt("role")]);
		}
		rs.close();
		if (closeCnx)
			cnx.close();

		return u;
	}
	
	public static int insert(User u, Connection cnx, boolean closeCnx) throws Exception {
		int nb = 0;
		String sql = "INSERT INTO t_users(login, password, role) VALUES(?,?,?)";
		PreparedStatement st = cnx.prepareStatement(sql);
		st.setString(1,u.getLogin());
		st.setString(2,u.getPassword());
		st.setInt(3,u.getRole().ordinal());
		nb = st.executeUpdate();
		
		if (closeCnx)
			cnx.close();

		return nb;
	}
	
}
