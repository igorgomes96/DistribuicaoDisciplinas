package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.Date;
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
import br.com.ufu.model.Professor;
import br.com.ufu.model.Semestre;
import br.com.ufu.view.RltProfessorDTO;
@Path("/")
public class RltProfessorRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public RltProfessorRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RltProfessorDTO> listar(Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.nome as NomeProfessor, "
					+"d.codigo, "
					+"t.turma, "
					+"d.nome as Nomedisc, "
					+"c.nome as NomeCurso, "
					+"ch as CargaHoraria, "
					+"'(' || f.qte_ministrada || '/' || f.qte_maximo || ')' as Qte "
				+"FROM "
					+"ministra m "
					+"INNER JOIN turma t ON t.id = m.id_turma and t.ano = :ano and t.semestre = :semestre "
					+"INNER JOIN professor p ON p.siape = m.siape "
					+"INNER JOIN disciplina d ON d.codigo = t.codigo_disc "
					+"INNER JOIN curso c ON d.curso = c.codigo "
					+"LEFT JOIN fila f ON d.codigo = f.codigo_disc and f.siape = p.siape and f.ano = :ano and f.semestre = :semestre "
				+"ORDER BY p.nome")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<RltProfessorDTO> professores = new ArrayList<RltProfessorDTO>();
		
		for (Object[] row : rows) {

			RltProfessorDTO p = new RltProfessorDTO();
			
			p.setNomeProfessor((String) row[0]);
			p.setCodigo((String) row[1]);
			p.setTurma((String) row[2]);
			p.setNomeDisc((String) row[3]);
			p.setNomeCurso((String) row[4]);
			p.setCargaHoraria((Integer) row[5]);
			p.setQte((String) row[6]);
			
			professores.add(p);
		    
		}

		session.close();

		return professores;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RltProfessorDTO> listarCurso(Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.nome as NomeProfessor, "
					+"d.codigo, "
					+"t.turma, "
					+"d.nome as Nomedisc, "
					+"c.nome as NomeCurso, "
					+"ch as CargaHoraria, "
					+"'(' || f.qte_ministrada || '/' || f.qte_maximo || ')' as Qte "
				+"FROM "
					+"ministra m "
					+"INNER JOIN turma t ON t.id = m.id_turma and t.ano = :ano and t.semestre = :semestre "
					+"INNER JOIN professor p ON p.siape = m.siape "
					+"INNER JOIN disciplina d ON d.codigo = t.codigo_disc "
					+"INNER JOIN curso c ON d.curso = c.codigo "
					+"LEFT JOIN fila f ON d.codigo = f.codigo_disc and f.siape = p.siape and f.ano = :ano and f.semestre = :semestre "
				+"ORDER BY NomeCurso")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<RltProfessorDTO> professores = new ArrayList<RltProfessorDTO>();
		
		for (Object[] row : rows) {

			RltProfessorDTO p = new RltProfessorDTO();
			
			p.setNomeProfessor((String) row[0]);
			p.setCodigo((String) row[1]);
			p.setTurma((String) row[2]);
			p.setNomeDisc((String) row[3]);
			p.setNomeCurso((String) row[4]);
			p.setCargaHoraria((Integer) row[5]);
			p.setQte((String) row[6]);
			
			professores.add(p);
		    
		}

		session.close();

		return professores;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RltProfessorDTO> listarDisciplina(Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.nome as NomeProfessor, "
					+"d.codigo, "
					+"t.turma, "
					+"d.nome as Nomedisc, "
					+"c.nome as NomeCurso, "
					+"ch as CargaHoraria, "
					+"'(' || f.qte_ministrada || '/' || f.qte_maximo || ')' as Qte "
				+"FROM "
					+"ministra m "
					+"INNER JOIN turma t ON t.id = m.id_turma and t.ano = :ano and t.semestre = :semestre "
					+"INNER JOIN professor p ON p.siape = m.siape "
					+"INNER JOIN disciplina d ON d.codigo = t.codigo_disc "
					+"INNER JOIN curso c ON d.curso = c.codigo "
					+"LEFT JOIN fila f ON d.codigo = f.codigo_disc and f.siape = p.siape and f.ano = :ano and f.semestre = :semestre "
				+"ORDER BY Nomedisc ")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<RltProfessorDTO> professores = new ArrayList<RltProfessorDTO>();
		
		for (Object[] row : rows) {

			RltProfessorDTO p = new RltProfessorDTO();
			
			p.setNomeProfessor((String) row[0]);
			p.setCodigo((String) row[1]);
			p.setTurma((String) row[2]);
			p.setNomeDisc((String) row[3]);
			p.setNomeCurso((String) row[4]);
			p.setCargaHoraria((Integer) row[5]);
			p.setQte((String) row[6]);
			
			professores.add(p);
		    
		}

		session.close();

		return professores;
		
	}
}
