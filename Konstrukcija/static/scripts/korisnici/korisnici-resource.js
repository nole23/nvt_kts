/**
 * 
 */

angular.module('nekretnineClientApp')
	.factory('KorisniciResource', ['Restangular', '_', function(Restangular, _) {
		'use strict';
		
		var registrovani = [];
		var korisnici = [];
		var korisnik = [];
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
			return Restangular.all('users/updat/info').post(korisnik1);
			
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
		return retVal;
		
	}]);