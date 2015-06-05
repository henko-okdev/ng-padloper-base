'use strict';

var appmodule = angular.module('ng-call-center.service.contacts', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.factory('ContactsService', function(AbstractService) {
    var baseUrl = '/rest/contacts';

    var fetch = function(id) {
        return AbstractService.fetch(id, baseUrl);
    };

    var find = function() {
        return AbstractService.find(baseUrl);
    };

    var create = function(contact) {
        var params = {
            'firstName' : contact.firstName,
            'lastName' : contact.lastName,
            'phone' : contact.phone,
            'email' : contact.email,
            'city' : contact.city
        };

        return AbstractService.create(params, baseUrl);
    };

    var update = function(contact) {
        var params = {
            'id' : contact.id,
            'firstName' : contact.firstName,
            'lastName' : contact.lastName,
            'phone' : contact.phone,
            'email' : contact.email,
            'city' : contact.city
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