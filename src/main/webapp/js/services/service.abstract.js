'use strict';

var appmodule = angular.module('ng-call-center.service.abstract', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.factory('AbstractService', function ($http, $q) {

    var fetch = function(id, baseUrl) {
        var deferred = $q.defer();
        var url = baseUrl + '/fetch/' + id;

        $http.get(url).success(deferred.resolve).error(deferred.reject);

        return deferred.promise;
    };

    var find = function(baseUrl) {
        var deferred = $q.defer();
        var url = baseUrl + '/find';

        $http.get(url).success(deferred.resolve).error(deferred.reject);

        return deferred.promise;
    };

    var create = function(params, baseUrl) {
        var deferred = $q.defer();
        var url = baseUrl + '/create';

        $http.post(url, params).success(deferred.resolve).error(deferred.reject);

        return deferred.promise;
    };

    var update = function(params, baseUrl) {
        var deferred = $q.defer();
        var url = baseUrl + '/update';

        $http.put(url, params).success(deferred.resolve).error(deferred.reject);

        return deferred.promise;
    };

    var remove = function(id, baseUrl) {
        console.log(id);
        var deferred = $q.defer();
        var url = baseUrl + '/delete/' + id;

        $http.delete(url).success(deferred.resolve).error(deferred.reject);

        return deferred.promise;
    };

    return {
        create: create,
        fetch: fetch,
        find: find,
        update: update,
        remove: remove
    }

});