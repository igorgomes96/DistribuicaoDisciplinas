package br.com.ufu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.ufu.model.Semestre;
import br.com.ufu.service.FilaProfessorServiceImpl;
import br.com.ufu.service.SemestreServiceImpl;
import br.com.ufu.view.FilaProfessorViewDTO;

@Path("/filaProfessor")
public class FilaProfessorControllerImpl {
	
	FilaProfessorServiceImpl FilaProfessorServiceImpl = new FilaProfessorServiceImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	@POST
	@Path("/removeDisciplinasFila")
	@Produces("application/json")
	public Map<String, Object> delete(@RequestBody FilaProfessorViewDTO p) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			FilaProfessorServiceImpl.removeDisciplinasFila(p);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@GET
	@Path("/listaFilaPorSiape/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<FilaProfessorViewDTO> listaFilaPorSiape(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<FilaProfessorViewDTO> filaDisciplinaProfessores = FilaProfessorServiceImpl.listaFilaPorSiape(siape, semestre);
		
		return filaDisciplinaProfessores;
 
	}
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody FilaProfessorViewDTO d) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			FilaProfessorServiceImpl.save(d);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
}
