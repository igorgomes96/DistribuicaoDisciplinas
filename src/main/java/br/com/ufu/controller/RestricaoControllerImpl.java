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
import br.com.ufu.model.Restricao;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.CursoRepositoryImpl;
import br.com.ufu.service.RestricaoServiceImpl;
import br.com.ufu.view.RestricaoDTO;

@Path("/restricao")
public class RestricaoControllerImpl {
	
	RestricaoServiceImpl restricaoServiceImpl = new RestricaoServiceImpl();
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<RestricaoDTO> listar() {
		List<RestricaoDTO> restricoes = restricaoServiceImpl.listar();
		return restricoes;
	}
	
	@POST
	@Path("/save")
	@Produces("application/json")
	public Map<String, Object> save(@RequestBody Restricao s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			restricaoServiceImpl.save(s);
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
	public Map<String, Object> delete(@RequestBody Restricao s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			restricaoServiceImpl.delete(s);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

}
