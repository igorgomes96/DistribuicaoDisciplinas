app.controller('ProfessorController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
    'use strict';
    var url = '/CDD/rest/';
  
    $scope.newRegister = function (){
        $scope.openCreate($scope.init);
    };
  
    $scope.editRegister = function(model){
	   $scope.openEdit(model,$scope.init);
    };
  
    $scope.openCreate = function(callback){
        $modal.open({
            size : 'lg',
            templateUrl: '../modal/professorModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.title = "Novo professor";
                $scope.professor = {};
                $scope.sim = function () {

                    if($scope.form.$invalid){

                        if($scope.form.dataSaida.$invalid) {

                            utils.error("Campo data de saída inválido");

                        } else if($scope.form.dataExoneracao.$invalid) {

                            utils.error("Campo data de exoneração inválido");

                        } else if($scope.form.dataAposentadoria.$invalid) {

                            utils.error("Campo data de aposentadoria inválido");

                        } else {

                            utils.error("Preencha todos os campos obrigatórios");

                        }
                        return;
                    } else {
                        $http.post(url + 'professor/save', $scope.professor).success(function(response) {
                        if(response && response.data){
                            if(response.data.status == 300){
                                utils.error(response.data.exception);
                            } else {
                                callback();
                            }
                        } 
                        $modalInstance.close();
                        });
                    }
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
  
   $scope.openEdit = function(model, callback){
	   $modal.open({
      	    size : 'lg',
            templateUrl: '../modal/professorModal.html',
            backdrop: 'static',
            keyboard: true,
            controller: function ($scope, $modalInstance) {
                $scope.siapeAntigo = [];
                $scope.title = "Editar professor";
                $scope.professor = angular.copy(model);
                $scope.professor.dataIngresso = addDays($scope.professor.dataIngresso,1);
                $scope.professor.dataNasc = addDays($scope.professor.dataNasc,1);
            
                $scope.siapeAntigo = $scope.professor.siape;
                $scope.disableSiape = false;

                if($scope.professor.dataSaida !== '' && $scope.professor.dataSaida !== null) {
                    $scope.professor.dataSaida = addDays($scope.professor.dataSaida,1);
                }

                if($scope.professor.dataExoneracao !== '' && $scope.professor.dataExoneracao !== null) {
                    $scope.professor.dataExoneracao = addDays($scope.professor.dataExoneracao,1);
                }

                if($scope.professor.dataAposentadoria !== '' && $scope.professor.dataAposentadoria !== null) {
                    $scope.professor.dataAposentadoria = addDays($scope.professor.dataAposentadoria,1);
                }
              
                function addDays(date, days) {
        	        var result = new Date(date);
        	        result.setDate(result.getDate() + days);
        	        return result;
    	        }
              
                $scope.sim = function () {

                    if($scope.form.$invalid){

                        if($scope.form.dataSaida.$invalid) {

                            utils.error("Campo data de saída inválido");

                        } else if($scope.form.dataExoneracao.$invalid) {

                            utils.error("Campo data de exoneração inválido");

                        } else if($scope.form.dataAposentadoria.$invalid) {

                            utils.error("Campo data de aposentadoria inválido");

                        } else {

                            utils.error("Preencha todos os campos obrigatórios");
                            
                        }
                        return;
                    } else {

                	    $http.post(url + 'professor/update/'+$scope.siapeAntigo, $scope.professor).success(function(response) {
                		    if(response && response.data){
            				    if(response.data.status == 300){
            					    utils.error(response.data.exception);
            				    } else {
            					    callback();
            				    }
            			    } 
                		    $modalInstance.close();
                	    });

                    }
                };
                $scope.nao = function () {
                    $modalInstance.close();
                };
            }
        });  
    };
    
    $scope.openRemove = function(model){
	    utils.confirm("Deseja realmente remover este registro ?",function(){
		    $http.post(url + 'professor/delete', model).success(function(response) {
			    if(response && response.data){
				    if(response.data.status == 300){
					    utils.error(response.data.exception);
				    } else {
					    $scope.init();
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
    };
  
    $scope.init();
  
}]);