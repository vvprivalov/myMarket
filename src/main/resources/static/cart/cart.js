angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/mymarket/api/v1/cart';


    $scope.loadCart = function () {
        $http.get(contextPath + '/' + $localStorage.springWebGuestCartId)
            .then(function successCallback(response) {
                    $scope.cart = response.data;
                }, function failCallback(response) {
                    alert(response.data.messages);
                }
            );
    }

    $scope.loadCart();
});