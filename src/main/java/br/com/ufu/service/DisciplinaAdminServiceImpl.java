package br.com.ufu.service;

import java.util.List;

import br.com.ufu.model.Disciplina;

public interface DisciplinaAdminServiceImpl {
	public List<Disciplina> findAll();
	public void save(Disciplina d) throws Exception;
	public void delete(Disciplina d) throws Exception;
	public void update(Disciplina d) throws Exception;
}
