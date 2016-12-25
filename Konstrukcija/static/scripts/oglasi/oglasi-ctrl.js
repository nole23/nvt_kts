/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('OglasiCtrl', ['$scope', '$uibModal',
	   '$log', '_', 'OglasiResource', 
	   function($scope, $uibModal, $log, _, OglasiResource) {
		
		OglasiResource.getOglasi().then(function(items) {
			$scope.oglasi = items;
		});
		
	}]);