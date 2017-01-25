/**
 * 
 */

(function () {
    angular
        .module('nekretnineClientApp')
        .factory('KompanijaResource', Service);

    function Service($http, $localStorage, $log, $window) {
        var service = {};
        
        service.registrationCompani = registrationCompani;
        service.findMyCompani = findMyCompani;
        service.upradeKompanija = upradeKompanija;
        service.updatAdresCompany = updatAdresCompany;

        return service;

        function registrationCompani(komoanija, callback) {
        	
            $http.post('http://localhost:8080/api/kompanija/add/kompanija', komoanija)
                .success(function (response) {
                   
                	callback(response);
                }, 100)
                .error(function(status) {
                	
                	callback(status);
			    });
        }
        
        function findMyCompani(callback) {
        	
        	$http.get('http://localhost:8080/api/kompanija/employed/company').success(function(response) {
				callback(response);
			});
        }
        
        function upradeKompanija(kompanija) {
			$http.post('http://localhost:8080/api/kompanija/').success(function(response) {
				console.log(response);
			})
		}
        
        function updatAdresCompany(addresa, id) {
        	var link = 'http://localhost:8080/api/kompanija/kompanija/'+id+'/adresa';
			$http.post(link, addresa).success(function(response) {
				console.log(response);
			})
		}
    }
})();