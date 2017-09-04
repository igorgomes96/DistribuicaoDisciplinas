package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplina;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.FilaTurma;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.FilaDisciplinaRepositoryImpl;

@Path("/")
public class FilaDisciplinaServiceImpl {
	
	FilaDisciplinaRepositoryImpl filaDisciplinaRepositoryImpl = new FilaDisciplinaRepositoryImpl();
	
	public List<FilaDisciplina> buscaPorCodigoDisciplina(String codigoDisc, Long turmaId, Semestre semestre) {

		List<FilaDisciplina> filaDisciplinas = filaDisciplinaRepositoryImpl.buscaPorCodigoDisciplina(codigoDisc, turmaId, semestre);

		return filaDisciplinas;
	}
	
	public List<DisciplinaTurmaProfessor> buscaPorSiape(String siape, Semestre semestre) {

		List<DisciplinaTurmaProfessor> filaProfessor = filaDisciplinaRepositoryImpl.buscaPorSiape(siape, semestre);

		return filaProfessor;
	}
	
	public List<DisciplinaTurmaProfessor> buscaPorSiapeDisciplina(String siape, String codDisciplina) {

		List<DisciplinaTurmaProfessor> filaProfessor = filaDisciplinaRepositoryImpl.buscaPorSiapeDisciplina(siape, codDisciplina);

		return filaProfessor;
	}
	
	
	public FilaTurma buscaFilaDisciplina(String siape, String codDisciplina, String turma) {
		
		FilaTurma filaTurma = filaDisciplinaRepositoryImpl.buscaFilaDisciplina(siape, codDisciplina, turma);
		
		return filaTurma;
	}
	
	

	public Integer buscaProximaPrioridadePorSiape(String siape) {
		
		Integer proximaPrioridade = filaDisciplinaRepositoryImpl.buscaProximaPrioridadePorSiape(siape);
		
		return proximaPrioridade;
		
	}
	
	
	public Integer buscaProximaPosicao(String codDisciplina, String turma) {
		
		Integer proximaPosicao = filaDisciplinaRepositoryImpl.buscaProximaPosicao(codDisciplina, turma);
		
		return proximaPosicao;

	}
	
	
	public int salvar(FilaTurma filaTurma) {
		
		int resultado = filaDisciplinaRepositoryImpl.salvar(filaTurma);
		
		return resultado;
		
	}
	
	
	public int atualizaPrioridades(List<FilaTurma> filaTurmas) {
		
		int resultado = filaDisciplinaRepositoryImpl.atualizaPrioridades(filaTurmas);
		
		return resultado;
		
	}
	
	
	public int atualizaStatus(FilaTurma filaTurma) {
		
		int resultado = filaDisciplinaRepositoryImpl.atualizaStatus(filaTurma);
		
		return resultado;
		
	}
	
	public void atualizaFila(List<Fila> listaFila){
		filaDisciplinaRepositoryImpl.atualizaFila(listaFila);
	}

}
