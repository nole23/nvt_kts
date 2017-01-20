/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KorisnikCtrl', ['$scope', '$location', '$uibModal', '$window',
	   '$log', '_', 'KorisniciResource', 'RegistrationResouce', 
	   function($scope, $uibModal, $window, $location, $log, _, KorisniciResource, RegistrationResouce) {
		
		$scope.korisnik = {};
		
		KorisniciResource.getKorisnici().then(function(items) {
			$scope.registrovani = items;
		});
		
		$scope.update = function() {
			KorisniciResource.updatUsers($scope.resource);
		}
		
		
		KorisniciResource.getNekretnine().then(function(items) {
			$scope.nekretnine = items;
		})
		
		$scope.registration = function() {
			RegistrationResouce.saveNewKorisnik($scope.korisnik, registrationCbck);
		}
		
		function registrationCbck(success) {
			if (success.error == 'mailZauzet') {
				$log.info('email zauzet!');
				$scope.messageRegistration = success.error;
				
			} else if (success.error == 'error') {
				$log.info('Nije sacuvano!');
				$scope.messageRegistration = success.error;
				
			} else if (success.success == 'uspesno') {
				$log.info('Uspesno ste registrovani!');
				$scope.messageRegistration = success.success;
				alert('Pre nego se registrujete proverite vas email')
				window.location = '#/login';
				
			} else if (success.error == 'usernamZauzet') {
				$log.info('Korisnicko ime zauzeto!');
				$scope.messageRegistration = success.error;
			} 
		}
		
	}]);