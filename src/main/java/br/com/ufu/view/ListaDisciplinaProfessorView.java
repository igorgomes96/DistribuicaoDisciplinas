package br.com.ufu.view;

public class ListaDisciplinaProfessorView {
	
	private String nome;
	
	private Integer pos;
	
	private Integer prioridade;
	
	private Integer quantidadeMinistrada;
	
	private Integer quantidadeMaxima;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getQuantidadeMinistrada() {
		return quantidadeMinistrada;
	}

	public void setQuantidadeMinistrada(Integer quantidadeMinistrada) {
		this.quantidadeMinistrada = quantidadeMinistrada;
	}

	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	@Override
	public String toString() {
		return "ListaDisciplinaProfessorView [nome=" + nome + ", pos=" + pos + ", prioridade=" + prioridade
				+ ", quantidadeMinistrada=" + quantidadeMinistrada + ", quantidadeMaxima=" + quantidadeMaxima + "]";
	}

}
