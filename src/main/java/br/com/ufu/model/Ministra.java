package br.com.ufu.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ministra")
public class Ministra implements Serializable {

	private static final long serialVersionUID = -6414387982730007510L;

	@Id 
	String siape;
	
	@Id 
	Integer id_turma;
	
	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public Integer getTurmaId(){
		return id_turma;
	}
	
	public void setTurmaId(Integer id_turma) {
		this.id_turma = id_turma;
		
	}
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
//		result = prime * result + ((siape == null) ? 0 : siape.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Ministra other = (Ministra) obj;
//		if (idTurma == null) {
//			if (other.idTurma != null)
//				return false;
//		} else if (!idTurma.equals(other.idTurma))
//			return false;
//		if (siape == null) {
//			if (other.siape != null)
//				return false;
//		} else if (!siape.equals(other.siape))
//			return false;
//		return true;
//	}
}
