package br.com.ufu.view;

import java.util.Comparator;

public class ProfessorPrioridadeComparator implements Comparator<RelatorioFilaDTO> {
	
    @Override
    public int compare(RelatorioFilaDTO o1, RelatorioFilaDTO o2) {
		int i = o1.getNomeProfessor().compareTo(o2.getNomeProfessor());
		if(i != 0) return i;
		
		return o1.getPrioridade().compareTo(o2.getPrioridade());
    }

}
