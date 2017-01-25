/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KorisniciResource', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var registrovani = [];
		var oglasi = [];
		var korisnici = [];
		var korisnik = [];
		var nekretnine = [];
		var users = [];
		var poruka = [];
		var retVal = {};
		
		retVal.getKorisnici = function() {
			return Restangular.all("users/all").getList().then(function(entries) {
				registrovani = entries;
				return registrovani;
			});
		};
		
		retVal.getObjava = function() {
			return Restangular.all("others/oglasi/korisnika").getList().then(function(entries) {
				oglasi = entries;
				return oglasi;
			});
		};
		
		retVal.getNekretnine = function() {
			return Restangular.all("oglas/prodaja/all").getList().then(function(entries) {
				nekretnine = entries;
				return nekretnine;
			});
		};
		
		retVal.saveNewKorisnik = function(korisnik, callback) {
			return Restangular.all('users/registration/korisnik').post(angular.toJson(korisnik)).then(function(response) {
				callback(response);
			});
		};
		
		//treba prvo implementirati tamo ovu funkciju
		retVal.getKorisnik = function(token) {
			return Restangular.one('users/profile').get().then(function(entries) {
				korisnik = entries;
				
				return korisnik;
			})
		};
		
		retVal.updatUsers = function(korisnik) {
			console.log(korisnik.username);
			var korisnik1 = {
					fname: korisnik.fname,
					lname: korisnik.lname,
					username: korisnik.username
			}
			return Restangular.all('lokacija/update/users').post(korisnik1);
			
		};
		
		retVal.updateAdress = function(adresa) {
			return Restangular.all('users/adresa').post(adresa);
		}
		
		retVal.updateEmail = function(email) {
			return Restangular.all('users/updat/email').post(email);
		}
		
		retVal.updatePassword = function(pass) {
			return Restangular.all('users/updat/password').post(pass);
		}
		
		retVal.getAllUsers = function() {
			return Restangular.all('users/all/users').getList().then(function(entries) {
				users = entries;
				return users;
			});
		}
		
		retVal.addZaposlenog = function(id, callBack) {
			var link = 'kompanija/kompanija/'+id;
			return Restangular.one(link).get().then(function(success) {
				//console.log(success.success);
				callBack(success)
			});
		}
		
		
		
		retVal.addAdmina = function(id, callBack) {
			var link = 'admin/add/admin/'+id;
			return Restangular.one(link).get().then(function(success) {
				//console.log(success.success);
				callBack(success)
			});
		}
		
		retVal.getAllNevalidni = function() {
			return Restangular.all('admin/prijavljena/nekretnina').getList().then(function(entries) {
				users = entries;
				return users;
			});
		}
		
		return retVal;
		
	}]);