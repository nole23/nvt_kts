'use strict';

/**
 * @ngdoc overview
 * @name studentsClientApp
 * @description
 * # studentsClientApp
 *
 * Main module of the application.
 */
angular
    .module('nekretnineClientApp', [
        'ngResource',
        'ngRoute',
        'restangular',
        'ui.bootstrap',
        'lodash'
    ])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'KategorijeCtrl',
                controllerAs: 'kategorije',
                controllerAS3: 'nekretnine'
            })
            .when('/registration', {
                templateUrl: 'views/registracija.html',
                    controller: 'KorisnikCtrl',
                    controllerAs1: 'registrovani',
                    controllerAs: 'nekretnine'
            })
            .otherwise({
                redirectTo: '/'
            });
    }])

    .run(['Restangular', '$log', function(Restangular, $log) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
    }]);
