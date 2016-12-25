/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KategorijeResource', ['Restangular', '_', function(Restangular, _) {
		'use stict';
		
		var kategorije = [];
		var nekretnine = [];
		var retVal = {};
		
		retVal.getAllKategorija = function() {
			return Restangular.all("kategorija/all").getList().then(function(entries) {
				kategorije = entries;
				return kategorije;
			});
		};
		
		retVal.getNekretnine = function() {
			return Restangular.all("nekretnineee/all").getList().then(function(entries) {
				nekretnine = entries;
				return nekretnine;
			});
		};
		
		return retVal;
		
	}]);