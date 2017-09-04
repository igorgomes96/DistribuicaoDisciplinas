package br.com.ufu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.service.RltProfessorServiceImpl;
import br.com.ufu.view.RltProfessorDTO;

@Path("/rltProfessor")
public class RltProfessorControllerImpl {
	
	RltProfessorServiceImpl professorServiceImpl = new RltProfessorServiceImpl();
	
	@GET
	@Path("/listar/{ano}/{semestre}")
	@Produces("application/json")
	public List<RltProfessorDTO> listar(@PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<RltProfessorDTO> professores = professorServiceImpl.listar(semestre);
		
		return professores;
 
	}
	
	@GET
	@Path("/listarDisciplina/{ano}/{semestre}")
	@Produces("application/json")
	public List<RltProfessorDTO> listarDisciplina(@PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<RltProfessorDTO> professores = professorServiceImpl.listarDisciplina(semestre);
		
		return professores;
 
	}
	
	@GET
	@Path("/listarCurso/{ano}/{semestre}")
	@Produces("application/json")
	public List<RltProfessorDTO> listarCurso(@PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<RltProfessorDTO> professores = professorServiceImpl.listarCurso(semestre);
		
		return professores;
 
	}
}