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
            .when('/prodaja/:name', {
                templateUrl: 'views/kategorija_prikaz.html',
                controller: 'OglasProdajaCtrl',
                controllerAs: 'prodaja'
            })
            .when('/izdavanje/:name', {
                templateUrl: 'views/kategorija_izdavanje.html',
                controller: 'OglasIzdavanjeaCtrl',
                controllerAs: 'prodaja'
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
            .when('/account', {
            	templateUrl: 'views/menu_users.html',
            	controller: 'AcauntCtrl',
            	controllerAs: 'korisnik',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                            $location.path('/login');
                        };
                    }
                }
            })
            .when('/add/estate', {
            	templateUrl: 'views/add_estate.html',
            	controller: 'KategorijeCtrl',
                controllerAs: 'kategorije',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                        	alert('prvo se ulogujte');
                        	$localStorage.message = 'ulogij'
                            $location.path('/login');
                        };
                    }
                }
            })
            .when('/add/estate/company/:idKompanija', {
            	templateUrl: 'views/add_estate.html',
            	controller: 'KategorijeCtrl',
                controllerAs: 'kategorije',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                        	alert('prvo se ulogujte');
                        	$localStorage.message = 'ulogij'
                            $location.path('/login');
                        };
                    }
                }
            })
            .when('/add/estate/equipment/:id', {
            	templateUrl: 'views/estate_opremljenost.html',
            	controller: 'AddEquipmentCtrl',
                controllerAs: 'opremljenost',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                        	alert('prvo se ulogujte');
                        	$localStorage.message = 'ulogij'
                            $location.path('/login');
                        };
                    }
                }
            })
            .when('/add/estate/location/:id', {
            	templateUrl: 'views/location_estate.html',
            	controller: 'AddLocationEstateCtrl',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                        	alert('prvo se ulogujte');
                        	$localStorage.message = 'ulogij'
                            $location.path('/login');
                        };
                    }
                }
            })
            .when('/add/estate/publish/:id', {
            	templateUrl: 'views/publish_estate.html',
            	controller: 'PublishEstateCtrl',
            	resolve: {
                    app: function($q, $localStorage, $location) {
                        
                        if ($localStorage.currentUser == null) {
                        	alert('prvo se ulogujte');
                        	$localStorage.message = 'ulogij'
                            $location.path('/login');
                        };
                    }
                }
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
              return LoginResources.getCurrentUser().rola;
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
