/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp') // ... omitted code
	.controller('LoginCtrl', ['$scope', '$localStorage', '$http', '$window', '$log', '_', '$rootScope', 'LoginResources', 
	   function($scope, $localStorage, $http, $window, $log, _, $rootScope, LoginResources) {
		$scope.isLogin = false;
		$scope.korisnik={};
		
		$scope.login=function () {
			
			LoginResources.login($scope.korisnik.username, $scope.korisnik.password, loginCbck);
			
			console.log('podaci '+JSON.stringify($scope.korisnik));
			
		};
		function loginCbck(success) {
			if (success.error == 'ime') {
				$log.info('pogresno ime!');
				$scope.messageLogin = success;
				//window.location = '#/'
			} else if(success.error == 'sifra'){
				$log.info('pogresna sifra!');
				$scope.messageLogin = success;
			} else if(success.error == null) {
				$log.info('success!');
				$rootScope.messageLogin = 'ulogovan';
				var id = success.cookies;
				var lokacija = "#/account/"+id;
				window.location = lokacija;
				$rootScope.messageLogin = '';
			}
		}
	 
	}]);