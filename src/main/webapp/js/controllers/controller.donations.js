'use strict';

var appmodule = angular.module('ng-call-center.controller.donations', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


appmodule.controller('DonationsListCtr',
    function($scope, $location, DonationsService) {

        $scope.itemsByPage = 5;
        $scope.totalAmount = 0;

        $scope.getDonations = function() {
            DonationsService.find().then(function(result){
                $scope.donations = (result !== 'null') ? result : {};
                $scope.displayedDonations = [].concat($scope.donations);
                $scope.calculateTotalAmount();
            }, printRequestErrorCause);
        };

        $scope.editDonation = function (donationId) {
            $location.path('/donation-detail/' + donationId);
        };

        $scope.deleteDonation = function (donationId) {
            DonationsService.remove(donationId).then(function () {
                $scope.getDonations();
            }, printRequestErrorCause);
        };

        $scope.calculateTotalAmount = function () {
            $scope.donations.forEach(function (donation) {
                $scope.totalAmount += donation.amount;
            })
        };

        $scope.getDonations();
    }
);

appmodule.controller('DonationsCreateUpdateCtr',
    function ($scope, $location, $filter, $routeParams, ContactsService, DonationsService, OperatorsService) {

        $scope.newDonation = {
            contact : {},
            operator : {},
            amount : '',
            date : ''
        };

        $scope.identifyDonation = function () {
            if( !$routeParams.id )
                $scope.donation = $scope.newDonation;
            else
                DonationsService.fetch($routeParams.id).then(function (result) {
                    $scope.donation = result;
                }, printRequestErrorCause);
        };

        $scope.getContacts = function() {
            ContactsService.find().then(function(result){
                $scope.contacts = (result !== 'null') ? result : {};
            }, printRequestErrorCause);
        };

        $scope.getOperators = function() {
            OperatorsService.find().then(function(result){
                $scope.operators = (result !== 'null') ? result : {};
            }, printRequestErrorCause);
        };

        $scope.createDonation = function () {
            $scope.donation.date = Date.now();

            DonationsService.create($scope.donation).then(function () {
                $location.path('/donations');
            }, printRequestErrorCause);
        };

        $scope.updateDonation = function () {
            $scope.donation.date = Date.now();

            DonationsService.update($scope.donation).then(function () {
                $location.path('/donations');
            }, printRequestErrorCause);
        };

        $scope.cancel = function () {
            $location.path('/donations');
        };

        $scope.identifyDonation();
        $scope.getContacts();
        $scope.getOperators();
    }
);