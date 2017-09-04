package br.com.ufu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor2 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long siape;
	
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

	public Long getSiape() {
		return siape;
	}

	public void setSiape(Long siape) {
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

	@Override
	public String toString() {
		return "Professor [siape=" + siape + ", nome=" + nome + ", dataIngresso=" + dataIngresso + ", dataNasc="
				+ dataNasc + ", afastado=" + afastado + ", regime=" + regime + ", cargaAtual=" + cargaAtual + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afastado == null) ? 0 : afastado.hashCode());
		result = prime * result + ((cargaAtual == null) ? 0 : cargaAtual.hashCode());
		result = prime * result + ((dataIngresso == null) ? 0 : dataIngresso.hashCode());
		result = prime * result + ((dataNasc == null) ? 0 : dataNasc.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((regime == null) ? 0 : regime.hashCode());
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
		Professor2 other = (Professor2) obj;
		if (afastado == null) {
			if (other.afastado != null)
				return false;
		} else if (!afastado.equals(other.afastado))
			return false;
		if (cargaAtual == null) {
			if (other.cargaAtual != null)
				return false;
		} else if (!cargaAtual.equals(other.cargaAtual))
			return false;
		if (dataIngresso == null) {
			if (other.dataIngresso != null)
				return false;
		} else if (!dataIngresso.equals(other.dataIngresso))
			return false;
		if (dataNasc == null) {
			if (other.dataNasc != null)
				return false;
		} else if (!dataNasc.equals(other.dataNasc))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (regime == null) {
			if (other.regime != null)
				return false;
		} else if (!regime.equals(other.regime))
			return false;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		return true;
	}
}
