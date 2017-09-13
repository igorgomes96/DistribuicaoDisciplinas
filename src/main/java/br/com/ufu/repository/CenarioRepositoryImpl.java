package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Cenario;

@Path("/")
public class CenarioRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public CenarioRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	public List<Cenario> getCenariosByNumBySemestre(int num, int idSemestre) {
		Session session = sessionFactory.openSession();
		List<Cenario> lista = new ArrayList<Cenario>();
		lista = session.createQuery("from Cenario c where c.cenarioPK.numCenario=" + num + " and c.cenarioPK.idSemestre="+idSemestre).list();
		session.close();
		return lista;
	}
	
	public List<Cenario> getCenariosBySemestre(int idSemestre) {
		Session session = sessionFactory.openSession();
		List<Cenario> lista = new ArrayList<Cenario>();
		lista = session.createQuery("from Cenario c where c.cenarioPK.idSemestre=" + idSemestre).list();
		session.close();
		return lista;
	}
	
	public void salvarCenarios(List<Cenario> cenarios) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		for (Cenario c:cenarios) {
			session.save(c);
		}
		
		tx.commit();
		session.close();
	}
	
	public void salvarCenario(Cenario cenario) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(cenario);
		tx.commit();
		session.close();
	}
	
	
}
