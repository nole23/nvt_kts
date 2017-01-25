/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('AddEstatCtrl', ['$scope', '$uibModal', '$window', '$routeParams',
	   '$log', '_', 'RegistrationResouce', 
	   function($scope, $uibModal, $window, $routeParams, $log, _, RegistrationResouce) {
		
		$scope.nekretnina = {};
		
		$scope.addNekretnina = function() {
			console.log('as '+JSON.stringify($scope.nekretnina));
			var re = /[0-9]/;
			if(!$scope.nekretnina.tip){
				$scope.messageAddTip = true;
				console.log('nevalja tip');
				return false;
			
			}else if($scope.nekretnina.povrsina != null) {
				if(!re.test($scope.nekretnina.povrsina)){
					$scope.messageAddPovrsina = true;
					console.log('nevalja povrsina');
					return false;
				}
			}else if($scope.nekretnina.spratova != null) {
				if(!re.test($scope.nekretnina.spratova)){
					$scope.messageAddSpratova = true;
					console.log('nevalja spratova');
					return false;
				}
			}else if($scope.nekretnina.sprat != null) {
				if(!re.test($scope.nekretnina.sprat)){
					$scope.messageAddSprat = true;
					console.log('nevalja sprat');
					return false;
				}
			}else if(!re.test($scope.nekretnina.cena)) {
				$scope.messageAddCena = true;
				console.log('nevalja cena');
				return false;
			}else if(!$scope.nekretnina.naziv_nekretnine){
				$scope.messageAddNaziv = true;
				console.log('nevalja naziv');
				return false;
			} 
			var idKategorija = $scope.nekretnina.tip;
			var idKompanija = $scope.nekretnina.komapnija;
			
			if(!$scope.nekretnina.komapnija) {
				
				if($routeParams.idKompanija == null) {
					var idKompanija = null;
					var idKorisnik = "nesto";
					RegistrationResouce.addNewEstate(idKategorija, $scope.nekretnina, idKompanija, idKorisnik, callBack);
				} else {
					var idKompanija = $routeParams.idKompanija;
					var idKorisnik = null;
					RegistrationResouce.addNewEstate(idKategorija, $scope.nekretnina, idKompanija, idKorisnik, callBack);
				}
			}
		}
		
		function callBack(success) {
			if(success.error != null) {
				$scope.messageErrorAddEstate = true;
				return true;
			} else if(success.error == null) {
				$window.location = '#/add/estate/equipment/'+success.id;
			}
		}

		
	}])
	
	.controller('AddEquipmentCtrl', ['$scope', '$uibModal', '$window', '$routeParams',
	   '$log', '_', 'RegistrationResouce', 
	   function($scope, $uibModal, $window, $routeParams, $log, _, RegistrationResouce) {
		
		console.log($routeParams.id);
		
		var opremljenost = {};
		
		var nek = {
				id: $routeParams.id,
				naziv: $routeParams.naziv
		};
		var id = ""+$routeParams.id;
		$scope.tehnickaOpremljenost = nek;
		
		$scope.addOpremljenost = function() {
			RegistrationResouce.addOpremljenost($scope.opremljenost, id, callBack);
		}
		
		function callBack(success) {
			if(success.error != null) {
				$scope.messageErrorAddOpremljenost = true;
				return true;
			} else if (success.error == null) {
				$window.location = '#/add/estate/location/'+nek.id;
			}
		}
	}])
	.controller('AddLocationEstateCtrl', ['$scope', '$uibModal', '$window', '$routeParams',
	   '$log', '_', 'RegistrationResouce', 
	   function($scope, $uibModal, $window, $routeParams, $log, _, RegistrationResouce) {
		
		console.log($routeParams.id);
		
		var opremljenost = {};
		
		var nek = {
				id: $routeParams.id,
				naziv: $routeParams.naziv
		};
		
		var id = ""+$routeParams.id;
		$scope.tehnickaOpremljenost = nek;
		
		$scope.addLocationEstate = function() {
			if($scope.location != null) {
				RegistrationResouce.addOLocation($scope.location, id, callBack);
			} else {
				var resource = {
						error: null
				}
				callBack(resource);
			}
			
			
		}
		
		function callBack(success) {
			if(success.error != null) {
				$scope.messageErrorAddOpremljenost = true;
				return true;
			} else if (success.error == null) {
				$window.location = '#/add/estate/publish/'+nek.id;
			}
		}
	}])
	.controller('PublishEstateCtrl', ['$scope', '$uibModal', '$window', '$routeParams',
	   '$log', '_', 'RegistrationResouce', 
	   function($scope, $uibModal, $window, $routeParams, $log, _, RegistrationResouce) {
		
		console.log($routeParams.id);
		
		var opremljenost = {};
		
		var nek = {
				id: $routeParams.id,
				naziv: $routeParams.naziv
		};
		
		var id = ""+$routeParams.id;
		$scope.tehnickaOpremljenost = nek;
		
		$scope.publishEstate = function() {
			RegistrationResouce.publishEstate(id, $scope.datum, callBack);
		}
		
		function callBack(success) {
			if(success.error != null) {
				$scope.messageErrorObjava = true;
				return true;
			} else if (success.error == null) {
				$window.location = '#/prodaja/nekretnina/'+nek.id;
			}
		}
		
	}])
	