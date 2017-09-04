package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="disciplina")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	String codigo;
	
	@Column(name="nome")
	String nome;
	
	@Column(name="ch_teorica")
	Integer chTeorica;
	
	@Column(name="ch_pratica")
	Integer chPratica;
	
	@Column(name="ch_total")
	Integer chtotal;
	
	@Column(name="periodo")
	Short periodo;
	
	@Column(name="curso")
	String curso;
	
	@Column(name="temfila")
	Boolean temfila;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getFila(){
		return temfila;
	}
	
	public void setFila(Boolean temfila){
		this.temfila = temfila;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getChTeorica() {
		return chTeorica;
	}

	public void setChTeorica(Integer chTeorica) {
		this.chTeorica = chTeorica;
	}

	public Integer getChPratica() {
		return chPratica;
	}

	public void setChPratica(Integer chPratica) {
		this.chPratica = chPratica;
	}

	public Integer getChtotal() {
		return chtotal;
	}

	public void setChtotal(Integer chtotal) {
		this.chtotal = chtotal;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public Short getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Short row) {
		this.periodo = row;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chPratica == null) ? 0 : chPratica.hashCode());
		result = prime * result + ((chTeorica == null) ? 0 : chTeorica.hashCode());
		result = prime * result + ((chtotal == null) ? 0 : chtotal.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((temfila == null) ? 0 : temfila.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (chPratica == null) {
			if (other.chPratica != null)
				return false;
		} else if (!chPratica.equals(other.chPratica))
			return false;
		if (chTeorica == null) {
			if (other.chTeorica != null)
				return false;
		} else if (!chTeorica.equals(other.chTeorica))
			return false;
		if (chtotal == null) {
			if (other.chtotal != null)
				return false;
		} else if (!chtotal.equals(other.chtotal))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
