package br.com.ufu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fila_id_gen")
//	@SequenceGenerator(name = "fila_id_gen", sequenceName = "fila_id_seq")
//	Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "turma_id_gen")
	@SequenceGenerator(name = "turma_id_gen", sequenceName = "turma_id_seq")
	Integer id;
	
	@Column(name="codigo_disc")
	String codigoDisc;
	
	/*@ManyToOne
	@JoinColumn(name="codigo_disc")
	private Disciplina disciplina;*/
	
	@Column(name="turma")
	String turma;
	
	@Column(name="ch")
	Integer ch;
	
	@Column(name="ano")
	Integer ano;
	
	@Column(name="semestre")
	Short semestre;
	
	@Transient
	public List<Professor> professores = new ArrayList<Professor>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCodigoDisc() {
		return codigoDisc;
	}

	public void setCodigoDisc(String codigo_disc) {
		this.codigoDisc = codigo_disc;
	}
	
	public String getTurma() {
		return turma;
	}

	/*public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}*/

	public void setTurma(String turma) {
		this.turma = turma;
	}


	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}
	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Short getSemestre() {
		return semestre;
	}

	public void setSemestre(Short semestre) {
		this.semestre = semestre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [id=" + id + ", codigoDisc=" + codigoDisc + ", turma=" + turma + ", ch=" + ch + ", ano=" + ano
				+ ", semestre=" + semestre + ", professores=" + professores.stream().map(x -> x.getSiape()).collect(Collectors.toList()) + "]";
	}
	
	
	
	
}
