'use strict';

var appmodule = angular.module('ng-call-center.service.territories', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.factory('TerritoriesService', function(AbstractService) {
    var baseUrl = '/rest/territories';

    var create = function (territory) {
        var params = {
            'name' : territory.name,
            'type' : territory.type,
            'zip' : territory.zip,
            'parent' : territory.parent
        };

        return AbstractService.create(params, baseUrl);
    };

    var fetch = function(id) {
        return AbstractService.fetch(id, baseUrl);
    };

    var find = function() {
        return AbstractService.find(baseUrl);
    };

    var update = function(territory) {
        var params = {
            'id' : territory.id,
            'name': territory.name,
            'type': territory.type,
            'zip': territory.zip,
            'parent': territory.parent
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