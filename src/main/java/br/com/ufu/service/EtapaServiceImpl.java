package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Etapa;
import br.com.ufu.repository.EtapaRepositoryImpl;

@Path("/")
public class EtapaServiceImpl {
	
	EtapaRepositoryImpl EtapaRepositoryImpl = new EtapaRepositoryImpl();
	
	public List<Etapa> listar() {
		List<Etapa> Etapas = EtapaRepositoryImpl.listar();
		return Etapas;
	}
	
	public void ativar(Etapa s) throws Exception{
		EtapaRepositoryImpl.ativar(s);
	}
}
