package controlers;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Livre;
import beans.enums.Genre;
import dao.ConnectionDB;
import dao.LivreDao;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/admin/livre")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("adminController : doGet");

		request.setAttribute("genres", Genre.values());

		String action = request.getParameter("action");
		Connection cnx = null;
		int id = 0;
		try {
			cnx = ConnectionDB.getConnection();

			if (action != null) {
				switch (action) {
				case "modifier":
					id = Integer.parseInt(request.getParameter("id"));
					Livre livTrouve = LivreDao.findById(id, cnx, false);
					request.setAttribute("livTrouve", livTrouve);
					break;
				case "supprimer":
					id = Integer.parseInt(request.getParameter("id"));
					int res = LivreDao.delete(id, cnx, false);
					if (res != 0) {
						request.setAttribute("msg", "Suppression effectué du livre : " + id);
					}
					break;
				case "exportcsv" :
					//si on veut un download, on écrit directement dans la réponse
					//en modifiant l'entête Content-Disposition pour la rendre en attachment.
					response.setContentType("text/csv");
					response.setHeader("Content-Disposition", "attachment;filename=livres.csv");
					ServletOutputStream out = response.getOutputStream();
					List<Livre> lst = LivreDao.readAll(cnx, false);
					for (Livre x : lst) {
						out.write(x.toCsv().getBytes());
					}
					out.close();
					return;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erreur : " + e.getMessage());
		}

		int page = 1;
		int max = 15;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			max = Integer.parseInt(request.getParameter("max"));
		} catch (Exception e) {
			//ignore
		}

		try {

			boolean suivExist = (page * max) < LivreDao.count(cnx, false);
			request.setAttribute("suivExist", suivExist);
			
			request.setAttribute("page", page);
			request.setAttribute("max", max);

			int start = (page - 1) * max;
			List<Livre> livres = LivreDao.readAll(start, max, cnx, true);
			request.setAttribute("livres", livres);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erreur : " + e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/views/espace-admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("adminController : doPost");
		try {

			String titre = request.getParameter("titre");

			String auteur = request.getParameter("auteur");

			String anneeStr = request.getParameter("annee");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date annee = sdf.parse(anneeStr);

			String genreStr = request.getParameter("genre");
			Genre genre = Genre.values()[Integer.parseInt(genreStr)];

			Connection cnx = ConnectionDB.getConnection();
			
			//Récupération du champ caché
			int id=0;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			}catch (Exception e) {
				// ignore id=0
			}
			
			Livre livre = new Livre(id,titre, auteur, annee, genre);
			
			if(livre.getId()==0)//insertion
				LivreDao.insert(livre, cnx, true);
			else //modification
				LivreDao.update(livre, cnx, true);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Erreur : " + e.getMessage());
		}

		doGet(request, response);

	}

}
