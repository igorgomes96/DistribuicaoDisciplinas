/*angular.module('indexApp', [])
	.config(function($locationProvider) {
		$locationProvider.html5Mode(true);
	})
	.controller('IndexController', function($scope, $location) {
		
		var url = '/CDD/';
		
		$scope.entrar = function() {
			if (!!$scope.siape && $scope.siape != '') {
				$locationProvider.html5Mode(true);
				$location.path('/main/'+$scope.siape);
			} else {
				alert('Siape inválido!');
			}
		}
		
    
  });*/
var app = angular.module("indexApp", []);

app.config(function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
});

app.controller("IndexController", function($scope, $location) {
	//var url = '/CDD/';
	var url = '/CDD/';
	$scope.entrar = function() {
		if (!!$scope.siape && $scope.siape != '') {
			var fullUrl = $location.absUrl();
			window.location = fullUrl+'main/'+$scope.siape;
		} else {
			alert('Siape inválido!');
		}
	}
	
	$scope.clicarEnter = function(keyEvent) {
		if (keyEvent.which === 13) {
			if (!!$scope.siape && $scope.siape != '') {
				var fullUrl = $location.absUrl();
				window.location = fullUrl+'main/'+$scope.siape;
			} else {
				alert('Siape inválido!');
			}
		}
	}
});
