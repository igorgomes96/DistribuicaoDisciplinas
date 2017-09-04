package br.com.ufu.view;

/*
 * NOVA CLASSE - NAO APAGAR
 */

public class FilaProfessorViewDTO {
    
	private String siape;
	private String nomeProfessor;
	private String codigoDisc;
	private Integer pos;
	private String quantidade;
	private Integer prioridade;
	private Integer qteMinistrada;
	private Integer qteMaximo;
	private Integer status;
	private Integer ano;
	private Integer semestre;
	private Boolean periodo;
	private String idFila;
	private String nomDisc;
	private String nomCurso;
	
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
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
	public void setPos(Integer posicaoFila) {
		this.pos = posicaoFila;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getSiape() {
		return siape;
	}
	public void setSiape(String siape) {
		this.siape = siape;
	}
	public Integer getSemestre() {
		return semestre;
	}
	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
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
	public Boolean getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Boolean periodo) {
		this.periodo = periodo;
	}
	public String getNomeDisc() {
		return nomDisc;
	}
	public void setNomeDisc(String nomDisc) {
		this.nomDisc = nomDisc;
	}
	public String getIdFila() {
		return idFila;
	}
	public void setIdFila(String idFila) {
		this.idFila = idFila;
	}
	public String getNomCurso() {
		return nomCurso;
	}
	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}
	
	
	
	

}
