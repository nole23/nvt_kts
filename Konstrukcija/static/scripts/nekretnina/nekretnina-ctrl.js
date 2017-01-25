/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('NekretnineCtrl', ['$scope', '$uibModal', '$timeout', '$routeParams',
	   '$log', '_', 'NekretnineResource', 
	   function($scope, $uibModal, $timeout, $routeParams, $log, _, NekretnineResource) {
		
		$scope.nekretnina = {};
		
		$scope.getIn=function(id){
		    
		    NekretnineResource.getObjave().then(function(items) {
				$scope.objave = items;
				
				window.location = "#/nekretnine/prodaja/"+id;
		    })
		 }
		
		$scope.addNekretnina = function() {
			console.log('as '+JSON.stringify($scope.nekretnina));
		}
		
		$scope.order_id = $routeParams.idNekretnina;
		
		/**
		 * Ispis svih nekretnina
		 */
		NekretnineResource.getNekretnina($routeParams.idNekretnina).then(function(items) {
			$scope.jedNekretnina = items;
		})
		
		/**
		 * Prijavljivanje nevalidnog oglasa
		 */
		$scope.prijava = function(id) {
			NekretnineResource.prijavaOglasa(id).then(function(success) {
				if(success.success == 'prijavljen') {
					$scope.messageOglasNevalidan = true;
					$timeout(function(){
				        $scope.messageOglasNevalidan = false;
				    }, 8000);
				} 
					
			});
		}
		
		/**
		 * Dodati poruku za datu nekretninu
		 */
		$scope.poruka = function(resource) {
			if(!resource){
				resource = {
						poruka: ''
				};
			}
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/message.html',
				controller: 'MessageModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
	}])
	
	.controller('MessageModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'NekretnineResource', 
	    function($scope, $uibModalInstance, resource, $log, _, NekretnineResource) {
		
		$scope.resource = resource;
		console.log($scope.jedNekretnina);
		
		$scope.ok = function() {
			/*NekretnineResource.sendMessage($scope.resource);
	          $uibModalInstance.close('ok');
	        };*/
		};
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
		
		
	}])