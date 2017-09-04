package br.com.ufu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.ufu.model.Ministra;
import br.com.ufu.service.DistribuicaoServiceImpl;
import br.com.ufu.service.FilaTurmaServiceImpl;


@Path("/filaTurma")
public class FilaTurmaControllerImpl {
	
	FilaTurmaServiceImpl filaTurmaSer = new FilaTurmaServiceImpl();
	
	@GET
	@Path("/distribuir")
	@Produces("application/json")
	public Response distribuir() {
		
		DistribuicaoServiceImpl d = new DistribuicaoServiceImpl(filaTurmaSer.FindAllFilaTurma());
		
		Thread tr = new Thread(d);
		tr.start();
		
		try {
			tr.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Integer i : DistribuicaoServiceImpl.cenarios.keySet()) {
			System.out.println("\nCenário " + i);
			for (Ministra m : DistribuicaoServiceImpl.cenarios.get(i)) {
				System.out.println("Professor " + m.getSiape() + " ministra turma " + m.getTurmaId());
			}
		}
		
		return Response.ok().build();
	}
}
