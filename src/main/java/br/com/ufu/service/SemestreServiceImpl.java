package br.com.ufu.service;

import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Semestre;
import br.com.ufu.repository.SemestreRepositoryImpl;
@Path("/")
public class SemestreServiceImpl {
	
	SemestreRepositoryImpl semestreRepositoryImpl = new SemestreRepositoryImpl();
	
	
	public Semestre buscarUltimoSemestre() {
		Semestre semestre = semestreRepositoryImpl.buscarUltimoSemestre();
		return semestre;
	}
	
	
	public List<Semestre> listar() {
		List<Semestre> semestres = semestreRepositoryImpl.listar();
		return semestres;
	}
	
	public void save(Semestre s) throws Exception{
		semestreRepositoryImpl.save(s);
	}
	
	public void delete(Semestre s) throws Exception{
		semestreRepositoryImpl.delete(s);
	}
	
	public void ativar(Semestre s) throws Exception{
		semestreRepositoryImpl.ativar(s);
	}
	
	public void update(Semestre s) throws Exception{
		semestreRepositoryImpl.update(s);
	}

}
