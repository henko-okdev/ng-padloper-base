'use strict';

var appmodule = angular.module('ng-call-center.controller.contacts', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


appmodule.controller('ContactsListCtr',
    function($scope, $location, ContactsService) {

        $scope.itemsByPage = 5;

        $scope.getContacts = function() {
            ContactsService.find().then(function(result){
                $scope.contacts = (result !== 'null') ? result : {};
                $scope.displayedContacts = [].concat($scope.contacts);
            }, printRequestErrorCause);
        };

        $scope.editContact = function (contactId) {
            $location.path('/contact-detail/' + contactId);
        };

        $scope.deleteContact = function (contactId) {
            ContactsService.remove(contactId).then(function () {
                $scope.getContacts();
            }, printRequestErrorCause);
        };

        $scope.getContacts();
    }
);

appmodule.controller('ContactCreateUpdateCtr',
    function ($scope, $location, $filter, $routeParams, ContactsService, TerritoriesService) {

        $scope.country = '';

        $scope.newContact = {
            firstName : '',
            lastName : '',
            phone : '',
            email : '',
            city : {}
        };

        $scope.identifyContact = function () {
            if( !$routeParams.id )
                $scope.contact = $scope.newContact;
            else
                ContactsService.fetch($routeParams.id).then(function (result) {
                    $scope.contact = result;
                }, printRequestErrorCause);
        };

        $scope.getTerritories = function() {
            TerritoriesService.find().then(function(result){
                $scope.territories = (result !== 'null') ? result : {};
                $scope.countries = $filter('filter')($scope.territories, { type : 'country'});
            }, printRequestErrorCause);
        };

        $scope.onCountrySelect = function () {
            $scope.cities = $filter('filter')($scope.territories, { parent : $scope.country });
        };

        $scope.createContact = function () {
            ContactsService.create($scope.contact).then(function () {
                $location.path('/contacts');
            }, printRequestErrorCause);
        };

        $scope.updateContact = function () {
            ContactsService.update($scope.contact).then(function () {
                $location.path('/contacts');
            }, printRequestErrorCause);
        };

        $scope.cancel = function () {
            $location.path('/contacts');
        };

        $scope.identifyContact();
        $scope.getTerritories();
    }
);