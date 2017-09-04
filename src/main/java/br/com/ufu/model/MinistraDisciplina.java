package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="ministra")
public class MinistraDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fila_id_gen")
	@SequenceGenerator(name = "fila_id_gen", sequenceName = "fila_id_seq")
	Long id;
	
	@Column(name="siape")
	private String siape;

	@Column(name="turma")
	private String turma;
	
	@Column(name="codigoDisciplina")
	private String codigodisciplina;

	@Column(name="nomeDisciplina")
	private String nomedisciplina;

	@Column(name="nomeCurso")
	private String nomeCurso;
	
	@Column(name="idTurma")
	private Integer idTurma;
	
	@Column(name="turmaAno")
	private Integer turmaAno;
	
	@Column(name="turmaSemestre")
	private Short turmaSemestre;
	
	@Column(name="semestre")
	private Integer semestre;
	
	@Column(name="ano")
	private Integer ano;
	
	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	public String getCodigoDisc(){
		return codigodisciplina;
	}
	
	public void setCodigoDisc(String codigodisciplina) {
		this.codigodisciplina = codigodisciplina;
		
	}

	public String getNomeDisc(){
		return nomedisciplina;
	}
	public void setNomeDisc(String nomedisciplina) {
		this.nomedisciplina = nomedisciplina;
		
	}

	public String getNomeCurso(){
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
		
	}
	
	public Integer getTurmaId(){
		return idTurma;
	}
	
	public void setTurmaId(Integer idTurma) {
		this.idTurma = idTurma;
		
	}
	
	public Integer getAno() {
		return ano;
	}

	public Integer getSemestre() {
		return semestre;
	}
	
	public Integer getTurmaAno() {
		return turmaAno;
	}
	
	public Short getTurmaSemestre() {
		return turmaSemestre;
	}
	
	public void setTumaAno(Integer turmaAno) {
		this.turmaAno = turmaAno;
	}

	public void setTurmaSemestre(Short turmaSemestre) {
		this.turmaSemestre = turmaSemestre;
	}

//	@Override
//	public String toString() {
//		return "Fila [id=" + id + ", siape=" + siape + ", codigoDisc=" + codigoDisc + ", pos=" + pos + ", prioridade="
//				+ prioridade + ", qteMinistrada=" + qteMinistrada + ", qteMaximo=" + qteMaximo + ", status=" + status
//				+ ", ano=" + ano + ", semestre=" + semestre + "]";
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
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
		MinistraDisciplina other = (MinistraDisciplina) obj;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		return true;
	}
}
