package br.com.ufu.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurma;
import br.com.ufu.model.DisciplinaTurmaHorario;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Horario;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.TodasDisciplinas;
import br.com.ufu.model.TurmaDisciplina;
import br.com.ufu.service.DisciplinaAdminServiceImpl;
@Path("/")
public class DisciplinaRepositoryImpl implements DisciplinaAdminServiceImpl{
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public DisciplinaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> listar() {
		
		Session session = sessionFactory.openSession();

		List<Disciplina> disciplinas = session.createCriteria(Disciplina.class).list();

		session.close();

		return disciplinas;
	}
	
	public Disciplina findDisciplina(String cod) {
		Session session = sessionFactory.openSession();
		Disciplina d = (Disciplina) session.get(Disciplina.class, cod);
		return d;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurma> listaDisciplina() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso "
				+"FROM "
					+"disciplina d "
				+"JOIN curso c ON d.curso = c.codigo "
				+"ORDER BY d.nome;");
		
		List<Object[]> rows = query.list();
		
		List<DisciplinaTurma> disciplinaTurmas = new ArrayList<DisciplinaTurma>();
		
		DisciplinaTurma dt = new DisciplinaTurma();
		
		for (Object[] row : rows) {
				
			dt = new DisciplinaTurma();
			
			dt.setCodigoDisciplina((String) row[0]);
			dt.setNomeDisciplina((String) row[1]);
			dt.setCodigoCurso((String) row[2]);
			dt.setNomeCurso((String) row[3]);
			
			disciplinaTurmas.add(dt);
		    
		}

		session.close();

		return disciplinaTurmas;
		
	}
		
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaHorario> listaDisciplinaTurmaHorario(Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
				  +"d.codigo codigoDisciplina, "
				  +"d.nome nomeDisciplina, "
				  +"d.ch_total cargaHorariaTotal, "
				  +"d.curso codigoCurso, "
				  +"c.nome nomeCurso, "
				  +"t.turma turma, "
				  +"s.descricao diaSemana, "
				  +"concat(h.hora_inicio, ' - ', h.hora_fim) horario, "
				  +"s.dia "
				+"FROM disciplina d "
				+"LEFT JOIN curso c on d.curso = c.codigo "
				+"LEFT JOIN turma t ON d.codigo = t.codigo_disc AND t.ano = :ano AND t.semestre = :semestre "
				+"LEFT JOIN oferta o ON o.id_turma = t.id "
				+"LEFT JOIN semana s ON o.dia = s.dia "
				+"LEFT JOIN horario h ON h.letra = o.letra "
				+"WHERE d.temfila = TRUE "
				+"ORDER BY d.nome, d.codigo, s.dia, horario, turma;")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());

		List<Object[]> rows = query.list();
		
		List<DisciplinaTurmaHorario> disciplinaTurmaHorarios = new ArrayList<DisciplinaTurmaHorario>();
		
		DisciplinaTurmaHorario dth = new DisciplinaTurmaHorario();
		
		for (Object[] row : rows) {
			
			if (dth.getCodigoDisciplina() == null || !dth.getCodigoDisciplina().equals((String) row[0])) {
				
				if (dth.getCodigoDisciplina() != null) {
					disciplinaTurmaHorarios.add(dth);
				}
				
				dth = new DisciplinaTurmaHorario();
				
				dth.setCodigoDisciplina((String) row[0]);
				dth.setNomeDisciplina((String) row[1]);
				dth.setCargaHorariaTotal(String.valueOf(row[2]));
				dth.setCodigoCurso((String) row[3]);
				dth.setNomeCurso((String) row[4]);
				
			}

			TurmaDisciplina td = new TurmaDisciplina();
			
			td.setTurma((String) row[5]);
			td.setDiaSemana((String) row[6]);
			td.setHorario((String) row[7]);
			
			dth.getTurmas().add(td);
		    
		}
		
		disciplinaTurmaHorarios.add(dth);

		session.close();

		return disciplinaTurmaHorarios;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurma> listaDisciplinaTurmaComHorarios(Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"t.id, "
					+"t.turma turma, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso, "
					+"COALESCE(o.dia, '0') as dia, "
					+"COALESCE(o.letra, 'a') as horario "
				+"FROM "
					+"disciplina d "
				+"JOIN turma t ON d.codigo = t.codigo_disc "
				+"LEFT JOIN oferta o ON t.id = o.id_turma "
				+"LEFT JOIN curso c ON d.curso = c.codigo "
				+"WHERE t.ano = :ano AND t.semestre = :semestre "
				+"ORDER BY d.nome;")
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<DisciplinaTurma> disciplinaTurmas = new ArrayList<DisciplinaTurma>();
		
		DisciplinaTurma dt = new DisciplinaTurma();
		
		for (Object[] row : rows) {
			
			if (dt.getCodigoDisciplina() == null || !dt.getCodigoDisciplina().equals((String) row[0]) || !dt.getTurma().equals((String) row[3])) {
				
				if (dt.getCodigoDisciplina() != null) {
					disciplinaTurmas.add(dt);
				}
				
				dt = new DisciplinaTurma();
				
				dt.setCodigoDisciplina((String) row[0]);
				dt.setNomeDisciplina((String) row[1]);
				dt.setTurmaId((Integer) row[2]);
				dt.setTurma((String) row[3]);
				dt.setCodigoCurso((String) row[4]);
				dt.setNomeCurso((String) row[5]);
				
			}

			Horario h = new Horario();
			
			h.setDia(Integer.parseInt(""+row[6]));
			h.setHorario(""+row[7]);
			
			dt.getHorarios().add(h);
		    
		}
		
		disciplinaTurmas.add(dt);

		session.close();

		return disciplinaTurmas;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaDoProfessorPrioridade(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		session.setCacheMode(CacheMode.IGNORE);
				
		Query query = session.createSQLQuery(
				" SELECT "
				+ "	d.codigo codigoDisciplina, "
				+ "					d.nome nomeDisciplina, "
				+ "					ft.turma turma, "
				+ "					d.curso codigoCurso, "
				+ "					ft.siape siape, "
				+ "					ft.prioridade prioridade, "
				+ "					ft.pos pos, "
				+ "					ft.qte_ministrada qteMinistrada, "
				+ "					ft.id_turma idTurma "
				+ "				FROM "
				+ "					disciplina d "
				+ "				JOIN turma t ON d.codigo = t.codigo_disc "
				+ "				JOIN fila_turma ft ON  ft.id_turma = t.id "
				+ "				WHERE ft.siape = :siape "
				+ "				AND t.ano = :ano AND t.semestre = :semestre "
				+ "				AND ft.status != 13 "
				+ "				ORDER BY ft.prioridade ")
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		session.close();
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = new ArrayList<DisciplinaTurmaProfessor>();
		
		DisciplinaTurmaProfessor dtp;
		
		for (Object[] row : rows) {
		
			dtp = new DisciplinaTurmaProfessor();
			
			dtp.setCodigoDisciplina((String) row[0]);
			dtp.setNomeDisciplina((String) row[1]);
			dtp.setTurma((String) row[2]);
			dtp.setCodigoCurso((String) row[3]);
			dtp.setSiape((String) row[4]);
			dtp.setPrioridade((Integer) row[5]);
			dtp.setPos((Integer) row[6]);
			dtp.setQteMinistrada((Integer) row[7]);
			dtp.setIdTurma(String.valueOf(row[8]));
			
			disciplinaTurmaProfessores.add(dtp);
		    
		}
		

		return disciplinaTurmaProfessores;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaComHorariosDoProfessor(String siape, Semestre semestre) {
		
		Session session = sessionFactory.openSession();
		
		session.setCacheMode(CacheMode.IGNORE);
				
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"t.turma turma, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso, "
					+"o.dia dia, "
					+"o.letra horario, "
					+"ft.siape siape, "
					+"ft.prioridade prioridade, "
					+"ft.pos pos, "
					+"ft.qte_ministrada qteMinistrada, "
					+"t.id idTurma "
				+"FROM "
					+"disciplina d "
				+"JOIN turma t ON d.codigo = t.codigo_disc "
				+"JOIN oferta o ON t.id = o.id_turma "
				+"JOIN curso c ON d.curso = c.codigo "
				+"JOIN fila ft ON ft.codigo_disc = d.codigo " 
				+"WHERE ft.siape = :siape "
				+"AND t.ano = :ano AND t.semestre = :semestre "
				+"AND ft.ano = :ano AND ft.semestre = :semestre "
				+"AND ft.status != 13 "
				+"ORDER BY ft.prioridade")
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		session.close();
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = new ArrayList<DisciplinaTurmaProfessor>();
		
		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();
		
		for (Object[] row : rows) {
			
			if (dtp.getCodigoDisciplina() == null || !dtp.getCodigoDisciplina().equals((String) row[0]) ||  !dtp.getTurma().equals((String) row[2])) {
				
				if (dtp.getCodigoDisciplina() != null) {
					disciplinaTurmaProfessores.add(dtp);
				}
				
				dtp = new DisciplinaTurmaProfessor();
				
				dtp.setCodigoDisciplina((String) row[0]);
				dtp.setNomeDisciplina((String) row[1]);
				dtp.setTurma((String) row[2]);
				dtp.setCodigoCurso((String) row[3]);
				dtp.setNomeCurso((String) row[4]);
				dtp.setSiape((String) row[7]);
				dtp.setPrioridade((Integer) row[8]);
				dtp.setPos((Integer) row[9]);
				dtp.setQteMinistrada((Integer) row[10]);
				dtp.setIdTurma(String.valueOf(row[11]));
				
			}

			Horario h = new Horario();
			
			h.setDia(Integer.parseInt(""+row[5]));
			h.setHorario(""+row[6]);
			
			dtp.getHorarios().add(h);
		    
		}
		
		disciplinaTurmaProfessores.add(dtp);

		return disciplinaTurmaProfessores;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorNotIn(String siape, Semestre semestre) {
		Session session = sessionFactory.openSession();
		session.setCacheMode(CacheMode.IGNORE);
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"t.turma turma, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso, "
					+"o.dia dia, "
					+"o.letra horario, "
					+"t.id idTurma "
				+"FROM "
					+"disciplina d "
				+"JOIN turma t ON d.codigo = t.codigo_disc "
				+"JOIN oferta o ON t.id = o.id_turma "
				+"JOIN curso c ON d.curso = c.codigo "
				+"WHERE d.codigo NOT IN ( "
				+ " SELECT DISTINCT f2.codigo_disc "
				+ " FROM fila f2 "
				+ " WHERE f2.siape = :siape "
				+ " AND f2.ano = :ano AND f2.semestre = :semestre "
				+ " AND f2.status != 13 "
				+ " ) "
				+" AND t.ano = :ano AND t.semestre = :semestre "
				+" ORDER BY d.nome ")
				.setParameter("siape", siape)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		session.close();
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = new ArrayList<DisciplinaTurmaProfessor>();
		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();

		for (Object[] row : rows) {
			if (dtp.getCodigoDisciplina() == null || !dtp.getCodigoDisciplina().equals((String) row[0]) ||  !dtp.getTurma().equals((String) row[2])) {
				if (dtp.getCodigoDisciplina() != null) {
					disciplinaTurmaProfessores.add(dtp);
				}
				dtp = new DisciplinaTurmaProfessor();
				dtp.setCodigoDisciplina((String) row[0]);
				dtp.setNomeDisciplina((String) row[1]);
				dtp.setTurma((String) row[2]);
				dtp.setCodigoCurso((String) row[3]);
				dtp.setNomeCurso((String) row[4]);
				dtp.setIdTurma(String.valueOf(row[7]));
			}

			Horario h = new Horario();
			h.setDia(Integer.parseInt(""+row[5]));
			h.setHorario(""+row[6]);
			dtp.getHorarios().add(h);
		}
		disciplinaTurmaProfessores.add(dtp);
		return disciplinaTurmaProfessores;
	}

	@SuppressWarnings("unchecked")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorMarcaHorario(String siape, Integer ano, Integer semestre) {

		siape = siape.trim();
		Session session = sessionFactory.openSession();
		session.setCacheMode(CacheMode.IGNORE);
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"t.turma turma, "
					+"o.dia dia, "
					+"o.letra horario, "
					+"t.id idTurma "
				+"FROM "
				+"disciplina d "
				+"INNER JOIN turma t ON d.codigo = t.codigo_disc "
				+"INNER JOIN oferta o ON t.id = o.id_turma "
				+"INNER JOIN ministra m ON m.id_turma = t.id "
				+"WHERE m.siape = :siape "
				+"AND t.ano = :ano AND t.semestre = :semestre "
				+"order by codigoDisciplina")
		        .setParameter("siape", siape)
				.setParameter("ano", ano)
				.setParameter("semestre", semestre);	
		
		List<Object[]> rows = query.list();
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = new ArrayList<DisciplinaTurmaProfessor>();
		DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();

		for (Object[] row : rows) {
			if (dtp.getCodigoDisciplina() == null || !dtp.getCodigoDisciplina().equals((String) row[0]) ||  !dtp.getTurma().equals((String) row[2])) {
				if (dtp.getCodigoDisciplina() != null) {
					disciplinaTurmaProfessores.add(dtp);
				}
				dtp = new DisciplinaTurmaProfessor();
				dtp.setCodigoDisciplina((String) row[0]);
				dtp.setNomeDisciplina((String) row[1]);
				dtp.setTurma((String) row[2]);
				dtp.setIdTurma(String.valueOf(row[5]));
			}

			Horario h = new Horario();
			h.setDia(Integer.parseInt(""+row[3]));
			h.setHorario(""+row[4]);
			dtp.getHorarios().add(h);
		}
		disciplinaTurmaProfessores.add(dtp);
		
		session.close();
		
		return disciplinaTurmaProfessores;

	}

	public List<TodasDisciplinas> listaTodasDisciplinas() {
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"d.codigo codigoDisciplina, "
					+"d.nome nomeDisciplina, "
					+"c.codigo codigoCurso, "
					+"c.nome nomeCurso "
				+"FROM "
					+"disciplina d "
				+"JOIN curso c ON d.curso = c.codigo "
				+"ORDER BY d.nome;");
		
		List<Object[]> rows = query.list();
		
		List<TodasDisciplinas> disciplinaTurmas = new ArrayList<TodasDisciplinas>();
		
		TodasDisciplinas dt = new TodasDisciplinas();
		
		for (Object[] row : rows) {
				
			dt = new TodasDisciplinas();
			
			dt.setCodigo((String) row[0]);
			dt.setNome((String) row[1]);
			dt.setCodigo((String) row[2]);
			dt.setNome((String) row[3]);
			
			disciplinaTurmas.add(dt);
		    
		}

		session.close();

		return disciplinaTurmas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Disciplina> findAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT d.*"
				+"FROM disciplina d "
				+"ORDER BY d.nome");
		List<Object[]> rows = query.list();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		
		for (Object[] row : rows) {
			Disciplina d = new Disciplina();
			d.setCodigo(row[0].toString());
			d.setNome(row[1].toString());
			d.setChTeorica((Integer) row[2]);
			d.setChPratica((Integer) row[3]);
			d.setChtotal((Integer) row[4]);
			d.setCurso(row[5].toString());
			d.setFila((Boolean) row[6]);
			d.setPeriodo((Short) row[7]);
			disciplinas.add(d);
		}
		session.close();
		return disciplinas;
	}

	@Override
	public void save(Disciplina d) throws Exception {
			if(d != null &&
					!"".equals(d.getCodigo()) && d.getCodigo() != null &&
					!"".equals(d.getNome()) && d.getNome() != null &&
					!"".equals(d.getCurso()) && d.getCurso() != null){
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				session.save(d);
				tx.commit();
				session.close();
			} else {
				 throw new Exception("Preencha todos os campos obrigatórios.");
			}
	}

	@Override
	public void delete(Disciplina d) throws Exception {
		if(d != null &&
				!"".equals(d.getCodigo()) && d.getCodigo() != null &&
				!"".equals(d.getNome()) && d.getNome() != null &&
				!"".equals(d.getCurso()) && d.getCurso() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(d);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	@Override
	public void update(Disciplina d) throws Exception {
		if(d != null &&
				!"".equals(d.getCodigo()) && d.getCodigo() != null &&
				!"".equals(d.getNome()) && d.getNome() != null &&
				!"".equals(d.getCurso()) && d.getCurso() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

}
