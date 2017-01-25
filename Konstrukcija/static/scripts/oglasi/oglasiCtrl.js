/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('OglasProdajaCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'OglasiResource', 
	   function($scope, $uibModal, $routeParams, $log, _, OglasiResource) {
		
		$scope.tip = "prodaja";
		$scope.name = $routeParams.name 
		
		OglasiResource.getAllObjavljene().then(function(items) {
			$scope.prodaja = items;
		})
		
	}])
	
	.controller('OglasIzdavanjeaCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'OglasiResource', 
	   function($scope, $uibModal, $routeParams, $log, _, OglasiResource) {
		
		$scope.tip = "izdavanje";
		$scope.name = $routeParams.name 
		
		OglasiResource.getAllObjavljene().then(function(items) {
			$scope.izdavanje = items;
		})
		
	}])