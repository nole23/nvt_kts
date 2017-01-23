/**
 * 
 */

(function () {
    angular
        .module('nekretnineClientApp')
        .factory('RegistrationResouce', Service);

    function Service($http, $localStorage, $log, $window) {
        var service = {};

        service.saveNewKorisnik = saveNewKorisnik;
        service.addNewEstate = addNewEstate;
        service.addOpremljenost = addOpremljenost;
        service.addOLocation = addOLocation;
        service.publishEstate = publishEstate;

        return service;

        function saveNewKorisnik(korisnik, callback) {

            $http.post('http://localhost:8080/api/users/registration/korisnik', korisnik)
                .success(function (response) {
                    callback(response)
                });
        }
        
        function addNewEstate(idKategorija, nekretnina, idKompanija, idKorisnik, callBack) {
        	var link = 'http://localhost:8080/api/nekretnineee/add/'+idKategorija+'/'+idKorisnik+'/'+idKompanija;
        	$http.post(link, nekretnina).success(function (response) {
        		callBack(response);
        	});
        }
        
        function addOpremljenost(opremljenost, id, callBack) {
        	var link = 'http://localhost:8080/api/nekretnineee/tehnicka/opremljenost/'+id;
        	$http.post(link, opremljenost).success(function (response) {
        		callBack(response);
        	})
        }
        
        function addOLocation(location, id, callBack) {
        	var link = 'http://localhost:8080/api/nekretnineee/lokacija/'+id;
        	$http.post(link, location).success(function (response) {
        		callBack(response);
        	})
        }
        
        function publishEstate(id, datum, callBack) {
			var link = 'http://localhost:8080/api/oglas/add/'+id;
			$http.post(link, datum).success(function (response) {
        		callBack(response);
        	})
		}
    }
})();