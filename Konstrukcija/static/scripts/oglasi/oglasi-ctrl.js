/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('OglasiCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'OglasiResource', 
	   function($scope, $uibModal, $routeParams, $log, _, OglasiResource) {
		
		OglasiResource.getOglasi().then(function(items) {
			$scope.oglasi = items;
		})

		OglasiResource.getAllObjavljene().then(function(items) {
			$scope.prodaja = items;
		})
		
		$scope.nekretninaJedna = function(id){
			var lokacija = "#/prodaja/nekretnina/"+id;
			window.location = lokacija;
			console.log('da li je dosao '+lokacija);
		}
		
		
	}]);