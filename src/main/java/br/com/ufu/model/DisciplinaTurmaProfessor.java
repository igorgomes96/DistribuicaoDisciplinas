package br.com.ufu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaTurmaProfessor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoDisciplina;
	
	private String nomeDisciplina;
	
	private String turma;
	
	private String codigoCurso;
	
	private String nomeCurso;
	
	private String siape;

	private Integer prioridade;
	
	private Integer pos;
	
	private Integer qteMinistrada;
	
	private Integer qteMaxima;
	
	private String idTurma;
	
	private List<Horario> horarios = new ArrayList<Horario>();

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

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
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

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getQteMinistrada() {
		return qteMinistrada;
	}

	public void setQteMinistrada(Integer qteMinistrada) {
		this.qteMinistrada = qteMinistrada;
	}

	public Integer getQteMaxima() {
		return qteMaxima;
	}

	public void setQteMaxima(Integer qteMaxima) {
		this.qteMaxima = qteMaxima;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	@Override
	public String toString() {
		return "DisciplinaTurmaProfessor [codigoDisciplina=" + codigoDisciplina + ", nomeDisciplina=" + nomeDisciplina
				+ ", turma=" + turma + ", codigoCurso=" + codigoCurso + ", nomeCurso=" + nomeCurso + ", siape=" + siape
				+ ", prioridade=" + prioridade + ", pos=" + pos + ", qteMinistrada=" + qteMinistrada + ", qteMaxima="
				+ qteMaxima + ", idTurma=" + idTurma + ", horarios=" + horarios + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCurso == null) ? 0 : codigoCurso.hashCode());
		result = prime * result + ((codigoDisciplina == null) ? 0 : codigoDisciplina.hashCode());
		result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
		result = prime * result + ((nomeCurso == null) ? 0 : nomeCurso.hashCode());
		result = prime * result + ((nomeDisciplina == null) ? 0 : nomeDisciplina.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		result = prime * result + ((qteMaxima == null) ? 0 : qteMaxima.hashCode());
		result = prime * result + ((qteMinistrada == null) ? 0 : qteMinistrada.hashCode());
		result = prime * result + ((siape == null) ? 0 : siape.hashCode());
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
		DisciplinaTurmaProfessor other = (DisciplinaTurmaProfessor) obj;
		if (codigoCurso == null) {
			if (other.codigoCurso != null)
				return false;
		} else if (!codigoCurso.equals(other.codigoCurso))
			return false;
		if (codigoDisciplina == null) {
			if (other.codigoDisciplina != null)
				return false;
		} else if (!codigoDisciplina.equals(other.codigoDisciplina))
			return false;
		if (horarios == null) {
			if (other.horarios != null)
				return false;
		} else if (!horarios.equals(other.horarios))
			return false;
		if (idTurma == null) {
			if (other.idTurma != null)
				return false;
		} else if (!idTurma.equals(other.idTurma))
			return false;
		if (nomeCurso == null) {
			if (other.nomeCurso != null)
				return false;
		} else if (!nomeCurso.equals(other.nomeCurso))
			return false;
		if (nomeDisciplina == null) {
			if (other.nomeDisciplina != null)
				return false;
		} else if (!nomeDisciplina.equals(other.nomeDisciplina))
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
		if (qteMaxima == null) {
			if (other.qteMaxima != null)
				return false;
		} else if (!qteMaxima.equals(other.qteMaxima))
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
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
	
}
