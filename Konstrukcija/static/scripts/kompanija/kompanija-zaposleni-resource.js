/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KompanijaZaposleniResource', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var retVal = {};
		var zaposleni = [];
		var oglasi = [];
		
		retVal.getZaposleni = function() {
			return Restangular.all("kompanija/company/users/1").getList().then(function(entries) {
				zaposleni = entries;
				return zaposleni;
			});
		}
		
		retVal.deleteZaposleni = function(id) {
			return Restangular.all("kompanija/delete/users/"+id).remove();
		}
		
		retVal.getAllAdvertisment = function() {
			return Restangular.all("oglas/all").getList().then(function(entries) {
				oglasi = entries;
				return oglasi;
			});
		}
		
		return retVal;
		
	}]);