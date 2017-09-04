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

import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplina;
import br.com.ufu.model.FilaDisciplina2;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.FilaTurma;
import br.com.ufu.model.Professor3;
import br.com.ufu.model.Semestre;
@Path("/")
public class FilaDisciplinaRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public FilaDisciplinaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FilaDisciplina> buscaPorCodigoDisciplina(String codigoDisc, Long turmaId, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"select DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"p.nome nomeProfessor, "
					+"ft.pos, "
					+"ft.prioridade, "
					+"ft.qte_ministrada quantidadeMinistrada, "
					+"ft.qte_maximo "
				+"from fila_turma ft "
				+"JOIN turma t on t.id = ft.id_turma "
				+"JOIN professor p on p.siape = ft.siape "
				+"JOIN disciplina d ON d.codigo = ft.codigo_disc "
				+"WHERE ft.codigo_disc = :codigoDisciplina AND t.id = :turmaId AND t.ano = :ano AND t.semestre = :semestre "
				+"ORDER BY ft.pos, d.nome")
				.setParameter("codigoDisciplina", codigoDisc)
				.setParameter("turmaId", turmaId)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<FilaDisciplina> filaDisciplinas = new ArrayList<FilaDisciplina>();
		
		for (Object[] row : rows) {
			
			Disciplina d = new Disciplina();
			d.setCodigo((String) row[0]);
			d.setNome((String) row[1]);
			
			Professor3 p = new Professor3();
			p.setNome((String) row[2]);
			
			FilaDisciplina filaDisciplina = new FilaDisciplina();
			filaDisciplina.setPos((Integer) row[3]);
			filaDisciplina.setPrioridade((Integer) row[4]);
			filaDisciplina.setQteMinistrada((Integer) row[5]);
			filaDisciplina.setQteMaximo((Integer) row[6]);
			
			filaDisciplina.setDisciplina(d);
			filaDisciplina.setProfessor(p);
			
			filaDisciplinas.add(filaDisciplina);
		    
		}

		//List<FilaDisciplina> filaDisciplina = session.createCriteria(FilaDisciplina.class).add( Restrictions.eq("disciplina.codigo", codigoDisc ) ).addOrder( Order.asc("pos") ).list();

		session.close();

		return filaDisciplinas;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> buscaPorSiape(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"ft.siape siape, "
					+"ft.prioridade prioridade, "
					+"ft.pos pos, "
					+"ft.qte_ministrada qteMinistrada, "
					+"ft.qte_maximo qteMaxima, "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					//+"o.turma turma, "
					+"t.turma turma, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso "
				+"FROM "
					+"fila_turma ft "
				+"JOIN disciplina d ON ft.codigo_disc = d.codigo "
				//+"JOIN oferta o ON ft.codigo_disc = o.codigo_disc AND ft.turma = o.turma "
				+"JOIN turma t ON t.id = ft.id_turma "
				+"JOIN curso c ON c.codigo = d.curso "
				+"WHERE ft.siape = :siape AND t.ano = :ano AND t.semestre = :semestre "
				+"ORDER BY ft.prioridade") 
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<DisciplinaTurmaProfessor> filaProfessor = new ArrayList<DisciplinaTurmaProfessor>();
		
		for (Object[] row : rows) {
			
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();

			dtp.setSiape((String) row[0]);
			dtp.setPrioridade((Integer) row[1]);
			dtp.setPos((Integer) row[2]);
			dtp.setQteMinistrada((Integer) row[3]);
			dtp.setQteMaxima((Integer) row[4]);
			dtp.setCodigoDisciplina((String) row[5]);
			dtp.setNomeDisciplina((String) row[6]);
			dtp.setTurma((String) row[7]);
			dtp.setCodigoCurso((String) row[8]);
			dtp.setNomeCurso((String) row[9]);
			
			filaProfessor.add(dtp);
		    
		}

		session.close();

		return filaProfessor;
	}
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> buscaApenasPorSiape(String siape) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"ft.siape siape, "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					//+"o.turma turma, "
					+"t.turma turma, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso "
				+"FROM "
					+"fila_turma ft "
				+"JOIN disciplina d ON ft.codigo_disc = d.codigo "
				//+"JOIN oferta o ON ft.codigo_disc = o.codigo_disc AND ft.turma = o.turma "
				+"JOIN turma t ON t.id = ft.id_turma "
				+"JOIN curso c ON c.codigo = d.curso "
				+"WHERE ft.siape = :siape "
				+"ORDER BY d.codigo ") 
				.setParameter("siape", siape);
		
		List<Object[]> rows = query.list();
		
		List<DisciplinaTurmaProfessor> filaProfessor = new ArrayList<DisciplinaTurmaProfessor>();
		
		for (Object[] row : rows) {
			
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();

			dtp.setSiape((String) row[0]);
			dtp.setCodigoDisciplina((String) row[1]);
			dtp.setNomeDisciplina((String) row[2]);
			dtp.setTurma((String) row[3]);
			dtp.setCodigoCurso((String) row[4]);
			dtp.setNomeCurso((String) row[5]);
			
			filaProfessor.add(dtp);
		    
		}

		session.close();

		return filaProfessor;
	}
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> buscaPorSiapeDisciplina(String siape, String codDisciplina) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"ft.siape siape, "
					+"ft.prioridade prioridade, "
					+"ft.pos pos, "
					+"ft.qte_ministrada qteMinistrada, "
					+"ft.qte_maximo qteMaxima, "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso "
				+"FROM fila ft "
				+"JOIN disciplina d ON ft.codigo_disc = d.codigo "
				+"JOIN curso c ON c.codigo = d.curso "
				+"WHERE ft.siape = :siape AND d.codigo = :codDisciplina  "
				+"ORDER BY ft.prioridade") 
				.setParameter("siape", siape)
				.setParameter("codDisciplina", codDisciplina);
		
		List<Object[]> rows = query.list();
		
		List<DisciplinaTurmaProfessor> filaProfessor = new ArrayList<DisciplinaTurmaProfessor>();
		
		for (Object[] row : rows) {
			
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();

			dtp.setSiape((String) row[0]);
			dtp.setPrioridade((Integer) row[1]);
			dtp.setPos((Integer) row[2]);
			dtp.setQteMinistrada((Integer) row[3]);
			dtp.setQteMaxima((Integer) row[4]);
			dtp.setCodigoDisciplina((String) row[5]);
			dtp.setNomeDisciplina((String) row[6]);
			dtp.setTurma(null);
			dtp.setCodigoCurso((String) row[7]);
			dtp.setNomeCurso((String) row[8]);
			
			filaProfessor.add(dtp);
		    
		}

		session.close();

		return filaProfessor;
	}
	
	
	@SuppressWarnings("unchecked")
	public FilaTurma buscaFilaDisciplina(String siape, String codDisciplina, String turma) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
						+"siape, "
						+"codigo_disc, "
						+"pos, "
						+"prioridade, "
						+"qte_ministrada, "
						+"qte_maximo, "
						+"status, "
						+"ch "
				+"FROM fila_turma "
				+"WHERE "
						+"siape = :siape "
						+"AND codigo_disc = :codDisciplina "
						+"AND turma = :turma")
				.setParameter("siape", siape)
				.setParameter("codDisciplina", codDisciplina)
				.setParameter("turma", turma);
		
		List<Object[]> rows = query.list();
		
		FilaTurma filaTurma = null;
		
		for (Object[] row : rows) {
			
			filaTurma = new FilaTurma();
			
			filaTurma.setSiape((String) row[0]);
			filaTurma.setCodigoDisc((String) row[1]);
			filaTurma.setPos((Integer) row[2]);
			filaTurma.setPrioridade((Integer) row[3]);
			filaTurma.setQteMinistrada((Integer) row[4]);
			filaTurma.setQteMaximo((Integer) row[5]);
			filaTurma.setStatus((Integer) row[6]);
			filaTurma.setCh((Integer) row[7]);
		    
		}

		session.close();

		return filaTurma;
	}
	
	
	public Integer buscaProximaPrioridadePorSiape(String siape) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT max(prioridade)+1 proxima_prioridade "
				+"FROM fila_turma "
				+"WHERE siape = :siape ") 
				.setParameter("siape", siape);
		
		Integer proximaPrioridade = (Integer) query.uniqueResult();

		session.close();

		return proximaPrioridade;
	}
	
	
	public Integer buscaProximaPosicao(String codDisciplina, String turma) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT max(pos)+1 proxima_posicao "
				+"FROM fila_turma "
				+"WHERE codigo_disc = :codDisciplina AND turma = :turma") 
				.setParameter("codDisciplina", codDisciplina)
				.setParameter("turma", turma);
		
		Integer proximaPosicao = (Integer) query.uniqueResult();

		session.close();

		return proximaPosicao;
	}
	
	
	public int salvar(FilaTurma filaTurma) {
	
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		FilaDisciplina2 fd = new FilaDisciplina2();
		fd.setSiape(filaTurma.getSiape());
		fd.setCodigoDisc(filaTurma.getCodigoDisc());
		fd.setPos(filaTurma.getPos());
		fd.setPrioridade(filaTurma.getPrioridade());
		fd.setQteMaximo(filaTurma.getQteMaximo());
		fd.setQteMinistrada(filaTurma.getQteMinistrada());
		fd.setStatus(filaTurma.getStatus());
		//fd.setTurma(filaTurma.getTurma());
		fd.setCh(filaTurma.getCh());
		
		session.save(fd);
		
		tx.commit();
		/*
		Query query = session.createQuery(
				"INSERT INTO fila_turma (siape, codigo_disc, pos, prioridade, qte_ministrada, qte_maximo, status, turma, ch) "
				+" (:siape, :codigo_disc, :pos, prioridade, :qte_ministrada, :qte_maximo, :status, :turma, :ch)")
				.setParameter("siape", filaTurma.getSiape())
				.setParameter("codigo_disc", filaTurma.getCodigoDisc())
				.setParameter("pos", filaTurma.getPos())
				.setParameter("prioridade", filaTurma.getPrioridade())
				.setParameter("qte_ministrada", filaTurma.getQteMinistrada())
				.setParameter("qte_maximo", filaTurma.getQteMaximo())
				.setParameter("status", filaTurma.getStatus())
				.setParameter("turma", filaTurma.getTurma())
				.setParameter("ch", filaTurma.getCh());
		
		int result = query.executeUpdate();
		*/
		session.close();
		
		return 1;
		
	}
	
	
	public int atualizaPrioridades(List<FilaTurma> filaTurmas) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		
		for (FilaTurma filaTurma : filaTurmas) {
		
			Query query = session.createQuery(
					"UPDATE FilaDisciplina2 "
					+"SET prioridade = :prioridade "
					+"WHERE siape = :siape AND codigoDisc = :codigo_disc AND id_turma = :idTurma")
					.setParameter("prioridade", filaTurma.getPrioridade())
					.setParameter("siape", filaTurma.getSiape())
					.setParameter("codigo_disc", filaTurma.getCodigoDisc())
					.setParameter("idTurma", filaTurma.getIdTurma());
			
			result = query.executeUpdate();
		
		}
		
		tx.commit();
		
		session.close();
		
		return result;
		
	}
	
	public void atualizaFila(List<Fila> listaFila){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Fila f1 = listaFila.get(0);

		Query query = session.createQuery("UPDATE Fila SET pos = -1 * pos WHERE semestre = ? AND ano = ? AND codigoDisc = ? ")
				.setParameter(0, f1.getSemestre())
				.setParameter(1, f1.getAno())
				.setParameter(2, f1.getCodigoDisc());
		
			query.executeUpdate();
		
		for ( int i=0; i<listaFila.size(); i++ ) {
			query = session.createQuery(
					"UPDATE Fila "
					+"SET pos = :pos, qteMinistrada = :qteMinistrada, qteMaximo = :qteMaximo "
					+"WHERE id = :idFila ")
					.setParameter("pos", Integer.parseInt(listaFila.get(i).getPos().toString()))
					.setParameter("idFila", Long.valueOf(listaFila.get(i).getId()).longValue())
					.setParameter("qteMinistrada", listaFila.get(i).getQteMinistrada())
					.setParameter("qteMaximo", listaFila.get(i).getQteMaximo());
			query.executeUpdate();
		}
				
		tx.commit();
		session.close();
	}
	
	public int atualizaStatus(FilaTurma filaTurma) {
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		
		Query query = session.createQuery(
				"UPDATE FilaDisciplina2 "
				+"SET status = :status "
				+"WHERE siape = :siape AND codigoDisc = :codigo_disc AND turma = :turma")
				.setParameter("status", filaTurma.getStatus())
				.setParameter("siape", filaTurma.getSiape())
				.setParameter("codigo_disc", filaTurma.getCodigoDisc())
				.setParameter("turma", filaTurma.getTurma());
		
		result = query.executeUpdate();
		
		tx.commit();
		
		session.close();
		
		return result;
		
	}
}
