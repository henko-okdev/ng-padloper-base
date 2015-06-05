'use strict';

var appmodule = angular.module('ng-call-center.service.donations', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.factory('DonationsService', function(AbstractService) {
    var baseUrl = '/rest/donations';

    var fetch = function(id) {
        return AbstractService.fetch(id, baseUrl);
    };

    var find = function() {
        return AbstractService.find(baseUrl);
    };

    var create = function(donation) {
        var params = {
            'contact' : donation.contact,
            'operator' : donation.operator,
            'amount' : donation.amount,
            'date' : donation.date
        };

        return AbstractService.create(params, baseUrl);
    };

    var update = function(donation) {
        var params = {
            'id' : donation.id,
            'contact' : donation.contact,
            'operator' : donation.operator,
            'amount' : donation.amount,
            'date' : donation.date
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