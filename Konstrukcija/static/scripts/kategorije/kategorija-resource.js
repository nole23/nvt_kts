/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KategorijeResource', ['Restangular', '_', function(Restangular, _) {
		'use stict';
		
		var kategorije = [];
		var retVal = {};
		
		retVal.getAllKategorija = function() {
			return Restangular.all("kategorija/all").getList().then(function(entries) {
				kategorije = entries;
				return kategorije;
			});
		};
		
		return retVal;
		
	}]);