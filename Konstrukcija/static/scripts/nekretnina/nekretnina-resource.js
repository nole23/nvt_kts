/**
 * 
 */

angular.module('nekretnineClientApp')
    .factory('NekretnineResource', ['Restangular', '_', function(Restangular, _) {
	'use strict';

	var objave = [];
	var nekretnineKorisnika = [];
	var jedNekretnina = [];
	var retVal = {};
	
	retVal.getObjave = function() {
		return Restangular.all("kategorija/all").getList().then(function(entries) {
			objave = entries;
			console.log('proba1111 '+objave.length);
			return objave;
		});
	};
	
	retVal.getUserEstate = function() {
		return Restangular.all("nekretnineee/all/users").getList().then(function(entries) {
			nekretnineKorisnika = entries;
			return nekretnineKorisnika;
		})
	}
	
	retVal.getSveNekretnine = function(id) {
		console.log('proba dva '+id);
		return Restangular.all("kategorija/nekretnina/"+id).getList().then(function(entries) {
			nekretnina = entries;
			
			return nekretnina;
		});
		return null;
	}
	
	retVal.getNekretnina = function(id) {
		
		return Restangular.one("oglas/nekretnina/"+id).get().then(function(item) {
			jedNekretnina = item;
			return jedNekretnina;
			
		});
	}
	
	
	retVal.updatGPS = function(resource) {
		return Restangular.all("lokacija/gps").post(resource);
	}
	
	retVal.updateTehnicka = function(resource, id) {
		
		return Restangular.all("lokacija/tehnicka/"+id).post(resource);
	}
	
	return retVal;
    }]);
