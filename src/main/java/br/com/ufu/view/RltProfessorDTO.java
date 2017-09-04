package br.com.ufu.view;


public class RltProfessorDTO {
	
	private String NomeProfessor;
	private String codigo;
	private String turma;
	private String NomeDisc;
	private String NomeCurso;
	private Integer CargaHoraria;
	private String Qte;
	
	
	public String getNomeProfessor() {
		return NomeProfessor;
	}
	public void setNomeProfessor(String NomeProfessor) {
		this.NomeProfessor = NomeProfessor;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
	public String getNomeDisc() {
		return NomeDisc;
	}
	public void setNomeDisc(String NomeDisc) {
		this.NomeDisc = NomeDisc;
	}
	
	public String getNomeCurso() {
		return NomeCurso;
	}
	public void setNomeCurso(String NomeCurso) {
		this.NomeCurso = NomeCurso;
	}
	
	public Integer getCargaHoraria() {
		return CargaHoraria;
	}
	public void setCargaHoraria(Integer CargaHoraria) {
		this.CargaHoraria = CargaHoraria;
	}
	
	public String getQte() {
		return Qte;
	}
	public void setQte(String Qte) {
		this.Qte = Qte;
	}
	
}
