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

import br.com.ufu.model.Professor;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.Turma;
import br.com.ufu.view.FilaProfessorViewDTO;
@Path("/")
public class FilaProfessorRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public FilaProfessorRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FilaProfessorViewDTO> listaFilaPorSiape(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
				  +"f.id idFila, "
				  +"f.siape siape, "
				  +"f.codigo_disc codigoDisciplina, "
				  +"f.pos posicao, "
				  +"f.prioridade prioridade, "
				  +"f.status status, "
				  +"f.periodo_preferencial periodoPreferencial, "
				  +"f.qte_maximo quantidadeMaximo, "
				  +"f.qte_ministrada quantidadeMinistrada, "
				  +"d.nome nomDisc, "
				  +"c.nome nomCurso "
				+"FROM fila f "
				+"JOIN disciplina d ON d.codigo = f.codigo_disc "
				+"JOIN curso c ON c.codigo = d.curso "
				+"JOIN professor p ON f.siape = p.siape "
				+"WHERE f.siape = :siape AND f.status != 13 "
				+"AND ano = :ano "
				+"AND semestre = :semestre "
				+"ORDER BY f.prioridade;")
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<FilaProfessorViewDTO> filaDisciplinaProfessores = new ArrayList<FilaProfessorViewDTO>();
		
		for (Object[] row : rows) {
			
			FilaProfessorViewDTO f = new FilaProfessorViewDTO();

			f.setIdFila(String.valueOf(row[0]));
			f.setSiape((String) row[1]);
			f.setCodigoDisc((String) row[2]);
			f.setPos((Integer)row[3]);
			f.setPrioridade((Integer) row[4]);
			f.setStatus((Integer)row[5]);
			f.setPeriodo((Boolean) row[6]);
			f.setQteMaximo((Integer) row[7]);
			f.setQteMinistrada((Integer)row[8]);
			f.setNomeDisc((String) row[9]);
			f.setNomCurso((String) row[10]);
			
			filaDisciplinaProfessores.add(f);
			
		    
		}

		session.close();

		return filaDisciplinaProfessores;
		
	}
	
	public void removeDisciplinasFila(FilaProfessorViewDTO p, Semestre semestre) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"DELETE Fila "
				+"WHERE siape = :siape "
				+"AND ano = :ano "
				+"AND semestre = :semestre "
				+"AND codigo_disc = :codDisc ")
				.setParameter("siape", p.getSiape().trim())
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre())
				.setParameter("codDisc", p.getCodigoDisc().trim());
		
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	
//	public void save(FilaProfessorViewDTO d, Semestre semestre) throws Exception {
//		if(d != null &&
//				!"".equals(d.getPrioridade()) && d.getPrioridade() != null &&
//				!"".equals(d.getQteMinistrada()) && d.getQteMinistrada() != null &&
//				!"".equals(d.getQteMaximo()) && d.getQteMaximo() != null &&
//				!"".equals(d.getStatus()) && d.getStatus() != null &&
//				!"".equals(d.getPeriodo()) && d.getPeriodo() != null &&
//				!"".equals(d.getSiape()) && d.getSiape() != null &&
//				!"".equals(d.getCodigoDisc()) && d.getCodigoDisc() != null &&
//				!"".equals(semestre.getAno()) && semestre.getAno() != null &&
//				!"".equals(semestre.getSemestre()) && semestre.getSemestre() != null){
//			FilaProfessorViewDTO t = new FilaProfessorViewDTO();
//			t.setPrioridade(d.getPrioridade());
//			t.setQteMinistrada(d.getQteMinistrada());
//			t.setQteMaximo(d.getQteMaximo());
//			t.setStatus(d.getStatus());
//			t.setPeriodo(d.getPeriodo());
//			t.setSiape(d.getSiape());
//			t.setCodigoDisc(d.getCodigoDisc());
//			t.setAno(semestre.getAno());
//			t.setSemestre(semestre.getSemestre());
//			Session session = sessionFactory.openSession();
//			Transaction tx = session.beginTransaction();
//			session.update(t);
//			tx.commit();
//			session.close();
//		} else {
//			 throw new Exception("Preencha todos os campos obrigatórios.");
//		}
//	}
	public void save(FilaProfessorViewDTO d, Semestre semestre) throws Exception {
		if(d != null &&
				!"".equals(d.getPrioridade()) && d.getPrioridade() != null &&
				!"".equals(d.getQteMinistrada()) && d.getQteMinistrada() != null &&
				!"".equals(d.getQteMaximo()) && d.getQteMaximo() != null &&
				!"".equals(d.getStatus()) && d.getStatus() != null &&
				!"".equals(d.getPeriodo()) && d.getPeriodo() != null &&
				!"".equals(d.getSiape()) && d.getSiape() != null &&
				!"".equals(d.getCodigoDisc()) && d.getCodigoDisc() != null &&
				!"".equals(semestre.getAno()) && semestre.getAno() != null &&
				!"".equals(semestre.getSemestre()) && semestre.getSemestre() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery(
					"UPDATE Fila "
					+"SET prioridade = :prioridade , "
					+"qte_ministrada = :qteMinistrada , "
					+"qte_maximo = :qteMaximo , "
					+"status = :status , "
					+"periodo_preferencial = :periodo "
					+" WHERE siape = :siape "
					+" AND codigo_disc = :codDisc "
					+" AND ano = :ano "
					+" AND semestre = :semestre ")
					.setParameter("prioridade", d.getPrioridade())
					.setParameter("qteMinistrada", d.getQteMinistrada())
					.setParameter("qteMaximo", d.getQteMaximo())
					.setParameter("status", d.getStatus())
					.setParameter("periodo", d.getPeriodo())
					.setParameter("siape", d.getSiape().trim())
					.setParameter("codDisc",d.getCodigoDisc().trim())
					.setParameter("ano", semestre.getAno())
					.setParameter("semestre", semestre.getSemestre());
			
			query.executeUpdate();
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
}
