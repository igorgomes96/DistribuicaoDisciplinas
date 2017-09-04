package br.com.ufu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

	private static final long serialVersionUID = 2771576679187218289L;

	@Id
	private String siape;

	@Column(name="nome")
	private String nome;

	@Column(name="data_ingresso")
	private Date dataIngresso;

	@Column(name="data_nasc")
	private Date dataNasc;

	@Column(name="afastado")
	private Boolean afastado;

	@Column(name="regime")
	private String regime;

	@Column(name="carga_atual")
	private Long cargaAtual;
	
	@Column(name="lotacao")
	private String lotacao;
	
	@Column(name="cnome")
	private String cnome;

	@Column(name="data_saida")
	private Date dataSaida;

	@Column(name="data_exoneracao")
	private Date dataExoneracao;
	
	@Column(name="status")
	private String statusProfessor;

	@Column(name="data_aposentadoria")
	private Date dataAposentadoria;
	
	@Transient
	public List<Turma> turmas = new ArrayList<Turma>();
	
	@Transient
	public int cargaDistribuida;

	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getCnome() {
		return cnome;
	}

	public void setCnome(String cnome) {
		this.cnome = cnome;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(Date dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Boolean getAfastado() {
		return afastado;
	}

	public void setAfastado(Boolean afastado) {
		this.afastado = afastado;
	}

	public String getRegime() {
		return regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	public Long getCargaAtual() {
		return cargaAtual;
	}

	public void setCargaAtual(Long cargaAtual) {
		this.cargaAtual = cargaAtual;
	}
	
	
	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataExoneracao() {
		return dataExoneracao;
	}

	public void setDataExoneracao(Date dataExoneracao) {
		this.dataExoneracao = dataExoneracao;
	}
	
	public String getStatusProfessor() {
		return statusProfessor;
	}
	
	public void setStatusProfessor(String statusProfessor) {
		this.statusProfessor = statusProfessor;
	}

	public Date getDataAposentadoria() {
		return dataAposentadoria;
	}

	public void setDataAposentadoria(Date dataAposentadoria) {
		this.dataAposentadoria = dataAposentadoria;
	}


	@Override
	public String toString() {
		return "Professor [siape=" + siape + ", nome=" + nome + ", dataIngresso=" + dataIngresso + ", dataNasc="
				+ dataNasc + ", afastado=" + afastado + ", regime=" + regime + ", cargaAtual=" + cargaAtual
				+ ", lotacao=" + lotacao + ", cnome=" + cnome + ", dataSaida=" + dataSaida + ", dataExoneracao="
				+ dataExoneracao + ", statusProfessor=" + statusProfessor + ", dataAposentadoria=" + dataAposentadoria
				+ ", turmas=" + turmas + ", cargaDistribuida=" + cargaDistribuida + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Professor other = (Professor) obj;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		return true;
	}

	

}
