package br.com.ufu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.FilaStatus;
import br.com.ufu.model.Semestre;
import br.com.ufu.service.DisciplinaServiceImpl;
import br.com.ufu.service.FilaServiceImpl;
import br.com.ufu.service.PrioridadeServiceImpl;
import br.com.ufu.service.SemestreServiceImpl;
import br.com.ufu.view.FilaSemestreDTO;
import br.com.ufu.view.FilaView;

@Path("/prioridade")
public class PrioridadeControllerImpl {
	
	PrioridadeServiceImpl prioridadeServiceImpl = new PrioridadeServiceImpl();
	DisciplinaServiceImpl disciplinaService = new DisciplinaServiceImpl();
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	@GET
	@Path("/buscaDisciplinasProfessor/{siape}")
	@Consumes("application/json")
	public List<DisciplinaTurmaProfessor> buscaDisciplinasProfessor(@PathParam("siape") String siape) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		return disciplinaService.listaDisciplinaTurmaComHorariosDoProfessor(siape, semestre);

	}
	
}
