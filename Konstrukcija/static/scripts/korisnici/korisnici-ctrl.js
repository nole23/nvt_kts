/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KorisnikCtrl', ['$scope', '$uibModal',
	   '$log', '_', 'KorisniciResource', 
	   function($scope, $uibModal, $log, _, KorisniciResource) {
		
		KorisniciResource.getKorisnici().then(function(items) {
			$scope.registrovani = items;
		});
		
		KorisniciResource.getNekretnine().then(function(items) {
			$scope.nekretnine = items;
		})
	}]);