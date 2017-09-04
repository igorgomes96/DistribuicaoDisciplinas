package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Professor;
import br.com.ufu.repository.ProfessorRepositoryImpl;
@Path("/")
public class ProfessorServiceImpl {
	
	ProfessorRepositoryImpl professorRepository = new ProfessorRepositoryImpl();
	
	
	public List<Professor> listar() {
		
		List<Professor> professores = professorRepository.listar();
		
		return professores;
	}
	
	
	public Professor buscaPorSiape(String siape) {
		
		Professor professor = professorRepository.buscaPorSiape(siape);
		
		return professor;
	}
	
	
	public void save(Professor c) throws Exception {
		professorRepository.save(c);
	}
	public void delete(Professor c) throws Exception{
		professorRepository.delete(c);
	}
	public void update(String siapeAntigo, Professor c) throws Exception{
		professorRepository.update(siapeAntigo, c);
	}
	public List<Professor> findAll() throws Exception{
		return professorRepository.findAll();
	}

	public List<Professor> buscaPorSemestre(Integer ano, Integer semestre) {
		return professorRepository.buscaPorSemestre(ano, semestre);
	}
	
	public boolean loginProfessor(String siape) {
		return professorRepository.loginProfessor(siape);
	}

}
