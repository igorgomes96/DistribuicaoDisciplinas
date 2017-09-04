package br.com.ufu.view;

public class FilaSemestreDTO {
    
	private Integer ano;
	private Short semestre;
	private Integer anoOld;
	private Short semestreOld;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Short getSemestre() {
		return semestre;
	}
	public void setSemestre(Short semestre) {
		this.semestre = semestre;
	}
	public Integer getAnoOld() {
		return anoOld;
	}
	public void setAnoOld(Integer anoOld) {
		this.anoOld = anoOld;
	}
	public Short getSemestreOld() {
		return semestreOld;
	}
	public void setSemestreOld(Short semestreOld) {
		this.semestreOld = semestreOld;
	}
	

}
