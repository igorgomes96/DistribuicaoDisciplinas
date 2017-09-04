app.controller('RltMinistra4Controller',['utils','$sessionStorage', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $sessionStorage, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.homeSiape = $sessionStorage.siape;
  
  $scope.init = function(){

	  $http.get(url + 'relatorio/relatFila4').success(function(response) {
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
  
  $scope.init();
  
}]);