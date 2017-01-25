/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KategorijeResource', ['Restangular', '_', function(Restangular, _) {
		'use stict';
		
		var kategorije = [];
		var nekretnine = [];
		var poruka = [];
		var retVal = {};
		
		retVal.getAllKategorija = function() {
			return Restangular.all("others/kategorija/all").getList().then(function(entries) {
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
		
		retVal.getAddKategorija = function(kategorija) {
			return Restangular.all('admin/add/kategorija').post(kategorija).then(function(success) {
				poruka = success;
				return poruka;
			})
		}
		
		return retVal;
		
	}]);