app.controller('SemestreController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
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
          templateUrl: '../modal/semestreModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Criar semestre";
              $scope.semestre = {
            		  "ano" : new Date().getFullYear(),
            		  "status": false
              };
              $scope.sim = function () {
            	  
            	  if(!$scope.form.$valid){
            		  utils.error("Preencha os campos obrigatórios");
            		  return;
            	  }
            	  
            	  $http.post(url + 'semestre/save', $scope.semestre).success(function(response) {
            		  if(response && response.data){
        				  if(response.data.status == 300){
        					  utils.error(response.data.exception);
        				  } else {
        					  callback();
            			  }
        			  } 
            		  $modalInstance.close();
            	  });
              };
              $scope.nao = function () {
                  $modalInstance.close();
              };
          }
      });  
  };
  
  $scope.ativarSemestre = function(semestre){
	  utils.confirm("Deseja realmente ativar este registro ?",function(){
		  $http.post(url + 'semestre/ativar', semestre).success(function(response) {
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
  
  $scope.openEdit = function(model, callback){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/semestreModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Editar semestre";
              $scope.semestre = angular.copy(model);
              $scope.disableCodigo = true;
              $scope.sim = function () {
            	  
            	  $http.post(url + 'semestre/update', $scope.semestre).success(function(response) {
            		  if(response && response.data){
        				  if(response.data.status == 300){
        					  utils.error(response.data.exception);
        				  } else {
        					  callback();
        				  }
        			  } 
            		  $modalInstance.close();
            	  });
              };
              $scope.nao = function () {
                  $modalInstance.close();
              };
          }
      });  
  };
    
  $scope.openRemove = function(model){
	  utils.confirm("Deseja realmente remover este registro ?",function(){
		  $http.post(url + 'semestre/delete', model).success(function(response) {
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
	  $http.get(url + 'semestre/listar').success(function(response) {
		  if(response && response.length > 0){
			  $scope.semestres = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);