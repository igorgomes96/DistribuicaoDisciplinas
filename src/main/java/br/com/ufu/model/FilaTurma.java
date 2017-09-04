package br.com.ufu.model;

import java.io.Serializable;

public class FilaTurma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String siape;
	
	private String codigoDisc;
	
	private Integer pos;
	
	private Integer prioridade;
	
	private Integer qteMinistrada;
	
	private Integer qteMaximo;
	
	private Integer status;
	
	private String turma;
	
	private Integer ch;
	
	private Integer idTurma;

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public String getCodigoDisc() {
		return codigoDisc;
	}

	public void setCodigoDisc(String codigoDisc) {
		this.codigoDisc = codigoDisc;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getQteMinistrada() {
		return qteMinistrada;
	}

	public void setQteMinistrada(Integer qteMinistrada) {
		this.qteMinistrada = qteMinistrada;
	}

	public Integer getQteMaximo() {
		return qteMaximo;
	}

	public void setQteMaximo(Integer qteMaximo) {
		this.qteMaximo = qteMaximo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	@Override
	public String toString() {
		return "FilaTurma [siape=" + siape + ", codigoDisc=" + codigoDisc + ", pos=" + pos + ", prioridade="
				+ prioridade + ", qteMinistrada=" + qteMinistrada + ", qteMaximo=" + qteMaximo + ", status=" + status
				+ ", turma=" + turma + ", ch=" + ch + ", idTurma=" + idTurma + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ch == null) ? 0 : ch.hashCode());
		result = prime * result + ((codigoDisc == null) ? 0 : codigoDisc.hashCode());
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		result = prime * result + ((qteMaximo == null) ? 0 : qteMaximo.hashCode());
		result = prime * result + ((qteMinistrada == null) ? 0 : qteMinistrada.hashCode());
		result = prime * result + ((siape == null) ? 0 : siape.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		FilaTurma other = (FilaTurma) obj;
		if (ch == null) {
			if (other.ch != null)
				return false;
		} else if (!ch.equals(other.ch))
			return false;
		if (codigoDisc == null) {
			if (other.codigoDisc != null)
				return false;
		} else if (!codigoDisc.equals(other.codigoDisc))
			return false;
		if (idTurma == null) {
			if (other.idTurma != null)
				return false;
		} else if (!idTurma.equals(other.idTurma))
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (prioridade == null) {
			if (other.prioridade != null)
				return false;
		} else if (!prioridade.equals(other.prioridade))
			return false;
		if (qteMaximo == null) {
			if (other.qteMaximo != null)
				return false;
		} else if (!qteMaximo.equals(other.qteMaximo))
			return false;
		if (qteMinistrada == null) {
			if (other.qteMinistrada != null)
				return false;
		} else if (!qteMinistrada.equals(other.qteMinistrada))
			return false;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
	
}
