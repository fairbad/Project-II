/**
 * 
 */
angular.module('dPercentile', [])
.controller('AppController', ['$scope', '$interval', function($scope, $interval) {
    
    var imageRoot = 'http://zbeyer.com/stuff/d100';

    // MATH
	$scope.rand = function(min, max) {
        return Math.floor((Math.random() * max) + min);
    };
    
    $scope.generateDOnes = function() {
        $scope.dOnes = {};
        var n = $scope.rand(0, 10);
        $scope.dOnes.n = n;
        $scope.dOnes.img = imageRoot + '/D' + n + '.png';
    };
    
    $scope.generateDTens = function() {
        $scope.dTens = {};
        var n = $scope.rand(0, 10);
        $scope.dTens.n = n;
        $scope.dTens.img = imageRoot + '/Dd' + n + '0.png';
    };
    
    $scope.roll = function(n) {
        $scope.generateDOnes();
        $scope.generateDTens();
        
        $scope.val = $scope.dTens.n*10 + $scope.dOnes.n;
    };
    
    $scope.roll();
}]);

