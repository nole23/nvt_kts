/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('NekretnineCtrl', ['$scope', '$uibModal',
	   '$log', '_', 'NekretnineResource', 
	   function($scope, $uibModal, $log, _, NekretnineResource) {
		
		$scope.getIn=function(id){
		    console.log('proba '+id);
		    NekretnineResource.getObjave().then(function(items) {
				$scope.objave = items;
				
		    })
		 
		 };
		 
		
	}])