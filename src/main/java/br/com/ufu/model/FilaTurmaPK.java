package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FilaTurmaPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idTurma;
	private int idFila;
	
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	public int getIdFila() {
		return idFila;
	}
	public void setIdFila(int idFila) {
		this.idFila = idFila;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idFila;
		result = prime * result + idTurma;
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
		FilaTurmaPK other = (FilaTurmaPK) obj;
		if (idFila != other.idFila)
			return false;
		if (idTurma != other.idTurma)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FilaTurmaPK [idTurma=" + idTurma + ", idFila=" + idFila + "]";
	}
	
	
	
}
