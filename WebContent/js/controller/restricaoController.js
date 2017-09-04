app.controller('RestricaoController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.newRegister = function (){
	  $scope.openCreate($scope.init);
  };
  
  $scope.editRegister = function(model,nomeProfessor){
	  $scope.openEdit(model,$scope.init,nomeProfessor);
  };
  
  $scope.openCreate = function(callback){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/restricaoModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Criar restrição";
              $scope.restricao = {};
              
              $scope.professores = [];
         	  $http.get(url + "professor/listar").success(function(response) {
         		  $scope.professores = response;
         	  });
         	  
         	  $scope.onSelectProfessor = function(item){
         		 $scope.restricao.siape = item.siape;
         	  };
         	 
              $scope.sim = function () {
            	  
            	  if(!$scope.form.$valid){
            		  utils.error("Preencha os campos obrigatórios");
            		  return;
            	  }
            	  
            	  $http.post(url + 'restricao/save', $scope.restricao).success(function(response) {
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
  
  $scope.openEdit = function(model, callback,nomeProfessor){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/restricaoModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Editar restricao";
              $scope.restricao = angular.copy(model);
              $scope.action = 'edit';
              
              $scope.editSiape = $scope.restricao.siape+' - '+nomeProfessor;
              
              $scope.sim = function () {
            	  
            	  if(!$scope.form.$valid){
            		  utils.error("Preencha os campos obrigatórios");
            		  return;
            	  }
            	  
            	  $http.post(url + 'restricao/update', $scope.restricao).success(function(response) {
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
		  $http.post(url + 'restricao/delete', model).success(function(response) {
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
	  $http.get(url + 'restricao/listar').success(function(response) {
		  if(response && response.length > 0){
			  $scope.restricoes = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);