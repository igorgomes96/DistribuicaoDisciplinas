package br.com.ufu.service;

import java.util.List;

import br.com.ufu.model.Curso;

public interface CursoServiceImpl {
	public List<Curso> findAll();
	public void save(Curso c) throws Exception;
	public void delete(Curso c) throws Exception;
	public void update(Curso c) throws Exception;
}
