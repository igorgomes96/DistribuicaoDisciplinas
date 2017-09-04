package br.com.ufu.repository;

import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Oferta;
@Path("/")
public class OfertaRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public OfertaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	public void removeHorarios(Long turmaId) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"DELETE FROM Oferta "+
				"WHERE turmaId = :turmaId ")
				.setParameter("turmaId", turmaId);
		
		query.executeUpdate();
		
		tx.commit();
		
		session.close();
		
	}
	
	
	public void savarHorarios(List<Oferta> ofertas) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		for (Oferta oferta : ofertas) {
		
			Long id = (Long) session.save(oferta);
			
			oferta.setId(id);
		
		}
		
		tx.commit();

		session.close();
		
	}

}
