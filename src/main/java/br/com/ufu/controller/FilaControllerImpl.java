package br.com.ufu.controller;

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

import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.FilaStatus;
import br.com.ufu.model.Professor;
import br.com.ufu.model.Semestre;
import br.com.ufu.service.FilaServiceImpl;
import br.com.ufu.service.SemestreServiceImpl;
import br.com.ufu.view.FilaPosicaoDTO;
import br.com.ufu.view.FilaSemestreDTO;
import br.com.ufu.view.FilaView;

@Path("/fila")
public class FilaControllerImpl {
	
	FilaServiceImpl filaServiceImpl = new FilaServiceImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	@GET
	@Path("/listaFilasSemestre")
	@Produces("application/json")
	public List<FilaSemestreDTO> listaFilasSemestre() {
		List<FilaSemestreDTO> filas = filaServiceImpl.listaFilasSemestre();
		return filas;
	}
	
	@POST
	@Path("/removeDisciplinasFila")
	@Produces("application/json")
	public void removeDisciplinasFila() {
		filaServiceImpl.removeDisciplinasFila();
	}
	
	@GET
	@Path("/listaFilaPorSiape/{siape}/{ano}/{semestre}")
	@Produces("application/json")
	public List<FilaDisciplinaProfessor> listaFilaPorSiape(@PathParam("siape") String siape, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = filaServiceImpl.listaFilaPorSiape(siape, semestre);
		
		return filaDisciplinaProfessores;
 
	}
	
	
	@GET
	@Path("/listaFilaPorCodigoDisciplina/{codigoDisciplina}/{ano}/{semestre}")
	@Produces("application/json")
	public List<FilaDisciplinaProfessor> listaFilaPorCodigoDisciplina(@PathParam("codigoDisciplina") String codigoDisciplina, @PathParam("ano") Integer ano, @PathParam("semestre") Integer numeroSemestre) {
		
		Semestre semestre = new Semestre();
		semestre.setAno(ano);
		semestre.setSemestre(numeroSemestre);
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = filaServiceImpl.listaFilaPorCodigoDisciplina(codigoDisciplina, semestre);
		
		return filaDisciplinaProfessores;
 
	}
	
	@GET
	@Path("/listaFilaDisciplina/{codigoDisciplina}")
	@Produces("application/json")
	public List<FilaPosicaoDTO> listaFilaDisciplina(@PathParam("codigoDisciplina") String codigoDisciplina) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		return filaServiceImpl.listaFilaDisciplina(codigoDisciplina, semestre);
 
	}
	
	
	@POST
	@Path("/entrarFilaDisciplina")
	@Consumes("application/json")
	public Response entrarFilaDisciplina( FilaView filaView ) {
		
		Long resultado = -10l;
		
		if (filaView == null || filaView.getSiape() == null || filaView.getSiape().isEmpty() || filaView.getCodigoDisciplina() == null || filaView.getCodigoDisciplina().isEmpty()) {
			resultado = -9l; // Parametros incorretos
		}
		
		// Verifica se existe um registro para o siape, codDisciplina recebido
		Fila fila = filaServiceImpl.buscaFila(filaView.getSiape(), filaView.getCodigoDisciplina());
		if (fila != null && fila.getId() != null) {
			
			int res = filaServiceImpl.atualizaStatus(filaView.getSiape(), filaView.getCodigoDisciplina(), FilaStatus.NAO_DISTRIBUIDA);
			
			String json = new Gson().toJson(res);

			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		}

		Integer proximaPosicao = filaServiceImpl.buscaProximaPosicao(filaView.getCodigoDisciplina());

		Integer proximaPrioridade = filaServiceImpl.buscaProximaPrioridadePorSiape(filaView.getSiape());
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		fila = new Fila();
		fila.setSiape(filaView.getSiape());
		fila.setCodigoDisc(filaView.getCodigoDisciplina());
		fila.setPos(proximaPosicao);
		fila.setPrioridade(proximaPrioridade);
		fila.setQteMinistrada(0);
		fila.setQteMaximo(4);
		fila.setStatus(-1);
		fila.setAno(semestre.getAno());
		fila.setSemestre(semestre.getSemestre());
		
		fila = filaServiceImpl.salvar(fila);
		
		resultado = fila.getId();

		String json = new Gson().toJson(resultado);

		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	
	@POST
	@Path("/atualizaStatusParaRemossao")
	@Consumes("application/json")
	public Response atualizaStatusParaRemossao( FilaView filaView ) {
		
		int resultado = 999;
		
		if (filaView == null || filaView.getSiape() == null || filaView.getSiape().isEmpty() || filaView.getCodigoDisciplina() == null || filaView.getCodigoDisciplina().isEmpty()) {
			resultado = -9; // Parametros incorretos
		}
		
		resultado = filaServiceImpl.atualizaStatus(filaView.getSiape(), filaView.getCodigoDisciplina(), FilaStatus.AGUARDANDO_REMOSSAO);

		String json = new Gson().toJson(resultado);

		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/copyFila")
	@Consumes("application/json")
	public Response copyFila(FilaSemestreDTO filaSemestre) {
		boolean hasFila = false;
		hasFila =  filaServiceImpl.hasFilaAnoSemestre(filaSemestre);
		
		if(!hasFila){
			List<Fila> listaFila =  filaServiceImpl.buscaTodosAnoSemestre(filaSemestre);
			
			for (Fila fila : listaFila) {
				fila.setAno(filaSemestre.getAno());
				fila.setSemestre(filaSemestre.getSemestre().intValue());
				fila.setId(null);
			}
			
			filaServiceImpl.salvarList(listaFila);
			
		}
		
		String json = new Gson().toJson("");
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
}
