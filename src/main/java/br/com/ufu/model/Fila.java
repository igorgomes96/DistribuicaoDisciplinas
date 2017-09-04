package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * NOVA CLASSE - NAO APAGAR
 */

@Entity
@Table(name="fila")
public class Fila implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fila_id_gen")
	@SequenceGenerator(name = "fila_id_gen", sequenceName = "fila_id_seq")
	Long id;
	
	@Column(name="siape")
	private String siape;
	
	@Column(name="codigo_disc")
	private String codigoDisc;
	
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
	
	@Column(name="ano")
	private Integer ano;
	
	@Column(name="semestre")
	private Integer semestre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		return "Fila [id=" + id + ", siape=" + siape + ", codigoDisc=" + codigoDisc + ", pos=" + pos + ", prioridade="
				+ prioridade + ", qteMinistrada=" + qteMinistrada + ", qteMaximo=" + qteMaximo + ", status=" + status
				+ ", ano=" + ano + ", semestre=" + semestre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((codigoDisc == null) ? 0 : codigoDisc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		result = prime * result + ((qteMaximo == null) ? 0 : qteMaximo.hashCode());
		result = prime * result + ((qteMinistrada == null) ? 0 : qteMinistrada.hashCode());
		result = prime * result + ((semestre == null) ? 0 : semestre.hashCode());
		result = prime * result + ((siape == null) ? 0 : siape.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Fila other = (Fila) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (codigoDisc == null) {
			if (other.codigoDisc != null)
				return false;
		} else if (!codigoDisc.equals(other.codigoDisc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (semestre == null) {
			if (other.semestre != null)
				return false;
		} else if (!semestre.equals(other.semestre))
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
		return true;
	}

}
