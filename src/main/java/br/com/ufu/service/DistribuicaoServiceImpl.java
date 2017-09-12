package br.com.ufu.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.Path;

import br.com.ufu.model.FilaTurmaNew;
import br.com.ufu.model.Ministra;
import br.com.ufu.model.Professor;
import br.com.ufu.model.Turma;
import br.com.ufu.repository.DisciplinaRepositoryImpl;
import br.com.ufu.repository.MinistraRepositoryImpl;
import br.com.ufu.repository.ProfessorRepositoryImpl;
import br.com.ufu.repository.TurmaRepositoryImpl;

//Classe privada para Ordenar lista de FilaTurmaNew por Prioridade
@Path("/")
class OrdenaPorPrioridade implements Comparator<FilaTurmaNew> {
	@Override
	public int compare(FilaTurmaNew o1, FilaTurmaNew o2) {
		return o1.getPrioridade() - o2.getPrioridade();
	}
}

// Classe privada para Ordenar lista de FilaTurmaNew por Posição
@Path("/")
class OrdenaPorPosicao implements Comparator<FilaTurmaNew> {
	@Override
	public int compare(FilaTurmaNew o1, FilaTurmaNew o2) {
		return o1.getFila().getPos() - o2.getFila().getPos();
	}
}

// Classe que representa um Deadlock
@Path("/")
class Deadlock {
	public Professor professor;
	public Turma turma;
	public Deadlock dependente;
	public Deadlock lastDependente = this;

	public void addDependente(Deadlock d) {
		lastDependente.dependente = d;
		lastDependente = d;
	}

	public int tamanho() {
		int i = 1;
		Deadlock d = this;
		while (d.dependente != null) {
			d = d.dependente;
			i++;
		}
		return i;
	}
}

@Path("/")
public class DistribuicaoServiceImpl implements Runnable, Serializable {

	private static final long serialVersionUID = 6121707188970423781L;

	public ArrayList<Professor> professores; // Lista de Professores
	public ArrayList<Turma> turmas; // Lista de Turmas
	public int numLoop = 0;
	private transient List<FilaTurmaNew> filasTurma; // Filas (Bootstrap)
	private transient DisciplinaRepositoryImpl discRep = new DisciplinaRepositoryImpl();
	private transient ProfessorRepositoryImpl profRep = new ProfessorRepositoryImpl();
	private transient TurmaRepositoryImpl turmaRep = new TurmaRepositoryImpl();
	private transient boolean insoluvel = false;

	public transient static Map<Integer, List<Ministra>> cenarios = new HashMap<Integer, List<Ministra>>();
	public transient int cenario;

	public DistribuicaoServiceImpl() {
	}

	// Construtor
	public DistribuicaoServiceImpl(List<FilaTurmaNew> filasTurma) {
		this.filasTurma = filasTurma;
		this.professores = new ArrayList<Professor>();
		this.turmas = new ArrayList<Turma>();
		this.cenario = 1;
		cenarios.put(this.cenario, new ArrayList<Ministra>());
		this.encadeamento();

		// Verificação
		System.out.println("----------- ENCADEAMENTO -----------");
		imprimeListas();
		System.out.println("------------------------------------\n");
	}

	public void imprimeListas() {
		synchronized (professores) {
			synchronized (turmas) {
				for (Professor p : professores) {
					System.out.print(p.getSiape() + "      -> ");
					for (Turma t : p.turmas) {
						System.out.print(t.getId() + "\t");
					}
					System.out.println();
				}
				
				System.out.println();
		
				for (Turma t : turmas) {
					System.out.print(t.getId() + " -> ");
					for (Professor p : t.professores) {
						System.out.print(p.getSiape() + "\t");
					}
					System.out.println();
				}
			}
		}
	}

	
	@Override
	public void run() {
				
		while ((!turmas.isEmpty() || !professores.isEmpty()) && !insoluvel) { //Enquanto houverem turmas e professores

			boolean deadlock = true;	//Começa o loop supondo que existe um deadlock
			
			
			// ***** DISTRIBUI OS CASOS TRIVIAIS *****
			for (Professor p : professores) {
				
				int indTurma = 0;  //Índice da turma a ser distribuída na iteração atual (começa com a primeira - maior preferencia)
				int cont = p.cargaDistribuida;  //Variável usada para verificar se o professor p tem CH suficiente para ministrar a turma indTurma
				long limiteCarga = p.getCargaAtual() + 2; // Define a carga máxima como sendo carga atual + 2

				//Enquanto existem turmas na fila do professor p && se o professor ministrar Turma indTurma sua CH não ultrapassará o limite
				while (indTurma < p.turmas.size() && cont + p.turmas.get(indTurma).getCh() < limiteCarga) {

					Turma t = p.turmas.get(indTurma); // Pega a turma a ser verificada

					if (t.professores.get(0) == p) { // Se o professor p for o professor com prioridade sobre a turma t

						// Professor p ministra Turma indTurma
						cenarios.get(cenario).add(new Ministra() {
							private static final long serialVersionUID = 8847049365326071782L;
							{
								setSiape(p.getSiape());
								setTurmaId(t.getId());
							}
						});
						
						System.out.println(p.getSiape() + " ministra " + t.getId());

						//Atualiza a carga distribuída do professor
						p.cargaDistribuida += t.getCh();
						
						//Remove a turma das listas
						removeTurma(t);
						
						deadlock = false; // Nessa iteração, não foram identificados deadlocks
						indTurma--; // Como foi feita a distribuição, o i continuará com o mesmo valor

					}

					indTurma++; //Próxima turma preferencial do professor p
					cont = cont + t.getCh();  //Atualiza a varíavel que verifica se o professor p tem CH para ministrar a turma indTurma
				}
			}
			
			//Remove os professores que já receberam todas as turmas que sua CH permite
			System.out.println("\nProfessores com carga horária completa:");
			removeProfessoresCHCompleta();

			numLoop++;
			System.out.println("\n\nCenário " + cenario + "; Loop " + numLoop);
			imprimeListas();
			// \***** DISTRIBUI OS CASOS TRIVIAIS *****
			
			
			
			
			
			

			if (deadlock) { // Se não houverem mais casos triviais, quebra um deadlock

				// ****** IDENTIFICA DEADLOCK ******
				List<Deadlock> deadlocks = new ArrayList<Deadlock>();

				// Percorre a lista de professores
				for (Professor p : professores) {
					
					if (p.turmas.size() > 0) {
						HashMap<String, Professor> mapa = new HashMap<String, Professor>();
						Turma t = p.turmas.get(0); // Pega a turma preferencial de p
						Deadlock head = new Deadlock(); // Cria um novo deadlock
						head.professor = p; // Seta o professor que é a cabeça do deadlock
						head.turma = t; // Seta a turma que está travando
						deadlocks.add(head); // Adiciona à lista de deadlocks
						
						mapa.put(p.getSiape(), p);
						Professor p2 = t.professores.get(0); // Pega o professor que tem prioridade sobre a turma que está travando
						while (!mapa.containsKey(p2.getSiape())) { //p2 != p) { // Enquanto não encontra a cadeia de dealock completo
							// Seta os dependentes (Professores e turmas que estão travando)
							mapa.put(p2.getSiape(), p2);
							t = p2.turmas.get(0);
							Deadlock d = new Deadlock();
							d.professor = p2;
							d.turma = t;
							head.addDependente(d);
							p2 = t.professores.get(0);
						}
					}

				}
				
				// \****** IDENTIFICA DEADLOCK ******
				
				
				quebraDeadlock(deadlocks);

			}

		}
		
		

	}
	
	
	public void quebraDeadlock(List<Deadlock> deadlocks) {
		
		Thread tr = null;
		boolean check = false;
		for (Deadlock head : deadlocks) {
			// Se o deadlock for simples, já quebra e sai
			if (head.tamanho() == 2) { // break;
				check = true;
				try {

					// Duplica a lista do cenário atual						
					List<Ministra> novoCenario = (List<Ministra>) duplicaObjeto(cenarios.get(cenario));
					
					// Duplica o objeto atual (this)
					DistribuicaoServiceImpl novoDistribuicao = (DistribuicaoServiceImpl) duplicaObjeto(this);

					// Adiciona o novo cenário, ao objeto duplicado
					cenarios.put(cenario + 1, novoCenario);
					novoDistribuicao.cenario = cenario + 1;

					//Faz as atribuições aos dois cenários
					//Cenário Corrente
					cenarios.get(cenario).add(new Ministra() {
						private static final long serialVersionUID = -8369384549445727798L;
						{
							setSiape(head.professor.getSiape());
							setTurmaId(head.turma.getId());
						}
					});
					head.professor.cargaDistribuida+=head.turma.getCh();
					
					
					//Novo Cenário
					novoCenario.add(new Ministra() {
						private static final long serialVersionUID = -1942351447357649011L;
						{
							setSiape(head.dependente.professor.getSiape());
							setTurmaId(head.dependente.turma.getId());
						}
					});
					//Pega o professor, dentro da instância de novoDistribuição, para atualizar a CH do professor
					Professor pNovoCenario = novoDistribuicao.professores.stream()
							.filter(x -> x.getSiape().equals(head.dependente.professor.getSiape()))
							.collect(Collectors.toList()).get(0);
					pNovoCenario.cargaDistribuida+=head.dependente.turma.getCh();

					// Faz a remoção das turmas distribuídas
					removeTurma(head.turma);
					novoDistribuicao.removeTurma(head.dependente.turma);
					removeProfessoresCHCompleta();
					novoDistribuicao.removeProfessoresCHCompleta();
					
					System.out.println("\n\nThread corrente. Cenário " + cenario);
					imprimeListas();
					
					System.out.println("\n\nNova Thread. Cenário " + novoDistribuicao.cenario);
					novoDistribuicao.imprimeListas();
					
					// Inicializa a nova Thread
					tr = new Thread(novoDistribuicao);
					tr.start();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;
			}
		}
		
		if (!check) {
			System.out.println("Não foram encontrados deadlocks de tamanho 2! (Cenário " + cenario + ")");
			insoluvel = true;
		}
	}
	
	public void removeProfessoresCHCompleta() {
		Professor p = null;
		for (int i=0;;i++) {
			if (professores.size() <= i) break;
			p = professores.get(i);
			if (p.getCargaAtual() <= p.cargaDistribuida) {
				System.out.println(p.getSiape());
				removeProfessor(p);
				i--;
			}
		}
	}

	public void removeTurma(Turma t) {
		for (Professor p : professores) {
			p.turmas.remove(t);
		}
		turmas.remove(t);
	}

	public void removeProfessor(Professor p) {
		for (Turma t : turmas) {
			t.professores.remove(p);
		}
		professores.remove(p);
	}
	
	private Object duplicaObjeto(Object obj) throws Exception {
		try {
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			synchronized(obj) {
				oos.writeObject(obj); //Pega o cenário da Thread corrente
			}
			oos.flush();
			oos.close();
			bos.close();
			byte[] byteData = bos.toByteArray();
	
			ByteArrayInputStream bais = new ByteArrayInputStream(byteData);		
			return new ObjectInputStream(bais).readObject();
			
		} catch (Exception e) {
			throw e;
		}
	}

	private void encadeamento() {
		
		HashMap<String, Professor> profs = new HashMap<String, Professor>();
		HashMap<Integer, Turma> turms = new HashMap<Integer, Turma>();
		
		filasTurma.forEach(ft -> {
			
			if (!profs.containsKey(ft.getFila().getSiape()))
				profs.put(ft.getFila().getSiape(), profRep.buscaPorSiape(ft.getFila().getSiape()));
			
			if (!turms.containsKey(ft.getFilaTurmaPK().getIdTurma()))
				turms.put(ft.getFilaTurmaPK().getIdTurma(), ft.getTurma());
			
		});
		
		// Ordena por posição
		filasTurma.sort(new OrdenaPorPosicao());
		
		filasTurma.forEach(ft -> {
			
			Turma t = turms.get(ft.getFilaTurmaPK().getIdTurma());
			
			if (!turmas.contains(t)) turmas.add(t);  //Se não der, trocar pela expressão
			t.professores.add(profs.get(ft.getFila().getSiape()));
			
		});
		
		//Ordeno por prioridade
		filasTurma.sort(new OrdenaPorPrioridade());
		
		filasTurma.forEach(ft -> {
			
			Professor p = profs.get(ft.getFila().getSiape());
			
			if (!professores.contains(p)) professores.add(p);
			p.turmas.add(turms.get(ft.getFilaTurmaPK().getIdTurma()));
			
		});
		
	}

}
