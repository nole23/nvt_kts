/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KorisniciResource', ['Restangular', '_', function(Restangular, _) {
		'use stict';
		
		var registrovani = [];
		var nekretnine = [];
		var retVal = {};
		
		retVal.getKorisnici = function() {
			return Restangular.all("users/all").getList().then(function(entries) {
				registrovani = entries;
				return registrovani;
			});
		};
		
		retVal.getNekretnine = function() {
			return Restangular.all("oglas/all").getList().then(function(entries) {
				nekretnine = entries;
				return nekretnine;
			});
		};
		
		return retVal;
		
	}]);