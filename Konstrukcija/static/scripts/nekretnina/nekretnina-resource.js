/**
 * 
 */

angular.module('nekretnineClientApp')
    .factory('NekretnineResource', ['Restangular', '_', function(Restangular, _) {
	'use strict';

	var objave = [];
	var jedNekretnina = [];
	var retVal = {};
	
	retVal.getObjave = function() {
		return Restangular.all("kategorija/all").getList().then(function(entries) {
			objave = entries;
			console.log('proba1111 '+objave.length);
			return objave;
		});
	};
	
	retVal.getSveNekretnine = function(id) {
		console.log('proba dva '+id);
		return Restangular.all("kategorija/nekretnina/"+id).getList().then(function(entries) {
			nekretnina = entries;
			
			return nekretnina;
		});
		return null;
	}
	
	retVal.getNekretnina = function(id) {
		console.log("da li je dosao id "+id);
		return Restangular.one("oglas/nekretnina/"+id).get().then(function(item) {
			jedNekretnina = item;
			return jedNekretnina;
		});
	}
	
	return retVal;
    }]);
