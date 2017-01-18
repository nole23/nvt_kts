/**
 * 
 */

(function () {
    angular
        .module('nekretnineClientApp')
        .factory('LoginResources', Service);

    function Service($http, $localStorage, $log, $window) {
        var service = {};

        service.login = login;
        service.logout = logout;
        service.getCurrentUser = getCurrentUser;

        return service;

        function login(ime, sifra, callback) {
        	var korisnik = {
					username: ime,
					password: sifra
			};
            $http.post('http://localhost:8080/api/users/login/', korisnik)
                .success(function (response) {
                    // ukoliko postoji token, prijava je uspecna
                	console.log(response);
                    if (response.cookies) {
                        // korisnicko ime, token i rola (ako postoji) cuvaju se u lokalnom skladištu
                        var currentUser = { username: username, token: response.cookies }
                        //var tokenPayload = jwtHelper.decodeToken(response.token);
                        //if(tokenPayload.role){
                        currentUser.role = response.rola;
                        //}
                        // prijavljenog korisnika cuva u lokalnom skladistu
                        $localStorage.currentUser = currentUser;
                        // jwt token dodajemo u to auth header za sve $http zahteve
                        $http.defaults.headers.common.Authorization = response.cookies;
                        // callback za uspesan login
                        callback(response);
                        //$state.go('main');
                    } else {
                        // callback za neuspesan login
                    	console.log(response.error);
                        callback(response);
                    }
                });
        }

        function logout() {
            // uklonimo korisnika iz lokalnog skladišta
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
            $window.location = '#/login';
        }

        function getCurrentUser() {
            return $localStorage.currentUser;
        }
    }
})();