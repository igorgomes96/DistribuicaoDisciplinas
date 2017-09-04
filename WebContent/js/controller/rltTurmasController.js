app.controller('RltTurmasController',['utils','$sessionStorage', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
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

			$scope.nomeRelatorio = 'Turmas semestre ' + $scope.semestreSelecionado.ano + " / " + $scope.semestreSelecionado.semestre;

	        $http.get(url + 'turma/findTurmaRelatorio/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
	        	$scope.turmas = [];
	            if(response && response.length > 0){	
            		$scope.turmas = response;
	            } 
	        });
	
	  	});  

  	};

  	$scope.alterarSemestre = function(semestre) {
		var s = semestre;
		$scope.semestreSelecionado = s;
		$scope.nomeRelatorio = 'Turmas semestre ' + $scope.semestreSelecionado.ano + " / " + $scope.semestreSelecionado.semestre;
		$http.get(url + 'turma/findTurmaRelatorio/'+$scope.semestreSelecionado.ano+'/'+$scope.semestreSelecionado.semestre).success(function(response) {
        	$scope.turmas = [];
            if(response && response.length > 0){	
        		$scope.turmas = response;
            } 
        });
  	}

	$scope.init();
  
}]);