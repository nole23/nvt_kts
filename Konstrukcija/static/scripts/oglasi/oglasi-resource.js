/**
 * Metoda koja se vezuje na server na odredjen controler i vraca podatke koji su nam potrebni
 * 
 */

angular.module('nekretnineClientApp')
	.factory('OglasiResource', ['Restangular', '_', function(Restangular, _) {
		'use stict';
		
		var oglasi = [];
		var nekretnine = [];
		var prodaja = [];
		var nekretnina = [];
		var retVal = {};
		
		retVal.getOglasi = function() {
			return Restangular.all("users/all").getList().then(function(entries) {
				oglasi = entries;
				return oglasi;
			});
		};
		
		retVal.getAllObjavljene = function() {
			return Restangular.all("oglas/prodaja/all").getList().then(function(entries) {
				prodaja = entries;
				return prodaja;
			});
		};
		
		
		return retVal;
		
	}]);