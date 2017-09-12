package br.com.ufu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.ufu.model.Cenario;
import br.com.ufu.model.CenarioPK;
import br.com.ufu.model.Ministra;
import br.com.ufu.repository.CenarioRepositoryImpl;
import br.com.ufu.service.DistribuicaoServiceImpl;
import br.com.ufu.service.FilaTurmaServiceImpl;


@Path("/filaTurma")
public class FilaTurmaControllerImpl {
	
	FilaTurmaServiceImpl filaTurmaSer = new FilaTurmaServiceImpl();
	CenarioRepositoryImpl cenarioRep = new CenarioRepositoryImpl();
	
	@GET
	@Path("/gerarCenarios/{idSemestre}")
	@Produces("application/json")
	public Response gerarCenarios(@PathParam("idSemestre") int idSemestre) {
		
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
				Cenario cenario = new Cenario();
				CenarioPK cenarioPK = new CenarioPK();
				cenarioPK.setIdSemestre(idSemestre);
				cenarioPK.setIdTurma(m.getTurmaId());
				cenarioPK.setNumCenario(i);
				cenarioPK.setSiape(m.getSiape());
				cenario.setCenarioPK(cenarioPK);
				cenarioRep.salvarCenario(cenario);
				System.out.println("Professor " + m.getSiape() + " ministra turma " + m.getTurmaId());
			}
		}
		
		return Response.ok().build();
	}
}
