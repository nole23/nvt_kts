'use strict';

angular.module('nekretnineClientApp')
	.controller('AcauntCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'KorisniciResource', 
	   function($scope, $uibModal, $routeParams, $log, _, KorisniciResource) {
		

		
		$scope.order_id = $routeParams.idKorisnik;
		console.log('jbg '+$routeParams.idKorisnik);
		KorisniciResource.getKorisnik($routeParams.idKorisnik).then(function(items) {
			$scope.jedanKorisnik = items;
			console.log(items);
		})
	
		
	}])