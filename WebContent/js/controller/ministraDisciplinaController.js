app.controller('MinistraDisciplinaController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal', function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
    //  'use strict';
    var url = '/CDD/rest/';
  
    $scope.editRegister = function(model){
        $scope.openEdit(model,$scope.buscaDisciplinasPorSiape);
    };    
  
  
//  
	$scope.disciplinasProfessor = [];
//	
//	$scope.buscaDisciplinasPorSiape = function(siape) {
//		$http.get(url + "turma/buscaTurmaPorSiape/"+siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) { $scope.disciplinasProfessor = []; $scope.disciplinasProfessor = response; });
//	}
	
	$scope.buscaDisciplinasPorSiape = function(professor) {

        $scope.disciplinas = [];
        $scope.prof = [];
        $scope.prof = professor;
        var siape = $scope.prof.professor.siape.trim();

		$http.get(url + "ministraDisciplina/buscaSiape/"+siape).success(function(response) { 
			$scope.disciplinasProfessor = response; 
        });

        $scope.nomeProfessor =  ": " + $scope.prof.professor.nome + " - " + $scope.prof.professor.siape;

        $scope.removeInputText();

	};

    $scope.removeInputText = function() {
        document.querySelector(".tags input").style.display = "none";
    };

    $scope.exibeInputText = function() {
        document.querySelector(".tags input").style.display = "inline-block";
    };
	
    $scope.openEdit = function(model, callback){
        $modal.open({
            size : 'lg',
            templateUrl: '../modal/ministraDisciplinaModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Editar turma professor";
                $scope.item = model;
                $scope.disableProfessor = true;
          		$http.get(url + 'semestre/listar').success(function(response) {
        			$scope.semestres = response; 
        			for(var i = 0; i < response.length; i++) {
        				if ($scope.semestres[i].status == true) {
        					$scope.semestres[i].estado = '(Ativo)'
        					$scope.item.semestre = $scope.semestres[i];
        					$scope.semestreSelecionado = $scope.semestres[i];
        				} else {
        					$scope.semestres[i].estado = '';
        				}
        			}
        			$http.get(url + 'turma/buscaTurmaDisc/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
        				$scope.turmas = response; 
        			});
                });
          		
          		$scope.alterarSemestre = function(semestre) {
        			var s = semestre;
        			$scope.semestreSelecionado = s;
          		}
          		
          		$scope.buscaTurma = function(semestre) {
          			$scope.turmas = [];
          			$http.get(url + 'turma/buscaTurmaDisc/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
              			$scope.turmas = response; 
              		});
          		}
          		
                $scope.sim = function (item) {
            	   var params = {
                        'turmaId' : item.turma.turma.id,
                        'siape' : item.siape,
                        'ano' : $scope.semestreSelecionado.ano,
                        'semestre' : $scope.semestreSelecionado.semestre  
                    }
                    $http.get(url + 'ministraDisciplina/validaTurma/'+item.turma.turma.id+'/'+item.siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre, params).success(function(response){
                        if(response > 0){
                            utils.error("Não é possivel atribuir essa disciplina, pois há conflito de horários.");
                            $scope.item.turma = null;
                        } else{
                            var parametros = {
                                'turmaId' : item.turma.turma.id,
                                'siape' : item.siape
                            }
                            $http.post(url + 'ministra/save', parametros).success(function(response) {
                                $scope.item.turma = null;
                                if(response && response.data){
                                    if(response.data.status == 300){
                                        utils.error("Não é possivel atribuir essa disciplina, pois há conflito de horários.");
                                    } else {
                                        $modalInstance.close();
                                        callback();
                                    }
                                } 
                                $modalInstance.close();
                            });
                        }  
                    })
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
    
    $scope.openRemove = function(model){
        utils.confirm("Deseja realmente remover este registro ?",function(){
            var params = {
                'turmaId' : model.turmaId,
                'siape' : model.siape
            }
            $http.post(url + 'ministraDisciplina/delete', params).success(function(response) {
                if(response && response.data){
                    if(response.data.status == 300){
                        utils.error(response.data.exception);
                    } else {
                        $scope.init();
                        $http.get(url + "ministraDisciplina/buscaSiape/"+ model.siape).success(function(response) { 
                            $scope.disciplinasProfessor = response; 
                        });
                    }
                } 
            });
        },null,"Excluir registro");  
    };
  
    $scope.init = function(){

        $http.get(url + 'professor/findAll').success(function(response) {
            if(response && response.length > 0){
                $scope.professores = response;
            }
        });  

        $scope.loadProfessores = function(query){

            $scope.professoresTags = [];
            $scope.professoresTagsAux = []; 

            var list = angular.copy($scope.professores);
            if(list.length <= 0){
                return;
            }

            var filtred = list.filter(function(d){
                return d.siape.toLowerCase().match(query.toLowerCase()) || d.nome.toLowerCase().match(query.toLowerCase());
            });
            for ( var i in filtred) {
                var nome = filtred[i].siape.trim()+' - '+filtred[i].nome.trim();
                if($scope.professoresTags.indexOf(nome)== -1){
                    $scope.professoresTagsAux = { text : nome , professor : filtred[i] };
                    $scope.professoresTags.push( $scope.professoresTagsAux);
                }
            }

            return $scope.professoresTags;
        };

    };
  
    $scope.init();
  
}]);