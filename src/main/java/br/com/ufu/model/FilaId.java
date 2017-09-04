package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FilaId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="siape")
	private String siape;
	
	@Column(name="codigo_disc")
	private String codigoDisc;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDisc == null) ? 0 : codigoDisc.hashCode());
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
		FilaId other = (FilaId) obj;
		if (codigoDisc == null) {
			if (other.codigoDisc != null)
				return false;
		} else if (!codigoDisc.equals(other.codigoDisc))
			return false;
		if (siape == null) {
			if (other.siape != null)
				return false;
		} else if (!siape.equals(other.siape))
			return false;
		return true;
	}
	
	 
}
