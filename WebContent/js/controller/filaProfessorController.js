app.controller('FilaProfessorController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
    'use strict';
    var url = '/CDD/rest/';
  
    $scope.buscaDisciplinas2 = function (siape){
        $scope.disciplinas = [];
        $http.get(url + 'filaProfessor/listaFilaPorSiape/'+siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
            if(response && response.length > 0){
                $scope.disciplinas = response;
            }
        });  
    };

    $scope.buscaDisciplinas = function (professor){

        $scope.disciplinas = [];

        $scope.prof = [];
        $scope.prof = professor;
        var siape = $scope.prof.professor.siape.trim();

        //console.log(siape); return;

        $http.get(url + 'filaProfessor/listaFilaPorSiape/'+siape+'/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
            if(response && response.length > 0){
                $scope.disciplinas = response;
            }
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
  
    $scope.editRegister = function(model){
        $scope.openEdit(model,$scope.init);
        $scope.disciplinas = [];
    };
  
    $scope.openEdit = function(model, callback){
        $modal.open({
            size : 'lg',
            templateUrl: '../modal/filaProfessorModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Editar fila";
                $scope.professor = angular.copy(model);
                $scope.disableSiape = true;

                $scope.sim = function () {
                    var validaFila = $scope.professor.periodo;
                    if (validaFila == "true" || validaFila == "false" || validaFila == true || validaFila == false) {
            	        $http.post(url + 'filaProfessor/save', $scope.professor).success(function(response) {
                		    if(response && response.data){
                    			if(response.data.status == 300){
                    				utils.error(response.data.exception);
                    			} else {
                                    callback();
                    			}
                    		} 
                    		 $modalInstance.close();
                    	});
                    } else {
                        utils.error("O campo Periodo Preferencial aceita apenas os valores: true/false");
                        callback();
                    };
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
        
    $scope.openRemove = function(model){
        utils.confirm("Deseja realmente remover este registro ?",function(){
            $http.post(url + 'filaProfessor/removeDisciplinasFila', model).success(function(response) {
                if(response && response.data){
                    if(response.data.status == 300){
                        utils.error(response.data.exception);
                    } else {
                        $scope.buscaDisciplinas2(model.siape);
                    }
                } 
            });
        },null,"Excluir registro");  
    };
  
    $scope.init = function(){
    	
	    $scope.botaoDesativado = true;

        $http.get(url + 'professor/findAll').success(function(response) {
            if(response && response.length > 0){
                $scope.professores = response;
            }
        });  

        $http.get(url + 'semestre/listar').success(function(response) {
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