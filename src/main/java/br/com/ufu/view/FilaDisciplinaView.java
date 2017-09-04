package br.com.ufu.view;

import java.util.List;

import br.com.ufu.model.Disciplina;

public class FilaDisciplinaView {
	
	private Disciplina disciplina;
	
	private List<ListaDisciplinaProfessorView> listaProfessores;

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<ListaDisciplinaProfessorView> getListaProfessores() {
		return listaProfessores;
	}

	public void setListaProfessores(List<ListaDisciplinaProfessorView> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	@Override
	public String toString() {
		return "FilaDisciplinaView [disciplina=" + disciplina + ", listaProfessores=" + listaProfessores + "]";
	}

}
