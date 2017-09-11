angular.module('mainApp').service('filaTurmaAPI', ['$http', function($http) {
	var self = this;
	
	self.distribuir = function() {
		$http.get('/CDD/filaTurma/distribuir');
	}
}]);