package br.com.ufu.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.ufu.model.Semestre;
import br.com.ufu.repository.RelatorioRepositoryImpl;
import br.com.ufu.service.SemestreServiceImpl;
import br.com.ufu.view.RelatorioFilaDTO;
import br.com.ufu.view.RelatorioFila3DTO;
import br.com.ufu.view.RelatorioFila4DTO;

@Path("/relatorio")
public class RelatorioControllerImpl {
	
	RelatorioRepositoryImpl relatorioServiceImpl = new RelatorioRepositoryImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();

	@GET
	@Path("/relatFila/{ano}/{semestre}/{order}")
	@Produces("application/json")
	public List<RelatorioFilaDTO> relatFila(@PathParam("ano") Integer ano, @PathParam("semestre") Integer semestre, @PathParam("order") String order) {
		
		Semestre semestreBusca = null;
		if(ano == null || semestre == null){
			semestreBusca = semestreServiceImpl.buscarUltimoSemestre();
		} else {
			semestreBusca = new Semestre();
			semestreBusca.setAno(ano);
			semestreBusca.setSemestre(semestre);
		}
		
		List<RelatorioFilaDTO> relatDTO = relatorioServiceImpl.relatFila(semestreBusca, order);
		return relatDTO;
	}

	@GET
	@Path("/relatFila4")
	@Produces("application/json")
	public List<RelatorioFila4DTO> relatFila4() {

		List<RelatorioFila4DTO> relatDTO = relatorioServiceImpl.relatFila4();
		return relatDTO;
	}

	@GET
	@Path("/relatFila3")
	@Produces("application/json")
	public List<RelatorioFila3DTO> relatFila3() {

		List<RelatorioFila3DTO> relatDTO = relatorioServiceImpl.relatFila3();
		return relatDTO;
	}

}
