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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurma;
import br.com.ufu.model.DisciplinaTurmaHorario;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Ministra;
import br.com.ufu.model.MinistraDisciplina;
import br.com.ufu.model.Professor;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.TodasDisciplinas;
import br.com.ufu.model.Turma;
import br.com.ufu.repository.DisciplinaRepositoryImpl;
import br.com.ufu.repository.MinistraDisciplinaRepositoryImpl;
import br.com.ufu.repository.MinistraRepositoryImpl;
import br.com.ufu.repository.TurmaRepositoryImpl;
import br.com.ufu.service.DisciplinaServiceImpl;

@Path("/ministra")
public class MinistraControllerImpl {
	MinistraRepositoryImpl ministraRep = new MinistraRepositoryImpl();
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Ministra t) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			ministraRep.save(t);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
}
