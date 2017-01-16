/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp') // ... omitted code
	.controller('LoginCtrl', ['$scope', '$http','$location', '$log', '_', 'LoginResources', 
	   function($scope, $http, $location, $log, _, LoginResources) {
		
		$scope.korisnik={};
		$scope.login=function () {
			LoginResources.login($scope.korisnik.username, $scope.korisnik.password, callBack);
			console.log('podaci '+JSON.stringify($scope.korisnik));
			//console.log('podaci '+JSON.stringify(callBack));
		};
		function callBack(success) {
			if (success == "ok") {
				$log.info('success!');
			}
			else{
				$log.info('failure!');
			}
		}
	 
	}]);