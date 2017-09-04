package br.com.ufu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

import br.com.ufu.model.DisciplinaTurmaProfessor;
import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplina;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.FilaTurma;
import br.com.ufu.model.Semestre;
import br.com.ufu.service.FilaDisciplinaServiceImpl;
import br.com.ufu.service.FilaServiceImpl;
import br.com.ufu.service.SemestreServiceImpl;
import br.com.ufu.view.FilaDisciplinaView;
import br.com.ufu.view.FilaTurmaView;
import br.com.ufu.view.ListaDisciplinaProfessorView;

@Path("/filaDiscilplina")
public class FilaDisciplinaControllerImpl {
	
	FilaDisciplinaServiceImpl filaDisciplinaServiceImpl = new FilaDisciplinaServiceImpl();
	FilaServiceImpl filaServiceImpl = new FilaServiceImpl();
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	/*@GET
	@Path("/buscaPorCodigoDisciplina/{codigoDisc}")
	@Produces("application/json")
	public Response buscaPorCodigoDisciplina(@PathParam("codigoDisc") String codigoDisc) {
		
		List<FilaDisciplina> filaDisciplina = filaDisciplinaServiceImpl.buscaPorCodigoDisciplina(codigoDisc);
		
		String json = new Gson().toJson(filaDisciplina);
		
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
 
	}*/
	
	
	@GET
	@Path("/buscaFilaProfessoresPorDisciplina/{codigoDisc}/{turmaId}/{ano}/{semestre}")
	@Produces("application/json")
	public FilaDisciplinaView buscaFilaProfessoresPorDisciplina(@PathParam("codigoDisc") String codigoDisc, @PathParam("turmaId") Long turmaId, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<FilaDisciplina> filaDisciplinas = filaDisciplinaServiceImpl.buscaPorCodigoDisciplina(codigoDisc, turmaId, semestre);
		 
		FilaDisciplinaView filaDisciplinaView = this.criaFilaDisciplinaView(filaDisciplinas);
		
		return filaDisciplinaView;
		
		//String json = new Gson().toJson(filaDisciplinaView);
		
		//return Response.ok(json, MediaType.APPLICATION_JSON).build();
 
	}
	
	
	@GET
	@Path("/buscaPorSiape/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> buscaPorSiape(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<DisciplinaTurmaProfessor> filaProfessor = filaDisciplinaServiceImpl.buscaPorSiape(siape, semestre);
		
		return filaProfessor;
 
	}
	
	
	@GET
	@Path("/buscaPorSiapeDisciplina/{siape}/{codigoDisc}")
	@Produces("application/json")
	public List<DisciplinaTurmaProfessor> buscaPorSiapeDisciplina(@PathParam("siape") String siape,@PathParam("codigoDisc") String codigoDisc ) {
		
		List<DisciplinaTurmaProfessor> filaProfessor = filaDisciplinaServiceImpl.buscaPorSiapeDisciplina(siape, codigoDisc);
		
		return filaProfessor;
 
	}
	
	@POST
	@Path("/atualizaPosicao")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizaPosicao(@RequestBody List<Fila> filas) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		filaDisciplinaServiceImpl.atualizaFila(filas);

		return Response.ok().build();
	}
	
	@POST
	@Path("/atualizaPrioridades")
	@Consumes("application/json")
	public Response atualizaPrioridades( List<FilaTurmaView> filaTurmasView ) {
		
		int resultado = 999;
		
		if (filaTurmasView == null) {
			String json = new Gson().toJson(resultado);

			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}
		
		List<FilaTurma> filaTurmas = new ArrayList<FilaTurma>();
		
		for (FilaTurmaView filaTurmaView : filaTurmasView) {
			
			FilaTurma filaTurma = new FilaTurma();
			
			filaTurma.setSiape(filaTurmaView.getSiape());
			filaTurma.setCodigoDisc(filaTurmaView.getCodigoDisciplina());
			filaTurma.setIdTurma(filaTurmaView.getIdTurma());
			filaTurma.setPrioridade(filaTurmaView.getPrioridade());
			
			filaTurmas.add(filaTurma);
			
		}
		
		List<Fila> filas = new ArrayList<Fila>();
		Boolean add = true;
		for (int i=0; i<filaTurmas.size(); i++) {
			
			add = true;
			
			for (int j=0; j<filas.size(); j++) {
				
				if ( filaTurmas.get(i).getSiape().equals(filas.get(j).getSiape()) && filaTurmas.get(i).getCodigoDisc().equals(filas.get(j).getCodigoDisc()) ) {
					
					add = false;
					
					break;
					
				}
				
			}
			
			if (add) {
				Fila fila = new Fila();
				fila.setSiape(filaTurmas.get(i).getSiape());
				fila.setCodigoDisc(filaTurmas.get(i).getCodigoDisc());
				fila.setPrioridade(filaTurmas.get(i).getPrioridade());
				
				filas.add(fila);
			}
			
		}
		
		filaServiceImpl.atualizaPrioridade(filas);
		
		resultado = filaDisciplinaServiceImpl.atualizaPrioridades(filaTurmas);

		String json = new Gson().toJson(resultado);

		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	
	/*@POST
	@Path("/atualizaStatusParaRemossao")
	@Consumes("application/json")
	public Response atualizaStatusParaRemossao( FilaTurmaView filaTurmaView ) {
		
		int resultado = 999;
		
			
		FilaTurma filaTurma = new FilaTurma();
		
		filaTurma.setSiape(filaTurmaView.getSiape());
		filaTurma.setCodigoDisc(filaTurmaView.getCodigoDisciplina());
		filaTurma.setTurma(filaTurmaView.getTurma());
		filaTurma.setStatus(FilaStatus.AGUARDANDO_REMOSSAO);
		
		resultado = filaDisciplinaServiceImpl.atualizaStatus(filaTurma);

		String json = new Gson().toJson(resultado);

		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}*/
	
	
	private FilaDisciplinaView criaFilaDisciplinaView(List<FilaDisciplina> filaDisciplinas) {
		
		FilaDisciplinaView filaDisciplinaView = new FilaDisciplinaView();
		
		if (filaDisciplinas == null || filaDisciplinas.isEmpty()) {
			
			return null;
		
		} else {
			
			filaDisciplinaView.setDisciplina(filaDisciplinas.get(0).getDisciplina());
			
		}
		
		List<ListaDisciplinaProfessorView> listaDisciplinaProfessorViews = new ArrayList<ListaDisciplinaProfessorView>();
		
		for (FilaDisciplina filaDisciplina : filaDisciplinas) {
			
			ListaDisciplinaProfessorView listaDisciplinaProfessorView = new ListaDisciplinaProfessorView();
			
			listaDisciplinaProfessorView.setNome(filaDisciplina.getProfessor().getNome());
			
			listaDisciplinaProfessorView.setPos(filaDisciplina.getPos());
			
			listaDisciplinaProfessorView.setPrioridade(filaDisciplina.getPrioridade());
			
			listaDisciplinaProfessorView.setQuantidadeMinistrada(filaDisciplina.getQteMinistrada());
			
			listaDisciplinaProfessorView.setQuantidadeMaxima(filaDisciplina.getQteMaximo());
			
			listaDisciplinaProfessorViews.add(listaDisciplinaProfessorView);
			
		}
		
		filaDisciplinaView.setListaProfessores(listaDisciplinaProfessorViews);
		
		return filaDisciplinaView;
	}


}
