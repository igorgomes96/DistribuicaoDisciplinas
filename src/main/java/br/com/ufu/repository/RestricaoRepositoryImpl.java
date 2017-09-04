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
import br.com.ufu.model.Restricao;
import br.com.ufu.model.Semestre;
import br.com.ufu.view.RestricaoDTO;
@Path("/")
public class RestricaoRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public RestricaoRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<RestricaoDTO> listar() {
		
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT "
					+"r.siape, "
					+"r.dia, "
					+"r.letra,"
					+"s.descricao,"
					+"CONCAT(h.hora_inicio,' - ',h.hora_fim),"
					+"p.nome "
				+"FROM restricoes r "
				+"JOIN semana s ON s.dia = r.dia "
				+"JOIN horario h ON h.letra = r.letra "
				+"JOIN professor p ON p.siape = r.siape "
				+"ORDER BY r.dia, r.letra, p.nome;");
		
		List<Object[]> rows = query.list();
		
		List<RestricaoDTO> restricoes = new ArrayList<RestricaoDTO>();
		
		for (Object[] row : rows) {
			
			RestricaoDTO restricao = new RestricaoDTO();
			Restricao r = new Restricao();
			r.setSiape((String) row[0].toString());
			r.setDia((String) row[1].toString());
			r.setLetra((String) row[2].toString());
			restricao.setRestricao(r);
			
			restricao.setDiaNome((String) row[3]);
			restricao.setHorarioNome((String) row[4]);
			restricao.setProfessorNome((String) row[5]);
			
			restricoes.add(restricao);
		}

		session.close();
		return restricoes;
 
	}
	
	public void save(Restricao s) throws Exception {
		if(s != null &&
				!"".equals(s.getSiape()) && s.getSiape() != null &&
				!"".equals(s.getDia()) && s.getDia() != null &&
				!"".equals(s.getLetra()) && s.getLetra() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(s);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	public void delete(Restricao s) throws Exception {
		if(s != null &&
				!"".equals(s.getSiape()) && s.getSiape() != null &&
				!"".equals(s.getDia()) && s.getDia() != null &&
				!"".equals(s.getLetra()) && s.getLetra() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(s);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
	
}
