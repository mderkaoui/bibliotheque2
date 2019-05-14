package controlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Livre;
import beans.User;
import beans.enums.Genre;
import beans.enums.Role;
import dao.ConnectionDB;
import dao.LivreDao;
import dao.UserDao;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/test-data")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Connection cnx = ConnectionDB.getConnection();
			UserDao.insert(new User(0,"arnaud", "password", Role.ADMIN),cnx,false);
			UserDao.insert(new User(0,"marcel", "123456", Role.USER),cnx,false);
			UserDao.insert(new User(0,"george", "password", Role.USER),cnx,false);
			UserDao.insert(new User(0,"Solène", "password", Role.USER),cnx,false);
			UserDao.insert(new User(0,"Margot", "password", Role.USER),cnx,false);
			UserDao.insert(new User(0,"Rachid", "password", Role.USER),cnx,false);
			UserDao.insert(new User(0,"anonymous", "", Role.ANONYMOUS),cnx,false);
			
			for (int i = 1; i <= 40; i++) {
				LivreDao.insert(new Livre("livre"+i, "auteur"+i, new Date(), Genre.HORREUR), cnx, false);
			}
			
			cnx.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
