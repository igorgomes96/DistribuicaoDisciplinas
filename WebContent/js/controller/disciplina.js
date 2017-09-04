angular.module('disciplinaApp', ['ui.bootstrap', 'ui.sortable', 'AxelSoft', 'ngRoute'])
	.config(function ($routeProvider, $locationProvider) {
	    //configure the routing rules here
	    $routeProvider.when('/main/:siape', {
	        controller: 'DisciplinaController'
	    });
	
	    //routing DOESN'T work without html5Mode
	    $locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
			});
	})
	.controller('DisciplinaController', function($scope, $http, $location, $routeParams, $anchorScroll, $timeout) {
		
		//var url = '/CDD/';
		var url = '/CDD/';
		var urlQuebrada = $location.absUrl().split('/');
		var login = urlQuebrada[0]+'//'+urlQuebrada[1]+'/'+urlQuebrada[2]+'/'+urlQuebrada[3]+'/'+urlQuebrada[4]+'/';
		console.log(login);
		
		$http.get(url + "rest/login/validaUsuarioAdministrador").success(function(administrador) {
			console.log(administrador);

			if (!administrador) {
				window.location = login;
				return;
			}
			
			
			$scope.disciplinas = [];
			$http.get(url + 'rest/semestre/listar').success(function(response) {
				for(var i = 0; i < response.length; i++) {
					if (response[i].status == true) {
						response[i].estado = '(Ativo)'
					} else {
						response[i].estado = '';
					}
				}
				
				$scope.semestres = response; 
				
				for(var i = 0; i < $scope.semestres.length; i++) {
					if ($scope.semestres[i].status == true) {
						$scope.semestre = '{"ano":'+$scope.semestres[i].ano+',"semestre":'+$scope.semestres[i].semestre+',"status":'+$scope.semestres[i].status+',"estado":"(Ativo)"}';
						
						$scope.semestreSelecionado = $scope.semestres[i];
					}
				}
				
				/*
				 * Atualiza a lista de turmas para exibição se suas filas de professores
				 */
				$http.get(url + "rest/disciplina/listaDisciplinaTurma/"+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { console.log(response); $scope.disciplinas = response; });
			
			});
			
			$scope.horariosDisciplina = [];
			
			$scope.savarHorarios = function(disciplina) {
				
				var d = angular.fromJson(disciplina);
				var turmaId = d.turmaId;
				
				console.log(turmaId);
				
				$scope.oferta = {
					'turmaId': turmaId,
					'horarios': $scope.horariosDisciplina
				}
				
				var res = $http.post(url + 'rest/oferta/alterarHorarioTurma', $scope.oferta).success(function(response) {
					console.log(response); 
					
					if (response != 'savo') {
						alert(response);
					}
					
					$scope.limparHorarios();
					$http.get(url + "rest/disciplina/listaDisciplinaTurma/"+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { console.log(response); $scope.disciplinas = response; });
				});
				
			};
			
			$scope.limparHorarios = function() {
				
				for (var i=0; i<$scope.calendario.length; i++) {
					//domingo
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].domingo);
					horarioTabela[0].bgColor = "#FFFFFF";
					//segunda
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].segunda);
					horarioTabela[0].bgColor = "#FFFFFF";
					//terca
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].terca);
					horarioTabela[0].bgColor = "#FFFFFF";
					//quarta
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].quarta);
					horarioTabela[0].bgColor = "#FFFFFF";
					//quinta
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].quinta);
					horarioTabela[0].bgColor = "#FFFFFF";
					//sexta
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].sexta);
					horarioTabela[0].bgColor = "#FFFFFF";
					//sabado
					var horarioTabela = document.getElementsByClassName($scope.calendario[i].sabado);
					horarioTabela[0].bgColor = "#FFFFFF";
				}
				
				$scope.horariosDisciplina = [];
				
			};
			
			$scope.exibirHorarios = function(disciplina) {
				
				$scope.limparHorarios();
				
				var d = angular.fromJson(disciplina);
				var horarios = d.horarios;
					
				for(var i = 0; i < horarios.length; i++) {
					var horarioTabela = document.getElementsByClassName(horarios[i].dia+"-"+horarios[i].horario);
					horarioTabela[0].bgColor = "#90CAF9";
					
					$scope.horariosDisciplina.push(horarios[i].dia+"-"+horarios[i].horario);
				}
				
			}
			
			$scope.selecionaHorario = function(horario) {
				/*
				 * Sem horarios: #FFFFFF
				 * Apenas uma disciplina: #90CAF9
				 */
				var horarioTabela = document.getElementsByClassName(horario);
				if (horarioTabela[0].bgColor == "#FFFFFF" || horarioTabela[0].bgColor == "") {
					
					horarioTabela[0].bgColor = "#90CAF9";
					
					// Adiciona horario ao array de horarios
					$scope.horariosDisciplina.push(horario);
					
				} else {
					
					horarioTabela[0].bgColor = "#FFFFFF";
					
					// Remove horario do array de horarios
					$scope.horariosDisciplina.splice($scope.horariosDisciplina.indexOf(horario), 1);
					
				}
				
				//console.log($scope.horariosDisciplina);
			};
			
			$scope.calendario = [
			    {
			    	horario: '07:10',
			    	domingo: '1-a',
			    	segunda: '2-a',
			    	terca: '3-a',
			    	quarta: '4-a',
			    	quinta: '5-a',
			    	sexta: '6-a',
			    	sabado: '7-a'
			    },
			    {
			    	horario: '08:00',
			    	domingo: '1-b',
			    	segunda: '2-b',
			    	terca: '3-b',
			    	quarta: '4-b',
			    	quinta: '5-b',
			    	sexta: '6-b',
			    	sabado: '7-b'
			    },
			    {
			    	horario: '08:50',
			    	domingo: '1-c',
			    	segunda: '2-c',
			    	terca: '3-c',
			    	quarta: '4-c',
			    	quinta: '5-c',
			    	sexta: '6-c',
			    	sabado: '7-c'
			    },
			    {
			    	horario: '09:50',
			    	domingo: '1-d',
			    	segunda: '2-d',
			    	terca: '3-d',
			    	quarta: '4-d',
			    	quinta: '5-d',
			    	sexta: '6-d',
			    	sabado: '7-d'
			    },
			    {
			    	horario: '10:40',
			    	domingo: '1-e',
			    	segunda: '2-e',
			    	terca: '3-e',
			    	quarta: '4-e',
			    	quinta: '5-e',
			    	sexta: '6-e',
			    	sabado: '7-e'
			    },
			    {
			    	horario: '11:30',
			    	domingo: '1-q',
			    	segunda: '2-q',
			    	terca: '3-q',
			    	quarta: '4-q',
			    	quinta: '5-q',
			    	sexta: '6-q',
			    	sabado: '7-q'
			    },
			    {
			    	horario: '13:10',
			    	domingo: '1-f',
			    	segunda: '2-f',
			    	terca: '3-f',
			    	quarta: '4-f',
			    	quinta: '5-f',
			    	sexta: '6-f',
			    	sabado: '7-f'
			    },
			    {
			    	horario: '14:00',
			    	domingo: '1-g',
			    	segunda: '2-g',
			    	terca: '3-g',
			    	quarta: '4-g',
			    	quinta: '5-g',
			    	sexta: '6-g',
			    	sabado: '7-g'
			    },
			    {
			    	horario: '14:50',
			    	domingo: '1-h',
			    	segunda: '2-h',
			    	terca: '3-h',
			    	quarta: '4-h',
			    	quinta: '5-h',
			    	sexta: '6-h',
			    	sabado: '7-h'
			    },
			    {
			    	horario: '16:00',
			    	domingo: '1-i',
			    	segunda: '2-i',
			    	terca: '3-i',
			    	quarta: '4-i',
			    	quinta: '5-i',
			    	sexta: '6-i',
			    	sabado: '7-i'
			    },
			    {
			    	horario: '16:50',
			    	domingo: '1-j',
			    	segunda: '2-j',
			    	terca: '3-j',
			    	quarta: '4-j',
			    	quinta: '5-j',
			    	sexta: '6-j',
			    	sabado: '7-j'
			    },
			    {
			    	horario: '17:40',
			    	domingo: '1-k',
			    	segunda: '2-k',
			    	terca: '3-k',
			    	quarta: '4-k',
			    	quinta: '5-k',
			    	sexta: '6-k',
			    	sabado: '7-k'
			    },
			    {
			    	horario: '18:10',
			    	domingo: '1-l',
			    	segunda: '2-l',
			    	terca: '3-l',
			    	quarta: '4-l',
			    	quinta: '5-l',
			    	sexta: '6-l',
			    	sabado: '7-l'
			    },
			    {
			    	horario: '19:00',
			    	domingo: '1-m',
			    	segunda: '2-m',
			    	terca: '3-m',
			    	quarta: '4-m',
			    	quinta: '5-m',
			    	sexta: '6-m',
			    	sabado: '7-m'
			    },
			    {
			    	horario: '19:50',
			    	domingo: '1-n',
			    	segunda: '2-n',
			    	terca: '3-n',
			    	quarta: '4-n',
			    	quinta: '5-n',
			    	sexta: '6-n',
			    	sabado: '7-n'
			    },
			    {
			    	horario: '20:50',
			    	domingo: '1-o',
			    	segunda: '2-o',
			    	terca: '3-o',
			    	quarta: '4-o',
			    	quinta: '5-o',
			    	sexta: '6-o',
			    	sabado: '7-o'
			    },
			    {
			    	horario: '21:40',
			    	domingo: '1-p',
			    	segunda: '2-p',
			    	terca: '3-p',
			    	quarta: '4-p',
			    	quinta: '5-p',
			    	sexta: '6-p',
			    	sabado: '7-p'
			    }
			];
		
		});
    
  });

