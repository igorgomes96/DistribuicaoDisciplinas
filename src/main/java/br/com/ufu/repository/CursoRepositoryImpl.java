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

import br.com.ufu.service.CursoServiceImpl;
@Path("/")
public class CursoRepositoryImpl implements CursoServiceImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public CursoRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> findAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT c.*"
				+"FROM curso c "
				+"ORDER BY c.nome");
		List<Object[]> rows = query.list();
		List<Curso> cursos = new ArrayList<Curso>();
		
		for (Object[] row : rows) {
			Curso c = new Curso();
			c.setCodigo(row[0].toString());
			c.setNome(row[1].toString());
			c.setUnidade(row[2].toString());
			cursos.add(c);
		}
		session.close();
		return cursos;
	}

	@Override
	public void save(Curso c) throws Exception {
		if(c != null &&
				!"".equals(c.getCodigo()) && c.getCodigo() != null &&
				!"".equals(c.getNome()) && c.getNome() != null &&
				!"".equals(c.getUnidade()) && c.getUnidade() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(c);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	@Override
	public void delete(Curso c) throws Exception {
		if(c != null &&
				!"".equals(c.getCodigo()) && c.getCodigo() != null &&
				!"".equals(c.getNome()) && c.getNome() != null &&
				!"".equals(c.getUnidade()) && c.getUnidade() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(c);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
	
	@Override
	public void update(Curso c) throws Exception {
		if(c != null &&
				!"".equals(c.getCodigo()) && c.getCodigo() != null &&
				!"".equals(c.getNome()) && c.getNome() != null &&
				!"".equals(c.getUnidade()) && c.getUnidade() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(c);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

}
