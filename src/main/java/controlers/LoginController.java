package controlers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.User;
import beans.enums.Role;
import dao.ConnectionDB;
import dao.UserDao;
import validators.ValidateLoginField;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger myRootLogger = LogManager.getRootLogger();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "logout":
				request.getSession().invalidate();
				break;
			}
		}
		myRootLogger.info("Access à login depuis : " + request.getRemoteAddr());
		request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String message = ValidateLoginField.isNull(new User(login, password));

		String cible = "WEB-INF/views/login.jsp";
		try {

			if (message.isEmpty()) {
				Connection cnx = ConnectionDB.getConnection();
				User u = UserDao.findByLogin(login, cnx, true);
				if(u==null || !u.getPassword().equals(password))
					request.setAttribute("msg", "Erreur : authentification incorrecte !");
				else {
					request.getSession().setAttribute("isConnected", true);
					
					if(u.getRole().equals(Role.ADMIN)) {
						response.sendRedirect("admin/livre?page=1&max=15");
						return;
					}else
						cible="WEB-INF/views/espace-user.jsp";
					
				}
			}else
				request.setAttribute("msg", message);

		} catch (Exception e) {
			request.setAttribute("msg", "Erreur : "+e.getMessage());
		}
		request.getRequestDispatcher(cible).forward(request, response);
	}

}
