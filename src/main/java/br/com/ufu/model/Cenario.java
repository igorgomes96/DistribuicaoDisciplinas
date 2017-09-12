package br.com.ufu.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cenario")
public class Cenario {

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="numCenario", column=@Column(name="num_cenario")),
		@AttributeOverride(name="idSemestre", column=@Column(name="id_semestre")),
		@AttributeOverride(name="siape", column=@Column(name="siape")),
		@AttributeOverride(name="idTurma", column=@Column(name="id_turma"))
	})
	private CenarioPK cenarioPK;
	
	@ManyToOne
	@JoinColumn(name="siape", referencedColumnName="siape", insertable=false, updatable=false)
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name="id_semestre", referencedColumnName="id", insertable=false, updatable=false)
	private Semestre semestre;
	
	@ManyToOne
	@JoinColumn(name="id_turma", referencedColumnName="id", insertable=false, updatable=false)
	private Turma turma;

	public CenarioPK getCenarioPK() {
		return cenarioPK;
	}

	public void setCenarioPK(CenarioPK cenarioPK) {
		this.cenarioPK = cenarioPK;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cenarioPK == null) ? 0 : cenarioPK.hashCode());
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
		Cenario other = (Cenario) obj;
		if (cenarioPK == null) {
			if (other.cenarioPK != null)
				return false;
		} else if (!cenarioPK.equals(other.cenarioPK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cenario [cenarioPK=" + cenarioPK + ", professor=" + professor + ", semestre=" + semestre + ", turma="
				+ turma + "]";
	}
	
	
}
