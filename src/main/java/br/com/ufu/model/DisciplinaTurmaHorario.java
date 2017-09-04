package br.com.ufu.model;

import java.util.ArrayList;
import java.util.List;

/*
 * NOVA CLASSE - NAO APAGAR
 */

public class DisciplinaTurmaHorario {

	  private String codigoDisciplina;
	  
	  private String nomeDisciplina;
	  
	  private String cargaHorariaTotal;
	  
	  private String codigoCurso;
	  
	  private String nomeCurso;
	  
	  List<TurmaDisciplina> turmas = new ArrayList<TurmaDisciplina>();

	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}

	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(String cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public List<TurmaDisciplina> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<TurmaDisciplina> turmas) {
		this.turmas = turmas;
	}
	
}
