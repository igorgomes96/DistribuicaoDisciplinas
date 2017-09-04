package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.FilaTurmaNew;
@Path("/")
public class FilaTurmaRepositoryImpl {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public FilaTurmaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	public List<FilaTurmaNew> FindAllFilaTurma() {
		Session session = sessionFactory.openSession();
		List<FilaTurmaNew> lista = new ArrayList<FilaTurmaNew>();
		lista = session.createQuery("from FilaTurmaNew").list();
		session.close();
		return lista;
	}
	
}
