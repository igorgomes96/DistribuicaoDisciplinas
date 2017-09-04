package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.FilaTurmaNew;
import br.com.ufu.repository.FilaTurmaRepositoryImpl;
@Path("/")
public class FilaTurmaServiceImpl {

	FilaTurmaRepositoryImpl filaTurmaRep = new FilaTurmaRepositoryImpl();
	
	public List<FilaTurmaNew> FindAllFilaTurma() {
		return filaTurmaRep.FindAllFilaTurma();
	}
}
