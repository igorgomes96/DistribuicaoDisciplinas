var app = angular.module('mainApp', ['ui.bootstrap', 'ui.sortable', 'AxelSoft', 'ngRoute'])
	.config(function ($routeProvider, $locationProvider) {
	    //configure the routing rules here
	    $routeProvider
	    .when('/main/:siape', {
	        controller: 'MainController'
	    });
	
	    //routing DOESN'T work without html5Mode
	    $locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
	})
	.controller('MainController', function($scope, $http, $location, $routeParams, $anchorScroll, $timeout) {
		
		//console.log($routeParams.siape);
		
		//console.log($location.path().split('/')[3]);
		
		//console.log($location.search());
		//411579
		var siape = $location.path().split('/')[3];
		$scope.siape = siape;
		
		//var url = '/CDD/';
		var url = '/CDD/';
		
		/*
		 * INFO
		 */
		var tmpList = [];
		$scope.disciplinas = [];
		$scope.semestres = [];
		$scope.semestre = {};
		$scope.filaDisciplinasProfessor = [];
		$scope.restricoes = [];
		$scope.etapas = [];
		
		$scope.etapaAtiva = function(codigo){
			for ( var e in $scope.etapas) {
				if(codigo==$scope.etapas[e].codigo){
					return true;
				}
			}
			return false;
		};
		
		$http.get(url + 'rest/etapa/listar').success(function(response) {
			if(response && response.length > 0){
				$scope.etapas = response.filter(function(e){
					return e.ativo;
				});
			}
		});
		
		
		$http.get(url + 'rest/restricao/listar').success(function(response) {
		  if(response && response.length > 0){
			  $scope.restricoes = response.filter(function(el) {
				 return el.restricao.siape.trim() == $scope.siape.trim(); 
			  });
		  }
		 });
		
		$http.get(url + 'rest/semestre/listar').success(function(response) {
			$scope.semestres = response; 
			for(var i = 0; i < response.length; i++) {
				if ($scope.semestres[i].status == true) {
					$scope.semestres[i].estado = '(Ativo)';
					//$scope.semestre = '{"id":'+$scope.semestres[i].id+',"ano":'+$scope.semestres[i].ano+',"semestre":'+$scope.semestres[i].semestre+',"status":'+$scope.semestres[i].status+',"estado":"(Ativo)"}';
					$scope.semestre = $scope.semestres[i];
					$scope.semestreSelecionado = $scope.semestres[i];
				} else {
					$scope.semestres[i].estado = '';
				}
			}
			
			//console.log("Semestre selecionado = ", $scope.semestreSelecionado);
			//console.log("Semestre", $scope.semestre);
			//console.log("$scope.semestres", $scope.semestres);
			
			/*
			 * Busca a lista de disciplinas por prioridade do professor para o semestre ativo (PADRAO)
			 */
			$http.get(url + 'rest/fila/listaFilaPorSiape/'+$scope.siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
				//console.log(response);
				$scope.filaDisciplinasProfessor = response;
			});
			
			
			/*
			 * Busca a lista de turmas por prioridade para o semestre ativo
			 */
			$http.get(url + "rest/disciplina/listaDisciplinaTurmaProfessor/"+$scope.siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
				tmpList =  $scope.list = response; 
				//console.log("$scope.list", $scope.list);
			});
		
			/*
			 * Atualiza a lista de turmas para exibição se suas filas de professores
			 */
			$http.get(url + "rest/disciplina/listaDisciplinaTurma/"+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { 
				//console.log(response); 
				$scope.disciplinas = response; 
			});
			
		});
		
		/*
		 * Função que sincroniza a pagina com o semestre selecionado
		 */
		$scope.alterarSemestre = function(semestre) {
			//console.log(semestre);
			//var s = angular.fromJson(semestre);
			//console.log(s);
			var s = semestre;
			/*
			 * Atualiza o semestre selecionado na tela
			 */
			$scope.semestreSelecionado = s;
			//console.log("semestre selecionado, select", $scope.semestreSelecionado);
			
			/*
			 * Atualiza a lista de disciplinas por prioridade do professor para o semestre selecionado
			 */
			$http.get(url + 'rest/fila/listaFilaPorSiape/'+$scope.siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
				//console.log(response);
				$scope.filaDisciplinasProfessor = response;
			});
			
			/*
			 * Limpa a fila por disciplina
			 */
			$scope.filaProfessores = null;
			
			/*
			 * Limpa a fila por professor
			 */
			$scope.filaProfessor = [];
			
			
			/*
			 * Atualiza a lista de turmas por prioridade do professor para o semestre selecionado
			 */
			$http.get(url + "rest/disciplina/listaDisciplinaTurmaProfessor/"+$scope.siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
				tmpList =  $scope.list = response;
				//console.log("$scope.list", $scope.list);
			});
		
			/*
			 * Atualiza a lista de turmas para exibição se suas filas de professores
			 */
			$scope.professoresDisciplina = null;
			$http.get(url + "rest/disciplina/listaDisciplinaTurma/"+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { 
				//console.log(response); 
				$scope.disciplinas = response; 
			});
			
			/*
			 *  Limpa a fila de turmas por professor
			 */
			$scope.disciplinasProfessor = [];
		}
		
		$scope.professorInfo = {};
		$http.get(url + 'rest/professor/buscaPorSiape/'+$scope.siape).success(function(response) { $scope.professorInfo = response; });
		
		$scope.semestreInfo = {};
		$http.get(url + 'rest/semestre/buscarUltimoSemestre').success(function(response) { $scope.semestreInfo = response; });
		
		$scope.esconderTodasMsgs = function() {
			$scope.showMsgSucesso = false;
			$scope.showMsgErro = false;
			$scope.msgAddDisciplina = '';
			$scope.showRemoveSucesso = false;
			$scope.showRemoveErro = false;
			$scope.msgRemoveDisciplina = '';
			$scope.showPrioridadeSucesso = false;
			$scope.showPrioridadeErro = false;
			$scope.msgAlteraPrioridade = '';
		};
		
		
		/*
		 * Aba 1
		 */
		$scope.disciplinaTurmaHorario = [];
		$http.get(url + 'rest/disciplina/listaDisciplinaTurmaHorario').success(function(response) { 
			//console.log(response); 
			$scope.disciplinaTurmaHorario = response; 
		});
		
		$scope.turmasInfo = [];
		$scope.disciplinaInfo = null;
		$scope.exibeInfoDisciplina = function(disciplina) {
			$scope.turmasInfo = [];
			$scope.disciplinaInfo = {};
			
			if (disciplina == 'null') {
				$scope.disciplinaInfo = null;
				return;
			}
			
			var d = angular.fromJson(disciplina);
			$scope.disciplinaInfo.nome = d.codigoDisciplina + ' - ' + d.nomeDisciplina + ' - ' + d.codigoCurso;
			$scope.disciplinaInfo.cargaHoraria = d.cargaHorariaTotal;
			$scope.disciplinaInfo.curso = d.nomeCurso;
			$scope.turmasInfo = d.turmas;
		};
		
		/*$scope.filaDisciplinasProfessor = [];
		$http.get(url + 'rest/fila/listaFilaPorSiape/'+siape).success(function(response) {
			console.log(response);
			$scope.filaDisciplinasProfessor = response;
		});*/
		
		$scope.showMsgSucesso = false;
		$scope.showMsgErro = false;
		$scope.msgAddDisciplina = '';
		
		$scope.fecharMsgAdicionar = function (){
			$scope.showMsgSucesso = false;
			$scope.showMsgErro = false;
			$scope.msgAddDisciplina = '';
		};
		
		$scope.entrarFilaDisciplina = function(disciplina) {
			
			//console.log(disciplina);
			if (disciplina === undefined || disciplina == 'null') {
				alert("Selecione uma disciplina!");
				return;
			}
			
			var d = angular.fromJson(disciplina);
			
			for(var i = 0; i < $scope.filaDisciplinasProfessor.length; i++) {
			    if ($scope.filaDisciplinasProfessor[i].codigoDisciplina == d.codigoDisciplina) {
			        alert('Essa disciplina já esta presente na lista!');
			        return;
			    }
			}
			
			//console.log(d);
			var filaView = {};
			filaView.siape = $scope.siape;
			filaView.codigoDisciplina = d.codigoDisciplina;
			
			var res = $http.post(url + 'rest/fila/entrarFilaDisciplina', filaView);
			
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				if (data > 0) {
					//alert('Disciplina adicionada com sucesso');
					
					// ADICIONA A DISCIPLINA DA LISTA NA TELA
					$scope.filaDisciplinasProfessor.push(d);
					
					$scope.msgAddDisciplina = 'Disciplina adicionada com sucesso!';
					$scope.showMsgSucesso = true;
					$scope.showMsgErro = false;
					
					
				} else {
					//alert('Ocorreu um problema ao adicionar a disciplina');
					
					$scope.msgAddDisciplina = 'Ocorreu um problema ao adicionar a disciplina!';
					$scope.showMsgSucesso = false;
					$scope.showMsgErro = true;
				}
				
				$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
			res.error(function(data, status, headers, config) {
				//alert( "failure message: " + JSON.stringify({data: data}));
				//console.log("failure message: " + JSON.stringify({data: data}));
				$scope.msgAddDisciplina = 'Ocorreu um problema ao adicionar a disciplina!';
				$scope.showMsgSucesso = false;
				$scope.showMsgErro = true;
				
				$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
		}
		
		$scope.showRemoveSucesso = false;
		$scope.showRemoveErro = false;
		$scope.msgRemoveDisciplina = '';
		
		$scope.fecharMsgRemover = function() {
			$scope.showRemoveSucesso = false;
			$scope.showRemoveErro = false;
			$scope.msgRemoveDisciplina = '';
		};
		
		$scope.atualizaStatusParaRemossao = function(item) {
			
			var filaView = {};
			filaView.siape = $scope.siape;
			filaView.codigoDisciplina = item.codigoDisciplina;

			var res = $http.post(url + 'rest/fila/atualizaStatusParaRemossao', filaView);
			
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				//alert(data);
				if (data == 1) {
					//alert('O status da disciplina foi alterado para aguardando remossão. Ela será removida no final da etapa de entrar / sair de filas.');
					
					// REMOVE A DISCIPLINA DA LISTA NA TELA
					var index = $scope.filaDisciplinasProfessor.indexOf(item);
					$scope.filaDisciplinasProfessor.splice(index, 1);
					
					$scope.msgRemoveDisciplina = 'O status da disciplina foi alterado para aguardando remoção. Ela será removida no final da etapa de entrar / sair de filas.';
					$scope.showRemoveSucesso = true;
					$scope.showRemoveErro = false;
					
				} else {
					//alert('Ocorreu um problema ao remover a disciplina');
					
					$scope.msgRemoveDisciplina = 'Ocorreu um problema ao remover a disciplina.';
					$scope.showRemoveSucesso = false;
					$scope.showRemoveErro = true;
				}
				
				$location.hash('removeMsg');
			    $anchorScroll();
			    
			    $timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
			res.error(function(data, status, headers, config) {
				//alert( "failure message: " + JSON.stringify({data: data}));
				//console.log("failure message: " + JSON.stringify({data: data}));
				$scope.msgRemoveDisciplina = 'Ocorreu um problema ao remover a disciplina.';
				$scope.showRemoveSucesso = false;
				$scope.showRemoveErro = true;
				
				$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
		}
		
		/*
		 * Aba 2
		 */
		$scope.filaProfessores = null;
		$scope.disciplinaFilaInfo = {};
		$scope.buscaFilaPorCodDisciplina = function(disciplina) {
			//console.log(disciplina);
			$scope.disciplinaFilaInfo = angular.fromJson(disciplina);
			$http.get(url + "rest/fila/listaFilaPorCodigoDisciplina/"+$scope.disciplinaFilaInfo.codigoDisciplina+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { 
				//console.log(response); 
				$scope.filaProfessores = []; 
				$scope.filaProfessores = response; 
				//console.log($scope.filaProfessores); 
			});
		}
		
		
		/*
		 * Aba 3
		 */
		$scope.filaProfessor = [];
		$scope.listaFilaPorSiape = function(siape, professorSelecionado) {
			$http.get(url + "rest/fila/listaFilaPorSiape/"+siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { 
				$scope.professorFilaInfo = professorSelecionado;
				$scope.filaProfessor = [];
				$scope.filaProfessor = response;  
			});
		};
		
		
		
		
		
		
		
		//$scope.disciplinas = [];
		//$http.get(url + "rest/disciplina/listaDisciplinaTurma").success(function(response) { console.log(response); $scope.disciplinas = response; });
	  
		//var tmpList = [];
		//$http.get(url + "rest/disciplina/listaDisciplinaTurmaProfessor/"+siape).success(function(response) { console.log(response); tmpList =  $scope.list = response; });
		
		$scope.professores = [];
		$http.get(url + "rest/professor/listar").success(function(response) { $scope.professores = []; $scope.professores = response; });
		
		$scope.adicionarDisciplina = function(disciplina) {
			
			$scope.items = ['item1', 'item2', 'item3'];
			
			var modalInstance = $uibModal.open({
			      animation: true,
			      templateUrl: 'myModalContent.html',
			      controller: 'ModalInstanceCtrl',
			      size: '',
			      resolve: {
			        items: function () {
			          return $scope.items;
			        }
			      }
			    });

			    modalInstance.result.then(function (selectedItem) {
			      $scope.selected = selectedItem;
			    }, function () {
			      $log.info('Modal dismissed at: ' + new Date());
			    });
			
			//console.log(disciplina);
			if (disciplina === undefined) {
				alert("Selecione uma disciplina!");
				return;
			}
			
			var d = angular.fromJson(disciplina);
			
			for(var i = 0; i < $scope.list.length; i++) {
			    if ($scope.list[i].codigoDisciplina == d.codigoDisciplina && $scope.list[i].turma == d.turma) {
			        alert('Essa disciplina já esta presente na lista!');
			        return;
			    }
			}
			
			//console.log(d);
			var filaTurmaView = {};
			filaTurmaView.siape = $scope.siape;
			filaTurmaView.codigoDisciplina = d.codigoDisciplina;
			filaTurmaView.turma = d.turma;
			
			var res = $http.post(url + 'rest/filaDiscilplina/adicionarDisciplina', filaTurmaView);
			
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				alert(data);
			});
			
			res.error(function(data, status, headers, config) {
				alert( "failure message: " + JSON.stringify({data: data}));
			});
			
			$scope.list.push(d);
			
		}
		
		$scope.showPrioridadeSucesso = false;
		$scope.showPrioridadeErro = false;
		$scope.msgAlteraPrioridade = '';
		
		$scope.fecharMsgAlterarPrioridade = function(){
			$scope.showPrioridadeSucesso = false;
			$scope.showPrioridadeErro = false;
			$scope.msgAlteraPrioridade = '';
		}
		
		$scope.submeterFilaPrioridades = function() {
			//console.log($scope.list);

			var listaPrioridades = [];
			var numeroPrioridade = 1;
			angular.forEach($scope.list, function(value, key){
			     
			     var prioridade = {};

			     prioridade.siape = $scope.siape;
			     prioridade.codigoDisciplina = value.codigoDisciplina;
			     prioridade.idTurma = value.idTurma;
			     prioridade.prioridade = numeroPrioridade;
			     
			     numeroPrioridade++;
			     
			     listaPrioridades.push(prioridade);
			     
			});
			
			//console.log(listaPrioridades);
			
			var res = $http.post(url + 'rest/filaDiscilplina/atualizaPrioridades', listaPrioridades);
			
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				//alert(data);
				if (data == 1) {
					//alert('Prioridades atualizadas com sucesso');
					
					$scope.msgAlteraPrioridade = 'Prioridades atualizadas com sucesso';
					$scope.showPrioridadeSucesso = true;
					$scope.showPrioridadeErro = false;
					
				} else {
					//alert('Ocorreu um problema ao atualizar a lista de prioridades');
					
					$scope.msgAlteraPrioridade = 'Ocorreu um problema ao atualizar a lista de prioridades';
					$scope.showPrioridadeSucesso = false;
					$scope.showPrioridadeErro = true;
					
				}
				
				$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
			res.error(function(data, status, headers, config) {
				//alert( "failure message: " + JSON.stringify({data: data}));
				//console.log("failure message: " + JSON.stringify({data: data}));
				
				$scope.msgAlteraPrioridade = 'Ocorreu um problema ao atualizar a lista de prioridades';
				$scope.showPrioridadeSucesso = false;
				$scope.showPrioridadeErro = true;
				
				$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
			});
			
		}
		
		$scope.removeDisciplina = function(item) {
			
			var filaTurmaView = {};
			filaTurmaView.siape = $scope.siape;
			filaTurmaView.codigoDisciplina = item.codigoDisciplina;
			filaTurmaView.turma = item.turma;

			var res = $http.post(url + 'rest/filaDiscilplina/atualizaStatusParaRemossao', filaTurmaView);
			
			res.success(function(data, status, headers, config) {
				$scope.message = data;
				alert(data);
			});
			
			res.error(function(data, status, headers, config) {
				alert( "failure message: " + JSON.stringify({data: data}));
			});
			
			var index = $scope.list.indexOf(item);
			$scope.list.splice(index, 1);
			
		}
		
		$scope.professoresDisciplina = null;
		$scope.buscaProfessoresPorCodDisciplina = function(disciplina) {
			var d = angular.fromJson(disciplina);
			//console.log(d);
			$http.get(url + "rest/filaDiscilplina/buscaFilaProfessoresPorDisciplina/"+d.codigoDisciplina+'/'+d.turmaId+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { 
				//console.log(response); 
				$scope.professoresDisciplina = {}; 
				$scope.professoresDisciplina = response; 
			});
		}
		
		$scope.disciplinasProfessor = [];
		$scope.buscaDisciplinasPorSiape = function(siape, professorSelecionado) {
			$http.get(url + "rest/filaDiscilplina/buscaPorSiape/"+siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { $scope.disciplinasProfessor = []; 
			$scope.disciplinasProfessor = response; 
			$scope.professorFilaInfoNome = professorSelecionado;
			});
			
		}
		
		$scope.marcaHorario = function($event, disciplina) {
			/*
			 * Sem horarios: #FFFFFF
			 * Apenas uma disciplina: #90CAF9
			 * Mais de uma disciplina: #C62828
			 */
			
			var checkbox = $event.target;
			var d = angular.fromJson(disciplina);
			var horarios = d.horarios;
			
			if (checkbox.checked) {
				
				for(var i = 0; i < horarios.length; i++) {
					//console.log(horarios[i].dia+""+horarios[i].horario);
					var horarioTabela = document.getElementsByClassName(horarios[i].dia+""+horarios[i].horario);
					horarioTabela[0].textContent = horarioTabela[0].textContent.replace(d.codigoDisciplina.trim()+" - ","");
					horarioTabela[0].textContent = horarioTabela[0].textContent+d.codigoDisciplina.trim()+" - ";
					if (horarioTabela[0].bgColor == "#FFFFFF" || horarioTabela[0].bgColor == "") {
						horarioTabela[0].bgColor = "#90CAF9";
					} else {
						horarioTabela[0].bgColor = "#C62828";
					}
				}
				
			} else {
				
				for(var i = 0; i < horarios.length; i++) {
					//console.log(horarios[i].dia+""+horarios[i].horario);
					var horarioTabela = document.getElementsByClassName(horarios[i].dia+""+horarios[i].horario);
					horarioTabela[0].textContent = horarioTabela[0].textContent.replace(d.codigoDisciplina.trim()+" - ","");
					if (horarioTabela[0].bgColor == "#C62828") {
						horarioTabela[0].bgColor = "#90CAF9";
					} else {
						horarioTabela[0].bgColor = "#FFFFFF";
					}
				}
				
			}
		}
		
		$scope.calendario = [
		    {
		    	horario: '07:10',
		    	domingo: '1a',
		    	segunda: '2a',
		    	terca: '3a',
		    	quarta: '4a',
		    	quinta: '5a',
		    	sexta: '6a',
		    	sabado: '7a'
		    },
		    {
		    	horario: '08:00',
		    	domingo: '1b',
		    	segunda: '2b',
		    	terca: '3b',
		    	quarta: '4b',
		    	quinta: '5b',
		    	sexta: '6b',
		    	sabado: '7b'
		    },
		    {
		    	horario: '08:50',
		    	domingo: '1c',
		    	segunda: '2c',
		    	terca: '3c',
		    	quarta: '4c',
		    	quinta: '5c',
		    	sexta: '6c',
		    	sabado: '7c'
		    },
		    {
		    	horario: '09:50',
		    	domingo: '1d',
		    	segunda: '2d',
		    	terca: '3d',
		    	quarta: '4d',
		    	quinta: '5d',
		    	sexta: '6d',
		    	sabado: '7d'
		    },
		    {
		    	horario: '10:40',
		    	domingo: '1e',
		    	segunda: '2e',
		    	terca: '3e',
		    	quarta: '4e',
		    	quinta: '5e',
		    	sexta: '6e',
		    	sabado: '7e'
		    },
		    {
		    	horario: '11:30',
		    	domingo: '1q',
		    	segunda: '2q',
		    	terca: '3q',
		    	quarta: '4q',
		    	quinta: '5q',
		    	sexta: '6q',
		    	sabado: '7q'
		    },
		    {
		    	horario: '13:10',
		    	domingo: '1f',
		    	segunda: '2f',
		    	terca: '3f',
		    	quarta: '4f',
		    	quinta: '5f',
		    	sexta: '6f',
		    	sabado: '7f'
		    },
		    {
		    	horario: '14:00',
		    	domingo: '1g',
		    	segunda: '2g',
		    	terca: '3g',
		    	quarta: '4g',
		    	quinta: '5g',
		    	sexta: '6g',
		    	sabado: '7g'
		    },
		    {
		    	horario: '14:50',
		    	domingo: '1h',
		    	segunda: '2h',
		    	terca: '3h',
		    	quarta: '4h',
		    	quinta: '5h',
		    	sexta: '6h',
		    	sabado: '7h'
		    },
		    {
		    	horario: '16:00',
		    	domingo: '1i',
		    	segunda: '2i',
		    	terca: '3i',
		    	quarta: '4i',
		    	quinta: '5i',
		    	sexta: '6i',
		    	sabado: '7i'
		    },
		    {
		    	horario: '16:50',
		    	domingo: '1j',
		    	segunda: '2j',
		    	terca: '3j',
		    	quarta: '4j',
		    	quinta: '5j',
		    	sexta: '6j',
		    	sabado: '7j'
		    },
		    {
		    	horario: '17:40',
		    	domingo: '1k',
		    	segunda: '2k',
		    	terca: '3k',
		    	quarta: '4k',
		    	quinta: '5k',
		    	sexta: '6k',
		    	sabado: '7k'
		    },
		    {
		    	horario: '18:10',
		    	domingo: '1l',
		    	segunda: '2l',
		    	terca: '3l',
		    	quarta: '4l',
		    	quinta: '5l',
		    	sexta: '6l',
		    	sabado: '7l'
		    },
		    {
		    	horario: '19:00',
		    	domingo: '1m',
		    	segunda: '2m',
		    	terca: '3m',
		    	quarta: '4m',
		    	quinta: '5m',
		    	sexta: '6m',
		    	sabado: '7m'
		    },
		    {
		    	horario: '19:50',
		    	domingo: '1n',
		    	segunda: '2n',
		    	terca: '3n',
		    	quarta: '4n',
		    	quinta: '5n',
		    	sexta: '6n',
		    	sabado: '7n'
		    },
		    {
		    	horario: '20:50',
		    	domingo: '1o',
		    	segunda: '2o',
		    	terca: '3o',
		    	quarta: '4o',
		    	quinta: '5o',
		    	sexta: '6o',
		    	sabado: '7o'
		    },
		    {
		    	horario: '21:40',
		    	domingo: '1p',
		    	segunda: '2p',
		    	terca: '3p',
		    	quarta: '4p',
		    	quinta: '5p',
		    	sexta: '6p',
		    	sabado: '7p'
		    }
		];
	  
    var todoList = this;
    todoList.todos = [
      {text:'learn angular', done:true},
      {text:'build an angular app', done:false}];
 
    todoList.addTodo = function() {
      todoList.todos.push({text:todoList.todoText, done:false});
      todoList.todoText = '';
    };
 
    todoList.remaining = function() {
      var count = 0;
      angular.forEach(todoList.todos, function(todo) {
        count += todo.done ? 0 : 1;
      });
      return count;
    };
 
    todoList.archive = function() {
      var oldTodos = todoList.todos;
      todoList.todos = [];
      angular.forEach(oldTodos, function(todo) {
        if (!todo.done) todoList.todos.push(todo);
      });
    };
    
    
    $scope.oneAtATime = true;

    $scope.groups = [
      {
        title: 'Dynamic Group Header - 1',
        content: 'Dynamic Group Body - 1'
      },
      {
        title: 'Dynamic Group Header - 2',
        content: 'Dynamic Group Body - 2'
      }
    ];

    $scope.items = ['Item 1', 'Item 2', 'Item 3'];

    $scope.addItem = function() {
      var newItemNo = $scope.items.length + 1;
      $scope.items.push('Item ' + newItemNo);
    };

    $scope.status = {
      isFirstOpen: true,
      isFirstDisabled: false
    };
    
    
    /*
    var tmpList = [];
    for (var i = 1; i <= 6; i++){
      tmpList.push({
        text: 'Item ' + i,
        value: i
      });
    }*/
    
    //$scope.list = tmpList;
    
    
    $scope.sortingLog = [];
    
    $scope.sortableOptions = {
      update: function(e, ui) {
        var logEntry = tmpList.map(function(i){
          return i.prioridade;
        }).join(', ');
        $scope.sortingLog.push('Update: ' + logEntry);
      },
      stop: function(e, ui) {
        // this callback has the changed model
        var logEntry = tmpList.map(function(i){
          return i.prioridade;
        }).join(', ');
        $scope.sortingLog.push('Stop: ' + logEntry);
      }
    };
    
    
    $scope.fruits = ['apple', 'orange', 'mango', 'grapefruit', 'banana', 'melon'];
	$scope.setToMango = function () {
		$scope.fruit = 'mango';
	};
	
	$scope.todoList = [
	                  {text:'learn angular', done:true},
	                  {text:'build an angular app', done:false}];
    
  });
