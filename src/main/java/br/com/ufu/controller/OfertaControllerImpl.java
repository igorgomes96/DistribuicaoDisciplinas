package br.com.ufu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.ufu.model.Oferta;
import br.com.ufu.service.OfertaServiceImpl;
import br.com.ufu.view.OfertaView;

@Path("/oferta")
public class OfertaControllerImpl {
	
	private OfertaServiceImpl ofertaServiceImpl = new OfertaServiceImpl();
	
	private LoginControllerImpl loginControllerImpl = new LoginControllerImpl();
	
	@POST
	@Path("/alterarHorarioTurma")
	@Consumes("application/json")
	public Response alterarHorarioTurma( @Context HttpServletRequest request, OfertaView ofertaView ) {
		
//		if (!loginControllerImpl.validaUsuarioAdministrador(request)) {
//			String json = new Gson().toJson("Este usu�rio n�o pode realizar essa opera��o!");
//			return Response.ok(json, MediaType.APPLICATION_JSON).build();
//		}
		
		System.out.println(ofertaView.toString());
		
		Long turmaId = ofertaView.getTurmaId();
		
		List<Oferta> ofertas = new ArrayList<Oferta>();
		
		for (String horario : ofertaView.getHorarios()) {
			
			String h[] = horario.split("-");
			
			Oferta oferta = new Oferta();
			
			oferta.setTurmaId(turmaId);
			oferta.setDia(Integer.parseInt(h[0]));
			oferta.setLetra(h[1]);
			
			ofertas.add(oferta);
			
		}
		
		ofertaServiceImpl.savarHorarios(turmaId, ofertas);
		
		String json = new Gson().toJson("savo");

		return Response.ok(json, MediaType.APPLICATION_JSON).build();
 
	}

}
