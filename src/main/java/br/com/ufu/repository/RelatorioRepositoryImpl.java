package br.com.ufu.repository;

import java.util.*;

import javax.ws.rs.Path;

import br.com.ufu.view.RelatorioFila3DTO;
import br.com.ufu.view.RelatorioFila4DTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ufu.model.Semestre;
import br.com.ufu.view.RelatorioFilaDTO;
@Path("/")
public class RelatorioRepositoryImpl {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	public RelatorioRepositoryImpl() {
		super();
		
		if (sessionFactory == null || serviceRegistry == null) {
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RelatorioFilaDTO> relatFila(Semestre semestre, String order) {
		String orderConsulta;
		
		if(order.equals("disciplina")){
			orderConsulta = "order by d.nome, f.pos ";
		} else if (order.equals("professor")){
			orderConsulta = "order by p.nome, f.prioridade";
		} else {
			orderConsulta = "order by f.prioridade, f.pos";
		}
		
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(
				" select p.nome as nomeProfessor,"
				+ " d.codigo, "
				+ " d.nome, "
				+ " coalesce(f.pos, 0) as pos, "
				+ " '(' || qte_ministrada || '/' || qte_maximo || ')' as qte, "
				+ " coalesce(f.prioridade, 0) as prioridade " +
				" from fila f "  +
				" join disciplina d on f.codigo_disc = d.codigo " +
				" join professor p on p.siape = f.siape " +
				" where ano = :ano and semestre = :semestre " +
				orderConsulta)
				.setParameter("ano", semestre.getAno())
				.setParameter("semestre", semestre.getSemestre());
		
		List<Object[]> rows = query.list();
		
		List<RelatorioFilaDTO> retorno = new LinkedList<RelatorioFilaDTO>();
		RelatorioFilaDTO relatFila = null;
		
		for (Object[] row : rows) {
			relatFila = new RelatorioFilaDTO();
			
			relatFila.setNomeProfessor(row[0].toString());
			relatFila.setDisciplinaCodigo(row[1].toString());
			relatFila.setDisciplinaNome(row[2].toString());
			relatFila.setPosicaoFila(Integer.parseInt(row[3].toString()));
			relatFila.setQuantidade(row[4].toString());
			relatFila.setPrioridade(row[5].toString());
			retorno.add(relatFila);
		}
		
		session.close();
		
//		if(order.equals("disciplina")){
//			Collections.sort(retorno, new Comparator<RelatorioFilaDTO>() {
//				@Override
//				public int compare(RelatorioFilaDTO o1, RelatorioFilaDTO o2) {
//					return o1.getDisciplinaNome().compareTo(o2.getDisciplinaNome());
//				}
//			});
//			
//		} else if(order.equals("professor")){
//			Collections.sort(retorno ,new Comparator<RelatorioFilaDTO>() {
//				@Override
//				public int compare(RelatorioFilaDTO o1, RelatorioFilaDTO o2) {
//					return o1.getNomeProfessor().compareTo(o2.getNomeProfessor());
//				}
//			});
//			
//		} else {
//			Collections.sort(retorno ,new Comparator<RelatorioFilaDTO>() {
//				@Override
//				public int compare(RelatorioFilaDTO o1, RelatorioFilaDTO o2) {
//					return o1.getPrioridade().compareTo(o2.getPrioridade());
//				}
//			});
//		}
		
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioFila4DTO> relatFila4() {

		Session session = sessionFactory.openSession();

		Query query = session.createSQLQuery(" select d.codigo, d.nome, p.nome as nomeProfessor, d.curso, '(' || qte_ministrada || '/' || qte_maximo || ')' as qte "
			+ " from fila f inner join disciplina d on f.codigo_disc = d.codigo "
			+ "     inner join professor p on p.siape = f.siape "
			+ " where ano = 2017 and semestre = 1      and qte_ministrada >= qte_maximo  and periodo_preferencial = true "
			+ " union "
			+ " select d.codigo, d.nome, '<sem professor>', d.curso, '--' "
			+ " from fila f inner join disciplina d on f.codigo_disc = d.codigo "
			+ " where ano = 2017 and semestre = 1"
			+ " group by codigo"
			+ " having count (distinct periodo_preferencial)=1 "
			+ " order by codigo");

		List<Object[]> rows = query.list();

		List<RelatorioFila4DTO> retorno = new LinkedList<RelatorioFila4DTO>();
		RelatorioFila4DTO relatFila = null;

		for (Object[] row : rows) {
			relatFila = new RelatorioFila4DTO();

			relatFila.setDisciplinaCodigo(row[0].toString());
			relatFila.setDisciplinaNome(row[1].toString());
			relatFila.setNomeProfessor(row[2].toString());
			relatFila.setCurso(row[3].toString());
			relatFila.setQuantidade(row[4].toString());
			retorno.add(relatFila);
		}

		session.close();

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioFila3DTO> relatFila3() {

		Session session = sessionFactory.openSession();

		Query query = session.createSQLQuery(" select d.codigo, d.nome, p.nome as nomeProfessor, d.curso, '(' || qte_ministrada || '/' || qte_maximo || ')' as qte "
			+ " from fila f inner join disciplina d on f.codigo_disc = d.codigo "
			+ " inner join professor p on p.siape = f.siape "
			+ " where ano = 2017 and semestre = 1 and qte_ministrada = qte_maximo-1 and periodo_preferencial = true "
			+ " order by lotacao desc,p.nome, prioridade ");

		List<Object[]> rows = query.list();

		List<RelatorioFila3DTO> retorno = new LinkedList<RelatorioFila3DTO>();
		RelatorioFila3DTO relatFila = null;

		for (Object[] row : rows) {
			relatFila = new RelatorioFila3DTO();

			relatFila.setDisciplinaCodigo(row[0].toString());
			relatFila.setDisciplinaNome(row[1].toString());
			relatFila.setNomeProfessor(row[2].toString());
			relatFila.setCurso(row[3].toString());
			relatFila.setQuantidade(row[4].toString());
			retorno.add(relatFila);
		}

		session.close();

		return retorno;
	}
	

}
