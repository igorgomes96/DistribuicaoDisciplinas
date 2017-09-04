package br.com.ufu.view;

import br.com.ufu.model.Restricao;

public class RestricaoDTO {
    
	private Restricao restricao;
	private String diaNome;
	private String horarioNome;
	private String professorNome;
	
	public String getProfessorNome() {
		return professorNome;
	}
	public void setProfessorNome(String professorNome) {
		this.professorNome = professorNome;
	}
	public Restricao getRestricao() {
		return restricao;
	}
	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}
	public String getDiaNome() {
		return diaNome;
	}
	public void setDiaNome(String diaNome) {
		this.diaNome = diaNome;
	}
	public String getHorarioNome() {
		return horarioNome;
	}
	public void setHorarioNome(String horarioNome) {
		this.horarioNome = horarioNome;
	}

}
