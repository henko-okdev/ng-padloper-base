'use strict';

var appmodule = angular.module('ng-call-center.service.operators', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.factory('OperatorsService', function(AbstractService) {
    var baseUrl = '/rest/operators';

    var fetch = function(id) {
        return AbstractService.fetch(id, baseUrl);
    };

    var find = function() {
        return AbstractService.find(baseUrl);
    };

    var create = function(operator) {
        console.log(operator);
        var params = {
            'firstName' : operator.firstName,
            'lastName' : operator.lastName,
            'phone' : operator.phone,
            'email' : operator.email
        };

        return AbstractService.create(params, baseUrl);
    };

    var update = function(operator) {
        var params = {
            'id' : operator.id,
            'firstName' : operator.firstName,
            'lastName' : operator.lastName,
            'phone' : operator.phone,
            'email' : operator.email
        };

        return AbstractService.update(params, baseUrl);
    };

    var remove = function(id) {
        return AbstractService.remove(id, baseUrl);
    };

    return {
        create: create,
        fetch: fetch,
        find: find,
        update: update,
        remove: remove
    }
});