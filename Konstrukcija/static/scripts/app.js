/**
 * @ngdoc overview
 * @name pocetnaStranica
 * 
 * Glavni modul aplikacije
 */

angular
	.module('nekretninaClientApp', [
	    'ngResource',
	    'ngRoute',
	    'restangular',
	    'ui.bootstrap',
	    'lodash'
	])
	
	.run(['Restangular', '$log', function(Restangular, $log) {
		Restangular.setBaseUrl("api");
		Restangular.setErrorInterceptor(function(response) {
			if (response.status === 500 ) {
				$log.info("inrerval server error!");
				return true;
			}
			return true;
		});
	}]);