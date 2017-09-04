package br.com.ufu.service;
import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Semestre;
import br.com.ufu.repository.FilaProfessorRepositoryImpl;
import br.com.ufu.view.FilaProfessorViewDTO;

@Path("/")
public class FilaProfessorServiceImpl {
	
	FilaProfessorRepositoryImpl filaProfessorRepositoryImpl = new FilaProfessorRepositoryImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	public void removeDisciplinasFila(FilaProfessorViewDTO p) throws Exception{
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		filaProfessorRepositoryImpl.removeDisciplinasFila(p, semestre);
	}
	
	public List<FilaProfessorViewDTO> listaFilaPorSiape(String siape, Semestre semestre) {
		
		List<FilaProfessorViewDTO> filaDisciplinaProfessores = filaProfessorRepositoryImpl.listaFilaPorSiape(siape, semestre);
		
		return filaDisciplinaProfessores;
		
	}

	public void save(FilaProfessorViewDTO d) throws Exception {
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		filaProfessorRepositoryImpl.save(d, semestre);
		
	}
}
