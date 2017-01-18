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
        'ngCookies',
        'ngStorage',
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
                controllerAs: 'prodaja',
                controllerAs1: 'kategorije'
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
            .when('/account/:idKorisnik', {
            	templateUrl: 'views/account.html',
            	controller: 'AcauntCtrl',
            	controllerAs: 'jedanKorisnik',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                            $location.path('/login');
                        };
                    }
                }
            	
            	//controller: 'AccauntCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
        
    }])
    
    .run(['Restangular', '$log', '$rootScope', '$http', '$location', '$localStorage', 'LoginResources', function(Restangular, $log, $rootScope, $http, $location, $localStorage, LoginResources) {
        Restangular.setBaseUrl("api");
        Restangular.setErrorInterceptor(function(response) {
            if (response.status === 500) {
                $log.info("internal server error");
                return true; // greska je obradjena
            }
            return true; // greska nije obradjena
        });
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.token;
        }
        $rootScope.logout = function () {
        	LoginResources.logout();
        }
        $rootScope.getCurrentUserRole = function () {
            if (!LoginResources.getCurrentUser()){
              return undefined;
            }
            else{
              return LoginResources.getCurrentUser().role;
            }
        }
        $rootScope.isLoggedIn = function () {
            if (LoginResources.getCurrentUser()){
              return true;
            }
            else{
              return false;
            }
        }
        $rootScope.getCurrentState = function () {
          return $state.current.name;
        }
        
        
    }]);
