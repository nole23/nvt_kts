/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp') // ... omitted code
	.controller('zasto', ['$scope', '$location', '$uibModal',
	   '$log', '_', 'LoginResources', 
	   function($scope, $uibModal, $location, $log, _, LoginResources) {
	
		$scope.user = {};
		
		$scope.login = function() {
			console.log('ime '+$scope.user.username);
			LoginResources.loginUser($scope.user);
			
		}
	 
	}]);