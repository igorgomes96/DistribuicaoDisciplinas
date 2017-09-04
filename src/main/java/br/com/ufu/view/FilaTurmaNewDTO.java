package br.com.ufu.view;

import br.com.ufu.model.FilaTurmaNew;

public class FilaTurmaNewDTO {
	
	private int idFila;
	private int idTurma;
	private int prioridade;
	
	public FilaTurmaNewDTO() {}
	
	public FilaTurmaNewDTO(FilaTurmaNew filaTurma) {
		this.idFila = filaTurma.getFilaTurmaPK().getIdFila();
		this.idTurma = filaTurma.getFilaTurmaPK().getIdTurma();
		this.prioridade = filaTurma.getPrioridade();
	}

	public int getIdFila() {
		return idFila;
	}

	public void setIdFila(int idFila) {
		this.idFila = idFila;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public String toString() {
		return "FilaTurmaNewDTO [idFila=" + idFila + ", idTurma=" + idTurma + ", prioridade=" + prioridade + "]";
	}
	
	
	
}
