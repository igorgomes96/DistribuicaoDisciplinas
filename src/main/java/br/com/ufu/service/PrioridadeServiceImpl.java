package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.FilaRepositoryImpl;
import br.com.ufu.repository.PrioridadeRepositoryImpl;
import br.com.ufu.view.FilaSemestreDTO;
@Path("/")
public class PrioridadeServiceImpl {
	
	PrioridadeRepositoryImpl prioridadeRepositoryImpl = new PrioridadeRepositoryImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	

}
