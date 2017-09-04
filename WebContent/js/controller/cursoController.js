app.controller('CursoController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
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
          templateUrl: '../modal/cursoModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Criar curso";
              $scope.curso = {};
              $scope.sim = function () {
            	  $http.post(url + 'curso/save', $scope.curso).success(function(response) {
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
  
  $scope.openEdit = function(model, callback){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/cursoModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Editar curso";
              $scope.curso = angular.copy(model);
              $scope.disableCodigo = true;
              $scope.sim = function () {
            	  $http.post(url + 'curso/update', $scope.curso).success(function(response) {
            		  if(response && response.data){
        				  if(response.data.status == 300){
        					  utils.error(response.data.exception);
        				  }
        			  }
                      callback();
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
		  $http.post(url + 'curso/delete', model).success(function(response) {
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
	  $http.get(url + 'curso/findAll').success(function(response) {
		  if(response && response.length > 0){
			  $scope.cursos = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);