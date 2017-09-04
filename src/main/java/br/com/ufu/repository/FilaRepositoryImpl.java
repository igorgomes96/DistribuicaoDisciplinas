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

import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.view.FilaPosicaoDTO;
import br.com.ufu.view.FilaSemestreDTO;
@Path("/")
public class FilaRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public FilaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Fila buscaFila(String siape, String codDisciplina, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
						+"id, "
						+"siape, "
						+"codigo_disc, "
						+"pos, "
						+"prioridade, "
						+"qte_ministrada, "
						+"qte_maximo, "
						+"status, "
						+"ano, "
						+"semestre "
				+"FROM fila "
				+"WHERE "
						+"siape = :siape "
						+"AND codigo_disc = :codDisciplina "
						+"AND ano = :ano "
						+"AND semestre = :semestre ")
				.setParameter("siape", siape)
				.setParameter("codDisciplina", codDisciplina)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		Fila fila = null;
		
		for (Object[] row : rows) {
			
			fila = new Fila();
			
			fila.setId(Long.parseLong(String.valueOf(row[0])));
			fila.setSiape((String) row[1]);
			fila.setCodigoDisc((String) row[2]);
			fila.setPos((Integer) row[3]);
			fila.setPrioridade((Integer) row[4]);
			fila.setQteMinistrada((Integer) row[5]);
			fila.setQteMaximo((Integer) row[6]);
			fila.setStatus((Integer) row[7]);
			fila.setAno((Integer) row[8]);
			fila.setSemestre(Integer.parseInt(String.valueOf(row[9])));
		    
		}

		session.close();

		return fila;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FilaDisciplinaProfessor> listaFilaPorSiape(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
				  +"f.id idFila, "
				  +"f.siape siape, "
				  +"f.codigo_disc codigoDisciplina, "
				  +"f.pos posicao, "
				  +"f.prioridade prioridade, "
				  +"concat(f.qte_ministrada, '/', f.qte_maximo) quantidadeMinistrada, "
				  +"d.nome nomeDisciplina, "
				  +"c.codigo codigoCurso, "
				  +"c.nome nomeCurso, "
				  +"p.nome nomeProfessor, "
				  +"p.afastado statusProfessor "
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
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = new ArrayList<FilaDisciplinaProfessor>();
		
		for (Object[] row : rows) {
			
			FilaDisciplinaProfessor f = new FilaDisciplinaProfessor();

			f.setIdFila(String.valueOf(row[0]));
			f.setSiape((String) row[1]);
			f.setCodigoDisciplina((String) row[2]);
			f.setPosicao(String.valueOf(row[3]));
			f.setPrioridade(String.valueOf(row[4]));
			f.setQuantidadeMinistrada(String.valueOf(row[5]));
			f.setNomeDisciplina((String) row[6]);
			f.setCodigoCurso((String) row[7]);
			f.setNomeCurso((String) row[8]);
			f.setNomeProfessor((String) row[9]);
			f.setStatusProfessor((Boolean) row[10]);
			
			filaDisciplinaProfessores.add(f);
			
		    
		}

		session.close();

		return filaDisciplinaProfessores;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FilaSemestreDTO> listaFilaSemestre() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
				+ "f.ano, "
				+ "f.semestre, "
				+ "(SELECT status FROM semestres s WHERE f.ano = s.ano AND f.semestre = s.semestre )as status "
				+ "FROM fila f "
				+ "ORDER BY f.ano desc, f.semestre desc;");
		
		List<Object[]> rows = query.list();
		List<FilaSemestreDTO> filaSemestre = new ArrayList<FilaSemestreDTO>();
		
		for (Object[] row : rows) {
			FilaSemestreDTO f = new FilaSemestreDTO();
			f.setAno((Integer) row[0]);
			f.setSemestre((Short) row[1]);
			f.setStatus((Boolean) row[2]);
			filaSemestre.add(f);
		}

		session.close();
		return filaSemestre;
	};
	
	
	@SuppressWarnings("unchecked")
	public List<FilaDisciplinaProfessor> listaFilaPorCodigoDisciplina(String codigoDisciplina, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
				  +"f.id idFila, "
				  +"f.siape siape, "
				  +"f.codigo_disc codigoDisciplina, "
				  +"f.pos posicao, "
				  +"f.prioridade prioridade, "
				  +"concat(f.qte_ministrada, '/', f.qte_maximo) quantidadeMinistrada, "
				  +"d.nome nomeDisciplina, "
				  +"c.codigo codigoCurso, "
				  +"c.nome nomeCurso, "
				  +"p.nome nomeProfessor, "
				  +"p.afastado statusProfessor "
				+"FROM fila f "
				+"JOIN disciplina d ON d.codigo = f.codigo_disc "
				+"JOIN curso c ON c.codigo = d.curso "
				+"JOIN professor p ON f.siape = p.siape "
				+"WHERE f.codigo_disc = :codigoDisciplina AND f.status != 13 "
				+"AND ano = :ano "
				+"AND semestre = :semestre "
				+"ORDER BY f.pos;") 
				.setParameter("codigoDisciplina", codigoDisciplina)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = new ArrayList<FilaDisciplinaProfessor>();
		
		for (Object[] row : rows) {
			
			FilaDisciplinaProfessor f = new FilaDisciplinaProfessor();

			f.setIdFila(String.valueOf(row[0]));
			f.setSiape((String) row[1]);
			f.setCodigoDisciplina((String) row[2]);
			f.setPosicao(String.valueOf(row[3]));
			f.setPrioridade(String.valueOf(row[4]));
			f.setQuantidadeMinistrada(String.valueOf(row[5]));
			f.setNomeDisciplina((String) row[6]);
			f.setCodigoCurso((String) row[7]);
			f.setNomeCurso((String) row[8]);
			f.setNomeProfessor((String) row[9]);
			f.setStatusProfessor((Boolean) row[10]);
			
			filaDisciplinaProfessores.add(f);
			
		    
		}

		session.close();

		return filaDisciplinaProfessores;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<FilaPosicaoDTO> listaFilaDisciplina(String codDisciplina, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
						+"f.id, "
						+"f.siape, "
						+"f.codigo_disc, "
						+"f.pos, "
						+"f.prioridade, "
						+"f.qte_ministrada, "
						+"f.qte_maximo, "
						+"f.status, "
						+"f.ano, "
						+"f.semestre, "
						+"p.nome as nomeProf "
				+"FROM fila f "
				+" JOIN professor p ON p.siape = f.siape "
				+"WHERE "
						+"f.codigo_disc = :codDisciplina "
						+"AND f.ano = :ano "
						+"AND f.semestre = :semestre "
				+ " ORDER BY f.pos ")
				.setParameter("codDisciplina", codDisciplina)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<FilaPosicaoDTO> fila = new ArrayList<FilaPosicaoDTO>();
		
		for (Object[] row : rows) {
			
			FilaPosicaoDTO f = new FilaPosicaoDTO();
			
			f.setId(Long.parseLong(String.valueOf(row[0])));
			f.setSiape((String) row[1]);
			f.setCodigoDisc((String) row[2]);
			f.setPos((Integer) row[3]);
			f.setPrioridade((Integer) row[4]);
			f.setQteMinistrada((Integer) row[5]);
			f.setQteMaximo((Integer) row[6]);
			f.setStatus((Integer) row[7]);
			f.setAno((Integer) row[8]);
			f.setSemestre(Integer.parseInt(String.valueOf(row[9])));
			f.setNomeProf((String)row[10] );
			
			fila.add(f);
		}

		session.close();

		return fila;
	}
	
	public Integer buscaProximaPrioridadePorSiape(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT max(prioridade)+1 proxima_prioridade "
				+"FROM fila "
				+"WHERE siape = :siape "
				+"AND ano = :ano "
				+"AND semestre = :semestre ") 
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		Integer proximaPrioridade = (Integer) query.uniqueResult();

		session.close();

		return proximaPrioridade;
	}
	
	
	public Integer buscaProximaPosicao(String codDisciplina, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT max(pos)+1 proxima_posicao "
				+"FROM fila "
				+"WHERE codigo_disc = :codDisciplina "
				+"AND ano = :ano "
				+"AND semestre = :semestre ") 
				.setParameter("codDisciplina", codDisciplina)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		Integer proximaPosicao = (Integer) query.uniqueResult();

		session.close();

		return proximaPosicao;
	}
	
	
	public Fila salvar(Fila fila) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Long id = (Long) session.save(fila);
		
		fila.setId(id);
		
		tx.commit();

		session.close();
		
		return fila;
		
	}
	
	public void salvarList(List<Fila> fila){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		for ( int i=0; i<fila.size(); i++ ) {
			Long id = (Long) session.save(fila.get(i));
			fila.get(i).setId(id);
		    if ( i % 20 == 0 ) {
		        session.flush();
		        session.clear();
		    }
		}
		   
		tx.commit();
		session.close();
		
	}
	
	
	public int atualizaStatus(String siape, String codigoDisciplina, Integer status, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		
		Query query = session.createQuery(
				"UPDATE Fila "
				+"SET status = :status "
				+"WHERE siape = :siape AND codigoDisc = :codigo_disc "
				+"AND ano = :ano "
				+"AND semestre = :semestre ")
				.setParameter("status", status)
				.setParameter("siape", siape)
				.setParameter("codigo_disc", codigoDisciplina)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		result = query.executeUpdate();
		
		tx.commit();
		
		session.close();
		
		return result;
		
	}
	
	
	
	public boolean hasFilaAnoSemestre(FilaSemestreDTO filaSemestre) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"SELECT DISTINCT ano, semestre FROM Fila WHERE ano = :ano AND semestre = :semestre ")
				.setParameter("ano", filaSemestre.getAno().intValue())
				.setParameter("semestre", filaSemestre.getSemestre().intValue());
		
		boolean result = !query.list().isEmpty();
		
		tx.commit();
		session.close();
		return result;
		
	}
	
		
	public List<Fila> buscaTodosAnoSemestre(FilaSemestreDTO filaSemestre) {
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"SELECT f FROM Fila f WHERE f.ano = :ano AND f.semestre = :semestre ")
				.setParameter("ano", filaSemestre.getAnoOld().intValue())
				.setParameter("semestre", filaSemestre.getSemestreOld().intValue());
		
		List<Fila> result = query.list();
		tx.commit();
		session.close();
		return result;
		
	}
	
	public void removeDisciplinasFila(Semestre semestre){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"DELETE Fila "
				+"WHERE status = 13 "
				+"AND ano = :ano "
				+"AND semestre = :semestre ")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		query.executeUpdate();
		tx.commit();
		session.close();
	}
	
	
	public int atualizaPrioridade(List<Fila> filas, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		
		for (Fila fila : filas) {
		
			Query query = session.createQuery(
					"UPDATE Fila "
					+"SET prioridade = :prioridade "
					+"WHERE siape = :siape AND codigoDisc = :codigo_disc "
					+"AND ano = :ano "
					+"AND semestre = :semestre ")
					.setParameter("prioridade", fila.getPrioridade())
					.setParameter("siape", fila.getSiape())
					.setParameter("codigo_disc", fila.getCodigoDisc())
					.setParameter("ano", semestre.getAno())
					.setParameter("semestre", semestre.getSemestre());
			
			result = query.executeUpdate();
		
		}
		
		tx.commit();
		
		session.close();
		
		return result;
		
	}

}
