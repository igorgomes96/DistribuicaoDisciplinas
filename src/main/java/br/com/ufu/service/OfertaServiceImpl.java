package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Oferta;
import br.com.ufu.repository.OfertaRepositoryImpl;
@Path("/")
public class OfertaServiceImpl {
	
	private OfertaRepositoryImpl ofertaRepositoryImpl = new OfertaRepositoryImpl();
	
	public void savarHorarios(Long turmaId, List<Oferta> ofertas) {
		
		ofertaRepositoryImpl.removeHorarios(turmaId);
		
		ofertaRepositoryImpl.savarHorarios(ofertas);
		
	}

}
