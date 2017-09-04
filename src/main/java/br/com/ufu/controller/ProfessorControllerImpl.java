package br.com.ufu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Professor;
import br.com.ufu.service.ProfessorServiceImpl;

@Path("/professor")
public class ProfessorControllerImpl {
	
	ProfessorServiceImpl professorServiceImpl = new ProfessorServiceImpl();
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Professor> listar(@Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		List<Professor> professores = professorServiceImpl.listar();
		
		return professores;
 
	}
	
	@GET
	@Path("/buscaPorSiape/{siape}")
	@Produces("application/json")
	public Professor buscaPorSiape(@PathParam("siape") String siape, @Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("siape", siape);
		
		Professor professor = professorServiceImpl.buscaPorSiape(siape);
		
		return professor;
	}
	
	@GET
	@Path("/findAll")
	@Produces("application/json")
	public List<Professor> listar() throws Exception {
		List<Professor> professores = professorServiceImpl.findAll();
		return professores;
	}
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Professor p) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			professorServiceImpl.save(p);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@POST
	@Path("/delete")
	@Produces("application/json")
	public Map<String, Object> delete(@RequestBody Professor p) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			professorServiceImpl.delete(p);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@POST
	@Path("/update/{siapeAntigo}")
	@Produces("application/json")
	public Map<String, Object> update(@PathParam("siapeAntigo") String siapeAntigo, @RequestBody Professor p) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			professorServiceImpl.update(siapeAntigo, p);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

	@GET
	@Path("/buscaPorSemestre/{ano}/{semestre}")
	@Produces("application/json")
	public List<Professor> buscaPorSemestre( @PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre) {
		
		List<Professor> professores = professorServiceImpl.buscaPorSemestre(ano, semestre);
		
		return professores;
 
	}


	@POST
	@Path("/loginProfessor/{siape}")
	@Produces("application/json")
	public boolean loginProfessor( @PathParam("siape") String siape) {
		
		boolean permiteLogin = professorServiceImpl.loginProfessor(siape);
		
 		return permiteLogin;
 		
	}
	


}
