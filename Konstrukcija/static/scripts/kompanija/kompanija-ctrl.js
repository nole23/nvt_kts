/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KompanijaCtrl', ['$scope', '$uibModal', '$window', '$timeout',
	   '$log', '_', 'KompanijaResource', 
	   function($scope, $uibModal, $window, $timeout, $log, _, KompanijaResource) {
	
		$scope.createCompany = function() {
			
			KompanijaResource.registrationCompani($scope.kompanija, callBack);
		};
		
		function callBack(success) {
			console.log(success);
			if(success.error == "nisteDodali") {
				$scope.messageKompani = success.error;
				console.log('zauzeto');
			} else if(success.success = 'dodato') {
				$scope.messageAddKompani = 'proslo';
			}
		}
		
		$scope.azuriranjeKompanije = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/update_kompanija.html',
				controller: 'KompanijaUpdatModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	  
		$scope.azuriranjeAdreseKompanije = function(resource) {
			if(!resource){
				resource = {
						drzava: '',
						grad: '',
						ulica: '',
						broj_ulice: '',
						broj_zgrade: '',
						broj_stana: ''
				};
			}
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/adresa_kompanije.html',
				controller: 'KompanijaAdresaModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	  
		

	  KompanijaResource.findMyCompani(callBack);
	  
	  
	}])
	.controller('KompanijaZaposleniCtrl', ['$scope', '$uibModal', '$window', '$timeout',
	   '$log', '_', 'KompanijaZaposleniResource', 
	   function($scope, $uibModal, $window, $timeout, $log, _, KompanijaZaposleniResource) {
	
		KompanijaZaposleniResource.getZaposleni().then(function(items) {
			console.log(items);
			$scope.zaposleni = items;
		})
		
		$scope.deleteZaposlenog = function(zaposlen) {
			console.log(zaposlen.id);
			KompanijaZaposleniResource.deleteZaposleni(zaposlen.id);
			$window.location.replace() = '#/account';
		}
	}])
	.controller('KompanijaOglasiCtrl', ['$scope', '$uibModal', '$window', '$timeout',
	   '$log', '_', 'KompanijaZaposleniResource', 
	   function($scope, $uibModal, $window, $timeout, $log, _, KompanijaZaposleniResource) {
			
		KompanijaZaposleniResource.getAllAdvertisment().then(function(items) {
			$scope.oglasi = items;
			console.log('ne znam sta cu '+JSON.stringify(items))
		})
		
	}])
	.controller('KompanijaUpdatModalCtrl', ['$scope', '$uibModalInstance', '$window', '$log', '_', 'KompanijaResource', 
	    function($scope, $uibModalInstance, $window, $log, _, KompanijaResource) {
		
		
		$scope.ok = function() {
			KompanijaResource.upradeKompanija($scope.korisnik.zaposleniDTO[0].kompanijaDTO);
			$uibModalInstance.close('ok');
		};
		
	    $scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	     };
	}])
	.controller('KompanijaAdresaModalCtrl', ['$scope', '$uibModalInstance', '$window', '$log', '_', 'KompanijaResource', 
	    function($scope, $uibModalInstance, $window, $log, _, KompanijaResource) {
		
		
		$scope.ok = function() {
			//console.log($scope.korisnik.zaposleniDTO[0].kompanijaDTO.id);
			KompanijaResource.updatAdresCompany($scope.korisnik.zaposleniDTO[0].kompanijaDTO.adresaDTO, $scope.korisnik.zaposleniDTO[0].kompanijaDTO.id);
			$uibModalInstance.close('ok');
		};
		
	    $scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	     };
	}])