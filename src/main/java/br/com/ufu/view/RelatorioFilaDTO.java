package br.com.ufu.view;

public class RelatorioFilaDTO {
    
	private String nomeProfessor;
	private String disciplinaCodigo;
	private String disciplinaNome;
	private Integer posicaoFila;
	private String quantidade;
	private String prioridade;
	
	public String getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getDisciplinaCodigo() {
		return disciplinaCodigo;
	}
	public void setDisciplinaCodigo(String disciplinaCodigo) {
		this.disciplinaCodigo = disciplinaCodigo;
	}
	public String getDisciplinaNome() {
		return disciplinaNome;
	}
	public void setDisciplinaNome(String disciplinaNome) {
		this.disciplinaNome = disciplinaNome;
	}
	public Integer getPosicaoFila() {
		return posicaoFila;
	}
	public void setPosicaoFila(Integer posicaoFila) {
		this.posicaoFila = posicaoFila;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

}
