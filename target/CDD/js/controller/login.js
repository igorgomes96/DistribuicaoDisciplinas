var app = angular.module("loginApp", []);

app.config(function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
		});
});

app.controller("LoginController", function($scope, $location, $http) {
	//var url = '/CDD/';
	var url = '/CDD/';
	$scope.entrar = function() {
		if (!!$scope.usuario && $scope.usuario != '' && !!$scope.senha && $scope.senha != '') {
			
			var usuarioView = {
				'usuario': $scope.usuario,
				'senha': $scope.senha
			}
			
			var res = $http.post(url + 'rest/login/efetuarLogin', usuarioView).success(function(response) {
				console.log(response); 
				if (response) {
					var fullUrl = $location.absUrl();
					window.location = fullUrl+'disciplina';
				} else {
					alert('Usuário ou senha incorretos!');
				}
			});
			
		} else {
			alert('Preencha os campos obrigatórios!');
		}
	}
});
