/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('LoginResources', ['Restangular', '_', function(Restangular, $http, _) {
		'use strict';
		
		var retVal = [];
		
		retVal.loginUser = function(user) {
			var parameter = JSON.stringify({username:user.username, password:user.password});
			 var loginDTO = [
                {
                    "username": user.username,
                    "password": user.password,
                }
            ];
			//console.log('ovo je json logina '+loginDTO)
			return Restangular.all('users/login').post(user);
			
			
			console.log('provera '+$http.get('users/loggeduser'));
		}
		
		return retVal;
		
	}]);