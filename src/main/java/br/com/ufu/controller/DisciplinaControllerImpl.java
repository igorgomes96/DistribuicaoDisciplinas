package br.com.ufu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Disciplina;
import br.com.ufu.model.DisciplinaTurma;
import br.com.ufu.model.DisciplinaTurmaHorario;
import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.model.TodasDisciplinas;
import br.com.ufu.repository.DisciplinaRepositoryImpl;
import br.com.ufu.service.DisciplinaServiceImpl;

@Path("/disciplina")
public class DisciplinaControllerImpl {
	
	DisciplinaServiceImpl disciplinaServiceImpl = new DisciplinaServiceImpl();
	DisciplinaRepositoryImpl disciplinaRep = new DisciplinaRepositoryImpl();
	
	
	@Deprecated
	@GET
	@Path("/listar")
	@Produces("application/json"+";charset=UTF-8")
	public Response listar() {
		
		List<Disciplina> disciplinas = disciplinaServiceImpl.listar();
		
		String json = new Gson().toJson(disciplinas);
		
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
 
	}
	
	@Deprecated
	@GET
	@Path("/listaDisciplina")
	@Produces("application/json"+";charset=UTF-8")
	public List<DisciplinaTurma> listaDisciplina() {
		
		List<DisciplinaTurma> disciplinaTurmas = disciplinaServiceImpl.listaDisciplina();
		
		return disciplinaTurmas;
 
	}
	
	
	@GET
	@Path("/listaDisciplinaTurma/{ano}/{semestre}")
	@Produces("application/json"+";charset=UTF-8")
	public List<DisciplinaTurma> listaDisciplinaTurmaComHorarios( @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<DisciplinaTurma> disciplinaTurmas = disciplinaServiceImpl.listaDisciplinaTurmaComHorarios(semestre);
		
		return disciplinaTurmas;
 
	}
	
	@GET
	@Path("/listaDisciplinaTurmaProfessorNotIn/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorNotIn(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		if (siape == null || siape.isEmpty()) {
			return null;
		}
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaServiceImpl.listaDisciplinaTurmaProfessorNotIn(siape, semestre);
		
		return disciplinaTurmaProfessores;
 
	}

	/**************************************************************/

	@GET
	@Path("/listaDisciplinaTurmaProfessorMarcaHorario/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaProfessorMarcaHorario(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre) {
		
		if (siape == null || siape.isEmpty()) {
			return null;
		}
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaServiceImpl.listaDisciplinaTurmaProfessorMarcaHorario(siape, ano, semestre);
		
		return disciplinaTurmaProfessores;
 
	}


	/****************************************************************/
	
	
	
	@GET
	@Path("/listaDisciplinaTurmaProfessorPrioridade/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaDoProfessorPrioridade(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		if (siape == null || siape.isEmpty()) {
			return null;
		}
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaServiceImpl.listaDisciplinaTurmaDoProfessorPrioridade(siape, semestre);
		
		return disciplinaTurmaProfessores;
 
	}
	
	@GET
	@Path("/listaDisciplinaTurmaProfessor/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> listaDisciplinaTurmaComHorariosDoProfessor(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		if (siape == null || siape.isEmpty()) {
			return null;
		}
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<DisciplinaTurmaProfessor> disciplinaTurmaProfessores = disciplinaServiceImpl.listaDisciplinaTurmaComHorariosDoProfessor(siape, semestre);
		
		return disciplinaTurmaProfessores;
 
	}
	
	
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Disciplina d) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			disciplinaRep.save(d);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	
	@GET
	@Path("/listaDisciplinaTurmaHorario")
	@Produces("application/json")
	public List<DisciplinaTurmaHorario> listaDisciplinaTurmaHorario() {
		
		List<DisciplinaTurmaHorario> disciplinaTurmaHorarios = disciplinaServiceImpl.listaDisciplinaTurmaHorario();
		
		return disciplinaTurmaHorarios;
 
	}
	
	@GET
	@Path("/listaTodasDisciplinas")
	@Produces("application/json")
	public List<TodasDisciplinas> listaTodasDisciplinas() {
		
		List<TodasDisciplinas> todasDisciplinas = disciplinaServiceImpl.listaTodasDisciplinas();
		
		return todasDisciplinas;
 
	}

	@GET
	@Path("/findAll")
	@Produces("application/json")
	public List<Disciplina> findAll() {
		List<Disciplina> disciplinas = disciplinaRep.findAll();
		return disciplinas;
	}
	
	@POST
	@Path("/delete")
	@Produces("application/json")
	public Map<String, Object> delete(@RequestBody Disciplina disciplina) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			disciplinaRep.delete(disciplina);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@POST
	@Path("/update")
	@Produces("application/json")
	public Map<String, Object> update(@RequestBody Disciplina disciplina) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			disciplinaRep.update(disciplina);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

}
