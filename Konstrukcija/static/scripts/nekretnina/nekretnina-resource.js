/**
 * 
 */

angular.module('nekretnineClientApp')
    .factory('NekretnineResource', ['Restangular', '_', function(Restangular, _) {
	'use strict';

	var objave = [];
	var retVal = {};

	retVal.getObjave = function() {
		return Restangular.all("kategorija/all").getList().then(function(entries) {
			objave = entries;
			return objave;
		});
	};
	/*
	retVal.getSveNekretnine = function(id) {
		console.log('proba dva '+id);
		return Restangular.all("kategorija/nekretnina/"+id).getList().then(function(entries) {
			nekretnina = entries;
			
			return nekretnina;
		});
		return null;
	}
	*/
	return retVal;
    }]);
