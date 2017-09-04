package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Professor;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.RltProfessorRepositoryImpl;
import br.com.ufu.view.RltProfessorDTO;
@Path("/")
public class RltProfessorServiceImpl {
	
	RltProfessorRepositoryImpl professorRepository = new RltProfessorRepositoryImpl();
	
	
	public List<RltProfessorDTO> listar(Semestre semestre) {
		
		List<RltProfessorDTO> professores = professorRepository.listar(semestre);
		
		return professores;
	}
	
	public List<RltProfessorDTO> listarCurso(Semestre semestre) {
			
			List<RltProfessorDTO> professores = professorRepository.listarCurso(semestre);
			
			return professores;
		}

	public List<RltProfessorDTO> listarDisciplina(Semestre semestre) {
		
		List<RltProfessorDTO> professores = professorRepository.listarDisciplina(semestre);
		
		return professores;
	}

}
