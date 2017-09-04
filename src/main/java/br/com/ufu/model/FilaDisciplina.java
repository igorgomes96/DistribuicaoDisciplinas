package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="fila_turma")
public class FilaDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//@Column(name="siape")
	//private String siape;
	
	//@Column(name="codigo_disc")
	//private String codigoDisc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fila_turma_id_gen")
	@SequenceGenerator(name = "fila_turma_id_gen", sequenceName = "fila_turma_id_seq")
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "siape", updatable=false, insertable=false)
	private Professor3 professor;
	
	@ManyToOne
	@JoinColumn(name = "codigo_disc", updatable=false, insertable=false)
	private Disciplina disciplina;
	
	@Column(name="pos")
	private Integer pos;
	
	@Column(name="prioridade")
	private Integer prioridade;
	
	@Column(name="qte_ministrada")
	private Integer qteMinistrada;
	
	@Column(name="qte_maximo")
	private Integer qteMaximo;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="turma")
	private String turma;
	
	@Column(name="ch")
	private Integer ch;

	public Professor3 getProfessor() {
		return professor;
	}

	public void setProfessor(Professor3 professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
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

	@Override
	public String toString() {
		return "FilaDisciplina [professor=" + professor + ", disciplina=" + disciplina + ", pos=" + pos
				+ ", prioridade=" + prioridade + ", qteMinistrada=" + qteMinistrada + ", qteMaximo=" + qteMaximo
				+ ", status=" + status + ", turma=" + turma + ", ch=" + ch + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ch == null) ? 0 : ch.hashCode());
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((qteMaximo == null) ? 0 : qteMaximo.hashCode());
		result = prime * result + ((qteMinistrada == null) ? 0 : qteMinistrada.hashCode());
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
		FilaDisciplina other = (FilaDisciplina) obj;
		if (ch == null) {
			if (other.ch != null)
				return false;
		} else if (!ch.equals(other.ch))
			return false;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
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
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
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
