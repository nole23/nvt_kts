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
                templateUrl: 'views/main.html'
            })
            .when('/registration', {
                templateUrl: 'views/registracija.html',
                    controller: 'KorisnikCtrl',
                    controllerAs: 'registracija'
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
