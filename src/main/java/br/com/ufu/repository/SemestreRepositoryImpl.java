package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Semestre;
@Path("/")
public class SemestreRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public SemestreRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Semestre buscarUltimoSemestre() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"ano, "
					+"semestre "
				+"FROM semestres "
				+"WHERE status = TRUE "
				+"ORDER BY ano DESC, semestre DESC "
				+"LIMIT 1;");
		
		List<Object[]> rows = query.list();
		
		Semestre semestre = new Semestre();
		
		for (Object[] row : rows) {
			
			semestre.setAno(Integer.parseInt(String.valueOf(row[0])));
			semestre.setSemestre(Integer.parseInt(String.valueOf(row[1])));
		    
		}

		session.close();

		return semestre;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Semestre> listar() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"id, "
					+"ano, "
					+"semestre, "
					+"status "
				+"FROM semestres "
				+"ORDER BY ano DESC, semestre DESC;");
		
		List<Object[]> rows = query.list();
		
		List<Semestre> semestres = new ArrayList<Semestre>();
		
		for (Object[] row : rows) {
			
			Semestre semestre = new Semestre();
			semestre.setId(Integer.parseInt(String.valueOf(row[0])));
			semestre.setAno(Integer.parseInt(String.valueOf(row[1])));
			semestre.setSemestre(Integer.parseInt(String.valueOf(row[2])));
			semestre.setStatus((Boolean) row[3]);
			
			semestres.add(semestre);
		    
		}

		session.close();
		
		return semestres;
 
	}
	
	public void save(Semestre s) throws Exception {
		if(s != null &&
				!"".equals(s.getAno()) && s.getAno() != null &&
				!"".equals(s.getSemestre()) && s.getSemestre() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	public void delete(Semestre s) throws Exception {
		if(s != null &&
				!"".equals(s.getAno()) && s.getAno() != null &&
				!"".equals(s.getSemestre()) && s.getSemestre() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(s);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
	
	public void ativar(Semestre s) throws Exception {
		if(s != null &&
				!"".equals(s.getAno()) && s.getAno() != null &&
				!"".equals(s.getSemestre()) && s.getSemestre() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("UPDATE Semestre SET status = false");
			query.executeUpdate();
			query = session.createQuery("UPDATE Semestre SET status = true WHERE ano = :ano AND semestre = :semestre")
					.setParameter("ano", s.getAno())
					.setParameter("semestre", s.getSemestre());
			query.executeUpdate();
			
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
	
	public void update(Semestre s) throws Exception {
		if(s != null &&
				!"".equals(s.getAno()) && s.getAno() != null &&
				!"".equals(s.getSemestre()) && s.getSemestre() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(s);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

}
