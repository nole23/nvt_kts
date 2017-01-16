/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KorisnikCtrl', ['$scope', '$location', '$uibModal',
	   '$log', '_', 'KorisniciResource', 
	   function($scope, $uibModal, $location, $log, _, KorisniciResource) {
		
		$scope.korisnik = {};
		
		KorisniciResource.getKorisnici().then(function(items) {
			$scope.registrovani = items;
		});
		
		KorisniciResource.getNekretnine().then(function(items) {
			$scope.nekretnine = items;
		})
		
		$scope.registration = function() {
			console.log('ime '+$scope.korisnik);
			KorisniciResource.saveNewKorisnik($scope.korisnik);
			//window.location = "#/login";
		}
		
	}]);