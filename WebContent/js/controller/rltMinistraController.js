app.controller('RltMinistraController',['utils','$sessionStorage', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $sessionStorage, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.semestres = [];
  $scope.order = 'disciplina';
	$scope.homeSiape = $sessionStorage.siape;
  
  $scope.init = function(){
	  
	  $http.get(url + 'semestre/listar').success(function(response) {
			$scope.semestres = response; 
			for(var i = 0; i < response.length; i++) {
				if ($scope.semestres[i].status == true) {
					$scope.semestres[i].estado = ' (Ativo)';
					$scope.semestre = $scope.semestres[i];
					$scope.semestreSelecionado = $scope.semestres[i];
				} else {
					$scope.semestres[i].estado = '';
				}
			}
			$scope.buscaRegistros($scope.semestreSelecionado);
	  });
	  
	    
  };
  
  $scope.buscaRegistros = function(semestre){
	  
	  $http.get(url + 'relatorio/relatFila/'+semestre.ano+'/'+semestre.semestre+'/'+$scope.order).success(function(response) {
		  $scope.relat = [];
		  if(response && response.length > 0){
			  var nomeAtivo = response[0].nomeProfessor;
			  var nomeClassAtiva = 'relatClass1';
			  $scope.relat = response.map(function(e){
				  if(nomeAtivo != e.nomeProfessor){
					  nomeAtivo = e.nomeProfessor;
					  nomeClassAtiva = (nomeClassAtiva == 'relatClass1') ? 'relatClass2' : 'relatClass1';
				  }
				  e.listClass = nomeClassAtiva;
				  return e;
			  });
		  }
	  });  
  };
  
  $scope.alterarOrdenacao = function(order){
	  $scope.order = order;
	  $scope.buscaRegistros($scope.semestre);
  };
  
  $scope.alterarSemestre = function(semestre){
	  $scope.buscaRegistros(semestre);
  }
  
  $scope.init();
  
}]);