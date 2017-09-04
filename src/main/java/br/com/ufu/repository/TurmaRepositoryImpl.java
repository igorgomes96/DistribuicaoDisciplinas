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

import br.com.ufu.model.Turma;
import br.com.ufu.service.TurmaAdminServiceImpl;
import br.com.ufu.view.RltTurmasDTO;
import br.com.ufu.view.TurmaDTO;
@Path("/")
public class TurmaRepositoryImpl implements TurmaAdminServiceImpl{
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public TurmaRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turma> findAll() {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT t.*"
				+"FROM turma t "
				+"ORDER BY t.codigo_disc");
		List<Object[]> rows = query.list();
		List<Turma> turmas = new ArrayList<Turma>();
		
		for (Object[] row : rows) {
			Turma t = new Turma();
			t.setCodigoDisc(row[0].toString());
			t.setTurma(row[1].toString());
			t.setCh((Integer) row[2]);
			t.setAno((Integer) row[3]);
			t.setSemestre((Short)row[4]);
			t.setId((Integer) row[5]);
			turmas.add(t);
		}
		session.close();
		return turmas;
	}
	
	public Turma findTurma(int id) {
		Session session = sessionFactory.openSession();
		Turma t = (Turma) session.get(Turma.class, id);
		session.close();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public List<TurmaDTO> buscaTurmaDisc(Integer ano, Integer semestre) {
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
				"SELECT t.*,"
				+"d.nome nomeDisciplina "
				+"FROM turma t "
				+"JOIN disciplina d ON d.codigo = t.codigo_disc "
				+"WHERE t.semestre = :semestre "
				+"and t.ano = :ano "
				+"ORDER BY t.codigo_disc")
				.setParameter("semestre", semestre).setParameter("ano", ano);
		List<Object[]> rows = query.list();
		List<TurmaDTO> turmas = new ArrayList<TurmaDTO>();
		
		for (Object[] row : rows) {
			TurmaDTO tDTO = new TurmaDTO();
			Turma t = new Turma();
			
			t.setCodigoDisc(row[0].toString());
			t.setTurma(row[1].toString());
			t.setCh((Integer) row[2]);
			t.setAno((Integer) row[3]);
			t.setSemestre((Short)row[4]);
			t.setId((Integer) row[5]);
			tDTO.setNomeDisciplina(row[6].toString());
			tDTO.setTurma(t);
			turmas.add(tDTO);
		}
		session.close();
		return turmas;
	}
	
	@Override
	public void save(Turma t) throws Exception {
		if(t != null &&
				!"".equals(t.getCodigoDisc()) && t.getCodigoDisc() != null &&
				!"".equals(t.getTurma()) && t.getTurma() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Integer id = (Integer)
			session.save(t);
			t.setId(id);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	@Override
	public Integer findTurmaCadastrada(Turma t) throws Exception {
		Integer id_turma = null;
		if(t != null && !"".equals(t.getCodigoDisc()) && t.getCodigoDisc() != null && !"".equals(t.getTurma()) && t.getTurma() != null){
			Session session = sessionFactory.openSession();
			Query query = session.createSQLQuery(
				"SELECT turma.id "
				+"FROM turma "
				+"WHERE codigo_disc = :codigo "
				+"AND turma = :turma")
			.setParameter("codigo", t.getCodigoDisc()).setParameter("turma", t.getTurma());
			id_turma = (Integer) query.list().get(0);
			session.close();
		}
		return id_turma;
	}

	@Override
	public void delete(Turma t) throws Exception {
		if(t != null &&
				!"".equals(t.getCodigoDisc()) && t.getCodigoDisc() != null &&
				!"".equals(t.getTurma()) && t.getTurma() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(t);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	@Override
	public void update(Turma t) throws Exception {
		if(t != null &&
				!"".equals(t.getCodigoDisc()) && t.getCodigoDisc() != null &&
				!"".equals(t.getTurma()) && t.getTurma() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(t);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}

	/* ###### Alteração Andre ################*/
	@SuppressWarnings("unchecked")
	@Override
	public List<TurmaDTO> findTurmaDuplicar(Integer ano, Integer semestre) {

		//System.out.println("ano: " + ano + ", semestre: " + semestre);
		
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
			"SELECT t.*,"
			+"d.nome nomeDisciplina, "
			+"d.curso nomeCurso "
			+"FROM turma t "
			+"JOIN disciplina d ON d.codigo = t.codigo_disc "
			+"WHERE t.semestre = :semestre "
			+"and t.ano = :ano "
			+"ORDER BY d.curso, d.nome")
			.setParameter("semestre", semestre).setParameter("ano", ano);
		List<Object[]> rows = query.list();
		List<TurmaDTO> turmas = new ArrayList<TurmaDTO>();
		
		for (Object[] row : rows) {
			TurmaDTO tDTO = new TurmaDTO();
			Turma t = new Turma();
			
			t.setCodigoDisc(row[0].toString());
			t.setTurma(row[1].toString());
			t.setCh((Integer) row[2]);
			t.setAno((Integer) row[3]);
			t.setSemestre((Short)row[4]);
			t.setId((Integer) row[5]);
			tDTO.setNomeDisciplina(row[6].toString());
			tDTO.setNomeCurso(row[7].toString());
			tDTO.setTurma(t);
			turmas.add(tDTO);
		}
		session.close();
		return turmas;
	}

	@Override
	public void duplica(List<Turma> turmas, Integer ano, Short semestre) throws Exception {

		Integer anoAtual = ano;
		Short semestreAtual = semestre;
		Integer anoAnterior = turmas.get(0).getAno();
		Short semestreAnterior = turmas.get(0).getSemestre();
		
		System.out.println(anoAtual + " " + semestreAtual + " " + anoAnterior + " " + semestreAnterior);

		for(int i = 0; i < turmas.size(); i++) {
			
			turmas.get(i).setAno(ano);
			turmas.get(i).setSemestre(semestre);
			
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Integer id = (Integer)
			session.save(turmas.get(i));
			turmas.get(i).setId(id);
			tx.commit();
			session.close();
		}

		Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createSQLQuery(
				"INSERT INTO OFERTA(id_turma,dia,letra) "
				+"SELECT proxima_turma.id, dia,letra "
				+"FROM OFERTA o "
				+"     INNER JOIN turma turma_anterior on o.id_turma = turma_anterior.id "
				+"     INNER JOIN turma proxima_turma on (turma_anterior.codigo_disc = proxima_turma.codigo_disc and "
				+"       turma_anterior.turma = proxima_turma.turma) "
				+"WHERE turma_anterior.ano = :anoAnterior and turma_anterior.semestre = :semestreAnterior and "
				+"      proxima_turma.ano = :anoAtual and proxima_turma.semestre = :semestreAtual "
			).setParameter("anoAtual", anoAtual)
			.setParameter("semestreAtual", semestreAtual)
			.setParameter("anoAnterior", anoAnterior)
			.setParameter("semestreAnterior", semestreAnterior);

			query.executeUpdate();
			tx.commit();
			session.close();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RltTurmasDTO> findTurmaRelatorio(Integer ano, Integer semestre) {

		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(
			"SELECT  d.codigo, t.turma, d.nome, d.curso , ch as CargaHoraria, "
				+"(Select (CASE WHEN array_to_string(array_agg( horario), ',') IS NOT NULL THEN array_to_string(array_agg( horario), ',') ELSE '' END ) as horario "
	 			+"FROM (select CONCAT(dia,letra) as horario "
			 	+"from oferta natural join horario natural join semana "
			 	+"where id_turma = t.id " 
			 	+"order by dia,letra) as hh1), "
 				+"(Select (CASE WHEN array_to_string(array_agg( horario), ',') IS NOT NULL THEN array_to_string(array_agg( horario), ',') ELSE '' END ) as horario1 "
			 	+"FROM (select CONCAT(descricao,' ',to_char(hora_inicio, 'HH24h:MI')) as horario "
			 	+"from oferta natural join horario natural join semana where id_turma = t.id order by dia,letra) as hh1) "
			+"FROM turma t "
     		+"INNER JOIN disciplina d "
       		+"ON d.codigo = t.codigo_disc   and t.ano = :ano and t.semestre = :semestre "
     		+"INNER JOIN curso c "
       		+"ON d.curso = c.codigo "
			+"ORDER BY d.curso, d.codigo")
			.setParameter("semestre", semestre).setParameter("ano", ano);
		
		List<Object[]> rows = query.list();
		List<RltTurmasDTO> turmasRel = new ArrayList<RltTurmasDTO>();
		
		for (Object[] row : rows) {
			
			RltTurmasDTO tRelDTO = new RltTurmasDTO();

			tRelDTO.setCodigo(row[0].toString());
			tRelDTO.setTurma(row[1].toString());
			tRelDTO.setNome(row[2].toString());
			tRelDTO.setCurso(row[3].toString());
			tRelDTO.setCargahoraria(row[4].toString());
			tRelDTO.setHorario(row[5].toString());
			tRelDTO.setHorarioExtenso(row[6].toString());
	
			turmasRel.add(tRelDTO);
		
		}
		session.close();
	
		return turmasRel;
	}

	/*##############*/



}

