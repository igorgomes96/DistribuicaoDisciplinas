package br.com.ufu.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="etapa")
public class Etapa{

//	private static final long serialVersionUID = 1L;
	@Id 
	Integer id;
	
//	@Column(name="id")
//	Integer id;
	
	@Column(name="codigo")
	String codigo;
	
	@Column(name="ativo")
	Boolean ativo;
	
	@Column(name="descricao")
	String descricao;

	public Integer getId() {
		return id;
	}

	public Integer setId(Integer id) {
		return this.id = id;
	}

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
