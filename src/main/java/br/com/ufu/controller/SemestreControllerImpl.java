package br.com.ufu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.ufu.model.Curso;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.CursoRepositoryImpl;
import br.com.ufu.service.SemestreServiceImpl;

@Path("/semestre")
public class SemestreControllerImpl {
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	
	@GET
	@Path("/buscarUltimoSemestre")
	@Produces("application/json")
	public Semestre buscarUltimoSemestre() {
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		return semestre;
	}
	
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Semestre> listar() {
		List<Semestre> semestres = semestreServiceImpl.listar();
		return semestres;
	}
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Semestre s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			semestreServiceImpl.save(s);
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
	public Map<String, Object> delete(@RequestBody Semestre s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			semestreServiceImpl.delete(s);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}
	
	@POST
	@Path("/ativar")
	@Produces("application/json")
	public Map<String, Object> ativar(@RequestBody Semestre s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			semestreServiceImpl.ativar(s);
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
	public Map<String, Object> update(@RequestBody Semestre s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			semestreServiceImpl.update(s);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}


}
