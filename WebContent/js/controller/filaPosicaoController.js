app.controller('PrioridadeController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
  
  $scope.submeterFilaPosicao = function(){
	  var list = angular.copy($scope.list);
	  for ( var i in list) {
		delete list[i].nomeProf;
		list[i].pos = parseInt(i)+parseInt(1);
	}
	  
	  var res = $http.post(url + 'filaDiscilplina/atualizaPosicao', list).then(function(){
		  $scope.msgAlteraPrioridade = 'Posições atualizadas com sucesso';
		  $scope.showPrioridadeSucesso = true;
		  $scope.showPrioridadeErro = false;
		  $timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
	  },function(){
		  $scope.msgAlteraPrioridade = 'Ocorreu um problema ao atualizar a lista de posições';
		  $scope.showPrioridadeSucesso = false;
		  $scope.showPrioridadeErro = true;
		  $timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
	  });
  };

  $scope.buscar = function(item){
	  $http.get(url + 'fila/listaFilaDisciplina/'+item.codigo).then(function(response){
		  $scope.list = response.data;
	  });
  };
  
  $scope.filterDisciplina = function(viewValue,item){
	  if($scope.disciplina){
		  return $scope.disciplinas.filter(function(d){
			  return d.nome.toLowerCase().match($scope.disciplina.toLowerCase()) || d.codigo.toLowerCase().match($scope.disciplina.toLowerCase());  
		  });
	  } else {
		  return true;
	  }
	  
  }
  
  $scope.init = function(){
	  $http.get(url + 'disciplina/findAll').success(function(response) {
		  if(response && response.length > 0){
			  $scope.disciplinas = response;
		  }
	  });  
  };
  
  $scope.esconderTodasMsgs = function() {
		$scope.showMsgSucesso = false;
		$scope.showMsgErro = false;
		$scope.msgAddDisciplina = '';
		$scope.showRemoveSucesso = false;
		$scope.showRemoveErro = false;
		$scope.msgRemoveDisciplina = '';
		$scope.showPrioridadeSucesso = false;
		$scope.showPrioridadeErro = false;
		$scope.msgAlteraPrioridade = '';
	};
  
  $scope.init();
  
}]);