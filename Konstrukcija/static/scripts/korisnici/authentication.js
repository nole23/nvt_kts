/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('LoginResources', ['Restangular', '_', '$http', '$q', function(Restangular, _, $http, $q) {
		'use strict';
		return {
			login : function(username, password, callBack) {
				console.log('ime '+username);
				var link = '/api/users/login/'+username+'/'+password;
				$http.get(link).success(function(data, textStatus, xhr) {
					console.log(JSON.stringify(xhr));
				}).error(function(data, textStatus, xhr) {
					console.log(JSON.stringify(data));
				})
				
			}
		}
		
	}]);