package br.com.ufu.repository;

import java.text.SimpleDateFormat;
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
@Path("/")
public class ProfessorRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public ProfessorRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	public List<Professor> findAll() {
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.siape, "
					+"p.nome, "
					+"p.data_ingresso, "
					+"p.data_nasc, "
					+"p.afastado, "
					+"p.regime, "
					+"p.carga_atual, "
					+"p.lotacao, "
					+"p.cnome, "
					+"p.data_saida, "
					+"p.data_exoneracao, "
					+"p.status, "
					+"p.data_aposentadoria "
				+"FROM "
					+"professor p "
				+"ORDER BY p.nome");
		
		List<Object[]> rows = query.list();
		
		List<Professor> professores = new ArrayList<Professor>();
		
		for (Object[] row : rows) {

			Professor p = new Professor();
			
			p.setSiape((String) row[0]);
			p.setNome((String) row[1]);
			p.setDataIngresso((Date) row[2]);
			p.setDataNasc((Date) row[3]);
			p.setAfastado((Boolean) row[4]);
			p.setRegime((String) row[5]);
			p.setCargaAtual(Long.parseLong((""+row[6]).trim(), 10));
			p.setLotacao((String) row[7]);
			p.setCnome((String) row[8]);
			p.setDataSaida((Date) row[9]);
			p.setDataExoneracao((Date) row[10]);
			p.setStatusProfessor((String) row[11]);
			p.setDataAposentadoria((Date) row[12]);
			
			professores.add(p);
		    
		}

		session.close();

		return professores;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Professor> listar() {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.siape, "
					+"p.nome, "
					+"p.data_ingresso, "
					+"p.data_nasc, "
					+"p.afastado, "
					+"p.regime, "
					+"p.carga_atual, "
					+"p.lotacao, "
					+"p.cnome, "
					+"p.data_saida, "
					+"p.data_exoneracao, "
					+"p.status, "
					+"p.data_aposentadoria "
				+"FROM "
					+"professor p "
//				+"WHERE p.afastado = FALSE "
				+"ORDER BY p.nome");
		
		List<Object[]> rows = query.list();
		
		List<Professor> professores = new ArrayList<Professor>();
		
		for (Object[] row : rows) {

			Professor p = new Professor();
			
			p.setSiape((String) row[0]);
			p.setNome((String) row[1]);
			p.setDataIngresso((Date) row[2]);
			p.setDataNasc((Date) row[3]);
			p.setAfastado((Boolean) row[4]);
			p.setRegime((String) row[5]);
			p.setCargaAtual(Long.parseLong((""+row[6]).trim(), 10));
			p.setLotacao((String) row[7]);
			p.setCnome((String) row[8]);
			p.setDataSaida((Date) row[9]);
			p.setDataExoneracao((Date) row[10]);
			p.setStatusProfessor((String) row[11]);
			p.setDataAposentadoria((Date) row[12]);

			professores.add(p);
		    
		}

		session.close();

		return professores;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public Professor buscaPorSiape(String siape) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT "
					+"p.siape, "
					+"p.nome, "
					+"p.data_ingresso, "
					+"p.data_nasc, "
					+"p.afastado, "
					+"p.regime, "
					+"p.carga_atual "
				+"FROM "
					+"professor p "
				+"WHERE p.siape = :siape ")
				.setParameter("siape", siape);
		
		List<Object[]> rows = query.list();
		
		Professor p = new Professor();
		
		for (Object[] row : rows) {
			
			p.setSiape((String) row[0]);
			p.setNome((String) row[1]);
			p.setDataIngresso((Date) row[2]);
			p.setDataNasc((Date) row[3]);
			p.setAfastado((Boolean) row[4]);
			p.setRegime((String) row[5]);
			p.setCargaAtual(Long.parseLong((""+row[6]).trim(), 10));
		    
		}

		session.close();

		return p;
		
	}
	
	public void save(Professor p) throws Exception {
		if(p != null &&
				!"".equals(p.getSiape()) && p.getSiape() != null &&
				!"".equals(p.getNome()) && p.getNome() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(p);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigat√≥rios.");
		}
	}

	public void delete(Professor p) throws Exception {
		if(p != null &&
				!"".equals(p.getSiape()) && p.getSiape() != null &&
				!"".equals(p.getNome()) && p.getNome() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(p);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigat√≥rios.");
		}
	}
	
	public void update(String siapeAntigo, Professor p) throws Exception {
		
		Session session_verifica = sessionFactory.openSession();
		
		Query query_verifica = session_verifica.createSQLQuery(
			"SELECT "
				+"COUNT(1) AS a "
			+"FROM professor "
			+"WHERE siape = :siape "
		).setParameter("siape", p.getSiape());
		
		Boolean executa = true;
		String verifica =  String.valueOf(query_verifica.list().get(0));

		if(verifica.equals("1") && !siapeAntigo.trim().equals(p.getSiape().trim())) {
			executa = false;
		} 

		session_verifica.close();
		
		if(p != null &&
				!"".equals(p.getSiape()) && p.getSiape() != null &&
				!"".equals(p.getNome()) && p.getNome() != null && executa == true){
			
			boolean afastado = false;
			if(p.getAfastado() != null && p.getAfastado() != false) {
				afastado = true;
			}
			
            String formato = "yyyy-MM-dd";
            SimpleDateFormat formata = new SimpleDateFormat(formato);

			String data_saida = null;
			String data_exoneracao = null;
			String data_aposentadoria = null;

			if(p.getDataSaida() != null && !"".equals(p.getDataSaida())) {
				data_saida = "'"+formata.format(p.getDataSaida())+"'";
			}

			if(p.getDataExoneracao() != null && !"".equals(p.getDataExoneracao())) {
				data_exoneracao = "'"+formata.format(p.getDataExoneracao())+"'";
			}

			if(p.getDataAposentadoria() != null && !"".equals(p.getDataAposentadoria())) {
				data_aposentadoria = "'"+formata.format(p.getDataAposentadoria())+"'";
			}

			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			Query query = session.createSQLQuery(
				"UPDATE professor "
				+"SET "
					+"siape = :siape, "
					+"nome = :nome, "
					+"data_ingresso = :data_ingresso, "
					+"data_nasc = :data_nasc, "
					+"afastado = :afastado, "
					+"regime = :regime, "
					+"carga_atual = :carga_atual, "
					+"lotacao = :lotacao, "
					+"cnome = :cnome, "
					+"data_saida = "+data_saida+", "
					+"data_exoneracao = "+data_exoneracao+", "
					+"status = :status, "
					+"data_aposentadoria = "+data_aposentadoria+" "
				+"WHERE siape = :siapeAntigo "
			).setParameter("siape", p.getSiape())
			.setParameter("nome", p.getNome())
			.setParameter("data_ingresso", p.getDataIngresso())
			.setParameter("data_nasc", p.getDataNasc())
			.setParameter("afastado", afastado)
			.setParameter("regime", p.getRegime())
			.setParameter("carga_atual", p.getCargaAtual())
			.setParameter("lotacao", p.getLotacao())
			.setParameter("cnome", p.getCnome())
			.setParameter("status", p.getStatusProfessor())
			.setParameter("siapeAntigo", siapeAntigo);

			query.executeUpdate();
			tx.commit();
			session.close();

		} else {
			if(executa == false) {
				throw new Exception("Siape j· cadastrado.");
			} else {
				throw new Exception("Preencha todos os campos obrigatÛrios.");
			}
		}
	}

	public List<Professor> buscaPorSemestre(Integer ano, Integer semestre) {
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				"SELECT DISTINCT "
					+"p.siape, " 
					+"p.nome, " 
					+"p.data_ingresso, " 
					+"p.data_nasc, " 
					+"p.afastado, " 
					+"p.regime, " 
					+"p.carga_atual, " 
					+"p.lotacao, " 
					+"p.cnome, " 
					+"p.data_saida, " 
					+"p.data_exoneracao, " 
					+"p.status, " 
					+"p.data_aposentadoria " 
				+"FROM professor p "
				+"INNER JOIN ministra m ON m.siape = p.siape "
				+"INNER JOIN turma t ON m.id_turma = t.id "
				+"WHERE " 
				+"t.ano = :ano "
				+"AND t.semestre = :semestre "
				+"ORDER BY p.nome")
				.setParameter("ano", ano)
				.setParameter("semestre", semestre);
		
		List<Object[]> rows = query.list();
		
		List<Professor> professores = new ArrayList<Professor>();
		
		for (Object[] row : rows) {

			Professor p = new Professor();
			
			p.setSiape((String) row[0]);
			p.setNome((String) row[1]);
			p.setDataIngresso((Date) row[2]);
			p.setDataNasc((Date) row[3]);
			p.setAfastado((Boolean) row[4]);
			p.setRegime((String) row[5]);
			p.setCargaAtual(Long.parseLong((""+row[6]).trim(), 10));
			p.setLotacao((String) row[7]);
			p.setCnome((String) row[8]);
			p.setDataSaida((Date) row[9]);
			p.setDataExoneracao((Date) row[10]);
			p.setStatusProfessor((String) row[11]);
			p.setDataAposentadoria((Date) row[12]);

			professores.add(p);
		    
		}

		session.close();

		return professores;
		
	}

	public boolean loginProfessor(String siape) {

		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
			"SELECT "
				+"COUNT(1) AS a "
			+"FROM professor "
			+"WHERE siape = :siape "
		).setParameter("siape", siape);

		Boolean permiteLogin = true;

		String verifica_siape =  String.valueOf(query.list().get(0));

		if(verifica_siape.equals("0")) {
			permiteLogin = false;
		} 

		return permiteLogin;
	}

}
