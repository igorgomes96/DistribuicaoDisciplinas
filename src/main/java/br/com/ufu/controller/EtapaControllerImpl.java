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
import br.com.ufu.model.Etapa;
import br.com.ufu.repository.CursoRepositoryImpl;
import br.com.ufu.service.EtapaServiceImpl;

@Path("/etapa")
public class EtapaControllerImpl {
	
	EtapaServiceImpl EtapaServiceImpl = new EtapaServiceImpl();
	
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Etapa> listar() {
		List<Etapa> Etapas = EtapaServiceImpl.listar();
		return Etapas;
	}
	
	@POST
	@Path("/ativar")
	@Produces("application/json")
	public Map<String, Object> ativar(@RequestBody Etapa s) {
		Map<String,Object> response = new HashMap<String,Object>();
		Map<String,Object> pong = new HashMap<String,Object>();
		try {
			EtapaServiceImpl.ativar(s);
			pong.put("status", 200);
		} catch (Exception e) {
			pong.put("status", 300);
			pong.put("exception", e.getMessage());
		}
		response.put("data", pong);
		return response;
	}

}
