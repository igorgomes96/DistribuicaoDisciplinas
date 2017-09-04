package br.com.ufu.service;

import java.util.List;

import org.hibernate.Query;

import br.com.ufu.model.Ministra;
import br.com.ufu.model.MinistraDisciplina;

public interface MinistraDisciplinaAdminServiceImpl {
	public List<MinistraDisciplina> buscaSiape(String siape);
	public void save(Ministra t) throws Exception;
	public void delete(MinistraDisciplina t) throws Exception;
//	public Query validaTurma(MinistraDisciplina t) throws Exception;
}
