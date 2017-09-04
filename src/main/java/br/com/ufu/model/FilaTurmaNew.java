package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fila_turma_new")
public class FilaTurmaNew implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="idTurma", column=@Column(name="id_turma")),
		@AttributeOverride(name="idFila", column=@Column(name="id_fila"))
	})
	private FilaTurmaPK filaTurmaPK;
	
	@ManyToOne
	@JoinColumn(name="id_turma", referencedColumnName="id", insertable=false, updatable=false)
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name="id_fila", referencedColumnName="id", insertable=false, updatable=false)
	private Fila fila;
	
	private int prioridade;

	public FilaTurmaPK getFilaTurmaPK() {
		return filaTurmaPK;
	}

	public void setFilaTurmaPK(FilaTurmaPK filaTurmaPK) {
		this.filaTurmaPK = filaTurmaPK;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Fila getFila() {
		return fila;
	}

	public void setFila(Fila fila) {
		this.fila = fila;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filaTurmaPK == null) ? 0 : filaTurmaPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilaTurmaNew other = (FilaTurmaNew) obj;
		if (filaTurmaPK == null) {
			if (other.filaTurmaPK != null)
				return false;
		} else if (!filaTurmaPK.equals(other.filaTurmaPK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FilaTurmaNew [filaTurmaPK=" + filaTurmaPK + ", turma=" + turma + ", fila=" + fila + ", prioridade="
				+ prioridade + "]";
	}
	
	
	
}
