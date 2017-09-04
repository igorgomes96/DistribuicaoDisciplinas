package br.com.ufu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaTurma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoDisciplina;
	
	private String nomeDisciplina;
	
	private Integer turmaId;
	
	private String turma;
	
	private String codigoCurso;
	
	private String nomeCurso;
	
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

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public Integer getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Integer turmaId) {
		this.turmaId = turmaId;
	}

	@Override
	public String toString() {
		return "DisciplinaTurma [codigoDisciplina=" + codigoDisciplina + ", nomeDisciplina=" + nomeDisciplina
				+ ", turmaId=" + turmaId + ", turma=" + turma + ", codigoCurso=" + codigoCurso + ", nomeCurso="
				+ nomeCurso + ", horarios=" + horarios + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCurso == null) ? 0 : codigoCurso.hashCode());
		result = prime * result + ((codigoDisciplina == null) ? 0 : codigoDisciplina.hashCode());
		result = prime * result + ((horarios == null) ? 0 : horarios.hashCode());
		result = prime * result + ((nomeCurso == null) ? 0 : nomeCurso.hashCode());
		result = prime * result + ((nomeDisciplina == null) ? 0 : nomeDisciplina.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		result = prime * result + ((turmaId == null) ? 0 : turmaId.hashCode());
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
		DisciplinaTurma other = (DisciplinaTurma) obj;
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
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		if (turmaId == null) {
			if (other.turmaId != null)
				return false;
		} else if (!turmaId.equals(other.turmaId))
			return false;
		return true;
	}
	
}
