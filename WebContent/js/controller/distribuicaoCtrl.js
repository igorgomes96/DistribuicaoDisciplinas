angular.module('mainApp').controller('distribuicaoCtrl', ['$scope', 'cenarioAPI', function($scope, cenarioAPI) {

	var self = this;

	self.numsCenarios = [];
	self.semestreAtual = null;
	self.distribuicao = [];

	var loadCenarios = function(idSemestre) {
		cenarioAPI.getNumerosPorSemestre(idSemestre)
		.then(function(dado) {
			self.numsCenarios = dado.data;
		}, function(error) {
			console.log(error);
		});
	}

	self.loadCenario = function(idSemestre, numCenario) {
		cenarioAPI.getDistribuicaoCenario(idSemestre, numCenario)
		.then(function(dado) {
			self.distribuicao = dado.data;
		}, function(error) {
			console.log(error);
		});
	}

	var listenerAlteraSemestre = $scope.$on('eventoAlteraSemestre', function(evento, semestre) {
		self.semestreAtual = semestre;
		if (semestre) {
			loadCenarios(semestre.id);
		} else {
			self.numsCenarios = [];			
		}
	});

	$scope.$on('destroy', function() {
		listenerAlteraSemestre();
	});
}]);