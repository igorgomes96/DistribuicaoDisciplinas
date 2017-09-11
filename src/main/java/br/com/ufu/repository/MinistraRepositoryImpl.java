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
import br.com.ufu.service.MinistraAdminServiceImpl;
import br.com.ufu.service.MinistraDisciplinaAdminServiceImpl;
import br.com.ufu.service.TurmaAdminServiceImpl;
@Path("/")
public class MinistraRepositoryImpl implements MinistraAdminServiceImpl{
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public MinistraRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	@Override
	public void save(Ministra t) throws Exception {
		if(t != null &&
				!"".equals(t.getSiape()) && t.getSiape() != null &&
				!"".equals(t.getTurmaId()) && t.getTurmaId() != null){
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(t);
			tx.commit();
			session.close();
		} else {
			 throw new Exception("Preencha todos os campos obrigatórios.");
		}
	}
	
	public void deleteAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery("delete from ministra");
		tx.commit();
		session.close();
	}
}
