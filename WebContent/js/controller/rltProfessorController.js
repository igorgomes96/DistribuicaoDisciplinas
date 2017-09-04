app.controller('RltProfessorController',['utils','$sessionStorage', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $sessionStorage, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';

  $scope.semestres = [];
  $scope.semestre = {};
    $scope.homeSiape = $sessionStorage.siape;
  
  $scope.init = function(){
  $http.get(url + 'semestre/listar').success(function(response) {
		$scope.semestres = response; 
		for(var i = 0; i < response.length; i++) {
			if ($scope.semestres[i].status == true) {
				$scope.semestres[i].estado = '(Ativo)';
				$scope.semestre = $scope.semestres[i];
				$scope.semestreSelecionado = $scope.semestres[i];
			} else {
				$scope.semestres[i].estado = '';
			}
		}
	
	  $http.get(url + 'rltProfessor/listar/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
		  if(response && response.length > 0){
			  var nomeAtivo = response[0].nomeProfessor;
			  var nomeClassAtiva = 'relatClass1';
			  $scope.professores = response.map(function(e){
				  if(nomeAtivo != e.nomeProfessor){
					  nomeAtivo = e.nomeProfessor;
					  nomeClassAtiva = (nomeClassAtiva == 'relatClass1') ? 'relatClass2' : 'relatClass1';
				  }
				  e.listClass = nomeClassAtiva;
				  return e;
			  });
		  }
	  });  
  	});
  };
  
  $scope.alterarSemestre = function(semestre) {
	  $scope.professores = [];
		var s = semestre;
		/*
		 * Atualiza o semestre selecionado na tela
		 */
		$scope.semestreSelecionado = s;
		
		$http.get(url + 'rltProfessor/listar/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
			  if(response && response.length > 0){
				  var nomeAtivo = response[0].nomeProfessor;
				  var nomeClassAtiva = 'relatClass1';
				  $scope.professores = response.map(function(e){
					  if(nomeAtivo != e.nomeProfessor){
						  nomeAtivo = e.nomeProfessor;
						  nomeClassAtiva = (nomeClassAtiva == 'relatClass1') ? 'relatClass2' : 'relatClass1';
					  }
					  e.listClass = nomeClassAtiva;
					  return e;
				  });
			  }
		  }); 
		
  }
  
  $scope.init();
  
}]);