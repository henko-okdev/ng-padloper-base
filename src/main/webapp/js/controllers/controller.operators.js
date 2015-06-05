'use strict';

var appmodule = angular.module('ng-call-center.controller.operators', []);

appmodule.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});


appmodule.controller('OperatorsListCtr',
    function($scope, $location, OperatorsService) {

        $scope.itemsByPage = 5;

        $scope.getOperators = function() {
            OperatorsService.find().then(function(result){
                $scope.operators = (result !== 'null') ? result : {};
                $scope.displayedOperators = [].concat($scope.operators);
            }, printRequestErrorCause);
        };

        $scope.editOperator = function (operatorId) {
            $location.path('/operator-detail/' + operatorId);
        };

        $scope.deleteOperator = function (operatorId) {
            OperatorsService.remove(operatorId).then(function () {
                $scope.getOperators();
            }, printRequestErrorCause);
        };

        $scope.getOperators();
    }
);

appmodule.controller('OperatorCreateUpdateCtr',
    function ($scope, $location, $filter, $routeParams, OperatorsService) {

        $scope.newOperator = {
            firstName : '',
            lastName : '',
            phone : '',
            email : ''
        };

        $scope.identifyOperator = function () {
            if( !$routeParams.id )
                $scope.operator = $scope.newOperator;
            else
                OperatorsService.fetch($routeParams.id).then(function (result) {
                    $scope.operator = result;
                }, printRequestErrorCause);
        };

        $scope.createOperator = function () {
            OperatorsService.create($scope.operator).then(function () {
                $location.path('/operators');
            }, printRequestErrorCause);
        };

        $scope.updateOperator = function () {
            OperatorsService.update($scope.operator).then(function () {
                $location.path('/operators');
            }, printRequestErrorCause);
        };

        $scope.cancel = function () {
            $location.path('/operators');
        };

        $scope.identifyOperator();
    }
);