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
		
		$scope.deleteNekretnina = function(id) {
			OglasiResource.deleteNekretnina(id);
			alert('Obrisana je nekretnina. Ako i dalje postoji u sistemu refresujte stranicu')
			$scope.messageDelete = true;
			window.location.reload() = "#/account"
			
		}
		
		$scope.obnoviti = function(resource) {
			
			var modalInstance = $uibModal.open({
				templateUrl: 'views/modals/obnoviti_oglas.html',
				controller: 'ObnovitiOglasCtrl',
				scope: $scope,
				resolve: {
					resource: function() {
						return resource;
					}
				}
			});
		}
	}])
	.controller('ObnovitiOglasCtrl', ['$scope', '$uibModalInstance', 'resource', '$log', '_', 'RegistrationResouce', 
	    function($scope, $uibModalInstance, resource, $log, _, RegistrationResouce) {
		
		$scope.resource = resource;
		console.log($scope.resource);
		
		
		
		
	}])