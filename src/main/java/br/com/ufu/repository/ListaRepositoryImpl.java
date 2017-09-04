package br.com.ufu.repository;

import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Fila;
@Path("/")
public class ListaRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private static Session session;
	
	public ListaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Fila> buscaPorSiape(String siape) {
		
		session = sessionFactory.openSession();

		List<Fila> Fila = session.createCriteria(Fila.class).add( Restrictions.eq("filaId.siape", siape ) ).addOrder( Order.asc("prioridade") ).list();

		session.close();

		return Fila;
	}

}
