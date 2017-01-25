'use strict';

angular.module('nekretnineClientApp')
	.controller('AcauntCtrl', ['$scope', '$uibModal', '$window', '$timeout', '$routeParams', 
	   '$log', '_', 'KorisniciResource', 
	   function($scope, $uibModal, $window, $timeout, $routeParams, $log, _, KorisniciResource) {
		
		var korisnikId = [];
		
		$scope.order_id = $routeParams.idKorisnik;
		KorisniciResource.getKorisnik($routeParams.idKorisnik).then(function(items) {
			$scope.korisnik = items;
			korisnikId = items;
			console.log(items);
		})
		
		$scope.addZaposlenog = function(id) {
			KorisniciResource.addZaposlenog(id, callBack);
		}
		
		$scope.addAdmin = function(id) {
			KorisniciResource.addAdmina(id, callBack);
		}
		
		function callBack(success) {
			if(success.success == 'uspesno') {
				$scope.messageAdd = true;
			    $timeout(function(){
			        $scope.messageAdd = false;
			        $window.location.reload();
			    }, 8000);
				
				
			} else if(success.error == 'neuspenos') {

				$scope.messageNotAdd = true;
			    $timeout(function(){
			        $scope.messageNotAdd = false;
			        $window.location.reload();
			    }, 8000);
				
			}
			
		}
		
		KorisniciResource.getObjava($scope.korisnik).then(function(items) {
			$scope.objava = items;
		})
		
		KorisniciResource.getAllUsers().then(function (items) {
			$scope.users = items;
			
		});
		
		KorisniciResource.getAllNevalidni().then(function (items) {
			$scope.nevalidni = items;
			
		});
		
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
				templateUrl: 'views/modals/mail.html',
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
		
	    $scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
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
			
			//console.log($scope.sifra); 
			
			if($scope.sifra.stara != $scope.sifra.ponovo){
				if(!$scope.sifra.ponovo < 6) {
					if($scope.sifra.nova == $scope.sifra.ponovo) {
						if(!$scope.sifra.nova == '' || !$scope.sifra.ponovo == '') {
							
							var korisnik = {
									password: $scope.sifra.nova
							};
							KorisniciResource.updatePassword(korisnik);
							$uibModalInstance.close('ok');
							alert('Proverite vas email')
							$scope.message = "proverite vas email kako bi verifikovali email"
							$rootScope.logout();
							
						} else {
							$scope.mesagePrazno = true;
						}
					} else {
						$scope.mesageRazliciti = true;
					}
				} else {
					$scope.mesageVelicina = true;
				}
			} else {
				$scope.mesageIsti = true;
			}
			/*
	          */
	     };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	     };
		
		
	}])