package x.exemplo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DisciplinaDAO {
	
	
	String CONSULTA_TODAS 	  = "disciplina.todas";
	String CONSULTA_PORTITULO = "disciplina.porTitulo";

	private EntityManagerFactory factory;
	private EntityManager em;


	public DisciplinaDAO() {
		super();
		if (factory == null)
			this.inicializa();
	}

	public void inicializa() {
		factory = Persistence.createEntityManagerFactory("dbEscola");
		em = factory.createEntityManager();
	}


	public boolean inserir(Disciplina obj) {
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletar(Disciplina obj) {
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Disciplina obj) {
		try {
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public Disciplina getPeloId(int id) {

		try {
			Disciplina obj = em.find(Disciplina.class, id);
			return obj;
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Disciplina> getTodas() {

		try {
			  Query query = em.createNamedQuery(CONSULTA_TODAS);
			  List<Disciplina> lista = query.getResultList();
			  return lista;

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Disciplina getPorTitulo(String titulo) {

		try {
			/*
			 Query query = em.createQuery("from TB_DISCIPLINA where titulo like :nome"); // a clausula select é dispensável			 
		     query.setParameter("titiulo", "%" + name + "%");
		     */			
			 Query query = em.createNamedQuery(CONSULTA_PORTITULO);
			 query.setParameter("t", titulo);
		     Disciplina obj = (Disciplina) query.getSingleResult();
			return obj;
		} catch (NoResultException e) {
			return null;
		}
	}
	

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();

		if (factory != null)
			factory.close();

		if (em != null)
			em.close();
	}
	
	
	
	

}