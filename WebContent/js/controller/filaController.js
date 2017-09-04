app.controller('FilaController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
  'use strict';
  var url = '/CDD/rest/';
    
  $scope.copiarFila = function(model){
	  $scope.openCopy(model,$scope.init);
  };
  
  $scope.removeDisciplinasFila = function(model){
	  $http.post(url + 'fila/removeDisciplinasFila').success(function(response) {
		  $scope.msgRemoverDisciplina = 'Disciplinas removidas com sucesso';
		  utils.info($scope.msgRemoverDisciplina);
	  });
  };
  
  $scope.openCopy = function(model, callback){
	  $modal.open({
      	  size : 'lg',
          templateUrl: '../modal/filaCopyModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
        	  $scope.fila = angular.copy(model);
        	  $scope.filaOld = angular.copy(model);
        	  
        	  
              $scope.sim = function () {
            	  var params = {
            			  'ano' : $scope.fila.ano,
            			  'semestre' : $scope.fila.semestre,
            			  'anoOld' : $scope.filaOld.ano,
            			  'semestreOld' : $scope.filaOld.semestre,
            	  }
            	  $http.post(url + 'fila/copyFila', params).success(function(response) {
            		  if(response && response.data){
        				  if(response.data.status == 300){
        					  utils.error(response.data.exception);
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
  
     
  $scope.editFila = function(model){
	  $scope.openEditFila(model,$scope.init);
  };
  
  $scope.alteraPosicao = function(){
	  window.location.href = window.location.href.replace("fila","filaPosicao");
  };
  
  $scope.openEditFila = function(model, callback){
	  $modal.open({
		  size : 'lg',
          templateUrl: '../modal/filaAddModal.html',
          backdrop: 'static',
          keyboard: true,
          controller: function ($scope, $modalInstance) {
        	  
        	 $scope.fila = angular.copy(model);
        	 $scope.title = "Adicionar";
        	  
        	 $scope.professores = [];
        	 $http.get(url + "professor/listar").success(function(response) {
        		 $scope.professores = response;
        	 });
        	 
        	 $http.get(url + "disciplina/listar").success(function(response) {
        		 $scope.disciplinas = response; 
 			 });
        	 
        	 $scope.removeProfessorFila = function() {
            	 var params = {
	    			 "siape": $scope.fila.professor.siape,
	    			 "codigoDisciplina": $scope.fila.disciplina.codigo
            	 }
     			
 				var res = $http.post(url + 'fila/atualizaStatusParaRemossao', params);
     				
 				res.success(function(data, status, headers, config) {
 					$scope.message = data;
 					if (data == 1) {
 						$scope.msgRemoveDisciplina = 'O status da disciplina foi alterado para aguardando remoção. Ela será removida no final da etapa de entrar / sair de filas.';
 						utils.info($scope.msgRemoveDisciplina);
 					} else {
 						$scope.msgRemoveDisciplina = 'O professor não está associado mais a esta disciplina.';
 						utils.info($scope.msgRemoveDisciplina);
 					}
 				});
 				
 				res.error(function(data, status, headers, config) {
 					$scope.msgRemoveDisciplina = 'Ocorreu um problema ao remover a disciplina.';
 					utils.info($scope.msgRemoveDisciplina);
 				});
     			
            	 $modalInstance.close();
             };
        	 
             $scope.addProfessorFila = function () {
            	 var params = {
	    			 "siape": $scope.fila.professor.siape,
	    			 "codigoDisciplina": $scope.fila.disciplina.codigo
            	 }
     			var res = $http.post(url + 'fila/entrarFilaDisciplina', params);
            	 
            	 res.success(function(data, status, headers, config) {
            		 console.log("data", data);
  					$scope.message = data;
  					if (data >= 1) {
  						$scope.msgAddDisciplina = 'Disciplina adicionada com sucesso';
  						utils.info($scope.msgAddDisciplina);
  					} else {
  						$scope.msgAddDisciplina = 'Ocorreu um problema ao adicionar';
  						utils.info($scope.msgAddDisciplina);
  					}
  				});
            	 
     			res.error(function(data, status, headers, config) {
     				$scope.msgAddDisciplina = 'Ocorreu um problema ao adicionar a disciplina!';
     				utils.info($scope.msgAddDisciplina);
     			});
            	 $modalInstance.close();
             };
             
             $scope.posicaoProfessorFila = function(){
            	 $scope.modalPosicao($scope.fila);
             }
             
             $scope.modalPosicao = function(fila){
            	 var modal = $modal.open({
                     templateUrl: '../modal/filaMudaPrioridade.html',
                     backdrop: 'static',
                     keyboard: true,
                     controller: ['$scope', '$modalInstance', function ($scope, $modalInstance) {
                    	 
                    	 $scope.filaPos = fila;
                    	 
	                	 $http.get(url + 'filaDiscilplina/buscaPorSiapeDisciplina/'+fila.professor.siape+'/'+fila.disciplina.codigo).success(function(response) {
	                		 if(response && response.length > 0){
	                			 $scope.filaDisciplina = response[0];
	                			 $scope.posicaoAtual = $scope.filaDisciplina.pos;
	                		 }
	                   	 });
	                	 
	                	$scope.sim = function (){
	                		if(!$scope.posicaoNova || !$scope.filaDisciplina){
	                			return;
	                		}
	                		var params = {
	                				posicaoNova: $scope.posicaoNova,
	                				siape: $scope.filaPos.professor.siape,
	                				disciplina: $scope.filaPos.disciplina.codigo,
	                				posicaoAntiga: $scope.posicaoAtual
	                		};
	                		$http.post(url + 'filaDiscilplina/atualizaPosicao', params).success(function(response) {
	                			$scope.msgAddDisciplina = 'Alteração realizada com sucesso.';
	      						utils.info($scope.msgAddDisciplina);
	                			$modalInstance.close();
	                		});
	                	 };
                    	 
                    	 $scope.nao = function () {
                             $modalInstance.close();
                         };
                     }]
            	 });
            	 
            	 modal.result.then(function (result) {
            		 $modalInstance.close(); 
            	 });
            	 
             };
             
             $scope.nao = function () {
                 $modalInstance.close();
             };
          }
      });  
  };
  
  $scope.init = function(){
	  $http.get(url + 'fila/listaFilasSemestre').success(function(response) {
		  if(response && response.length > 0){
			  $scope.filas = response;
		  }
	  });  
  };
  
  $scope.init();
  
}]);