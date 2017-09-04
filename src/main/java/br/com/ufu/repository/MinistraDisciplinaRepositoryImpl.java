package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.ParamDef;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurma;
import br.com.ufu.model.DisciplinaTurmaHorario;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Horario;
import br.com.ufu.model.Ministra;
import br.com.ufu.model.MinistraDisciplina;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.TodasDisciplinas;
import br.com.ufu.model.Turma;
import br.com.ufu.model.TurmaDisciplina;
import br.com.ufu.service.DisciplinaAdminServiceImpl;
import br.com.ufu.service.MinistraDisciplinaAdminServiceImpl;
import br.com.ufu.service.TurmaAdminServiceImpl;
@Path("/")
public class MinistraDisciplinaRepositoryImpl implements MinistraDisciplinaAdminServiceImpl{
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public MinistraDisciplinaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	@SuppressWarnings("unchecked")
//	@Override
	public List<MinistraDisciplina> buscaSiape(String siape) {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"m.siape siape, "
					+"t.codigo_disc codigoDisciplina, "
					+"t.turma turma, "
					+"d.nome nomeDisciplina, "
					+"c.nome nomeCurso, "
					+"m.id_turma idTurma, "
					+"t.ano turmaAno, "
					+"t.semestre turmaSemestre "
				+"FROM "
					+"ministra m "
				+"JOIN turma t ON t.id = m.id_turma "
				+"JOIN disciplina d ON d.codigo = t.codigo_disc "
				+"JOIN curso c ON c.codigo = d.curso "
				+"WHERE m.siape = :siape "
				+"ORDER BY t.ano desc") 
				.setParameter("siape", siape);
		List<Object[]> rows = query.list();
		List<MinistraDisciplina> turmas = new ArrayList<MinistraDisciplina>();
		
		for (Object[] row : rows) {
			MinistraDisciplina t = new MinistraDisciplina();
			t.setSiape(row[0].toString());
			t.setCodigoDisc(row[1].toString());
			t.setTurma(row[2].toString());
			t.setNomeDisc(row[3].toString());
			t.setNomeCurso(row[4].toString());
			t.setTurmaId((Integer) row[5]);
			t.setTumaAno((Integer) row[6]);
			t.setTurmaSemestre((Short) row[7]);
			turmas.add(t);
		}
		session.close();
		return turmas;
	}


	@Override
	public void delete(MinistraDisciplina t) throws Exception {
		if(t != null &&
		!"".equals(t.getSiape()) && t.getSiape() != null &&
		!"".equals(t.getTurmaId()) && t.getTurmaId() != null){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(
				"DELETE FROM MinistraDisciplina "+
			    "WHERE id_turma = :turmaId "+
						"AND siape = :siape ")
				.setParameter("turmaId", t.getTurmaId()).setParameter("siape", t.getSiape());
//		session.delete(t);
		query.executeUpdate();
		tx.commit();
		session.close();
	} else {
		 throw new Exception("Preencha todos os campos obrigatórios.");
	}
		
	}
	
	@Override
	public void save(Ministra t) throws Exception {
			if(t != null &&
					!"".equals(t.getSiape()) && t.getSiape() != null &&
					!"".equals(t.getTurmaId()) && t.getTurmaId() != null){
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				Query query = session.createQuery(
						"INSERT into ministra (siape, id_turma) "
						+"(:siape, :turmaId)")
						.setParameter("siape", t.getSiape()).setParameter("turmaId", t.getTurmaId());
//				t.setSiape(t.getSiape());
//				t.setTurmaId(t.getTurmaId());
//				session.save(t);
				query.executeUpdate();
				tx.commit();
				session.close();
			} else {
				 throw new Exception("Preencha todos os campos obrigatórios.");
			}
	}
	
	public Integer validaTurma(Integer turmaId, String siape, Integer ano, Integer numeroSemestre) {
		Session session = sessionFactory.openSession();
		int count = ((Number)session.createSQLQuery(
//		Query query = session.createSQLQuery(
				"SELECT COUNT(t.id) as contador "
				+"FROM turma t "
				+"JOIN oferta o on o.id_turma = t.id "
				+"WHERE t.semestre = :semestre "
				+"and t.ano = :ano "
				+"and t.id = :id_turma " 
				+"and (o.dia,o.letra) IN ( "
				+"SELECT o2.dia, o2.letra "
				+"FROM ministra m2 "
				+"JOIN turma t2 ON t2.id = m2.id_turma "
				+"JOIN oferta o2 on o2.id_turma = t2.id "
				+"WHERE m2. siape = :siape "
				+"and t2.semestre = :semestre "
				+"and t2.ano = :ano)") 
				.setParameter("id_turma", turmaId).setParameter("siape", siape).setParameter("semestre", numeroSemestre).setParameter("ano", ano).uniqueResult()).intValue();
//		List<Object[]> rows = query.list();
//		List<MinistraDisciplina> turmas = new ArrayList<MinistraDisciplina>();
//		
//		for (Object[] row : rows) {
//			MinistraDisciplina t = new MinistraDisciplina();
//			t.setSiape(row[0].toString());
//			t.setCodigoDisc(row[1].toString());
//			t.setTurma(row[2].toString());
//			t.setNomeDisc(row[3].toString());
//			t.setNomeCurso(row[4].toString());
//			t.setTurmaId((Integer) row[5]);
//			turmas.add(t);
//		}
//		Integer count = query.getClass();
		session.close();
		return count;
	}
}
