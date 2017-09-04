package br.com.ufu.service;

import java.util.List;

import br.com.ufu.model.Disciplina;
import br.com.ufu.model.Turma;
import br.com.ufu.view.RltTurmasDTO;
import br.com.ufu.view.TurmaDTO;

public interface TurmaAdminServiceImpl {
	public List<Turma> findAll();
	public void save(Turma t) throws Exception;
	public void delete(Turma t) throws Exception;
	public void update(Turma t) throws Exception;
	public List<TurmaDTO> buscaTurmaDisc(Integer ano, Integer semestre);
	public List<TurmaDTO> findTurmaDuplicar(Integer ano, Integer semestre);
	public void duplica(List<Turma> turmas, Integer ano, Short semestre) throws Exception;
	public List<RltTurmasDTO> findTurmaRelatorio(Integer ano, Integer semestre);
	public Integer findTurmaCadastrada(Turma t) throws Exception;
}
