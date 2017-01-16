/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('LoginResources', ['Restangular', '_', function(Restangular, $http, _) {
		'use strict';
		
		var retVal = {};
		
		retVal.loginUsers = function(username, password) {
			return Restangular.all('users/login/nolee/321').getList().then(function(data){
				console.log('authentication token: ' + data);
			})
		}
		
		return retVal;
		
	}]);