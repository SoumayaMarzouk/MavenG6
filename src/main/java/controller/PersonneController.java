package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Departement;
import model.Personne;
import model.Projet;

import java.io.IOException;
import java.util.List;

import dao.DepartementDAO;
import dao.PersonneDAO;
import dao.ProjetDAO;

/**
 * Servlet implementation class PersonneController
 */
@WebServlet("/PersonneController")
public class PersonneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PersonneDAO pdao;
	private ProjetDAO prDao;
	private DepartementDAO deptDao;
	public PersonneController() {
		super();
		pdao=new PersonneDAO();
		prDao=new ProjetDAO();
		deptDao=new DepartementDAO();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message="";
		if(request.getParameter("id")!=null) {
		message="suppression impossible";
		long id=Long.parseLong(request.getParameter("id"));
		if(pdao.delete(id))
			message="suppression de la personne "+id+" avec succes";
		}
		if(request.getParameter("idUpdate")!=null) {
			long id=Long.parseLong(request.getParameter("idUpdate"));
			Personne p=pdao.findById(id);
			request.setAttribute("personne",p);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/PersonneUpdateJstl.jsp");
			rd.forward(request, response); 
			}else if(request.getParameter("formCreate")!=null) {
				
				List<Projet> pr=prDao.findAll();
				List<Departement> dept=deptDao.findAll();
				request.setAttribute("projets",pr);
				request.setAttribute("depts",dept);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/PersonneCreateJstl.jsp");
				rd.forward(request, response); 
				}
		
		else {
		List<Personne> results = pdao.findAll();
		request.setAttribute("listPersonne",results);
		request.setAttribute("message",message);
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/personneViewJstl.jsp");
		rd.forward(request, response); 
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message="";
		if(request.getParameter("create")!=null) {
		message="creation impossible";
		String cin=request.getParameter("cin");
		String prenom=request.getParameter("prenom");
		String nom=request.getParameter("nom");
		long dept=Long.parseLong(request.getParameter("dept"));
		String pr[]=request.getParameterValues("projet");
		long[] projet=new long[10];
		int i=0;
		for(String s:pr) {
			System.out.print(s);
		projet[i]=Long.parseLong(s);
		i++;
		}
		Personne p=new Personne(cin,nom,prenom);
		if(pdao.create(p,dept,projet))
			   message="personne "+cin+" cree avec succes";
		}
		else if(request.getParameter("update")!=null) {
			message="mise a jour impossible";
			Long id=Long.parseLong(request.getParameter("id"));
			String cin=request.getParameter("cin");
			String prenom=request.getParameter("prenom");
			String nom=request.getParameter("nom");
			if(pdao.update(id,cin,nom,prenom)) 
				     message="personne "+cin+" mis a jour avec succes";
			}
		List<Personne> results;
		if(request.getParameter("search")!=null) {
			
			Long id=Long.parseLong(request.getParameter("id"));
			message="Personne filtrées par projet "+id;
			results =pdao.getByProject(id);
		}
		else {
			results = pdao.findAll();
		}
			request.setAttribute("listPersonne",results);
			request.setAttribute("message",message);
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/personneViewJstl.jsp");
			rd.forward(request, response); 
			}

	

}
