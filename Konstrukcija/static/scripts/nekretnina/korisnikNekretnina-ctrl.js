/**
 * 
 */

'use strict';

angular.module('nekretnineClientApp')
	.controller('KorisnikNekretninaCtrl', ['$scope', '$uibModal', '$window', '$routeParams',
	   '$log', '_', 'NekretnineResource', 
	   function($scope, $uibModal, $window, $routeParams, $log, _, NekretnineResource) {
		
		NekretnineResource.getUserEstate().then(function(items) {
			if(items[0] == null) {
				$scope.poruka = false;
			} else {
				$scope.nekretnineKorisnika = items;
				$scope.poruka = true;
				
			}
			
		})
		
		$scope.azuriranje = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/update_gps.html',
				controller: 'GPSUpdatModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
		$scope.tehnickaOpremljenost = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/update_opremljenost.html',
				controller: 'OpremljenostUpdatModalCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
		
		
		
	}])
	
	.controller('GPSUpdatModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'KorisniciResource', 
	    function($scope, $uibModalInstance, resource, $log, _, NekretnineResource) {
		
		$scope.resource = resource;
		console.log($scope.resource);
		
		
		$scope.ok = function() {
			NekretnineResource.updatGPS($scope.resource);
	          $uibModalInstance.close('ok');
	        };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
		
		
	}])
	.controller('OpremljenostUpdatModalCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'NekretnineResource', 
	    function($scope, $uibModalInstance, resource, $log, _, NekretnineResource) {
		
		$scope.resource = resource;
		
		$scope.ok = function() {

			NekretnineResource.updateTehnicka($scope.tehnickaOpremljenostDTO, $scope.resource.id);
			  $scope.messageUpdatTehnicka = true;
	          $uibModalInstance.close('ok');
	        };
		
		$scope.cancel = function() {
	          $uibModalInstance.dismiss('cancel');
	        };
		
		
	}])