'use strict';

var appmodule = angular.module('ng-call-center.controller.territories', ['smart-table']);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

appmodule.controller('TerritoriesListCtr',
    function($scope, $location, $filter, TerritoriesService) {

        $scope.itemsByPage = 5;

        $scope.getTerritories = function() {
            TerritoriesService.find().then(function(result){
                $scope.territories = (result !== 'null') ? result : {};
                $scope.displayedTerritories = [].concat($scope.territories);
            }, printRequestErrorCause);
        };

        $scope.editTerritory = function(territoryId) {
            $location.path('/territory-detail/' + territoryId);
        };

        $scope.deleteTerritory = function (territoryId) {
            TerritoriesService.remove(territoryId).then(function (result) {
                $scope.getTerritories();
            }, printRequestErrorCause)
        };

        $scope.getTerritories();

    });

appmodule.controller('TerritoryDetailCtr',
    function($scope, $routeParams, $location, $filter, TerritoriesService) {

        $scope.getTerritory = function(id) {
            TerritoriesService.fetch(id).then(function (result) {
                $scope.territory = (result !== 'null') ? result : {};

                if($scope.territory.type === 'city') {
                    TerritoriesService.find().then(function (result) {
                        $scope.countries = $filter('filter')(result, {type : 'country'});
                    }, printRequestErrorCause );
                }

            }, printRequestErrorCause);
        };

        $scope.updateTerritory = function() {
            if($scope.territory.type === 'country'){
                TerritoriesService.update($scope.territory).then(function () {
                    $location.path('/territories');
                }, printRequestErrorCause);
            } else {
                TerritoriesService.fetch($scope.territory.parent.id).then(function (result) {
                    $scope.territory.parent = (result !== 'null') ? result : {};

                    TerritoriesService.update($scope.territory).then(function () {
                        $location.path('/territories');
                    }, printRequestErrorCause);

                }, printRequestErrorCause);
            }

        };

        $scope.cancel = function () {
            $location.path('/territories');
        };

        $scope.getTerritory($routeParams.id);

    }
);

appmodule.controller('TerritoryCreationCtr',
    function ($scope, $location, $filter, TerritoriesService) {

        $scope.types = ['city', 'country'];

        $scope.createTerritory = function() {
            if($scope.territory.parentId == undefined)
                $scope.territory.parentId = 0;

            TerritoriesService.fetch($scope.territory.parentId).then(function (result) {
                $scope.territory.parent = (result !== 'null') ? result : {};

                TerritoriesService.create($scope.territory).then(function () {
                    $location.path('/territories');
                }, printRequestErrorCause);

            }, printRequestErrorCause);

        };

        $scope.getTerritories = function() {
            TerritoriesService.find().then(function(result){
                $scope.territories = (result !== 'null') ? result : {};
            }, printRequestErrorCause);
        };

        $scope.getCountries = function () {
            if($scope.territory.type === 'city') {
                $scope.countries = $filter('filter')($scope.territories, { type : 'country'});
            } else {
                $scope.countries = {};
            }
        };

        $scope.cancel = function () {
            $location.path('/territories');
        };

        $scope.newTerritory = function () {
            $scope.territory = {
                name: '',
                type: '',
                zip: '',
                parent: {}
            };
        };

        $scope.newTerritory();
        $scope.getTerritories();

    }
);

appmodule.controller('TerritoriesCreateUpdateCtr',
    function ($scope, $location, $filter, $routeParams, TerritoriesService) {

        $scope.newTerritory = {
            name : '',
            type : '',
            zip : '',
            parent : {}
        };

        $scope.types = ['city', 'country'];

        $scope.identifyTerritory = function () {
            if ( !$routeParams.id )
                $scope.territory = $scope.newTerritory;
            else
                TerritoriesService.fetch($routeParams.id).then(function (result) {
                    $scope.territory = result;
                }, printRequestErrorCause);
        };

        $scope.getTerritories = function() {
            TerritoriesService.find().then(function(result){
                $scope.territories = (result !== 'null') ? result : {};
                $scope.onTypeSelect();
            }, printRequestErrorCause);
        };

        $scope.onTypeSelect = function () {
            if($scope.territory.type === 'city')
                $scope.countries = $filter('filter')($scope.territories, { type : 'country' });
            else
                $scope.countries = null;
        };

        $scope.createTerritory = function () {
            TerritoriesService.create($scope.territory).then(function () {
                $location.path('/territories');
            }, printRequestErrorCause);
        };

        $scope.updateTerritory = function () {
            TerritoriesService.update($scope.territory).then(function () {
                $location.path('/territories');
            }, printRequestErrorCause);
        };

        $scope.cancel = function () {
            $location.path('/territories');
        };

        $scope.identifyTerritory();
        $scope.getTerritories();
    }
);