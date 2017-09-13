package br.com.ufu.view;

import br.com.ufu.model.Turma;

public class TurmaDTO {
	
	private Turma turma;
	private String nomeDisciplina;
	private String nomeCurso;
	private String ch;
	

	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	@Override
	public String toString() {
		return "TurmaDTO [turma=" + turma + ", nomeDisciplina=" + nomeDisciplina + ", nomeCurso=" + nomeCurso + "]";
	}
	
}
