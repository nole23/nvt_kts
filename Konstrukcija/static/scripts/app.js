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
                controllerAS1: 'nekretnine'
            })
            .when('/registration', {
                templateUrl: 'views/registracija.html',
                    controller: 'KorisnikCtrl',
                    controllerAs1: 'registrovani',
                    controllerAs: 'nekretnine'
            })
            .when('/prodaja', {
                templateUrl: 'views/prodaja.html',
                controller: 'OglasiCtrl',
                controllerAs: 'prodaja'
            })
            .when('/prodaja/nekretnina/:idNekretnina', {
            	templateUrl: 'views/nekretnine.html',
            	controller: 'NekretnineCtrl',
            	controllerAs: 'jedNekretnina'
            })
            .when('/izdavanje', {
            	
                templateUrl: 'views/izdavanje.html',
                controller: 'OglasiCtrl',
                controllerAs: 'izdavanje'
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl',
                controllerAs: 'registrovan'
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
