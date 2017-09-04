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
import br.com.ufu.repository.CursoRepositoryImpl;

@Path("/curso")
public class CursoControllerImpl {
	
	CursoRepositoryImpl cursoServiceImpl = new CursoRepositoryImpl();

	@GET
	@Path("/findAll")
	@Produces("application/json")
	public List<Curso> listar() {
		List<Curso> cursos = cursoServiceImpl.findAll();
		return cursos;
	}
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Curso curso) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			cursoServiceImpl.save(curso);
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
	public Map<String, Object> delete(@RequestBody Curso curso) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			cursoServiceImpl.delete(curso);
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
	public Map<String, Object> update(@RequestBody Curso curso) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			cursoServiceImpl.update(curso);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

}
