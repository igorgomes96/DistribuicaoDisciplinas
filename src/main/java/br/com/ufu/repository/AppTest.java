/*package br.com.ufu.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Professor2;

public class AppTest {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	@SuppressWarnings("unchecked")
	public List<Professor2> testApp() {
		
		Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		Session session = sessionFactory.openSession();
		//session.beginTransaction();

		List<Professor2> professores = session.createCriteria(Professor2.class).list();
		
		//session.getTransaction().commit();
		session.close();

		return professores;
	}

}
*/