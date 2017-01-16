/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KorisniciResource', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var registrovani = [];
		var korisnici = [];
		var nekretnine = [];
		var retVal = {};
		
		retVal.getKorisnici = function() {
			return Restangular.all("users/all").getList().then(function(entries) {
				registrovani = entries;
				return registrovani;
			});
		};
		
		retVal.getNekretnine = function() {
			return Restangular.all("oglas/prodaja/all").getList().then(function(entries) {
				nekretnine = entries;
				return nekretnine;
			});
		};
		
		retVal.saveNewKorisnik = function(korisnik) {
			return Restangular.all('users/registration/korisnik').post(angular.toJson(korisnik));
		}
		
		return retVal;
		
	}]);