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

import br.com.ufu.model.Turma;
import br.com.ufu.repository.TurmaRepositoryImpl;
import br.com.ufu.view.RltTurmasDTO;
import br.com.ufu.view.TurmaDTO;

@Path("/turma")
public class TurmaControllerImpl {
	TurmaRepositoryImpl turmaRep = new TurmaRepositoryImpl();
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Turma t) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			turmaRep.save(t);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

	@POST
	@Path("/findTurmaCadastrada")
	@Produces("application/json")
	public Integer findTurmaCadastrada(@RequestBody Turma t) throws Exception {
		Integer response = null;
		response = turmaRep.findTurmaCadastrada(t);
		return response;
	}
	
	@POST
	@Path("/delete")
	@Produces("application/json")
	public Map<String, Object> delete(@RequestBody Turma turma) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			turmaRep.delete(turma);
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
	public Map<String, Object> update(@RequestBody Turma turma) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			turmaRep.update(turma);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@GET
	@Path("/findAll")
	@Produces("application/json")
	public List<Turma> findAll() {
		List<Turma> turmas = turmaRep.findAll();
		return turmas;
	}

/*   Alterações andré ############*/;
	@GET
	@Path("/findTurmaDuplicar/{ano}/{semestre}")
	@Produces("application/json")
	public List<TurmaDTO> findTurmaDuplicar(@PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("ano", ano);	
		session.setAttribute("semestre", semestre);	
		List<TurmaDTO> turmasDuplicar = turmaRep.findTurmaDuplicar(ano,semestre);
		return turmasDuplicar;
	}

	@POST
	@Path("/duplica/{ano}/{semestre}")
	@Produces("application/json")
	public Map<String, Object> duplica(@RequestBody List<Turma> turmas, @PathParam("ano") Integer ano, @PathParam("semestre") Short semestre, @Context HttpServletRequest request) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();

		HttpSession session = request.getSession();
		session.setAttribute("ano", ano);	
		session.setAttribute("semestre", semestre);	
		
		try {
			turmaRep.duplica(turmas, ano, semestre);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

/*##################*/
	
	@GET
	@Path("/buscaTurmaDisc/{ano}/{semestre}")
	@Produces("application/json")
	public List<TurmaDTO> buscaTurmaDisc(@PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("ano", ano);
		session.setAttribute("semestre", semestre);	
		List<TurmaDTO> turmas = turmaRep.buscaTurmaDisc(ano, semestre);
		return turmas;
	}

	@GET
	@Path("/findTurmaRelatorio/{ano}/{semestre}")
	@Produces("application/json")
	public List<RltTurmasDTO> findTurmaRelatorio(@PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre, @Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("ano", ano);	
		session.setAttribute("semestre", semestre);	
		List<RltTurmasDTO> turmas = turmaRep.findTurmaRelatorio(ano,semestre);
		return turmas;
	}

}
