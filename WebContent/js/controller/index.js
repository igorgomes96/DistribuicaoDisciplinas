var app = angular.module("indexApp", ['ngStorage']);

app.config(function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
});

app.controller("IndexController", function($scope, $location, $sessionStorage, $http) {
	'use strict';
	var url = '/CDD/rest/';

	$scope.entrar = function() {
		$scope.doLogin();
	}
	
	$scope.clicarEnter = function(keyEvent) {
		if (keyEvent.which === 13) {
			$scope.doLogin();
		}
	}

	$scope.doLogin = function(){

		$sessionStorage.siape = $scope.siape;

 		$http.post(url + 'professor/loginProfessor/'+$sessionStorage.siape).success(function(response) {

	 	 	$scope.permiteLogin = response; 

	 	 	//console.log($scope.permiteLogin);

	 	 	if($scope.permiteLogin == true) {

	 	 		var fullUrl = $location.absUrl();
				window.location = fullUrl+'main/'+$scope.siape;

	 	 	} else {

	 	 		alert('Siape não cadastrado!');

	 	 	}
 		   
 	    });
	 

	}

});
