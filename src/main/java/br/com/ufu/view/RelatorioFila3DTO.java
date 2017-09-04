package br.com.ufu.view;

public class RelatorioFila3DTO {
    
	private String disciplinaCodigo;
	private String disciplinaNome;
	private String nomeProfessor;
	private String curso;
	private String quantidade;

	public String getDisciplinaCodigo() {
		return disciplinaCodigo;
	}

	public void setDisciplinaCodigo( String disciplinaCodigo ) {
		this.disciplinaCodigo = disciplinaCodigo;
	}

	public String getDisciplinaNome() {
		return disciplinaNome;
	}

	public void setDisciplinaNome( String disciplinaNome ) {
		this.disciplinaNome = disciplinaNome;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor( String nomeProfessor ) {
		this.nomeProfessor = nomeProfessor;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso( String curso ) {
		this.curso = curso;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade( String quantidade ) {
		this.quantidade = quantidade;
	}
}
