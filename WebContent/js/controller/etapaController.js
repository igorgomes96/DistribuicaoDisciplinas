app.controller('EtapaController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.ativarEtapa = function(etapa){
		  if(etapa.ativo){
			  etapa.ativo = false;
		  } else {
			  etapa.ativo = true;
		  }
		   
		  $http.post(url + 'etapa/ativar', etapa).success(function(response) {
			  if(response && response.data){
				  if(response.data.status == 300){
					  utils.error(response.data.exception);
				  } else {
					  $scope.init();
				  }
			  } 
    	  });
  };
  
  $scope.init = function(){
	  $http.get(url + 'etapa/listar').success(function(response) {
		  if(response && response.length > 0){
			  $scope.etapas = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);