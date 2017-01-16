/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp') // ... omitted code
	.controller('LoginCtrl', ['$scope', '$http', '$log', 'LoginResources', 
	   function($scope, $http, $log, LoginResources) {
		
		$scope.login
		
		$scope.login = function() {
			
			LoginResources.loginUsers($scope.login.username, $scope.login.password);
			
            /*console.log('logIn called')
 
            $http.post('http://localhost:8080/api/users/login', { username: $scope.login.username, password: $scope.login.password }).
                success(function(data) {
                    console.log('authentication token: ' + data.token);
                    localStorage["authToken"] = data.token;
                    authService.loginConfirmed({}, function(config) {
                        if(!config.headers["X-Auth-Token"]) {
                            console.log('X-Auth-Token not on original request; adding it');
                            config.headers["X-Auth-Token"] = getLocalToken();
                        }
                        return config;
                    });
                }).
                error(function(data) {
                    console.log('login error: ' + data);
                    $rootScope.$broadcast('event:auth-loginFailed', data);
                });*/
			
			
        }
	 
	}]);