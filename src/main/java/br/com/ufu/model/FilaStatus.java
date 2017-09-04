package br.com.ufu.model;

import java.io.Serializable;

public class FilaStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer NAO_DISTRIBUIDA = -1;
	
	public static final Integer ESPERA = -2;
	
	public static final Integer CHOQUE_HORARIO = 3;
	
	public static final Integer CARGA_HORARIA_ATINGIDA = 5;
	
	public static final Integer AGUARDANDO_REMOSSAO = 13;
	
	/*
	Existe uma store procedure que se chama resetdb;
	
	fila.status com valor -1: nao distribuida;
	
	fila.pos com valor -1: Professor que ministra a disciplina;
	
	1. Mover para o final da fila os professores que possuem quantidade maxima de vezes ministradas;
		Tabela fila_turma, coluna qte_ministrada e coluna qte_maximo;
	
	2. Excluir professores afastados (professor.afastado = true);
	
	3. Buscar disciplinas nao distribuidas (status < 0) ordenando pela prioridade do professor e posição na fila;
		SELECT siape, codigo_disc, pos, prioridade, qte_ministrada, qte_maximo, status, turma, ch
		FROM fila_turma where status < 0 order by prioridade, pos asc;
		
	4. Verifica quem esta no topo da fila da disciplina corrente;
		Validações:
			Verificar se a disciplina com maior prioridade possui o professor no topo da lista, alterar status da fila_turma para -2 (ESPERA);
			Verificar se o horario da disciplina nao colide nenhum impedimento;
			Verificar se nao existe choque de horarios com outra disciplina, se houver, atualizar status da fila_turma para 3 (CHOQUE DE HORARIO);
			Ao atribuir uma disciplina verificar se o professor nao ultrapassou o carga horaria maxima. Se sim atualizar o status da fila_turma desse professor em todas as linhas restantes para 5 (CARGA HORARIA ATINGIDA);
	
	Regra de distribuição para a mesma disciplina com diferentes turmas (filas sao copias)
		Ao atribuir a disciplina para o professor X, mover esse mesmo professor para o final da fila das turmas pertencentes a essa mesma disciplina
	*/
	
	
}
