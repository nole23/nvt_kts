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
        

        return service;

        function saveNewKorisnik(korisnik, callback) {

            $http.post('http://localhost:8080/api/users/registration/korisnik', korisnik)
                .success(function (response) {
                    callback(response)
                });
        }
    }
})();