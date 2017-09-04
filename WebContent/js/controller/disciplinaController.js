app.controller('DisciplinaController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.newRegister = function (){
	  $scope.openCreate($scope.init);
  };
  
  $scope.editRegister = function(model){
      $scope.disciplina = {};
	  $scope.openEdit(model,$scope.init);
  };
  
  $scope.onSelectCurso = function(item){
	  $scope.disciplina.curso = item;
  };

  $scope.openCreate = function(callback){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/disciplinaModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Criar disciplina";
              $scope.disciplina = {};
              $scope.cursos = [];
         	  $http.get(url + "curso/findAll").success(function(response) {
         		  $scope.cursos = response;
         	  });
         	 
              $scope.sim = function () {
            	  $scope.disciplina.curso = $scope.disciplina.curso.codigo;
            	  $http.post(url + 'disciplina/save', $scope.disciplina).success(function(response) {
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
          templateUrl: '../modal/disciplinaModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
              $scope.title = "Editar Disciplina";
              $scope.disciplina = angular.copy(model);
              $scope.disciplina.curso = model.curso;
              $scope.disableCodigo = true;
              $scope.disciplina.fila = String($scope.disciplina.fila);
              $scope.sim = function () {
                var validaFila = $scope.disciplina.fila;
                if (validaFila == "true" || validaFila == "false" || validaFila == true || validaFila == false) {
              	  $http.post(url + 'disciplina/update', $scope.disciplina).success(function(response) {
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
                    utils.error("O campo Tem Fila? aceita apenas os valores: true/false");
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
		  $http.post(url + 'disciplina/delete', model).success(function(response) {
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
	  $http.get(url + 'disciplina/findAll').success(function(response) {
		  if(response && response.length > 0){
			  $scope.disciplinas = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);