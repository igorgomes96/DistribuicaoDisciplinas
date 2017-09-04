package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Restricao;
import br.com.ufu.repository.RestricaoRepositoryImpl;
import br.com.ufu.view.RestricaoDTO;
@Path("/")
public class RestricaoServiceImpl {
	
	RestricaoRepositoryImpl restricaoRepositoryImpl = new RestricaoRepositoryImpl();
	
	
	public List<RestricaoDTO> listar() {
		List<RestricaoDTO> restricoes = restricaoRepositoryImpl.listar();
		return restricoes;
	}
	
	public void save(Restricao r) throws Exception{
		restricaoRepositoryImpl.save(r);
	}
	
	public void delete(Restricao r) throws Exception{
		restricaoRepositoryImpl.delete(r);
	}
	
}
