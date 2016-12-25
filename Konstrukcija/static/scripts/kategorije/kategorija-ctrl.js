/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KategorijeCtrl', ['$scope', '$uibModal',
	   '$log', '_', 'KategorijeResource', 
	   function($scope, $uibModal, $log, _, KategorijeResource) {
		
		KategorijeResource.getAllKategorija().then(function(items) {
			$scope.kategorije = items;
		})
		
		KategorijeResource.getNekretnine().then(function(items) {
			$scope.nekretnine = items;
		})
		
	}])