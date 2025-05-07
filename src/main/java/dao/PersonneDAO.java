package dao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Departement;
import model.Personne;
import model.Projet;
import util.HibernateUtil;

public class PersonneDAO {
	SessionFactory sessionFactory;
	public PersonneDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Personne findById(long id) {
		Session session=sessionFactory.openSession();
		Personne p=session.get(Personne.class, id);
		session.close(); 
		return p;
	}
	public boolean create(Personne p,long deptId,long[] projetId) {
		Session session=sessionFactory.openSession();
		Departement d=session.get(Departement.class, deptId);
		p.setDepartment(d);
		List<Projet> listProjets=new ArrayList<Projet>();
		for(long id:projetId) {
			listProjets.add(session.get(Projet.class, id));
		}
		p.setProjets(listProjets);
		Transaction tx=null;
		boolean success = false;
		try {
			tx = session.beginTransaction();
			session.persist(p);
			tx.commit();
			success=true;
		}
		catch (Exception e) { 
			if (tx!=null) 
				tx.rollback(); throw e; 
		}
		finally { session.close(); }
		return success;
	}

	public boolean update(Long id, String cin, String nom, String prenom) {
		Session session=sessionFactory.openSession();
		Personne p=session.get(Personne.class, id);
		boolean success = false;
		if(p!=null) {
			p.setCin(cin);
			p.setNom(nom);
			p.setPrenom(prenom);
			Transaction tx=null;
			try {
				tx = session.beginTransaction();
				session.persist(p);
				tx.commit();
				success=true;
			} catch (Exception e) { 
				if (tx!=null) 
					tx.rollback(); throw e; 
			}
			finally { session.close(); }
		} 
		return success;
	}
	public boolean delete(long id) {
		Session session=sessionFactory.openSession();
		Personne p=session.get(Personne.class, id);
		boolean success = false;
		if(p!=null) {
			Transaction tx=null;
			try {
				tx = session.beginTransaction();
				session.remove(p);
				tx.commit();
				success=true;
			}
			catch (Exception e) { 
				if (tx!=null) 
				tx.rollback(); throw e; 
			}
			finally { session.close(); }
		}
	return success;
	}


	public List<Personne> findAll(){
		   Session session=sessionFactory.openSession();
		   List<Personne> result = 
		 		session.createQuery("from Personne as p left join fetch p.projets", Personne.class).getResultList(); 
		   session.close(); 
		   return result;
		
		}
	public List<Personne> findByProject(long id){
		   Session session=sessionFactory.openSession();
		   List<Personne> result = 
		 		session.createQuery("from Personne as p left join fetch p.projets pr where pr.id="+id, Personne.class).getResultList(); 
		   session.close(); 
		   return result;
		
		}
	public List<Personne> getByProject(long id){
		   Session session=sessionFactory.openSession();
			Projet p=session.get(Projet.class, id);
		   List<Personne> result = p.getPersonnes();
		   System.out.print(result);
		 		 session.close(); 
		   return result;
		
		}
		}
