package br.com.ufu.model;

import java.io.Serializable;

public class CenarioPK implements Serializable {
	
	private int numCenario;
	private int idSemestre;
	private String siape;
	private int idTurma;
	
	public int getNumCenario() {
		return numCenario;
	}
	public void setNumCenario(int numCenario) {
		this.numCenario = numCenario;
	}
	public int getIdSemestre() {
		return idSemestre;
	}
	public void setIdSemestre(int idSemestre) {
		this.idSemestre = idSemestre;
	}
	public String getSiape() {
		return siape;
	}
	public void setSiape(String siape) {
		this.siape = siape;
	}
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSemestre;
		result = prime * result + idTurma;
		result = prime * result + numCenario;
		result = prime * result + ((siape == null) ? 0 : siape.hashCode());
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
		CenarioPK other = (CenarioPK) obj;
		if (idSemestre != other.idSemestre)
			return false;
		if (idTurma != other.idTurma)
			return false;
		if (numCenario != other.numCenario)
			return false;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CenarioPK [numCenario=" + numCenario + ", idSemestre=" + idSemestre + ", siape=" + siape + ", idTurma="
				+ idTurma + "]";
	}
	
	
}
