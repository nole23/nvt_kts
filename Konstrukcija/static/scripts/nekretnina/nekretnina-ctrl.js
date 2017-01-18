/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('NekretnineCtrl', ['$scope', '$uibModal', '$routeParams',
	   '$log', '_', 'NekretnineResource', 
	   function($scope, $uibModal, $routeParams, $log, _, NekretnineResource) {
		
		$scope.getIn=function(id){
		    
		    NekretnineResource.getObjave().then(function(items) {
				$scope.objave = items;
				
				window.location = "#/nekretnine/prodaja/"+id;
		    })
		 }
		
		$scope.order_id = $routeParams.idNekretnina;
		console.log('jbg '+$routeParams.idNekretnina);
		NekretnineResource.getNekretnina($routeParams.idNekretnina).then(function(items) {
			$scope.jedNekretnina = items;
			console.log('test sa necim '+items.nekretninaDTO.objavioDTO[0].korisnikDTO.fname);
		})
	
		
	}])