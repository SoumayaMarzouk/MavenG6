package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Departement;
import model.Projet;

import java.io.IOException;
import java.util.List;

import dao.ProjetDAO;

/**
 * Servlet implementation class ProjetController
 */
@WebServlet("/ProjetController")
public class ProjetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       ProjetDAO prDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjetController() {
        super();
        prDao=new ProjetDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object[]> pr=prDao.findNb();
		request.setAttribute("projets",pr);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/ProjetView.jsp");
		rd.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
