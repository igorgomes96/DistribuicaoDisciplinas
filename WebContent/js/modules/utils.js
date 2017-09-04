app.factory('utils', function($modal) {
		return {
			warn : function (message, title, sucessCallback) {
		        var modalInstance = $modal.open({
		            template: ' <div class="modal-header bg-warning-light"><h3 style="margin: 0;">{{title}}</h3></div><div class="modal-body"><span>{{message}}</span></div><div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">OK</button></div>',
		            controller: function ($scope, $modalInstance) {
		                $scope.message = message;
		                $scope.title = title || "Alerta";
		                $scope.ok = function () {
		                    $modalInstance.close();
		                };
		            }
		        });
		        modalInstance.result.then(sucessCallback);
			 },
		
			 info : function (message, sucessCallback, title,size) {
					 if (size===undefined) {
					 	size = 'lg';
					 }
			        var modalInstance = $modal.open({
			            size : size,
			        	template: ' <div class="modal-header bg-info-light"><h3 style="margin: 0;">{{title}}</h3></div><div class="modal-body"><span>{{message}}</span></div><div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">OK</button></div>',
			            controller: function ($scope, $modalInstance) {
			                $scope.message = message;
			                $scope.title = title || "Informação";
			                $scope.ok = function () {
			                    $modalInstance.close();
			                };
			            }
			        });
		
			        modalInstance.result.then(sucessCallback);
			 },
		
			 confirm : function (message, positiveCallback, negativeCallback, title,size) {
				 if (size===undefined) {
					 	size = 'lg';
					 }
			        $modal.open({
			        	size : size,
			            template: ' <div class="modal-header bg-modal"><h3 style="margin: 0;">{{title}}</h3></div><div class="modal-body"><span>{{message}}</span></div><div class="modal-footer"><button class="btn btn-primary" ng-click="sim()">Sim</button><button class="btn btn-danger" ng-click="nao()">Não</button></div>',
			            controller: function ($scope, $modalInstance) {
			                $scope.message = message;
			                $scope.title = title || "Confirmação";
			                $scope.sim = function () {
			                    $modalInstance.result.then(positiveCallback);
			                    $modalInstance.close();
			                };
			                $scope.nao = function () {
			                    $modalInstance.result.then(negativeCallback);
			                    $modalInstance.close();
			                };
			            }
			        });
			 },
			    
			 error : function (message, title, sucessCallback, size) {
				 	if (size===undefined) {
				 		size = 'lg';
				 	}
			        var modalInstance = $modal.open({
			        	size : size,
			            template: ' <div class="modal-header bg-danger"><h3 style="margin: 0;">{{title}}</h3></div><div class="modal-body"><span>{{message}}</span></div><div class="modal-footer"><button class="btn btn-primary" ng-click="ok()">OK</button></div>',
			            controller: function ($scope, $modalInstance) {
			                $scope.message = message;
			                $scope.title = title || "Erro";
			                $scope.ok = function () {
			                    $modalInstance.close();
			                };
			            }
			        });
			        modalInstance.result.then(sucessCallback);
			}
		}
  });
