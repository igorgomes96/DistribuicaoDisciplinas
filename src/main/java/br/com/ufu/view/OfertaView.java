package br.com.ufu.view;

import java.util.List;

/*
 * NOVA CLASSE - NAO APAGAR
 */

public class OfertaView {
    
	private Long turmaId;
	
	private List<String> horarios;

	public Long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Long turmaId) {
		this.turmaId = turmaId;
	}

	public List<String> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<String> horarios) {
		this.horarios = horarios;
	}

}
