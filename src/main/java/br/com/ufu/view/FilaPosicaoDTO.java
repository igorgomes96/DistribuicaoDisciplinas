package br.com.ufu.view;

import javax.persistence.Column;

public class FilaPosicaoDTO {

	Long id;
	private String siape;
	private String codigoDisc;
	private Integer pos;
	private Integer prioridade;
	private Integer qteMinistrada;
	private Integer qteMaximo;
	private Integer status;
	private Integer ano;
	private Integer semestre;
	private String nomeProf;
	
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
	public String getNomeProf() {
		return nomeProf;
	}
	public void setNomeProf(String nomeProf) {
		this.nomeProf = nomeProf;
	}
}
