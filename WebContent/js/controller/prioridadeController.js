app.controller('PrioridadeController',['utils', '$scope', '$http', '$location', '$routeParams', '$anchorScroll', '$timeout', '$modal',
                        function(utils, $scope, $http, $location, $routeParams, $anchorScroll, $timeout, $modal) {
	'use strict';
	var url = '/CDD/rest/';
  
	$scope.submeterFilaPrioridades = function() {
		var listaPrioridades = [];
		var numeroPrioridade = 1;
		angular.forEach($scope.list, function(value, key){
		     
			var prioridade = {};

			prioridade.siape = value.siape;
			prioridade.codigoDisciplina = value.codigoDisciplina;
			prioridade.idTurma = value.idTurma;
			prioridade.prioridade = numeroPrioridade;

			numeroPrioridade++;

			listaPrioridades.push(prioridade);
		     
		});
		
		var res = $http.post(url + 'filaDiscilplina/atualizaPrioridades', listaPrioridades);
//		var res = $http.post(url + 'rest/filaDiscilplina/atualizaPrioridades', listaPrioridades);
		
		res.success(function(data, status, headers, config) {
			$scope.message = data;
			if (data == 1) {
				
				$scope.msgAlteraPrioridade = 'Prioridades atualizadas com sucesso';
				$scope.showPrioridadeSucesso = true;
				$scope.showPrioridadeErro = false;
				
			} else {
				
				$scope.msgAlteraPrioridade = 'Ocorreu um problema ao atualizar a lista de prioridades';
				$scope.showPrioridadeSucesso = false;
				$scope.showPrioridadeErro = true;
				
			}
			
			$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
		});
		
		res.error(function(data, status, headers, config) {
			
			$scope.msgAlteraPrioridade = 'Ocorreu um problema ao atualizar a lista de prioridades';
			$scope.showPrioridadeSucesso = false;
			$scope.showPrioridadeErro = true;
			
			$timeout(function(){ $scope.esconderTodasMsgs(); }, 6000);
		});
		
	}
  
	/*$scope.buscar = function(item){
		$http.get(url + 'prioridade/buscaDisciplinasProfessor/'+item.siape).then(function(response){
	  		$scope.list = response.data;
		});
	};*/

	$scope.buscar = function(professor){

		$scope.prof = [];
        $scope.prof = professor;
        var siape = $scope.prof.professor.siape.trim();

		//console.log(siape);

		$http.get(url + 'prioridade/buscaDisciplinasProfessor/'+siape).then(function(response){
		  	$scope.list = response.data;
		});

		$scope.nomeProfessor =  $scope.prof.professor.nome + " - " + $scope.prof.professor.siape;
		
		$scope.botaoDesativado = false;
		$scope.removeInputText();

	};

  	$scope.removeInputText = function() {
        document.querySelector(".tags input").style.display = "none";
    };

    $scope.exibeInputText = function() {
        document.querySelector(".tags input").style.display = "inline-block";
    };
  
	$scope.init = function(){
		$http.get(url + 'professor/listar').success(function(response) {
			if(response && response.length > 0){
				$scope.professores = response;
			}
		});  

	 	$scope.loadProfessores = function(query){

            $scope.professoresTags = [];
            $scope.professoresTagsAux = []; 

            var list = angular.copy($scope.professores);
            if(list.length <= 0){
                return;
            }

            var filtred = list.filter(function(d){
                return d.siape.toLowerCase().match(query.toLowerCase()) || d.nome.toLowerCase().match(query.toLowerCase());
            });
            for ( var i in filtred) {
                var nome = filtred[i].siape.trim()+' - '+filtred[i].nome.trim();
                if($scope.professoresTags.indexOf(nome)== -1){
                    $scope.professoresTagsAux = { text : nome , professor : filtred[i] };
                	$scope.professoresTags.push( $scope.professoresTagsAux);
                }
            }

            return $scope.professoresTags;
        };

        $scope.botaoDesativado = true;
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