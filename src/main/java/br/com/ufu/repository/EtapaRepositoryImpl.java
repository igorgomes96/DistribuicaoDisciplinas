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
import br.com.ufu.model.Etapa;
import br.com.ufu.model.Semestre;
@Path("/")
public class EtapaRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public EtapaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Etapa> listar() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"id, "
					+"codigo, "
					+"ativo, "
					+"descricao "
				+"FROM etapa "
				+"ORDER BY codigo DESC;");
		
		List<Object[]> rows = query.list();
		
		List<Etapa> etapas = new ArrayList<Etapa>();
		
		for (Object[] row : rows) {
			
			Etapa etapa = new Etapa();
			etapa.setId(Integer.parseInt(String.valueOf(row[0])));
			etapa.setCodigo(row[1].toString());
			etapa.setAtivo((Boolean) row[2]);
			etapa.setDescricao(row[3].toString());
			
			etapas.add(etapa);
		    
		}
		session.close();
		return etapas;
	}
	
	public void ativar(Etapa e) throws Exception {
		if(e != null && !"".equals(e.getId()) && e.getId() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("UPDATE Etapa SET ativo = :ativo WHERE id = :id")
					.setParameter("id", e.getId())
					.setParameter("ativo", e.getAtivo());
			query.executeUpdate();
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
}
