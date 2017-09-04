package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurma;
import br.com.ufu.model.DisciplinaTurmaHorario;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.TodasDisciplinas;
import br.com.ufu.repository.DisciplinaRepositoryImpl;

@Path("/")
public class DisciplinaServiceImpl {
	
	DisciplinaRepositoryImpl disciplinaRepository = new DisciplinaRepositoryImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	
	@Deprecated
	public List<Disciplina> listar() {

		List<Disciplina> disciplinas = disciplinaRepository.listar();

		return disciplinas;
	}
	
	
	@Deprecated
	public List<DisciplinaTurma> listaDisciplina() {
		
		List<DisciplinaTurma> disciplinaTurmas = disciplinaRepository.listaDisciplina();
		
		return disciplinaTurmas;
	}
	
	
	public List<DisciplinaTurma> listaDisciplinaTurmaComHorarios(Semestre semestre) {
		
		//Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<DisciplinaTurma> disciplinaTurmas = disciplinaRepository.listaDisciplinaTurmaComHorarios(semestre);
		
		return disciplinaTurmas;
	}
	
	
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaDoProfessorPrioridade(String siape, Semestre semestre) {
		
		//Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaRepository.listaDisciplinaTurmaDoProfessorPrioridade(siape, semestre);
		
		return disciplinaTurmaProfessores;
	}
	
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaComHorariosDoProfessor(String siape, Semestre semestre) {
		
		//Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaRepository.listaDisciplinaTurmaComHorariosDoProfessor(siape, semestre);
		
		return disciplinaTurmaProfessores;
	}
	
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorNotIn(String siape, Semestre semestre) {
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaRepository.listaDisciplinaTurmaProfessorNotIn(siape, semestre);
		return disciplinaTurmaProfessores;
	}

	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorMarcaHorario(String siape, Integer ano, Integer semestre) {
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaRepository.listaDisciplinaTurmaProfessorMarcaHorario(siape, ano, semestre);
		return disciplinaTurmaProfessores;
	}
	
	public List<DisciplinaTurmaHorario> listaDisciplinaTurmaHorario() {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<DisciplinaTurmaHorario> disciplinaTurmaHorarios = disciplinaRepository.listaDisciplinaTurmaHorario(semestre);
		
		return disciplinaTurmaHorarios;
		
	}


	public List<TodasDisciplinas> listaTodasDisciplinas() {
		List<TodasDisciplinas> disciplinas = disciplinaRepository.listaTodasDisciplinas();

		return disciplinas;
	}
}
