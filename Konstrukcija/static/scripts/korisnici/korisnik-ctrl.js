'use strict';

angular.module('nekretnineClientApp')
	.controller('AcauntCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'KorisniciResource', 
	   function($scope, $uibModal, $routeParams, $log, _, KorisniciResource) {
		

		
		$scope.order_id = $routeParams.idKorisnik;
		console.log('jbg '+$routeParams.idKorisnik);
		KorisniciResource.getKorisnik($routeParams.idKorisnik).then(function(items) {
			$scope.korisnik = items;
			console.log(items);
		})
		
		$scope.azuriranjeAdrese = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/adres.html',
				controller: 'AdresModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
		$scope.dodatiAdrese = function() {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/adresAdd.html',
				controller: 'AdresAddModalCtrl',
				scope: $scope
			});
		}
		
		$scope.azuriranjeLicnih = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/account.html',
				controller: 'KorisnikModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
		$scope.azuriranjeEmaila = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/email.html',
				controller: 'EmailModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
		$scope.azuriranjeSifre = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/sifre.html',
				controller: 'SifraModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	
		
	}])
	
	.controller('KorisnikModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, resource, $log, _, KorisniciResource) {
		
		$scope.resource = resource;
		console.log($scope.resource.username);
		
		
		$scope.ok = function() {
			KorisniciResource.updatUsers($scope.resource);
	          $uibModalInstance.close('ok');
	        };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
		
		
	}])
	
	.controller('AdresModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, resource, $log, _, KorisniciResource) {
		
		$scope.resource = resource;
		console.log($scope.resource.username);
		
		$scope.ok = function() {
			KorisniciResource.updateAdress($scope.resource);
	          $uibModalInstance.close('ok');
	        };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
		
		
	}])
	
	.controller('AdresAddModalCtrl', ['$scope', '$uibModalInstance', '$window', '$log', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, $window, $log, _, KorisniciResource) {
		$scope.ok = function() {
			//$scope.adres = {};
			console.log($scope.adres);
			KorisniciResource.updateAdress($scope.adres);
			alert('Uspesno ste azurirali podatke');
			window.location = "#/account/"+$scope.adres.drzava;
	    };
		
	}])
	
	.controller('EmailModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$rootScope', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, resource, $rootScope, _, KorisniciResource) {
		
		$scope.resource = resource;
		
		$scope.ok = function() {
			KorisniciResource.updateEmail($scope.resource);
	          $uibModalInstance.close('ok');
	          alert('Proverite vas email')
	          $scope.message = "proverite vas email kako bi verifikovali email"
	          $rootScope.logout();
	     };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	     };
		
		
	}])
	
	.controller('SifraModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$rootScope', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, resource, $rootScope, _, KorisniciResource) {
		
		$scope.resource = resource;
		
		$scope.ok = function() {
			KorisniciResource.updatePassword($scope.resource);
	          $uibModalInstance.close('ok');
	          alert('Proverite vas email')
	          $scope.message = "proverite vas email kako bi verifikovali email"
	          $rootScope.logout();
	     };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	     };
		
		
	}])