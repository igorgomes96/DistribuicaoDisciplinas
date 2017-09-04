package br.com.ufu.view;

import java.util.Comparator;

public class CustomComparator implements Comparator<RelatorioFilaDTO> {
	
    @Override
    public int compare(RelatorioFilaDTO o1, RelatorioFilaDTO o2) {
		int i = o1.getDisciplinaNome().compareTo(o2.getDisciplinaNome());
		if(i != 0) return i;
		 
		return o1.getPrioridade().compareTo(o2.getPrioridade());
    }

}
