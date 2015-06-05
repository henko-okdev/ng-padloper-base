'use strict';


var appmodule = angular.module('ng-call-center', [
    'ngRoute',
    'ngResource',
    'smart-table',
    'ng-call-center.service.abstract',
    'ng-call-center.service.territories',
    'ng-call-center.service.contacts',
    'ng-call-center.service.operators',
    'ng-call-center.service.donations',
    'ng-call-center.controller.territories',
    'ng-call-center.controller.contacts',
    'ng-call-center.controller.operators',
    'ng-call-center.controller.donations'
]);

// <-------------------  Routes ------------------------->
appmodule.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/contacts', {
            templateUrl : 'partials/contacts.html',
            controller : 'ContactsListCtr'
        })
        .when('/contact-detail/:id', {
            templateUrl : 'partials/contact-detail.html',
            controller : 'ContactCreateUpdateCtr'
        })
        .when('/contact-creation', {
            templateUrl : 'partials/contact-creation.html',
            controller : 'ContactCreateUpdateCtr'
        })
        .when('/operators', {
            templateUrl : 'partials/operators.html',
            controller : 'OperatorsListCtr'
        })
        .when('/operator-detail/:id', {
            templateUrl : 'partials/operator-detail.html',
            controller : 'OperatorCreateUpdateCtr'
        })
        .when('/operator-creation', {
            templateUrl : 'partials/operator-creation.html',
            controller : 'OperatorCreateUpdateCtr'
        })
        .when('/territories', {
            templateUrl : 'partials/territories.html',
            controller : 'TerritoriesListCtr'
        })
        .when('/territory-detail/:id', {
            templateUrl : 'partials/territory-detail.html',
            controller : 'TerritoriesCreateUpdateCtr'
        })
        .when('/territory-creation', {
            templateUrl : 'partials/territory-creation.html',
            controller : 'TerritoriesCreateUpdateCtr'
        })
        .when('/donations', {
            templateUrl : 'partials/donations.html',
            controller : 'DonationsListCtr'
        })
        .when('/donation-detail/:id', {
            templateUrl : 'partials/donation-detail.html',
            controller : 'DonationsCreateUpdateCtr'
        })
        .when('/donation-creation', {
            templateUrl : 'partials/donation-creation.html',
            controller : 'DonationsCreateUpdateCtr'
        })
        .otherwise({
            redirectTo : '/'
        });

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

var printRequestErrorCause = function (reason) {
    console.log('ERROR: ' + reason);
};