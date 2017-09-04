package br.com.ufu.service;
import java.util.List;

import javax.ws.rs.Path;

import br.com.ufu.model.Fila;
import br.com.ufu.model.FilaDisciplinaProfessor;
import br.com.ufu.model.Semestre;
import br.com.ufu.repository.FilaRepositoryImpl;
import br.com.ufu.view.FilaPosicaoDTO;
import br.com.ufu.view.FilaSemestreDTO;
@Path("/")
public class FilaServiceImpl {
	
	FilaRepositoryImpl filaRepositoryImpl = new FilaRepositoryImpl();
	
	SemestreServiceImpl semestreServiceImpl = new SemestreServiceImpl();
	
	
	public Fila buscaFila(String siape, String codDisciplina) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		Fila fila = filaRepositoryImpl.buscaFila(siape, codDisciplina, semestre);
		
		return fila;
		
	}
	
	public void removeDisciplinasFila() {
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		filaRepositoryImpl.removeDisciplinasFila(semestre);
	}
	
	
	
	
	public List<FilaSemestreDTO> listaFilasSemestre() {
				
		List<FilaSemestreDTO> filaSemestreDTO = filaRepositoryImpl.listaFilaSemestre();
		
		return filaSemestreDTO;
		
	}
	
	
	public List<FilaDisciplinaProfessor> listaFilaPorSiape(String siape, Semestre semestre) {
		
		//Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = filaRepositoryImpl.listaFilaPorSiape(siape, semestre);
		
		return filaDisciplinaProfessores;
		
	}
	
	
	public List<FilaDisciplinaProfessor> listaFilaPorCodigoDisciplina(String codigoDisciplina, Semestre semestre) {
		
		//Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		List<FilaDisciplinaProfessor> filaDisciplinaProfessores = filaRepositoryImpl.listaFilaPorCodigoDisciplina(codigoDisciplina, semestre);
		
		return filaDisciplinaProfessores;
		
	}
	
	public List<FilaPosicaoDTO> listaFilaDisciplina(String codigoDisciplina, Semestre semestre) {
		
		return filaRepositoryImpl.listaFilaDisciplina(codigoDisciplina, semestre);
		
	}
	
	
	
	
	
	public Integer buscaProximaPrioridadePorSiape(String siape) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		return filaRepositoryImpl.buscaProximaPrioridadePorSiape(siape, semestre);
		
	}
	
	
	public Integer buscaProximaPosicao(String codDisciplina) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		return filaRepositoryImpl.buscaProximaPosicao(codDisciplina, semestre);
		
	}
	
	
	public Fila salvar(Fila fila) {
		
		fila = filaRepositoryImpl.salvar(fila);
		
		return fila;
		
	}
	
	public void salvarList(List<Fila> newFila){
		filaRepositoryImpl.salvarList(newFila);
	}
	
	
	public int atualizaStatus(String siape, String codigoDisciplina, Integer status) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		return filaRepositoryImpl.atualizaStatus(siape, codigoDisciplina, status, semestre);
		
	}
	
	
	public int atualizaPrioridade(List<Fila> filas) {
		
		Semestre semestre = semestreServiceImpl.buscarUltimoSemestre();
		
		return filaRepositoryImpl.atualizaPrioridade(filas, semestre);
		
	}
	
	public boolean hasFilaAnoSemestre(FilaSemestreDTO filasemestre){
		return filaRepositoryImpl.hasFilaAnoSemestre(filasemestre);
	}
	
	public List<Fila> buscaTodosAnoSemestre (FilaSemestreDTO filasemestre){
		return filaRepositoryImpl.buscaTodosAnoSemestre(filasemestre);
	}
	

}
